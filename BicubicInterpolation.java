import java.lang.Math;

public class BicubicInterpolation {
    /* Problem Bicubic Interpolation (Acuan: Halaman 3 pdf tubes)
    Persamaannya matriks fxy = matriks xiyj x matriks aij <=> matriks aij = inverse(matriks xiyj) x matriks fxy
    
    METODE PENCARIAN matriks aij: inverse
    matriks aij dipake lagi buat ngitung f(a,b)

    /* INPUT HANDLING */
    static double getA (matriks input) {
        return input.Mat[4][0];
    }

    static double getB (matriks input) {
        return input.Mat[4][1];
    }
    
    static matriks stdInput (matriks input) {
        return operasiMatriks.transpose(operasiMatriks.sliceLastRow(input));
    }
    
    /* PROCESS */
    static matriks fxy(matriks stdInput) {
        matriks fxy = new matriks();
        fxy.jumlahBaris = 16;
        fxy.jumlahKolom = 1;
        int baris = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fxy.Mat[baris][0] = stdInput.Mat[i][j];
                baris++;
            }
        }
        return fxy;
    }

    static matriks xiyj () {
        // Membuat matriks xiyj berdimensi 16 x 16
        matriks xiyj = new matriks();
        xiyj.jumlahBaris = 16;
        xiyj.jumlahKolom = 16;
        
        int y, x, i, j, baris, kolom;
        baris = 0;
        for (y = -1; y <= 2; y++){
            for (x = -1; x <= 2; x++) {
                kolom = 0;
                for (j = 0; j <= 3; j++) {
                    for (i = 0; i <= 3; i++) {
                        xiyj.Mat[baris][kolom] = Math.pow (x, i) * Math.pow (y, j);
                        kolom++;
                    }
                }
            baris++;
            }
        }
        return xiyj;
    }
    
    static matriks aij (matriks fxy) {
        // Membuat matriks aij berdimensi 16 x 1
        return operasiMatriks.perkalianMatriks(operasiMatriks.invIdentitas(xiyj()), fxy);
    }

    static double bicIntpol (matriks aij, double a, double b) {
        // Menghasilkan nilai f(a,b)
        int i, j, baris;
        double fab;
        fab = 0;
        baris = 0;
        for (j = 0; j <= 3; j++) {
            for (i = 0; i <= 3; i++) {
                fab += aij.Mat[baris][0] * Math.pow(a, i) * Math.pow(b, j);
                baris++;
            }
        }
        return fab;
    }
}