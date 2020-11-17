package Main.java.DatabaseService;

import Main.java.models.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDB extends DBConnection{

    public ArrayList<Category> getAllType(){
        ArrayList<Category> list = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
            while (rs.next()){
                list.add(new Category(rs.getInt("id"),rs.getString("name")));
            }
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
