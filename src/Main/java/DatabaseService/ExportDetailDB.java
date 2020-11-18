package Main.java.DatabaseService;

import Main.java.Main;
import Main.java.models.ProductAddCartBean;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExportDetailDB extends DBConnection{

    public void createExportDetail(ObservableList<ProductAddCartBean> cart, int export_id){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO exportdetail (export_id, product_id, quantity) VALUES (?, ?, ?)");
            for (ProductAddCartBean p : cart){
                stmt.setInt(1, export_id);
                stmt.setString(2, p.getId());
                stmt.setInt(3, p.getQuantity());
                stmt.execute();
            }
            stmt.close();
            if (Main.currentUser.getRole().equals("Manager")){
                new ProductDB().updateStock(export_id, false);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
