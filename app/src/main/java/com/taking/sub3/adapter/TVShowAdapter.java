package com.taking.sub3.adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.taking.sub3.R;
import com.taking.sub3.activity.TVShowDetailActivity;
import com.taking.sub3.model.TVShow;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvData = new ArrayList<>();

    public void setTvData(ArrayList<TVShow> items) {
        tvData.clear();
        tvData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View tvView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_tv_show, viewGroup, false);
        return new TVShowViewHolder(tvView);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder tvShowViewHolder, int position) {
        tvShowViewHolder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imgPoster;
        TextView textViewName, textViewDetailOver,
                textViewVoteAverage,
                textViewVoteCount;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tv_item_name);
            textViewDetailOver = itemView.findViewById(R.id.tv_item_over);

            imgPoster = itemView.findViewById(R.id.img_item_photo);

            itemView.setOnClickListener(this);
        }

        void bind(TVShow tvShow) {
            String vote_average = Double.toString(tvShow.getVote_average());
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();


            textViewName.setText(tvShow.getName());

            textViewDetailOver.setText(tvShow.getOverview());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPoster);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TVShow tvShow = tvData.get(position);
//
            tvShow.setName(tvShow.getName());
            tvShow.setOverview(tvShow.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), TVShowDetailActivity.class);
            moveWithObjectIntent.putExtra(TVShowDetailActivity.EXTRA_TV_SHOW, tvShow);
            itemView.getContext().startActivity(moveWithObjectIntent);

        }
    }
}
