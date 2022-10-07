package com.chuncongcong.health.service.impl;

import org.springframework.stereotype.Service;

import com.chuncongcong.health.mapper.TSmStatMapper;
import com.chuncongcong.health.model.po.TSmStatPo;
import com.chuncongcong.health.service.ITSmStatService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;

/**
 * @author HU
 * @date 2022/1/21 17:48
 */

@Service
public class ITSmStatServiceImpl extends MppServiceImpl<TSmStatMapper, TSmStatPo> implements ITSmStatService {

}
