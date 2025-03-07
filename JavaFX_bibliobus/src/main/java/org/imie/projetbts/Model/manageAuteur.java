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
                    rs.getString("name")
            );
                    auteur.setAuteur_id(rs.getInt("id"));
            auteurList.add(auteur);
        }

        return auteurList;
    }

    public Auteur getAuteurByName(String name){
        Auteur auteur = null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM author WHERE name = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                auteur = new Auteur(
                        result.getString("name")
                );
                auteur.setAuteur_id(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auteur;
    }
    public Auteur getAuteurById(int id){
        Auteur auteur = null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM author WHERE id = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                auteur = new Auteur(
                        result.getString("name")
                );
                auteur.setAuteur_id(result.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auteur;
    }

    public void createAuteur(Auteur a){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "INSERT INTO author (name) VALUES (?)";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, a.getName());
            statement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAuteur(String name){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "DELETE FROM author WHERE name = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateAuteur(String newName, int id) throws SQLException {
        Connection conn = BDconnection.getConnection();
        String sql = "UPDATE author SET name = ? WHERE id = ?";
        assert conn != null;
        PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, newName);
        statement.setInt(2, id);
        statement.executeUpdate();
        System.out.println("Record updated.");
    }
}
