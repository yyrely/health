package com.chuncongcong.health.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuncongcong.health.model.vo.RespiratoryRateInfoVo;
import com.chuncongcong.health.model.vo.RespiratoryRateQueryVo;
import com.chuncongcong.health.model.vo.RespiratoryRateVo;

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
	public RespiratoryRateInfoVo info(RespiratoryRateQueryVo respiratoryRateQueryVo) {
		return new RespiratoryRateInfoVo();
	}

	@GetMapping("/list")
	@ApiOperation("根据时间获取呼吸率列表(分页)")
	public IPage<RespiratoryRateVo> monthError(RespiratoryRateQueryVo respiratoryRateQueryVo, Page page) {
		return new Page<>();
	}


}
