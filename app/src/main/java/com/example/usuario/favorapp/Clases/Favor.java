package com.example.usuario.favorapp.Clases;

public class Favor {


    //////////////////////////////////////
    //Variables///////////////////////////
    //////////////////////////////////////

    String name;

    Boolean available;

    //////////////////////////////////////
    //Constructor/////////////////////////
    //////////////////////////////////////

    public Favor(String name, Boolean available) {
        this.name = name;
        this.available = available;
    }

    //////////////////////////////////////
    //Getters and Setters/////////////////
    //////////////////////////////////////

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
