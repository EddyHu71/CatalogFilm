package com.example.user.movieproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import static com.example.user.movieproject.Template.DATE_AND_TIME_DETAIL;
import static com.example.user.movieproject.Template.IMAGE_DETAIL;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_DATE_TIME;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_IMAGE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_OVERVIEW;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TITLE;
import static com.example.user.movieproject.Template.MovieColumns.URI_CONTENT;
import static com.example.user.movieproject.Template.OVERVIEW_DETAIL;
import static com.example.user.movieproject.Template.TITLE_DETAIL;

import android.widget.Toast;

public class Detail extends AppCompatActivity {
    String imgDetail, judulDetail, overviewDetail, tglDetail;
    ImageView img_detail;
    FloatingActionButton favButton;
    TextView judul_detail, overview_detail, tgl_detail;

    private long id;

    boolean result = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    img_detail = findViewById(R.id.txt_img_detail);
    judul_detail = findViewById(R.id.txt_judul_detail);
    overview_detail = findViewById(R.id.txt_overview_detail);
    tgl_detail = findViewById(R.id.txt_date_detail);
    favButton = findViewById(R.id.favorButton);

    imgDetail = getIntent().getStringExtra(IMAGE_DETAIL);
    judulDetail = getIntent().getStringExtra(TITLE_DETAIL);
    overviewDetail = getIntent().getStringExtra(OVERVIEW_DETAIL);
    tglDetail = getIntent().getStringExtra(DATE_AND_TIME_DETAIL);

    Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+imgDetail)
            .override(200, 350)
            .into(img_detail);
    Log.d("TAG ", "https://image.tmdb.org/t/p/w500"+imgDetail);
    judul_detail.setText(judulDetail);
    overview_detail.setText(overviewDetail);
    tgl_detail.setText(tglDetail);
    favButton.setImageResource(R.drawable.star_unfavorit);

        favorit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Detail Film");
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private boolean favorit() {
        Uri uri = Uri.parse(URI_CONTENT+"");

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        String judul;
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(0);
                judul = cursor.getString(1);
                if (judul.equals(getIntent().getStringExtra(TITLE_DETAIL))) {
                    favButton.setImageResource(R.drawable.star_favorit);
                    result = true;
                }
            } while (cursor.moveToNext());
        }
        Log.d("TAG ", "myFavorit : "+id);
        return result;
    }

    public void myFavorit(View view) {
        if (favorit()) {
            Uri uri = Uri.parse(URI_CONTENT+"/"+id);
            getContentResolver().delete(uri, null, null);
            favButton.setImageResource(R.drawable.star_unfavorit);
            Log.d("TAG ", URI_CONTENT+"/"+id);
            Toast.makeText(getApplicationContext(), "Favorit is added", Toast.LENGTH_LONG).show();
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put(MOVIE_TITLE, judulDetail);
            contentValues.put(MOVIE_IMAGE, imgDetail);
            contentValues.put(MOVIE_OVERVIEW, overviewDetail);
            contentValues.put(MOVIE_DATE_TIME, tglDetail);

            getContentResolver().insert(URI_CONTENT, contentValues);
            setResult(101);

            favButton.setImageResource(R.drawable.star_favorit);
            Toast.makeText(getApplicationContext(), "Favorit is removed", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home : {
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
