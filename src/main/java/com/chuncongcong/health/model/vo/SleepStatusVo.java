package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:30
 */

@Data
public class SleepStatusVo {

	@ApiModelProperty("睡眠状态, 0: 深睡,  1: 浅睡, 2：快速眼动  3：觉醒  4：无人")
	private Integer status;

	@ApiModelProperty("时间")
	private LocalDateTime time;
}
