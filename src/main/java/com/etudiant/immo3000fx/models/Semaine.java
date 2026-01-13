package com.etudiant.immo3000fx.models;

public class Semaine {

    private final int numSemaine;
    private final Saison saison;

    public Semaine(int numSemaine, Saison saison) {
        this.numSemaine = numSemaine;
        this.saison = saison;
    }

    public int getNumSemaine() {
        return numSemaine;
    }

    public Saison getSaison() {
        return saison;
    }

    @Override
    public String toString() {
        return "Semaine " + numSemaine + " (" + saison + " )";
    }

}
