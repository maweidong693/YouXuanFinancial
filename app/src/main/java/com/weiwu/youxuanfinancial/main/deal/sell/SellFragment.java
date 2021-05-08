package com.weiwu.youxuanfinancial.main.deal.sell;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.data.entity.SellListData;
import com.weiwu.youxuanfinancial.data.repositories.DealRepository;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;
import com.weiwu.youxuanfinancial.main.deal.OrderDetailActivity;
import com.weiwu.youxuanfinancial.main.deal.buy.BuyPresenter;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;

public class SellFragment extends BaseFragment implements IDealContract.ISellView {

    private SmartRefreshLayout mSrlSell;
    private RecyclerView mRvSellList;
    private int mPosition;
    private SellListAdapter mAdapter;
    private IDealContract.ISellPresenter mPresenter;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sell;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        setPresenter(new SellPresenter(DealRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mSrlSell = (SmartRefreshLayout) view.findViewById(R.id.srl_sell);
        mSrlSell.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                switch (mPosition) {
                    case 0:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mAdapter.clearList();
                switch (mPosition) {
                    case 0:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }
        });
        mRvSellList = (RecyclerView) view.findViewById(R.id.rv_sell_list);
        mRvSellList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SellListAdapter();
        mRvSellList.setAdapter(mAdapter);

        mAdapter.setSellItemClickListener(new SellListAdapter.ISellItemClickListener() {
            @Override
            public void mItemClickListener(SellListData.DataBean dataBean) {
                Intent orderDetailIntent = new Intent(getContext(), OrderDetailActivity.class);
                orderDetailIntent.putExtra(AppConstant.ORDER_ID, dataBean.getId());
                orderDetailIntent.putExtra(AppConstant.ORDER_TYPE, 2);
                startActivity(orderDetailIntent);
            }
        });

        switch (mPosition) {
            case 0:
                mPresenter.getSellLIst(new ProductRequest("1", "10", "1", "1"));
                break;
            case 1:
                mPresenter.getSellLIst(new ProductRequest("1", "10", "2", "1"));
                break;
            case 2:
                mPresenter.getSellLIst(new ProductRequest(String.valueOf(page), "10", "2"));
                break;
        }
    }

    @Override
    public void mSellListResult(SellListData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                mAdapter.setList(data.getData());
                mSrlSell.finishRefresh().finishLoadMore();
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(getContext(), LoginActivity.class);
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
    public void setPresenter(IDealContract.ISellPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return getActivity();
    }
}