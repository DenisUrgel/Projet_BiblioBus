package org.imie.projetbts;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
    public Button btnDelCollection;
    public ChoiceBox<String> menuEditeur;
    public TextField txtCollection;

    public void onAddEditeur(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Editeur.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 392, 264);
        stage.setTitle("Editeur");
        stage.setScene(scene);
        stage.show();
    }


    public void initialize() throws SQLException {
        manageCollection manageCollection = new manageCollection();
        managePublisher managePublisher = new managePublisher();
        colIdCollection.setCellValueFactory(cellData -> cellData.getValue().collection_idProperty().asObject());
        colNomCollection.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colEditeurCollection.setCellValueFactory(cellData -> cellData.getValue().publicherNameProperty());
        tablCollection.setItems(manageCollection.getCollectionList());

        menuEditeur.getItems().clear();
        ObservableList<Publisher> publisherlist = managePublisher.getPublisherList();
        for (Publisher publisher : publisherlist) {
            menuEditeur.getItems().add(publisher.getName());
        }
    }

    public void onAddCollection(ActionEvent actionEvent) throws SQLException {
        manageCollection mngC = new manageCollection();
        if (mngC.getCollectionByName(txtCollection.getText()) == null){
            managePublisher mngP = new managePublisher();
            String selectedItem = menuEditeur.getSelectionModel().getSelectedItem();
            Publisher selectedPublisher = mngP.getPublisherByName(selectedItem);

            Collection collection = new Collection(txtCollection.getText(), selectedPublisher.getPublisher_id(), selectedPublisher.getName());
            mngC.createCollection(collection);
        } else {
            System.out.println("already existing Collection");
        }
        initialize();
    }

    public void onDelCollection(ActionEvent actionEvent) throws SQLException {
        manageCollection mng = new manageCollection();
        mng.deleteCollection(txtCollection.getText());
        initialize();
    }

    public void onCollectionPressed(MouseEvent mouseEvent) {
        if(tablCollection.getSelectionModel().getSelectedItems() != null){
            Collection selectedCollection = tablCollection.getSelectionModel().getSelectedItem();
            this.txtCollection.setText(selectedCollection.getName());
        }
    }

    public void onCollectionNameChanged(ActionEvent actionEvent) throws SQLException {
        if(tablCollection.getSelectionModel().getSelectedItems() != null){
            manageCollection mng = new manageCollection();
            managePublisher mngP = new managePublisher();

            String selectedItem = menuEditeur.getSelectionModel().getSelectedItem();
            Publisher selectedPublisher = mngP.getPublisherByName(selectedItem);

            tablCollection.getSelectionModel().getSelectedItem().setName(txtCollection.getText());
            mng.updateCollection(txtCollection.getText(), selectedPublisher.getPublisher_id(), tablCollection.getSelectionModel().getSelectedItem().getCollection_id());
        }
        initialize();
    }
}