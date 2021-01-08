package com.anita.pricify.newsapp.newschannel.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.ReadMoreActivity;
import com.anita.pricify.newsapp.newschannel.model.NewsModel;
import com.anita.pricify.newsapp.sportschannel.view.SportsAdaptor;
import com.anita.pricify.newsapp.webapi.WebUrl;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NewsDetails extends AppCompatActivity {
    TextView mTextTitle, mTextDescription, mTextReadMore;
    ImageView imgNews;
    Context context;
    NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        context = NewsDetails.this;
        newsModel = (NewsModel) getIntent().getExtras().getSerializable("news");
        mTextTitle = (TextView) findViewById(R.id.tv_details_title);
        mTextDescription = (TextView) findViewById(R.id.tv_details_description);
        mTextReadMore = (TextView) findViewById(R.id.tv_read_more);
        imgNews = (ImageView) findViewById(R.id.img_news);
        if (newsModel != null) {
            getNewsDetails();
        }
        mTextReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url",  newsModel.getUrl());
                Intent intent = new Intent(context, ReadMoreActivity.class);
                intent.putExtra("url",newsModel.getUrl());
                ((Activity) context).startActivity(intent);
            }
        });

    }

    private void getNewsDetails() {
        mTextTitle.setText(newsModel.getTitle());
        mTextDescription.setText(newsModel.getDescription());
        Picasso.with(context).load(newsModel.getUrlToImage()).into(imgNews);

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }

    @Override
    public void onBackPressed() {
        NewsDetails.this.finish();
        super.onBackPressed();
    }
}