// Import
import java.util.*;

import src.matriks;
import src.ImageUpsc;
import src.ImageUtil;

public class basreng {
    public static void main(String[] args) {

        Scanner in = new Scanner (System.in);

        // Method
        matriks M = new matriks();
        M.bacaMatriks(4, 3);
        M.writeMatrixFile(M);

        in.close();
    }
}