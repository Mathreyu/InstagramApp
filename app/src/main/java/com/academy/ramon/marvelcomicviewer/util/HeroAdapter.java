package com.academy.ramon.marvelcomicviewer.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.academy.ramon.marvelcomicviewer.R;
import com.academy.ramon.marvelcomicviewer.models.Hero;
import com.academy.ramon.marvelcomicviewer.views.ComicListActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ramon on 12/21/2016.
 */

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {

    private List<Hero> heroList;

    public HeroAdapter() {
        heroList = new ArrayList<>();
    }

    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View heroItem = inflater.inflate(R.layout.character_item, parent, false);
        return new ViewHolder(heroItem);
    }

    @Override
    public void onBindViewHolder(HeroAdapter.ViewHolder viewHolder, int position) {
        Hero hero = heroList.get(position);
        viewHolder.bind(hero);
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    public void addResults(List<Hero> results) {
        heroList.addAll(results);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.hero_name)
        TextView heroName;
        @BindView(R.id.hero_thumbnail)
        CircleImageView heroThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Hero hero) {
            heroName.setText(hero.getName());
            Picasso.with(heroThumbnail.getContext()).load(hero.getThumbnail().getPath() + "." + hero.getThumbnail().getExtension()).into(heroThumbnail);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ComicListActivity.class);
                    intent.putExtra("heroID", hero.getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
