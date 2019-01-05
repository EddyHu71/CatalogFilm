package com.example.user.movieproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseFilm {

    @SerializedName("page")
    @Expose
    private int page;

    @SerializedName("total_results")
    @Expose
    private int totalResult;

    @SerializedName("total_pages")
    @Expose
    private int totalPage;

    @SerializedName("results")
    @Expose
    private List<Film> film = null;

    public ResponseFilm(int page, int totalResult, int totalPage, List<Film> film) {
        this.page = page;
        this.totalResult = totalResult;
        this.totalPage = totalPage;
        this.film = film;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<Film> getFilm() {
        return film;
    }
}
