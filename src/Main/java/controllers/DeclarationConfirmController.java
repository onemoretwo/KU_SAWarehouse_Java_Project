package Main.java.controllers;

import Main.java.DatabaseService.DeclarationDB;
import Main.java.DatabaseService.ProductDB;
import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.Declaration;
import Main.java.models.DeclarationConfirmBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeclarationConfirmController extends MenuBtn implements Initializable {

    ObservableList<DeclarationConfirmBean> importList, exportList;
    DeclarationDB declarationDB;

    @FXML
    Button declarationWait, declarationConfirm;

    @FXML
    TableView importTable, exportTable;
    @FXML
    TableColumn<DeclarationConfirmBean, Integer> idICol, idECol;
    @FXML TableColumn<DeclarationConfirmBean, String> timeICol, timeECol;
    @FXML TableColumn<DeclarationConfirmBean, Button> detailICol, detailECol, yICol, yECol, nICol, nECol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }

        importList = FXCollections.observableArrayList();
        exportList = FXCollections.observableArrayList();
        declarationDB = new DeclarationDB();

        timeICol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        timeECol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        idICol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idECol.setCellValueFactory(new PropertyValueFactory<>("id"));
        detailICol.setCellValueFactory(new PropertyValueFactory<>("detailBtn"));
        detailECol.setCellValueFactory(new PropertyValueFactory<>("detailBtn"));
        yICol.setCellValueFactory(new PropertyValueFactory<>("yesBtn"));
        yECol.setCellValueFactory(new PropertyValueFactory<>("yesBtn"));
        nICol.setCellValueFactory(new PropertyValueFactory<>("noBtn"));
        nECol.setCellValueFactory(new PropertyValueFactory<>("noBtn"));

        show();
    }

    public void show(){
        importList.clear();
        exportList.clear();
        ArrayList<Declaration> list = declarationDB.getAllDeclaration();
        for (Declaration d : list){
            Button detailBtn = new Button();
            Button noBtn = new Button();
            noBtn.setText("No");
            detailBtn.setText("แสดงรายละเอียด");
            detailBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/declarationDetail.fxml"));
                    Parent root1 = null;
                    try {
                        root1 = (Parent) fxmlLoader.load();
                    }catch (IOException e){
                        System.out.println("Can't load page");
                    }
                    DeclarationDetailController controller = fxmlLoader.getController();
                    controller.setUp(d.getId(), d.getType());
                    Stage stage = new Stage();
                    stage.setTitle("รายละเอียดของใบเบิกหรือใบนำเข้า");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
            });
            noBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"ยืนยัน ไม่อนุมัติ รายการนี้หรือไม่");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.CANCEL)
                        return;
                    declarationDB.noClickUpdate(d.getId(), d.getType());
                    show();

                }
            });
            Button yesBtn = new Button();
            yesBtn.setText("Yes");
            if (d.getType().equals("import") || declarationDB.canExport(d.getId())){
                yesBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"ยืนยัน ไม่อนุมัติ รายการนี้หรือไม่");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.CANCEL)
                            return;
                        Boolean type;
                        if (d.getType().equals("import")) type=true; else type =false;
                        new ProductDB().updateStock(d.getId(), type);
                        declarationDB.yesClickUpdate(d.getId(), d.getType());
                        show();
                    }
                });
            }else {
                yesBtn.setDisable(true);
            }
            if (d.getType().equals("import")){
                importList.add(new DeclarationConfirmBean(d.getId(), d.getTimestamp(), detailBtn, yesBtn, noBtn));
            }else if (d.getType().equals("export")){
                exportList.add(new DeclarationConfirmBean(d.getId(), d.getTimestamp(), detailBtn, yesBtn, noBtn));
            }
        }
        importTable.setItems(importList);
        exportTable.setItems(exportList);
    }
}
