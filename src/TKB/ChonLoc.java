/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import static TKB.ConnectDatabase.getConnection;
import static TKB.QuanThe.Cathe;
import static TKB.XepTKB.tableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thanh
 */
public class ChonLoc {

    public static HashMap<Integer, Integer> diemsocathe;
   

    public static void diemcathe() {
        diemsocathe = new HashMap<>();
        for (int i = 0; i < Cathe.size(); i++) {
            int tam = DanhGiaCaThe.danhgiacathe(i);
            diemsocathe.put((i + 1), tam);
        }

    }

    public static boolean sapxep() {
        
        List value = new ArrayList(diemsocathe.values());
        Collections.sort(value);
        if(Integer.valueOf((int) value.get(Cathe.size()-1))!=10)
        {
            return false;
        }
        else
        {
            for (Map.Entry<Integer, Integer> entry : diemsocathe.entrySet()) {
            if (entry.getValue() == value.get(Cathe.size() - 1)) {
                System.out.println("Cá thể có độ thích nghi cao nhất là cá thể " + entry.getKey()
                        + " có lịch là: " + Cathe.get(entry.getKey() - 1));
                hienthitkb(entry.getKey() - 1);   
                return true;
            }
        }
        }
        return true;
    }

    public static void hienthitkb(int cathe) {
        int sophong = ConnectDatabase.soluongphonghoc();
        String[][] a = new String[sophong][60];
        for (int i = 0; i < sophong; i++) {
            for (int j = 0; j < 60; j++) {
                a[i][j] = "0";
            }
        }
        System.out.println("Môn\t\tphòng học\tThứ\ttiết bắt đầu\tsố tiết học: " );
        HashMap<Integer, String> monhoc = new HashMap<>();
        monhoc = Cathe.get(cathe);
        int tam1=0;
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            String[] words = entry.getValue().split("-");
            int vitri = Integer.valueOf(words[0]);
            int sotiet = Integer.valueOf(words[1]);
            int x = vitri / 60;
            int y = vitri - x * 60 - 1;
            for (int i = 0; i < sotiet; i++) {
                try {
                    if (!a[x][y + i].equals("0")) {
                        a[x][y + i] = a[x][y + i] + "-" + entry.getKey();
                    } else {
                        a[x][y + i] = String.valueOf(entry.getKey());
                    }
                } catch (Exception e) {

                }
            }
            tam1++;
            String tam=laythongtinmonhoc(entry.getKey());
            tableModel.addRow(new Object[]{tam1,tam,thu(y + 1),tiet(y + 1),sotiet,laythongtinphonghoc(x + 1)});
            System.out.println( tam + 
                    "\t" + laythongtinphonghoc(x + 1) + 
                    "\t" + thu(y + 1) + 
                    "\t" + tiet(y + 1) + 
                    "\t" + sotiet);
        };
//        //Hiển thị 1 cá thể
//        System.out.print("Thời khóa biểu \nPhòng/Tiết ");
//        for (int i = 1; i < 61; i++) {
//            System.out.print(i + "\t");
//        }
//        System.out.print("\n");
//        for (int i = 0; i < sophong; i++) {
//            System.out.print(i + 1 + "          ");
//            for (int j = 0; j < 60; j++) {
//                System.out.print(a[i][j] + "\t");
//            }
//            System.out.println("\n");
//        }
    }

    public static String thu(int y) {
        String thu = "";
        if (y <= 10) {
            thu = "Thứ 2";
        } else {
            if (y <= 20) {
                thu = "Thứ 3";
            } else {
                if (y <= 30) {
                    thu = "Thứ 4";
                } else {
                    if (y <= 40) {
                        thu = "Thứ 5";
                    } else {
                        if (y <= 50) {
                            thu = "Thứ 6";
                        } else {
                            if (y <= 60) {
                                thu = "Thứ 7";
                            }
                        }
                    }
                }
            }
        }
        return thu;
    }
    public static int tiet(int y) {
        return y%10 ;
    }
    public static String laythongtinmonhoc(int t)
    {
        String tenmonhoc="";
        try {
            // connnect to database 'tkb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'monho'
            ResultSet rs = stmt.executeQuery("select * from monhoc where MaMonHoc="+t);
            // show data
            while (rs.next()) {
                tenmonhoc=rs.getString(2);
            }
            
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tenmonhoc;
    }
    public static String laythongtinphonghoc(int t)
    {
        String tenphonghoc="";
        try {
            // connnect to database 'tkb'
            Connection conn = getConnection();
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'phonghoc'
            ResultSet rs = stmt.executeQuery("select * from phonghoc where ID="+t);
            // show data
            while (rs.next()) {
                tenphonghoc=rs.getString(2);
            }
            
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tenphonghoc;
    }
}
