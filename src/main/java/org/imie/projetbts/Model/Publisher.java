package org.imie.projetbts.Model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Publisher {
    public SimpleIntegerProperty publisher_id;
    public SimpleStringProperty name;
    public String createdAt;
    public String updatedAt;

    public Publisher( String name) {
        this.publisher_id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty(name);
    }

    public int getPublisher_id() {
        return publisher_id.get();
    }

    public SimpleIntegerProperty publisher_idProperty() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id.set(publisher_id);
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
}
