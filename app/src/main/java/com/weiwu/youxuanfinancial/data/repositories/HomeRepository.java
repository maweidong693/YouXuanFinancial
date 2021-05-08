package com.weiwu.youxuanfinancial.data.repositories;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.BaseRepository;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.network.DataService;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.home.HomeContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 17:06 
 */
public class HomeRepository extends BaseRepository implements HomeContract.IHomeSource {

    public volatile static HomeRepository mHomeRepository;

    public static HomeRepository getInstance() {
        if (mHomeRepository == null) {
            synchronized (HomeRepository.class) {
                if (mHomeRepository == null) {
                    mHomeRepository = new HomeRepository();
                }
            }
        }
        return mHomeRepository;
    }

    @Override
    public void getHuiduiList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<ProductListData> callBack) {
        observerNoMap(lifecycleProvider, DataService.getApiService().getProductList(body), callBack);
    }

    @Override
    public void buyProduct(LifecycleProvider provider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack) {
        observerNoMap(provider, DataService.getApiService().buyProduct(body), callBack);
    }
}
