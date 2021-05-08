package com.weiwu.youxuanfinancial.main.mine.bank;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.ImmersionBar;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qiniu.android.utils.StringUtils;
import com.weiwu.youxuanfinancial.AppConstant;
import com.weiwu.youxuanfinancial.MyApplication;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.repositories.MineRepository;
import com.weiwu.youxuanfinancial.data.request.BindBankRequest;
import com.weiwu.youxuanfinancial.main.mine.MineContract;
import com.weiwu.youxuanfinancial.utils.GlideEngine;
import com.weiwu.youxuanfinancial.utils.SystemFacade;
import com.weiwu.youxuanfinancial.view.BankListPopup;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddBankCardActivity extends BaseActivity implements View.OnClickListener, MineContract.IBankView {

    private Toolbar mAddBankCardToolbar;
    private ImageView mIvBankCardMore;
    private TextView mTvBankName;
    private EditText mEtBankCardNumber;
    private Button mBtConfirmBank;
    private BankListPopup mBankListPopup;
    private RecyclerView mRvBankList;
    private BankListAdapter mAdapter;
    private MineContract.IBankPresenter mPresenter;
    private String mBankName = "";
    private RelativeLayout mRlBankName;
    private EditText mEtBankCardUserName;
    private ImageView mIvBankCardPic;
    private TextView mTvUploadBankPic;
    private String mBankPicUrl;
    private String mBankPicPath = "";
    private String cardNumber;
    private String userName;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        setPresenter(new BankCardPresenter(MineRepository.getInstance()));
        initView();
        toolbarBack(mAddBankCardToolbar);
    }

    private void initView() {
        mBankListPopup = new BankListPopup(this);
        mRvBankList = mBankListPopup.findViewById(R.id.rv_bank_list);
        mRvBankList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BankListAdapter();
        mRvBankList.setAdapter(mAdapter);
        mPresenter.getBankList();

        mAddBankCardToolbar = (Toolbar) findViewById(R.id.add_bank_card_toolbar);
        mRlBankName = (RelativeLayout) findViewById(R.id.rl_bank_name);
        mEtBankCardUserName = (EditText) findViewById(R.id.et_bank_card_user_name);
        mIvBankCardPic = (ImageView) findViewById(R.id.iv_bank_card_pic);
        mTvUploadBankPic = (TextView) findViewById(R.id.tv_upload_bank_pic);
        mTvUploadBankPic.setOnClickListener(this);
        mRlBankName.setOnClickListener(this);
        mIvBankCardMore = (ImageView) findViewById(R.id.iv_bank_card_more);
        mIvBankCardMore.setOnClickListener(this);
        mTvBankName = (TextView) findViewById(R.id.tv_bank_name);
        mTvBankName.setOnClickListener(this);
        mEtBankCardNumber = (EditText) findViewById(R.id.et_bank_card_number);
        mBtConfirmBank = (Button) findViewById(R.id.bt_confirm_bank);
        mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);

        mBtConfirmBank.setOnClickListener(this);

        mAdapter.setBankListItemClickListener(new BankListAdapter.IBankListItemClickListener() {
            @Override
            public void mItemClickListener(String bankName) {
                mBankName = bankName;
                mTvBankName.setText(mBankName);
                mBankListPopup.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_bank_name:
                mBankListPopup.showPopupWindow();
                break;

            case R.id.bt_confirm_bank:
                cardNumber = mEtBankCardNumber.getText().toString();
                userName = mEtBankCardUserName.getText().toString();
                if (StringUtils.isNullOrEmpty(userName)) {
                    showToast("请输入持卡人姓名！");
                } else {
                    if (StringUtils.isNullOrEmpty(mBankName)) {
                        showToast("请选择所属行！");
                        mBankListPopup.showPopupWindow();
                    } else {
                        if (SystemFacade.checkBankCard(cardNumber)) {
                            if (StringUtils.isNullOrEmpty(mBankPicPath)) {
                                showToast("请上传银行卡照片！");
                            } else {
                                mProgressDialog.setMessage("正在提交中，请稍后");
                                mProgressDialog.show();
                                File file = new File(mBankPicPath);
                                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                                mPresenter.uploadBankPic(body);
                            }
                        } else {
                            showToast("请输入正确的银行卡号!");
                        }
                    }
                }
                break;

            case R.id.tv_upload_bank_pic:
                checkPermissionAndCamera();
                break;
        }
    }

    // 申请相机权限的requestCode
    private static final int CAMERA_REQUEST_CODE = 0x00000012;

    /**
     * 检查权限并拍照。
     * 调用相机前先检查权限。
     */
    private void checkPermissionAndCamera() {
        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(), Manifest.permission.CAMERA);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            //有调起相机拍照。
            openCamera();
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    CAMERA_REQUEST_CODE);
        }
    }

    /**
     * 处理权限申请的回调。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //允许权限，有调起相机拍照。
                openCamera();
            } else {
                //拒绝权限，弹出提示框。
                Toast.makeText(this, "拍照权限被拒绝", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void openCamera() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
//                .isSingleDirectReturn(true)
                .previewImage(true)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isCamera(true)
                .compress(true)
                .forResult(AppConstant.BANK_CARD_PIC_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == AppConstant.BANK_CARD_PIC_REQUEST) {
            List<LocalMedia> media = PictureSelector.obtainMultipleResult(data);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                mBankPicPath = media.get(0).getAndroidQToPath();
            } else {
                mBankPicPath = media.get(0).getPath();
            }
            Glide.with(this).load(mBankPicPath).into(mIvBankCardPic);
        }
    }

    @Override
    public void bankListResult(BankCardListData data) {
        if (data != null) {
            if (data.getData() != null && data.getData().size() > 0) {
                List<String> list = data.getData();
                mAdapter.setList(list);
            }
        }
    }

    @Override
    public void bindBankCardResult(BaseResult resultl) {
        if (resultl != null) {
            if (resultl.getCode() == 1) {
                mProgressDialog.cancel();
                showToast("绑定成功！");
                finish();
            }
        }
    }

    @Override
    public void uploadBankPicResult(UploadData data) {
        if (data != null) {
            if (data.getCode() == 1) {
                mBankPicUrl = data.getData().getUrl();
                mPresenter.bindBankCard(new BindBankRequest(mBankName, cardNumber, userName, mBankPicUrl));
            } else {
                showToast("上传失败！");
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
    public void setPresenter(MineContract.IBankPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }
}