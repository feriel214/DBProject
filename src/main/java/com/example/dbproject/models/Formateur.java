package com.example.dbproject.models;

public class Formateur {
    //Attributes
    private Integer code_formateur;
    private String nom;
    private String prenom;
    private String email;
    private Integer tel;
    private Integer code_domaine;

    //Null constructor
    public Formateur( ) {}

    //constructor with parameters
    public Formateur(Integer code_formateur, String nom, String prenom, String email, Integer tel, Integer code_domaine) {
        this.code_formateur = code_formateur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.code_domaine = code_domaine;
    }

    public Formateur(String nom, String prenom, String email, Integer tel, Integer code_domaine) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.code_domaine = code_domaine;
    }

    //Getters and Setters
    public Integer getCode_formateur() {
        return code_formateur;
    }

    public void setCode_formateur(Integer code_formateur) {
        this.code_formateur = code_formateur;
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

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public Integer getCode_domaine() {
        return code_domaine;
    }

    public void setCode_domaine(Integer code_domaine) {
        this.code_domaine = code_domaine;
    }
}
