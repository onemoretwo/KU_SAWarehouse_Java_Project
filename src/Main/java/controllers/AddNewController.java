package Main.java.controllers;

import Main.java.DatabaseService.CategoryDB;
import Main.java.DatabaseService.ProductDB;
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

public class AddNewController implements Initializable {

    private ArrayList<Category> list;

    @FXML private TextField fname, fid, funit, fprice, fsaftystock;
    @FXML private ComboBox fshelf_id, ftype;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        //check product
        ArrayList<Product> list = new ProductDB().getAllProduct();
        for (Product p : list){
            System.out.println(p);
        }
    }

    @FXML
    public void clearBtn(){
        fname.clear();
        fid.clear();
        funit.clear();
        fprice.clear();
        fsaftystock.clear();
        fshelf_id.getItems().clear();
    }

    @FXML
    public void addBtn(ActionEvent event) throws IOException {
        if (fname.getText().equals("") || fid.getText().equals("") || fprice.getText().equals("") ||
            funit.getText().equals("") || fsaftystock.getText().equals("") || fshelf_id.getSelectionModel().isEmpty()){
            //Validation alert
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"ยืนยันการเพิ่มสินค้า");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.CANCEL)
            return;
        int category_id = 1;
        for (Category c : list){
            if (c.getName().equals(ftype.getValue().toString())){
                category_id = c.getId();
            }
        }
        Product product = new Product(fid.getText(), category_id, fname.getText(), 0, funit.getText(), Double.parseDouble(fprice.getText()),
                                        Integer.parseInt(fsaftystock.getText()), "สินค้าหมด", "null");
        if (!new ProductDB().createNewProduct(product)){
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "เพิ่มสินค้าสำเร็จ");
            alert1.show();
        }else{
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "รหัสสินค้าซ้ำไม่สามารถสร้างรายการใหม่ได้\n\nโปรดกำหนดรหัสสินค้าใหม่");
            alert2.show();
        }
    }
}
