package Main.java;

import Main.java.DatabaseService.DBConnection;
import Main.java.DatabaseService.UserDB;
import Main.java.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class Main extends Application {

    public static User currentUser;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/login.fxml"));
        primaryStage.setTitle("SAWarehouse");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        DBConnection dbConnection = new DBConnection();
        ResultSet rs = dbConnection.getAllRow("users");
        while(rs.next()){
            System.out.println(rs.getString("username") + " " + rs.getString("name"));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
