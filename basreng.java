// Import
import java.util.*;

public class basreng {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        Scanner in = new Scanner (System.in);
        int a , b;
        double x;

        // Method
        matriks M = new matriks();
        matriks M2 = new matriks();

        M = ScalingImage.loadImage("C:/Users/Muhamad/Desktop/tubes1/Algeo01-13521095/testride.png");

        M2 = ImageUpsc.interpolate2x(M);
        ScalingImage.writeImage("Bwasrweng.png", M2);

        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: "
        + elapsedTime/1000000);

        // Menutup scanner
        in.close();
    }
}