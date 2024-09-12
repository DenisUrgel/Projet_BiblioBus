package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;

import java.io.IOException;

public class GererCollectionController {
    public TableColumn colIdCollection;
    public TableColumn colNomCollection;
    public TableColumn colEditeurCollection;
    public Button btnAddCollection;
    public Button btnAddEditeur;

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
