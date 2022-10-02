
import src.InterpolasiPolinom;

import src.matriks;


public class tesip {
    public static void main(String[] args) {
        matriks x = new matriks();
        x.bacaMatriks(10, 1);
        x.tulisMatriks();
        System.out.print("\n");
        matriks fx = new matriks();
        fx.bacaMatriks(10, 1);
        fx.tulisMatriks();
        System.out.print("\n");
        matriks xi = InterpolasiPolinom.xi(x);
        xi.tulisMatriks();
        System.out.print("\n");
        matriks ai = InterpolasiPolinom.ai(xi, fx);
        ai.tulisMatriks();
        System.out.print("\n");
        InterpolasiPolinom.printFx(ai);
        System.out.print("\n f(16/07/2022) = " + InterpolasiPolinom.fa(ai, (double) (7 + ((double) 16)/ ((double) 31))));
        System.out.print("\n f(10/08/2022) = " + InterpolasiPolinom.fa(ai, (double) (8 + ((double) 10)/ ((double) 31))));
        System.out.print("\n f(05/09/2022) = " + InterpolasiPolinom.fa(ai, (double) (9 + ((double) 5)/ ((double) 30))) + "\n");
        // System.out.print("\n f(1.28) = " + InterpolasiPolinom.fa(ai, 1.28) + "\n");
    }
}
