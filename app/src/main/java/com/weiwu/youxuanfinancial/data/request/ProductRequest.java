package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:44 
 */
public class ProductRequest {
    private String page;
    private String size;
    private String type;//1 理财活期 2 理财定期
    private String cate;//1 理财 2 汇兑 （只取汇兑type 可不传）

    @Override
    public String toString() {
        return "ProductDetailRequest{" +
                "page='" + page + '\'' +
                ", size='" + size + '\'' +
                ", type='" + type + '\'' +
                ", cate='" + cate + '\'' +
                '}';
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public ProductRequest() {
    }

    public ProductRequest(String page, String size, String cate) {
        this.page = page;
        this.size = size;
        this.cate = cate;
    }

    public ProductRequest(String page, String size, String type, String cate) {
        this.page = page;
        this.size = size;
        this.type = type;
        this.cate = cate;
    }
}
