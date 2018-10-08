package com.example.usuario.favorapp.Clases;

public class Solicitud implements FirebaseItems {

    /////////////////////////
    //Variables
    ////////////////////////

    private String idSolicitud;
    private String idSolicitante;
    private String idFavor;
    private String idOwner;
    private String mailSolicitante;
    private String nameSolicitante;
    private String ptsFavor;
    private String nameFavor;
    private String fecha;
    private int estadoS;

    /////////////////////////
    //Constructores
    ////////////////////////

    public Solicitud(){}

    public Solicitud(String idSolicitud, String idSolicitante, String idFavor, String idOwner, String mailSolicitante, String nameSolicitante, String ptsReto, String nameReto, String fecha, int estadoS) {
        this.idSolicitud = idSolicitud;
        this.idSolicitante = idSolicitante;
        this.idFavor = idFavor;
        this.idOwner = idOwner;
        this.mailSolicitante = mailSolicitante;
        this.nameSolicitante = nameSolicitante;
        this.ptsFavor = ptsReto;
        this.nameFavor = nameReto;
        this.fecha = fecha;
        this.estadoS = estadoS;
    }

    /////////////////////////
    //Getter and Setters
    ////////////////////////


    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getIdFavor() {
        return idFavor;
    }

    public void setIdFavor(String idFavor) {
        this.idFavor = idFavor;
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getMailSolicitante() {
        return mailSolicitante;
    }

    public void setMailSolicitante(String mailSolicitante) {
        this.mailSolicitante = mailSolicitante;
    }

    public String getNameSolicitante() {
        return nameSolicitante;
    }

    public void setNameSolicitante(String nameSolicitante) {
        this.nameSolicitante = nameSolicitante;
    }

    public String getPtsFavor() {
        return ptsFavor;
    }

    public void setPtsFavor(String ptsFavor) {
        this.ptsFavor = ptsFavor;
    }

    public String getNameFavor() {
        return nameFavor;
    }

    public void setNameFavor(String nameFavor) {
        this.nameFavor = nameFavor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getEstadoS() {
        return estadoS;
    }

    public void setEstadoS(int estadoS) {
        this.estadoS = estadoS;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Solicitudes";
    }
}
