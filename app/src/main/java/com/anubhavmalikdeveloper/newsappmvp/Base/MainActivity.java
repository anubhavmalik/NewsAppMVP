package com.anubhavmalikdeveloper.newsappmvp.Base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.anubhavmalikdeveloper.newsappmvp.R;
import com.anubhavmalikdeveloper.newsappmvp.AllNews.AllNewsFragment;
import com.anubhavmalikdeveloper.newsappmvp.TopNews.TopNewsFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_trending:
                    selectFragment(new TopNewsFragment());
                    return true;
                case R.id.navigation_all_news:
                    selectFragment(new AllNewsFragment());
                    return true;
                case R.id.navigation_preferred_sources:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        selectFragment(new TopNewsFragment());
    }

    private void selectFragment(Fragment fragment){

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}
