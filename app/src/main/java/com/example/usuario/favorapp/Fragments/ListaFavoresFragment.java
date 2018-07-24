package com.example.usuario.favorapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Models.RecyclerAdapterFavorGroup;
import com.example.usuario.favorapp.Models.RecyclerAdapterFavorList;
import com.example.usuario.favorapp.R;

import java.util.ArrayList;

public class ListaFavoresFragment extends Fragment {

    private RecyclerView mRecyclerDates;
    private RecyclerAdapterFavorGroup mFavors;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Favor> mDataTest = new ArrayList();
    private View view ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista_favores, container, false);
        setRecycler();
        return view;

    }


    private void setRecycler(){
        Favor f = new Favor("papa","papa","papa","papa","papa");
        mDataTest.add(f);

        mRecyclerDates = view.findViewById(R.id.rv_favors_group) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);

        mFavors = new RecyclerAdapterFavorGroup(view.getContext(),mDataTest);
        mRecyclerDates.setAdapter(mFavors);


    }

}
