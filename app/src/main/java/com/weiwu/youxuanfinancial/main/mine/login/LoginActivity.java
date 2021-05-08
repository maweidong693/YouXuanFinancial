package com.weiwu.youxuanfinancial.main.mine.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.qiniu.android.utils.StringUtils;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.LoginData;
import com.weiwu.youxuanfinancial.data.entity.VerifyData;
import com.weiwu.youxuanfinancial.data.repositories.MineRepository;
import com.weiwu.youxuanfinancial.data.request.VerifyRequestBody;
import com.weiwu.youxuanfinancial.main.MainActivity;
import com.weiwu.youxuanfinancial.main.mine.MineContract;
import com.weiwu.youxuanfinancial.utils.CountDownTimerUtils;
import com.weiwu.youxuanfinancial.utils.SPUtils;
import com.weiwu.youxuanfinancial.utils.SystemFacade;

public class LoginActivity extends BaseActivity implements View.OnClickListener, MineContract.ILoginView {

    private EditText mEtLoginUsername;
    private EditText mEtLoginPassword;
    private TextView mTvGetVerify;
    private Button mBtLogin;
    private MineContract.ILoginPresenter mPresenter;
    private CountDownTimerUtils downTimerUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImmersionBar.with(this).init();
        setPresenter(new LoginPresenter(MineRepository.getInstance()));
        startMain();
        initView();
    }

    public void startMain() {
        String token = SPUtils.getValue(AppConstant.USER, AppConstant.USER_TOKEN);
        if (StringUtils.isNullOrEmpty(token)) {
            return;
        } else {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        }
    }

    private void initView() {
        mEtLoginUsername = (EditText) findViewById(R.id.et_login_username);
        mEtLoginPassword = (EditText) findViewById(R.id.et_login_password);
        mTvGetVerify = (TextView) findViewById(R.id.tv_get_login_verify);
        mTvGetVerify.setOnClickListener(this);
        mBtLogin = (Button) findViewById(R.id.bt_login);
        mBtLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_go_register:
                Intent goRegister = new Intent(this, RegisterActivity.class);
                startActivity(goRegister);
                break;

            case R.id.bt_login:
                String phone = mEtLoginUsername.getText().toString();
                String verify = mEtLoginPassword.getText().toString();
                if (StringUtils.isNullOrEmpty(verify)) {
                    showToast("请输入验证码！");
                } else {
                    mPresenter.login(new VerifyRequestBody(phone, verify));
                }

                break;

            case R.id.tv_get_login_verify:
                String phoneNumber = mEtLoginUsername.getText().toString();
                if (true) {
                    if (SystemFacade.isOnInternet(this)) {
                        downTimerUtils = new CountDownTimerUtils(mTvGetVerify, 120000, 1000);
                        mPresenter.verifyCode(new VerifyRequestBody(phoneNumber));
                    } else {
                        showToast("当前网络状态异常，请稍后再试！");
                    }
                } else {
                    showToast("请输入正确的手机号！");
                }

                break;
        }
    }

    @Override
    public void getLoginResultSuccess(LoginData data) {
        if (data.getCode() == 1) {
            LoginData.DataBean bean = data.getData();
            SPUtils.commitValue(AppConstant.USER, AppConstant.USER_TOKEN, bean.getToken());
            SPUtils.commitValue(AppConstant.USER,AppConstant.USER_PHONE, bean.getMobile());
            SPUtils.commitValue(AppConstant.USER,AppConstant.USER_STATUS,bean.getIs_real()+"");
            SPUtils.commitValue(AppConstant.USER,AppConstant.USER_NAME,bean.getName());
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        } else {
            showToast(data.getMsg());
        }
    }

    @Override
    public void getLoginResultFail(String msg, int code) {
        showToast(msg);
    }

    @Override
    public void getVerifyResult(VerifyData data) {
        if (data != null) {
            if (data.getCode() == 200) {
                downTimerUtils.start();
                showToast("发送成功");
            } else {
                showToast(data.getMsg());
            }
        }
    }

    @Override
    public void setPresenter(MineContract.ILoginPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }
}