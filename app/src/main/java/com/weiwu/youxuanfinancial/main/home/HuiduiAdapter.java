package com.weiwu.youxuanfinancial.main.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.data.entity.ProductListData;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/29 09:31 
 */
public class HuiduiAdapter extends RecyclerView.Adapter<HuiduiAdapter.HuiduiViewHolder> {

    private List<ProductListData.DataBean> mList = new ArrayList<>();

    public void setList(List<ProductListData.DataBean> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public HuiduiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HuiduiViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_huidui, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HuiduiViewHolder holder, int position) {
        ProductListData.DataBean bean = mList.get(position);
        holder.setData(bean);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mHuiduiItemClickListener != null) {
                    mHuiduiItemClickListener.mItemClickListener(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class HuiduiViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitle, mBuyRate, mSellRate, mExchangeRate;

        public HuiduiViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_huidui_money_type);
            mBuyRate = (TextView) itemView.findViewById(R.id.tv_huidui_finish_money);
            mSellRate = (TextView) itemView.findViewById(R.id.tv_huidui_jiehuihuilv);
            mExchangeRate = (TextView) itemView.findViewById(R.id.tv_huidui_gouhuihuilv);
        }

        public void setData(ProductListData.DataBean data) {
            mTitle.setText(data.getTitle());
            mBuyRate.setText(String.valueOf(data.getBuy_rate()));
            mSellRate.setText(String.valueOf(data.getSell_rate()));
            mExchangeRate.setText(String.valueOf(data.getExchange_rate()));
        }
    }

    private IHuiduiItemClickListener mHuiduiItemClickListener;

    public void setHuiduiItemClickListener(IHuiduiItemClickListener huiduiItemClickListener) {
        mHuiduiItemClickListener = huiduiItemClickListener;
    }

    public interface IHuiduiItemClickListener {
        void mItemClickListener(ProductListData.DataBean dataBean);
    }
}
