package com.weiwu.youxuanfinancial.main.deal.sell;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.SellListData;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;

import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/30 14:08 
 */
public class SellPresenter implements IDealContract.ISellPresenter {

    private IDealContract.ISellView mView;
    private IDealContract.IDealSource mSource;

    public SellPresenter(IDealContract.IDealSource source) {
        mSource = source;
    }

    @Override
    public void getSellLIst(ProductRequest body) {
        mSource.getSellProductList((LifecycleProvider) mView, body, new IBaseCallBack<SellListData>() {
            @Override
            public void onSuccess(SellListData data) {
                mView.mSellListResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IDealContract.ISellView view) {
        mView = view;
    }

    @Override
    public void detachView(IDealContract.ISellView view) {
        mView = null;
    }
}
