package com.chuncongcong.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.health.common.constant.CommonConstant;
import com.chuncongcong.health.model.vo.SleepQualityQueryVo;
import com.chuncongcong.health.model.vo.SleepQualityResultVo;

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

	@GetMapping("/info")
	@ApiOperation("分析报告")
	public SleepQualityResultVo info(SleepQualityQueryVo sleepQualityQueryVo) {
		return new SleepQualityResultVo();
	}
}
