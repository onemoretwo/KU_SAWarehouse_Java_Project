package Main.java.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class DeclarationDetailBean {
    private ImageView img;
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleIntegerProperty quantity;
    private SimpleIntegerProperty actual;
    private SimpleStringProperty warning;

    public DeclarationDetailBean(Product product, int quantity, String type){
        this.img = new ImageView(new Image(new File(getClass().getClassLoader().getResource("images/" + product.getImgName()).getPath()).toURI().toString()));
        img.setFitHeight(70);
        img.setFitWidth(100);
        this.id = new SimpleStringProperty(product.getId());
        this.name = new SimpleStringProperty(product.getName());
        this.quantity = new SimpleIntegerProperty(quantity);
        this.actual = new SimpleIntegerProperty(product.getQuantity());
        if (type.equals("import")){
            warning = new SimpleStringProperty("");
        }else if (product.getQuantity() < quantity) warning = new SimpleStringProperty("⚠");
        else warning = new SimpleStringProperty("");
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

    public int getActual() {
        return actual.get();
    }

    public SimpleIntegerProperty actualProperty() {
        return actual;
    }

    public String getWarning() {
        return warning.get();
    }

    public SimpleStringProperty warningProperty() {
        return warning;
    }
}
