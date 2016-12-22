package com.academy.ramon.marvelcomicviewer.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.models.Heroes;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ramon on 12/21/2016.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.hero_name) TextView heroName;
        public ImageView heroThumbnail;

        public ViewHolder (View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);

            heroThumbnail = (ImageView) itemView.findViewById(R.id.hero_thumbnail);
        }
    }

    private List<Heroes> heroesList;
    private Context context;

    public HeroAdapter(List<Heroes> heroesList, Context context) {
        this.heroesList = heroesList;

    }

    public Context getContext() {
        return context;
    }
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View heroItem = inflater.inflate(R.layout.character_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(heroItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HeroAdapter.ViewHolder viewHolder, int position){
        Heroes hero = heroesList.get(position);
        TextView textView = viewHolder.heroName;
        textView.setText(hero.getName());
        ImageView imageView = viewHolder.heroThumbnail;
        Picasso.with(this.context).load(hero.getThumbnail().getPath()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return heroesList.size();
    }
}
