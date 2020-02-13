package com.taking.sub3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.taking.sub3.R;
import com.taking.sub3.adapter.TVShowAdapter;
import com.taking.sub3.model.TVShow;
import com.taking.sub3.viewModel.TVShowViewModel;
import java.util.ArrayList;

public class TVShowFragment extends Fragment {

    private TVShowAdapter adapter;
    private ProgressBar progressBar;
    private TVShowViewModel tvShowViewModel;

    public TVShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new TVShowAdapter();
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rv_tvShow);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);

        tvShowViewModel = ViewModelProviders.of(this).get(TVShowViewModel.class);
        tvShowViewModel.getTvShow().observe(this, getTvShow);
        tvShowViewModel.setTvShow("EXTRA_TV_SHOW");

        showLoading(true);

        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_tvshow, container, false);
    }

    private Observer<ArrayList<TVShow>> getTvShow = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvShows) {
            if (tvShows != null) {
                adapter.setTvData(tvShows);
            }

            showLoading(false);
        }
    };
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
