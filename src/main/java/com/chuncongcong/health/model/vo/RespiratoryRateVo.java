package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:41
 */

@Data
public class RespiratoryRateVo {

	@ApiModelProperty("数值")
	private Integer nums;

	@ApiModelProperty("状态")
	private String status;

	@ApiModelProperty("时间")
	private LocalDateTime time;
}
