package com.chuncongcong.health.service;

import java.time.LocalDateTime;
import java.util.List;

import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.model.vo.SleepStatusRateVo;
import com.github.jeffreyning.mybatisplus.service.IMppService;

/**
 * @author HU
 * @date 2022/1/21 17:47
 */

public interface ITSmStatService extends IMppService<TSmStatPo> {

	List<SleepStatusRateVo> sleepStatus(String deviceCode, LocalDateTime startTime, LocalDateTime endTime);
}
