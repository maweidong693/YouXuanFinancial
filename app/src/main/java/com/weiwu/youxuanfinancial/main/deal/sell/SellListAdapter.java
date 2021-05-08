package com.weiwu.youxuanfinancial.main.deal.sell;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;
import com.weiwu.youxuanfinancial.data.entity.SellListData;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 17:45 
 */
public class SellListAdapter extends RecyclerView.Adapter<SellListAdapter.SellListViewHolder> {

    private List<SellListData.DataBean> mList = new ArrayList<>();

    public void setList(List<SellListData.DataBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public SellListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SellListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SellListViewHolder holder, int position) {
        SellListData.DataBean bean = mList.get(position);
        holder.setData(bean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSellItemClickListener != null) {
                    mSellItemClickListener.mItemClickListener(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SellListViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mBuyTime, mBuyMoney, mReportRate;

        public SellListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_sell_title);
            mBuyTime = (TextView) itemView.findViewById(R.id.tv_sell_time);
            mBuyMoney = (TextView) itemView.findViewById(R.id.tv_sell_money);
            mReportRate = (TextView) itemView.findViewById(R.id.tv_shouyi);
        }

        public void setData(SellListData.DataBean data) {
            mTitle.setText(data.getTitle());
            mBuyTime.setText(data.getSell_time());
            mReportRate.setText(data.getStatus_msg());
            mBuyMoney.setText(String.valueOf(data.getBuy_money()));
        }
    }

    private ISellItemClickListener mSellItemClickListener;

    public void setSellItemClickListener(ISellItemClickListener sellItemClickListener) {
        mSellItemClickListener = sellItemClickListener;
    }

    public interface ISellItemClickListener {
        void mItemClickListener(SellListData.DataBean dataBean);
    }
}
