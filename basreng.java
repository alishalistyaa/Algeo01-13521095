// Import
import java.util.*;

import src.matriks;
import src.ImageUpsc;
import src.ImageUtil;

public class basreng {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Scanner in = new Scanner (System.in);

        // Method
        matriks M = new matriks();
        matriks M2 = new matriks();

        M = ImageUtil.loadImage("C:/Users/Muhamad/Desktop/tubes1/Algeo01-13521095/kecilbet.png");

        M2 = ImageUpsc.interpolate2x(M);
        ImageUtil.writeImage("Bwasrweng.png", M2);

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: "
        + elapsedTime/1000000);

        // Menutup scanner
        in.close();
    }
}