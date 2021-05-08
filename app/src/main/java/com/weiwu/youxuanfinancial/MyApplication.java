package com.weiwu.youxuanfinancial;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.utils.SPUtils;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/25 09:34 
 */
public class MyApplication extends Application {

    public static Application mApplicationContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }

    private static volatile long lastJump = 0;

    public static void loginAgain() {
        if (System.currentTimeMillis() - lastJump > 2000) {
            boolean b = SPUtils.clearValue(AppConstant.USER);
            lastJump = System.currentTimeMillis();
            Intent intent = new Intent(mApplicationContext, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            mApplicationContext.startActivity(intent);
        }
    }
}
