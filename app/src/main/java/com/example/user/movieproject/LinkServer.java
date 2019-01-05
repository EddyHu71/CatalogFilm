package com.example.user.movieproject;


public class LinkServer {

    public static final String BASE_URL = "https://api.themoviedb.org/";

    public static ApiFilm getApiFilm() {
        return RetrofitHelper.getClient(BASE_URL).create(ApiFilm.class);
    }
}
