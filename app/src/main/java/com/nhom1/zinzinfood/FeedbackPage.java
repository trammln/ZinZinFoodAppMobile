package com.nhom1.zinzinfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nhom1.DatabaseHelpers.FeedbackDatabase;
import com.nhom1.DatabaseHelpers.ProductDatabase;
import com.nhom1.adapters.FeedbackAdapter;
import com.nhom1.adapters.ProductAdapter;
import com.nhom1.models.Feedback;
import com.nhom1.models.Product;
import com.nhom1.zinzinfood.databinding.ActivityFeedbackpageBinding;

import java.util.ArrayList;

public class FeedbackPage extends AppCompatActivity {

    ActivityFeedbackpageBinding binding;
    FeedbackDatabase db;
    FeedbackAdapter adapter;
    ArrayList<Feedback>feedbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_feedbackpage);
        binding = ActivityFeedbackpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createDb();
        loadData();
        addEvents();
    }

    private void addEvents() {
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FeedbackPage.this, DetailProductPage.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        feedbacks = new ArrayList<>();
        Cursor c = db.getData("SELECT * FROM " + FeedbackDatabase.TBL_NAME);
        while (c.moveToNext())
        {
            feedbacks.add(new Feedback(c.getInt(0), c.getString(1), c.getDouble(2), c.getString(3), c.getString(4), c.getString(5)));
        }
        c.close();
        adapter = new FeedbackAdapter(FeedbackPage.this, R.layout.listview_feedback, feedbacks);
        binding.lvFeebacks.setAdapter(adapter);
    }

    private void createDb() {
        db = new FeedbackDatabase(FeedbackPage.this);
        db.createSampleData();
    }
}