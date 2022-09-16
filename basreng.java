// Import
import java.util.*;

public class basreng {
    public static void main(String[] args) {
        // Atribut
        Scanner in = new Scanner (System.in);


        int a , b;
        // Method
        matriks M = new matriks();
        matriks M2 = new matriks();
        matriks M3 = new matriks();

        a = in.nextInt();
        b = in.nextInt();

        M.bacaMatriks(a , b);
        M2.bacaMatriks(a , b);

        M3.pertambahanMatriks(M, M2);

        M3.tulisMatriks();
    }

}