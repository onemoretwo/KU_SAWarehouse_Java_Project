package Main.java.DatabaseService;

import Main.java.Main;
import Main.java.models.ProductAddCartBean;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImportDeclarationDB extends DBConnection {

    public void createNewImport(ObservableList<ProductAddCartBean> cart){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO importdeclaration (username, status) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Main.currentUser.getUsername());
            if (Main.currentUser.getRole().equals("Staff")){
                stmt.setInt(2, 1);
            }else if (Main.currentUser.getRole().equals("Manager")){
                stmt.setInt(2, 2);
            }
            stmt.execute();
            int import_id;
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                import_id = generatedKeys.getInt(1);
            }else{
                throw new SQLException("Creating user failed, no rows affected.");
            }
            new ImportDetailDB().createImportDetail(cart, import_id);
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
