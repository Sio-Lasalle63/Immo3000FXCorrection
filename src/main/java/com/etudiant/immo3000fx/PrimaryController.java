package com.etudiant.immo3000fx;

import com.etudiant.immo3000fx.models.Appartement;
import com.etudiant.immo3000fx.models.Datas;
import com.etudiant.immo3000fx.models.Location;
import com.etudiant.immo3000fx.models.Saison;
import com.etudiant.immo3000fx.models.Semaine;
import com.sun.javafx.property.adapter.PropertyDescriptor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class PrimaryController implements Initializable {

    @FXML
    private ListView<Appartement> lvAppartements;
    @FXML
    private ListView<Location> lvLocations;
    @FXML
    private ComboBox<Semaine> cbSemaine;
    @FXML
    private Label labelMontantTotal;
    @FXML
    private ComboBox<Saison> cbSaison;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvAppartements.setItems(Datas.getInstance().getLesAppartements());
        cbSaison.setItems(Datas.getInstance().getLesSaisons());
        cbSaison.getSelectionModel().selectedItemProperty().addListener((ov, saisonOld, saisonNew) -> {
            cbSemaine.setItems(Datas.getInstance().getLesSemaines(saisonNew));
        });
        lvAppartements.getSelectionModel().selectedItemProperty().addListener((ov, appOld, appNew) -> {
            lvLocations.setItems(appNew.getSesLocations());
            labelMontantTotal.setText(appNew.montantTotal() + " €");
        });
        chargerDepuisFichier();

    }

    @FXML
    private void clicAjouterLocation(ActionEvent event) {
        //Récupère l'appartement sélectionné afin de lui ajouter si possible la 
        //semaine de location
        //Doit informer l'utilisateur si la location est ajoutée ou pas
        //et mettre à jour la liste des locations
        if (!lvAppartements.getSelectionModel().isEmpty() && !cbSemaine.getSelectionModel().isEmpty()) {
            //boolean ajoutOK = lvAppartements.getSelectionModel().getSelectedItem().ajouter(cbSemaine.getSelectionModel().getSelectedItem()) ;
            Appartement appSel = lvAppartements.getSelectionModel().getSelectedItem();
            Semaine semSel = cbSemaine.getSelectionModel().getSelectedItem();
            boolean ajoutOK = appSel.ajouter(semSel);
            if (!ajoutOK) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Réservation impossible sur cette semaine " + semSel);
                alert.showAndWait();
            } else {
                ecrireDansFichier(appSel, semSel);
            }
        }
    }

    private void ecrireDansFichier(Appartement appSel, Semaine semSel) {
        try {
            FileWriter fw = new FileWriter("locations.txt", true);
            fw.write(appSel.getNumero() + "\t" + semSel.getNumSemaine() + "\n");
            fw.close();
        } catch (IOException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    private void chargerDepuisFichier() {

        Scanner sc;
        try {
            sc = new Scanner(new File("locations.txt"));
            while (sc.hasNext()) {
                String ligne = sc.nextLine();
                String[] tab = ligne.split("\t");
                String numApp = tab[0];
                String numSem = tab[1];
                System.out.println(numApp + " " + numSem);
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            System.getLogger(PrimaryController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

    }

}
