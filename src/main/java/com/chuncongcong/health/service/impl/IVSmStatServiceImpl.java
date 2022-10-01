package com.chuncongcong.health.service.impl;

import org.springframework.stereotype.Service;

import com.chuncongcong.health.mapper.VSmStatMapper;
import com.chuncongcong.health.model.po.VSmStatPo;
import com.chuncongcong.health.service.IVSmStatService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;

/**
 * @author HU
 * @date 2022/1/21 17:48
 */

@Service
public class IVSmStatServiceImpl extends MppServiceImpl<VSmStatMapper, VSmStatPo> implements IVSmStatService {

}
