package Main.java.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ProductBean {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;
    private SimpleStringProperty unit;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty saftyStock;
    private SimpleStringProperty shelf_id;
    private ImageView img;

    public ProductBean(Product product){
        this.id = new SimpleStringProperty(product.getId());
        this.name = new SimpleStringProperty(product.getName());
        this.price = new SimpleDoubleProperty(product.getPrice());
        this.unit = new SimpleStringProperty(product.getUnit());
        this.quantity = new SimpleIntegerProperty(product.getQuantity());
        this.saftyStock = new SimpleIntegerProperty(product.getSaftyStock());
        this.shelf_id = new SimpleStringProperty(product.getShelf_id());
        this.img = new ImageView(new Image(new File(getClass().getClassLoader().getResource("images/null.jpg").getPath()).toURI().toString()));
        img.setFitHeight(60);
        img.setFitWidth(60);
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

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public String getUnit() {
        return unit.get();
    }

    public SimpleStringProperty unitProperty() {
        return unit;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public int getSaftyStock() {
        return saftyStock.get();
    }

    public SimpleIntegerProperty saftyStockProperty() {
        return saftyStock;
    }

    public String getShelf_id() {
        return shelf_id.get();
    }

    public SimpleStringProperty shelf_idProperty() {
        return shelf_id;
    }

    public ImageView getImg() {
        return img;
    }
}
