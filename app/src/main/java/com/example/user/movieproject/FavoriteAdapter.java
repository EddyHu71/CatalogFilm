package com.example.user.movieproject;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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

import static com.example.user.movieproject.Template.DATE_AND_TIME_DETAIL;
import static com.example.user.movieproject.Template.IMAGE_DETAIL;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_DATE_TIME;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_IMAGE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_OVERVIEW;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TITLE;
import static com.example.user.movieproject.Template.OVERVIEW_DETAIL;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyHolder>{

    private Context context;
    private Cursor listFavorite;

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setListFavorite(Cursor listFavorite) {
        this.listFavorite = listFavorite;
    }


    @Override
    public FavoriteAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.template_movie, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.MyHolder holder, int position) {
    final Favorit favorit = getItem(position);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+favorit.getImage())
                .override(400, 300)
                .into(holder.img);
        holder.judul.setText(favorit.getName());
        holder.tgl.setText(favorit.getDate());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(context, Detail.class);
                a.putExtra(IMAGE_DETAIL, favorit.getName());
                a.putExtra(OVERVIEW_DETAIL, favorit.getOverview());
                a.putExtra(DATE_AND_TIME_DETAIL, favorit.getDate());
                a.putExtra(MOVIE_IMAGE, favorit.getImage());
                a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(a);
            }
        });
        Log.d("TAG ", "onBindViewHolder in Favorite adapter executed "+favorit.getName());
    }

    @Override
    public int getItemCount() {
        if (listFavorite == null) {
            return 0;
        }
        return listFavorite.getCount();
    }

    private Favorit getItem(int position) {
        if (!listFavorite.moveToPosition(position)) {
            throw new IllegalStateException("Position failed");
        }
        return new Favorit(listFavorite);
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView judul, tgl;
        CardView cardView;

        public MyHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_template);
            img = itemView.findViewById(R.id.img_template);
            judul = itemView.findViewById(R.id.text_judul_template);
            tgl = itemView.findViewById(R.id.txt_date_template);

        }
    }
}
