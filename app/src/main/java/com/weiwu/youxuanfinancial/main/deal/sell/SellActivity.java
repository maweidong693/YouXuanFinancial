package com.weiwu.youxuanfinancial.main.deal.sell;

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
import com.weiwu.youxuanfinancial.main.deal.buy.BuyActivity;
import com.weiwu.youxuanfinancial.main.deal.buy.BuyFragment;
import com.weiwu.youxuanfinancial.main.deal.buy.TabData;

import java.util.ArrayList;
import java.util.List;

public class SellActivity extends BaseActivity {

    private Toolbar mSellToolbar;
    private TabLayout mTbSell;
    private ViewPager mVpSell;

    private List<TabData> mTabData = new ArrayList<>();
    private SellViewPagerAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        ImmersionBar.with(this).init();
        initView();
        toolbarBack(mSellToolbar);
    }

    private void initView() {
        mSellToolbar = (Toolbar) findViewById(R.id.sell_toolbar);
        mTbSell = (TabLayout) findViewById(R.id.tb_sell);
        mVpSell = (ViewPager) findViewById(R.id.vp_sell);

        initData();

        mVpAdapter = new SellViewPagerAdapter(getSupportFragmentManager());
        mVpSell.setAdapter(mVpAdapter);
        mTbSell.setupWithViewPager(mVpSell);
    }

    private void initData() {
        TabData patientReferralHistoryTab = new TabData("活期");
        mTabData.add(patientReferralHistoryTab);
        TabData patientFollowHistoryTab = new TabData("定期");
        mTabData.add(patientFollowHistoryTab);
        TabData patientScreeningHistoryTab = new TabData("汇兑");
        mTabData.add(patientScreeningHistoryTab);
    }


    public class SellViewPagerAdapter extends FragmentPagerAdapter {
        public SellViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            SellFragment patientPageFragment = new SellFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            patientPageFragment.setArguments(bundle);
            return patientPageFragment;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            SellFragment fragment = (SellFragment) super.instantiateItem(container, position);
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