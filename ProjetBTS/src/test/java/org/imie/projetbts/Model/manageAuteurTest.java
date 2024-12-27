package org.imie.projetbts.Model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class manageAuteurTest {

    private manageAuteur manageAuteur;

    @BeforeEach
    public void setUp() throws SQLException {
        manageAuteur = new manageAuteur();
        Connection conn = BDconnection.getConnection();
        Statement stmt = conn.createStatement();

        // Setup de la table en mémoire pour les tests
        stmt.execute("CREATE TABLE IF NOT EXISTS author (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
        stmt.execute("DELETE FROM author"); // Nettoie la table avant chaque test
    }

    @AfterEach
    public void tearDown() throws SQLException {
        Connection conn = BDconnection.getConnection();
        Statement stmt = conn.createStatement();
        stmt.execute("DROP TABLE author"); // Nettoie la table après chaque test
    }

    @Test
    public void testCreateAuteur() {
        Auteur auteur = new Auteur("Auteur Test");
        manageAuteur.createAuteur(auteur);

        Auteur fetchedAuteur = manageAuteur.getAuteurByName("Auteur Test");
        assertNotNull(fetchedAuteur, "L'auteur devrait être ajouté et récupéré");
        assertEquals("Auteur Test", fetchedAuteur.getName(), "Le nom de l'auteur devrait être 'Auteur Test'");
    }

    @Test
    public void testGetAuteurById() {
        Auteur auteur = new Auteur("Auteur Test");
        manageAuteur.createAuteur(auteur);

        Auteur fetchedAuteur = manageAuteur.getAuteurByName("Auteur Test");
        assertNotNull(fetchedAuteur, "L'auteur devrait être récupéré avec un ID valide");

        Auteur auteurById = manageAuteur.getAuteurById(fetchedAuteur.getAuteur_id());
        assertNotNull(auteurById, "L'auteur devrait être récupéré par son ID");
        assertEquals("Auteur Test", auteurById.getName(), "Le nom de l'auteur récupéré par ID devrait être 'Auteur Test'");
    }

    @Test
    public void testGetAuteurList() throws SQLException {
        manageAuteur.createAuteur(new Auteur("Auteur Test 1"));
        manageAuteur.createAuteur(new Auteur("Auteur Test 2"));

        List<Auteur> auteurs = manageAuteur.getAuteurList();
        assertEquals(2, auteurs.size(), "Il devrait y avoir 2 auteurs dans la liste");
    }

    @Test
    public void testUpdateAuteur() throws SQLException {
        Auteur auteur = new Auteur("Auteur Test");
        manageAuteur.createAuteur(auteur);

        Auteur fetchedAuteur = manageAuteur.getAuteurByName("Auteur Test");
        assertNotNull(fetchedAuteur, "L'auteur devrait être récupéré pour la mise à jour");

        manageAuteur.updateAuteur("Auteur Modifié", fetchedAuteur.getAuteur_id());

        Auteur updatedAuteur = manageAuteur.getAuteurById(fetchedAuteur.getAuteur_id());
        assertEquals("Auteur Modifié", updatedAuteur.getName(), "Le nom de l'auteur devrait être mis à jour à 'Auteur Modifié'");
    }

    @Test
    public void testDeleteAuteur() {
        Auteur auteur = new Auteur("Auteur Supprimé");
        manageAuteur.createAuteur(auteur);

        Auteur fetchedAuteur = manageAuteur.getAuteurByName("Auteur Supprimé");
        assertNotNull(fetchedAuteur, "L'auteur devrait être récupéré avant suppression");

        manageAuteur.deleteAuteur("Auteur Supprimé");

        Auteur deletedAuteur = manageAuteur.getAuteurByName("Auteur Supprimé");
        assertNull(deletedAuteur, "L'auteur ne devrait plus exister après suppression");
    }
}