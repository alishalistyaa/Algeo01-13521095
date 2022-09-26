// Import
import java.util.*;

public class basreng {
    public static void main(String[] args) {
        // Atribut
        Scanner in = new Scanner (System.in);
        int a , b, x;

        // Method
        matriks M2 = new matriks();

        a = in.nextInt();
        b = in.nextInt();

        M2.bacaMatriks(a , b);
        M2.tulisMatriks();
        System.out.print("\n");

        M2 = operasiMatriks.gauss(M2);
        M2.tulisMatriks();
        System.out.print("\n");

        SPL.solveSPL(M2);

        x = SPL.checkSPL(M2);
        System.out.println(x);

        // Menutup scanner
        in.close();
    }
}