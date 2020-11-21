package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ProductAddOldBean {
    private ImageView img;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty safetyStock;
    private Button button;

    public ProductAddOldBean(Product product, Button button){
        this.img = new ImageView(new Image(new File(getClass().getClassLoader().getResource("images/null.jpg").getPath()).toURI().toString()));
        img.setFitHeight(70);
        img.setFitWidth(100);
        this.id = new SimpleStringProperty(product.getId());
        this.name = new SimpleStringProperty(product.getName());
        this.quantity = new SimpleIntegerProperty(product.getQuantity());
        this.safetyStock = new SimpleIntegerProperty(product.getSaftyStock());
        this.button = button;
    }

    public ImageView getImg() {
        return img;
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

    public int getSafetyStock() {
        return safetyStock.get();
    }

    public SimpleIntegerProperty safetyStockProperty() {
        return safetyStock;
    }

    public Button getButton() {
        return button;
    }
}
