/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TKB;

import static TKB.QuanThe.Cathe;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 *
 * @author thanh
 */
public class LaiGhep {
    public static void laighep(int cathe1, int cathe2) {
        Random generator = new Random();
        System.out.println("Chọn ngẫu nhiên cá thể " + (cathe1) + " để lai ghép : " + Cathe.get(cathe1-1));
        System.out.println("Chọn ngẫu nhiên cá thể " + (cathe2) + " để lai ghép: " + Cathe.get(cathe2-1));
        int diemlaighep = generator.nextInt(Cathe.get(cathe1-1).size() - 1) + 1;
        System.out.println("Điểm lai ghép: " + diemlaighep);
        HashMap<Integer, String> monhoc1 = new HashMap<>();
        for (int j = 1; j <= diemlaighep; j++) {
            monhoc1.put(j, (String) Cathe.get(cathe1-1).get(j));
        }
        for (int j = diemlaighep + 1; j <= Cathe.get(cathe2-1).size(); j++) {
            monhoc1.put(j, (String) Cathe.get(cathe2-1).get(j));
        }
        System.out.println("Cá thể con 1:");
        for (Map.Entry<Integer, String> entry : monhoc1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        Cathe.add(monhoc1);
        HashMap<Integer, String> monhoc2 = new HashMap<>();
        for (int j = 1; j <= diemlaighep; j++) {
            monhoc2.put(j, (String) Cathe.get(cathe2-1).get(j));
        }
        for (int j = diemlaighep + 1; j <= Cathe.get(cathe1-1).size(); j++) {
            monhoc2.put(j, (String) Cathe.get(cathe1-1).get(j));
        }
        System.out.println("Cá thể con 2:");
        for (Map.Entry<Integer, String> entry : monhoc2.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        Cathe.add(monhoc2);
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể cha");
        int diem1=DanhGiaCaThe.danhgiacathe(cathe1-1);
        System.out.println("Độ thích nghi của cá thể cha: "+diem1);
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể mẹ");
        int diem2=DanhGiaCaThe.danhgiacathe(cathe2-1);
        System.out.println("Độ thích nghi của cá thể mẹ: "+diem2);
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể con 1");
        int diem3=DanhGiaCaThe.danhgiacathe(Cathe.size()-2);
        System.out.println("Độ thích nghi của cá thể con 1: "+diem3);
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể con 2");
        int diem4=DanhGiaCaThe.danhgiacathe(Cathe.size()-1);
        System.out.println("Độ thích nghi của cá thể con 2: "+diem4);
        if(diem3==10 || diem4==10)
        {
            TKB.co=true;
        }
        else
        {
            TKB.co=false;
        }
        if(diem3>diem1)
        {
            System.out.println("Loại bỏ cá thể "+(cathe1));
            Cathe.add(cathe1-1,monhoc1);
            Cathe.remove(cathe1);
            Cathe.remove(Cathe.size()-2);
        }
        else
        {
            System.out.println("Loại bỏ cá thể "+Cathe.size());
            Cathe.remove(Cathe.size()-2);
        }
        if(diem4>diem2)
        {
            System.out.println("Loại bỏ cá thể "+(cathe2));
            Cathe.add(cathe2-1,monhoc2);
            Cathe.remove(cathe2);
            Cathe.remove(Cathe.size()-1);
        }
        else
        {
            System.out.println("Loại bỏ cá thể "+Cathe.size());
            Cathe.remove(Cathe.size()-1);
        }
    }
}
