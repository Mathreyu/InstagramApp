package com.academy.ramon.marvelcomicviewer.presenter;

import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.components.DaggerRetrofitComponent;
import com.academy.ramon.marvelcomicviewer.components.MarvelRetrofitModule;
import com.academy.ramon.marvelcomicviewer.components.RetrofitComponent;
import com.academy.ramon.marvelcomicviewer.models.ComicResponse;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.models.Hero;
import com.academy.ramon.marvelcomicviewer.models.HeroesResponse;
import com.academy.ramon.marvelcomicviewer.util.ComicAdapter;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ramon on 1/3/2017.
 */

public class Presenter {
    RetrofitComponent retrofitComponent;
    @Inject
    MarvelAPI service;

    public Presenter() {
        retrofitComponent = DaggerRetrofitComponent.builder().marvelRetrofitModule(new MarvelRetrofitModule()).build();
        service = retrofitComponent.provideAPI();
    }

    public void getHeroes(HeroAdapter adapter) {
        Observable<HeroesResponse> heroService = service.listHeroes();

        heroService.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(hero -> {
                    List<Hero> results = hero.getData().getHeroes();
                    adapter.addResults(results);
                }, Throwable::printStackTrace);
    }

    public void getComics(int idExtra, ComicAdapter adapter) {
        String id = idExtra + "";
        Observable<ComicResponse> comicService = service.listComics(id);

        comicService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(comic -> {
                    List<ComicResults> comics = comic.getData().getResults();
                    adapter.addResults(comics);
                }, Throwable::printStackTrace);
    }
}
