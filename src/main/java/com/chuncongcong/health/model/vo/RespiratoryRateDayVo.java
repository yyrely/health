package com.chuncongcong.health.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:38
 */

@Data
public class RespiratoryRateDayVo {

	@ApiModelProperty("呼吸率列表")
	private List<RespiratoryRateVo> respiratoryRateVos;

	@ApiModelProperty("异常呼吸率列表")
	private List<RespiratoryRateVo> errorRespiratoryRateVos;

}
