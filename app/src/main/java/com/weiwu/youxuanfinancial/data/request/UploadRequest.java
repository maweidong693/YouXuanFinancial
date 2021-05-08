package com.weiwu.youxuanfinancial.data.request;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/27 15:52 
 */
public class UploadRequest {
    private String image;

    @Override
    public String toString() {
        return "UploadRequest{" +
                "image='" + image + '\'' +
                '}';
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public UploadRequest() {
    }

    public UploadRequest(String image) {
        this.image = image;
    }
}
