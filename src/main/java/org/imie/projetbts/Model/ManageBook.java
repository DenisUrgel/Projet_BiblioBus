package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ManageBook {
    public ObservableList<Book> getBookList() throws SQLException {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from book");
        String sql = "SELECT * FROM book_collection WHERE book_id = ?";
        manageCollection collection = new manageCollection();
        PreparedStatement statement = conn.prepareStatement(sql);
        while (rs.next()) {
            statement.setInt(1, rs.getInt("id"));
            ResultSet result = statement.executeQuery();
            result.next();
            Book book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("image"),
                    rs.getInt("auteur_id"),
                    collection.getCollection(result.getInt("collection_id")).getName()
            );

            bookList.add(book);
        }

        return bookList;
    }
    public void createBook(Book b){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "INSERT INTO book (title, author_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getTitle());
            statement.setObject(2, b.getAuthor_id());
            statement.executeUpdate();
            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
