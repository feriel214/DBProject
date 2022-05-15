package com.example.dbproject.models;

public class Formation {

    private Integer code_formation;
    private String intitule;
    private Integer domaine;
    private Integer formateur;
    private Integer mois;
    private Integer annee;
    private Integer nombre_jours;

    public Formation( String intitule, Integer domaine,Integer nombre_jours, Integer annee,Integer mois,Integer formateur) {
        this.intitule = intitule;
        this.domaine = domaine;
        this.nombre_jours = nombre_jours;
        this.annee = annee;
        this.mois = mois;
        this.formateur = formateur;
    }
    public Formation( Integer code_formation,String intitule, Integer domaine,Integer nombre_jours, Integer annee,Integer mois,Integer formateur) {
       this.code_formation=code_formation;
        this.intitule = intitule;
        this.domaine = domaine;
        this.nombre_jours = nombre_jours;
        this.annee = annee;
        this.mois = mois;
        this.formateur = formateur;
    }


    public Integer getCode_formation() {
        return code_formation;
    }


    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Integer getDomaine() {
        return domaine;
    }

    public void setDomaine(Integer domaine) {
        this.domaine = domaine;
    }

    public Integer getFormateur() {
        return formateur;
    }

    public void setFormateur(Integer formateur) {
        this.formateur = formateur;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getNombre_jours() {
        return nombre_jours;
    }

    public void setNombre_jours(Integer nombre_jours) {
        this.nombre_jours = nombre_jours;
    }
}
