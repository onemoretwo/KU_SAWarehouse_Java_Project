package Main.java.controllers;

import Main.java.DatabaseService.DeclarationDB;
import Main.java.models.DeclarationDetailBean;
import Main.java.models.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeclarationDetailController implements Initializable {

    ObservableList<DeclarationDetailBean> observableList;
    DeclarationDB declarationDB;

    private int declaration_id;
    private String type;

    @FXML
    TableView table;
    @FXML
    TableColumn<DeclarationDetailBean, String> idCol, nameCol, warnCol;
    @FXML TableColumn<DeclarationDetailBean, Integer> quantityCol, actualCol;
    @FXML TableColumn<DeclarationDetailBean, ImageView> imgCol;

    public void setUp(int id, String type){
        this.declaration_id = id;
        this.type = type;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imgCol.setCellValueFactory(new PropertyValueFactory<>("img"));
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                actualCol.setCellValueFactory(new PropertyValueFactory<>("actual"));
                warnCol.setCellValueFactory(new PropertyValueFactory<>("warning"));

                if (type.equals("import")){
                    quantityCol.setText("จำนวนที่เพิ่ม");
                }else quantityCol.setText("จำนวนที่เบิก");

                observableList = FXCollections.observableArrayList();
                declarationDB = new DeclarationDB();

                show();
            }
        });
    }

    public void show(){
        ResultSet rs = declarationDB.getDeclarationDetail(declaration_id, type);
        try {
            while (rs.next()) {
                String imgName = rs.getString("imgName");
                String id = rs.getString("id");
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                int actual = rs.getInt("actual");
                Product product = new Product(imgName, id, name, quantity);
                observableList.add(new DeclarationDetailBean(product, actual, type));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(observableList);
    }
}
