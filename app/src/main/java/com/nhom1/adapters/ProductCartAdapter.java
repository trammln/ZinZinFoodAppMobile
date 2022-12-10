package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.DatabaseHelpers.CartDatabase;
import com.nhom1.models.ProductCart;
import com.nhom1.zinzinfood.CartActivity;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class ProductCartAdapter extends BaseAdapter {

    CartActivity activity;
    int item_layout;
    List<ProductCart> productCarts;
    double subtotal;

    public ProductCartAdapter(CartActivity activity, int item_layout, List<ProductCart> productCarts) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.productCarts = productCarts;
    }

    @Override
    public int getCount() {
        return productCarts.size();
    }

    @Override
    public Object getItem(int position) {
        return productCarts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Link views and binding data
        ViewHolder holder;
        if(convertView == null){
            //Link views
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            holder.imvThumb = convertView.findViewById(R.id.imv_Thumb);
            holder.txtName = convertView.findViewById(R.id.txt_Name);
            holder.txtPrice = convertView.findViewById(R.id.txt_Price);
            holder.txtAmount = convertView.findViewById(R.id.txt_Amount);
            holder.txtSubPrice = convertView.findViewById(R.id.txt_SubPrice);
            holder.btnAdd = convertView.findViewById(R.id.btn_Add);
            holder.btnMinus = convertView.findViewById(R.id.btn_Minus);
            holder.btnDelete = convertView.findViewById(R.id.btn_Delete);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        //Binding data
        ProductCart b = productCarts.get(position);

        int imageThumb = activity.getResources().getIdentifier(b.getProductThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtName.setText(b.getProductName());
        holder.txtPrice.setText(String.format("%.0f", b.getProductPrice()) + " đ");
        holder.txtAmount.setText(String.valueOf(b.getProductAmount()));

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.AddAmount(b);
                activity.getTotal();
                activity.getAmount();
                }
        });
        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.MinusAmount(b);
                activity.getTotal();
                activity.getAmount();
                }
        });
        subtotal = b.getProductPrice() * b.getProductAmount();
        holder.txtSubPrice.setText(String.format("%.0f", subtotal));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Delete product
                activity.openDialogDelete(b);
            }
        });

        return convertView;
    }
    public static class ViewHolder{
        ImageView imvThumb;
        TextView txtName, txtAmount, txtPrice, txtSubPrice;
        ImageButton btnAdd, btnMinus, btnDelete;
    }
}
