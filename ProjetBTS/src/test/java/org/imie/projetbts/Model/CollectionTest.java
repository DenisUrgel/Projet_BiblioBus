package org.imie.projetbts.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionTest {

    private Collection collection;

    @BeforeEach
    public void setUp() {
        collection = new Collection("Science Fiction", 10, "Sample Publisher");
    }

    @Test
    public void testGetAndSetCollection_id() {
        collection.setCollection_id(1);
        assertEquals(1, collection.getCollection_id(), "L'ID de la collection devrait être 1");
    }

    @Test
    public void testGetAndSetPublicher_id() {
        assertEquals(10, collection.getPublicher_id(), "L'ID initial de l'éditeur devrait être 10");
        collection.setPublicher_id(20);
        assertEquals(20, collection.getPublicher_id(), "L'ID de l'éditeur devrait être 20 après modification");
    }

    @Test
    public void testGetAndSetPublicherName() {
        assertEquals("Sample Publisher", collection.getPublicherName(), "Le nom initial de l'éditeur devrait être 'Sample Publisher'");
        collection.setPublicherName("Updated Publisher");
        assertEquals("Updated Publisher", collection.getPublicherName(), "Le nom de l'éditeur devrait être 'Updated Publisher' après modification");
    }

    @Test
    public void testGetAndSetName() {
        assertEquals("Science Fiction", collection.getName(), "Le nom initial de la collection devrait être 'Science Fiction'");
        collection.setName("Fantasy");
        assertEquals("Fantasy", collection.getName(), "Le nom de la collection devrait être 'Fantasy' après modification");
    }

    @Test
    public void testGetAndSetCreatedAt() {
        collection.setCreatedAt("2023-10-31");
        assertEquals("2023-10-31", collection.getCreatedAt(), "La date de création devrait être '2023-10-31'");
    }

    @Test
    public void testGetAndSetUpdatedAt() {
        collection.setUpdatedAt("2023-11-01");
        assertEquals("2023-11-01", collection.getUpdatedAt(), "La date de mise à jour devrait être '2023-11-01'");
    }
}