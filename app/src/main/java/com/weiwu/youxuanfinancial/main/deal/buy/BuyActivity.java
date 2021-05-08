package com.weiwu.youxuanfinancial.main.deal.buy;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends BaseActivity {

    private Toolbar mByToolbar;
    private TabLayout mTbBuy;
    private ViewPager mVpBuy;

    private List<TabData> mTabData = new ArrayList<>();
    private BuyViewPagerAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        ImmersionBar.with(this).init();
        initView();
        toolbarBack(mByToolbar);
    }

    private void initView() {
        mByToolbar = (Toolbar) findViewById(R.id.by_toolbar);
        mTbBuy = (TabLayout) findViewById(R.id.tb_buy);
        mVpBuy = (ViewPager) findViewById(R.id.vp_buy);
        initData();

        mVpAdapter = new BuyViewPagerAdapter(getSupportFragmentManager());
        mVpBuy.setAdapter(mVpAdapter);
        mTbBuy.setupWithViewPager(mVpBuy);
    }

    private void initData() {
        TabData patientReferralHistoryTab = new TabData("活期");
        mTabData.add(patientReferralHistoryTab);
        TabData patientFollowHistoryTab = new TabData("定期");
        mTabData.add(patientFollowHistoryTab);
        TabData patientScreeningHistoryTab = new TabData("汇兑");
        mTabData.add(patientScreeningHistoryTab);
    }


    public class BuyViewPagerAdapter extends FragmentPagerAdapter {
        public BuyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            BuyFragment patientPageFragment = new BuyFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            patientPageFragment.setArguments(bundle);
            return patientPageFragment;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            BuyFragment fragment = (BuyFragment) super.instantiateItem(container, position);
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabData.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTabData.get(position).getTitle();
        }
    }
}