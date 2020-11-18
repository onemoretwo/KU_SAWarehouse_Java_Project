package Main.java.controllers;

import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.DeclarationConfirmBean;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DeclarationConfirmController extends MenuBtn implements Initializable {

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
    }
}
