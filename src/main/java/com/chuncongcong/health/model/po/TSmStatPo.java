package com.chuncongcong.health.model.po;

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
@TableName("t_sm_stat")
public class TSmStatPo {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @TableField(value = "ZhD_xlh")
    private String deviceCode;

    @MppMultiId
    @TableField(value = "stattime")
    private LocalDateTime statTime;

    @TableField(value = "BreathRate")
    private Integer breathRate;

    @TableField(value = "AHIS")
    private Integer ahis;

    @TableField(value = "AHIO")
    private Integer ahio;

    @TableField(value = "stat1")
    private Integer stat1;

    @TableField(value = "stat2")
    private Integer stat2;

    @TableField(value = "HRate")
    private Integer hRate;

    @TableField(value = "RespRate")
    private Integer respRate;

}
