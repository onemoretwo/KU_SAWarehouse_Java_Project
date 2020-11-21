package Main.java.controllers;

import Main.java.DatabaseService.ProductDB;
import Main.java.models.Product;
import Main.java.models.ProductBean;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AllProductController implements Initializable {

    ObservableList<ProductBean> observableList;
    ProductDB productDB;

    @FXML
    TableView productTable;
    @FXML
    TextField fsearch;
    @FXML
    ComboBox<String> orderBy;
    @FXML ToggleButton upDown;
    @FXML
    TableColumn<ProductBean, String> idCol, nameCol, unitCol, shelf_idCol;
    @FXML TableColumn<ProductBean, Double> priceCol;
    @FXML TableColumn<ProductBean, Integer> quantityCol, saftyStockCol;
    @FXML TableColumn<ProductBean, ImageView> imgCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                productDB = new ProductDB();
                observableList = FXCollections.observableArrayList();
                orderBy.getItems().add("id");
                orderBy.getItems().add("price");
                orderBy.getItems().add("quantity");
                orderBy.getSelectionModel().selectFirst();
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
                unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));
                quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                saftyStockCol.setCellValueFactory(new PropertyValueFactory<>("saftyStock"));
                shelf_idCol.setCellValueFactory(new PropertyValueFactory<>("shelf_id"));
                imgCol.setCellValueFactory(new PropertyValueFactory<>("img"));

                show(productDB.getAllProduct());
            }
        });
    }

    @FXML
    public void search(){
        String keyword = fsearch.getText().trim();
        show(productDB.getProduct(keyword, orderBy.getSelectionModel().getSelectedItem(), upDown.isSelected()));
    }

    @FXML
    public void toggleUpDown(){
        if (upDown.isSelected()){
            upDown.setText("▲");
        }else upDown.setText("▼");
    }

    private void show(ArrayList<Product> products){
        observableList.clear();
        for (Product product : products){
            observableList.add(new ProductBean(product));
        }
        productTable.setItems(observableList);
    }
}
