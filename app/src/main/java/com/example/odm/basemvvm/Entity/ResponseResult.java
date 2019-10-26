package com.example.odm.basemvvm.Entity;

import java.io.Serializable;


/**
 * description: 泛型 实体 包装类
 *              根据后端的返回字段修改
 *
 * author: ODM
 * date: 2019/10/26
 */

public class ResponseResult<T> implements Serializable {

    public static final int RESULT_SUCCESS = 0;

    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess(){
        return RESULT_SUCCESS == errorCode;
    }

}
