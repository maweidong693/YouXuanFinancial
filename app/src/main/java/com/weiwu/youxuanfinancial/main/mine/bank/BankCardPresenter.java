package com.weiwu.youxuanfinancial.main.mine.bank;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.request.BindBankRequest;
import com.weiwu.youxuanfinancial.main.mine.MineContract;

import okhttp3.MultipartBody;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/26 10:44 
 */
public class BankCardPresenter implements MineContract.IBankPresenter {

    private MineContract.IBankView mView;
    private MineContract.IMineSource mSource;

    public BankCardPresenter(MineContract.IMineSource source) {
        mSource = source;
    }

    @Override
    public void getBankList() {
        mSource.getBankList((LifecycleProvider) mView, new IBaseCallBack<BankCardListData>() {
            @Override
            public void onSuccess(BankCardListData data) {
                mView.bankListResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {

            }
        });
    }

    @Override
    public void bindBankCard(BindBankRequest body) {
        mSource.bindBankCard((LifecycleProvider) mView, body, new IBaseCallBack<BaseResult>() {
            @Override
            public void onSuccess(BaseResult data) {
                mView.bindBankCardResult(data);

            }

            @Override
            public void onFail(String msg, int statusCode) {

            }
        });
    }

    @Override
    public void uploadBankPic(MultipartBody.Part file) {
        mSource.uploadPic((LifecycleProvider) mView, file, new IBaseCallBack<UploadData>() {
            @Override
            public void onSuccess(UploadData data) {
                mView.uploadBankPicResult(data);
            }

            @Override
            public void onFail(String msg, int statusCode) {

            }
        });
    }

    @Override
    public void attachView(MineContract.IBankView view) {
        mView = view;
    }

    @Override
    public void detachView(MineContract.IBankView view) {
        mView = null;
    }
}
