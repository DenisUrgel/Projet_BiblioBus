package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.imie.projetbts.Model.Auteur;
import org.imie.projetbts.Model.Publisher;
import org.imie.projetbts.Model.manageAuteur;
import org.imie.projetbts.Model.managePublisher;

import java.sql.SQLException;

public class GererAuteurController {
    public TableView<Auteur> tablAuteur;
    public TableColumn<Auteur, Integer> colIdAuteur;
    public TableColumn<Auteur, String> colNomAuteur;
    public TextField txtNom;
    public Button btnAddAuteur;
    public Button btnDelAuteur;

    public void initialize() throws SQLException {
        manageAuteur manageAuteur = new manageAuteur();
        colIdAuteur.setCellValueFactory(cellData -> cellData.getValue().auteur_idProperty().asObject());
        colNomAuteur.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tablAuteur.setItems(manageAuteur.getAuteurList());
    }

    public void onAddAuteur(ActionEvent actionEvent) throws SQLException {
        manageAuteur mng = new manageAuteur();
        if (mng.getAuteurByName(txtNom.getText()) == null){
            Auteur auteur = new Auteur(txtNom.getText());
            mng.createAuteur(auteur);
        } else {
            System.out.println("Author already existing");
        }
        initialize();
    }

    public void onDelAuteur(ActionEvent actionEvent) throws SQLException {
        manageAuteur mng = new manageAuteur();
        mng.deleteAuteur(txtNom.getText());
        initialize();
    }

    public void onAuteurPressed(MouseEvent mouseEvent) {
        if(tablAuteur.getSelectionModel().getSelectedItems() != null){
            Auteur selectedPublisher = tablAuteur.getSelectionModel().getSelectedItem();
            this.txtNom.setText(selectedPublisher.getName());
        }
    }

    public void onAuteurNameChanged(ActionEvent actionEvent) throws SQLException {
        if(tablAuteur.getSelectionModel().getSelectedItems() != null){
            tablAuteur.getSelectionModel().getSelectedItem().setName(txtNom.getText());
            manageAuteur mng = new manageAuteur();
            mng.updateAuteur(txtNom.getText(),tablAuteur.getSelectionModel().getSelectedItem().getAuteur_id());
        }
        initialize();
    }
}
