package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 14:36
 */

@Data
public class SleepQualityResultVo {

	@ApiModelProperty("睡眠状态集合（下标对应日期）")
	private SleepStatusListVo[] sleepStatusListVos;

	@ApiModelProperty("匹兹堡睡眠质量集合（下标对应日期）")
	private Integer[] pittsburghSleepQualityList;
}
