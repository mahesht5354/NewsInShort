package com.example.newsinshort;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailsActivity extends AppCompatActivity {

    ImageView image_details;
    TextView title_detail, content_detail;
    News newsData;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        image_details = findViewById(R.id.news_image_detail);
        title_detail = findViewById(R.id.news_title_details);
        content_detail = findViewById(R.id.news_content_textview);


        Intent i = getIntent();
        newsData = (News) i.getSerializableExtra("DETAILS");
        Glide.with(this).load(newsData.getImageUrl()).into(image_details);
        title_detail.setText(newsData.getTitle());
        content_detail.setText(newsData.getContent());

    }
}
