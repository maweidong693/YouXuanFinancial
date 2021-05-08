package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 09:56 
 */
public class VerifyRequestBody {
    private String mobile;
    private String yzm;

    public VerifyRequestBody(String mobile) {
        this.mobile = mobile;
    }

    public VerifyRequestBody() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    public VerifyRequestBody(String mobile, String yzm) {
        this.mobile = mobile;
        this.yzm = yzm;
    }
}
