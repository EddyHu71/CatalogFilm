package com.example.favoritemovie;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmItem implements Parcelable {
    private int id;
    private String title;
    private String image;
    private String date;
    private String overview;
    private String votecount;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    protected FilmItem(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.overview = in.readString();
        this.date = in.readString();
        this.image = in.readString();
    }

    public static final Creator<FilmItem> CREATOR = new Creator<FilmItem>() {
        @Override
        public FilmItem createFromParcel(Parcel in) {

            return new FilmItem(in);
        }

        @Override
        public FilmItem[] newArray(int size) {
            return new FilmItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    parcel.writeInt(this.id);
    parcel.writeString(this.title);
    parcel.writeString(this.overview);
    parcel.writeString(this.image);
    parcel.writeString(this.date);
    }
}
