package com.example.usuario.favorapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Models.RecyclerAdapterFavorList;
import com.example.usuario.favorapp.R;

import java.util.ArrayList;

public class ListaFavoresFragment extends Fragment {

    private ArrayList<Favor> mData = new ArrayList<>();

    private RecyclerView recyclerView;

    private RecyclerAdapterFavorList favorList;

    private LinearLayoutManager mLinearLayoutManager;

    private FragmentTransaction transaction;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lista_favores, container, false);
        // Inflate the layout for this fragment
        transaction = getFragmentManager().beginTransaction();
        initComponents();
        return view;
    }

    /**
     * Inicializa los componetnes de la vista asociada
     */
    private void initComponents(){

        mData.add(new Favor("Favor 1", Boolean.TRUE));
        mData.add(new Favor("Favor 2", Boolean.TRUE));
        mData.add(new Favor("Favor 3", Boolean.TRUE));
        recyclerView = view.findViewById(R.id.rv_favores_list);
        recyclerView.setHasFixedSize(true);
        mLinearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        favorList = new RecyclerAdapterFavorList(view.getContext(), mData, transaction);
        recyclerView.setAdapter(favorList);
    }

}
