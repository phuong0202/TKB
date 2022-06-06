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
import java.util.Map;
import java.util.Random;

/**
 *
 * @author thanh
 */
public class CaThe {

    /**
     */
    public static HashMap<Integer, String> monhoc;
    public static HashMap<Integer, String> diemso;
    public static int x, y, vitri, sotiet, d;
    public static Random generator = new Random();
    public static HashMap TaoCaThe() {
        // STODO code application logic here
        System.out.println("Mã môn học - Tiết bắt đầu - Số tiết cần học:");
        taoNST();
        while (kiemtraNST() == false) {
            taoNST();
        }
        //System.out.println("Tạo NST");
        int sophong=QuanThe.sophong;
        String[][] a = new String[sophong][60];
        for (int i = 0; i < sophong; i++) {
            for (int j = 0; j < 60; j++) {
                a[i][j] = "0";
            }
        }
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            String[] words = entry.getValue().split("-");
            vitri = Integer.valueOf(words[0]);
            sotiet = Integer.valueOf(words[1]);
            x = vitri / 60;
            y = vitri - x * 60 - 1;
            System.out.println(entry.getKey() + " - " + entry.getValue());
            for (int i = 0; i < sotiet; i++) {
                if (!a[x][y + i].equals("0")) {
                    a[x][y + i] = a[x][y + i] + "-" + entry.getKey();
                } else {
                    a[x][y + i] = String.valueOf(entry.getKey());
                }
            }

        };

        //Hiển thị 1 cá thể
        System.out.print("Thời khóa biểu \nPhòng/Tiết ");
        for (int i = 1; i < 61; i++) {
            System.out.print(i + "\t");
        }
        System.out.print("\n");
        for (int i = 0; i < sophong; i++) {
            System.out.print(i + 1 + "          ");
            for (int j = 0; j < 60; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println("\n");
        }

        return monhoc;
    }

    public static void taoNST() {
        //Tạo NST
        monhoc = new HashMap<>();
        try {
            // connnect to database 'tkb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'monhoc'
            ResultSet rs = stmt.executeQuery("select * from monhoc");
            // show data
            while (rs.next()) {
                monhoc.put(rs.getInt(1), generator.nextInt(60*QuanThe.sophong) + 1 + "-" + rs.getInt(6));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean kiemtraNST() {
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            String[] words = entry.getValue().split("-");
            int vitri = Integer.valueOf(words[0]);
            int sotiet = Integer.valueOf(words[1]);
            int x = vitri / 60;
            int y = vitri - x * 60 - 1;
            if (y + sotiet > 60 || vitri % 60 == 0) {
                //System.out.println("Lỗi");
                monhoc.clear();
                return false;
            }
        }
        return true;
    }
}
