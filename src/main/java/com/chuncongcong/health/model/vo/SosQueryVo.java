package com.chuncongcong.health.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:56
 */

@Data
public class SosQueryVo {

	@ApiModelProperty("设备号")
	String deviceCode;

}
