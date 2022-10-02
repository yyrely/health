package com.chuncongcong.health.model.vo;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:58
 */

@Data
public class SosVo {

	@ApiModelProperty("设备号")
	private String zhdXlh;

	@ApiModelProperty("求助时间")
	private LocalDateTime time;

	@ApiModelProperty("时间")
	private String status;

}
