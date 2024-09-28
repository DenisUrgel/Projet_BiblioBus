package org.imie.projetbts;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.imie.projetbts.Model.*;

import java.io.IOException;
import java.sql.SQLException;

public class AccueilController {
    public TableView<Book> tablLivre;
    public TableColumn<Book, Integer> colId;
    public TableColumn<Book, String> colTitre;
    public TableColumn<Book, Integer> colAuteur;
    public TableColumn<Book, String> colCollection;
    public TableColumn<Book, ImageView> colCouverture;

    public TextField txtTitre;
    public TextField txtCouverture;

    public ChoiceBox<String> choixAuteur;
    public ChoiceBox<String> choixCollection;

    public Button btnModifCollection;
    public Button btnModifAuteur;
    public Button btnAddBook;

    public TextField searchTitle;
    public Button btnDelBook;

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
        Scene scene = new Scene(fxmlLoader.load(), 375, 338);
        stage.setTitle("Collection");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
        manageBook manageBook = new manageBook();
        try {
            colId.setCellValueFactory(cellData -> cellData.getValue().Book_idProperty().asObject());
            colTitre.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
            colAuteur.setCellValueFactory(cellData -> cellData.getValue().author_idIntproperty().asObject());
            colCollection.setCellValueFactory(cellData -> cellData.getValue().getCollectionName());
            colCouverture.setCellValueFactory(cellData -> {
                String imagePath = cellData.getValue().getImagePath();
                    Image image = new Image(imagePath);

                ImageView imageView = new ImageView(image);


                imageView.setFitHeight(300);
                imageView.setFitWidth(300);

                return new SimpleObjectProperty<>(imageView);
            });
            tablLivre.setItems(manageBook.getBookList());

            manageCollection manageCollection = new manageCollection();
            manageAuteur manageAuteur = new manageAuteur();

            choixCollection.getItems().clear();
            ObservableList<Collection> collectionlist = manageCollection.getCollectionList();
            for (Collection collection : collectionlist) {
                choixCollection.getItems().add(collection.getName());
            }

            choixAuteur.getItems().clear();
            ObservableList<Auteur> auteurlist = manageAuteur.getAuteurList();
            for (Auteur auteur : auteurlist) {
                choixAuteur.getItems().add(auteur.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onAddBook(ActionEvent actionEvent) throws SQLException {
        String titre = txtTitre.getText();
        String couverture = txtCouverture.getText();

        manageCollection mngC = new manageCollection();
        String selectedCollectionItem = choixCollection.getSelectionModel().getSelectedItem();
        Collection selectedCollection = mngC.getCollectionByName(selectedCollectionItem);

        manageAuteur mngA = new manageAuteur();
        String selectedAuteurItem = choixAuteur.getSelectionModel().getSelectedItem();
        Auteur selectedAuteur = mngA.getAuteurByName(selectedAuteurItem);

        manageBook mngB = new manageBook();
        Book book = new Book(titre, couverture, selectedAuteur.getAuteur_id(), selectedCollection.getName());
        book.setCopiesAvailable(1);
        mngB.createBook(book);
        mngB.createBookCollection(book, selectedCollection);
        initialize();
    }

    public void onDelBook(ActionEvent actionEvent) throws SQLException {
        manageBook mng = new manageBook();
        Book selectedBook = tablLivre.getSelectionModel().getSelectedItem();
        mng.deleteBook(selectedBook.getId());
        initialize();
    }

    public void onBookPressed(MouseEvent mouseEvent) {
        if(tablLivre.getSelectionModel().getSelectedItems() != null){
            Book selectedBook = tablLivre.getSelectionModel().getSelectedItem();
            this.txtTitre.setText(selectedBook.getTitle());
            this.txtCouverture.setText(selectedBook.getImagePath());
        }
    }

    public void onSearchBook(ActionEvent actionEvent) throws SQLException {
        manageBook mng = new manageBook();
        String titre = searchTitle.getText();
        if (titre == ""){
            initialize();
        } else {
        Book book = mng.getBookByName(titre);
        if (book != null) {
            ObservableList<Book> bookList = FXCollections.observableArrayList(book);
            tablLivre.setItems(bookList);
        } else {
            System.out.println("Book not found");
            tablLivre.setItems(FXCollections.observableArrayList());
        }
        }
    }
}
