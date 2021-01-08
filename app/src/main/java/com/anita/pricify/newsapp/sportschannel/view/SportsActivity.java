package com.anita.pricify.newsapp.sportschannel.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.aboutme.AboutMeActivity;
import com.anita.pricify.newsapp.newschannel.view.MainActivity;
import com.anita.pricify.newsapp.newschannel.view.NewsAdaptor;

public class SportsActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSports;
    private SportsAdaptor sportsAdaptor;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    startActivity(new Intent(SportsActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_sport:
                    startActivity(new Intent(SportsActivity.this, SportsActivity.class));
                    return true;
                case R.id.navigation_about_me:
                    startActivity(new Intent(SportsActivity.this, AboutMeActivity.class));
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        sportsAdaptor = new SportsAdaptor(this);
        recyclerViewSports = (RecyclerView) findViewById(R.id.recycler_view_sports);
        recyclerViewSports.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerViewSports.setLayoutManager(mLayoutManager);
        recyclerViewSports.setAdapter(sportsAdaptor);
    }
}
