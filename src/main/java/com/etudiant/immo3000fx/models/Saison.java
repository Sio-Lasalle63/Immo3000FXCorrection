package com.etudiant.immo3000fx.models;

public class Saison {

    private final String nom;
    private final float tarif;

    public Saison(String nom, float tarif) {
        this.nom = nom;
        this.tarif = tarif;
    }

    public float getTarif() {
        return this.tarif;
    }

    @Override
    public String toString() {
        return nom + " - " + tarif + "€";
    }

}
