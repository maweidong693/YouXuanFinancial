package com.weiwu.youxuanfinancial.data.entity;

import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 09:14 
 */
public class ProductListData<T> {
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
        private double buy_rate;
        private double sell_rate;
        private double exchange_rate;
        private String seven_rate;
        private List<String> tag;
        private String start_money;

        public String getSeven_rate() {
            return seven_rate;
        }

        public void setSeven_rate(String seven_rate) {
            this.seven_rate = seven_rate;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public String getStart_money() {
            return start_money;
        }

        public void setStart_money(String start_money) {
            this.start_money = start_money;
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

        public double getBuy_rate() {
            return buy_rate;
        }

        public void setBuy_rate(double buy_rate) {
            this.buy_rate = buy_rate;
        }

        public double getSell_rate() {
            return sell_rate;
        }

        public void setSell_rate(double sell_rate) {
            this.sell_rate = sell_rate;
        }

        public double getExchange_rate() {
            return exchange_rate;
        }

        public void setExchange_rate(double exchange_rate) {
            this.exchange_rate = exchange_rate;
        }
    }
}
