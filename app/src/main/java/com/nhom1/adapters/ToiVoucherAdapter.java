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
import com.nhom1.zinzinfood.VoucherCuaToi;

import java.util.List;

public class ToiVoucherAdapter extends BaseAdapter {

    VoucherCuaToi activity;
    int item_layout;
    List<Voucher> voucherList;

    public ToiVoucherAdapter(VoucherCuaToi activity, int item_layout, List<Voucher> voucherList) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.voucherList = voucherList;
    }

    @Override
    public int getCount() {
        return voucherList.size();
    }

    @Override
    public Object getItem(int i) {
        return voucherList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view ==null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtVoucherName = view.findViewById(R.id.txtVoucherName1);
            holder.txtCondition = view.findViewById(R.id.txtCondition1);
            holder.txtTime = view.findViewById(R.id.txtTime1);
            holder.txtNote = view.findViewById(R.id.txtNote1);
            holder.btnXoa = view.findViewById(R.id.btnXoa1);
            holder.btnDungNgay = view.findViewById(R.id.btnDungNgay1);
            view.setTag(holder);
        }
        else{
            holder = (ViewHolder) view.getTag();
        }

        Voucher v = voucherList.get(i);
        holder.txtVoucherName.setText(v.getVoucherName());
        holder.txtCondition.setText(v.getVoucherCondition());
        holder.txtTime.setText(v.getVoucherTime());
        holder.txtNote.setText(v.getVoucherNote());

        holder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.xoaVoucher(v);
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
        Button btnXoa, btnDungNgay;
    }
}
