package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.nhom1.models.Voucher;
import com.nhom1.zinzinfood.R;
import com.nhom1.zinzinfood.VoucherPage;

import java.util.List;

public class VoucherAdapter extends BaseAdapter {
    VoucherPage activity;
    int item_layout;
    List<Voucher> vouchers;


    public VoucherAdapter(VoucherPage activity, int item_layout, List<Voucher> vouchers) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.vouchers = vouchers;
    }

    @Override
    public int getCount() {
        return vouchers.size();
    }

    @Override
    public Object getItem(int i) {
        return vouchers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtVoucherName = view.findViewById(R.id.txtVoucherName);
            holder.txtCondition = view.findViewById(R.id.txtCondition);
            holder.txtTime = view.findViewById(R.id.txtTime);
            holder.txtNote = view.findViewById(R.id.txtNote);
            holder.btnLuu = view.findViewById(R.id.btnLuu);
            holder.btnDungNgay = view.findViewById(R.id.btnDungNgay);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }
        Voucher v = vouchers.get(i);
        holder.txtVoucherName.setText(v.getVoucherName());
        holder.txtCondition.setText(v.getVoucherCondition());
        holder.txtTime.setText(v.getVoucherTime());
        holder.txtNote.setText(v.getVoucherNote());

        holder.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.luuVoucher(v);
            }
        });
        holder.btnDungNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.dungVoucher(v);
            }
        });
        return view;
    }

    public static class ViewHolder{
        TextView txtVoucherName, txtCondition, txtTime, txtNote;
        Button btnLuu, btnDungNgay;
    }
}
