package com.weiwu.youxuanfinancial.main.home;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 09:53 
 */
public class HomePresenter implements HomeContract.IHuiduiPresenter {

    private HomeContract.IHuiduiView mView;
    private HomeContract.IHomeSource mSource;

    public HomePresenter(HomeContract.IHomeSource source) {
        mSource = source;
    }

    @Override
    public void getHuiduiList(ProductRequest body) {
        mSource.getHuiduiList((LifecycleProvider) mView, body, new IBaseCallBack<ProductListData>() {
            @Override
            public void onSuccess(ProductListData data) {
                mView.huiduiListResult(data);
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
    public void attachView(HomeContract.IHuiduiView view) {
        mView = view;
    }

    @Override
    public void detachView(HomeContract.IHuiduiView view) {
        mView = null;
    }
}
