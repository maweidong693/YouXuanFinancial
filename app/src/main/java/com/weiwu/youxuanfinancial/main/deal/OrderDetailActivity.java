package com.weiwu.youxuanfinancial.main.deal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.qiniu.android.utils.StringUtils;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.OrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.SellOrderDetailData;
import com.weiwu.youxuanfinancial.data.repositories.DealRepository;
import com.weiwu.youxuanfinancial.data.request.OrderDetailRequest;

public class OrderDetailActivity extends BaseActivity implements IDealContract.IOrderView {

    private Toolbar mOrderDetailToolbar;
    private TextView mTvOrderDetailOrderNumber;
    private TextView mTvOrderDetailName;
    private TextView mTvOrderDetailMoney;
    private TextView mTvOrderDetailTime;
    private TextView mTvOrderDetailStatus;
    private TextView mTvOrderDetailMessage;
    private IDealContract.IOrderPresenter mPresenter;
    private int mId;
    private int mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ImmersionBar.with(this).init();
        Intent intent = getIntent();
        mId = intent.getIntExtra(AppConstant.ORDER_ID, 0);
        mType = intent.getIntExtra(AppConstant.ORDER_TYPE, 0);
        setPresenter(new OrderDetailPresenter(DealRepository.getInstance()));
        initView();
    }

    private void initView() {
        mOrderDetailToolbar = (Toolbar) findViewById(R.id.order_detail_toolbar);
        toolbarBack(mOrderDetailToolbar);
        mTvOrderDetailOrderNumber = (TextView) findViewById(R.id.tv_order_detail_order_number);
        mTvOrderDetailName = (TextView) findViewById(R.id.tv_order_detail_name);
        mTvOrderDetailMoney = (TextView) findViewById(R.id.tv_order_detail_money);
        mTvOrderDetailTime = (TextView) findViewById(R.id.tv_order_detail_time);
        mTvOrderDetailStatus = (TextView) findViewById(R.id.tv_order_detail_status);
        mTvOrderDetailMessage = (TextView) findViewById(R.id.tv_order_detail_message);
        if (mType == 1) {
            mPresenter.getOrderDetail(new OrderDetailRequest(String.valueOf(mId)));
        }else {
            mPresenter.getSellOrderDetail(new OrderDetailRequest(String.valueOf(mId)));
        }
    }

    @Override
    public void mOrderResult(OrderDetailData data) {
        if (data != null) {
            OrderDetailData.DataBean bean = data.getData();
            mTvOrderDetailOrderNumber.setText(bean.getOrder_sn());
            mTvOrderDetailName.setText(bean.getTitle());
            mTvOrderDetailMessage.setText(StringUtils.isNullOrEmpty(bean.getRemake()) ? "无" : bean.getRemake());
            mTvOrderDetailMoney.setText(bean.getBuy_money() + "元");
            mTvOrderDetailStatus.setText(bean.getStatus());
            mTvOrderDetailTime.setText(bean.getBuy_time());
        }
    }

    @Override
    public void mSellOrderResult(SellOrderDetailData data) {
        if (data != null) {
            SellOrderDetailData.DataBean bean = data.getData();
            mTvOrderDetailOrderNumber.setText(bean.getOrder_sn());
            mTvOrderDetailName.setText(bean.getTitle());
            mTvOrderDetailMessage.setText(StringUtils.isNullOrEmpty(bean.getRemake()) ? "无" : bean.getRemake());
            mTvOrderDetailMoney.setText(bean.getSell_money() + "元");
            mTvOrderDetailStatus.setText(bean.getStatus());
            mTvOrderDetailTime.setText(bean.getSell_time());
        }
    }

    @Override
    public void onFail(String msg, int code) {
        if (code == 401) {
            showToast("身份验证失败，请重新登录！");
            MyApplication.loginAgain();
        }
    }

    @Override
    public void setPresenter(IDealContract.IOrderPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }
}