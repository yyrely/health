package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/13 16:23
 */

@Data
public class PittsburghSleepQualityReportVo {

	@ApiModelProperty("指数")
	private Integer status;

	@ApiModelProperty("建议")
	private String propose;
}
