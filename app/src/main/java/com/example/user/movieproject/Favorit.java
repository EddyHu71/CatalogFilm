package com.example.user.movieproject;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static com.example.user.movieproject.Template.MovieColumns.MOVIE_DATE_TIME;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_IMAGE;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_OVERVIEW;
import static com.example.user.movieproject.Template.MovieColumns.MOVIE_TITLE;
import static com.example.user.movieproject.Template.getColumnInt;
import static com.example.user.movieproject.Template.getColumnString;
import static android.provider.BaseColumns._ID;
public class Favorit implements Parcelable{
    private int id;
    private String name;
    private String image;
    private String date;
    private String overview;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public Favorit (Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.name = getColumnString(cursor, MOVIE_TITLE);
        this.image = getColumnString(cursor, MOVIE_IMAGE);
        this.overview = getColumnString(cursor, MOVIE_OVERVIEW);
        this.date = getColumnString(cursor, MOVIE_DATE_TIME);
    }

    public Favorit() {

    }

    protected Favorit(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.image = in.readString();
        this.date = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<Favorit> CREATOR = new Parcelable.Creator<Favorit>() {
        @Override
        public Favorit createFromParcel(Parcel in) {
            return new Favorit(in);
        }

        @Override
        public Favorit[] newArray(int size) {
            return new Favorit[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        parcel.writeString(this.date);
        parcel.writeString(this.overview);
    }
}
