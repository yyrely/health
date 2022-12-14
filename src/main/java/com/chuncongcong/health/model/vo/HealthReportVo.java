package com.chuncongcong.health.model.vo;

import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Hu
 * @since 2022-01-21
 */
@Data
public class HealthReportVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("睡眠信息")
    private SleepInfoVo sleepInfoVo;

    @ApiModelProperty("睡眠状态列表")
    private Map<Integer, Integer> sleepStatusMap;

    @ApiModelProperty("心率信息")
    private HeartRateReportVo heartRateVos;

    @ApiModelProperty("呼吸率信息")
    private RespiratoryRateReportVo respiratoryRateVos;

    @ApiModelProperty("AHI指数列表")
    private AHIReportVo ahiVos;

    @ApiModelProperty("匹兹堡睡眠质量列表")
    private PittsburghSleepQualityReportVo pittsburghSleepQualityVos;

}
