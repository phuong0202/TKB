/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import static TKB.ConnectDatabase.getConnection;
import static TKB.QuanThe.Cathe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author thanh
 */
public class DanhGiaCaThe {

    public static String loi = "";

    public static int danhgiacathe(int cathe) {
        int diem = 10;
        int sophong = QuanThe.sophong;
        String[][] a = new String[sophong][60];
        for (int i = 0; i < sophong; i++) {
            for (int j = 0; j < 60; j++) {
                a[i][j] = "0";
            }
        }
        HashMap<Integer, String> monhoc = new HashMap<>();
        HashMap<Integer, Integer> tongsophong = new HashMap<>();
        monhoc = Cathe.get(cathe);
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            String[] words = entry.getValue().split("-");
            int vitri = Integer.valueOf(words[0]);
            int sotiet = Integer.valueOf(words[1]);
            int x = vitri / 60;
            int y = vitri - x * 60 - 1;
            //Trừ 1 điểm: 1 môn học ở 2 phòng khác nhau
            for (int i = 0; i < sotiet; i++) {
                try {
                    if (!a[x][y + i].equals("0")) {
                        a[x][y + i] = a[x][y + i] + "-" + entry.getKey();
                    } else {
                        a[x][y + i] = String.valueOf(entry.getKey());
                    }
                } catch (Exception e) {
                    
                    diem--;
                }
            //Trừ 1 điểm: 1 môn học ở 2 thứ khác nhau     
            }
            for (int i = 1; i <= sotiet; i++) {
                if ((y + 1) % 10 == 0 || (y+i+1) % 10 == 0) {
                   
                    diem--;
                }
            }

        };
        //Trừ 1 điểm một giờ dạy quá 1 môn;
        for (int i = 0; i < sophong; i++) {
            for (int j = 0; j < 60; j++) {
                if (a[i][j].contains("-")) {
                    
                    diem--;
                }
            }
        }
        //                System.out.println("Trừ 1 điểm vì số chỗ ngồi phòng " + (x + 1) + " có:" + sochongoicuaphonghoc.get(x + 1)
//                        + " chỗ ngồi không đủ cho môn học  " + entry.getKey()
//                        + " với " + soSVcuamonhoc.get(entry.getKey()) + " SV");
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            String[] words = entry.getValue().split("-");
            int vitri = Integer.valueOf(words[0]);
            int x = vitri / 60;
            if (QuanThe.sochongoicuaphonghoc.get(x + 1) < QuanThe.soSVcuamonhoc.get(entry.getKey())) {
                diem--;
            }
        }
        return diem;
    }
}
