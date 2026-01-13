package com.etudiant.immo3000fx.models;

public class Location {

    private final Appartement appart;
    private final Semaine semaine;

    public Location(Appartement appart, Semaine semaine) {
        this.appart = appart;
        this.semaine = semaine;
    }

    public Appartement getAppart() {
        return appart;
    }

    public Semaine getSemaine() {
        return semaine;
    }

    @Override
    public String toString() {
        return semaine.toString();
    }

}
