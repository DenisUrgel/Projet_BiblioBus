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

                    rs.getString("name"),
                    rs.getInt("publisher_id"),
                    publisher.getPublisherById(rs.getInt("publisher_id")).getName()
            );
            collection.setCollection_id(rs.getInt("id"));
            collectionList.add(collection);
        }

        return collectionList;
    }

    public Collection getCollectionById(int id){
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

                        result.getString("name"),
                        result.getInt("publisher_id"),
                        publisher.getPublisherById(result.getInt("publisher_id")).getName()

                );
                collection.setCollection_id(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }
    public Collection getCollectionByName(String name){
        Collection collection =null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM collection WHERE name = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            managePublisher publisher = new managePublisher();
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                collection = new Collection(

                        result.getString("name"),
                        result.getInt("publisher_id"),
                        publisher.getPublisherById(result.getInt("publisher_id")).getName()

                );
                collection.setCollection_id(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    public void createCollection(Collection c){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "INSERT INTO collection (name, publisher_id) VALUES (?,?)";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, c.getName());
            statement.setInt(2, c.getPublicher_id());
            statement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCollection(String name){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "DELETE FROM collection WHERE name = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateCollection(String newName, int newPublisher_id, int id) throws SQLException {
        Connection conn = BDconnection.getConnection();
        String sql = "UPDATE collection SET name = ?, publisher_id = ? WHERE id = ?";
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, newName);
        statement.setInt(2, newPublisher_id);
        statement.setInt(3, id);
        System.out.println(newPublisher_id);
        statement.executeUpdate();
        System.out.println("Record updated.");
    }
}
