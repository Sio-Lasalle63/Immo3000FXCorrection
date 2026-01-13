package com.etudiant.immo3000fx;

import com.etudiant.immo3000fx.models.Appartement;
import com.etudiant.immo3000fx.models.Datas;
import com.etudiant.immo3000fx.models.Location;
import com.etudiant.immo3000fx.models.Saison;
import com.etudiant.immo3000fx.models.Semaine;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        //Affecter la liste d'appartement à la listView des appartements
        lvAppartements.setItems(Datas.getInstance().getLesAppartements());
        //Affecter la liste des semaines au combox des semaines
        //Affecter la liste des saisons au combox des saisons
        //Définir un listener sur la listview des appartements afin de mettre à jour:
        //   la liste des locations effectuées
        //   le montant total des locations pour cet appartement
        //Définir un listener sur le combobox des saisons afin de mettre à jour
        //le combobox des semaine
    }

    @FXML
    private void clicAjouterLocation(ActionEvent event) {
        //Récupère l'appartement sélectionné afin de lui ajouter si possible la 
        //semaine de location
        //Doit informer l'utilisateur si la location est ajoutée ou pas
        //et mettre à jour la liste des locations
    }

}
