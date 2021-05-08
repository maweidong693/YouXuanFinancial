package com.weiwu.youxuanfinancial.data.repositories;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.BaseRepository;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.AuthenticationStatusData;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.LoginData;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.entity.VerifyData;
import com.weiwu.youxuanfinancial.data.network.DataService;
import com.weiwu.youxuanfinancial.data.request.AuthenticationRequest;
import com.weiwu.youxuanfinancial.data.request.BindBankRequest;
import com.weiwu.youxuanfinancial.data.request.VerifyRequestBody;
import com.weiwu.youxuanfinancial.main.mine.MineContract;

import okhttp3.MultipartBody;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 10:00 
 */
public class MineRepository extends BaseRepository implements MineContract.IMineSource {

    public volatile static MineRepository mMineRepository;

    public static MineRepository getInstance() {
        if (mMineRepository == null) {
            synchronized (MineRepository.class) {
                if (mMineRepository == null) {
                    mMineRepository = new MineRepository();
                }
            }
        }
        return mMineRepository;
    }

    @Override
    public void getLogin(LifecycleProvider lifecycleProvider, VerifyRequestBody body, IBaseCallBack<LoginData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getLogin(body), callBack);
    }

    @Override
    public void getVerify(LifecycleProvider lifecycleProvider, VerifyRequestBody body, IBaseCallBack<VerifyData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getVerify(body), callBack);
    }

    @Override
    public void getBankList(LifecycleProvider lifecycleProvider, IBaseCallBack<BankCardListData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getBankListData(), callBack);
    }

    @Override
    public void bindBankCard(LifecycleProvider lifecycleProvider, BindBankRequest body, IBaseCallBack<BaseResult> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getBindBankResult(body), callBack);
    }

    @Override
    public void uploadPic(LifecycleProvider lifecycleProvider, MultipartBody.Part file, IBaseCallBack<UploadData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().uploadPicture(file), callBack);
    }

    @Override
    public void uploadReversePic(LifecycleProvider lifecycleProvider, MultipartBody.Part file,IBaseCallBack<UploadData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().uploadPicture(file), callBack);
    }

    @Override
    public void commitAuthentication(LifecycleProvider lifecycleProvider, AuthenticationRequest body, IBaseCallBack<BaseResult> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().commitAuthentication(body), callBack);
    }

    @Override
    public void getAuthenticationStatus(LifecycleProvider lifecycleProvider, IBaseCallBack<AuthenticationStatusData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getAuthenticationStatus(), callBack);

    }

    @Override
    public void getBindBankStatus(LifecycleProvider lifecycleProvider, IBaseCallBack<AuthenticationStatusData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getBankCardBindStatus(), callBack);
    }
}
