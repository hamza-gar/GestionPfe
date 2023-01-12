package com.example.gestionpfe.Requests;

public class EtudiantRequest {
    private Long apogee;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
    private String password;

    public Long getApogee() {
        return apogee;
    }

    public void setApogee(Long apogee) {
        this.apogee = apogee;
    }

    public String getCne() {
        return cne;
    }

    public void setCne(String cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
