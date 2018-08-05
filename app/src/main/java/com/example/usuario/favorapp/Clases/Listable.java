package com.example.usuario.favorapp.Clases;

import android.support.v4.app.Fragment;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public interface Listable {
    Listable getInstance(DataSnapshot dataSnapshot);

    String[] print();

    Fragment getActivity();

    List<Listable> filter(List<Listable> data, String filter);

}
