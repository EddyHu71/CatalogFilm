package com.example.favoritemovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.example.favoritemovie.DbContract.DATE_AND_TIME_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.IMAGE_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.OVERVIEW_DETAIL_FAVORITE;
import static com.example.favoritemovie.DbContract.TITLE_DETAIL_FAVORITE;

public class DetailFavorite extends AppCompatActivity {
    String img, judul, desc, tgl;
    ImageView imgFavorit;
    TextView judulFav, descFav, tglFav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_favorite);

        img = getIntent().getStringExtra(IMAGE_DETAIL_FAVORITE);
        judul = getIntent().getStringExtra(TITLE_DETAIL_FAVORITE);
        desc = getIntent().getStringExtra(OVERVIEW_DETAIL_FAVORITE);
        tgl = getIntent().getStringExtra(DATE_AND_TIME_DETAIL_FAVORITE);

        imgFavorit = findViewById(R.id.img_Favorit);
        judulFav = findViewById(R.id.judul_Favorit);
        descFav = findViewById(R.id.overview_Favorit);
        tglFav = findViewById(R.id.date_Favorit);

        Glide.with(this)
                .load(img)
                .placeholder(R.drawable.image_icon)
                .into(imgFavorit);
        judulFav.setText(judul);
        descFav.setText(desc);
        tglFav.setText(tgl);
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
