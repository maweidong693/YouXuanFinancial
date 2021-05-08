package com.weiwu.youxuanfinancial.main.mine.login;

import android.os.Bundle;

import com.gyf.immersionbar.ImmersionBar;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImmersionBar.with(this).init();

    }
}