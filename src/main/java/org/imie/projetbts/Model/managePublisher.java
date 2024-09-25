package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class managePublisher {
    public ObservableList<Publisher> getPublisherList() throws SQLException {
        ObservableList<Publisher> publisherList = FXCollections.observableArrayList();
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from publisher");
        while (rs.next()) {
            Publisher publisher = new Publisher(
                    rs.getString("name")
            );
            publisher.setPublisher_id(rs.getInt("id"));
            publisherList.add(publisher);
        }

        return publisherList;
    }
    public Publisher getPublisher(int id){
        Publisher publisher = null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM publisher WHERE id = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                publisher = new Publisher(
                        result.getString("name")
                );
                publisher.setPublisher_id(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisher;
    }

    public void createPublisher(Publisher p){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "INSERT INTO publisher (name) VALUES (?)";
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, p.getName());
            statement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deletePublisher(String name){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "DELETE FROM publisher WHERE name = ?";
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
