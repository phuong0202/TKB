/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import static TKB.ConnectDatabase.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author thanh
 */
public class QuanThe {
    public static Vector<HashMap> Cathe= new Vector<>();
    public static HashMap<Integer, String> cathe = new HashMap<>();
    public static HashMap<Integer, Integer> xacsuat = new HashMap<>();
    public static int sophong;
    public static HashMap<Integer, Integer> soSVcuamonhoc = new HashMap<>();
    public static HashMap<Integer, Integer> sochongoicuaphonghoc = new HashMap<>();
    public static void TaoQuanThe() {
        sophong = ConnectDatabase.soluongphonghoc();
        Random generator = new Random();
        for (int i = 1; i <= 1000; i++) {
            xacsuat.put(i, generator.nextInt(99) + 1);
            System.out.println("Cá thể " + i+" có xác suất: "+xacsuat.get(i)+"%");
            cathe=CaThe.TaoCaThe();
            Cathe.add(cathe);
        }
        laythongtinchongoi(); 
    }
    public static void laythongtinchongoi()
    {
        try {
            // connnect to database 'tkb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'monhoc'
            ResultSet rs1 = stmt.executeQuery("select * from monhoc");
            // show data
            while (rs1.next()) {
                soSVcuamonhoc.put(rs1.getInt(1), rs1.getInt(7));
            }
            // get data from table 'phonghoc'
            ResultSet rs2 = stmt.executeQuery("select * from phonghoc");
            // show data
            while (rs2.next()) {
                sochongoicuaphonghoc.put(rs2.getInt(1), rs2.getInt(3));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
