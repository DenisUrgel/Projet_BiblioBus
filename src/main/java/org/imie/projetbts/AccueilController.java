package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {
    public TableColumn colId;
    public TableColumn colTitre;
    public TableColumn colAuteur;
    public TableColumn colCollection;
    public TableColumn colCouverture;

    public TextField txtTitre;
    public TextField txtCouverture;

    public ChoiceBox choixAuteur;
    public ChoiceBox choixCollection;

    public Button btnModifCollection;
    public Button btnModifAuteur;
    public Button btnAddBook;

    public TextField chercherTitre;
    public Button voirLivreEmprunt;

    public void onAddBook(ActionEvent actionEvent) {
        String titre = txtTitre.getText();
        String couverture = txtCouverture.getText();
    }

    public void onModifAuteur(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Gerer_Auteur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Auteur");
        stage.setScene(scene);
        stage.show();
    }

    public void onModifCollection(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Gerer_Collection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 334, 276);
        stage.setTitle("Collection");
        stage.setScene(scene);
        stage.show();
    }

    public void onSearchBook(ActionEvent actionEvent) {
    }
}
