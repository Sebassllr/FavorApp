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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Clases.Usuario;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Models.RAFavorProfileG;
import com.example.usuario.favorapp.R;
import com.example.usuario.favorapp.Util.GridSpacingItemDecoration;
import com.example.usuario.favorapp.Util.Util;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    private Button btnAddFavor;

    private RecyclerView mRecyclerDates;
    private RAFavorProfileG mFavors;
    private LinearLayoutManager mLinearLayoutManager;

    private ArrayList<Favor> mDataTest = new ArrayList();
    private View view ;
    private Resources r;

    private FirebaseDAO firebaseDAO;
    private TextView tvName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_perfil, container, false);
        // Inflate the layout for this fragment
        initializer();
        return view;
    }

    /**
     * Inicializa los compontentes de la vista
     */
    private void initializer(){
        tvName = view.findViewById(R.id.tvName);
        btnAddFavor = view.findViewById(R.id.btnAddFavor);
        btnAddFavor.setOnClickListener(this);
        firebaseDAO = new FirebaseDAO();
        getNodes(new Favor());
        getNodes(new Usuario());
    }

    /**
     * Setea el recyclerView
     */
    private void setRecycler(){
        r = getResources();

        mRecyclerDates = view.findViewById(R.id.rv_favors_group) ;
        mRecyclerDates.setHasFixedSize(true);

        mLinearLayoutManager =  new GridLayoutManager(view.getContext(), 2);
        mRecyclerDates.setLayoutManager(mLinearLayoutManager);
        mRecyclerDates.addItemDecoration(new GridSpacingItemDecoration(2, Util.dpToPx(10,r), true));
        mRecyclerDates.setItemAnimator(new DefaultItemAnimator());

        mFavors = new RAFavorProfileG(view.getContext(),mDataTest);
        mRecyclerDates.setAdapter(mFavors);
    }

    public void getNodes(final Favor favor){
        mDataTest = new ArrayList<>();
        firebaseDAO.getDatabaseReference().child(favor.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseUser user = firebaseDAO.getFirebaseUser();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Favor object = snapshot.getValue(favor.getClass());

                            if(object != null && object.getIdOwner().equals(user.getUid())){
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

    public void getNodes(final Usuario us){
        mDataTest = new ArrayList<>();
        firebaseDAO.getDatabaseReference().child(us.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseUser user = firebaseDAO.getFirebaseUser();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Usuario object = snapshot.getValue(us.getClass());
                            if(object.getId().equals(user.getUid())){
                                tvName.setText(object.getNombre());
                            }

                        }
                        setRecycler();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Integer id = v.getId();
        switch (id){
            case R.id.btnAddFavor:{
                NavigationActivity activity = (NavigationActivity) view.getContext();
                AgregarFavorFragment fragmentClassroomCode = new AgregarFavorFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragment, fragmentClassroomCode).addToBackStack(null).commit();
                break;
            }
        }
    }
}
