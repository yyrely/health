package com.chuncongcong.health.model.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author HU
 * @date 2022/10/2 13:38
 */

@Data
public class HeartRateInfoVo {

	@ApiModelProperty("平均次数")
	private Integer averageNums;

	@ApiModelProperty("心率列表")
	private List<HeartRateVo> heartRateVoList;

}
