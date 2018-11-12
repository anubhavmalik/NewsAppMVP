package com.anubhavmalikdeveloper.newsappmvp.Data;

import com.anubhavmalikdeveloper.newsappmvp.AppUtils.DatabaseConstants;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Article;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.NewsModel;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.Source;
import com.anubhavmalikdeveloper.newsappmvp.Data.Models.SourceWrapper;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    public TinyDB tinyDb;
    private Gson gson;


    public DatabaseHelper() {
        tinyDb = TinyDB.getInstance();
        gson = new Gson();
    }

    public void addAllNewsToDb(NewsModel newsModel){
        tinyDb.putString(DatabaseConstants.allNews, gson.toJson(newsModel));
    }

    public void addTopNewsToDb(NewsModel newsModel){
        tinyDb.putString(DatabaseConstants.topHeadlinesList, gson.toJson(newsModel));
    }

    public void addMoreAllNewsListToDb(NewsModel newsModel) {
        List<Article> articleArrayList = getAllNewsListFromDb();
        articleArrayList.addAll(newsModel.getArticles());
        newsModel.setArticles(articleArrayList);
        tinyDb.putString(DatabaseConstants.topHeadlinesList, gson.toJson(newsModel));
    }

    public void addMoreTopNewsListToDb(NewsModel newsModel) {
        List<Article> articleArrayList = getTopNewsListFromDb();
        articleArrayList.addAll(newsModel.getArticles());
        newsModel.setArticles(articleArrayList);
        tinyDb.putString(DatabaseConstants.topHeadlinesList, gson.toJson(newsModel));
    }

    public void addAllSourceListToDb(SourceWrapper sourceWrapper) {
        tinyDb.putString(DatabaseConstants.allNews, gson.toJson(sourceWrapper));
    }

//    public void addToSelectedSource(Source source) {
//        List<Source> sourceList = getAllSourcesFromDb();
//        if (sourceList.contains(source)) {
//            source.setFavorite(true);
//            sourceList.add(source);
//            tinyDb.putString(DatabaseConstants.selectedSources, gson.toJson(new SourceWrapper(sourceList)));
//        } else {
//            return;
//        }
//    }

    public List<Article> getAllNewsListFromDb() {
        if (tinyDb.getString(DatabaseConstants.allNews) != null && !tinyDb.getString(DatabaseConstants.allNews).isEmpty()) {
            NewsModel newsModel = gson.fromJson(tinyDb.getString(DatabaseConstants.allNews), NewsModel.class);
            return newsModel.getArticles();
        }
        return new ArrayList<>();
    }

    public List<Article> getTopNewsListFromDb() {
        if (tinyDb.getString(DatabaseConstants.topHeadlinesList) != null && !tinyDb.getString(DatabaseConstants.topHeadlinesList).isEmpty()) {
            NewsModel newsModel = gson.fromJson(tinyDb.getString(DatabaseConstants.topHeadlinesList), NewsModel.class);
            return newsModel.getArticles();
        }
        return new ArrayList<>();
    }

    public List<Source> getAllSourcesFromDb() {
        if (tinyDb.getString(DatabaseConstants.allSources) != null && !tinyDb.getString(DatabaseConstants.allSources).isEmpty()) {
            SourceWrapper sourceWrapper = gson.fromJson(tinyDb.getString(DatabaseConstants.allSources), SourceWrapper.class);
            return sourceWrapper.getSourceList();
        }
        return new ArrayList<>();
    }

    public String getSelectedCountry() {
        return tinyDb.getString(DatabaseConstants.userCountry);
        //TODO: IDHAR IMPLEMENT ACCORDING TO SELECTION
    }

    public String getSelectedSourcesString() {
        List<Source> sourceList = getAllSourcesFromDb();
        String selectedList = "";

        for( Source source : sourceList){
            if(source.isFavorite()){
                selectedList = selectedList + ",";
            }
            if(selectedList.contains(",")) {
                if (selectedList.lastIndexOf(",") == selectedList.length() - 1) {
                    selectedList.substring(0, selectedList.length() - 1);
                }
            }
        }
        return selectedList;
    }
}
