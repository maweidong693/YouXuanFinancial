package com.weiwu.youxuanfinancial.main.management;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.base.IBasePresenter;
import com.weiwu.youxuanfinancial.base.IBaseView;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductDeatilData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 16:58 
 */
public interface IManagementContract {
    interface IProductListView extends IBaseView<IProductListPresenter> {
        void productListResult(ProductListData data);

        void onFail(String msg, int code);

    }

    interface IProductDetailView extends IBaseView<IProductDetailPresenter> {
        void productDetailResult(ProductDeatilData data);

        void buyProductResult(BuyResultData data);

        void onFail(String msg, int code);

    }

    interface IProductListPresenter extends IBasePresenter<IProductListView> {
        void getProductList(ProductRequest request);
    }

    interface IProductDetailPresenter extends IBasePresenter<IProductDetailView> {
        void getProductDetail(ProductDetailRequest body);

        void buyProduct(BuyProductRequest body);
    }

    interface IProductSource {
        void getProductList(LifecycleProvider provider, ProductRequest request, IBaseCallBack<ProductListData> callBack);

        void getProductDetailData(LifecycleProvider provider, ProductDetailRequest body, IBaseCallBack<ProductDeatilData> callBack);

        void buyProduct(LifecycleProvider provider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack);
    }
}
