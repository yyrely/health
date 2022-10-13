package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/13 16:26
 */

@Data
public class RespiratoryRateReportVo {

	@ApiModelProperty("总数")
	private Integer totalNum;

	@ApiModelProperty("异常数")
	private Integer abnormalNum;
}
