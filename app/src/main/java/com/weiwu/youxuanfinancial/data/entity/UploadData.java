package com.weiwu.youxuanfinancial.data.entity;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/27 15:49 
 */
public class UploadData {
    private int code;
    private String msg;
    private DataBean data;

    public UploadData() {
    }

    public UploadData(int code, String msg, DataBean data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String url;

        public DataBean() {
        }

        public DataBean(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
