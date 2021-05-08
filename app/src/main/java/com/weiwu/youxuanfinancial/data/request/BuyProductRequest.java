package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 16:30 
 */
public class BuyProductRequest {
    private String product_id;
    private String buy_money;
    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public BuyProductRequest(String order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "BuyProductRequest{" +
                "product_id='" + product_id + '\'' +
                ", buy_money='" + buy_money + '\'' +
                '}';
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBuy_money() {
        return buy_money;
    }

    public void setBuy_money(String buy_money) {
        this.buy_money = buy_money;
    }

    public BuyProductRequest() {
    }

    public BuyProductRequest(String product_id, String buy_money) {
        this.product_id = product_id;
        this.buy_money = buy_money;
    }
}
