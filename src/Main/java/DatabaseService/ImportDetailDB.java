package Main.java.DatabaseService;

import Main.java.Main;
import Main.java.models.ProductAddCartBean;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImportDetailDB extends DBConnection{

    public void createImportDetail(ObservableList<ProductAddCartBean> cart, int import_id){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO importdetail (import_id, product_id, quantity) VALUES (?, ?, ?)");
            for (ProductAddCartBean p : cart){
                stmt.setInt(1, import_id);
                stmt.setString(2, p.getId());
                stmt.setInt(3, p.getQuantity());
                stmt.execute();
            }
            stmt.close();
            if (Main.currentUser.getRole().equals("Manager")){
                new ProductDB().updateStock(import_id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
