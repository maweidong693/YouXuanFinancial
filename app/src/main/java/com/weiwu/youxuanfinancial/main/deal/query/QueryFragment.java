package com.weiwu.youxuanfinancial.main.deal.query;

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
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;
import com.weiwu.youxuanfinancial.data.repositories.DealRepository;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;

public class QueryFragment extends BaseFragment implements IDealContract.IQueryView {

    private SmartRefreshLayout mSrlQuery;
    private RecyclerView mRvQueryList;
    private int mPosition;
    private QueryListAdapter mAdapter;
    private IDealContract.IQueryPresenter mPresenter;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_query;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        setPresenter(new QueryPresenter(DealRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mSrlQuery = (SmartRefreshLayout) view.findViewById(R.id.srl_query);
        mSrlQuery.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                switch (mPosition) {
                    case 0:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mAdapter.clearList();
                switch (mPosition) {
                    case 0:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }
        });
        mRvQueryList = (RecyclerView) view.findViewById(R.id.rv_query_list);
        mRvQueryList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new QueryListAdapter();
        mRvQueryList.setAdapter(mAdapter);

        switch (mPosition) {
            case 0:
                mPresenter.getQueryList(new ProductRequest("1", "10", "1", "1"));
                break;
            case 1:
                mPresenter.getQueryList(new ProductRequest("1", "10", "2", "1"));
                break;
            case 2:
                mPresenter.getQueryList(new ProductRequest(String.valueOf(page), "10", "2"));
                break;
        }
    }

    @Override
    public void mQueryListResult(QueryListData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                mAdapter.setList(data.getData());
                mSrlQuery.finishRefresh().finishLoadMore();
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
    public void setPresenter(IDealContract.IQueryPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return getActivity();
    }
}