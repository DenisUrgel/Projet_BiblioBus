package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.imie.projetbts.Model.Collection;
import org.imie.projetbts.Model.manageCollection;

import java.io.IOException;
import java.sql.SQLException;

public class GererCollectionController {
    public TableView<Collection> tablCollection;
    public TableColumn<Collection, Integer> colIdCollection;
    public TableColumn<Collection, String> colNomCollection;
    public TableColumn<Collection, Integer> colEditeurCollection;

    public Button btnAddCollection;
    public Button btnAddEditeur;

    public void initialize() throws SQLException {
        manageCollection manageCollection = new manageCollection();
        colIdCollection.setCellValueFactory(cellData -> cellData.getValue().collection_idProperty().asObject());
        colNomCollection.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colEditeurCollection.setCellValueFactory(cellData -> cellData.getValue().getPublicher_id().asObject());
        tablCollection.setItems(manageCollection.getCollectionList());
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
