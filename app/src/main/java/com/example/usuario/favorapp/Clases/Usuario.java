package com.example.usuario.favorapp.Clases;

public class Usuario implements FirebaseItems{

    private String nombre;
    private String id;
    private String puntos;
    private String mail;

    public Usuario (){}

    public Usuario(String nombre, String id, String puntos, String mail) {
        this.nombre = nombre;
        this.id = id;
        this.puntos = puntos;
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Users";
    }
}
