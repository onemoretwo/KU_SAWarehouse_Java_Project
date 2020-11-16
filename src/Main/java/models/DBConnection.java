package Main.java.models;

import java.sql.*;

public class DBConnection {

    private Connection connection;

    public DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sawarehouse?useSSL=false", "root", "");
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM categories");
//            while (rs.next()){
//                System.out.println(rs.getString("name"));
//            }
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllRow(String table){
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery("SELECT * FROM " + table);
        } catch (SQLException throwables) {
            System.out.println("error");
        }
        return null;
    }
}
