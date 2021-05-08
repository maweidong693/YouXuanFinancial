package com.weiwu.youxuanfinancial.main.deal.query;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.data.entity.QueryListData;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/5/3 18:26 
 */
public class QueryListAdapter extends RecyclerView.Adapter<QueryListAdapter.QueryListViewHolder> {

    private List<QueryListData.DataBean> mList = new ArrayList<>();

    public void setList(List<QueryListData.DataBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public QueryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QueryListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_query, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QueryListViewHolder holder, int position) {
        QueryListData.DataBean bean = mList.get(position);
        holder.setData(bean);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class QueryListViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvQueryTitle;
        private TextView mTvQueryMoney;
        private TextView mTvQueryShouyi;
        private TextView mTvQueryTime;

        public QueryListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvQueryTitle = (TextView) itemView.findViewById(R.id.tv_query_title);
            mTvQueryMoney = (TextView) itemView.findViewById(R.id.tv_query_money);
            mTvQueryShouyi = (TextView) itemView.findViewById(R.id.tv_query_shouyi);
            mTvQueryTime = (TextView) itemView.findViewById(R.id.tv_query_time);
        }

        public void setData(QueryListData.DataBean data) {
            mTvQueryTitle.setText(data.getTitle());
            mTvQueryMoney.setText(data.getSell_money() + "");
            mTvQueryShouyi.setText(data.getIncome() + "");
            mTvQueryTime.setText(data.getPay_time());
        }
    }
}
