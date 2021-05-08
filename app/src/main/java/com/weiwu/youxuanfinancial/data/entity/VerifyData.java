package com.weiwu.youxuanfinancial.data.entity;

import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 09:49 
 */
public class VerifyData {
    /**
     * code : 200
     * msg : 发送成功
     * data : []
     */

    private int code;
    private String msg;
    private List<?> data;

    @Override
    public String toString() {
        return "VerifyData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public VerifyData() {
    }

    public VerifyData(int code, String msg, List<?> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
