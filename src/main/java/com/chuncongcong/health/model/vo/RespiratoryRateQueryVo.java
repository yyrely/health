package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:56
 */

@Data
public class RespiratoryRateQueryVo {

	@ApiModelProperty("设备号")
	private String zhdXlh;

	@ApiModelProperty("年月")
	private String yearMonth;

	@ApiModelProperty("年月日")
	private String time;

}
