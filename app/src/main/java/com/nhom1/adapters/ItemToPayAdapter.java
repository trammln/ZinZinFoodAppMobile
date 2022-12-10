package com.nhom1.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nhom1.models.PayProduct;
import com.nhom1.zinzinfood.PaymentActitvity;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class ItemToPayAdapter extends BaseAdapter {

    PaymentActitvity activity;
    int item_layout;
    List<PayProduct> payproducts;

    public ItemToPayAdapter(PaymentActitvity activity, int item_layout, List<PayProduct> payproducts){
        this.activity = activity;
        this.item_layout = item_layout;
        this.payproducts = payproducts;
    }

    @Override
    public int getCount() {
        return payproducts.size();
    }

    @Override
    public Object getItem(int i) {
        return payproducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            //link view
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //(LayoutInflater) ép kiểu
            view = inflater.inflate(item_layout, null);

            holder.imvProduct = view.findViewById(R.id.imvProduct);
            holder.txtProductName = view.findViewById(R.id.txtProductName);
            holder.txtUnitPrice = view.findViewById(R.id.txtUnitPrice);
            holder.txtQuantity = view.findViewById(R.id.txt_newQuantity);
//            holder.btnPlus = view.findViewById(R.id.btn_plus);
//            holder.btnMinus = view.findViewById(R.id.btn_minus);
//            holder.btnDelete = view.findViewById(R.id.btn_Delete);


            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        //Binding data
        PayProduct b = payproducts.get(i);
        int imvThumb = activity.getResources().getIdentifier(b.getProductThumb(), "drawable", activity.getPackageName());
        holder.imvProduct.setImageResource(imvThumb);
        holder.txtProductName.setText(b.getProductName());
        holder.txtUnitPrice.setText(String.format("%.0f", b.getProductPrice()) + " đ");
        holder.txtQuantity.setText(String.valueOf(b.getProductQuantity()));

//        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Delete product
//                activity.openDialogDelete(b);
//            }
//        });
//        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int numberOrder = b.getProductQuantity();
//                numberOrder++;
//                b.setProductQuantity(numberOrder);
//                holder.txtQuantity.setText(String.valueOf(numberOrder));
//                activity.getTotal();
//            }
//        });
//        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int numberOrder = b.getProductQuantity();
//                numberOrder--;
//                if(numberOrder > 0 ) {
//                    b.setProductQuantity(numberOrder);
//                    holder.txtQuantity.setText(String.valueOf(numberOrder));
//                    activity.getTotal();
//                }
//            }
//        });
        return view;
    }

    public static class ViewHolder {
        ImageView imvProduct;
        TextView txtProductName, txtUnitPrice, txtQuantity;
//        ImageButton btnDelete, btnPlus, btnMinus;
    }
}
