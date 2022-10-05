package com.chuncongcong.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuncongcong.health.model.vo.HeartRateInfoVo;
import com.chuncongcong.health.model.vo.HeartRateQueryVo;
import com.chuncongcong.health.model.vo.HeartRateVo;

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

	@GetMapping("/list")
	@ApiOperation("根据时间获取心率列表(分页)")
	public IPage<HeartRateVo> list(HeartRateQueryVo heartRateQueryVo, Page page) {
		return new Page<>();
	}

}
