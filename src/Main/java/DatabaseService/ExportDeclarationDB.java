package Main.java.DatabaseService;

import Main.java.Main;
import Main.java.models.ProductAddCartBean;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExportDeclarationDB extends DBConnection{

    public void createNewExport(ObservableList<ProductAddCartBean> cart){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO exportdeclaration (username, status) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Main.currentUser.getUsername());
            if (Main.currentUser.getRole().equals("Staff")){
                stmt.setInt(2, 1);
            }else if (Main.currentUser.getRole().equals("Manager")){
                stmt.setInt(2, 2);
            }
            stmt.execute();
            int export_id;
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                export_id = generatedKeys.getInt(1);
            }else{
                throw new SQLException("Creating export failed, no rows affected.");
            }
            new ExportDetailDB().createExportDetail(cart, export_id);
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
