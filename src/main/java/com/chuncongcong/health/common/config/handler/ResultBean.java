package com.chuncongcong.health.common.config.handler;

import java.io.Serializable;

import com.chuncongcong.health.common.exception.BaseErrorCode;
import com.chuncongcong.health.common.exception.ServiceException;
import lombok.Data;

/**
 * api通用返回数据
 * @author HU
 * @date 2022/1/19 14:28
 */

@Data
public class ResultBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static int SUCCESS = 0;
    public final static int FAIL = 1;

    private int code;

    private T data;

    private String msg;


    public ResultBean() {
        super();
        this.code = SUCCESS;
        this.msg = "操作成功";
    }
    public ResultBean(T data) {
        super();
        this.code = SUCCESS;
        this.data = data;
        this.msg = "操作成功";
        if (data instanceof BaseErrorCode) {
            BaseErrorCode baseErrorCode = (BaseErrorCode) data;
            this.code = baseErrorCode.getCode();
            this.msg = baseErrorCode.getMessage();
            this.data = null;
        }
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL ;
        if(e instanceof ServiceException){
            this.msg = e.getMessage();
            this.code = ((ServiceException) e).getCode();
        }
    }


}
