package com.weiwu.youxuanfinancial.data.repositories;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.BaseRepository;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductDeatilData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.network.DataService;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.main.management.IManagementContract;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 10:14 
 */
public class ProductRepository extends BaseRepository implements IManagementContract.IProductSource {

    public volatile static ProductRepository mProductRepository;

    public static ProductRepository getInstance() {
        if (mProductRepository == null) {
            synchronized (ProductRepository.class) {
                if (mProductRepository == null) {
                    mProductRepository = new ProductRepository();
                }
            }
        }
        return mProductRepository;
    }

    @Override
    public void getProductList(LifecycleProvider provider, ProductRequest request, IBaseCallBack<ProductListData> callBack) {
        observerNoMap(provider, DataService.getApiService().getProductList(request), callBack);
    }

    @Override
    public void getProductDetailData(LifecycleProvider provider, ProductDetailRequest body, IBaseCallBack<ProductDeatilData> callBack) {
        observerNoMap(provider, DataService.getApiService().getProductDetail(body), callBack);
    }

    @Override
    public void buyProduct(LifecycleProvider provider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack) {
        observerNoMap(provider, DataService.getApiService().buyProduct(body),callBack);
    }

}
