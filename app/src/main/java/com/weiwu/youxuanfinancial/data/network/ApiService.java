package com.weiwu.youxuanfinancial.data.network;

import com.weiwu.youxuanfinancial.data.entity.AuthenticationStatusData;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.BuyResultData;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.OrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;
import com.weiwu.youxuanfinancial.data.entity.LoginData;
import com.weiwu.youxuanfinancial.data.entity.ProductDeatilData;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;
import com.weiwu.youxuanfinancial.data.entity.SellListData;
import com.weiwu.youxuanfinancial.data.entity.SellOrderDetailData;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.entity.VerifyData;
import com.weiwu.youxuanfinancial.data.request.AuthenticationRequest;
import com.weiwu.youxuanfinancial.data.request.BindBankRequest;
import com.weiwu.youxuanfinancial.data.request.BuyProductRequest;
import com.weiwu.youxuanfinancial.data.request.OrderDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductDetailRequest;
import com.weiwu.youxuanfinancial.data.request.ProductRequest;
import com.weiwu.youxuanfinancial.data.request.VerifyRequestBody;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 09:33 
 */
public interface ApiService {

    @POST("Pub/sendMs")
    Observable<VerifyData> getVerify(@Body VerifyRequestBody body);

    @POST("Pub/login")
    Observable<LoginData> getLogin(@Body VerifyRequestBody body);

    @POST("Member/get_bank")
    Observable<BankCardListData> getBankListData();

    @POST("Member/bank_sub")
    Observable<BaseResult> getBindBankResult(@Body BindBankRequest body);

    @POST("Member/real_sub")
    Observable<BaseResult> commitAuthentication(@Body AuthenticationRequest body);

    @Multipart
    @POST("Pub/upload")
    Observable<UploadData> uploadPicture(@Part MultipartBody.Part file);

    @POST("Member/getRealStatus")
    Observable<AuthenticationStatusData> getAuthenticationStatus();

    @POST("Member/getBankStatus")
    Observable<AuthenticationStatusData> getBankCardBindStatus();

    @POST("Product/product_list")
    Observable<ProductListData> getProductList(@Body ProductRequest body);

    @POST("Product/product_detail")
    Observable<ProductDeatilData> getProductDetail(@Body ProductDetailRequest body);

    @POST("Order/buy")
    Observable<BuyResultData> buyProduct(@Body BuyProductRequest body);

    @POST("Order/buy_list")
    Observable<BuyListData> getBuyProductList(@Body ProductRequest body);

    @POST("Order/sell")
    Observable<BuyResultData> getSellData(@Body BuyProductRequest body);

    @POST("Order/sell_list")
    Observable<SellListData> getSellListData(@Body ProductRequest body);

    @POST("Order/buy_detail")
    Observable<OrderDetailData> getOrderDetailData(@Body OrderDetailRequest body);

    @POST("Order/sell_detail")
    Observable<SellOrderDetailData> getSellDetailData(@Body OrderDetailRequest body);

    @POST("Order/deal_order")
    Observable<QueryListData> getQueryListData(@Body ProductRequest body);
}
