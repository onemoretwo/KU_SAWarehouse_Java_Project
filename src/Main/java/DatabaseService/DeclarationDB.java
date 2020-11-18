package Main.java.DatabaseService;

import Main.java.models.Declaration;

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
