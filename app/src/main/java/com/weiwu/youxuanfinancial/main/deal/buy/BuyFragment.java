package com.weiwu.youxuanfinancial.main.deal.buy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.repositories.DealRepository;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;
import com.weiwu.youxuanfinancial.main.deal.OrderDetailActivity;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.view.SellPopup;

public class BuyFragment extends BaseFragment implements IDealContract.IBuyView, View.OnClickListener {

    private SmartRefreshLayout mSrlBuy;
    private RecyclerView mRvBuyList;
    private IDealContract.IBuyPresenter mPresenter;
    private int mPosition;
    private BuyListAdapter mAdapter;
    private SellPopup mSellPopup;
    private Button mBtSellCacel;
    private Button mBtSellConfirm;
    private BuyListData.DataBean mCheckProductData;
    private int page = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_buy;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mPosition = bundle.getInt("position");
        }
        setPresenter(new BuyPresenter(DealRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mSrlBuy = (SmartRefreshLayout) view.findViewById(R.id.srl_buy);
        mSrlBuy.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                switch (mPosition) {
                    case 0:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mAdapter.clearList();
                switch (mPosition) {
                    case 0:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "2", "1"));
                        break;
                    case 2:
                        mPresenter.getBuyProductList(new ProductRequest(String.valueOf(page), "10", "2"));
                        break;
                }
            }
        });
        mRvBuyList = (RecyclerView) view.findViewById(R.id.rv_buy_list);
        mRvBuyList.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new BuyListAdapter();
        mRvBuyList.setAdapter(mAdapter);

        mSellPopup = new SellPopup(getContext());
        mBtSellCacel = mSellPopup.findViewById(R.id.bt_cancel_sell);
        mBtSellConfirm = mSellPopup.findViewById(R.id.bt_confirm_sell);
        mBtSellCacel.setOnClickListener(this);
        mBtSellConfirm.setOnClickListener(this);

        mAdapter.setBuyItemClickListener(new BuyListAdapter.IBuyItemClickListener() {
            @Override
            public void mItemClickListener(BuyListData.DataBean dataBean) {
                mCheckProductData = dataBean;
                mSellPopup.showPopupWindow();
            }
        });

        mAdapter.setItemClickListener(new BuyListAdapter.IItemClickListener() {
            @Override
            public void mItemClickListener(BuyListData.DataBean dataBean) {
                Intent orderDetailIntent = new Intent(getContext(), OrderDetailActivity.class);
                orderDetailIntent.putExtra(AppConstant.ORDER_ID, dataBean.getId());
                orderDetailIntent.putExtra(AppConstant.ORDER_TYPE, 1);
                startActivity(orderDetailIntent);
            }
        });

        switch (mPosition) {
            case 0:
                mPresenter.getBuyProductList(new ProductRequest("1", "10", "1", "1"));
                break;
            case 1:
                mPresenter.getBuyProductList(new ProductRequest("1", "10", "2", "1"));
                break;
            case 2:
                mPresenter.getBuyProductList(new ProductRequest("1", "10", "2"));
                break;
        }
    }

    @Override
    public void buyProductListResult(BuyListData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                mAdapter.clearList();
                mAdapter.setList(data.getData());
                mSrlBuy.finishRefresh().finishLoadMore();
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
    public void getSellOutResult(BuyResultData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                showToast("卖出成功！");
                mSellPopup.dismiss();
                switch (mPosition) {
                    case 0:
                        mPresenter.getBuyProductList(new ProductRequest("1", "10", "1", "1"));
                        break;
                    case 1:
                        mPresenter.getBuyProductList(new ProductRequest("1", "10", "2", "1"));
                        break;

                    case 2:
                        mPresenter.getBuyProductList(new ProductRequest("1", "10", "2"));
                        break;
                }
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
    public void setPresenter(IDealContract.IBuyPresenter presenter) {
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
            case R.id.bt_cancel_sell:
                mSellPopup.dismiss();
                break;
            case R.id.bt_confirm_sell:
                mPresenter.sellOut(new BuyProductRequest(String.valueOf(mCheckProductData.getId())));
                break;
        }
    }
}