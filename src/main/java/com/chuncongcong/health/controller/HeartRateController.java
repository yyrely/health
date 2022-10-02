package com.chuncongcong.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.health.model.vo.HeartRateDayVo;
import com.chuncongcong.health.model.vo.HeartRateInfoVo;
import com.chuncongcong.health.model.vo.HeartRateQueryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 14:07
 */

@RestController
@RequestMapping("/heart/rate")
@Api(value = "heart rate", tags = "心率")
public class HeartRateController {

	@GetMapping("/info")
	@ApiOperation("心率信息")
	public HeartRateInfoVo info(HeartRateQueryVo heartRateQueryVo) {
		return new HeartRateInfoVo();
	}

	@GetMapping("/month/error")
	@ApiOperation("月异常信息")
	public Boolean[] monthError(HeartRateQueryVo heartRateQueryVo) {
		return new Boolean[30];
	}

	@GetMapping("/day/report")
	@ApiOperation("日统计")
	public HeartRateDayVo dayReport(HeartRateQueryVo heartRateQueryVo) {
		return new HeartRateDayVo();
	}

}
