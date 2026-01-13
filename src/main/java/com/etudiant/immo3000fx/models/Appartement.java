package com.etudiant.immo3000fx.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Appartement {

    private final int numero;
    private final String libelle;
    private final String adresse;
    private final ObservableList<Location> sesLocations = FXCollections.observableArrayList();

    public Appartement(int numero, String libelle, String adresse) {
        this.numero = numero;
        this.libelle = libelle;
        this.adresse = adresse;
    }

    public ObservableList<Location> getSesLocations() {
        return sesLocations;
    }

    public boolean ajouterSemaine(Semaine s) {
        throw new UnsupportedOperationException("A faire !!! ");
    }
    // Permet d'ajouter une nouvelle semaine de location

    public boolean dejaLoue(int n) {
        throw new UnsupportedOperationException("A faire !!! ");
    }
    // Indique si la semaine dont le numéro est passé en paramètre 
    // fait partie des semaines pendant lesquelles l’appartement a été loué.

    public float montantTotal() {
        throw new UnsupportedOperationException("A faire !!! ");
    }
    // Calcule et retourne ce qu’a rapporté l’appartement,
    // c’est-à-dire le montant total correspondant à toutes les semaines 
    // pour lesquelles l’appartement a été loué.

    public int nbSemainesSaison(Saison s) {
        throw new UnsupportedOperationException("A faire !!! ");
    }
    // Retourne le nombre de semaines pendant lesquelles 
    // l’appartement a été loué au cours de la saison passée en paramètre.

    @Override
    public String toString() {
        return  this.libelle +" / " + this.adresse ;
    }
}
