
import src.RegresiLinierBerganda;
import src.matriks;
import src.operasiMatriks;

public class tesreg {
    public static void main(String[] args) {
        matriks xnk = new matriks();
        xnk.bacaMatriks(20, 4);
        xnk.tulisMatriks();
        System.out.print("\n");
        matriks y = new matriks();
        y.bacaMatriks(20, 1);
        y.tulisMatriks();
        System.out.print("\n");
        matriks xnktrans = operasiMatriks.transpose(xnk);
        xnktrans.tulisMatriks();
        System.out.print("\n");
        matriks xtx = operasiMatriks.perkalianMatriks(xnktrans, xnk);
        xtx.tulisMatriks();
        System.out.print("\n");
        matriks xty = operasiMatriks.perkalianMatriks(xnktrans, y);
        xty.tulisMatriks();
        System.out.print("\n");
        (operasiMatriks.concatKolom(operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnk), xnk),operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnk), y))).tulisMatriks();
        System.out.print("\n");
        matriks b = RegresiLinierBerganda.b(xnk, y);
        b.tulisMatriks();
        System.out.print("\n");
        System.out.println("F(xk) = ");
        RegresiLinierBerganda.printFxk(b);
        matriks xk = new matriks();
        xk.bacaMatriks(1, 3);
        System.out.println(RegresiLinierBerganda.fxk(xk, b));
    }
}
