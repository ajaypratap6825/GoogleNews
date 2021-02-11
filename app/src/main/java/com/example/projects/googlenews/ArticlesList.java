package com.example.projects.googlenews;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArticlesList {

    @SerializedName("articles")
    private ArrayList<Articles> articles;

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
