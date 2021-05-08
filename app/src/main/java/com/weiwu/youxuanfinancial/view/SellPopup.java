package com.weiwu.youxuanfinancial.view;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;

import com.weiwu.youxuanfinancial.R;

import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.TranslationConfig;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/30 13:20 
 */
public class SellPopup extends BasePopupWindow {
    public SellPopup(Context context) {
        super(context);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.popup_sell);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.FROM_BOTTOM)
                .toShow();

    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.TO_BOTTOM)
                .toDismiss();
    }
}
