package Main.java.controllers;

import Main.java.Main;
import Main.java.contents.MenuBtn;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class RequisitionController extends MenuBtn implements Initializable {

    @FXML
    Button declarationConfirm, declarationWait;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }
    }
}
