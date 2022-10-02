package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:18
 */

@Data
public class HealthReportQueryVo {

	@ApiModelProperty("设备号")
	private String zhdXlh;

	@ApiModelProperty("报表日期（yyyy-MM-dd）")
	private String flagDate;
}
