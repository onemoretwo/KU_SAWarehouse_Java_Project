package Main.java.controllers;

import Main.java.DatabaseService.CategoryDB;
import Main.java.DatabaseService.ProductDB;
import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.Category;
import Main.java.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddNewController extends MenuBtn implements Initializable {

    ProductDB productDB;

    private ArrayList<Category> list;

    @FXML private TextField fname, fid, funit, fprice, fsaftystock;
    @FXML private ComboBox fshelf_id, ftype;
    @FXML private Button declarationConfirm, declarationWait;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }

        productDB = new ProductDB();
        //category setup
        ftype.setPromptText("Category");
        list = new CategoryDB().getAllType();
        for (Category c : list){
            ftype.getItems().add(c.getName());
        }
        //shelf id setup
        fshelf_id.setPromptText("Shelf Id");
        ObservableList<String> options = FXCollections.observableArrayList("A1","A2","A3","A4","A5","A6","A7","A8");
        fshelf_id.setItems(options);
    }

    private void clear(){
        fname.clear();
        ftype.setValue(null);
        fid.clear();
        funit.clear();
        fprice.clear();
        fsaftystock.clear();
        fshelf_id.setValue(null);
    }

    @FXML
    public void clearBtn(){
        clear();
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if (fname.getText().trim().equals("") || fid.getText().trim().equals("") || fprice.getText().trim().equals("") ||
            funit.getText().trim().equals("") || fsaftystock.getText().trim().equals("") || fshelf_id.getSelectionModel().isEmpty() || ftype.getSelectionModel().isEmpty()){
            //กรณีกรอกไม่ครบ
            alert.setContentText("กรุณากรอกข้อมูลให้ครบถ้วน");
            alert.show();
            return;
        }else {
            //Validation alert
            try{
                if (Float.parseFloat(fprice.getText()) <= 0){
                    alertInvalidInput("ราคาสินค้าต้องมากกว่า 0");
                    return;
                }
                if (Integer.parseInt(fsaftystock.getText()) <= 0){
                    alertInvalidInput("จำนวน Safety Stock ต้องมากกว่า 0");
                    return;
                }
                if (productDB.isDuplicatedId(fid.getText().trim())){
                    alertInvalidInput("รหัสสินค้าซ้ำ โปรดกำหนดรหัสสินค้าใหม่");
                    return;
                }
            }catch (NumberFormatException e){
                alertInvalidInput("ราคาสินค้า ต้องเป็นจำนวนจริง(มากกว่า 0) และจำนวน Safety Stock ต้องเป็นจำนวนเต็มบวกเท่านั้น");
                return;
            }
        }
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,"ยืนยันการเพิ่มสินค้า");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.get() == ButtonType.CANCEL)
            return;
        int category_id = 1;
        for (Category c : list){
            if (c.getName().equals(ftype.getValue().toString())){
                category_id = c.getId();
            }
        }
        Product product = new Product(fid.getText().trim(), category_id, fname.getText().trim(), 0, funit.getText().trim(), Double.parseDouble(fprice.getText()),
                                        Integer.parseInt(fsaftystock.getText()), "สินค้าหมด", fshelf_id.getValue().toString(), "null.jpg");
        productDB.createNewProduct(product);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "เพิ่มสินค้าสำเร็จ");
        alert1.show();
        clear();
    }

    private void alertInvalidInput(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING, message);
        alert.show();
    }
}
