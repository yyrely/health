package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:46
 */

@Data
public class AHIVo {

	@ApiModelProperty("指数")
	private Integer nums;

	@ApiModelProperty("指数状态")
	private String status;

	@ApiModelProperty("发送次数")
	private Integer happenNums;

	@ApiModelProperty("最严重发送时间段")
	private String severityTime;

	@ApiModelProperty("最严重持续时间")
	private Integer severityPersistTime;

	@ApiModelProperty("建议")
	private String suggest;
}
