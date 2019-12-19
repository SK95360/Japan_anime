package fr.sylvain.myapplication.controller;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import fr.sylvain.myapplication.view.MainActivity;
import fr.sylvain.myapplication.model.Movie;
import fr.sylvain.myapplication.model.MovieRestAPI;
import fr.sylvain.myapplication.view.MovieFrag;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {

    private final MovieFrag mainActivity;
    private List<Movie> listMovie;

    public MainController(MovieFrag mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onCreate() {
        //mainActivity.showLoader();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre interface MovieRestAPI
        MovieRestAPI movieRestApi = retrofit.create(MovieRestAPI.class);

        //On récupére un objet call.
        Call<List<Movie>> call = movieRestApi.getListMovie();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                listMovie = response.body();
                mainActivity.showList(listMovie);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                Log.d("Erreur", "API ERROR");
                t.printStackTrace();
            }
        });
    }

}