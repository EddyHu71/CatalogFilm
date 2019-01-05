package com.example.user.movieproject;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.example.user.movieproject.Template.DATE_AND_TIME_DETAIL;
import static com.example.user.movieproject.Template.IMAGE_DETAIL;
import static com.example.user.movieproject.Template.OVERVIEW_DETAIL;
import static com.example.user.movieproject.Template.TITLE_DETAIL;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {
    private Context context;
    private List<Film> listFilm;

    public FilmAdapter(Context context, List<Film> listFilm) {
        this.context = context;
        this.listFilm = listFilm;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_movie, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Film film = listFilm.get(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+film.getPosterPath())
                .into(holder.img_template);
        holder.judul.setText(film.getTitle());
        holder.tanggal.setText(film.getReleaseDate());
        holder.card_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Detail.class);
                i.putExtra(IMAGE_DETAIL, film.getPosterPath());
                i.putExtra(TITLE_DETAIL, film.getTitle());
                i.putExtra(OVERVIEW_DETAIL, film.getOverview());
                i.putExtra(DATE_AND_TIME_DETAIL, film.getReleaseDate());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        Log.d("TAG ",  film.getPosterPath()+ " "+IMAGE_DETAIL+ " ");
    }

    @Override
    public int getItemCount() {
        Log.d("TAG ", "getItemCount executed");
        return listFilm.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_template;
        CardView card_main;
        TextView judul, tanggal;

        public MyViewHolder(View itemView) {
            super(itemView);

        card_main = itemView.findViewById(R.id.card_template);

        img_template = itemView.findViewById(R.id.img_template);
        judul = itemView.findViewById(R.id.text_judul_template);
        tanggal = itemView.findViewById(R.id.txt_date_template);
        }
    }
}
