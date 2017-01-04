package com.academy.ramon.marvelcomicviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.academy.ramon.marvelcomicviewer.presenter.Presenter;
import com.academy.ramon.marvelcomicviewer.util.HeroAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {
    @BindView(R.id.rvHeroes)
    RecyclerView rvHeroes;
    @Inject
    Presenter presenter = new Presenter();

    private HeroAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new HeroAdapter();
        presenter = new Presenter();
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        rvHeroes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getHeroes(adapter);
    }


}

