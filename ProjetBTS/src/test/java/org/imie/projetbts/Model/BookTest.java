package org.imie.projetbts.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Sample Title", "sample_image",  "Sample summary",123, "Sample collection");
    }

    @Test
    public void testGetAndSetId() {
        book.setId(1);
        assertEquals(1, book.getId(), "L'ID du livre devrait être 1");
    }

    @Test
    public void testTitleProperty() {
        assertEquals("Sample Title", book.getTitle(), "Le titre initial devrait être 'Sample Title'");
        book.setTitle("Updated Title");
        assertEquals("Updated Title", book.getTitle(), "Le titre devrait être 'Updated Title' après modification");
    }

    @Test
    public void testGetAndSetImagePath() {
        book.setImage("new_image");
        // Cette partie suppose que vous avez un fichier d'image de test dans le répertoire des ressources pour cette URL :
        assertTrue(book.getImagePath().contains("new_image.jpg"), "Le chemin de l'image devrait contenir 'new_image.jpg'");
    }

    @Test
    public void testGetAndSetAuthorId() {
        assertEquals(123, book.getAuthor_id(), "L'ID de l'auteur initial devrait être 123");
        book.setAuthor_id(456);
        assertEquals(456, book.getAuthor_id(), "L'ID de l'auteur devrait être 456 après modification");
    }

    @Test
    public void testGetAndSetCopiesAvailable() {
        book.setCopiesAvailable(10);
        assertEquals(10, book.getCopiesAvailable(), "Le nombre de copies disponibles devrait être 10");
    }

    @Test
    public void testCollectionNameProperty() {
        assertEquals("Sample Collection", book.getCollectionName(), "Le nom initial de la collection devrait être 'Sample Collection'");
        book.setCollectionName("Updated Collection");
        assertEquals("Updated Collection", book.getCollectionName(), "Le nom de la collection devrait être 'Updated Collection' après modification");
    }
}