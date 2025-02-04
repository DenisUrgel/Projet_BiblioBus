package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import org.imie.projetbts.Model.Publisher;
import org.imie.projetbts.Model.managePublisher;

import java.sql.SQLException;
import java.util.Objects;

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

    public void onAddEditeur(ActionEvent actionEvent) throws SQLException {
        managePublisher mng = new managePublisher();
        if (mng.getPublisherByName(txtNomEditeur.getText()) != null){

            Publisher publisher = new Publisher(txtNomEditeur.getText());
            mng.createPublisher(publisher);
        } else {
            System.out.println("Publisher already existing");
        }
        initialize();
    }

    public void onDelEditeur(ActionEvent actionEvent) throws SQLException {
        managePublisher mng = new managePublisher();
        mng.deletePublisher(txtNomEditeur.getText());
        initialize();
    }

    public void onEditeurPressed(MouseEvent mouseEvent) {
        if(tablPublisher.getSelectionModel().getSelectedItems() != null){
            Publisher selectedPublisher = tablPublisher.getSelectionModel().getSelectedItem();
            this.txtNomEditeur.setText(selectedPublisher.getName());
        }
    }

    public void onEditeurNameChanged(ActionEvent actionEvent) throws SQLException {
        if(tablPublisher.getSelectionModel().getSelectedItems() != null){
            tablPublisher.getSelectionModel().getSelectedItem().setName(txtNomEditeur.getText());
            managePublisher mng = new managePublisher();
            mng.updatePublisher(txtNomEditeur.getText(),tablPublisher.getSelectionModel().getSelectedItem().getPublisher_id());
        }
        initialize();
    }
}
