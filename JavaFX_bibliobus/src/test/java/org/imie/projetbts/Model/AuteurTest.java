package org.imie.projetbts.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuteurTest {

    private Auteur auteur;

    @BeforeEach
    public void setUp() {
        auteur = new Auteur("Jean Dupont");
    }

    @Test
    public void testGetAndSetAuteur_id() {
        auteur.setAuteur_id(1);
        assertEquals(1, auteur.getAuteur_id(), "L'ID de l'auteur devrait être 1");
    }

    @Test
    public void testGetAndSetName() {
        assertEquals("Jean Dupont", auteur.getName(), "Le nom initial de l'auteur devrait être 'Jean Dupont'");
        auteur.setName("Marie Dupont");
        assertEquals("Marie Dupont", auteur.getName(), "Le nom de l'auteur devrait être 'Marie Dupont' après modification");
    }

    @Test
    public void testAuteur_idProperty() {
        assertNotNull(auteur.auteur_idProperty(), "La propriété auteur_id ne doit pas être nulle");
        auteur.setAuteur_id(5);
        assertEquals(5, auteur.auteur_idProperty().get(), "La propriété auteur_id devrait être 5 après modification");
    }

    @Test
    public void testNameProperty() {
        assertNotNull(auteur.nameProperty(), "La propriété name ne doit pas être nulle");
        auteur.setName("Pierre Martin");
        assertEquals("Pierre Martin", auteur.nameProperty().get(), "La propriété name devrait être 'Pierre Martin' après modification");
    }

    @Test
    public void testGetAndSetCreatedAt() {
        auteur.setCreatedAt("2023-10-31");
        assertEquals("2023-10-31", auteur.getCreatedAt(), "La date de création devrait être '2023-10-31'");
    }

    @Test
    public void testGetAndSetUpdatedAt() {
        auteur.setUpdatedAt("2023-11-01");
        assertEquals("2023-11-01", auteur.getUpdatedAt(), "La date de mise à jour devrait être '2023-11-01'");
    }
}