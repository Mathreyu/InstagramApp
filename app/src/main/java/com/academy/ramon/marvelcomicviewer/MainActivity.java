package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.DefaultItemAnimator;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.models.Hero;
import com.academy.ramon.marvelcomicviewer.models.HeroesResponse;
import com.academy.ramon.marvelcomicviewer.util.GridSpacingItemDecoration;
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
    List<Hero> heros;


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
                    heros = hero.getData().getResults();
                    HeroAdapter adapter = new HeroAdapter(heros, rvHeroes.getContext());
                    rvHeroes.setLayoutManager(new GridLayoutManager(this,1));
                    rvHeroes.setItemAnimator(new DefaultItemAnimator());
                    rvHeroes.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
                    rvHeroes.setAdapter(adapter);
                });
    }

    private int dpToPx(int dp) {
        DisplayMetrics r = new DisplayMetrics();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r));
    }
}

