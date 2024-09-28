package org.imie.projetbts.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Auteur {
    public SimpleIntegerProperty auteur_id;
    public SimpleStringProperty name;
    public String createdAt;
    public String updatedAt;

    public Auteur(String name) {
        this.auteur_id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty(name);
    }

    public int getAuteur_id() {
        return auteur_id.get();
    }

    public SimpleIntegerProperty auteur_idProperty() {
        return auteur_id;
    }

    public void setAuteur_id(int auteur_id) {
        this.auteur_id.set(auteur_id);
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
