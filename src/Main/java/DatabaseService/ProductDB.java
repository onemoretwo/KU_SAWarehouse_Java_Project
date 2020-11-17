package Main.java.DatabaseService;

import Main.java.models.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDB extends DBConnection{

    public ArrayList<Product> getAllProduct(){
        ArrayList<Product> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM products");
            while (rs.next()){
                list.add(new Product(rs.getString("id"),
                                    rs.getInt("category_id"),
                                    rs.getString("name"),
                                    rs.getInt("quantity"),
                                    rs.getString("unit"),
                                    rs.getDouble("price"),
                                    rs.getInt("saftyStock"),
                                    rs.getString("status"),
                                    rs.getString("imgName")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean createNewProduct(Product product){
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, product.getId());
            stmt.setInt(2, product.getCategory_id());
            stmt.setString(3, product.getName());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getUnit());
            stmt.setDouble(6, product.getPrice());
            stmt.setInt(7, product.getSaftyStock());
            stmt.setInt(8, product.getStatus());
            stmt.setString(9, product.getImgName());
            boolean success = stmt.execute();
            System.out.println(success);
            stmt.close();
            return success;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
