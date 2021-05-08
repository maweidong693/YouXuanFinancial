package com.weiwu.youxuanfinancial.main.management.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.qiniu.android.utils.StringUtils;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductDeatilData;
import com.weiwu.youxuanfinancial.data.repositories.ProductRepository;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductDetailRequest;
import com.weiwu.youxuanfinancial.main.management.IManagementContract;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.view.BuyPopup;

public class MoneyManagementDetailActivity extends BaseActivity implements IManagementContract.IProductDetailView, View.OnClickListener {

    private IManagementContract.IProductDetailPresenter mPresenter;
    private TextView mToolbarTitle;
    private TextView mTvBuyPrice;
    private TextView mTvProduceName;
    private Button mBtBuyProduct;
    private int id;

    private Toolbar mProductDetailToolbar;
    private TextView mTvProduceType;
    private TextView mTvHintRisk;
    private TextView mTvMoneyProductReport;
    private TextView mTvHintTime;
    private TextView mTvProduceTime;
    private TextView mTextView5;
    private TextView mTvInvestDay;
    private BuyPopup mBuyPopup;
    private EditText mEtBuyPrice;
    private Button mBtCancelBuy;
    private Button mBtConfirmBuy;
    private TextView mTextView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_management_detail);
        id = getIntent().getIntExtra(AppConstant.PRODUCT_ID, 0);
        ImmersionBar.with(this).init();
        setPresenter(new ProductDetailPresenter(ProductRepository.getInstance()));
        initView();
        toolbarBack(mProductDetailToolbar);
    }

    private void initView() {
        mProductDetailToolbar = (Toolbar) findViewById(R.id.product_detail_toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mTvBuyPrice = (TextView) findViewById(R.id.tv_buy_price);
        mTvProduceName = (TextView) findViewById(R.id.tv_produce_name);
        mBtBuyProduct = (Button) findViewById(R.id.bt_buy_product);
        mBtBuyProduct.setOnClickListener(this);
        mTvProduceType = (TextView) findViewById(R.id.tv_produce_type);
        mTvHintRisk = (TextView) findViewById(R.id.tv_produce_risk);
        mTvMoneyProductReport = (TextView) findViewById(R.id.tv_money_product_report);
        mTvHintTime = (TextView) findViewById(R.id.tv_hint_time);
        mTvProduceTime = (TextView) findViewById(R.id.tv_produce_time);
        mTextView5 = (TextView) findViewById(R.id.textView5);
        mTvInvestDay = (TextView) findViewById(R.id.tv_invest_day);
        mPresenter.getProductDetail(new ProductDetailRequest(String.valueOf(id)));
        mBuyPopup = new BuyPopup(this);
        mEtBuyPrice = mBuyPopup.findViewById(R.id.et_buy_price);
        mBtConfirmBuy = mBuyPopup.findViewById(R.id.bt_confirm_buy);
        mBtConfirmBuy.setOnClickListener(this);
        mBtCancelBuy = mBuyPopup.findViewById(R.id.bt_cancel_buy);
        mBtCancelBuy.setOnClickListener(this);
        mTextView2 = (TextView) findViewById(R.id.textView2);
    }

    @Override
    public void productDetailResult(ProductDeatilData data) {
        if (data != null) {
            if (data.getCode() == 1) {
                ProductDeatilData.DataBean bean = data.getData();
                mTvBuyPrice.setText(bean.getStart_money());
                mTvProduceName.setText(bean.getTitle());
                mToolbarTitle.setText(bean.getTitle());
                mTvMoneyProductReport.setText(bean.getSeven_rate());
                mTvProduceType.setText(bean.getCate_name());
                mTvHintRisk.setText(bean.getRisk_level());
                if (StringUtils.isNullOrEmpty(bean.getFixed_time())) {
                    mTvHintTime.setVisibility(View.GONE);
                    mTvProduceTime.setVisibility(View.GONE);
                    mTextView5.setVisibility(View.GONE);
                    mTvInvestDay.setVisibility(View.GONE);
                    mTextView2.setText("七日年化收益率");

                } else {
                    mTvInvestDay.setText(bean.getFixed_time());
                    mTvProduceTime.setText(bean.getFixed_time());
                    mTvHintTime.setVisibility(View.VISIBLE);
                    mTvProduceTime.setVisibility(View.VISIBLE);
                    mTextView5.setVisibility(View.VISIBLE);
                    mTvInvestDay.setVisibility(View.VISIBLE);
                }
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
    public void setPresenter(IManagementContract.IProductDetailPresenter presenter) {
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
            case R.id.bt_buy_product:
                mBuyPopup.showPopupWindow();
                break;
            case R.id.bt_confirm_buy:
                String price = mEtBuyPrice.getText().toString();
                if (StringUtils.isNullOrEmpty(price)) {
                    showToast("请输入购买金额！");
                } else {
                    mPresenter.buyProduct(new BuyProductRequest(String.valueOf(id), price));
                }
                break;

            case R.id.bt_cancel_buy:
                mBuyPopup.dismiss();
                break;
        }
    }
}