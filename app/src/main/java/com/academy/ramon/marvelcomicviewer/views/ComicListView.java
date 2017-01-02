package com.academy.ramon.marvelcomicviewer.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.api.MarvelAPI;
import com.academy.ramon.marvelcomicviewer.models.ComicResponse;
import com.academy.ramon.marvelcomicviewer.models.ComicResults;
import com.academy.ramon.marvelcomicviewer.util.ComicAdapter;

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

public class ComicListView extends Activity {

    ComicResults[] comics;
    @BindView(R.id.rvComics) RecyclerView rvComics;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getExtras().getString("heroID");
        setContentView(R.layout.comics_list);
        getComics();
        ButterKnife.bind(this);
    }

    public void getComics(){
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MarvelAPI.ENDPOINT)
                .build();

        MarvelAPI service = retrofit.create(MarvelAPI.class);
        Observable<ComicResponse> comicService = service.listComics(id);

        comicService.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(e -> e.printStackTrace())
                .subscribe(comic -> {
                    comics = comic.getData().getResults();
                    ComicAdapter adapter = new ComicAdapter(comics, rvComics.getContext());
                    rvComics.setLayoutManager(new GridLayoutManager(this,2));
                    rvComics.setItemAnimator(new DefaultItemAnimator());
                    rvComics.setAdapter(adapter);
                });
    }
}
