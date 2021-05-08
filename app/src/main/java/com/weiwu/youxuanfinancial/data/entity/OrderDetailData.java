package com.weiwu.youxuanfinancial.data.entity;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/30 18:43 
 */
public class OrderDetailData {

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
        private String title;
        private String status;
        private String buy_time;
        private int buy_money;
        private String order_sn;
        private String cate_name;

        public String getRemake() {
            return remake;
        }

        public void setRemake(String remake) {
            this.remake = remake;
        }

        private String remake;

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

        public String getBuy_time() {
            return buy_time;
        }

        public void setBuy_time(String buy_time) {
            this.buy_time = buy_time;
        }

        public int getBuy_money() {
            return buy_money;
        }

        public void setBuy_money(int buy_money) {
            this.buy_money = buy_money;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
