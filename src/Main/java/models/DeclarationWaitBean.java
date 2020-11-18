package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class DeclarationWaitBean {

    private SimpleIntegerProperty id;
    private SimpleStringProperty timestamp;
    private SimpleStringProperty type;
    private Button button;

    public DeclarationWaitBean(Declaration declaration, Button button){
        this.id = new SimpleIntegerProperty(declaration.getId());
        this.timestamp = new SimpleStringProperty(declaration.getTimestamp());
        this.type = new SimpleStringProperty(declaration.getType());
        this.button = button;
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getTimestamp() {
        return timestamp.get();
    }

    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public Button getButton() {
        return button;
    }
}
