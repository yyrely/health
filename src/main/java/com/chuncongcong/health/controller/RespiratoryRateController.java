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
import com.chuncongcong.health.model.vo.RespiratoryRateInfoVo;
import com.chuncongcong.health.model.vo.RespiratoryRateQueryVo;
import com.chuncongcong.health.model.vo.RespiratoryRateVo;
import com.chuncongcong.health.service.ITSmStatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 14:07
 */

@RestController
@RequestMapping(CommonConstant.BASE_URL + "/respiratory/rate")
@Api(value = "respiratory rate", tags = "呼吸率")
public class RespiratoryRateController {

	@Autowired
	private ITSmStatService itSmStatService;

	@GetMapping("/info")
	@ApiOperation("呼吸信息")
	public RespiratoryRateInfoVo info(RespiratoryRateQueryVo respiratoryRateQueryVo) {
		return new RespiratoryRateInfoVo();
	}

	@GetMapping("/list")
	@ApiOperation("根据时间获取呼吸率列表(分页)")
	public IPage<RespiratoryRateVo> monthError(RespiratoryRateQueryVo respiratoryRateQueryVo, Page page) {
		LocalDate startTime =
				LocalDate.parse(respiratoryRateQueryVo.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endTime =
				LocalDate.parse(respiratoryRateQueryVo.getEndTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LambdaQueryWrapper<TSmStatPo> tSmStatPoQuery = Wrappers.lambdaQuery(TSmStatPo.class);
		tSmStatPoQuery.eq(TSmStatPo::getDeviceCode, respiratoryRateQueryVo.getDeviceCode());
		tSmStatPoQuery.ge(TSmStatPo::getStatTime, startTime.atStartOfDay());
		tSmStatPoQuery.le(TSmStatPo::getStatTime, LocalDateTime.of(endTime, LocalTime.MAX));
		IPage<TSmStatPo> tSmStatPos = itSmStatService.page(page, tSmStatPoQuery);

		IPage<RespiratoryRateVo> respiratoryRateVoIPage = new Page<>(tSmStatPos.getCurrent(), tSmStatPos.getSize(), tSmStatPos.getTotal());
		List<RespiratoryRateVo> respiratoryRateVos = new ArrayList<>();
		for (TSmStatPo tSmStatPo : tSmStatPos.getRecords()) {
			RespiratoryRateVo respiratoryRateVo = new RespiratoryRateVo();
			respiratoryRateVo.setNums(tSmStatPo.getRespRate());
			respiratoryRateVo.setTime(tSmStatPo.getStatTime());
			respiratoryRateVos.add(respiratoryRateVo);
		}
		respiratoryRateVoIPage.setRecords(respiratoryRateVos);
		return respiratoryRateVoIPage;
	}


}
