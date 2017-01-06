package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.api.MarvelApi;
import com.academy.ramon.marvelcomicviewer.components.DaggerRetrofitComponent;
import com.academy.ramon.marvelcomicviewer.components.RetrofitModule;
import com.academy.ramon.marvelcomicviewer.presenter.MainActivityPresenter;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.rvHeroes)
    RecyclerView rvHeroes;
    @Inject
    MainActivityPresenter mainActivityPresenter;

    private HeroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerRetrofitComponent.builder().retrofitModule(new RetrofitModule(MarvelApi.ENDPOINT)).build().inject(this);

        adapter = new HeroAdapter();
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        rvHeroes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainActivityPresenter.getHeroes(adapter);
    }


}

