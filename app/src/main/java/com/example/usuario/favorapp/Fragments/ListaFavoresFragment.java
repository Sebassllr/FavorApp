package com.example.usuario.favorapp.Fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Models.RAFavorGroup;
import com.example.usuario.favorapp.R;
import com.example.usuario.favorapp.Util.GridSpacingItemDecoration;
import com.example.usuario.favorapp.Util.Util;

import java.util.ArrayList;

public class ListaFavoresFragment extends Fragment {

    private RecyclerView mRecyclerDates;
    private RAFavorGroup mFavors;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Favor> mDataTest = new ArrayList();
    private View view;
    private Resources r;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista_favores, container, false);
        setRecycler();
        return view;

    }


    private void setRecycler(){
        r = getResources();
        int[] covers = new int[]{
                R.drawable.art1,
                R.drawable.art2};

        Favor f = new Favor("Reloj",covers[0],"PTS: 250","22/12/2018","papa");
        Favor f1 = new Favor("Reloj super lindo ",covers[1],"PTS: 500","22/12/2018","papa");
        mDataTest.add(f);
        mDataTest.add(f1);
        mDataTest.add(f1);
        mDataTest.add(f);


        mRecyclerDates = view.findViewById(R.id.rv_favors_group) ;
        mRecyclerDates.setHasFixedSize(true);


        mLinearLayoutManager =  new GridLayoutManager(view.getContext(), 2);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mRecyclerDates.addItemDecoration(new GridSpacingItemDecoration(2, Util.dpToPx(10,r), true));
        mRecyclerDates.setItemAnimator(new DefaultItemAnimator());

        mFavors = new RAFavorGroup(view.getContext(),mDataTest);
        mRecyclerDates.setAdapter(mFavors);

    }


}
