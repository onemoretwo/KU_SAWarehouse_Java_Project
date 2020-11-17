package Main.java.controllers;

import Main.java.DatabaseService.UserDB;
import Main.java.Main;
import Main.java.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField fusername,fpassword;

    @FXML
    public void loginBtn(ActionEvent event) throws IOException {
        User user = new UserDB().AuthorizeUser(fusername.getText(),fpassword.getText());
        if (user == null){
            // Invalid username or password ***
        }else{
            Main.currentUser = user;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/home.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
    }
}
