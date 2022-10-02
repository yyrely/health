package com.chuncongcong.health.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:38
 */

@Data
public class RespiratoryRateInfoVo {

	@ApiModelProperty("次数")
	private Integer nums;

	@ApiModelProperty("异常次数")
	private Integer errorNums;

	@ApiModelProperty("状态")
	private String status;

	@ApiModelProperty("呼吸率列表")
	private List<RespiratoryRateVo> respiratoryRateVos;

}
