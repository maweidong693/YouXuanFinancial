package com.weiwu.youxuanfinancial.main.mine;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.base.IBasePresenter;
import com.weiwu.youxuanfinancial.base.IBaseView;
import com.weiwu.youxuanfinancial.data.entity.AuthenticationStatusData;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.LoginData;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.entity.VerifyData;
import com.weiwu.youxuanfinancial.data.request.AuthenticationRequest;
import com.weiwu.youxuanfinancial.data.request.BindBankRequest;
import com.weiwu.youxuanfinancial.data.request.VerifyRequestBody;

import okhttp3.MultipartBody;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 10:02 
 */
public interface MineContract {

    interface ILoginView extends IBaseView<ILoginPresenter> {
        void getLoginResultSuccess(LoginData data);

        void getLoginResultFail(String msg, int code);

        void getVerifyResult(VerifyData data);
    }

    interface ILoginPresenter extends IBasePresenter<ILoginView> {
        void login(VerifyRequestBody body);

        void verifyCode(VerifyRequestBody body);
    }

    interface IBankView extends IBaseView<IBankPresenter> {
        void bankListResult(BankCardListData data);

        void bindBankCardResult(BaseResult resultl);

        void uploadBankPicResult(UploadData data);

        void onFail(String msg, int code);
    }

    interface IBankPresenter extends IBasePresenter<IBankView> {
        void getBankList();

        void bindBankCard(BindBankRequest body);

        void uploadBankPic(MultipartBody.Part file);
    }

    interface IMineView extends IBaseView<IMinePresenter> {
        void authenticationStatusResult(AuthenticationStatusData data);

        void bindBankStatusResult(AuthenticationStatusData data);

        void onFail(String msg, int code);
    }

    interface IMinePresenter extends IBasePresenter<IMineView> {
        void getAuthenticationStatus();

        void getBindBankStatus();

    }

    interface IAuthenticationView extends IBaseView<IAuthenticationPresenter> {
        void uploadPicResult(UploadData data);

        void uploadPicReverseResult(UploadData data);

        void commitAuthenticationResult(BaseResult data);

        void onFail(String msg, int code);

    }

    interface IAuthenticationPresenter extends IBasePresenter<IAuthenticationView> {
        void uploadCardPic(MultipartBody.Part file);

        void uploadCardReversePic(MultipartBody.Part file);

        void commitAuthentication(AuthenticationRequest requestl);
    }

    interface IMineSource {
        void getLogin(LifecycleProvider lifecycleProvider, VerifyRequestBody body, IBaseCallBack<LoginData> callBack);

        void getVerify(LifecycleProvider lifecycleProvider, VerifyRequestBody body, IBaseCallBack<VerifyData> callBack);

        void getBankList(LifecycleProvider lifecycleProvider, IBaseCallBack<BankCardListData> callBack);

        void bindBankCard(LifecycleProvider lifecycleProvider, BindBankRequest body, IBaseCallBack<BaseResult> callBack);

        void uploadPic(LifecycleProvider lifecycleProvider, MultipartBody.Part file, IBaseCallBack<UploadData> callBack);

        void uploadReversePic(LifecycleProvider lifecycleProvider, MultipartBody.Part file, IBaseCallBack<UploadData> callBack);

        void commitAuthentication(LifecycleProvider lifecycleProvider, AuthenticationRequest body, IBaseCallBack<BaseResult> callBack);

        void getAuthenticationStatus(LifecycleProvider lifecycleProvider, IBaseCallBack<AuthenticationStatusData> callBack);

        void getBindBankStatus(LifecycleProvider lifecycleProvider, IBaseCallBack<AuthenticationStatusData> callBack);
    }
}
