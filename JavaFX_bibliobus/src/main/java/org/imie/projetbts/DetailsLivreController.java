package org.imie.projetbts;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.imie.projetbts.Model.*;

import java.sql.SQLException;

public class DetailsLivreController {
    public TextField txtTitre;
    public ChoiceBox<String> choiceAuteur;
    public ChoiceBox<String> choiceCollection;
    public TextArea summaryArea;
    public TextArea urlImage;
    public ImageView image;

    public Book selectedBook;

    public void setBook(Book book) throws SQLException {
        manageBook mngB = new manageBook();
        manageAuteur mngA = new manageAuteur();
        manageCollection mngC = new manageCollection();

        this.selectedBook = book;

        this.txtTitre.setText(book.getTitle());
        this.summaryArea.setText(book.getSummary());
        this.urlImage.setText(book.getImagePath());
        urlImage.setWrapText(true);
        summaryArea.setWrapText(true);

        Image image;
        try {
            image = new Image(book.getImagePath(), true);
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur : URL invalide ou inaccessible. Utilisation d'une image par défaut.");
            image = new Image(getClass().getResource("/image/default.jpg").toExternalForm());
        }

        this.image.setImage(image);

        this.choiceAuteur.setValue(mngA.getAuteurById(book.getAuthor_id()).getName());
        ObservableList<Auteur> auteurlist = mngA.getAuteurList();
        for (Auteur auteur : auteurlist) {
            choiceAuteur.getItems().add(auteur.getName());
        }

        this.choiceCollection.setValue(mngC.getCollectionByName(book.getCollectionName()).getName());
        choiceCollection.getItems().clear();
        ObservableList<Collection> collectionlist = mngC.getCollectionList();
        for (Collection collection : collectionlist) {
            choiceCollection.getItems().add(collection.getName());
        }
    }

    public void onDelete(ActionEvent actionEvent) {
        manageBook mngB = new manageBook();
        Book bookToDelete = this.selectedBook;
        mngB.deleteBook(bookToDelete.getId());

        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }

    public void onModify(ActionEvent actionEvent) throws SQLException {
        manageBook mngB = new manageBook();
        manageAuteur mngA = new manageAuteur();
        Book bookToModify = this.selectedBook;

        String selectedAuteurItem = choiceAuteur.getSelectionModel().getSelectedItem();
        Auteur selectedAuteur = mngA.getAuteurByName(selectedAuteurItem);

        mngB.updateBook(this.txtTitre.getText(), this.summaryArea.getText(),this.urlImage.getText(), selectedAuteur.getAuteur_id(), bookToModify.getId());

        Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        currentStage.close();
    }
}
