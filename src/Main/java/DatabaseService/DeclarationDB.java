package Main.java.DatabaseService;

import Main.java.models.Declaration;
import Main.java.models.DeclarationDetailBean;
import Main.java.models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DeclarationDB extends DBConnection{

    public ArrayList<Declaration> getAllDeclaration(){
        ArrayList<Declaration> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM importdeclaration WHERE STATUS=1");
            while (rs.next()){
                int id = rs.getInt("id");
                String myUsername = rs.getString("username");
                String status = rs.getString("status");
                String timestamp = rs.getTimestamp("created_at").toString();
                list.add(new Declaration(id, myUsername, status, timestamp, "import"));
            }
            rs = stmt.executeQuery("SELECT * FROM exportdeclaration WHERE status=1");
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

    public ResultSet getDeclarationDetail(int id, String type){
        ResultSet rs = null;
        String table = type+"detail";
        try {
            //SELECT importdetail.quantity AS actual, products.id, products.name, products.quantity, products.imgName FROM importdetail INNER JOIN products ON importdetail.product_id=products.id WHERE import_id=10
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT " + table + ".quantity AS actual, products.id, products.name, products.quantity, products.imgName FROM " + table +
                    " INNER JOIN products ON " + table + ".product_id=products.id WHERE " + type + "_id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rs;
    }

    public boolean canExport(int id){
        ResultSet rs = getDeclarationDetail(id, "export");
        try {
            while (rs.next()) {
                if (rs.getInt("actual") > rs.getInt("quantity")){
                    return false;
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public void noClickUpdate(int id, String type){
        String table = type+="declaration";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE " + table + " SET STATUS=3 WHERE id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void yesClickUpdate(int id, String type){
        String table = type+="declaration";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE " + table + " SET STATUS=2 WHERE id=" + id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
