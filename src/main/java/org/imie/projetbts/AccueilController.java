package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.imie.projetbts.Model.Book;
import org.imie.projetbts.Model.ManageBook;

import java.io.IOException;
import java.sql.SQLException;

public class AccueilController {
    public TableView<Book> tablLivre;
    public TableColumn<Book, Integer> colId;
    public TableColumn<Book, String> colTitre;
    public TableColumn<Book, Integer> colAuteur;
    public TableColumn<Book, String> colCollection;
    public TableColumn<Book, String> colCouverture;

    public TextField txtTitre;
    public TextField txtCouverture;

    public ChoiceBox choixAuteur;
    public ChoiceBox choixCollection;

    public Button btnModifCollection;
    public Button btnModifAuteur;
    public Button btnAddBook;

    public TextField chercherTitre;

    public void initialize() throws SQLException {
        ManageBook manageBook = new ManageBook();
        try {
            colId.setCellValueFactory(cellData -> cellData.getValue().Book_idProperty().asObject());
            colTitre.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
            colAuteur.setCellValueFactory(cellData -> cellData.getValue().getAuthor_id().asObject());
            colCollection.setCellValueFactory(cellData -> cellData.getValue().getCollectionName());
            colCouverture.setCellValueFactory(cellData -> cellData.getValue().getImage());
            tablLivre.setItems(manageBook.getBookList());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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
