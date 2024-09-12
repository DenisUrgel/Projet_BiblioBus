package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.imie.projetbts.Model.Auteur;
import org.imie.projetbts.Model.manageAuteur;

import java.sql.SQLException;

public class GererAuteurController {
    public TableView<Auteur> tablAuteur;
    public TableColumn<Auteur, Integer> colIdAuteur;
    public TableColumn<Auteur, String> colNomAuteur;
    public TextField txtNom;
    public Button btnAddAuteur;

    public void initialize() throws SQLException {
        manageAuteur manageAuteur = new manageAuteur();
        colIdAuteur.setCellValueFactory(cellData -> cellData.getValue().auteur_idProperty().asObject());
        colNomAuteur.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tablAuteur.setItems(manageAuteur.getAuteurList());
    }

    public void onAddAuteur(ActionEvent actionEvent) {

    }
}
