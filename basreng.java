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
        M3 = operasiMatriks.gaussJordan(M2);
        M3.tulisMatriks();

        det = operasiMatriks.determinan(M2);
        System.out.print(det);


        // Menutup scanner
        in.close();
    }
}