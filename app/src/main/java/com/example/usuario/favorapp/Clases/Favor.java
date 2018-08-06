package com.example.usuario.favorapp.Clases;

import android.support.v4.app.Fragment;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

public class Favor extends NodoFirebase implements FirebaseItems {

    //////////////////
    //Variables
    /////////////////

    private String name;
    private String idOwner;
    private String image;
    private String pts;
    private String fecha;
    private String descripcion;
    private String id;
    private int disponibilidad;
    //////////////////
    //Constructor
    /////////////////

    public Favor(){}

    public Favor(String id, String name, String image, String pts, String fecha, String descripcion, int disponibilidad, String idOwner) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.pts = pts;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.disponibilidad = disponibilidad;
        this.idOwner = idOwner;
    }


    //////////////////////////
    //Getter ans Setter
    ///////////////////////////


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Favores";
    }

    @Override
    public void delete(String[] fatherKey) {

    }

    @Override
    public Listable getInstance(DataSnapshot dataSnapshot) {
        return null;
    }

    @Override
    public String[] print() {
        return new String[0];
    }

    @Override
    public Fragment getActivity() {
        return null;
    }

    @Override
    public List<Listable> filter(List<Listable> data, String filter) {
        return null;
    }
}
