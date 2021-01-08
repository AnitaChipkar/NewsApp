package com.anita.pricify.newsapp.sportschannel.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.ReadMoreActivity;
import com.anita.pricify.newsapp.newschannel.model.NewsModel;
import com.anita.pricify.newsapp.newschannel.view.NewsDetails;
import com.squareup.picasso.Picasso;

public class SportsDetailsActivity extends AppCompatActivity {
    TextView mTextTitle, mTextDescription, mTextReadMore;
    ImageView imgNews;
    Context context;
    NewsModel sportsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        context = SportsDetailsActivity.this;
        sportsModel = (NewsModel) getIntent().getExtras().getSerializable("sports");
        mTextTitle = (TextView) findViewById(R.id.tv_details_title);
        mTextDescription = (TextView) findViewById(R.id.tv_details_description);
        mTextReadMore = (TextView) findViewById(R.id.tv_read_more);
        imgNews = (ImageView) findViewById(R.id.img_news);
        if (sportsModel != null) {
            getSportsDetails();
        }
        mTextReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url",  sportsModel.getUrl());
                Intent intent = new Intent(context, ReadMoreActivity.class);
                intent.putExtra("url",sportsModel.getUrl());
                ((Activity) context).startActivity(intent);
            }
        });

    }

    private void getSportsDetails() {
        mTextTitle.setText(sportsModel.getTitle());
        mTextDescription.setText(sportsModel.getDescription());
        Picasso.with(context).load(sportsModel.getUrlToImage()).into(imgNews);

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
        SportsDetailsActivity.this.finish();
        super.onBackPressed();
    }
}
