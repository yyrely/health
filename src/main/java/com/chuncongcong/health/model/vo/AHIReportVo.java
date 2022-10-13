package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/13 16:16
 */

@Data
public class AHIReportVo {

	@ApiModelProperty("指数")
	private Integer status;

	@ApiModelProperty("发送次数")
	private Integer happenNum;

	@ApiModelProperty("持续时长")
	private Integer continued;

	@ApiModelProperty("最严重发生时间")
	private LocalDateTime seriousTime;

	@ApiModelProperty("发送次数")
	private Integer num;

	@ApiModelProperty("建议")
	private String propose;
}
