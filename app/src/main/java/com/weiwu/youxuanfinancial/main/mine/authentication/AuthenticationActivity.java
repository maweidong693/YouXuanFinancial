package com.weiwu.youxuanfinancial.main.mine.authentication;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
import com.weiwu.youxuanfinancial.data.entity.BaseResult;
import com.weiwu.youxuanfinancial.data.entity.UploadData;
import com.weiwu.youxuanfinancial.data.repositories.MineRepository;
import com.weiwu.youxuanfinancial.data.request.AuthenticationRequest;
import com.weiwu.youxuanfinancial.main.mine.MineContract;
import com.weiwu.youxuanfinancial.main.mine.login.LoginActivity;
import com.weiwu.youxuanfinancial.utils.GlideEngine;
import com.weiwu.youxuanfinancial.utils.IdCardUtil;
import com.weiwu.youxuanfinancial.utils.SPUtils;
import com.weiwu.youxuanfinancial.utils.SystemFacade;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class AuthenticationActivity extends BaseActivity implements View.OnClickListener, MineContract.IAuthenticationView, RadioGroup.OnCheckedChangeListener {

    private Toolbar mAuthenticationToolbar;
    private ImageView mIvUpIDCardFront;
    private ImageView mIvUpIDCardReverse;
    private TextView mTvCheckFront;
    private TextView mTvCheckReverse;
    private EditText mEtName;
    private RadioGroup mRgSex;
    private RadioButton mRbMan;
    private RadioButton mRbWoman;
    private EditText mEtCardNumber;
    private Button mBtAuthentication;
    private MineContract.IAuthenticationPresenter mPresenter;

    private boolean isFront;
    private String frontPath = "";
    private String reversePath = "";
    private int sex = 0;
    private String mName;
    private String mCardNumber;

    private int i = 0;
    private String mFrontUrl;
    private String mReverseUrl;
    private ProgressDialog mProgressDialog;
    private String TAG = "aaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        setPresenter(new AuthenticationPresenter(MineRepository.getInstance()));
        Log.d(TAG, "onCreate: " + Build.VERSION.SDK_INT);
        initView();
    }

    private void initView() {
        mAuthenticationToolbar = (Toolbar) findViewById(R.id.authentication_toolbar);
        toolbarBack(mAuthenticationToolbar);
        mIvUpIDCardFront = (ImageView) findViewById(R.id.iv_up_IDCard_front);
        mIvUpIDCardReverse = (ImageView) findViewById(R.id.iv_up_IDCard_reverse);
        mTvCheckFront = (TextView) findViewById(R.id.tv_check_front);
        mTvCheckReverse = (TextView) findViewById(R.id.tv_check_reverse);
        mTvCheckFront.setOnClickListener(this);
        mTvCheckReverse.setOnClickListener(this);
        mEtName = (EditText) findViewById(R.id.et_name);
        mRgSex = (RadioGroup) findViewById(R.id.rg_sex);
        mRgSex.setOnCheckedChangeListener(this);
        mRbMan = (RadioButton) findViewById(R.id.rb_man);
        mRbWoman = (RadioButton) findViewById(R.id.rb_woman);
        mEtCardNumber = (EditText) findViewById(R.id.et_card_number);
        mBtAuthentication = (Button) findViewById(R.id.bt_authentication);
        mBtAuthentication.setOnClickListener(this);
        mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_check_front:
                isFront = true;
                checkPermissionAndCamera();
                break;

            case R.id.tv_check_reverse:
                isFront = false;
                checkPermissionAndCamera();
                break;

            case R.id.bt_authentication:
                mName = mEtName.getText().toString();
                mCardNumber = mEtCardNumber.getText().toString();
                if (StringUtils.isNullOrEmpty(frontPath) || StringUtils.isNullOrEmpty(reversePath)) {
                    showToast("请选择正反面照片！");
                } else {
                    if (StringUtils.isNullOrEmpty(mName)) {
                        showToast("请输入姓名！");
                    } else {
                        if (sex == 0) {
                            showToast("请选择性别！");
                        } else {
                            if (StringUtils.isNullOrEmpty(mCardNumber)) {
                                showToast("请输入身份证号！");
                            } else {
                                if (IdCardUtil.isIDCardNo(mCardNumber)) {
                                    mProgressDialog.setMessage("正在提交中，请稍后");
                                    mProgressDialog.show();
                                    File file = new File(frontPath);
                                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                    MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                                    mPresenter.uploadCardPic(body);
                                } else {
                                    showToast("请输入正确的身份证号！");
                                }

                            }
                        }
                    }
                }

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
                .forResult(isFront ? AppConstant.ID_CARD_FRONT_REQUEST : AppConstant.ID_CARD_REVERSE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case AppConstant.ID_CARD_FRONT_REQUEST:
                    List<LocalMedia> frontIDCard = PictureSelector.obtainMultipleResult(data);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        frontPath = frontIDCard.get(0).getAndroidQToPath();
                    } else {
                        frontPath = frontIDCard.get(0).getPath();
                    }
                    Glide.with(this).load(frontPath).into(mIvUpIDCardFront);
                    break;

                case AppConstant.ID_CARD_REVERSE_REQUEST:
                    List<LocalMedia> reseverIDCard = PictureSelector.obtainMultipleResult(data);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        reversePath = reseverIDCard.get(0).getAndroidQToPath();
                    } else {
                        reversePath = reseverIDCard.get(0).getPath();
                    }
                    Glide.with(this).load(reversePath).into(mIvUpIDCardReverse);
                    break;

                default:
                    break;

            }

        }
    }

    @Override
    public void uploadPicResult(UploadData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1 && data.getData() != null) {
                mFrontUrl = data.getData().getUrl();
                File file = new File(reversePath);
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                mPresenter.uploadCardReversePic(body);
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                showToast("上传失败");
            }
        }
    }


    @Override
    public void uploadPicReverseResult(UploadData data) {
        if (data != null) {
            int code = data.getCode();
            if (code == 1 && data.getData() != null) {
                mReverseUrl = data.getData().getUrl();

                mPresenter.commitAuthentication(new AuthenticationRequest(mName, mCardNumber, sex, mFrontUrl, mReverseUrl));
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                showToast("上传失败");
            }
        }
    }

    @Override
    public void commitAuthenticationResult(BaseResult data) {
        mProgressDialog.cancel();
        if (data != null) {
            Integer code = data.getCode();
            if (code == 1) {
                mProgressDialog.cancel();
                showToast(data.getMsg());
                finish();
            } else if (code == 401) {
                showToast("身份验证失败，请重新登录！");
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                showToast(data.getMsg());
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
    public void setPresenter(MineContract.IAuthenticationPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Activity getActivityObject() {
        return this;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.rb_man:
                sex = 1;
                break;

            case R.id.rb_woman:
                sex = 2;
                break;
            default:
                break;
        }
    }
}