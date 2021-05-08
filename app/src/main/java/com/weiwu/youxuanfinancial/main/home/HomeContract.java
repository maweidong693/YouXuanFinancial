package com.weiwu.youxuanfinancial.main.home;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.weiwu.youxuanfinancial.base.IBaseCallBack;
import com.weiwu.youxuanfinancial.base.IBasePresenter;
import com.weiwu.youxuanfinancial.base.IBaseView;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 09:42 
 */
public interface HomeContract {
    interface IHuiduiView extends IBaseView<IHuiduiPresenter> {
        void huiduiListResult(ProductListData data);

        void buyProductResult(BuyResultData data);

        void onFail(String msg, int code);
    }

    interface IHuiduiPresenter extends IBasePresenter<IHuiduiView> {
        void getHuiduiList(ProductRequest body);

        void buyProduct(BuyProductRequest body);

    }

    interface IHomeSource {
        void getHuiduiList(LifecycleProvider lifecycleProvider, ProductRequest body, IBaseCallBack<ProductListData> callBack);

        void buyProduct(LifecycleProvider provider, BuyProductRequest body, IBaseCallBack<BuyResultData> callBack);
    }

}
