package Main.java.DatabaseService;

import Main.java.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDB extends DBConnection{

    public User AuthorizeUser(String username, String password){
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "'");
            while (rs.next()){
                String myusername = rs.getString("username");
                String mypassword = rs.getString("password");
                String name = rs.getString("name");
                String role = rs.getString("role");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String telephoneNumber = rs.getString("telephoneNumber");
                String email = rs.getString("email");
                String created_at = rs.getTimestamp("created_at").toString();
                String imgName = rs.getString("imgName");
                stmt.close();
                if (mypassword.equals(password)){
                    return new User(myusername, mypassword, name, role, age, gender, telephoneNumber,
                                    email, created_at, imgName);
                } return null;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
