package org.imie.projetbts.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class manageBook {
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
                    rs.getString("title"),
                    rs.getString("image"),
                    rs.getInt("auteur_id"),
                    collection.getCollectionById(result.getInt("collection_id")).getName()
            );
            book.setId(rs.getInt("id"));

            bookList.add(book);
        }

        return bookList;
    }

    public Book getBookByName(String title){
        Book book =null;
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "SELECT * FROM book WHERE title = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, title);
            ResultSet result = statement.executeQuery();
            manageCollection collection = new manageCollection();
            if (result.next()) {
                book = new Book(
                        result.getString("title"),
                        result.getString("image"),
                        result.getInt("auteur_id"),
                        null
                );
                book.setId(result.getInt("id"));

                String sql2 = "SELECT * FROM book_collection WHERE book_id = ?";
                PreparedStatement statement2 = conn.prepareStatement(sql2);
                statement2.setInt(1, book.getId());
                ResultSet rs = statement2.executeQuery();

                if (rs.next()) {
                    manageCollection collection2 = new manageCollection();
                    String collectionName = collection2.getCollectionById(rs.getInt("collection_id")).getName();
                    book.setCollectionName(collectionName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public void createBook(Book b){
        try {
            Connection conn = BDconnection.getConnection();
            assert conn != null;
            String sql = "INSERT INTO book (title, auteur_id) VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, b.getTitle());
            statement.setInt(2, b.getAuthor_id());
            statement.executeUpdate();

            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next())
                b.setId(generatedKey.getInt(1));

            System.out.println("Record created.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void createBookCollection(Book b, Collection c) throws SQLException {
        Connection conn = BDconnection.getConnection();
        assert conn != null;
        String sql = "INSERT INTO book_collection (book_id, collection_id) VALUES (?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, b.getId());
        statement.setInt(2, c.getCollection_id());
        statement.executeUpdate();


    }

    public void deleteBook(int id){
        try {
            Connection conn = BDconnection.getConnection();
            String sql = "DELETE FROM book WHERE id = ?";
            assert conn != null;
            PreparedStatement statement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Record deleted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
