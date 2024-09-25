package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class manageCollection {
    public ObservableList<Collection> getCollectionList() throws SQLException {
        ObservableList<Collection> collectionList = FXCollections.observableArrayList();
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from collection");
        String sql = "SELECT * FROM publisher WHERE id = ?";
        managePublisher publisher = new managePublisher();
        PreparedStatement statement = conn.prepareStatement(sql);
        while (rs.next()) {
            statement.setInt(1, rs.getInt("id"));
            ResultSet result = statement.executeQuery();
            result.next();
            Collection collection = new Collection(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("publisher_id"),
                    publisher.getPublisher(rs.getInt("publisher_id")).getName()
            );
            collectionList.add(collection);
        }

        return collectionList;
    }



    public Collection getCollection(int id){
        Collection collection =null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM collection WHERE id = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            managePublisher publisher = new managePublisher();
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                collection = new Collection(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getInt("publisher_id"),
                        publisher.getPublisher(result.getInt("publisher_id")).getName()

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }
}
