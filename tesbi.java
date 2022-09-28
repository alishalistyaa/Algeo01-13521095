import java.util.*;
public class tesbi {
    public static void main(String[] args) {

        // Method
        matriks M = new matriks();
        M.bacaMatriks(4, 4);
        M.tulisMatriks();
        System.out.print("\n");
        matriks fxy = BicubicInterpolation.fxy(M);
        fxy.tulisMatriks();
        System.out.print("\n");
        matriks xiyj = BicubicInterpolation.xiyj();
        xiyj.tulisMatriks();
        System.out.print("\n INV ADJ \n");
        matriks invxiyj1 = operasiMatriks.invAdj(xiyj);
        invxiyj1.tulisMatriks();
        matriks hasil1 = operasiMatriks.perkalianMatriks(invxiyj1, fxy);
        hasil1.tulisMatriks();
        System.out.print("\n INV IDENTITAS \n");
        matriks invxiyj2 = operasiMatriks.invIdentitas(xiyj);
        invxiyj2.tulisMatriks();
        matriks hasil2 = operasiMatriks.perkalianMatriks(invxiyj2, fxy);
        hasil2.tulisMatriks();
        System.out.print("\n");
        matriks aij = BicubicInterpolation.aij(fxy);
        aij.tulisMatriks();
        System.out.print("\n");
        System.out.print("x = 0, y = 0\n");
        System.out.println(BicubicInterpolation.bicIntpol(aij, 0, 0));
        System.out.print("x = 0.5, y = 0.5\n");
        System.out.println(BicubicInterpolation.bicIntpol(aij, 0.5, 0.5));
        System.out.print("x = 0.25, y = 0.75\n");
        System.out.println(BicubicInterpolation.bicIntpol(aij, 0.25, 0.75));
        System.out.print("x = 0.1, y = 0.9\n");
        System.out.println(BicubicInterpolation.bicIntpol(aij, 0.1, 0.9));
    }
}
