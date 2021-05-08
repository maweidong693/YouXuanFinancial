package com.weiwu.youxuanfinancial.main.deal.buy;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 17:41 
 */
public class BuyPresenter implements IDealContract.IBuyPresenter {

    private IDealContract.IBuyView mView;
    private IDealContract.IDealSource mSource;

    public BuyPresenter(IDealContract.IDealSource source) {
        mSource = source;
    }

    @Override
    public void getBuyProductList(ProductRequest body) {
        mSource.getBuyProductList((LifecycleProvider) mView, body, new IBaseCallBack<BuyListData>() {
            @Override
            public void onSuccess(BuyListData data) {
                mView.buyProductListResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void sellOut(BuyProductRequest body) {
        mSource.sellOut((LifecycleProvider) mView, body, new IBaseCallBack<BuyResultData>() {
            @Override
            public void onSuccess(BuyResultData data) {
                mView.getSellOutResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IDealContract.IBuyView view) {
        mView = view;
    }

    @Override
    public void detachView(IDealContract.IBuyView view) {
        mView = null;
    }
}
