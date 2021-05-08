package com.weiwu.youxuanfinancial.main.mine.deal_detail;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.gyf.immersionbar.ImmersionBar;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;

import java.util.Calendar;

public class DealDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mDealDetailToolbar;
    private TextView mTvCalendar1;
    private TextView mTvCalendar2;
    private Button mBtConfirmTime;
    private Calendar mCalendar;
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        initView();
        toolbarBack(mDealDetailToolbar);
    }

    private void initView() {
        mDealDetailToolbar = (Toolbar) findViewById(R.id.deal_detail_toolbar);
        mTvCalendar1 = (TextView) findViewById(R.id.tv_calendar_1);
        mTvCalendar1.setOnClickListener(this);
        mTvCalendar2 = (TextView) findViewById(R.id.tv_calendar_2);
        mTvCalendar2.setOnClickListener(this);
        mBtConfirmTime = (Button) findViewById(R.id.bt_confirm_time);
        mCalendar = Calendar.getInstance();     //  获取当前时间    —   年、月、日
        year = mCalendar.get(Calendar.YEAR);         //  得到当前年
        month = mCalendar.get(Calendar.MONTH);       //  得到当前月
        day = mCalendar.get(Calendar.DAY_OF_MONTH);  //  得到当前日
        mTvCalendar2.setText(year + "." + month + "." + day + ".");
        mTvCalendar1.setText(year + "." + month + "." + day + ".");

    }

    public void getCalendarTime(TextView textView) {

        new DatePickerDialog(DealDetailActivity.this, new DatePickerDialog.OnDateSetListener() {      //  日期选择对话框
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //  这个方法是得到选择后的 年，月，日，分别对应着三个参数 — year、month、dayOfMonth
                textView.setText(year + "." + month+1 + "." + dayOfMonth + ".");
            }
        }, year, month, day).show();   //  弹出日历对话框时，默认显示 年，月，日协议，
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_calendar_1:
                getCalendarTime(mTvCalendar1);
                break;
            case R.id.tv_calendar_2:
                getCalendarTime(mTvCalendar2);
                break;
        }
    }
}