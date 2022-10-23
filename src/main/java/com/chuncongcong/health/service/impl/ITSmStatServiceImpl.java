package com.chuncongcong.health.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.chuncongcong.health.mapper.TSmStatMapper;
import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.model.vo.SleepStatusRateVo;
import com.chuncongcong.health.service.ITSmStatService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;

/**
 * @author HU
 * @date 2022/1/21 17:48
 */

@Service
public class ITSmStatServiceImpl extends MppServiceImpl<TSmStatMapper, TSmStatPo> implements ITSmStatService {

	@Override
	public List<SleepStatusRateVo> sleepStatus(String deviceCode, LocalDateTime startTime, LocalDateTime endTime) {
		return this.baseMapper.sleepStatus(deviceCode, startTime, endTime);
	}
}
