package Main.java.DatabaseService;

import Main.java.models.Declaration;
import Main.java.models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeclarationDB extends DBConnection{

    public ArrayList<Declaration> getAllDeclarationByUser(String username){
        ArrayList<Declaration> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM importdeclaration WHERE username='" + username+ "' AND STATUS=1");
            while (rs.next()){
                int id = rs.getInt("id");
                String myUsername = rs.getString("username");
                String status = rs.getString("status");
                String timestamp = rs.getTimestamp("created_at").toString();
                list.add(new Declaration(id, myUsername, status, timestamp, "import"));
            }
            rs = stmt.executeQuery("SELECT * FROM exportdeclaration WHERE username='" + username+ "' AND status=1");
            while (rs.next()){
                int id = rs.getInt("id");
                String myUsername = rs.getString("username");
                String status = rs.getString("status");
                String timestamp = rs.getTimestamp("created_at").toString();
                list.add(new Declaration(id, myUsername, status, timestamp, "export"));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public ArrayList<Product> getDeclarationDetail(int id, String type){
        ArrayList<Product> list = new ArrayList<>();
        String table = type+"detail";
        try {
            //SELECT importdetail.quantity, products.id, products.name FROM
            // importdetail INNER JOIN products ON importdetail.product_id=products.id WHERE import_id=10
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + table + ".quantity, products.id, products.name FROM " + table +
                    " INNER JOIN products ON " + table + ".product_id=products.id WHERE " + type + "_id=" + id);
            while (rs.next()){
                list.add(new Product(rs.getString("id"), rs.getString("name"), rs.getInt("quantity")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
