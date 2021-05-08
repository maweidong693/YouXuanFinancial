package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/26 10:35 
 */
public class BindBankRequest {
    private String bank_name;
    private String bank_account;
    private String name;
    private String bank_img;

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_account() {
        return bank_account;
    }

    public void setBank_account(String bank_account) {
        this.bank_account = bank_account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank_img() {
        return bank_img;
    }

    public void setBank_img(String bank_img) {
        this.bank_img = bank_img;
    }

    public BindBankRequest() {
    }

    public BindBankRequest(String bank_name, String bank_account, String name, String bank_img) {
        this.bank_name = bank_name;
        this.bank_account = bank_account;
        this.name = name;
        this.bank_img = bank_img;
    }
}
