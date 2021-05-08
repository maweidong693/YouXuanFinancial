package com.weiwu.youxuanfinancial.main.deal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseFragment;
import com.weiwu.youxuanfinancial.main.deal.buy.BuyActivity;
import com.weiwu.youxuanfinancial.main.deal.query.QueryActivity;
import com.weiwu.youxuanfinancial.main.deal.sell.SellActivity;
import com.weiwu.youxuanfinancial.main.mine.deal_detail.DealDetailActivity;

public class DealFragment extends BaseFragment implements View.OnClickListener {

    private TextView mTvBuyIn;
    private TextView mTvBuyOut;
    private TextView mTvQueryOrder;
    private TextView mTvDealDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deal;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {
        mTvBuyIn = (TextView) view.findViewById(R.id.tv_buy_in);
        mTvBuyIn.setOnClickListener(this);
        mTvBuyOut = (TextView) view.findViewById(R.id.tv_buy_out);
        mTvBuyOut.setOnClickListener(this);
        mTvQueryOrder = (TextView) view.findViewById(R.id.tv_query_order);
        mTvQueryOrder.setOnClickListener(this);
        mTvDealDetail = (TextView) view.findViewById(R.id.tv_deal_detail);
        mTvDealDetail.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_buy_in:
                Intent buyInIntent = new Intent(getContext(), BuyActivity.class);
                startActivity(buyInIntent);
                break;

            case R.id.tv_deal_detail:
                Intent dealDetailIntent = new Intent(getContext(), DealDetailActivity.class);
                startActivity(dealDetailIntent);
                break;

            case R.id.tv_buy_out:
                Intent sellIntent = new Intent(getContext(), SellActivity.class);
                startActivity(sellIntent);
                break;

            case R.id.tv_query_order:
                Intent queryIntent = new Intent(getContext(), QueryActivity.class);
                startActivity(queryIntent);
                break;

        }
    }
}