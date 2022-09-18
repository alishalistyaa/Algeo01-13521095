// Import
import java.util.*;

public class basreng {
    public static void main(String[] args) {
        // Atribut
        Scanner in = new Scanner (System.in);
        int a , b;
        double det, detExCof;

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

        detExCof = operasiMatriks.detExCofRow0(M2);
        System.out.println(detExCof);

        det = operasiMatriks.determinan(M2);
        System.out.print("\n");
        System.out.print(det);

        // Menutup scanner
        in.close();
    }
}