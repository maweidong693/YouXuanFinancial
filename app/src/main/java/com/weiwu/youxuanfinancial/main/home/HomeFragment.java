package com.weiwu.youxuanfinancial.main.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qiniu.android.utils.StringUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.repositories.HomeRepository;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.view.BuyPopup;


public class HomeFragment extends BaseFragment implements View.OnClickListener, HomeContract.IHuiduiView {


    private TextView mTvRmbBuy;
    private TextView mTvOutBFinish;
    private TextView mTvHomeDealDetail;
    private SmartRefreshLayout mSrlHui;
    private RecyclerView mRvHuidui;
    private HuiduiAdapter mAdapter;
    private HomeContract.IHuiduiPresenter mPresenter;

    private int size = 10;
    private int page = 1;
    private BuyPopup mBuyPopup;
    private EditText mEtBuyPrice;
    private Button mBtConfirmBuy;
    private Button mBtCancelBuy;
    private ProductListData.DataBean mCheckData;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPresenter(new HomePresenter(HomeRepository.getInstance()));
        initView(view);
    }

    private void initView(View view) {
        mTvRmbBuy = (TextView) view.findViewById(R.id.tv_rmb_buy);
        mTvOutBFinish = (TextView) view.findViewById(R.id.tv_out_b_finish);
        mTvHomeDealDetail = (TextView) view.findViewById(R.id.tv_home_deal_detail);
        mTvRmbBuy.setOnClickListener(this);
        mTvOutBFinish.setOnClickListener(this);
        mTvHomeDealDetail.setOnClickListener(this);
        mSrlHui = (SmartRefreshLayout) view.findViewById(R.id.srl_hui);
        mSrlHui.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = page + 1;
                mPresenter.getHuiduiList(new ProductRequest(String.valueOf(page), String.valueOf(size), "2"));
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                mAdapter.clearList();
                mPresenter.getHuiduiList(new ProductRequest(String.valueOf(page), String.valueOf(size), "2"));
            }
        });
        mRvHuidui = (RecyclerView) view.findViewById(R.id.rv_huidui);
        mRvHuidui.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HuiduiAdapter();
        mRvHuidui.setAdapter(mAdapter);

        mBuyPopup = new BuyPopup(getContext());
        mEtBuyPrice = mBuyPopup.findViewById(R.id.et_buy_price);
        mBtConfirmBuy = mBuyPopup.findViewById(R.id.bt_confirm_buy);
        mBtConfirmBuy.setOnClickListener(this);
        mBtCancelBuy = mBuyPopup.findViewById(R.id.bt_cancel_buy);
        mBtCancelBuy.setOnClickListener(this);

        mAdapter.setHuiduiItemClickListener(new HuiduiAdapter.IHuiduiItemClickListener() {
            @Override
            public void mItemClickListener(ProductListData.DataBean dataBean) {
                mCheckData = dataBean;
                mBuyPopup.showPopupWindow();
            }
        });

        mPresenter.getHuiduiList(new ProductRequest(String.valueOf(page), String.valueOf(size), "2"));
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

    @Override
    public void huiduiListResult(ProductListData data) {
        if (data != null) {
            int code = data.getCode();
            if (data.getData() != null) {
                mSrlHui.finishRefresh().finishLoadMore();
                mAdapter.setList(data.getData());
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
    public void buyProductResult(BuyResultData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1) {
                showToast(data.getMsg());
                mEtBuyPrice.setText("");
                mBuyPopup.dismiss();
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
    public void setPresenter(HomeContract.IHuiduiPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return getActivity();
    }
}