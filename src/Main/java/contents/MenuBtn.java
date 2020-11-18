package Main.java.contents;

import Main.java.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuBtn {

    @FXML
    public void addNewBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/addNew.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void logoutBtn(ActionEvent event) throws IOException {
        Main.currentUser = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/login.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void allProductBtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/allProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("สินค้าทั้งหมดในโกดัง");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    @FXML
    public void homeBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/home.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    public void addOldBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/addOld.fxml"));
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

    @FXML
    public void declarationWait(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/declarationWait.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
}
