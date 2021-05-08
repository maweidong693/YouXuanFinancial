package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/27 15:46 
 */
public class AuthenticationRequest {
    private String name;
    private String card;
    private int sex;
    private String card_img_z;
    private String card_img_f;

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "name='" + name + '\'' +
                ", card='" + card + '\'' +
                ", sex=" + sex +
                ", card_img_z='" + card_img_z + '\'' +
                ", card_img_f='" + card_img_f + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCard_img_z() {
        return card_img_z;
    }

    public void setCard_img_z(String card_img_z) {
        this.card_img_z = card_img_z;
    }

    public String getCard_img_f() {
        return card_img_f;
    }

    public void setCard_img_f(String card_img_f) {
        this.card_img_f = card_img_f;
    }

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String name, String card, int sex, String card_img_z, String card_img_f) {
        this.name = name;
        this.card = card;
        this.sex = sex;
        this.card_img_z = card_img_z;
        this.card_img_f = card_img_f;
    }
}
