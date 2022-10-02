package com.chuncongcong.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.health.model.vo.HeartRateDayVo;
import com.chuncongcong.health.model.vo.HeartRateInfoVo;
import com.chuncongcong.health.model.vo.HeartRateQueryVo;

import com.chuncongcong.health.model.vo.RespiratoryRateQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 14:07
 */

@RestController
@RequestMapping("/respiratory/rate")
@Api(value = "respiratory rate", tags = "呼吸率")
public class RespiratoryRateController {

	@GetMapping("/info")
	@ApiOperation("呼吸信息")
	public HeartRateInfoVo info(RespiratoryRateQueryVo heartRateQueryVo) {
		return new HeartRateInfoVo();
	}

	@GetMapping("/month/error")
	@ApiOperation("月异常信息, 数组下标标识日期，1-异常，0-正常")
	public Boolean[] monthError(RespiratoryRateQueryVo heartRateQueryVo) {
		return new Boolean[30];
	}

	@GetMapping("/day/report")
	@ApiOperation("日统计")
	public HeartRateDayVo dayReport(RespiratoryRateQueryVo heartRateQueryVo) {
		return new HeartRateDayVo();
	}

}
