package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ProductAddCartBean {
    private String id;
    private String unit;
    private ImageView img;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;
    private Button button;

    public ProductAddCartBean(Product product, int quantity, Button button){
        this.id = product.getId();
        this.unit = product.getUnit();
        this.img = new ImageView(new Image(new File(getClass().getClassLoader().getResource("images/null.jpg").getPath()).toURI().toString()));
        img.setFitHeight(50);
        img.setFitWidth(60);
        this.name = new SimpleStringProperty(product.getName());
        this.quantity = new SimpleIntegerProperty(quantity);
        this.button = button;
        this.button.setText("ลบรายการ");
    }

    public String getId() {
        return id;
    }

    public ImageView getImg() {
        return img;
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

    public Button getButton() {
        return button;
    }

    public String getUnit() {
        return unit;
    }
}
