package com.example.projects.googlenews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private ArrayList<Articles> list ;
    private OnItemClickListener listener;

    public ArticleAdapter(ArrayList<Articles> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list, parent, false);
        return new ArticleViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getUrlToImage()).fit().centerCrop().into(holder.image);
        holder.author.setText("By - "+list.get(position).getAuthor());
        holder.title.setText(list.get(position).getTitle());
        holder.description.setText(list.get(position).getDescription());
        holder.published.setText(list.get(position).getPublishedAt());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView author, title, description, published;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            published = itemView.findViewById(R.id.published);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(list.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Articles article);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
