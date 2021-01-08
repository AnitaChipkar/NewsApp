package com.anita.pricify.newsapp.newschannel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.aboutme.AboutMeActivity;
import com.anita.pricify.newsapp.sportschannel.view.SportsActivity;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewNews;
    private NewsAdaptor newsAdaptor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                    return true;

                case R.id.navigation_sport:
                    startActivity(new Intent(MainActivity.this, SportsActivity.class));
                    return true;
                case R.id.navigation_about_me:
                    startActivity(new Intent(MainActivity.this, AboutMeActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        newsAdaptor = new NewsAdaptor(this);
        recyclerViewNews = (RecyclerView) findViewById(R.id.recycler_view_news);
        recyclerViewNews.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewNews.setLayoutManager(mLayoutManager);
        recyclerViewNews.setAdapter(newsAdaptor);
    }

}
