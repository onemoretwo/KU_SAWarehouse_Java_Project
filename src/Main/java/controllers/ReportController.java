package Main.java.controllers;

import Main.java.DatabaseService.ExportDeclarationDB;
import Main.java.contents.MenuBtn;
import Main.java.models.ReportBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ReportController  extends MenuBtn implements Initializable {

    ObservableList<ReportBean> list;
    ExportDeclarationDB exportDeclarationDB;

    @FXML
    TableView table;
    @FXML
    TableColumn<ReportBean, String> dateCol, idCol, nameCol;
    @FXML TableColumn<ReportBean, Integer> quantityCol;
    @FXML
    DatePicker start,stop;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        list = FXCollections.observableArrayList();
        exportDeclarationDB = new ExportDeclarationDB();
    }

    @FXML
    public void submitBtn(){
        if (start.getValue() == null || stop.getValue() == null || start.getValue().isAfter(stop.getValue())){
            Alert alert = new Alert(Alert.AlertType.ERROR, "วันที่ไม่ถูกต้องกรุณากรอกวันที่ใหม่");
            alert.showAndWait();
            start.setValue(null);
            stop.setValue(null);
            return;
        }
        String startTime = start.getValue().toString();
        String stopTime = stop.getValue().toString();
        show(startTime, stopTime);
    }

    private void show(String startTime, String stopTime){
        list.clear();
        ResultSet rs = exportDeclarationDB.getExportReport(startTime, stopTime);
        try {
            while (rs.next()){
                list.add(new ReportBean(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate().toString(),rs.getString("id"),rs.getString("name"),rs.getInt("quantity")));
                System.out.println(rs.getTimestamp("created_at").toLocalDateTime().toLocalDate().toString());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        table.setItems(list);
    }
}
