package com.example.projects.googlenews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetArticlesData service = RetrofitInstance.getRetrofitInstance().create(GetArticlesData.class);
        Call<ArticlesList> call = service.getArticles();
        call.enqueue(new Callback<ArticlesList>() {
            @Override
            public void onResponse(Call<ArticlesList> call, Response<ArticlesList> response) {
               generateArticleList(response.body().getArticles());
            }

            @Override
            public void onFailure(Call<ArticlesList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void generateArticleList(ArrayList<Articles> articles) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        ArticleAdapter adapter = new ArticleAdapter(articles);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Articles article) {
                String url = article.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }
}