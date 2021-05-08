package com.weiwu.youxuanfinancial.data.entity;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 09:43 
 */
public class LoginData {

    /**
     * code : 1
     * msg : 登录成功
     * data : {"uid":669,"token":"76C5A45240027AFEBD08F3CC2960BE8D1D7A56D5","name":null,"is_real":0,"mobile":"13629518726"}
     */

    private Integer code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        /**
         * uid : 669
         * token : 76C5A45240027AFEBD08F3CC2960BE8D1D7A56D5
         * name : null
         * is_real : 0
         * mobile : 13629518726
         */

        private Integer uid;
        private String token;
        private String name;
        private Integer is_real;
        private String mobile;

        @Override
        public String toString() {
            return "DataBean{" +
                    "uid=" + uid +
                    ", token='" + token + '\'' +
                    ", name='" + name + '\'' +
                    ", is_real=" + is_real +
                    ", mobile='" + mobile + '\'' +
                    '}';
        }

        public Integer getUid() {
            return uid;
        }

        public void setUid(Integer uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getIs_real() {
            return is_real;
        }

        public void setIs_real(Integer is_real) {
            this.is_real = is_real;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public DataBean() {
        }

        public DataBean(Integer uid, String token, String name, Integer is_real, String mobile) {
            this.uid = uid;
            this.token = token;
            this.name = name;
            this.is_real = is_real;
            this.mobile = mobile;
        }
    }
}
