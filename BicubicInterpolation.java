import java.lang.Math;

public class BicubicInterpolation {
    /* Problem Bicubic Interpolation (Acuan: Halaman 3 pdf tubes)
    Persamaannya matriks fxy = matriks XiYj x matriks aij <=> matriks aij = inverse(matriks XiYj) x matriks fxy
    matriks aij dipake lagi buat ngitung f(a,b)

    Yang belom: 
    - Ngambil inputan matriks fxy, nilai a, b dari file
    - Nyesuain urutan f(x,y) sama yang di halaman 3 karena urutannya agak beda sama yang di halaman 3 jadi harus disesuain lagi */ 

    static matriks XiYj () {
        // Membuat matriks XiYj berdimensi 16 x 16
        matriks XiYj = new matriks();
        XiYj.jumlahBaris = 16;
        XiYj.jumlahKolom = 16;
        
        int y, x, i, j, baris, kolom;
        baris = 0;
        for (y = -1; y <= 2; y++){
            for (x = -1; x <= 2; x++) {
                kolom = 0;
                for (j = 0; j <= 3; j++) {
                    for (i = 0; i <= 3; i++) {
                        XiYj.Mat[baris][kolom] = Math.pow (x, i) * Math.pow (y, j);
                        kolom++;
                    }
                }
            baris++;
            }
        }
        return XiYj;
    }
    
    static matriks aij (matriks fxy, matriks XiYj) {
        // Membuat matriks aij berdimensi 16 x 1
        return operasiMatriks.perkalianMatriks(operasiMatriks.invAdj(XiYj), fxy);
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