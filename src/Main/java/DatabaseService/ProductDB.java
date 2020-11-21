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

    public boolean isDuplicatedId(String id){
        try {
            Statement find = connection.createStatement();
            ResultSet product = find.executeQuery("SELECT * FROM products WHERE id='" + id + "'");
            if (product.next()){
                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void createNewProduct(Product product){
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
            stmt.execute();
            stmt.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateStock(int id, boolean type){ // true for import false for export
        try {
            String table;
            String table_id;
            if (type){
                table = "importdetail";
                table_id = "import_id";
            } else{
                table = "exportdetail";
                table_id = "export_id";
            }
            Statement stmt = connection.createStatement();
            PreparedStatement update = connection.prepareStatement("UPDATE products SET quantity = ? WHERE id = ?");
            Statement find = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " WHERE " + table_id + " = "+ id);
            while (rs.next()){
                String product_id = rs.getString("product_id");
                int quantity = rs.getInt("quantity");
                ResultSet rsProduct = find.executeQuery("SELECT * FROM products WHERE id = \""+product_id+"\"");
                int oldQuantity = 0;
                if (rsProduct.next()){
                    oldQuantity = rsProduct.getInt("quantity");
                }
                int totalQuantity;
                if (type) totalQuantity = oldQuantity+quantity; else totalQuantity = oldQuantity-quantity;
                update.setInt(1, totalQuantity);
                update.setString(2, product_id);
                update.executeUpdate();
            }
            stmt.close();
            find.close();
            update.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Product> getProduct(String keyword, String orderBy, boolean upDown){
        ArrayList<Product> products = null;
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM products WHERE name LIKE '%" + keyword + "%' OR id like '%" + keyword + "%' ORDER BY " + orderBy + " ";
            if (upDown){
                sql += "DESC";
            }
            rs = stmt.executeQuery(sql);
            products = RStoAL(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }


}
