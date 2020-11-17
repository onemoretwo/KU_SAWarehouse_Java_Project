package Main.java.DatabaseService;

import Main.java.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDB extends DBConnection{

    public ArrayList<Product> RStoAL(ResultSet rs){
        ArrayList<Product> list = new ArrayList<>();
        try {
            while (rs.next()) {
                list.add(new Product(rs.getString("id"),
                        rs.getInt("category_id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getString("unit"),
                        rs.getDouble("price"),
                        rs.getInt("saftyStock"),
                        rs.getString("status"),
                        rs.getString("shelf_id"),
                        rs.getString("imgName")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ArrayList<Product> getAllProduct(){
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM products");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return RStoAL(rs);
    }

    public boolean createNewProduct(Product product){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, product.getId());
            stmt.setInt(2, product.getCategory_id());
            stmt.setString(3, product.getName());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getUnit());
            stmt.setDouble(6, product.getPrice());
            stmt.setInt(7, product.getSaftyStock());
            stmt.setString(8, product.getShelf_id());
            stmt.setInt(9, product.getStatus());
            stmt.setString(10, product.getImgName());
            boolean success = stmt.execute();
            stmt.close();
            return success;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
