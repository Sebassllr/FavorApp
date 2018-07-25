package com.example.usuario.favorapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.usuario.favorapp.MainActivity;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;

public class PerfilFragment extends Fragment implements View.OnClickListener{

    Button btnAddFavor;

    View view;

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
        btnAddFavor = view.findViewById(R.id.btnAddFavor);
        btnAddFavor.setOnClickListener(this);
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
