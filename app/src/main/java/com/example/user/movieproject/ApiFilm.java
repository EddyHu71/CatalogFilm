package com.example.user.movieproject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiFilm {


    @GET("3/discover/movie")
    Call<ResponseFilm> getAllMovies(@Query("api_key") String api_key,
                                      @Query("language") String language);

    @GET("/3/search/movie")
    Call<ResponseFilm> getSearchMovie(@Query("api_key") String api_key,
                                        @Query("language") String language,
                                        @Query("query") String query);

    @GET("/3/movie/now_playing")
    Call<ResponseFilm> getNowPlayingMovie(@Query("api_key") String api_key,
                                            @Query("language") String language);

    @GET("/3/movie/upcoming")
    Call<ResponseFilm> getUpComingMovie(@Query("api_key") String api_key,
                                          @Query("language") String language);

    @GET("/3/movie/popular")
    Call<ResponseFilm> getPopularMovies(@Query("api_key") String api_key,
                                          @Query("language") String language);

    @GET("/3/movie/top_rated")
    Call<ResponseFilm> getTopRatedMovies(@Query("api_key") String api_key,
                                           @Query("language") String language);

}
