package com.example.projects.googlenews;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetArticlesData {

    @GET("everything?q=bitcoin&apiKey=95d4767be9b44701b500d02ef86abfff")
    Call<ArticlesList> getArticles();
}
