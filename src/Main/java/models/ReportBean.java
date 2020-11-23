package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReportBean {
    private SimpleStringProperty date;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;

    public ReportBean(String date, String id, String name, int quantity){
        this.date = new SimpleStringProperty(date);
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }
}
