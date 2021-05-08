package com.weiwu.youxuanfinancial.main.mine.login;

import android.util.Log;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.LoginData;
import com.weiwu.youxuanfinancial.data.entity.VerifyData;
import com.weiwu.youxuanfinancial.data.request.VerifyRequestBody;
import com.weiwu.youxuanfinancial.main.mine.MineContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 10:14 
 */
public class LoginPresenter implements MineContract.ILoginPresenter {


    private MineContract.IMineSource mSource;
    private MineContract.ILoginView mView;

    public LoginPresenter(MineContract.IMineSource source) {
        mSource = source;
    }


    @Override
    public void login(VerifyRequestBody body) {
        mSource.getLogin((LifecycleProvider) mView, body, new IBaseCallBack<LoginData>() {
            @Override
            public void onSuccess(LoginData data) {
                mView.getLoginResultSuccess(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.getLoginResultFail(msg, statusCode);
            }
        });
    }

    @Override
    public void verifyCode(VerifyRequestBody body) {
        mSource.getVerify((LifecycleProvider) mView, body, new IBaseCallBack<VerifyData>() {
            @Override
            public void onSuccess(VerifyData data) {
                mView.getVerifyResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.getLoginResultFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(MineContract.ILoginView view) {
        mView = view;
    }

    @Override
    public void detachView(MineContract.ILoginView view) {
        mView = null;
    }
}
