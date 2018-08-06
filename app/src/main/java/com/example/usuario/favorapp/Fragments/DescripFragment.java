package com.example.usuario.favorapp.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.R;

public class DescripFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Bundle args;
    private ImageView iVDescripcion;
    private TextView tvNameFav,tvDescription;
    private Button btnPayFav;
    private Favor favor;
    private Transacciones tr = new Transacciones();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_descripcion, container, false);

        init();
        return view;

    }

    public void init()  {
        iVDescripcion = view.findViewById(R.id.iVDescripcion);
        tvNameFav = view.findViewById(R.id.tvNameFav);
        tvDescription = view.findViewById(R.id.tvDescription);
        btnPayFav = view.findViewById(R.id.btnPayFav);
        btnPayFav.setOnClickListener(this);
        tr.inicializatedFireBase(view.getContext());
        args = getArguments();
        if (args != null) {
             favor =  (Favor) args.getSerializable("fav");
             Glide.with(view.getContext()).load(favor.getImage()).into(iVDescripcion);
             tvNameFav.setText(favor.getName());
             tvDescription.setText(favor.getDescripcion());
        }
    }

    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista) {
            case R.id.btnPayFav: {
                tr.updateEstado(favor.getId(),"disponibilidad",favor.isDisponibilidad() ? false:true);
                break;
            }
        }
    }
}
