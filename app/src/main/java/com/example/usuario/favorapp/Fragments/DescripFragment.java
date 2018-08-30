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
import com.example.usuario.favorapp.Clases.FirebaseDAO;
import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.Clases.Usuario;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DescripFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Bundle args;
    private ImageView iVDescripcion;
    private TextView tvNameFav,tvDescription;
    private Button btnPayFav;
    private Favor favor;
    private String name;
    private Calendar cal = Calendar.getInstance();
    private Transacciones tr = new Transacciones();
    private FirebaseDAO firebaseDAO;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_descripcion, container, false);
        firebaseDAO = new FirebaseDAO();
        getNodes(new Usuario());
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
<<<<<<< HEAD
                pedirFavor();
                changeF();
=======
                String idEntregable = tr.databaseReference.push().getKey();
                FirebaseUser user = tr.firebaseAuth.getCurrentUser();
                String fecha = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
                String mail = user.getEmail();
                String nameFavor = favor.getName();
                String ptsReto = favor.getPts();
                tr.pedirFavor(user.getUid(),fecha,favor.getId(),favor.getIdOwner(),mail,name,ptsReto,nameFavor,idEntregable);
                tr.updateEstado(favor.getId(),"disponibilidad",1);
>>>>>>> 0ea0cb5cb6281b1b8df5f353a569e09687cecb24
                break;
            }
        }
    }

    public void getNodes(final Usuario us){
        firebaseDAO.getDatabaseReference().child(us.getFirebaseNodeName())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        FirebaseUser user = firebaseDAO.getFirebaseUser();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Usuario object = snapshot.getValue(us.getClass());
                            if(object.getId().equals(user.getUid())){
                                name = object.getNombre();
                            }

                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
    private void pedirFavor(){
        String idEntregable = tr.databaseReference.push().getKey();
        FirebaseUser user = tr.firebaseAuth.getCurrentUser();
        String fecha = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
        String mail = user.getEmail();
        String nameFavor = favor.getName();
        String ptsReto = favor.getPts();
        tr.pedirFavor(user.getUid(),fecha,favor.getId(),favor.getIdOwner(),mail,name,ptsReto,nameFavor,idEntregable);
        tr.updateEstado(favor.getId(),"disponibilidad",1);
    }

    private void changeF(){
        NavigationActivity activity = (NavigationActivity) view.getContext();
        ListaFavoresFragment pf = new ListaFavoresFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragment, pf).addToBackStack(null).commit();
    }
}
