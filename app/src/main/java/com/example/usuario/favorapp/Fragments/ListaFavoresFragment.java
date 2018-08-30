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
import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Models.RAFavorGroup;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;
import com.example.usuario.favorapp.Util.GridSpacingItemDecoration;
import com.example.usuario.favorapp.Util.Util;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaFavoresFragment extends Fragment {

    private RecyclerView mRecyclerDates;
    private RAFavorGroup mFavors;
    private LinearLayoutManager mLinearLayoutManager;

    public static List<Favor> mDataTest = new ArrayList();
    private View view;
    private Resources r;
    private FirebaseDAO firebaseDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_lista_favores, container, false);

        NavigationActivity.toolbar.setTitle("Inicio");
        init();
        getNodes(new Favor());

        return view;

    }

    private void init(){
        firebaseDAO = new FirebaseDAO();
    }

    private void setRecycler(){
        r = getResources();

        mRecyclerDates = view.findViewById(R.id.rv_favors_group) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager =  new GridLayoutManager(view.getContext(), 2);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mRecyclerDates.addItemDecoration(new GridSpacingItemDecoration(2, Util.dpToPx(10,r), true));
        mRecyclerDates.setItemAnimator(new DefaultItemAnimator());
        mFavors = new RAFavorGroup(view.getContext(), mDataTest);
        mRecyclerDates.setAdapter(mFavors);
    }

    public void getNodes(final Favor favor){
        mDataTest = new ArrayList<>();
        final FirebaseUser usa = firebaseDAO.firebaseAuth.getCurrentUser();
        firebaseDAO.getDatabaseReference().child(favor.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Favor object = snapshot.getValue(favor.getClass());

                            if(object.getDisponibilidad()==0 && !object.getIdOwner().equals(usa.getUid())){
                                mDataTest.add(object);
                            }
                        }
                        setRecycler();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}
