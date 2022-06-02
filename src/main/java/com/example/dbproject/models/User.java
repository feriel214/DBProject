package com.example.dbproject.models;

import java.util.Date;

public class User {

    private int id_user;
    private String nom,prenom,login,password;
    private Date datenaiss;

    public User(String nom, String prenom, String login, String password, Date datenaiss) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.datenaiss = datenaiss;
    }

    public User(int id_user, String nom, String prenom, String login, String password, Date datenaiss) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.datenaiss = datenaiss;
    }

    public int getId_user() {
        return id_user;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatenaiss() {
        return datenaiss;
    }

    public void setDatenaiss(Date datenaiss) {
        this.datenaiss = datenaiss;
    }
}
