package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class manageAuteur {
    public ObservableList<Auteur> getAuteurList() throws SQLException {
        ObservableList<Auteur> auteurList = FXCollections.observableArrayList();
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from author");
        while (rs.next()) {
            Auteur auteur = new Auteur(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            auteurList.add(auteur);
        }

        return auteurList;
    }

}
