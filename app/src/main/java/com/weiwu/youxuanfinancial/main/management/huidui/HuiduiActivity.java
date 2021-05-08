package com.weiwu.youxuanfinancial.main.management.huidui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.qiniu.android.utils.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.repositories.HomeRepository;
import com.weiwu.youxuanfinancial.data.repositories.ProductRepository;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.home.HomeContract;
import com.weiwu.youxuanfinancial.main.home.HomePresenter;
import com.weiwu.youxuanfinancial.main.home.HuiduiAdapter;
import com.weiwu.youxuanfinancial.main.management.detail.ProductDetailPresenter;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.view.BuyPopup;

public class HuiduiActivity extends BaseActivity implements HomeContract.IHuiduiView, View.OnClickListener {

    private Toolbar mHuiduiListToolbar;
    private SmartRefreshLayout mSrlHuiduiList;
    private RecyclerView mRvHuiduiList;
    private HomeContract.IHuiduiPresenter mPresenter;
    private HuiduiAdapter mAdapter;
    private int page = 1;

    private BuyPopup mBuyPopup;
    private EditText mEtBuyPrice;
    private Button mBtConfirmBuy;
    private Button mBtCancelBuy;
    private ProductListData.DataBean mCheckData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_huidui);
        ImmersionBar.with(this).init();
        setPresenter(new HomePresenter(HomeRepository.getInstance()));
        initView();
        toolbarBack(mHuiduiListToolbar);
    }

    private void initView() {
        mHuiduiListToolbar = (Toolbar) findViewById(R.id.huidui_list_toolbar);
        mSrlHuiduiList = (SmartRefreshLayout) findViewById(R.id.srl_huidui_list);
        mSrlHuiduiList.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                mPresenter.getHuiduiList(new ProductRequest(String.valueOf(page), "10", "2"));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mAdapter.clearList();
                mPresenter.getHuiduiList(new ProductRequest(String.valueOf(page), "10", "2"));

            }
        });
        mRvHuiduiList = (RecyclerView) findViewById(R.id.rv_huidui_list);
        mRvHuiduiList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new HuiduiAdapter();

        mBuyPopup = new BuyPopup(this);
        mEtBuyPrice = mBuyPopup.findViewById(R.id.et_buy_price);
        mBtConfirmBuy = mBuyPopup.findViewById(R.id.bt_confirm_buy);
        mBtConfirmBuy.setOnClickListener(this);
        mBtCancelBuy = mBuyPopup.findViewById(R.id.bt_cancel_buy);
        mBtCancelBuy.setOnClickListener(this);

        mRvHuiduiList.setAdapter(mAdapter);
        mPresenter.getHuiduiList(new ProductRequest("1", "10", "2"));

        mAdapter.setHuiduiItemClickListener(new HuiduiAdapter.IHuiduiItemClickListener() {
            @Override
            public void mItemClickListener(ProductListData.DataBean dataBean) {
                mCheckData = dataBean;
                mBuyPopup.showPopupWindow();
            }
        });
    }

    @Override
    public void huiduiListResult(ProductListData data) {
        if (data != null) {
            int code = data.getCode();
            if (data.getData() != null) {
                mSrlHuiduiList.finishRefresh().finishLoadMore();
                mAdapter.setList(data.getData());
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                showToast(data.getMsg());
            }
        }
    }

    @Override
    public void buyProductResult(BuyResultData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                showToast(data.getMsg());
                mEtBuyPrice.setText("");
                mBuyPopup.dismiss();
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                showToast(data.getMsg());
            }
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
    public void setPresenter(HomeContract.IHuiduiPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm_buy:
                String price = mEtBuyPrice.getText().toString();
                if (StringUtils.isNullOrEmpty(price)) {
                    showToast("请输入购买金额！");
                } else {
                    mPresenter.buyProduct(new BuyProductRequest(String.valueOf(mCheckData.getId()), price));
                }
                break;
            case R.id.bt_cancel_buy:
                mBuyPopup.dismiss();
                mEtBuyPrice.setText("");
                break;
        }
    }
}