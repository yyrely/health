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

	@ApiModelProperty("睡眠状态")
	private String status;

	@ApiModelProperty("睡眠状态开始时间")
	private LocalDateTime startTime;

	@ApiModelProperty("睡眠状态结束时间")
	private LocalDateTime endTime;
}
