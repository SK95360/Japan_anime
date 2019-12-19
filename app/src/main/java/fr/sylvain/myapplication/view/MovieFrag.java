package fr.sylvain.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.sylvain.myapplication.R;
import fr.sylvain.myapplication.controller.MainController;
import fr.sylvain.myapplication.controller.MyAdapter;
import fr.sylvain.myapplication.model.Movie;

public class MovieFrag extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private MainController controller;
    private View vu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vu=inflater.inflate(R.layout.frag_movie, container, false);
        recyclerView = (RecyclerView) vu.findViewById(R.id.my_recycler_view);


        controller = new MainController(this);
        controller.onCreate();
        return vu;
    }

    public void showList(List<Movie> list){
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
        mAdapter = new MyAdapter(list, this, getActivity().getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        Intent intentMain = new Intent(getActivity().getApplicationContext() ,
                MovieActivity.class);
        intentMain.putExtra("id",(String) v.getTag());
        MovieFrag.this.startActivity(intentMain);
    }
}
