package org.imie.projetbts.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class Book {
    public SimpleIntegerProperty id;
    public SimpleStringProperty title;
    public SimpleStringProperty imagePath;
    public SimpleIntegerProperty author_id;
    public SimpleStringProperty summary;
    public SimpleStringProperty categorie;
    public int copiesAvailable;
    public SimpleStringProperty collectionName;
    public String createdAt;
    public String updatedAt;

    public Book(String title, String categorie, String image, String summary, int author_id, String collectionName) {
        this.id = new SimpleIntegerProperty();
        this.title = new SimpleStringProperty(title);
        this.categorie = new SimpleStringProperty(categorie);
        this.summary = new SimpleStringProperty(summary);
        this.author_id = new SimpleIntegerProperty(author_id);
        this.imagePath = new SimpleStringProperty(image);
        this.collectionName = new SimpleStringProperty(collectionName);

    }

    public String getCategorie() {
        return categorie.get();
    }

    public SimpleStringProperty categorieProperty() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie.set(categorie);
    }

    public String getSummary() {
        return summary.get();
    }

    public SimpleStringProperty summaryProperty() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary.set(summary);
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty Book_idProperty() {
        return id;
    }

    public String getTitle() {
        return title.get();
    }
    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getImagePath() {
        return imagePath.get();

    }

    public void setImage(String image) {
        this.imagePath.set(image);
    }

    public int getAuthor_id() {
        return author_id.get();
    }

    public IntegerProperty author_idIntproperty() {
        return author_id;
    }


    public void setAuthor_id(int author_id) {
        this.author_id.set(author_id);
    }

    public StringProperty CollectionNameProperty() {
        return collectionName;
    }

    public String getCollectionName() {
        return collectionName.get();
    }

    public void setCollectionName(String collectionName) {
        this.collectionName.set(collectionName);
    }
}
