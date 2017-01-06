package com.academy.ramon.marvelcomicviewer.presenter;

import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.models.ComicResponse;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.util.ComicAdapter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ramon on 1/5/2017.
 */

public class ComicListPresenter {

    private MarvelApi service;

    public ComicListPresenter(MarvelApi service) {
        this.service = service;
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
