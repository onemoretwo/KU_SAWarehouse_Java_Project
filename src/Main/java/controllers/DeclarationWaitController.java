package Main.java.controllers;

import Main.java.DatabaseService.DeclarationDB;
import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.Declaration;
import Main.java.models.DeclarationWaitBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeclarationWaitController extends MenuBtn implements Initializable {

    ObservableList<DeclarationWaitBean> observableList;
    DeclarationDB declarationDB;

    @FXML
    Button declarationWait, declarationConfirm;
    @FXML
    TableView table;
    @FXML
    TableColumn<DeclarationWaitBean, Integer> idCol;
    @FXML TableColumn<DeclarationWaitBean, String> timeStampCol, typeCol;
    @FXML TableColumn<DeclarationWaitBean, Button> btnCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        timeStampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        btnCol.setCellValueFactory(new PropertyValueFactory<>("button"));

        observableList = FXCollections.observableArrayList();
        declarationDB = new DeclarationDB();

        show();
    }

    public void show(){
        for (Declaration d : declarationDB.getAllDeclarationByUser(Main.currentUser.getUsername())){
            Button button = new Button();
            button.setText("แสดงรายละเอียด");
            button.setOnAction(new EventHandler<ActionEvent>() {
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
            observableList.add(new DeclarationWaitBean(d, button));
        }
        table.setItems(observableList);
    }
}
