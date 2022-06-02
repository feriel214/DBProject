package com.example.dbproject.models;

import javafx.scene.control.Button;

import java.util.Date;

public class UserFormation {

    private Integer code_formation;
    private String intitule;
    private Integer domaine;
    private Integer formateur;
    private Date date_debut,date_fin;
    private Button actionBtn;


    public UserFormation(Integer code_formation, String intitule, Integer domaine, Integer formateur, Date date_debut, Date date_fin, Button actionBtn) {
        this.code_formation = code_formation;
        this.intitule = intitule;
        this.domaine = domaine;
        this.formateur = formateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.actionBtn = actionBtn;
    }
    public UserFormation(String intitule, Integer domaine, Integer formateur, Date date_debut, Date date_fin, Button actionBtn) {
        this.intitule = intitule;
        this.domaine = domaine;
        this.formateur = formateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.actionBtn = actionBtn;
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

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public Button getActionBtn() {
        return actionBtn;
    }

    public void setActionBtn(Button actionBtn) {
        this.actionBtn = actionBtn;
    }
}
