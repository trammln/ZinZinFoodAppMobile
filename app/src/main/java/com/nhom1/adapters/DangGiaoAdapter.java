package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.models.DonMua;
import com.nhom1.zinzinfood.DangGiaoPage;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class DangGiaoAdapter extends BaseAdapter {

    DangGiaoPage activity;
    int item_layout;
    List<DonMua> donMuaList;

    public DangGiaoAdapter(DangGiaoPage activity, int item_layout, List<DonMua> donMuaList) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.donMuaList = donMuaList;
    }

    @Override
    public int getCount() {
        return donMuaList.size();
    }

    @Override
    public Object getItem(int i) {
        return donMuaList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DangGiaoAdapter.ViewHolder holder= null;
        if(view==null)
        {
            holder = new DangGiaoAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtProName = view.findViewById(R.id.txtProNameDang);
            holder.txtProQuantity = view.findViewById(R.id.txtQuantityDang);
            holder.txtProPrice = view.findViewById(R.id.txtPriceDonMuaDang);
            holder.txtQuantityTotal = view.findViewById(R.id.txtQuantitytotalDang);
            holder.txtTotal = view.findViewById(R.id.txtTotalDonMuaDang);
            holder.txtStatus = view.findViewById(R.id.txtStatus);
            holder.imvThumb = view.findViewById(R.id.imv_DangPic);
            view.setTag(holder);
        }
        else{
            holder = (DangGiaoAdapter.ViewHolder) view.getTag();
        }

        DonMua donMua = donMuaList.get(i);
        int imageThumb = activity.getResources().getIdentifier(donMua.getProductThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtProName.setText(donMua.getProName());
        holder.txtProQuantity.setText(String.valueOf(donMua.getProQuantity()));
        holder.txtProPrice.setText(String.valueOf(donMua.getProRice()));
        holder.txtQuantityTotal.setText(String.valueOf(donMua.getQuantityTotal()));
        holder.txtTotal.setText(String.valueOf(donMua.getTotalDonMua()));
        holder.txtStatus.setText(donMua.getDanhgiaDue());

        return view;
    }

    public static class ViewHolder{
        TextView txtProName, txtProQuantity, txtProPrice, txtQuantityTotal, txtTotal, txtStatus;
        ImageView imvThumb;
    }
}
