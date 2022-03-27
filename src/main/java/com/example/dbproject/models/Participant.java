package com.example.dbproject.models;

import java.util.Date;

public class Participant {
    private Integer matricule_participant;
    private String  nom;
    private String  prenom;
    private Integer id_profile;
    private Date date_naissance;

    public Integer getMatricule_participant() {
        return matricule_participant;
    }

    public void setMatricule_participant(Integer matricule_participant) {
        this.matricule_participant = matricule_participant;
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

    public Integer getId_profile() {
        return id_profile;
    }

    public void setId_profile(Integer id_profile) {
        this.id_profile = id_profile;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Participant(Integer matricule_participant, String nom, String prenom, Integer id_profile, Date date_naissance) {
        this.matricule_participant = matricule_participant;
        this.nom = nom;
        this.prenom = prenom;
        this.id_profile = id_profile;
        this.date_naissance = date_naissance;
    }
}
