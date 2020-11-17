package Main.java.controllers;

import Main.java.DatabaseService.ImportDeclarationDB;
import Main.java.DatabaseService.ProductDB;
import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.Product;
import Main.java.models.ProductAddCartBean;
import Main.java.models.ProductAddOldBean;
import Main.java.models.ProductBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddOldController extends MenuBtn implements Initializable {

    ObservableList<ProductAddOldBean> observableList;
    ObservableList<ProductAddCartBean> cart;
    ProductDB productDB;
    ImportDeclarationDB importDeclarationDB;

    @FXML private TableView aTable,bTable;
    @FXML private TableColumn<ProductAddOldBean, String> idACol, nameACol, nameBCol;
    @FXML private TableColumn<ProductAddOldBean, ImageView> imgACol, imgBCol;
    @FXML private TableColumn<ProductAddOldBean, Integer> quantityACol, quantityBCol;
    @FXML private TableColumn<ProductAddOldBean, Button> btnACol, btnBCol;
    @FXML private Button declarationConfirm, declarationWait;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }

        observableList = FXCollections.observableArrayList();
        cart = FXCollections.observableArrayList();
        productDB = new ProductDB();
        importDeclarationDB = new ImportDeclarationDB();

        //Table A
        imgACol.setCellValueFactory(new PropertyValueFactory<>("img"));
        idACol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameACol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityACol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        btnACol.setCellValueFactory(new PropertyValueFactory<>("button"));

        //Table B
        imgBCol.setCellValueFactory(new PropertyValueFactory<>("img"));
        nameBCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityBCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        btnBCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        show(productDB.getAllProduct());
    }

    private void show(ArrayList<Product> list){
        observableList.clear();
        for (Product product : list){
            Button button = new Button();
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    TextInputDialog td = new TextInputDialog("1");
                    td.setHeaderText(product.getName());
                    td.setContentText("ระบุจำนวนสินค้าที่เพิ่ม : ");
                    Optional<String> result = td.showAndWait();
                    if (result.isPresent()){
                        Button deleteButton = new Button();
                        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                for (int i=0; i<cart.size(); i++){
                                    if (cart.get(i).getId().equals(product.getId())){
                                        cart.remove(i);
                                        break;
                                    }
                                }
                                bTable.setItems(cart);
                            }
                        });
                        ProductAddCartBean row = new ProductAddCartBean(product, Integer.parseInt(result.get()), deleteButton);
                        cart.add(row);
                        bTable.setItems(cart);
                    }
                }
            });
            observableList.add(new ProductAddOldBean(product, button));
        }
        aTable.setItems(observableList);
    }

    public void submitBtn(ActionEvent event) throws IOException {
        if (cart.size() == 0){
            Alert noItemAlert = new Alert(Alert.AlertType.WARNING, "ไม่มีรายการเพิ่มสินค้า โปรดเลือกรายการ");
            noItemAlert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String content = "ยืนยันการเพิ่มรายการสินค้าดังนี้หรือไม่\n\n";
        for (ProductAddCartBean p : cart){
            content += "(" + p.getId() + ") " + p.getName() + "  " + p.getQuantity() + "  " + p.getUnit() + "\n";
        }
        alert.setContentText(content);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL)
            return;
        importDeclarationDB.createNewImport(cart);
        Alert success = null;
        if (Main.currentUser.getRole().equals("Staff")){
            success = new Alert(Alert.AlertType.INFORMATION,"สร้างรายการนำเข้าสินค้าสำเร็จ\n\nรอการยืนยันจากหัวหน้าแผนก");
        }else if (Main.currentUser.getRole().equals("Manager")){
            success = new Alert(Alert.AlertType.INFORMATION,"สร้างรายการนำเข้าสินค้าสำเร็จ\n\nรายการสินค้า Update แล้ว");
        }
        success.show();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/home.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
