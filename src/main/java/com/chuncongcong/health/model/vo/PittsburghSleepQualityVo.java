package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:43
 */

@Data
public class PittsburghSleepQualityVo {

	@ApiModelProperty("指数")
	private Integer nums;

	@ApiModelProperty("日期")
	private LocalDateTime time;
}
