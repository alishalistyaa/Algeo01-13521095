import java.lang.Math;

public class InterpolasiPolinom {
    /* Problem Interpolasi Polinom
    Persamaannya matriks fx = matriks xi x matriks ai <=> matriks ai = inverse(matriks xi) x matriks fx
    Keterangan: matriks fx berdimensi (n + 1) x 1, matriks xi berdimensi (n + 1) x (n + 1), matriks ai berdimensi (n + 1) x 1
    Ini aku contohin karna gada di pdfnya ya, misal datanya: n = 2, berarti ada 3 (dari n + 1) titik masukan, misalnya (1,-2), (2,-3), (3,-4), berarti:
    matriks x = 1           matriks fx = -2            matriks xi = 1^0  1^1  1^2           matriks ai = a0   (bakal dicari)
                2                        -3                         2^0  2^1  2^2                        a1
                3                        -4                         3^0  3^1  3^2                        a2  
    
    Matriks ai dipake lagi buat ngitung f(a)

    Yang belom:
    - Ngambil inputan
    - Split inputan jadi 2 matriks, matriks x sama matriks fx */

    static matriks xi (matriks x) {
        /* Menghasilkan matriks xi */
        matriks xi = new matriks();
        xi.jumlahBaris = x.jumlahKolom;
        xi.jumlahKolom = x.jumlahKolom;
        int i, j;
        for (i = 0; i < xi.jumlahBaris; i++) {
            for (j = 0; j < xi.jumlahKolom; j++) {
                xi.Mat[i][j] = Math.pow(x.Mat[i][0], j);
            }
        }
        return xi;
    }

    static matriks ai (matriks fx, matriks xi) {
        /* Membuat matriks ai */ 
        return operasiMatriks.perkalianMatriks(operasiMatriks.invAdj(xi), fx);
    }

    void printFx(matriks ai) {
        /* Ngeprint fx, contohnya f(x) = -0.0064x^2 + 0.2266x - 0.6762  
        Beberapa constraint untuk koefisien:
        - Kalo semua koefisiennya bernilai nol, maka ditulis f(x) = 0
        - Kalo koefisien dari suatu suku bernilai 0, sukunya ga ditulis
        - Kalo koefisien dari suatu suku bernilai positif, digunakan tanda +, kalau negatif, digunakan tanda -
        - Kalo koefisien bernilai 1 atau -1, koefisien tidak ditulis
        Untuk x^:
        - Kalo derajat x dari suatu suku bernilai 1, ditulis x aja
        - Kalo derajat x dari suatu suku bernilai 0, x tidak ditulis
        */

        System.out.print("f(x) =");

        int a, i, lastNonZeroIdx;
        boolean found;

        if (ai.isAllZero()) {
            /* Kalo semua koefisiennya bernilai nol, maka ditulis f(x) = 0 */
            System.out.print(" 0");
        } else {
            /* Mencari indeks baris terakhir di matriks ai yang tidak bernilai nol */
            lastNonZeroIdx = ai.jumlahBaris - 1;
            found = false;
            for (a = ai.jumlahBaris - 2; a >= 0 && !found; a--) {
                if (ai.Mat[a][0] != 0) {
                    found = true;
                    lastNonZeroIdx = a;
                }
            }

            /* Print koefisien dari suku pertama */
            if (ai.Mat[lastNonZeroIdx][0] > 0) {
                if (ai.Mat[lastNonZeroIdx][0] != 1) {
                    System.out.print(" " + ai.Mat[lastNonZeroIdx][0]);
                } else {
                    System.out.print(" ");
                }
            } else {
                if (ai.Mat[lastNonZeroIdx][0] != -1) {
                    System.out.print(" - " + (-1) * ai.Mat[lastNonZeroIdx][0]);
                } else {
                    System.out.print(" - ");
                }    
            }
            
            /* Print x^ dari suku pertama */
            if (lastNonZeroIdx == 1) {
                System.out.print("x");
            } else if (lastNonZeroIdx != 0) {
                System.out.print("x^" + lastNonZeroIdx);
            }

            /* Print suku-suku selanjutnya */
            for (i = lastNonZeroIdx - 1; i >= 0; i--) {
                if (ai.Mat[i][0] != 0) {
                    /* Print koefisien dari suku */
                    if (ai.Mat[i][0] > 0) {
                        if (ai.Mat[i][0] != 1) {
                            System.out.print(" + " + ai.Mat[i][0]);
                        } else {
                            System.out.print(" + ");
                        }
                    } else {
                        if (ai.Mat[i][0] != -1) {
                            System.out.print(" - " + (-1) * ai.Mat[i][0]);
                        } else {
                            System.out.print(" - ");
                        }
                    }

                    /* Print x^ dari suku */
                    if (i == 1) {
                        System.out.print("x");
                    } else if (i != 0) {
                        System.out.print("x^" + i);
                    }
                }
            }
        }
        System.out.print("\n");        
    } 

    static double fa (matriks ai, double a) {
        /* Menghasilkan f(a) */
        double fa;
        int i, baris;
        fa = 0;
        baris = 0;
        for (i = 0; i < ai.jumlahBaris ; i++) {
            fa += ai.Mat[baris][0] * Math.pow(a, i);
            baris++;
        }
        return fa;
    }
}