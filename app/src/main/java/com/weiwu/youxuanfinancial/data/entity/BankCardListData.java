package com.weiwu.youxuanfinancial.data.entity;

import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/26 09:09 
 */
public class BankCardListData {
    /**
     * code
     */
    private Integer code;
    /**
     * msg
     */
    private String msg;
    /**
     * data
     */
    private List<String> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
