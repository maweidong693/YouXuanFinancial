package com.weiwu.youxuanfinancial.main;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.main.deal.DealFragment;
import com.weiwu.youxuanfinancial.main.home.HomeFragment;
import com.weiwu.youxuanfinancial.main.management.MoneyManagementFragment;
import com.weiwu.youxuanfinancial.main.mine.MineFragment;

public class MainActivity extends BaseActivity {
    private FrameLayout mMainBody;
    private RadioGroup mRgNavigation;
    /**
     * 首页
     */
    private RadioButton mRbHome;
    /**
     * 首页
     */
    private RadioButton mRbMine;
    private TextView toolbarTitle;
    private RadioButton rbDeal;
    private RadioButton rbMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImmersionBar.with(this)./*fitsSystemWindows(true).transparentStatusBar().statusBarDarkFont(true).*/init();
        initView();
    }

    public void initView() {
        mMainBody = (FrameLayout) findViewById(R.id.fl_body);
        mRgNavigation = (RadioGroup) findViewById(R.id.rg_navigation);
        mRbHome = (RadioButton) findViewById(R.id.rb_home);
        mRbMine = (RadioButton) findViewById(R.id.rb_mine);
        rbDeal = (RadioButton) findViewById(R.id.rb_deal);
        rbMoney = (RadioButton) findViewById(R.id.rb_money);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);

        mRbHome.setChecked(true);
        addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.fl_body, null);
        initFragment();
    }

    private void initFragment() {
        mRgNavigation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        addFragment(getSupportFragmentManager(), HomeFragment.class, R.id.fl_body, null);
                        break;
                    case R.id.rb_deal:
                        addFragment(getSupportFragmentManager(), DealFragment.class, R.id.fl_body, null);
                        break;
                    case R.id.rb_money:
                        addFragment(getSupportFragmentManager(), MoneyManagementFragment.class, R.id.fl_body, null);
                        break;
                    case R.id.rb_mine:
                        addFragment(getSupportFragmentManager(), MineFragment.class, R.id.fl_body, null);
                        break;
                }
            }
        });
    }
}