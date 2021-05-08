package com.weiwu.youxuanfinancial.main.mine.bank;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.weiwu.youxuanfinancial.R;
import com.weiwu.youxuanfinancial.data.entity.BankCardListData;

import java.util.ArrayList;
import java.util.List;

/**
 *  
 * author : 马伟东
 * email : maweidong693@163.com
 * date : 2021/4/26 10:17 
 */
public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.BankListViewHolder> {

    private List<String> mList = new ArrayList<>();

    public void setList(List<String> list) {
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
    }

    @NonNull
    @Override
    public BankListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BankListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BankListViewHolder holder, int position) {
        String bankName = mList.get(position);
        holder.setData(bankName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBankListItemClickListener != null) {
                    mBankListItemClickListener.mItemClickListener(bankName);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class BankListViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvBankName;

        public BankListViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvBankName = itemView.findViewById(R.id.tv_bank_list_name);
        }

        public void setData(String bankName) {
            mTvBankName.setText(bankName);
        }

    }

    private IBankListItemClickListener mBankListItemClickListener;

    public void setBankListItemClickListener(IBankListItemClickListener bankListItemClickListener) {
        mBankListItemClickListener = bankListItemClickListener;
    }

    public interface IBankListItemClickListener {
        void mItemClickListener(String bankName);
    }
}
