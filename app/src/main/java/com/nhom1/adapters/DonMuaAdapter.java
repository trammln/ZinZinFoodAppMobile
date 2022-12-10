package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.models.DonMua;
import com.nhom1.zinzinfood.DonMuaPage;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class DonMuaAdapter extends BaseAdapter {

    DonMuaPage activity;
    int item_layout;
    List<DonMua> donMuaList;

    public DonMuaAdapter(DonMuaPage activity, int item_layout, List<DonMua> donMuaList) {
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
        DonMuaAdapter.ViewHolder holder= null;
        if(view==null)
        {
            holder = new DonMuaAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtProName = view.findViewById(R.id.txtProNameDonMua);
            holder.txtProQuantity = view.findViewById(R.id.txtQuantity);
            holder.txtProPrice = view.findViewById(R.id.txtPriceDonMua);
            holder.txtQuantityTotal = view.findViewById(R.id.txtQuantitytotal);
            holder.txtTotal = view.findViewById(R.id.txtTotalDonMua);
            holder.txtDue = view.findViewById(R.id.txtDueDonMua);
            holder.imvThumb = view.findViewById(R.id.imv_DonMuaPic);
            view.setTag(holder);
        }
        else{
            holder = (DonMuaAdapter.ViewHolder) view.getTag();
        }

        DonMua donMua = donMuaList.get(i);
        int imageThumb = activity.getResources().getIdentifier(donMua.getProductThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtProName.setText(donMua.getProName());
        holder.txtProQuantity.setText(String.valueOf(donMua.getProQuantity()));
        holder.txtProPrice.setText(String.valueOf(donMua.getProRice()));
        holder.txtQuantityTotal.setText(String.valueOf(donMua.getQuantityTotal()));
        holder.txtTotal.setText(String.valueOf(donMua.getTotalDonMua()));
        holder.txtDue.setText(donMua.getDanhgiaDue());

        return view;
    }
    public static class ViewHolder{
        TextView txtProName, txtProQuantity, txtProPrice, txtQuantityTotal, txtTotal, txtDue;
        ImageView imvThumb;
    }
}
