package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.models.Heroes;
import com.academy.ramon.marvelcomicviewer.models.HeroesResponse;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import java.util.List;
import rx.Observable;
import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends Activity {
    @BindView(R.id.rvHeroes) RecyclerView rvHeroes;
    List<Heroes> heroes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHeroes();
        ButterKnife.bind(this);
    }

    public void getHeroes(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MarvelAPI.ENDPOINT)
                .build();

        MarvelAPI service = retrofit.create(MarvelAPI.class);
        Observable<HeroesResponse> heroService = service.listHeroes();

        heroService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hero -> {
                    heroes = hero.getData().getResults();
                    HeroAdapter adapter = new HeroAdapter(heroes, rvHeroes.getContext());
                    rvHeroes.setAdapter(adapter);
                    rvHeroes.setLayoutManager(new LinearLayoutManager(rvHeroes.getContext()));
                });
    }
}

