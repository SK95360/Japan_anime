package fr.sylvain.myapplication.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieRestAPI {

    @GET("films")
    Call<List<Movie>> getListMovie();

    @GET("{id}")
    Call<Movie> getMovie(@Path("id") String id);

}
