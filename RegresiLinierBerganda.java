public class RegresiLinierBerganda {
    /* Problem Regresi Linear Berganda
    Persamaannya (transpose(matriks xnk)) x (matriks xnk) x (matriks b) = (transpose(matriks xnk)) x (matriks y)
    Keterangan: matriks y berdimensi n x 1, matriks xnk berdimensi n x (k + 1), matriks b berdimensi (k + 1) x 1
        y = y1          xnk = 1  x11  x12  ...  x1k             b = b0
            y2                1  x21  x22  ...  x2k                 b1
            ...               ...          ...  ...                 ...
            yn                1  xn1  xn2  ...  xnk                 bk
    
    METODE PENCARIAN matriks b: GAUSS
    Matriks b dipake lagi buat ngitung f(x1, x2, ..., xk)
    
    Yang belom:
    - Ngambil inputan
    - Split inputan jadi 2 matriks, matriks xnk sama matriks y */

    static matriks b (matriks xnk, matriks y) {
        matriks b = new matriks();
        b.jumlahBaris = xnk.jumlahKolom;
        b.jumlahKolom = 1;
        matriks augmented = operasiMatriks.concatKolom(operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnk), xnk),operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnk), y));
        matriks gaussed = operasiMatriks.gauss(augmented);
        
        int i, j;
        double cache;
        
        for(i = 0; i < gaussed.jumlahKolom - 1; i++){
            b.Mat[i][0] = 0;
        }

        for(i = gaussed.jumlahBaris - 1; i >= 0; i--){

            cache = gaussed.Mat[i][gaussed.jumlahKolom-1];
            for(j = i; j < gaussed.jumlahKolom-1; j++){
                cache -= b.Mat[j][0] * gaussed.Mat[i][j];
            }

            b.Mat[i][0] = cache;
        }

        return b;
    }
    static void printFxk(matriks b) {
        /* Print Fxk, contohnya f(x) = -9.5872 + 1.0732x1
        Beberapa constraint untuk koefisien:
        - Kalo semua koefisiennya bernilai nol, maka ditulis f(x) = 0
        - Kalo koefisien dari suatu suku bernilai 0, sukunya ga ditulis
        - Kalo koefisien dari suatu suku bernilai positif, digunakan tanda +, kalau negatif, digunakan tanda -
        - Kalo koefisien bernilai 1 atau -1, koefisien tidak ditulis
        Untuk xk:
        - Kalo k dari suatu suku bernilai 0, xk tidak ditulis
        */

        System.out.print("f(x) =");

        int a, i, firstNonZeroIdx;
        boolean found;

        if (b.isAllZero()) {
            /* Kalo semua koefisiennya bernilai nol, maka ditulis f(x) = 0 */
            System.out.print(" 0");
        } else {
            /* Mencari indeks baris pertama di matriks ai yang tidak bernilai nol */
            firstNonZeroIdx = 0;
            found = false;
            for (a = firstNonZeroIdx; a <= b.jumlahBaris - 1 && !found; a++) {
                if (b.Mat[a][0] != 0) {
                    found = true;
                    firstNonZeroIdx = a;
                }
            }

            /* Print koefisien dari suku pertama */
            if (b.Mat[firstNonZeroIdx][0] > 0) {
                if (b.Mat[firstNonZeroIdx][0] != 1) {
                    System.out.print(" " + b.Mat[firstNonZeroIdx][0]);
                } else {
                    if (firstNonZeroIdx == 0) {
                        System.out.print(" " + b.Mat[firstNonZeroIdx][0]);
                    } else {
                        System.out.print(" ");
                    }
                }
            } else {
                if (b.Mat[firstNonZeroIdx][0] != -1) {
                    System.out.print(" - " + (-1) * b.Mat[firstNonZeroIdx][0]);
                } else {
                    if (firstNonZeroIdx == 0) {
                        System.out.print(" - " + (-1) * b.Mat[firstNonZeroIdx][0]);
                    } else {
                        System.out.print(" - ");
                    }
                }    
            }
            
            /* Print xk dari suku pertama */
            if (firstNonZeroIdx != 0) {
                System.out.print("x" + firstNonZeroIdx);
            }

            /* Print suku-suku selanjutnya */
            for (i = firstNonZeroIdx + 1; i <= b.jumlahBaris - 1; i++) {
                if (b.Mat[i][0] != 0) {
                    /* Print koefisien dari suku */
                    if (b.Mat[i][0] > 0) {
                        if (b.Mat[i][0] != 1) {
                            System.out.print(" + " + b.Mat[i][0]);
                        } else {
                            if (i == 0) {
                                System.out.print(" + " + b.Mat[i][0]);
                            } else {
                                System.out.print(" + ");
                            }
                        }
                    } else {
                        if (b.Mat[i][0] != -1) {
                            System.out.print(" - " + (-1) * b.Mat[i][0]);
                        } else {
                            if (i == 0) {
                                System.out.print(" - " + (-1) * b.Mat[i][0]);
                            } else {
                                System.out.print(" - ");
                            }
                        }
                    }

                    /* Print xk dari suku */
                    if (i != 0) {
                        System.out.print("x" + i);
                    }
                }
            }
        }
        System.out.print("\n");  
    }
    static double Fxk(matriks xk, matriks b) {
        // Matriks xk: masukan user, bentuk [x1, x2, ..., xk], ukuran 1 x k
        /* Matriks b: dari perhitungan sebelumnya, bentuk b0 , ukuran (k + 1) x k
                                                          b1
                                                          ...
                                                          bk
        */ 
        int i;
        double hasil;
        hasil = b.Mat[0][0];
        for (i = 0; i < xk.jumlahKolom; i++) {
            hasil += xk.Mat[0][i] * b.Mat[i + 1][0];
        }
        return hasil;
    }
}
