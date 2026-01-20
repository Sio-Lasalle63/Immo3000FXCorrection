package com.etudiant.immo3000fx.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Datas {

    private final ObservableList<Appartement> lesAppartements;
    private final ObservableList<Semaine> lesSemaines;
    private final ObservableList<Saison> lesSaisons;
    private static Datas Instance;

    private Datas() {
        lesAppartements = FXCollections.observableArrayList();
        lesSaisons = FXCollections.observableArrayList();
        lesSemaines = FXCollections.observableArrayList();
        loadMariaDB();
    }

    public static Datas getInstance() {
        if (Instance == null) {
            Instance = new Datas();
        }
        return Instance;
    }

    public ObservableList<Appartement> getLesAppartements() {
        return lesAppartements;
    }

    public ObservableList<Semaine> getLesSemaines() {
        return lesSemaines;
    }

    public ObservableList<Saison> getLesSaisons() {
        return lesSaisons;
    }

    public ObservableList<Semaine> getLesSemaines(Saison s) {
        ObservableList<Semaine> laListeDeSemaines = FXCollections.observableArrayList() ;
        for( Semaine uneSemaine : this.lesSemaines ){
            if( uneSemaine.getSaison() == s ){
                laListeDeSemaines.add(uneSemaine) ;
            }
        }
        return laListeDeSemaines;
    }

    private void load() {
        lesAppartements.add(new Appartement(1, "Appt 21 Les lilas fleuris", "15 avenue Charras 63000 Clermont-Ferrand"));
        lesAppartements.add(new Appartement(2, "Appt 22 Les lilas fleuris", "15 avenue Charras 63000 Clermont-Ferrand"));
        lesAppartements.add(new Appartement(3, "Appt 3 Le peuplier coupé", "14 avenue Victor Hugo 63000 Clermont-Ferrand"));
        lesAppartements.add(new Appartement(4, "Appt 17 Les framboises mûres", "12 rue Godefroy de Bouillon 63000 Clermont-Ferrand"));
        lesSaisons.add(new Saison("Basse Saison", 300));
        lesSaisons.add(new Saison("Moyenne Saison", 500));
        lesSaisons.add(new Saison("Haute Saison", 1200));
        for (int i = 1; i <= 20; i++) {
            lesSemaines.add(new Semaine(i, lesSaisons.get(0)));
        }
        for (int i = 21; i <= 45; i++) {
            lesSemaines.add(new Semaine(i, lesSaisons.get(1)));
        }
        for (int i = 46; i <= 52; i++) {
            lesSemaines.add(new Semaine(i, lesSaisons.get(2)));
        }
    }

    private void loadMariaDB() {
        try (Connection conn = DriverManager.getConnection(Config.jdbc, Config.login, Config.password)) {
            chargerSaisons(conn);
            chargerSemaines(conn);
            chargerAppartements(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void chargerSaisons(Connection conn) throws SQLException {
        String sql = "SELECT id, nom, tarif FROM Saison";

        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            lesSaisons.clear();

            while (rs.next()) {
                lesSaisons.add(new Saison(
                        rs.getString("nom"),
                        rs.getFloat("tarif")
                ));
            }
        }
    }

    private void chargerSemaines(Connection conn) throws SQLException {
        String sql = "SELECT numSemaine, idSaison FROM Semaine";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            lesSemaines.clear();
            while (rs.next()) {
                // Recherche de la saison correspondante
                int idSaison = rs.getInt("idSaison");

                Saison saison = lesSaisons.stream()
                        .skip(idSaison - 1)
                        .findFirst()
                        .orElse(null);
                Semaine s = new Semaine(
                        rs.getInt("numSemaine"), saison
                );
                lesSemaines.add(s);
            }
        }
    }

    private void chargerAppartements(Connection conn) throws SQLException {
        String sql = "SELECT numero, libelle, adresse FROM Appartement";
        try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            lesAppartements.clear();
            while (rs.next()) {

                Appartement a = new Appartement(
                        rs.getInt("numero"),
                        rs.getString("libelle"),
                        rs.getString("adresse")
                );
                lesAppartements.add(a);
            }
        }
    }


}
