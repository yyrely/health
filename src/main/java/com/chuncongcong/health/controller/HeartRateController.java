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
import com.chuncongcong.health.model.vo.HeartRateInfoVo;
import com.chuncongcong.health.model.vo.HeartRateQueryVo;
import com.chuncongcong.health.model.vo.HeartRateVo;
import com.chuncongcong.health.service.ITSmStatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 14:07
 */

@RestController
@RequestMapping(CommonConstant.BASE_URL + "/heart/rate")
@Api(value = "heart rate", tags = "心率")
public class HeartRateController {

	@Autowired
	private ITSmStatService itSmStatService;

	@GetMapping("/info")
	@ApiOperation("心率信息")
	public HeartRateInfoVo info(HeartRateQueryVo heartRateQueryVo) {
		return new HeartRateInfoVo();
	}

	@GetMapping("/list")
	@ApiOperation("根据时间获取心率列表(分页)")
	public IPage<HeartRateVo> list(HeartRateQueryVo heartRateQueryVo, Page page) {
		LocalDate startTime =
				LocalDate.parse(heartRateQueryVo.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endTime =
				LocalDate.parse(heartRateQueryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LambdaQueryWrapper<TSmStatPo> tSmStatPoQuery = Wrappers.lambdaQuery(TSmStatPo.class);
		tSmStatPoQuery.eq(TSmStatPo::getDeviceCode, heartRateQueryVo.getDeviceCode());
		tSmStatPoQuery.ge(TSmStatPo::getStatTime, startTime.atStartOfDay());
		tSmStatPoQuery.le(TSmStatPo::getStatTime, LocalDateTime.of(endTime, LocalTime.MAX));
		IPage<TSmStatPo> tSmStatPos = itSmStatService.page(page, tSmStatPoQuery);

		IPage<HeartRateVo> respiratoryRateVoIPage = new Page<>(tSmStatPos.getCurrent(), tSmStatPos.getSize(), tSmStatPos.getTotal());
		List<HeartRateVo> heartRateVos = new ArrayList<>();
		for (TSmStatPo tSmStatPo : tSmStatPos.getRecords()) {
			HeartRateVo heartRateVo = new HeartRateVo();
			heartRateVo.setNums(tSmStatPo.getHRate());
			heartRateVo.setTime(tSmStatPo.getStatTime());
			heartRateVos.add(heartRateVo);
		}
		respiratoryRateVoIPage.setRecords(heartRateVos);
		return respiratoryRateVoIPage;
	}

}
