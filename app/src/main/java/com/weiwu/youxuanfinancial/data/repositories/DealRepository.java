package com.weiwu.youxuanfinancial.data.repositories;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.BaseRepository;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.base.ServerException;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.OrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;
import com.weiwu.youxuanfinancial.data.entity.SellListData;
import com.weiwu.youxuanfinancial.data.entity.SellOrderDetailData;
import com.weiwu.youxuanfinancial.data.network.DataService;
import com.weiwu.youxuanfinancial.data.network.HttpResult;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.OrderDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.deal.IDealContract;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:06 
 */
public class DealRepository extends BaseRepository implements IDealContract.IDealSource {

    public volatile static DealRepository mDealRepository;

    public static DealRepository getInstance() {
        if (mDealRepository == null) {
            synchronized (DealRepository.class) {
                if (mDealRepository == null) {
                    mDealRepository = new DealRepository();
                }
            }
        }
        return mDealRepository;
    }

    @Override
    public void getBuyProductList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<BuyListData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getBuyProductList(body), callBack);
    }

    @Override
    public void sellOut(LifecycleProvider lifecycleProvider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getSellData(body), callBack);
    }

    @Override
    public void getSellProductList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<SellListData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getSellListData(body), callBack);
    }

    @Override
    public void getOrderDetail(LifecycleProvider lifecycleProvider, OrderDetailRequest body, IBaseCallBack<OrderDetailData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getOrderDetailData(body), callBack);
    }

    @Override
    public void getSellOrderDetail(LifecycleProvider lifecycleProvider, OrderDetailRequest body, IBaseCallBack<SellOrderDetailData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getSellDetailData(body), callBack);

    }

    @Override
    public void getQueryList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<QueryListData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getQueryListData(body), callBack);
    }
}
