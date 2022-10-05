package com.chuncongcong.health.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 14:36
 */

@Data
public class SleepQualityResultVo {

	@ApiModelProperty("睡眠状态集合")
	private List<SleepStatusVo> sleepStatusVoList;

	@ApiModelProperty("匹兹堡睡眠质量集合")
	private List<PittsburghSleepQualityVo> pittsburghSleepQualityList;
}
