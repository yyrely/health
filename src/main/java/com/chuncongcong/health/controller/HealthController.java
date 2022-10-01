package com.chuncongcong.health.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuncongcong.health.common.exception.ServiceException;
import com.chuncongcong.health.model.po.VSmStatPo;
import com.chuncongcong.health.service.IVSmStatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HU
 * @date 2022/10/1 17:18
 */

@RestController
@RequestMapping("/health")
public class HealthController {

	@Autowired
	private IVSmStatService ivSmStatService;

	@GetMapping("/report")
	public VSmStatPo getReport(String zhdXlh, String flagDate) {
		if(StrUtil.isEmpty(zhdXlh) || StrUtil.isEmpty(flagDate)) {
			throw new ServiceException("参数异常");
		}
		LambdaQueryWrapper<VSmStatPo> query = Wrappers.lambdaQuery(VSmStatPo.class);
		query.eq(VSmStatPo::getZhdXlh, zhdXlh);
		query.eq(VSmStatPo::getFlagDate, LocalDate.parse(flagDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		return ivSmStatService.getOne(query);
	}

}
