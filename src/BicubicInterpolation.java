package src;

import java.lang.Math;
import java.util.*;
import java.io.*;

public class BicubicInterpolation {
    /* Problem Bicubic Interpolation (Acuan: Halaman 3)

    ISTILAH
    Tipe Double
    a, b            : nilai x, y yang akan diinterpolasi (dihitung dalam f(a, b)), batasan 0 <= a,b <= 1
    Tipe Matriks
    stdInput (5 x 4): bentuk standar masukan pengguna, baris 0..3 berisi nilai f(x,y), baris 4 berisi nilai x dan y yang akan diinterpolasi
    fxy4x4 (4 x 4)  : berisi nilai f(x, y), -1 <= x,y <= 2
    fxy (16 x 1)    : berisi nilai f(x, y), -1 <= x,y <= 2, namun memanjang ke bawah
    xiyj (16 x 16)  : berisi nilai x^i y^j , 0 <= i,j <= 3
    aij (16 x 1)    : berisi koefisien dari x^i y^j , 0 <= i,j <= 3
    Ilustrasi
    stdInput = f(-1,-1) f(-1,0) f(-1,1) f(-1,2)      fxy4x4 = f(-1,-1) f(0,-1) f(1,-1) f(2,-1)      fxy = f(-1,-1)      xiyj = 1 -1 ... -1  1      aij = a00
               f(0,-1)  f(0,0)  f(0,1)  f(0,2)                f(-1,0)  f(0,0)  f(1,0)  f(2,0)             f(0,-1)              1  0 ...  0  0            a10
               f(1,-1)  f(1,0)  f(1,1)  f(1,2)                f(-1,1)  f(0,1)  f(1,1)  f(2,1)             ...                  ...  ...   ...            ...
               f(2,-1)  f(2,0)  f(2,1)  f(2,2)                f(-1,2)  f(0,2)  f(1,2)  f(2,2)             f(1,2)               1  1 ...  8  8            a23
               a        b       *       *                                                                 f(2,2)               1  2 ... 32 64            a33

    METODE
    Metode inverse matriks dengan persamaan:
    fxy = xiyj x aij <=> aij = inverse(xiyj) x fxy */

    /* INPUT EXTRACTING
       I.S. stdInput sudah dideklarasikan dan merupakan hasil dari bacaFileMatriksBolong(namafile, 2) 
       F.S. dari stdInput, diekstrak matriks fxy4x4, nilai a, dan b */
    
    static Scanner in = new Scanner (System.in);

    public static double a (matriks stdInput) {
        /* Mengekstrak nilai a matriks stdInput */
        return stdInput.Mat[4][0];
    }

    public static double b (matriks stdInput) {
        /* Mengekstrak nilai b matriks stdInput */
        return stdInput.Mat[4][1];
    }
    
    public static matriks fxy4x4 (matriks stdInput) {
        /* Mengekstrak matriks fxy4x4 dari matriks stdInput */
        return operasiMatriks.transpose(operasiMatriks.sliceLastRow(stdInput));
    }
    
    public static matriks fxy(matriks fxy4x4) {
        /* Membuat matriks fxy dari matriks fxy4x4 */
        matriks fxy = new matriks();
        fxy.jumlahBaris = 16;
        fxy.jumlahKolom = 1;

        int baris = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fxy.Mat[baris][0] = fxy4x4.Mat[i][j];
                baris++;
            }
        }
        return fxy;
    }

    /* MATH */

    public static matriks xiyj () {
        /* Membuat matriks xiyj */
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
    
    public static matriks aij (matriks fxy) {
        /* Mencari matriks aij dari matriks xiji dan fxy dengan metode inverse */
        return operasiMatriks.perkalianMatriks(operasiMatriks.invIdentitas(xiyj()), fxy);
    }

    public static double bicIntpol (matriks aij, double a, double b) {
        /* Menghasilkan nilai f(a,b) */ 
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

    /* OUTPUT */
    public static void BIFile(matriks aij, double a, double b) {
        // Kamus Lokal
        String filename;

        // Algoritma
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write
            bw.write("Hasil Perhitungan Bicubic Interpolation");
            bw.newLine();
            bw.write(("f("+ a + ", " + b + ") = " + bicIntpol(aij, a, b)));
            
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}