package com.weiwu.youxuanfinancial.main.deal.query;

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
import com.weiwu.youxuanfinancial.main.deal.buy.BuyFragment;
import com.weiwu.youxuanfinancial.main.deal.buy.TabData;

import java.util.ArrayList;
import java.util.List;

public class QueryActivity extends BaseActivity {

    private Toolbar mQueryToolbar;
    private TabLayout mTbQuery;
    private ViewPager mVpQuery;

    private List<TabData> mTabData = new ArrayList<>();
    private QueryViewPagerAdapter mVpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ImmersionBar.with(this).init();
        initView();
        toolbarBack(mQueryToolbar);
    }

    private void initView() {
        mQueryToolbar = (Toolbar) findViewById(R.id.query_toolbar);
        mTbQuery = (TabLayout) findViewById(R.id.tb_query);
        mVpQuery = (ViewPager) findViewById(R.id.vp_query);
        initData();

        mVpAdapter = new QueryViewPagerAdapter(getSupportFragmentManager());
        mVpQuery.setAdapter(mVpAdapter);
        mTbQuery.setupWithViewPager(mVpQuery);
    }

    private void initData() {
        TabData patientReferralHistoryTab = new TabData("活期");
        mTabData.add(patientReferralHistoryTab);
        TabData patientFollowHistoryTab = new TabData("定期");
        mTabData.add(patientFollowHistoryTab);
        TabData patientScreeningHistoryTab = new TabData("汇兑");
        mTabData.add(patientScreeningHistoryTab);
    }

    public class QueryViewPagerAdapter extends FragmentPagerAdapter {
        public QueryViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            QueryFragment patientPageFragment = new QueryFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            patientPageFragment.setArguments(bundle);
            return patientPageFragment;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            QueryFragment fragment = (QueryFragment) super.instantiateItem(container, position);
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