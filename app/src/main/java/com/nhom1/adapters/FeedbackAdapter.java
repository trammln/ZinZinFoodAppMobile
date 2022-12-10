package com.nhom1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nhom1.models.Feedback;
import com.nhom1.zinzinfood.FeedbackPage;
import com.nhom1.zinzinfood.R;

import java.util.List;

public class FeedbackAdapter extends BaseAdapter {

    FeedbackPage activity;
    int item_layout;
    List<Feedback> feedbackList;

    public FeedbackAdapter(FeedbackPage activity, int item_layout, List<Feedback> feedbackList) {
        this.activity = activity;
        this.item_layout = item_layout;
        this.feedbackList = feedbackList;
    }

    @Override
    public int getCount() {
        return feedbackList.size();
    }

    @Override
    public Object getItem(int i) {
        return feedbackList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FeedbackAdapter.ViewHolder holder = null;
        if(view==null)
        {
            holder = new FeedbackAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.txtCustomerName = view.findViewById(R.id.txtCustomerName);
            holder.txtCustomerRate = view.findViewById(R.id.txtCustomerRate);
            holder.txtDateTime = view.findViewById(R.id.txtDateTime);
            holder.txtContent = view.findViewById(R.id.txtReview);
            holder.imvThumb = view.findViewById(R.id.imvFeedbackThumb);
            view.setTag(holder);
        }
        else{
            holder = (FeedbackAdapter.ViewHolder) view.getTag();
        }

        Feedback f = feedbackList.get(i);
        int imageThumb = activity.getResources().getIdentifier(f.getFeedbackThumb(), "drawable", activity.getPackageName());
        holder.imvThumb.setImageResource(imageThumb);
        holder.txtCustomerName.setText(f.getCustomerName());
        holder.txtCustomerRate.setText(String.valueOf(f.getCustomerRate()));
        holder.txtDateTime.setText(f.getDateTime());
        holder.txtContent.setText(f.getContent());
        return view;
    }

    public static class ViewHolder{
        TextView txtCustomerName, txtCustomerRate, txtDateTime, txtContent;
        ImageView imvThumb;
    }
}
