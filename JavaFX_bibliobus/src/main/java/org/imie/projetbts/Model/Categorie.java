package org.imie.projetbts.Model;

public class Categorie {
    private String nom;
    private int nb;

    public Categorie (String nom,int nb) {
        this.nb = nb;
        this.nom = nom;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
