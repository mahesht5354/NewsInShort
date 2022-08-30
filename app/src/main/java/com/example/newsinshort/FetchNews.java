package com.example.newsinshort;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchNews extends AsyncTask<String, Void, String> {



    Context context;

    public FetchNews(Context context, RecyclerView recyclerView, ProgressBar pb) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.pb = pb;
    }

    RecyclerView recyclerView;
    ProgressBar pb;

    @Override
    protected String doInBackground(String... strings) {
        String link = strings[0];
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream is = connection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line = "";
            while((line = br.readLine() )!= null)
            {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        List<News> list = new ArrayList<>();
        pb.setVisibility(View.GONE);

        try {
            JSONObject root_object = new JSONObject(s);
            JSONArray data = root_object.getJSONArray("data");

            for(int i=0; i< data.length();i++) {
                JSONObject news = data.getJSONObject(i);
                String title = news.getString("title");
                String content = news.getString("content");
                String imageUrl = news.getString("imageUrl");

                list.add(new News(title, content, imageUrl));
            }
            NewsAdapter newsAdapter = new NewsAdapter(context, list);
            recyclerView.setAdapter(newsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
