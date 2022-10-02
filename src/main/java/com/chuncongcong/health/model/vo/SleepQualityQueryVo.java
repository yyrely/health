package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:18
 */

@Data
public class SleepQualityQueryVo {

	@ApiModelProperty("设备号")
	private String zhdXlh;

	@ApiModelProperty("查询开始日期（yyyy-MM-dd）")
	private String startTime;

	@ApiModelProperty("查询结束日期（yyyy-MM-dd）")
	private String endTime;
}
