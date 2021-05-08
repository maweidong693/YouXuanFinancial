package com.weiwu.youxuanfinancial.data.entity;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:42 
 */
public class ProductDeatilData {

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
        private int id;
        private String title;
        private String start_money;
        private String risk_level;
        private int cate_id;
        private String fixed_rate;
        private int type;
        private String fixed_time;
        private String seven_rate;
        private String cate_name;

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

        public String getStart_money() {
            return start_money;
        }

        public void setStart_money(String start_money) {
            this.start_money = start_money;
        }

        public String getRisk_level() {
            return risk_level;
        }

        public void setRisk_level(String risk_level) {
            this.risk_level = risk_level;
        }

        public int getCate_id() {
            return cate_id;
        }

        public void setCate_id(int cate_id) {
            this.cate_id = cate_id;
        }

        public String getFixed_rate() {
            return fixed_rate;
        }

        public void setFixed_rate(String fixed_rate) {
            this.fixed_rate = fixed_rate;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getFixed_time() {
            return fixed_time;
        }

        public void setFixed_time(String fixed_time) {
            this.fixed_time = fixed_time;
        }

        public String getSeven_rate() {
            return seven_rate;
        }

        public void setSeven_rate(String seven_rate) {
            this.seven_rate = seven_rate;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }
    }
}
