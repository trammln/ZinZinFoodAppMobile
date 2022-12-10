package com.nhom1.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.models.Product;
import com.nhom1.models.Sanphamyeuthich;
import com.nhom1.zinzinfood.CartActivity;
import com.nhom1.zinzinfood.Homepage;
import com.nhom1.zinzinfood.R;
import com.nhom1.zinzinfood.SanphamyeuthichPage;

import java.util.List;

public class SanphamyeuthichAdapter extends BaseAdapter {
    SanphamyeuthichPage activity;
    int item_layout;
    List<Product>products;

    public SanphamyeuthichAdapter(SanphamyeuthichPage activity, int item_layout, List<Product> products) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.products = products;
    }
    //    List<Sanphamyeuthich> spyt;
//    SanphamyeuthichAdapter adapter = this;

// khong bi loi
//    public SanphamyeuthichAdapter(Activity activity, int item_layout, List<Sanphamyeuthich> products) {
//        this.activity = (SanphamyeuthichPage) activity;
//        this.item_layout = item_layout;
//        this.spyt = products;
//    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            //Link views
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.spytThumb = view.findViewById(R.id.spyt_Thumb);
            holder.spytName = view.findViewById(R.id.spyt_Name);
            holder.spytPrice = view.findViewById(R.id.spyt_Price);
            holder.imvDelete = view.findViewById(R.id.imv_Delete);
            holder.spytMuangay = view.findViewById(R.id.spyt_Muangay);
            // holder.txtSold = view.findViewById(R.id.txtSold);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        Product b = products.get(i);
        int imageThumb = activity.getResources().getIdentifier(b.getProductThumb(), "drawable", activity.getPackageName());
//        holder.spytThumb.setImageResource(b.getProductPicture());
        holder.spytThumb.setImageResource(imageThumb);
        holder.spytName.setText(b.getProductName());
        holder.spytPrice.setText(String.valueOf((b.getProductPrice())));
        // holder.txtSold.setText(b.getProductSold());

        holder.imvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.openDelete(b);
            }
        });

      /*  holder.spytMuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.openMuangay(b);
            }
        });*/

        /*holder.imvDelete.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View arg1)
            {
                products.remove(i);
                //SanphamyeuthichAdapter.notifyDataSetChanged();
                activity.openDelete(b);
            }
        });*/
      /*  holder.spytMuangay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.openMuangay(b);
            }
        });*/
        return view;
    }

    public static class ViewHolder{
        ImageView spytThumb,imvDelete;
        TextView spytName, spytPrice, txtSold;
        Button spytMuangay;
    }
}
