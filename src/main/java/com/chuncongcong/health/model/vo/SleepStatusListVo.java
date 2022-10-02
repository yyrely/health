package com.chuncongcong.health.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 14:37
 */

@Data
public class SleepStatusListVo {

	@ApiModelProperty("睡眠状态列表")
	private List<SleepStatusVo> sleepStatusVoList;
}
