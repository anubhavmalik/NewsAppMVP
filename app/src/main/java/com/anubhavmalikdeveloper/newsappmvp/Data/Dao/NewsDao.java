package com.anubhavmalikdeveloper.newsappmvp.Data.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;

import java.util.List;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM article")
    List<Article> getAll();

    @Query("SELECT * FROM article WHERE title IN (:sourceIds)")
    List<Article> loadAllArticlesBySearchTerm(String[] sourceIds);

    @Query("SELECT * FROM article WHERE source = (:source)")
    List<Article> loadAllArticlesBySource(String source);

    @Insert
    void insertAll(Article... articles);

    @Delete
    void delete(Article article);

    @Query("Delete from article")
    void deleteAll();
}
