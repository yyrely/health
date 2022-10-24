package com.chuncongcong.health.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuncongcong.health.common.constant.CommonConstant;
import com.chuncongcong.health.common.exception.ServiceException;
import com.chuncongcong.health.model.po.VSmStatPo;
import com.chuncongcong.health.model.vo.AHIReportVo;
import com.chuncongcong.health.model.vo.HealthReportQueryVo;
import com.chuncongcong.health.model.vo.HealthReportVo;
import com.chuncongcong.health.model.vo.HeartRateReportVo;
import com.chuncongcong.health.model.vo.RespiratoryRateReportVo;
import com.chuncongcong.health.model.vo.SleepInfoVo;
import com.chuncongcong.health.model.vo.SleepStatusRateVo;
import com.chuncongcong.health.service.ITSmStatService;
import com.chuncongcong.health.service.IVSmStatService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
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
        AHIReportVo ahiReportVo = new AHIReportVo();
        if (Objects.nonNull(vSmStatPo)) {
			try {
                String query = "{\"query\":\"query {\\r\\n  iot_report(where: {serial_no: {_eq: \\\""+ healthReportQueryVo.getDeviceCode() +"\\\"}, _and: {dt: {_eq: "+ startTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")) +"}}}) {\\r\\n    create_time\\r\\n    bed_time\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{}}";
                String pgSleepTime = HttpRequest.post("https://hasura.d.leyinlin.com/v1/graphql")
                        .header(Header.CONTENT_TYPE, "application/json").header("x-hasura-admin-secret", "myadminsecretkey")
                        .body(query).execute().body();
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
            ahiReportVo.setStatus(healthReportVo.getSleepInfoVo().getAhis());
            ahiReportVo.setHappenNum(healthReportVo.getSleepInfoVo().getAhio());
        }
//        LambdaQueryWrapper<TSmStatPo> tSmStatPoQuery = Wrappers.lambdaQuery(TSmStatPo.class);
//        tSmStatPoQuery.eq(TSmStatPo::getDeviceCode, healthReportQueryVo.getDeviceCode());
//        tSmStatPoQuery.ge(TSmStatPo::getStatTime, startTime.atStartOfDay());
//        tSmStatPoQuery.le(TSmStatPo::getStatTime, LocalDateTime.of(endTime, LocalTime.MAX));
//        List<TSmStatPo> tSmStatPos = itSmStatService.list(tSmStatPoQuery);

        Map<Integer, Integer> sleepStatusRateMap = new HashMap<>();
        sleepStatusRateMap.put(0, 0);
        sleepStatusRateMap.put(1, 0);
        sleepStatusRateMap.put(2, 0);
        sleepStatusRateMap.put(3, 0);
        sleepStatusRateMap.put(4, 0);
        List<SleepStatusRateVo> sleepStatusRateVos = itSmStatService.sleepStatus(healthReportQueryVo.getDeviceCode(), startTime.atStartOfDay(), LocalDateTime.of(endTime, LocalTime.MAX));
        for (SleepStatusRateVo sleepStatusRateVo : sleepStatusRateVos) {
            sleepStatusRateMap.put(sleepStatusRateVo.getStatus(), sleepStatusRateVo.getSize());
        }
        healthReportVo.setSleepStatusMap(sleepStatusRateMap);

        HeartRateReportVo heartRateReportVo = new HeartRateReportVo();
        RespiratoryRateReportVo respiratoryRateReportVo = new RespiratoryRateReportVo();
        Integer heartRateNums = 0;
        Integer respiratoryRateNums = 0;
        heartRateReportVo.setTotalNum(heartRateNums);
        respiratoryRateReportVo.setTotalNum(respiratoryRateNums);
        try {
            String query = "{\"query\":\"query {\\r\\n  iot_breath(where: {serial_no: {_eq: \\\""+ healthReportQueryVo.getDeviceCode() +"\\\"}, measure_time: {_lte: \\\""+ LocalDateTime.of(endTime, LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +"\\\", _gte: \\\""+ startTime.atStartOfDay().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) +"\\\"}}, order_by: {measure_time: desc}, limit: 100) {\\r\\n    heart_rate\\r\\n    breath_rate\\r\\n    measure_time\\r\\n    serial_no\\r\\n  }\\r\\n}\",\"variables\":{}}";
            String iotBreathResult = HttpRequest.post("https://hasura.d.leyinlin.com/v1/graphql")
                    .header(Header.CONTENT_TYPE, "application/json").header("x-hasura-admin-secret", "myadminsecretkey")
                    .body(query).execute().body();
            JSONArray iotBreathJsonArray = JSONUtil.parseObj(iotBreathResult).getJSONObject("data").getJSONArray("iot_breath");
                if(Objects.nonNull(iotBreathJsonArray) && iotBreathJsonArray.size() > 0) {
                for (int i = 0; i < iotBreathJsonArray.size(); i++) {
                    JSONObject iotBreathObject = iotBreathJsonArray.getJSONObject(i);
                    Integer heartRate = iotBreathObject.getInt("heart_rate");
                    if(Objects.nonNull(heartRate) && heartRate > 0) {
                        heartRateNums += heartRate;
                    }
                    Integer breathRate = iotBreathObject.getInt("breath_rate");
                    if(Objects.nonNull(breathRate) && breathRate > 0) {
                        respiratoryRateNums += breathRate;
                    }
                }
                heartRateReportVo.setTotalNum(heartRateNums/iotBreathJsonArray.size());
                respiratoryRateReportVo.setTotalNum(respiratoryRateNums/iotBreathJsonArray.size());
            }
        } catch (Exception e) {

        }
        healthReportVo.setHeartRateVos(heartRateReportVo);
        healthReportVo.setRespiratoryRateVos(respiratoryRateReportVo);
        healthReportVo.setAhiVos(ahiReportVo);
        return healthReportVo;
    }

}
