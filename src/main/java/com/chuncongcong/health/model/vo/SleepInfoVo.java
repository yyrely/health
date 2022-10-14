package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/5 14:02
 */

@Data
public class SleepInfoVo {

	@ApiModelProperty("睡眠质量")
	private Integer sleepQuality;

	@ApiModelProperty("上床时间")
	private LocalDateTime upDedTime;

	@ApiModelProperty("睡眠开始时间")
	private LocalDateTime sleepStart;

	@ApiModelProperty("睡眠结束时间")
	private LocalDateTime sleepEnd;

	@ApiModelProperty("睡眠延迟")
	private Long sleepLatency;

	@ApiModelProperty("总睡眠时间")
	private Integer sleepAll;

	@ApiModelProperty("卧床时间")
	private Integer sleepBed;

	@ApiModelProperty("离床时间")
	private Integer removeBed;

	@ApiModelProperty("离床时间状态")
	private Integer removeBedStatus;

	@ApiModelProperty("离床次数")
	private Integer removeBedNums;

	@ApiModelProperty("离床次数状态")
	private Integer removeBedNumsStatus;

	@ApiModelProperty("体动次数")
	private Integer doNums;

	@ApiModelProperty("体动次数状态")
	private Integer doNumsStatus;

	@ApiModelProperty("睡眠效率")
	private Integer sleepEfficiency;

	@ApiModelProperty("睡眠效率状态")
	private Integer sleepEfficiencyStatus;

	@ApiModelProperty("入睡后醒来时间")
	private Integer waso;

	@ApiModelProperty("入睡后醒来次数")
	private Integer na;

	@ApiModelProperty("睡眠比率/日")
	private Integer ratioOfSleep;

	@ApiModelProperty("每小时低通量/日")
	private Integer ahis;

	@ApiModelProperty("每小时呼吸暂停/日")
	private Integer ahio;
}
