package org.imie.projetbts;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.imie.projetbts.Model.Collection;
import org.imie.projetbts.Model.Publisher;
import org.imie.projetbts.Model.manageCollection;
import org.imie.projetbts.Model.managePublisher;

import java.io.IOException;
import java.sql.SQLException;

public class GererCollectionController {
    public TableView<Collection> tablCollection;
    public TableColumn<Collection, Integer> colIdCollection;
    public TableColumn<Collection, String> colNomCollection;
    public TableColumn<Collection, String> colEditeurCollection;

    public Button btnAddCollection;
    public Button btnAddEditeur;
    public ChoiceBox<String> menuEditeur;

    public void initialize() throws SQLException {
        manageCollection manageCollection = new manageCollection();
        managePublisher managePublisher = new managePublisher();
        colIdCollection.setCellValueFactory(cellData -> cellData.getValue().collection_idProperty().asObject());
        colNomCollection.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colEditeurCollection.setCellValueFactory(cellData -> cellData.getValue().publicherNameProperty());
        tablCollection.setItems(manageCollection.getCollectionList());

        ObservableList<Publisher> publisherlist = managePublisher.getPublisherList();
        for (Publisher publisher : publisherlist) {
            menuEditeur.getItems().add(publisher.getName());
        }


    }

    public void onAddCollection(ActionEvent actionEvent) {
    }

    public void onAddEditeur(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Editeur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 392, 264);
        stage.setTitle("Editeur");
        stage.setScene(scene);
        stage.show();
    }
}
