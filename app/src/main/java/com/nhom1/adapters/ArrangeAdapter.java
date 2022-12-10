package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.models.Product;
import com.nhom1.zinzinfood.ArrangeProduct;
import com.nhom1.zinzinfood.ProductPage;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class ArrangeAdapter extends BaseAdapter {

    ArrangeProduct activity;
    int item_layout;
    List<Product> productList;

    public ArrangeAdapter(ArrangeProduct activity, int item_layout, List<Product> productList) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ArrangeAdapter.ViewHolder holder = null;
        if(view==null)
        {
            holder = new ArrangeAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPrice = view.findViewById(R.id.txtPrice);
            holder.txtRate = view.findViewById(R.id.txtRate);
            holder.txtSold = view.findViewById(R.id.txtSold);
            holder.imvThumb = view.findViewById(R.id.imvPic);
            view.setTag(holder);
        }
        else{
            holder = (ArrangeAdapter.ViewHolder) view.getTag();
        }

        Product p = productList.get(i);
        int imageThumb = activity.getResources().getIdentifier(p.getProductThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtName.setText(p.getProductName());
        holder.txtPrice.setText(String.valueOf(p.getProductPrice()));
        holder.txtRate.setText(String.valueOf(p.getProductRate()));
        holder.txtSold.setText(String.valueOf(p.getProductSold()));
        return view;
    }
    public static class ViewHolder{
        TextView txtName, txtPrice, txtSold, txtRate;
        ImageView imvThumb;
    }
}
