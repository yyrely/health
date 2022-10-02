package com.chuncongcong.health.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.chuncongcong.health.common.exception.ServiceException;
import com.chuncongcong.health.model.po.VSmStatPo;
import com.chuncongcong.health.model.vo.HealthReportQueryVo;
import com.chuncongcong.health.model.vo.HealthReportVo;
import com.chuncongcong.health.service.IVSmStatService;

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
@RequestMapping("/report")
@Api(value = "report", tags = "每日报表")
public class ReportController {

	@Autowired
	private IVSmStatService ivSmStatService;

	@GetMapping("/health")
	@ApiOperation("根据设备号和日期查询报表")
	public HealthReportVo getReport(HealthReportQueryVo healthReportQueryVo) {
		if(StrUtil.isEmpty(healthReportQueryVo.getZhdXlh()) || StrUtil.isEmpty(healthReportQueryVo.getFlagDate())) {
			throw new ServiceException("参数异常");
		}
		LambdaQueryWrapper<VSmStatPo> query = Wrappers.lambdaQuery(VSmStatPo.class);
		query.eq(VSmStatPo::getZhdXlh, healthReportQueryVo.getZhdXlh());
		LocalDate localFlagDate = LocalDate.parse(healthReportQueryVo.getFlagDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		query.eq(VSmStatPo::getFlagDate, localFlagDate);
		VSmStatPo vSmStatPo = ivSmStatService.getOne(query);
		if(Objects.isNull(vSmStatPo)) {
			return null;
		}

		String pgSleepTime = HttpRequest.post("https://hasura.d.leyinlin.com/v1/graphql")
				.header(Header.CONTENT_TYPE, "application/json")
				.header("x-hasura-admin-secret", "myadminsecretkey")
				.body("{\"query\":\"query {\\r\\n  iot_report(where: {serial_no: {_eq: " + healthReportQueryVo.getZhdXlh() + "}, _and: {dt: {_eq: " + localFlagDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "}}}) {\\r\\n    create_time\\r\\n    bed_time\\r\\n  }\\r\\n}\\r\\n\",\"variables\":{}}")
				.execute().body();

		JSONObject jsonObject = JSONUtil.parseObj(pgSleepTime).getJSONObject("data").getJSONObject("iot_report");
		String upTime = jsonObject.getStr("up_time");
		String bedTime = jsonObject.getStr("bed_time");

		if(StrUtil.isNotBlank(upTime)) {
			vSmStatPo.setSleepStart(LocalDateTimeUtil.parse(upTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}
		if(StrUtil.isNotBlank(bedTime)) {
			vSmStatPo.setSleepEnd(LocalDateTimeUtil.parse(bedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		}
		return BeanUtil.copyProperties(vSmStatPo, HealthReportVo.class);
	}


}
