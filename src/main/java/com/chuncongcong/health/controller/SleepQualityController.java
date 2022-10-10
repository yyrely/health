package com.chuncongcong.health.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuncongcong.health.common.constant.CommonConstant;
import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.model.vo.SleepQualityQueryVo;
import com.chuncongcong.health.model.vo.SleepQualityResultVo;
import com.chuncongcong.health.model.vo.SleepStatusVo;
import com.chuncongcong.health.service.ITSmStatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 14:33
 */

@RestController
@RequestMapping(CommonConstant.BASE_URL + "/sleep/quality")
@Api(value = "sleep quality", tags = "睡眠质量")
public class SleepQualityController {

	@Autowired
	private ITSmStatService itSmStatService;

	@GetMapping("/info")
	@ApiOperation("分析报告")
	public SleepQualityResultVo info(SleepQualityQueryVo sleepQualityQueryVo, Page page) {
		LocalDate startTime =
				LocalDate.parse(sleepQualityQueryVo.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endTime =
				LocalDate.parse(sleepQualityQueryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		SleepQualityResultVo sleepQualityResultVo = new SleepQualityResultVo();

		LambdaQueryWrapper<TSmStatPo> tSmStatPoQuery = Wrappers.lambdaQuery(TSmStatPo.class);
		tSmStatPoQuery.eq(TSmStatPo::getDeviceCode, sleepQualityQueryVo.getDeviceCode());
		tSmStatPoQuery.ge(TSmStatPo::getStatTime, startTime.atStartOfDay());
		tSmStatPoQuery.le(TSmStatPo::getStatTime, LocalDateTime.of(endTime, LocalTime.MAX));
		Page<TSmStatPo> tSmStatPoPage = itSmStatService.page(page, tSmStatPoQuery);
		IPage<SleepStatusVo> sleepStatusVoIPage = new Page<>(tSmStatPoPage.getCurrent(), tSmStatPoPage.getSize(), tSmStatPoPage.getTotal());
		List<SleepStatusVo> sleepStatusVoList = new ArrayList<>();
		for (TSmStatPo tSmStatPo : tSmStatPoPage.getRecords()) {
			SleepStatusVo sleepStatusVo = new SleepStatusVo();
			sleepStatusVo.setStatus(tSmStatPo.getStat2());
			sleepStatusVo.setTime(tSmStatPo.getStatTime());
			sleepStatusVoList.add(sleepStatusVo);
		}
		sleepStatusVoIPage.setRecords(sleepStatusVoList);
		sleepQualityResultVo.setSleepStatusVoPage(sleepStatusVoIPage);

		return sleepQualityResultVo;
	}
}
