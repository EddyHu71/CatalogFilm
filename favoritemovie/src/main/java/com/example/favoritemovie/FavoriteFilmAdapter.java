package com.example.favoritemovie;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.CardView;
import com.bumptech.glide.Glide;

import static com.example.favoritemovie.DbContract.DATE_AND_TIME_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.FavoriteMovie.OVERVIEW_FAVORITE;
import static com.example.favoritemovie.DbContract.FavoriteMovie.POSTER_FAVORITE;
import static com.example.favoritemovie.DbContract.FavoriteMovie.RELEASE_DATE_FAVORITE;
import static com.example.favoritemovie.DbContract.FavoriteMovie.TITLE_FAVORITE;
import static com.example.favoritemovie.DbContract.IMAGE_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.OVERVIEW_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.TITLE_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.getColumnString;

public class FavoriteFilmAdapter extends CursorAdapter {
    public FavoriteFilmAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View view = LayoutInflater.from(context).inflate(R.layout.template_film, viewGroup, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        if (cursor != null) {
            final TextView textTitle, textRelease;
            final String loadGambar = "https://image.tmdb.org/t/p/w500"+getColumnString(cursor, POSTER_FAVORITE);
            ImageView gambar;
            CardView card_view;
            gambar = view.findViewById(R.id.poster_favorite);
            textTitle = view.findViewById(R.id.title_favorite);
            textRelease = view.findViewById(R.id.date_favorite);
            card_view = view.findViewById(R.id.card_view_favorite);
            card_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailFavorite.class);
                    i.putExtra(IMAGE_DETAIL_FAVORITE, loadGambar);
                    i.putExtra(TITLE_DETAIL_FAVORITE, getColumnString(cursor, TITLE_FAVORITE));
                    i.putExtra(OVERVIEW_DETAIL_FAVORITE, getColumnString(cursor, OVERVIEW_FAVORITE));
                    i.putExtra(DATE_AND_TIME_DETAIL_FAVORITE, getColumnString(cursor, RELEASE_DATE_FAVORITE));
                    context.startActivity(i);
                    Log.d("TAG ", "CardView executed"+POSTER_FAVORITE+" "+TITLE_FAVORITE+" "+OVERVIEW_FAVORITE+" "+RELEASE_DATE_FAVORITE);
                }
            });


            Glide.with(context)
                    .load(loadGambar)
                    .override(380, 180).into(gambar);
            textTitle.setText(getColumnString(cursor, TITLE_FAVORITE));
            textRelease.setText(getColumnString(cursor, RELEASE_DATE_FAVORITE));
            Log.d("TAG ", "bindView executed"+TITLE_DETAIL_FAVORITE+" "+IMAGE_DETAIL_FAVORITE+" "+OVERVIEW_DETAIL_FAVORITE+" "+DATE_AND_TIME_DETAIL_FAVORITE);
        }
    }
}
