package com.example.usuario.favorapp.Fragments;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Clases.Solicitud;
import com.example.usuario.favorapp.Clases.Usuario;
import com.example.usuario.favorapp.Models.RARegalos;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class RegalosFragments extends Fragment {

    private RecyclerView mRecyclerDates;
    private RARegalos mRegalos;
    private LinearLayoutManager mLinearLayoutManager;
    private ArrayList<Solicitud> mDataTest = new ArrayList();
    private FirebaseDAO firebaseDAO;
    private View view;
    private Resources r;
    private Usuario userGiveFigt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_regalos, container, false);
        NavigationActivity.toolbar.setTitle("Mis Regalos");
        firebaseDAO = new FirebaseDAO();
        getNodes(new Solicitud());
        return view;
    }
    /**
     * Setea el recyclerView
     */
    private void setRecycler(){
        r = getResources();

        mRecyclerDates = view.findViewById(R.id.rv_regalos_nf) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager =  new GridLayoutManager(view.getContext(), 1);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mRecyclerDates.addItemDecoration(new GridSpacingItemDecoration(1, Util.dpToPx(10,r), true));
        mRecyclerDates.setItemAnimator(new DefaultItemAnimator());

        mRegalos = new RARegalos(view.getContext(),mDataTest, userGiveFigt);
        mRecyclerDates.setAdapter(mRegalos);

    }

    public void getNodes(final Solicitud solicitud){
        mDataTest = new ArrayList<>();
        firebaseDAO.getDatabaseReference().child(solicitud.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseUser user = firebaseDAO.getFirebaseUser();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Solicitud object = snapshot.getValue(solicitud.getClass());
                            if(object.getIdSolicitante().equals(user.getUid()) && object.getEstadoS() != 0 ){
                                mDataTest.add(object);
                            }
                        }
                        getNodes(new Usuario());

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    public void getNodes(final Usuario usuario){
        firebaseDAO.getDatabaseReference().child(usuario.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Usuario object = snapshot.getValue(usuario.getClass());
                            if(   mDataTest.size()>0 ){
                               if(object.getId().equals(mDataTest.get(0).getIdOwner())){
                                   userGiveFigt = object;
                                   Log.e("Err", userGiveFigt.getNombre());
                               }
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
