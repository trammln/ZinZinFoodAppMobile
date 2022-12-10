package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.zinzinfood.R;

public class BestsellerAdapter extends BaseAdapter {
    private Context context;
    private String[] tenBestseller;
    private int[] hinhBestseller;
    private String[] giaBestseller;

    public BestsellerAdapter(Context context, String[] tenBestseller, int[] hinhBestseller, String[] giaBestseller) {
        this.context = context;
        this.tenBestseller = tenBestseller;
        this.hinhBestseller = hinhBestseller;
        this.giaBestseller = giaBestseller;
    }

    @Override
    public int getCount() {
        return tenBestseller.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gridview_home_bestseller,null);
        TextView txtTen = view.findViewById(R.id.product_Name);
        TextView txtGia = view.findViewById(R.id.product_Price);
        ImageView imgHinh = view.findViewById(R.id.product_Thumb);

        txtTen.setText(tenBestseller[i]);
        txtGia.setText(giaBestseller[i]);
        imgHinh.setImageResource(hinhBestseller[i]);
        return view;
    }
}
