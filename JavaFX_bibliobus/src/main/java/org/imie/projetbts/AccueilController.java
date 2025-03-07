package org.imie.projetbts;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.imie.projetbts.Model.*;

import java.io.IOException;
import java.sql.SQLException;

public class AccueilController {
    public TableView<Book> tablLivre;
    public TableColumn<Book, Integer> colId;
    public TableColumn<Book, String> colTitre;
    public TableColumn<Book, String> colCategorie;
    public TableColumn<Book, String> colAuteur;
    public TableColumn<Book, String> colCollection;
    public TableColumn<Book, ImageView> colCouverture;
    public TableColumn<StringProperty, String> colDetails;

    public TextField txtTitre;
    public TextField txtCouverture;
    public TextArea summaryTxt;

    public ChoiceBox<String> choixAuteur;
    public ChoiceBox<String> choixCollection;

    public Button btnModifCollection;
    public Button btnModifAuteur;
    public Button btnAddBook;

    public TextField searchTitle;
    public Button btnDelBook;
    public Button btnRefresh;
    public TextField txtCategorie;
    public Button btnCatégorie;

    public void onModifAuteur(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Gerer_Auteur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 424, 298);
        stage.setTitle("Auteur");
        stage.setScene(scene);
        stage.show();
    }

    public void onModifCollection(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Gerer_Collection.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 586, 408);
        stage.setTitle("Collection");
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
        manageBook manageBook = new manageBook();
        manageCollection manageCollection = new manageCollection();
        manageAuteur manageAuteur = new manageAuteur();
        SimpleStringProperty details = new SimpleStringProperty("Cliquer ici pour avoir les détails");

        try {
            colId.setCellValueFactory(cellData -> cellData.getValue().Book_idProperty().asObject());
            colTitre.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
            colCategorie.setCellValueFactory(cellData -> cellData.getValue().categorieProperty());
            colDetails.setCellValueFactory(cellData-> details);
            colAuteur.setCellValueFactory(cellData -> {
                Auteur auteur = manageAuteur.getAuteurById(cellData.getValue().getAuthor_id());
                return auteur.nameProperty();
            });
            colCollection.setCellValueFactory(cellData -> cellData.getValue().CollectionNameProperty());
            colCouverture.setCellValueFactory(cellData -> {
                String imagePath = cellData.getValue().getImagePath();

                Image image;
                try {
                    image = new Image(imagePath, true);
                } catch (IllegalArgumentException e) {
                    System.err.println("Erreur : URL invalide ou inaccessible. Utilisation d'une image par défaut.");
                    image = new Image(getClass().getResource("/image/default.jpg").toExternalForm());
                }


                ImageView imageView = new ImageView(image);

                imageView.setFitHeight(50);
                imageView.setFitWidth(50);

                return new SimpleObjectProperty<>(imageView);
            });

            configureTextWrapping(colTitre);

            tablLivre.setItems(manageBook.getBookList());

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

    private void configureTextWrapping(TableColumn<Book, String> column) {
        column.setCellFactory(param -> new TableCell<>() {
            private final Text text;

            {
                text = new Text();
                text.wrappingWidthProperty().bind(param.widthProperty().subtract(10)); // Ajuste la largeur d'enroulement
                setGraphic(text);
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    text.setText(null);
                } else {
                    text.setText(item); // Définit le texte pour l'enroulement
                }
            }
        });
    }

    public void onAddBook(ActionEvent actionEvent) throws SQLException {
        String titre = txtTitre.getText();
        String couverture = txtCouverture.getText();
        String resume = summaryTxt.getText();
        String categorie = txtCategorie.getText();

        manageCollection mngC = new manageCollection();
        String selectedCollectionItem = choixCollection.getSelectionModel().getSelectedItem();
        Collection selectedCollection = mngC.getCollectionByName(selectedCollectionItem);

        manageAuteur mngA = new manageAuteur();
        String selectedAuteurItem = choixAuteur.getSelectionModel().getSelectedItem();
        Auteur selectedAuteur = mngA.getAuteurByName(selectedAuteurItem);

        manageBook mngB = new manageBook();
        Book book = new Book(titre, categorie, couverture, resume, selectedAuteur.getAuteur_id(), selectedCollection.getName());
        book.setCopiesAvailable(1);
        mngB.createBook(book);
        mngB.createBookCollection(book, selectedCollection);
        initialize();
    }

    public void onBookPressed(MouseEvent mouseEvent) {
        Book selectedBook = tablLivre.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("details_livre.fxml"));
            Parent root = fxmlLoader.load();
            DetailsLivreController controller = fxmlLoader.getController();

            controller.setBook(selectedBook);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Détails du livre");
            stage.setScene(scene);
            stage.show();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void onSearchBook(ActionEvent actionEvent) throws SQLException {
        manageBook mng = new manageBook();
        String titre = searchTitle.getText();
        if (titre == "") {
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

    public void onRefresh(ActionEvent actionEvent) {
        try {
            initialize();
            System.out.println("successfuly refreshed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onGererCategorie(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Categorie.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 424, 298);
        stage.setTitle("Categorie");
        stage.setScene(scene);
        stage.show();
    }
}
