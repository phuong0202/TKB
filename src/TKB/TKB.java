/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import static TKB.QuanThe.Cathe;
import static TKB.QuanThe.xacsuat;
import java.util.Random;

/**
 *
 * @author thanh
 */
public class TKB {

    public static int xacsuatlaighep = 80;
    public static int xacsuatdobien = 3;
    public static boolean co = false;
    public static void tkb() {
        Random generator = new Random();
        int sllg = 0, sldb = 0, vonglap = 0;
        QuanThe.TaoQuanThe();
        ChonLoc.diemcathe();
        if (ChonLoc.sapxep() == false) {
            System.out.println("Quần thể ban đầu không có cá thể nào có độ thích nghi là 10đ");
            System.out.println("Tiến hành chạy thuật toán di truyền");
            while (co == false) {
                int choncathe1 = generator.nextInt(Cathe.size()) + 1;
                System.out.println("Chọn ngẫu nhiên cá thể " + choncathe1 + " có xác suất: " + xacsuat.get(choncathe1) + "%");
                if (xacsuat.get(choncathe1) < xacsuatdobien) {
                    System.out.println("Tiến hành đột biến");
                    DotBien.dotbien(choncathe1);
                    sldb++;
                }
                if (xacsuat.get(choncathe1) < xacsuatlaighep && xacsuatdobien < xacsuat.get(choncathe1)) {
                    System.out.println("Tiến hành lai ghép");
                    boolean co1 = true;
                    while (co1) {
                        int choncathe2 = generator.nextInt(Cathe.size()) + 1;
                        if (xacsuat.get(choncathe2) < xacsuatlaighep && choncathe2 != choncathe1) {
                            LaiGhep.laighep(choncathe1, choncathe2);
                            sllg++;
                            co1 = false;
                        }
                    }
                }
                vonglap++;
            }
            XepTKB.jLabel5.setText(String.valueOf(vonglap));
            XepTKB.jLabel6.setText(String.valueOf(sllg));
            XepTKB.jLabel7.setText(String.valueOf(sldb));
            XepTKB.jLabel9.setText(String.valueOf(Cathe.size()));
            System.out.println("Kết thúc quá trình xếp thời khóa biểu với " + vonglap + " lần chạy trong đó có: " + sllg + " lần lai ghép và " + sldb + " lần đột biến");
            System.out.println("Danh sách các cá thể" + Cathe);
            ChonLoc.diemcathe();
            ChonLoc.sapxep();
        } else {
            System.out.println("Quần thể ban đầu có cá thể đạt yêu cầu");
            System.out.println("Danh sách các cá thể" + Cathe);
           
        }
    }
}
