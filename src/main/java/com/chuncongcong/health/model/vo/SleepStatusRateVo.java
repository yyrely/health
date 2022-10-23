package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/23 16:24
 */

@Data
public class SleepStatusRateVo {

	@ApiModelProperty("睡眠状态, 0: 深睡,  1: 浅睡, 2：快速眼动  3：觉醒  4：无人")
	private Integer status;

	@ApiModelProperty("次数")
	private Integer size;
}
