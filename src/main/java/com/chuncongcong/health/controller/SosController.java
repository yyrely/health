package com.chuncongcong.health.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.health.common.constant.CommonConstant;
import com.chuncongcong.health.model.vo.SosVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author HU
 * @date 2022/10/2 13:54
 */

@RestController
@RequestMapping(CommonConstant.BASE_URL + "/sos")
@Api(value = "sos", tags = "紧急求助")
public class SosController {

	@GetMapping("/list")
	@ApiOperation("紧急求助列表")
	public List<SosVo> list(SosVo sosVo) {
		return new ArrayList<>();
	}
}
