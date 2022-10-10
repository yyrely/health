package com.chuncongcong.health.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuncongcong.health.common.constant.CommonConstant;
import com.chuncongcong.health.common.exception.ServiceException;
import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.model.po.VSmStatPo;
import com.chuncongcong.health.model.vo.AHIVo;
import com.chuncongcong.health.model.vo.HealthReportQueryVo;
import com.chuncongcong.health.model.vo.HealthReportVo;
import com.chuncongcong.health.model.vo.HeartRateVo;
import com.chuncongcong.health.model.vo.RespiratoryRateVo;
import com.chuncongcong.health.model.vo.SleepInfoVo;
import com.chuncongcong.health.model.vo.SleepStatusVo;
import com.chuncongcong.health.service.ITSmStatService;
import com.chuncongcong.health.service.IVSmStatService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/1 17:18
 */

@RestController
@RequestMapping(CommonConstant.BASE_URL + "/report")
@Api(value = "report", tags = "每日报表")
public class ReportController {

    @Autowired
    private IVSmStatService ivSmStatService;

    @Autowired
    private ITSmStatService itSmStatService;

    @GetMapping("/health")
    @ApiOperation("根据设备号和日期查询报表")
    public HealthReportVo getReport(HealthReportQueryVo healthReportQueryVo) {
        if (StrUtil.isEmpty(healthReportQueryVo.getDeviceCode())
            || StrUtil.isEmpty(healthReportQueryVo.getStartTime())) {
            throw new ServiceException("参数异常");
        }

        HealthReportVo healthReportVo = new HealthReportVo();
        LocalDate startTime =
            LocalDate.parse(healthReportQueryVo.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endTime =
            LocalDate.parse(healthReportQueryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LambdaQueryWrapper<VSmStatPo> vSmStatPoQuery = Wrappers.lambdaQuery(VSmStatPo.class);
        vSmStatPoQuery.eq(VSmStatPo::getDeviceCode, healthReportQueryVo.getDeviceCode());

        vSmStatPoQuery.eq(VSmStatPo::getFlagDate, startTime);
        VSmStatPo vSmStatPo = ivSmStatService.getOne(vSmStatPoQuery);
        if (Objects.nonNull(vSmStatPo)) {
            String query = "{\"query\":\"query {\\r\\n  iot_report(where: {serial_no: {_eq: \\\""+ healthReportQueryVo.getDeviceCode() +"\\\"}, _and: {dt: {_eq: "+ startTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")) +"}}}) {\\r\\n    create_time\\r\\n    bed_time\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{}}";
            String pgSleepTime = HttpRequest.post("https://hasura.d.leyinlin.com/v1/graphql")
                .header(Header.CONTENT_TYPE, "application/json").header("x-hasura-admin-secret", "myadminsecretkey")
                .body(query).execute().body();
			try {
				JSONObject jsonObject = JSONUtil.parseObj(pgSleepTime).getJSONObject("data").getJSONObject("iot_report");
				String upTime = jsonObject.getStr("up_time");
				String bedTime = jsonObject.getStr("bed_time");
				if (StrUtil.isNotBlank(upTime)) {
					vSmStatPo.setSleepStart(LocalDateTimeUtil.parse(upTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
				if (StrUtil.isNotBlank(bedTime)) {
					vSmStatPo.setSleepEnd(LocalDateTimeUtil.parse(bedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
				}
			} catch (Exception e) {

			}
            healthReportVo.setSleepInfoVo(BeanUtil.copyProperties(vSmStatPo, SleepInfoVo.class));
        }

        LambdaQueryWrapper<TSmStatPo> tSmStatPoQuery = Wrappers.lambdaQuery(TSmStatPo.class);
        tSmStatPoQuery.eq(TSmStatPo::getDeviceCode, healthReportQueryVo.getDeviceCode());
        tSmStatPoQuery.ge(TSmStatPo::getStatTime, startTime.atStartOfDay());
        tSmStatPoQuery.le(TSmStatPo::getStatTime, LocalDateTime.of(endTime, LocalTime.MAX));
        List<TSmStatPo> tSmStatPos = itSmStatService.list(tSmStatPoQuery);
        if (CollectionUtil.isNotEmpty(tSmStatPos)) {
            List<SleepStatusVo> sleepStatusVos = new ArrayList<>();
            List<HeartRateVo> heartRateVos = new ArrayList<>();
            List<RespiratoryRateVo> respiratoryRateVos = new ArrayList<>();
            List<AHIVo> ahiVos = new ArrayList<>();
            for (TSmStatPo tSmStatPo : tSmStatPos) {
                SleepStatusVo sleepStatusVo = new SleepStatusVo();
                sleepStatusVo.setStatus(tSmStatPo.getStat2());
                sleepStatusVo.setTime(tSmStatPo.getStatTime());
                sleepStatusVos.add(sleepStatusVo);

                HeartRateVo heartRateVo = new HeartRateVo();
                heartRateVo.setNums(tSmStatPo.getHRate());
                heartRateVo.setTime(tSmStatPo.getStatTime());
                heartRateVos.add(heartRateVo);

                RespiratoryRateVo respiratoryRateVo = new RespiratoryRateVo();
                respiratoryRateVo.setNums(tSmStatPo.getRespRate());
                respiratoryRateVo.setTime(tSmStatPo.getStatTime());
                respiratoryRateVos.add(respiratoryRateVo);

                AHIVo ahiVo = new AHIVo();
                ahiVo.setNums(tSmStatPo.getAhio() == null ? 0 : tSmStatPo.getAhio() +
                        (tSmStatPo.getAhis() == null ? 0 : tSmStatPo.getAhis()));
                ahiVo.setTime(tSmStatPo.getStatTime());
                ahiVos.add(ahiVo);
            }
            healthReportVo.setSleepStatusVos(sleepStatusVos);
            healthReportVo.setHeartRateVos(heartRateVos);
            healthReportVo.setRespiratoryRateVos(respiratoryRateVos);
            healthReportVo.setAhiVos(ahiVos);
        }
        return healthReportVo;
    }

}
