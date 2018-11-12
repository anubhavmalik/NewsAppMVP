package com.anubhavmalikdeveloper.newsappmvp.Base;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.anubhavmalikdeveloper.newsappmvp.Data.DatabaseHelper;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.SourceWrapper;
import com.anubhavmalikdeveloper.newsappmvp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreen extends AppCompatActivity {

    @BindView(R.id.lottie_view)
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        // Because te source filter returned HTML rather than JSON for every request
        DatabaseHelper databaseHelper = new DatabaseHelper();
        if (databaseHelper.getAllSourcesFromDb() == null) {
            if (databaseHelper.getAllSourcesFromDb().size() == 0) {
                Source source = new Source();
                source.setName("ABC News");
                source.setId("abc-news");
                List<Source> list = new ArrayList<>();
                list.add(source);
                databaseHelper.addAllSourceListToDb(new SourceWrapper(list));
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        lottieAnimationView.playAnimation();
        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }
}
