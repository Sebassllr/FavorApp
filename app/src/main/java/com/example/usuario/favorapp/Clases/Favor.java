package com.example.usuario.favorapp.Clases;

public class Favor implements FirebaseItems{

    //////////////////
    //Variables
    /////////////////

    private String name;
    private String idOwner;
    private String image;
    private String pts;
    private String fecha;
    private String descripcion;
    private boolean disponibilidad;
    //////////////////
    //Constructor
    /////////////////

    public Favor(){}

    public Favor(String name, String image, String pts, String fecha,String descripcion, boolean disponibilidad, String idOwner) {
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

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Favores";
    }
}
