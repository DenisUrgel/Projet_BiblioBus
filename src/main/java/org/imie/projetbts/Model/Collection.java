package org.imie.projetbts.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Collection {
    public SimpleIntegerProperty collection_id;
    public SimpleIntegerProperty publicher_id;
    public SimpleStringProperty name;
    public String createdAt;
    public String updatedAt;

    public Collection(int id, String name, int publicher_id) {
        this.collection_id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.publicher_id = new SimpleIntegerProperty(publicher_id);
    }

    public SimpleIntegerProperty getCollection_id() {
        return collection_id;
    }

    public SimpleIntegerProperty collection_idProperty() {
        return collection_id;
    }

    public void setCollection_id(int collection_id) {
        this.collection_id.set(collection_id);
    }

    public SimpleIntegerProperty getPublicher_id() {
        return publicher_id;
    }

    public void setPublicher_id(int publicher_id) {
        this.publicher_id.set(publicher_id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
