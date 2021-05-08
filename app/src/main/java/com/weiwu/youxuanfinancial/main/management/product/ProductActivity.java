package com.weiwu.youxuanfinancial.main.management.product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.repositories.ProductRepository;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.management.IManagementContract;
import com.weiwu.youxuanfinancial.main.management.ProductListAdapter;
import com.weiwu.youxuanfinancial.main.management.ProductPresenter;
import com.weiwu.youxuanfinancial.main.management.detail.MoneyManagementDetailActivity;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;

public class ProductActivity extends BaseActivity implements IManagementContract.IProductListView {

    private Toolbar mProductListToolbar;
    private TextView mToolbarTitle;
    private SmartRefreshLayout mSrlProductList;
    private RecyclerView mRvProductList;
    private ProductListAdapter mAdapter;
    private int mTag;
    private IManagementContract.IProductListPresenter mPresenter;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mTag = getIntent().getIntExtra(AppConstant.PRODUCT_LIST_TYPE, 0);
        setPresenter(new ProductPresenter(ProductRepository.getInstance()));
        ImmersionBar.with(this).init();
        initView();
        toolbarBack(mProductListToolbar);
    }

    private void initView() {
        mProductListToolbar = (Toolbar) findViewById(R.id.product_list_toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mSrlProductList = (SmartRefreshLayout) findViewById(R.id.srl_product_list);
        mSrlProductList.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                if (mTag == 1) {
                    mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                } else {
                    mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "2", "1"));

                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (mTag == 1) {
                    mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                } else {
                    mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "2", "1"));

                }

            }
        });
        mRvProductList = (RecyclerView) findViewById(R.id.rv_product_list);
        mRvProductList.setLayoutManager(new LinearLayoutManager(this));

        if (mTag == 1) {
            mAdapter = new ProductListAdapter(1);
            mPresenter.getProductList(new ProductRequest("1", "10", "1", "1"));
        } else {
            mAdapter = new ProductListAdapter(0);
            mPresenter.getProductList(new ProductRequest("1", "10", "2", "1"));
            mToolbarTitle.setText("定期理财");

        }
        mRvProductList.setAdapter(mAdapter);

        mAdapter.setProductClickListener(new ProductListAdapter.IProductClickListener() {
            @Override
            public void mItemClickListener(ProductListData.DataBean data) {
                Intent detailIntent = new Intent(ProductActivity.this, MoneyManagementDetailActivity.class);
                detailIntent.putExtra(AppConstant.PRODUCT_ID, data.getId());
                startActivity(detailIntent);
            }
        });
    }

    @Override
    public void productListResult(ProductListData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                mSrlProductList.finishRefresh().finishLoadMore();
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
    public void onFail(String msg, int code) {
        if (code == 401) {
            showToast("身份验证失败，请重新登录！");
            MyApplication.loginAgain();
        }
    }

    @Override
    public void setPresenter(IManagementContract.IProductListPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }
}