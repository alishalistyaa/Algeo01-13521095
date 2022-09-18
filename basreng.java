// Import
import java.util.*;

public class basreng {
    public static void main(String[] args) {
        // Atribut
        Scanner in = new Scanner (System.in);
        int a , b;
        double det;

        // Method
        matriks M2 = new matriks();
        matriks M3 = new matriks();

        a = in.nextInt();
        b = in.nextInt();

        M2.bacaMatriks(a , b);
        M2.tulisMatriks();
        System.out.print("\n");

        a = in.nextInt();
        b = in.nextInt();

        M3.bacaMatriks(a , b);
        M3.tulisMatriks();
        System.out.print("\n");

        M3 = operasiMatriks.perkalianMatriks(M2, M3);
        M3.tulisMatriks();
        System.out.print("\n");


        // Menutup scanner
        in.close();
    }
}