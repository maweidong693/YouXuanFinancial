package com.weiwu.youxuanfinancial.main.management;

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
 * date : 2021/4/25 17:14 
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder> {

    private final int mProductType;

    public ProductListAdapter(int type) {
        mProductType = type;
    }

    private List<ProductListData.DataBean> mList = new ArrayList<>();

    public void setList(List<ProductListData.DataBean> data) {
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_money_mangement, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
        ProductListData.DataBean bean = mList.get(position);
        holder.setData(bean);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mProductClickListener != null) {
                    mProductClickListener.mItemClickListener(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ProductListViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvMoneyPercent;
        private TextView mTvMoneyManagementName;
        private TextView mTvMoneyManagementTarget1;
        private TextView mTvMoneyManagementTarget2;
        private TextView mTvMoneyManagementTarget3;
        private TextView mTvMoneyManagementTarget4, mType;

        public ProductListViewHolder(@NonNull View itemView) {
            super(itemView);

            mTvMoneyPercent = (TextView) itemView.findViewById(R.id.tv_money_percent);
            mType = (TextView) itemView.findViewById(R.id.text_product_hint);
            mTvMoneyManagementName = (TextView) itemView.findViewById(R.id.tv_money_management_name);
            mTvMoneyManagementTarget1 = (TextView) itemView.findViewById(R.id.tv_money_management_target_1);
            mTvMoneyManagementTarget2 = (TextView) itemView.findViewById(R.id.tv_money_management_target_2);
            mTvMoneyManagementTarget3 = (TextView) itemView.findViewById(R.id.tv_money_management_target_3);
            mTvMoneyManagementTarget4 = (TextView) itemView.findViewById(R.id.tv_money_management_target_4);
        }

        public void setData(ProductListData.DataBean data) {

            mTvMoneyManagementName.setText(data.getTitle());
            mTvMoneyPercent.setText(data.getSeven_rate());
            if (mProductType == 1) {
                mType.setText("7日年化收益");
            }
            List<String> tag = data.getTag();
            if (tag.size() == 2) {
                mTvMoneyManagementTarget1.setText(tag.get(0));
                mTvMoneyManagementTarget2.setText(tag.get(1));
                mTvMoneyManagementTarget3.setVisibility(View.GONE);
                mTvMoneyManagementTarget4.setVisibility(View.GONE);
            } else if (tag.size() == 3) {
                mTvMoneyManagementTarget1.setText(tag.get(0));
                mTvMoneyManagementTarget2.setText(tag.get(1));
                mTvMoneyManagementTarget3.setText(tag.get(2));
                mTvMoneyManagementTarget4.setVisibility(View.GONE);
            } else if (tag.size() == 4) {
                mTvMoneyManagementTarget1.setText(tag.get(0));
                mTvMoneyManagementTarget2.setText(tag.get(1));
                mTvMoneyManagementTarget3.setText(tag.get(2));
                mTvMoneyManagementTarget4.setText(tag.get(3));
            }

        }
    }

    private IProductClickListener mProductClickListener;

    public void setProductClickListener(IProductClickListener productClickListener) {
        mProductClickListener = productClickListener;
    }

    public interface IProductClickListener {
        void mItemClickListener(ProductListData.DataBean data);
    }
}
