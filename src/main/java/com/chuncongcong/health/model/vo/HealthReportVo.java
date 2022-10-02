package com.chuncongcong.health.model.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Hu
 * @since 2022-01-21
 */
@Data
public class HealthReportVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("设备号")
    private String zhdXlh;

    @ApiModelProperty("日报日期")
    private LocalDate flagDate;

    @ApiModelProperty("睡眠质量")
    private Integer sleepQuality;

    @ApiModelProperty("睡眠状态列表")
    private List<SleepStatusVo> sleepStatusVos;

    @ApiModelProperty("上床时间")
    private LocalDateTime upDedTime;

    @ApiModelProperty("睡眠开始时间")
    private LocalDateTime sleepStart;

    @ApiModelProperty("睡眠结束时间")
    private LocalDateTime sleepEnd;

    @ApiModelProperty("睡眠延迟")
    private Long sleepLatency;

    @ApiModelProperty("总睡眠时间")
    private Integer sleepAll;

    @ApiModelProperty("卧床时间")
    private Integer sleepBed;

    @ApiModelProperty("离床时间")
    private Integer removeBed;

    @ApiModelProperty("离床次数")
    private Integer removeBedNums;

    @ApiModelProperty("体动次数")
    private Integer doNums;

    @ApiModelProperty("睡眠效率")
    private Integer sleepEfficiency;

    @ApiModelProperty("入睡后醒来时间")
    private Integer waso;

    @ApiModelProperty("入睡后醒来次数")
    private Integer na;

    @ApiModelProperty("睡眠比率/日")
    private Integer ratioOfSleep;

    @ApiModelProperty("每小时低通量/日")
    private Integer ahis;

    @ApiModelProperty("每小时呼吸暂停/日")
    private Integer ahio;

    @ApiModelProperty("心率信息")
    private HeartRateInfoVo heartRateVo;

    @ApiModelProperty("呼吸率信息")
    private RespiratoryRateInfoVo respiratoryRateVo;

    @ApiModelProperty("AHI指数")
    private AHIVo ahiVo;

    @ApiModelProperty("匹兹堡睡眠质量")
    private PittsburghSleepQualityVo pittsburghSleepQualityVo;

}
