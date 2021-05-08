package com.weiwu.youxuanfinancial.main.management.detail;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductDeatilData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductDetailRequest;
import com.weiwu.youxuanfinancial.main.management.IManagementContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:48 
 */
public class ProductDetailPresenter implements IManagementContract.IProductDetailPresenter {

    private IManagementContract.IProductDetailView mView;
    private IManagementContract.IProductSource mSource;

    public ProductDetailPresenter(IManagementContract.IProductSource source) {
        mSource = source;
    }

    @Override
    public void getProductDetail(ProductDetailRequest body) {
        mSource.getProductDetailData((LifecycleProvider) mView, body, new IBaseCallBack<ProductDeatilData>() {
            @Override
            public void onSuccess(ProductDeatilData data) {
                mView.productDetailResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void buyProduct(BuyProductRequest body) {
        mSource.buyProduct((LifecycleProvider) mView, body, new IBaseCallBack<BuyResultData>() {
            @Override
            public void onSuccess(BuyResultData data) {
                mView.buyProductResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IManagementContract.IProductDetailView view) {
        mView = view;
    }

    @Override
    public void detachView(IManagementContract.IProductDetailView view) {
        mView = null;

    }
}
