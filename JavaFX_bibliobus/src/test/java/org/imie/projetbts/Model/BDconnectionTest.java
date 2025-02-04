package org.imie.projetbts.Model;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class BDconnectionTest {

    @Test
    void testGetConnection() {
        Connection connection = BDconnection.getConnection();
        assertNotNull(connection, "La connexion ne doit pas être nulle");
        try {
            assertFalse(connection.isClosed(), "La connexion doit être ouverte");
        } catch (Exception e) {
            fail("La connexion a généré une exception inattendue : " + e.getMessage());
        }
    }
}