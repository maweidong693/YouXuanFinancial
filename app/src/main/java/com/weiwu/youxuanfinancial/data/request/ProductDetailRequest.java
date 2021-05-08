package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 10:26 
 */
public class ProductDetailRequest {
    private String product_id;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public ProductDetailRequest() {
    }

    public ProductDetailRequest(String product_id) {
        this.product_id = product_id;
    }
}
