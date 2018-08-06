package com.example.usuario.favorapp.Fragments;

import android.support.v4.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Clases.Solicitud;
import com.example.usuario.favorapp.Clases.Usuario;
import com.example.usuario.favorapp.Models.RAFavorGroup;
import com.example.usuario.favorapp.Models.RAFavorProfileG;
import com.example.usuario.favorapp.Models.RANotificaciones;
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

public class FragmentNotificaciones extends Fragment {

    private View view;
    private RecyclerView mRecyclerDates;
    private RANotificaciones mNotificaciones;
    private LinearLayoutManager mLinearLayoutManager;
    private Resources r;
    public static List<Solicitud> mDataSolicitudes = new ArrayList();
    private FirebaseDAO firebaseDAO;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notificaciones, container, false);
        NavigationActivity.toolbar.setTitle("Notificaciones");
        firebaseDAO = new FirebaseDAO();
        getNodes(new Solicitud());

        return view;

    }

    private void setRecycler(){
       // mDataUsers.add(new Usuario("an","id","puntos","mail"));
       // mDataFavors.add(new Favor("id"," name","image", "pts", "fecha","descripcion", true, "idOwner"));
        r = getResources();

        mRecyclerDates = view.findViewById(R.id.rv_notificaciones) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager =  new GridLayoutManager(view.getContext(), 1);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mRecyclerDates.addItemDecoration(new GridSpacingItemDecoration(1, Util.dpToPx(10,r), true));
        mRecyclerDates.setItemAnimator(new DefaultItemAnimator());

        mNotificaciones = new RANotificaciones(view.getContext(),mDataSolicitudes);
        mRecyclerDates.setAdapter(mNotificaciones);

    }

    public void getNodes(final Solicitud solicitud){
        mDataSolicitudes = new ArrayList<>();
        final FirebaseUser user = firebaseDAO.firebaseAuth.getCurrentUser();
        firebaseDAO.getDatabaseReference().child(solicitud.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Solicitud object = snapshot.getValue(solicitud.getClass());
                            if(object.getIdOwner().equals(user.getUid())) {
                                mDataSolicitudes.add(object);
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