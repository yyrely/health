package com.chuncongcong.health.model.po;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;

import lombok.Data;

/**
 * @author Hu
 * @since 2022-01-21
 */
@Data
@TableName("v_sm_stat")
public class VSmStatPo {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "ZhD_xlh")
    private String deviceCode;

    @MppMultiId
    @TableField(value = "flagdate")
    private LocalDate flagDate;

    @TableField(value = "sleepStart")
    private LocalDateTime sleepStart;

    @TableField(value = "sleepEnd")
    private LocalDateTime sleepEnd;

    @TableField(value = "sleepLatency")
    private Long sleepLatency;

    @TableField(value = "sleepAll")
    private Integer sleepAll;

    @TableField(value = "sleepBed")
    private Integer sleepBed;

    @TableField(value = "waso")
    private Integer waso;

    @TableField(value = "NA")
    private Integer na;

    @TableField(value = "ratio_of_sleep")
    private Integer ratioOfSleep;

    @TableField(value = "AHIS")
    private Integer ahis;

    @TableField(value = "AHIO")
    private Integer ahio;

}
