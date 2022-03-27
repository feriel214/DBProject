package com.example.dbproject.models;

public class Domaine {
    private Integer code_domaine;
    private String libelle;

    public Domaine(Integer code_domaine, String libelle) {
        this.code_domaine = code_domaine;
        this.libelle = libelle;
    }
    public Domaine( String libelle) {
        this.libelle = libelle;
    }
    public Domaine( ) {

    }
    public Integer getCode_domaine() {
        return code_domaine;
    }

    public void setCode_domaine(Integer code_domaine) {
        this.code_domaine = code_domaine;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
