package com.weiwu.youxuanfinancial.main.deal;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.base.IBasePresenter;
import com.weiwu.youxuanfinancial.base.IBaseView;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.OrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;
import com.weiwu.youxuanfinancial.data.entity.SellListData;
import com.weiwu.youxuanfinancial.data.entity.SellOrderDetailData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.OrderDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 17:35 
 */
public interface IDealContract {

    interface IBuyView extends IBaseView<IBuyPresenter> {
        void buyProductListResult(BuyListData data);

        void getSellOutResult(BuyResultData data);

        void onFail(String msg, int code);

    }

    interface IBuyPresenter extends IBasePresenter<IBuyView> {
        void getBuyProductList(ProductRequest body);

        void sellOut(BuyProductRequest body);
    }

    interface ISellView extends IBaseView<ISellPresenter> {
        void mSellListResult(SellListData data);

        void onFail(String msg, int code);

    }

    interface IQueryView extends IBaseView<IQueryPresenter> {
        void mQueryListResult(QueryListData data);

        void onFail(String msg, int code);

    }

    interface ISellPresenter extends IBasePresenter<ISellView> {
        void getSellLIst(ProductRequest body);
    }

    interface IQueryPresenter extends IBasePresenter<IQueryView> {
        void getQueryList(ProductRequest body);
    }

    interface IOrderView extends IBaseView<IOrderPresenter> {
        void mOrderResult(OrderDetailData data);

        void mSellOrderResult(SellOrderDetailData data);

        void onFail(String msg, int code);

    }

    interface IOrderPresenter extends IBasePresenter<IOrderView> {
        void getOrderDetail(OrderDetailRequest body);

        void getSellOrderDetail(OrderDetailRequest body);
    }

    interface IDealSource {
        void getBuyProductList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<BuyListData> callBack);

        void sellOut(LifecycleProvider lifecycleProvider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack);

        void getSellProductList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<SellListData> callBack);

        void getOrderDetail(LifecycleProvider lifecycleProvider, OrderDetailRequest body, IBaseCallBack<OrderDetailData> callBack);

        void getSellOrderDetail(LifecycleProvider lifecycleProvider, OrderDetailRequest body, IBaseCallBack<SellOrderDetailData> callBack);

        void getQueryList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<QueryListData> callBack);
    }

}
