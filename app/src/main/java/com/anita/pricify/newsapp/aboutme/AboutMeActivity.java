package com.anita.pricify.newsapp.aboutme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.anita.pricify.newsapp.R;
import com.anita.pricify.newsapp.newschannel.view.MainActivity;
import com.anita.pricify.newsapp.sportschannel.view.SportsActivity;

public class AboutMeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_news:
                    startActivity(new Intent(AboutMeActivity.this, MainActivity.class));
                    return true;
                case R.id.navigation_sport:
                    startActivity(new Intent(AboutMeActivity.this, SportsActivity.class));
                    return true;
                case R.id.navigation_about_me:
                    startActivity(new Intent(AboutMeActivity.this, AboutMeActivity.class));
                    return true;
            }
            return false;
        }

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public void onBackPressed() {
        AboutMeActivity.this.finish();
        super.onBackPressed();
    }
}
