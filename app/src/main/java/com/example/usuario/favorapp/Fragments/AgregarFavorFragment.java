package com.example.usuario.favorapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.favorapp.Clases.Favor;
import com.example.usuario.favorapp.Clases.Transacciones;
import com.example.usuario.favorapp.NavigationActivity;
import com.example.usuario.favorapp.R;
import com.example.usuario.favorapp.Util.Util;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.app.Activity.RESULT_OK;
import static com.example.usuario.favorapp.Models.RAFavorGroup.favorCommit;
import static com.example.usuario.favorapp.Models.RAFavorProfileG.favorProfile;
import static com.example.usuario.favorapp.Models.RAFavorProfileG.isEdit;

public class AgregarFavorFragment extends Fragment implements View.OnClickListener {

    private View view;
    private EditText tvNameFavor, tvUrlImage, tvDescription;
    private Spinner sp_dynamic;
    private Button btnAddFavor, btnAddImage;
    private Transacciones tr = new Transacciones();
    private FirebaseAuth mAuth;
    private Uri descargarFoto = null;
    private Calendar cal = Calendar.getInstance();
    private Boolean isEditing = Boolean.FALSE;

    private static final int GALLERY_INTENT= 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_agregar_favor, container, false);
        tr.inicializatedFireBase(view.getContext());
        init();
        return view;
    }

    public void init(){
        tvNameFavor = view.findViewById(R.id.tvNameFavor);
        tvUrlImage = view.findViewById(R.id.tvUrlImage);
        tvDescription = view.findViewById(R.id.tvDescription);
        sp_dynamic = view.findViewById(R.id.sp_dynamic);
        btnAddImage = view.findViewById(R.id.btnAddImage);
        btnAddFavor = view.findViewById(R.id.btnAddFavor);
        btnAddFavor.setOnClickListener(this);
        btnAddImage.setOnClickListener(this);

        if(favorCommit != null || favorProfile != null){
            Favor favor;
            if(favorCommit != null){
                favor = favorCommit;
            }else{
                favor = favorProfile;
            }
            if(isEdit != null){
                isEditing = isEdit;
                isEdit = null;
                btnAddFavor.setText("Editar favor");
                ((TextView)view.findViewById(R.id.titleEdit)).setText("Editar favor");
            }else{
                tvNameFavor.setEnabled(Boolean.FALSE);
                tvDescription.setEnabled(Boolean.FALSE);
                tvUrlImage.setVisibility(View.GONE);
                sp_dynamic.setEnabled(Boolean.FALSE);
                btnAddImage.setVisibility(View.GONE);
            }

            tvNameFavor.setText(favor.getName());
            tvDescription.setText(favor.getDescripcion());
            tvUrlImage.setText(!favor.getImage().equals("")?"Contiene una imagen":"Sin imagen");
            sp_dynamic.setSelection(Integer.parseInt(favor.getPts())/100);


        }
    }


    @Override
    public void onClick(View view) {
        int vista = view.getId();
        switch (vista){
            case R.id.btnAddFavor: {
                if(isEditing){
                    editarFavor();
                    changeF();
                }else{
                    createFavor();
                    changeF();
                }
                break;
            }

            case R.id.btnAddImage:{
                Intent intentGallery = new Intent(Intent.ACTION_PICK);
                intentGallery.setType("image/*");
                startActivityForResult(intentGallery,GALLERY_INTENT);
                break;
            }
        }
    }

    private void createFavor(){
        if(!Util.emptyCampMSG(tvNameFavor,"Ingrese Nombre") && !Util.emptyCampMSG(tvUrlImage,"Agregue una imagen") && !Util.emptyCampMSG(tvDescription,"Agregue una descripcion")){
            String name = Util.getTxt(tvNameFavor);
            String foto = descargarFoto+"";
            String puntos = points()+"";
            String fecha = new SimpleDateFormat("yyyy/MM/dd").format(cal.getTime());
            String idEntregable = tr.databaseReference.push().getKey();
            String descripcion = Util.getTxt(tvDescription);
            tr.registrarFavor(name,foto,puntos,fecha,descripcion,0,tr.firebaseAuth.getCurrentUser().getUid(),idEntregable);
            favorCommit = null;
            favorProfile = null;
        }
    }

    private void editarFavor(){
        if(!Util.emptyCampMSG(tvNameFavor,"Ingrese Nombre") && !Util.emptyCampMSG(tvUrlImage,"Agregue una imagen") && !Util.emptyCampMSG(tvDescription,"Agregue una descripcion")){
            String name = Util.getTxt(tvNameFavor);
            Uri foto = descargarFoto;
            String descripcion = Util.getTxt(tvDescription);
            String points = points()+"";
            if(favorCommit != null || favorProfile != null) {
                Favor favor;
                if (favorCommit != null) {
                    favor = favorCommit;
                } else {
                    favor = favorProfile;
                }
                favor.setName(name);
                if(foto != null){
                    favor.setImage(foto+"");
                }
                favor.setDescripcion(descripcion);
                favor.setPts(points);
                tr.updateFavor(favor.getId(),favor);
                favorCommit = null;
                favorProfile = null;
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            final Uri uri = data.getData();
            StorageReference filePath = tr.mStorage.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    descargarFoto = taskSnapshot.getDownloadUrl();
                    tvUrlImage.setText(uri+"");
                }
            });
        }
    }

    private void changeF(){
        NavigationActivity activity = (NavigationActivity) view.getContext();
        PerfilFragment pf = new PerfilFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.FrFragment, pf).addToBackStack(null).commit();
    }

    private int points(){
        int v = sp_dynamic.getSelectedItemPosition();
        int points = 0;
        switch (v){
            case 0:{
                points = 100;
                break;
            }
            case 1:{
                points = 220;
                break;
            }
            case 2:{
                points = 350;
                break;
            }
        }
      return points;
    }
}

