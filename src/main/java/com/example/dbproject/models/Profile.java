package com.example.dbproject.models;

public class Profile
{


    private Integer code_profile;
    private String libelle;

    public Profile(String libelle) {
        this.libelle = libelle;
    }

    public Profile(String libelle, Integer code_profile) {
        this.libelle = libelle;
        this.code_profile = code_profile;
    }

    public Profile() {

    }


    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getCode_profile() {
        return code_profile;
    }




}
