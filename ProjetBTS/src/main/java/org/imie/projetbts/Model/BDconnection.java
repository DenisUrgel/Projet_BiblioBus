package org.imie.projetbts.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconnection {
    static final String DB_URL = "jdbc:mysql://localhost/bibliobus";
    static final String USER = "root";
    static final String PASS = "";

    public static Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
