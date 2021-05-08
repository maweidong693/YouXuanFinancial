package com.weiwu.youxuanfinancial.main.management;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:10 
 */
public class ProductPresenter implements IManagementContract.IProductListPresenter {

    private IManagementContract.IProductListView mView;
    private IManagementContract.IProductSource mSource;

    public ProductPresenter(IManagementContract.IProductSource source) {
        mSource = source;
    }

    @Override
    public void getProductList(ProductRequest request) {
        mSource.getProductList((LifecycleProvider) mView, request, new IBaseCallBack<ProductListData>() {
            @Override
            public void onSuccess(ProductListData data) {
                mView.productListResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(IManagementContract.IProductListView view) {
        mView = view;
    }

    @Override
    public void detachView(IManagementContract.IProductListView view) {
        mView = null;
    }
}
