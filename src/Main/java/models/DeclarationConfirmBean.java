package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class DeclarationConfirmBean {
    private SimpleIntegerProperty id;
    private SimpleStringProperty timestamp;
    private Button detailBtn;
    private Button yesBtn;
    private Button noBtn;

    public DeclarationConfirmBean(int id, String timestamp, Button b1, Button b2, Button b3){
        this.id = new SimpleIntegerProperty(id);
        this.timestamp = new SimpleStringProperty(timestamp);
        this.detailBtn = b1;
        this.yesBtn = b2;
        this.noBtn = b3;
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

    public Button getDetailBtn() {
        return detailBtn;
    }

    public Button getYesBtn() {
        return yesBtn;
    }

    public Button getNoBtn() {
        return noBtn;
    }
}
