package com.example.usuario.favorapp.Clases;

public class Favor {

    //////////////////
    //Variables
    /////////////////

    private String name;
    private int image;
    private String pts;
    private String fecha;
    private String ubicacion;

    //////////////////
    //Constructor
    /////////////////

    public Favor(){}

    public Favor(String name, int image, String pts, String fecha, String ubicacion) {
        this.name = name;
        this.image = image;
        this.pts = pts;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    //////////////////////////
    //Getter ans Setter
    ///////////////////////////



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
