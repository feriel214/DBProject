package com.example.dbproject.models;

import java.time.LocalDate;
import java.util.Date;

public class Participant {
    private Integer matricule_participant;
    private String  nom;
    private String  prenom;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private Integer id_profile;
    private LocalDate date_naissance;

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

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Participant(Integer matricule_participant, String nom, String prenom, Integer id_profile, LocalDate date_naissance,String password) {
        this.matricule_participant = matricule_participant;
        this.nom = nom;
        this.prenom = prenom;
        this.id_profile = id_profile;
        this.date_naissance = date_naissance;
        this.password=password;
    }
    public Participant(Integer matricule_participant, String nom, String prenom, Integer id_profile, LocalDate date_naissance) {
        this.matricule_participant = matricule_participant;
        this.nom = nom;
        this.prenom = prenom;
        this.id_profile = id_profile;
        this.date_naissance = date_naissance;

    }
}
