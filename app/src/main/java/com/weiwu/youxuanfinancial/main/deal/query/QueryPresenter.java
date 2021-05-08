package com.weiwu.youxuanfinancial.main.deal.query;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/5/3 18:32 
 */
public class QueryPresenter implements IDealContract.IQueryPresenter {

    private IDealContract.IQueryView mView;
    private IDealContract.IDealSource mSource;

    public QueryPresenter(IDealContract.IDealSource source) {
        mSource = source;
    }

    @Override
    public void getQueryList(ProductRequest body) {
        mSource.getQueryList((LifecycleProvider) mView, body, new IBaseCallBack<QueryListData>() {
            @Override
            public void onSuccess(QueryListData data) {
                mView.mQueryListResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IDealContract.IQueryView view) {
        mView = view;
    }

    @Override
    public void detachView(IDealContract.IQueryView view) {
        mView = null;
    }
}
