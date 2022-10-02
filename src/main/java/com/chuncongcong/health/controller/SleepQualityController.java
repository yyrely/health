package com.chuncongcong.health.controller;

import com.chuncongcong.health.model.vo.SleepQualityQueryVo;
import com.chuncongcong.health.model.vo.SleepQualityResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HU
 * @date 2022/10/2 14:33
 */

@RestController
@RequestMapping("/sleep/quality")
@Api(value = "sleep quality", tags = "睡眠质量")
public class SleepQualityController {

	@GetMapping("/info")
	@ApiOperation("分析报告")
	public SleepQualityResultVo info(SleepQualityQueryVo sleepQualityQueryVo) {
		return new SleepQualityResultVo();
	}
}
