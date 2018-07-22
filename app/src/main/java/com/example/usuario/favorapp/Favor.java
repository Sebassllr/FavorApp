package com.example.usuario.favorapp;

public class Favor {

    ////////////////////////////////////////////
    /////Variables//////////////////////////////
    ///////////////////////////////////////////

    private String name;

    private Boolean available;

    ////////////////////////////////////////////
    /////Constructor////////////////////////////
    ///////////////////////////////////////////

    public Favor(String name, Boolean available) {
        this.name = name;
        this.available = available;
    }


    ////////////////////////////////////////////
    /////Getters and Setters////////////////////
    ////////////////////////////////////////////

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
