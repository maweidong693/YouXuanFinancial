package com.weiwu.youxuanfinancial.data.entity;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/5/3 16:42 
 */
public class SellOrderDetailData {
    private int code;
    private String msg;
    private DataBean data;

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
        private String order_sn;
        private String income;
        private String remake;
        private String title;
        private String status;
        private String sell_time;
        private int sell_money;
        private String cate_name;

        public String getRemake() {
            return remake;
        }

        public void setRemake(String remake) {
            this.remake = remake;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getSell_time() {
            return sell_time;
        }

        public void setSell_time(String sell_time) {
            this.sell_time = sell_time;
        }

        public int getSell_money() {
            return sell_money;
        }

        public void setSell_money(int sell_money) {
            this.sell_money = sell_money;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
