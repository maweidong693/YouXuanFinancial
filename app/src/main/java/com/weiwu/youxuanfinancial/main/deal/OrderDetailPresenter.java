package com.weiwu.youxuanfinancial.main.deal;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.OrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.SellOrderDetailData;
import com.weiwu.youxuanfinancial.data.request.OrderDetailRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/30 18:47 
 */
public class OrderDetailPresenter implements IDealContract.IOrderPresenter {

    private IDealContract.IOrderView mView;
    private IDealContract.IDealSource mSource;

    public OrderDetailPresenter(IDealContract.IDealSource source) {
        mSource = source;
    }

    @Override
    public void getOrderDetail(OrderDetailRequest body) {
        mSource.getOrderDetail((LifecycleProvider) mView, body, new IBaseCallBack<OrderDetailData>() {
            @Override
            public void onSuccess(OrderDetailData data) {
                mView.mOrderResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void getSellOrderDetail(OrderDetailRequest body) {
        mSource.getSellOrderDetail((LifecycleProvider) mView, body, new IBaseCallBack<SellOrderDetailData>() {
            @Override
            public void onSuccess(SellOrderDetailData data) {
                mView.mSellOrderResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IDealContract.IOrderView view) {
        mView = view;
    }

    @Override
    public void detachView(IDealContract.IOrderView view) {
        mView = null;
    }
}
