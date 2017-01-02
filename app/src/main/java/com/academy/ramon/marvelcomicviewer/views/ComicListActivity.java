package com.academy.ramon.marvelcomicviewer.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.models.ComicResponse;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.util.ComicAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ramon on 12/28/2016.
 */

public class ComicListActivity extends Activity {

    @BindView(R.id.rvComics)
    RecyclerView rvComics;

    private List<ComicResults> comics;
    private int idExtra;
    private Retrofit retrofit;
    private MarvelAPI service;
    private ComicAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comics_list);
        ButterKnife.bind(this);
        idExtra = getIntent().getExtras().getInt("heroID");
        retrofit = buildRetrofit();
        service = buildMarvelAPI(retrofit);

        adapter = new ComicAdapter();
        rvComics.setLayoutManager(new GridLayoutManager(this, 2));
        rvComics.setItemAnimator(new DefaultItemAnimator());
        rvComics.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getComics();
    }

    public void getComics() {
        String id = idExtra + "";
        Observable<ComicResponse> comicService = service.listComics(id);

        comicService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> e.printStackTrace())
                .subscribe(comic -> {
                    comics = comic.getData().getResults();
                    adapter.addResults(comics);
                });
    }

    private MarvelAPI buildMarvelAPI(Retrofit retrofit) {
        return retrofit.create(MarvelAPI.class);
    }

    @NonNull
    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MarvelAPI.ENDPOINT)
                .build();
    }
}
