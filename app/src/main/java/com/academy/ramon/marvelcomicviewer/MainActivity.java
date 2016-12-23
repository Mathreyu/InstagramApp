package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.models.Data;
import com.academy.ramon.marvelcomicviewer.models.Heroes;
import com.academy.ramon.marvelcomicviewer.models.HeroesResponse;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {
    @BindView(R.id.rvHeroes) RecyclerView rvHeroes;
    List<Heroes> heroes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHeroesList();
        ButterKnife.bind(this);


    }

    public void getHeroesList(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MarvelAPI.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MarvelAPI heroesList = retrofit.create(MarvelAPI.class);
        Call<HeroesResponse> callHeroes = heroesList.listHeroes();

        callHeroes.enqueue(new Callback<HeroesResponse>() {
            @Override
            public void onResponse(Call<HeroesResponse> call, Response<HeroesResponse> response) {
                HeroesResponse heroesResponse;
                heroesResponse = response.body();
                heroes = heroesResponse.getData().getResults();
                HeroAdapter adapter = new HeroAdapter(heroes, rvHeroes.getContext());
                rvHeroes.setAdapter(adapter);
                rvHeroes.setLayoutManager(new LinearLayoutManager(rvHeroes.getContext()));
            }
            @Override
            public void onFailure(Call<HeroesResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}
