package com.weiwu.youxuanfinancial.main.management;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.repositories.ProductRepository;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.management.detail.MoneyManagementDetailActivity;
import com.weiwu.youxuanfinancial.main.management.huidui.HuiduiActivity;
import com.weiwu.youxuanfinancial.main.management.product.ProductActivity;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;

public class MoneyManagementFragment extends BaseFragment implements IManagementContract.IProductListView, View.OnClickListener {

    private RecyclerView mRvMoneyProduct;
    private IManagementContract.IProductListPresenter mPresenter;
    private ProductListAdapter mAdapter;
    private TextView mTvMoneyHuoqi;
    private TextView mTvMoneyDingqi;
    private TextView mTvMoneyHuidui;
    private SmartRefreshLayout mSrlMoneyMangerment;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_money_management;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPresenter(new ProductPresenter(ProductRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mRvMoneyProduct = (RecyclerView) view.findViewById(R.id.rv_money_product);
        mRvMoneyProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        mSrlMoneyMangerment = (SmartRefreshLayout) view.findViewById(R.id.srl_money_mangerment);
        mSrlMoneyMangerment.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mPresenter.getProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
            }
        });
        mTvMoneyHuoqi = (TextView) view.findViewById(R.id.tv_money_huoqi);
        mTvMoneyDingqi = (TextView) view.findViewById(R.id.tv_money_dingqi);
        mTvMoneyHuidui = (TextView) view.findViewById(R.id.tv_money_huidui);
        mTvMoneyHuoqi.setOnClickListener(this);
        mTvMoneyDingqi.setOnClickListener(this);
        mTvMoneyHuidui.setOnClickListener(this);
        mAdapter = new ProductListAdapter(1);
        mRvMoneyProduct.setAdapter(mAdapter);
        mPresenter.getProductList(new ProductRequest("1", "10", "1", "1"));
        mAdapter.setProductClickListener(new ProductListAdapter.IProductClickListener() {
            @Override
            public void mItemClickListener(ProductListData.DataBean data) {
                Intent detailIntent = new Intent(getContext(), MoneyManagementDetailActivity.class);
                detailIntent.putExtra(AppConstant.PRODUCT_ID, data.getId());
                startActivity(detailIntent);
            }
        });
    }

    @Override
    public void productListResult(ProductListData data) {
        if (data != null) {
            int code = data.getCode();
            if (data.getData() != null) {
                mSrlMoneyMangerment.finishRefresh().finishLoadMore();
                mAdapter.setList(data.getData());
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(getActivity(), LoginActivity.class);
                startActivity(loginIntent);
                getActivity().finish();
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
        return getActivity();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_money_huoqi:
                Intent huoqiIntent = new Intent(getActivity(), ProductActivity.class);
                huoqiIntent.putExtra(AppConstant.PRODUCT_LIST_TYPE, 1);
                startActivity(huoqiIntent);
                break;
            case R.id.tv_money_dingqi:
                Intent dingqiIntent = new Intent(getActivity(), ProductActivity.class);
                dingqiIntent.putExtra(AppConstant.PRODUCT_LIST_TYPE, 2);
                startActivity(dingqiIntent);
                break;
            case R.id.tv_money_huidui:
                Intent huiduiIntent = new Intent(getActivity(), HuiduiActivity.class);
                startActivity(huiduiIntent);
                break;
        }
    }
}