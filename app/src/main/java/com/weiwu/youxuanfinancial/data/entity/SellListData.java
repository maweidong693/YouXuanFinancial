package com.weiwu.youxuanfinancial.data.entity;

import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/30 13:52 
 */
public class SellListData {

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private int id;
        private String title;
        private double income;
        private String sell_time;
        private int status;
        private int buy_money;
        private String status_msg;

        public String getStatus_msg() {
            return status_msg;
        }

        public void setStatus_msg(String status_msg) {
            this.status_msg = status_msg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }

        public String getSell_time() {
            return sell_time;
        }

        public void setSell_time(String sell_time) {
            this.sell_time = sell_time;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getBuy_money() {
            return buy_money;
        }

        public void setBuy_money(int buy_money) {
            this.buy_money = buy_money;
        }
    }
}
