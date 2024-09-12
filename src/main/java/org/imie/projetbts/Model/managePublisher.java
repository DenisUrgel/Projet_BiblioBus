package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class managePublisher {
    public ObservableList<Publisher> getPublisherList() throws SQLException {
        ObservableList<Publisher> publisherList = FXCollections.observableArrayList();
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from publisher");
        while (rs.next()) {

            Publisher publisher = new Publisher(
                    rs.getInt("id"),
                    rs.getString("name")

            );
            publisherList.add(publisher);
        }

        return publisherList;
    }
}
