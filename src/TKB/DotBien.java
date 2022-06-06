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
public class DotBien {

    public static void dotbien(int cathe) {
        System.out.println("Chọn ngẫu nhiên cá thể " + (cathe) + " để đột biến : " + Cathe.get(cathe-1));
        Random generator = new Random();
        int diemdotbien1 = generator.nextInt(Cathe.get(cathe-1).size())+1;
        int diemdotbien2 = generator.nextInt(Cathe.get(cathe-1).size())+1;
        while (diemdotbien1 == diemdotbien2 ) {
            diemdotbien2 = generator.nextInt(Cathe.get(cathe-1).size()) + 1;
        }
        System.out.println("Chọn ngẫu hiên 2 vị trí hoán vị là: " + (diemdotbien1) + " và " + (diemdotbien2));
        HashMap<Integer, String> monhoc = new HashMap<>();
        for (int i = 1; i <= Cathe.get(cathe-1).size(); i++) {
            if (i == diemdotbien1) {
                monhoc.put(diemdotbien1, (String) Cathe.get(cathe-1).get(diemdotbien2));
            } else {
                if (i == diemdotbien2) {
                    monhoc.put(diemdotbien2, (String) Cathe.get(cathe-1).get(diemdotbien1));
                } else {
                    monhoc.put(i, (String) Cathe.get(cathe-1).get(i));
                }
            }
        }
        Cathe.add(monhoc);
        System.out.println("Cá thể con:");
        for (Map.Entry<Integer, String> entry : monhoc.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể cha/mẹ");
        int diem1=DanhGiaCaThe.danhgiacathe(cathe);
        System.out.println("Độ thích nghi của cá thể cha/mẹ: "+diem1);
        System.out.println("Bắt đầu đánh giá độ thích nghi của cá thể con");
        int diem2=DanhGiaCaThe.danhgiacathe(Cathe.size()-1);
        System.out.println("Độ thích nghi của cá thể con: "+diem2);
        if(diem2==10)
        {
            TKB.co=true;
        }
        else
        {
            TKB.co=false;
        }
        if(diem1>=diem2)
        {
            System.out.println("Loại bỏ cá thể "+Cathe.size());
            Cathe.remove(Cathe.size()-1);
        }
        else
        {
            System.out.println("Loại bỏ cá thể "+cathe);
            Cathe.add(cathe-1,monhoc);
            Cathe.remove(cathe);
            Cathe.remove(Cathe.size()-1);
            
        }

    }
}
