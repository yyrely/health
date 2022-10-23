package com.chuncongcong.health.mapper;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.model.vo.SleepStatusRateVo;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;

/**
 * @author Hu
 * @since 2022-01-21
 */

@Mapper
public interface TSmStatMapper extends MppBaseMapper<TSmStatPo> {

	List<SleepStatusRateVo> sleepStatus(@Param("deviceCode") String deviceCode, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
