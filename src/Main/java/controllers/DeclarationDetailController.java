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

import java.net.URL;
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
    TableColumn<DeclarationDetailBean, String> idCol, nameCol;
    @FXML TableColumn<DeclarationDetailBean, Integer> quantityCol;

    public void setUp(int id, String type){
        this.declaration_id = id;
        this.type = type;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
                nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
                quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

                observableList = FXCollections.observableArrayList();
                declarationDB = new DeclarationDB();

                show();
            }
        });
    }

    public void show(){
        ArrayList<Product> list = declarationDB.getDeclarationDetail(declaration_id, type);
        for (Product p : list){
            observableList.add(new DeclarationDetailBean(p.getId(), p.getName(), p.getQuantity()));
        }
        table.setItems(observableList);
    }
}
