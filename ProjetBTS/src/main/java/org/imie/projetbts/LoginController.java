package org.imie.projetbts;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.internal.access.JavaIOFileDescriptorAccess;

import java.io.IOException;

public class LoginController {
    public TextField passwordTxt;
    public TextField idTxt;
    public Button loginBtn;

    public void onLoginBtn(ActionEvent actionEvent) throws IOException {
        if (passwordTxt.getText().isEmpty() && idTxt.getText().isEmpty()){
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(BiblioBusApplication.class.getResource("Accueil.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1087, 636);
            stage.setTitle("Accueil");
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }
}