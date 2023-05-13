package com.example.myadapterplayer;

import android.widget.ImageView;

public class PlayerC  {

    private String nom;
    private String prenom;
    private String equipe;
    private String poste;
    private int img;

    //Constructeur
    public PlayerC(String _nom,String _prenom,String _equipe,String _poste,int _img){
        nom = _nom;
        prenom = _prenom;
        equipe = _equipe;
        poste=_poste;
        img=_img;
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

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
