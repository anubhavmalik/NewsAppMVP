package com.anubhavmalikdeveloper.newsappmvp.Data.Databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.anubhavmalikdeveloper.newsappmvp.Data.Dao.NewsDao;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;

public abstract class NewsDatabase extends RoomDatabase {
    @Database(entities = {Article.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract NewsDao newsDao();
    }
}
