/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author thanh
 */
public class ConnectDatabase {

    private static String DB_URL = "jdbc:mysql://localhost:3306/tkb?useUnicode=true&characterEncoding=UTF-8";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
    public static Connection getConnection() {
        String dbURL=DB_URL;
        String userName=USER_NAME;
        String password=PASSWORD;
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            //System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    public static int soluongphonghoc()
    {
        int tong=0;
        try {
            // connnect to database 'tkb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from phonghoc");
            // show data
            while (rs.next()) {
                tong++;
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tong;
    }
    
}
