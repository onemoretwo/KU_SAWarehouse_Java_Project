package Main.java.controllers;

import Main.java.Main;
import Main.java.contents.MenuBtn;
import Main.java.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController extends MenuBtn implements Initializable {

    @FXML
    private Label srole, sname, susername, sage, semail, stelephoneNumber, screated_at;
    @FXML
    private Button declarationConfirm, declarationWait;
    @FXML private ImageView myImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Main.currentUser.getRole().equals("Staff")){
            declarationConfirm.setVisible(false);
        }else if (Main.currentUser.getRole().equals("Manager")){
            declarationWait.setVisible(false);
        }
        setUserDetail(Main.currentUser);
        myImg.setImage(new Image(new File(getClass().getClassLoader().getResource("images/" +Main.currentUser.getImgName()).getPath()).toURI().toString()));
    }

    public void setUserDetail(User user){
        srole.setText("ตำแหน่ง : " + user.getRole());
        susername.setText("Username : " + user.getUsername());
        sname.setText("ชื่อ " + user.getName());
        sage.setText("อายุ : " + user.getAge());
        semail.setText("email : " + user.getEmail());
        stelephoneNumber.setText("เบอร์โทรศัพท์ : " + user.getTelephoneNumber());
        screated_at.setText("วันที่ลงทะเบียน : " + user.getCreated_at());
    }

    @FXML
    public void addOldBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/addOld.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void declarationWaitBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/declarationWait.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void declarationConfirmBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/declarationConfirm.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void requisitionBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/requisition.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
