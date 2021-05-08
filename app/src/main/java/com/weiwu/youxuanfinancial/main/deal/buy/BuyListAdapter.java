package com.weiwu.youxuanfinancial.main.deal.buy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.data.entity.BuyListData;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 17:45 
 */
public class BuyListAdapter extends RecyclerView.Adapter<BuyListAdapter.BuyListViewHolder> {

    private List<BuyListData.DataBean> mList = new ArrayList<>();

    public void setList(List<BuyListData.DataBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public BuyListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BuyListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BuyListViewHolder holder, int position) {
        BuyListData.DataBean bean = mList.get(position);
        holder.setData(bean);
        if (bean.getStatus() == 2) {
            holder.mTvSellOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mBuyItemClickListener != null) {
                        mBuyItemClickListener.mItemClickListener(bean);
                    }
                }
            });
            holder.mTvSellOut.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.bg_confirm));
        } else {
            holder.mTvSellOut.setOnClickListener(null);
            holder.mTvSellOut.setBackground(holder.itemView.getContext().getResources().getDrawable(R.drawable.bg_cancle));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.mItemClickListener(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BuyListViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mBuyTime, mBuyMoney, mReportRate, mTvSellOut;

        public BuyListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_huidui_money_type);
            mBuyTime = (TextView) itemView.findViewById(R.id.tv_huidui_finish_money);
            mBuyMoney = (TextView) itemView.findViewById(R.id.tv_huidui_jiehuihuilv);
            mReportRate = (TextView) itemView.findViewById(R.id.tv_huidui_gouhuihuilv);
            mTvSellOut = (TextView) itemView.findViewById(R.id.tv_sell_out);
        }

        public void setData(BuyListData.DataBean data) {
            mTitle.setText(data.getTitle());
            mBuyTime.setText(data.getBuy_time());
            mReportRate.setText(data.getBuy_money() + "元");
            String payStatus = "";
            switch (data.getStatus()) {
                case 0:
                    payStatus = "付款失败";
                    break;
                case 1:
                    payStatus = "交易中";
                    break;
                case 2:
                    payStatus = "交易成功";
                    break;
                case 3:
                    payStatus = "交易失败";
                    break;
                default:
                    payStatus = "交易失败";
                    break;
            }
            mBuyMoney.setText(payStatus);
        }
    }

    private IBuyItemClickListener mBuyItemClickListener;

    public void setBuyItemClickListener(IBuyItemClickListener buyItemClickListener) {
        mBuyItemClickListener = buyItemClickListener;
    }

    public interface IBuyItemClickListener {
        void mItemClickListener(BuyListData.DataBean dataBean);
    }

    private IItemClickListener mItemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public interface IItemClickListener {
        void mItemClickListener(BuyListData.DataBean dataBean);
    }
}
