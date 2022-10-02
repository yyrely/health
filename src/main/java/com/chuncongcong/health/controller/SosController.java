package com.chuncongcong.health.controller;

import java.util.ArrayList;
import java.util.List;

import com.chuncongcong.health.model.vo.SosQueryVo;
import com.chuncongcong.health.model.vo.SosVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HU
 * @date 2022/10/2 13:54
 */

@RestController
@RequestMapping("/sos")
@Api(value = "sos", tags = "紧急求助")
public class SosController {


	@GetMapping("/list")
	@ApiOperation("紧急求助列表")
	public List<SosVo> list(SosQueryVo sosQueryVo) {
		return new ArrayList<>();
	}
}
