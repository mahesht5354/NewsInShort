package com.example.newsinshort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String URL= "https://inshortsapi.vercel.app/news?category=entertainment";
    RecyclerView recyclerView;

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        pb = findViewById(R.id.progressBar2);
        pb.setVisibility(View.GONE);

    }

    public void getNews(View view) {
        pb.setVisibility(View.VISIBLE);
        new FetchNews(this, recyclerView ,pb).execute(URL);
    }
}