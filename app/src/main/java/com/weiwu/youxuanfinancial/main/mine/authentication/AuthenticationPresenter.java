package com.weiwu.youxuanfinancial.main.mine.authentication;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.request.AuthenticationRequest;
import com.weiwu.youxuanfinancial.main.mine.MineContract;

import okhttp3.MultipartBody;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/27 16:02 
 */
public class AuthenticationPresenter implements MineContract.IAuthenticationPresenter {

    private MineContract.IAuthenticationView mView;
    private MineContract.IMineSource mSource;

    public AuthenticationPresenter(MineContract.IMineSource source) {
        mSource = source;
    }

    @Override
    public void uploadCardPic(MultipartBody.Part file) {
        mSource.uploadPic((LifecycleProvider) mView, file, new IBaseCallBack<UploadData>() {
            @Override
            public void onSuccess(UploadData data) {
                mView.uploadPicResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void uploadCardReversePic(MultipartBody.Part file) {
        mSource.uploadReversePic((LifecycleProvider) mView, file, new IBaseCallBack<UploadData>() {
            @Override
            public void onSuccess(UploadData data) {
                mView.uploadPicReverseResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }


    @Override
    public void commitAuthentication(AuthenticationRequest requestl) {
        mSource.commitAuthentication((LifecycleProvider) mView, requestl, new IBaseCallBack<BaseResult>() {
            @Override
            public void onSuccess(BaseResult data) {
                mView.commitAuthenticationResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {
                mView.onFail(msg, statusCode);
            }
        });
    }

    @Override
    public void attachView(MineContract.IAuthenticationView view) {
        mView = view;
    }

    @Override
    public void detachView(MineContract.IAuthenticationView view) {
        mView = null;
    }
}
