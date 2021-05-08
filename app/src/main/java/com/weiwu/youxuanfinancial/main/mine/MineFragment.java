package com.weiwu.youxuanfinancial.main.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.qiniu.android.utils.StringUtils;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.data.entity.AuthenticationStatusData;
import com.weiwu.youxuanfinancial.data.repositories.MineRepository;
import com.weiwu.youxuanfinancial.main.mine.authentication.AuthenticationActivity;
import com.weiwu.youxuanfinancial.main.mine.bank.AddBankCardActivity;
import com.weiwu.youxuanfinancial.main.mine.deal_detail.DealDetailActivity;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.utils.SPUtils;

public class MineFragment extends BaseFragment implements View.OnClickListener, MineContract.IMineView {

    private TextView mTvMineDealDetail;
    private TextView mTvMineAuthentication;
    private TextView mTvMineBankCard;
    private TextView mTvUserName;
    private TextView mTvPhone;
    private MineContract.IMinePresenter mPresenter;
    private Button mBtOutLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPresenter(new MinePresenter(MineRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mTvMineDealDetail = (TextView) view.findViewById(R.id.tv_mine_deal_detail);
        mTvMineDealDetail.setOnClickListener(this);
        mTvMineAuthentication = (TextView) view.findViewById(R.id.tv_mine_authentication);
        mTvMineAuthentication.setOnClickListener(this);
        mTvMineBankCard = (TextView) view.findViewById(R.id.tv_mine_bank_card);
        mTvMineBankCard.setOnClickListener(this);
        mTvUserName = (TextView) view.findViewById(R.id.tv_user_name);
        mTvPhone = (TextView) view.findViewById(R.id.tv_phone);
        mBtOutLogin = (Button) view.findViewById(R.id.bt_out_login);
        mBtOutLogin.setOnClickListener(this);
        String name = SPUtils.getValue(AppConstant.USER, AppConstant.USER_NAME);
        if (StringUtils.isNullOrEmpty(name)) {
            mTvUserName.setText("未实名认证");
        } else {
            mTvUserName.setText(name);
        }
        String phone = SPUtils.getValue(AppConstant.USER, AppConstant.USER_PHONE);
        mTvPhone.setText(phone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_mine_authentication:
                mPresenter.getAuthenticationStatus();
                break;

            case R.id.tv_mine_bank_card:
                mPresenter.getBindBankStatus();

                break;

            case R.id.tv_mine_deal_detail:
                Intent dealDetailIntent = new Intent(getActivity(), DealDetailActivity.class);
                startActivity(dealDetailIntent);
                break;

            case R.id.bt_out_login:
                SPUtils.clearValue(AppConstant.USER);
                Intent loginItent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginItent);
                getActivity().finish();
                break;
        }
    }

    @Override
    public void authenticationStatusResult(AuthenticationStatusData data) {
        if (data != null) {
            dismissLoadingPage();
            int code = data.getCode();
            if (code == 1) {
                switch (data.getData().getStatus()) {
                    case 0:
                        Intent authenticationIntent = new Intent(getActivity(), AuthenticationActivity.class);
                        startActivity(authenticationIntent);
                        break;

                    case 1:
                        showToast("审核中！");
                        break;
                    case 2:
                        showToast("实名认证已通过！");
                        break;
                }
            }
        }
    }

    @Override
    public void bindBankStatusResult(AuthenticationStatusData data) {
        if (data != null) {
            dismissLoadingPage();
            int code = data.getCode();
            if (code == 1) {
                switch (data.getData().getStatus()) {
                    case 0:
                        Intent addBankCardIntent = new Intent(getActivity(), AddBankCardActivity.class);
                        startActivity(addBankCardIntent);
                        break;

                    case 1:
                        showToast("审核中！");
                        break;
                    case 2:
                        showToast("银行卡已绑定！");
                        break;
                }
            }
        }

    }

    private String TAG = "abc";

    @Override
    public void onFail(String msg, int code) {
        if (code == 401) {
            showToast("身份验证失败，请重新登录！");
            MyApplication.loginAgain();
        }
    }

    @Override
    public void setPresenter(MineContract.IMinePresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return getActivity();
    }
}