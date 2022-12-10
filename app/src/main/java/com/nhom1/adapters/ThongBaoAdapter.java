package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.models.ProductCart;
import com.nhom1.models.ThongBao;
import com.nhom1.zinzinfood.CartActivity;
import com.nhom1.zinzinfood.R;
import com.nhom1.zinzinfood.ThongBaoActivity;

import java.util.List;

public class ThongBaoAdapter extends BaseAdapter {
    ThongBaoActivity activity;
    int item_layout;
    List<ThongBao> thongbao;

    public ThongBaoAdapter(ThongBaoActivity activity, int item_layout, List<ThongBao> thongbao) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.thongbao = thongbao;
    }

    @Override
    public int getCount() {
        return thongbao.size();
    }

    @Override
    public Object getItem(int i) {
        return thongbao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        //Link views and binding data
        ThongBaoAdapter.ViewHolder holder;
        if(convertView == null){
            //Link views
            holder = new ThongBaoAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            holder.imvThumb = convertView.findViewById(R.id.imv_Thumb);
            holder.txtName = convertView.findViewById(R.id.txt_Name);
            holder.txtcontent = convertView.findViewById(R.id.txt_Content);


            convertView.setTag(holder);
        }else{
            holder = (ThongBaoAdapter.ViewHolder) convertView.getTag();
        }
        //Binding data
        ThongBao b = thongbao.get(i);
        int imageThumb = activity.getResources().getIdentifier(b.getThongBaoThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtName.setText(b.getThongBaoName());
        holder.txtcontent.setText(b.getThongBaoContent());
        return convertView;
    }

    public class ViewHolder {
        ImageView imvThumb;
        TextView txtName, txtcontent;
    }
}
