package com.weiwu.youxuanfinancial.main.mine;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.AuthenticationStatusData;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/28 16:07 
 */
public class MinePresenter implements MineContract.IMinePresenter {

    private MineContract.IMineView mView;
    private MineContract.IMineSource mSource;

    public MinePresenter(MineContract.IMineSource source) {
        mSource = source;
    }

    @Override
    public void getAuthenticationStatus() {
        mSource.getAuthenticationStatus((LifecycleProvider) mView, new IBaseCallBack<AuthenticationStatusData>() {
            @Override
            public void onSuccess(AuthenticationStatusData data) {
                mView.authenticationStatusResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void getBindBankStatus() {
        mSource.getBindBankStatus((LifecycleProvider) mView, new IBaseCallBack<AuthenticationStatusData>() {
            @Override
            public void onSuccess(AuthenticationStatusData data) {
                mView.bindBankStatusResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);

            }
        });
    }

    @Override
    public void attachView(MineContract.IMineView view) {
        mView = view;
    }

    @Override
    public void detachView(MineContract.IMineView view) {
        mView = null;
    }
}
