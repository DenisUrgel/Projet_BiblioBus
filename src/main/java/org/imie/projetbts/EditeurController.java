package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.imie.projetbts.Model.Publisher;
import org.imie.projetbts.Model.managePublisher;

import java.sql.SQLException;

public class EditeurController {
    public TableView<Publisher> tablPublisher;
    public TableColumn<Publisher, Integer> colIdEditeur;
    public TableColumn<Publisher, String> colNomEditeur;
    public TextField txtNomEditeur;
    public Button btnAddEditeur;
    public Button btnDelEditeur;

    public void initialize() throws SQLException {
        managePublisher managePublisher = new managePublisher();
        colIdEditeur.setCellValueFactory(cellData -> cellData.getValue().publisher_idProperty().asObject());
        colNomEditeur.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tablPublisher.setItems(managePublisher.getPublisherList());
    }

    public void onAddEditeur(ActionEvent actionEvent) {
        Publisher publisher = new Publisher(txtNomEditeur.getText());
        managePublisher mng = new managePublisher();
        mng.createPublisher(publisher);
    }

    public void onDelEditeur(ActionEvent actionEvent) {
        managePublisher mng = new managePublisher();
        mng.deletePublisher(txtNomEditeur.getText());
    }
}
