import java.lang.Math;
import java.util.*;

public class InterpolasiPolinom {
    /* Problem Interpolasi Polinom

    ISTILAH
    Tipe Integer
    n                       : derajat polinomial yang akan diinterpolasi
    Tipe Double             
    a                       : nilai x yang akan diinterpolasi
    Tipe Matriks
    stdInput ((n + 2) x 2)  : standar masukan pengguna, baris 0..n berisi titik masukan, baris n + 1 berisi nilai x yang akan diinterpolasi
    x ((n + 1) x 1)         : kumpulan nilai absis dari titik masukan pengguna
    fx ((n + 1) x 1)        : kumpulan nilai ordinat dari titik masukan pengguna
    xi ((n + 1) x (n + 1))  : matriks yang berisi xi^0 xi^1 ... xi^n, dengan 0 <= i <= n
    ai ((n + 1) x 1)        : koefisien dari polinomial
    Ilustrasi
    stdInput = x0  y0      x = x0      fx = y0      xi = x0^0 x0^1 ... x0^n      ai = a0
               x1  y1          x1           y1           x1^0 x1^1 ... x1^n           a1
               ... ...         ...          ...          ...  ...  ... ...            ...
               xn  yn          xn           yn           xn^0 xn^1 ... xn^n           an
               a   *           
    
    METODE
    Gauss, dengan matriks augmented xi | fx (dari persamaan: fx = xi x ai) */
    
    /* INPUT */
    /* Input dari Keyboard */
    static Scanner in = new Scanner (System.in);

    static matriks stdInputKeyboard() {
        /* Mengambil masukan dari keyboard, menghasilkan matriks stdInput */
        int n;
        System.out.print("Masukkan derajat polinom (n): ");
        n = in.nextInt();

        matriks stdInput = new matriks();
        stdInput.jumlahBaris = n + 2;
        stdInput.jumlahKolom = 2;

        for (int i = 0; i < stdInput.jumlahBaris; i++) {
            for (int j = 0; j < stdInput.jumlahKolom; j++) {
                if (i != stdInput.jumlahBaris - 1) {
                    if (j == 0) {
                        System.out.print("Masukkan nilai x titik ke-" + i + ": ");
                    } else {
                        System.out.print("Masukkan nilai y titik ke-" + i + ": ");
                    }
                    stdInput.Mat[i][j] = in.nextDouble();
                } else {
                    if (j != stdInput.jumlahKolom - 1) {
                        System.out.print("Masukkan nilai x yang akan diinterpolasi: ");
                        stdInput.Mat[i][j] = in.nextDouble();
                    }
                }
            }
        }
        stdInput.Mat[stdInput.jumlahBaris - 1][stdInput.jumlahKolom - 1] = -999.0;
        
        return stdInput;
    }
    
    /* Input dari File */
    /* Cukup menggunakan bacaFileMatriksBolong(namafile, 1) untuk mendapatkan stdInput, deklarasikan di main */

    /* INPUT EXTRACTING */
    /* I.S. stdInput sudah dideklarasikan
       F.S. dari stdInput, diekstrak matriks x, y, dan nilai a */

    static matriks x (matriks stdInput) {
        /* Mengekstrak matriks x dari matriks stdInput */
        matriks x = new matriks();
        x.jumlahBaris = stdInput.jumlahBaris - 1;
        x.jumlahKolom = 1;

        for (int i = 0; i < x.jumlahBaris; i++) {
            x.Mat[i][0] = stdInput.Mat[i][0];
        }

        return x;
    }

    static matriks fx (matriks stdInput) {
        /* Mengekstrak matriks fx dari matriks stdInput */
        matriks fx = new matriks();
        fx.jumlahBaris = stdInput.jumlahBaris - 1;
        fx.jumlahKolom = 1;

        for (int i = 0; i < fx.jumlahBaris; i++) {
            fx.Mat[i][0] = stdInput.Mat[i][1];
        }
        return fx;
    }

    static double a (matriks stdInput) {
        /* Mengekstrak nilai a dari matriks stdInput */
        return stdInput.Mat[stdInput.jumlahBaris - 1][0];
    }

    /* MATH */
    static matriks xi (matriks x) {
        /* Membuat matriks xi dari matriks x */
        matriks xi = new matriks();
        xi.jumlahBaris = x.jumlahBaris;
        xi.jumlahKolom = x.jumlahBaris;

        for (int i = 0; i < xi.jumlahBaris; i++) {
            for (int j = 0; j < xi.jumlahKolom; j++) {
                xi.Mat[i][j] = Math.pow(x.Mat[i][0], j);
            }
        }

        return xi;
    }

    static matriks ai (matriks xi, matriks fx) {
        /* Membuat matriks ai dengan metode gauss dari matriks augmented xi|fx */ 
        matriks ai = new matriks();
        ai.jumlahBaris = fx.jumlahBaris;
        ai.jumlahKolom = 1;
        
        matriks augmented = operasiMatriks.concatKolom(xi, fx);
        matriks gaussed = operasiMatriks.gauss(augmented);
        
        double cache;
        
        for (int i = 0; i < gaussed.jumlahKolom - 1; i++){
            ai.Mat[i][0] = 0;
        }

        for (int i = gaussed.jumlahBaris - 1; i >= 0; i--){
            cache = gaussed.Mat[i][gaussed.jumlahKolom-1];
            for (int j = i; j < gaussed.jumlahKolom-1; j++){
                cache -= ai.Mat[j][0] * gaussed.Mat[i][j];
            }
            ai.Mat[i][0] = cache;
        }

        return ai;
    }

    static double fa (matriks ai, double a) {
        /* Menghasilkan f(a) */
        double fa;
        int baris;

        fa = 0;
        baris = 0;
        for (int i = 0; i < ai.jumlahBaris ; i++) {
            fa += ai.Mat[baris][0] * Math.pow(a, i);
            baris++;
        }

        return fa;
    }

    /* PRINTING */
    static void printFx(matriks ai) {
        /* Print f(x), contohnya f(x) = -0.0064x^2 + 0.2266x - 0.6762, dengan beberapa aturan.
        Aturan untuk koefisien:
        - Jika semua koefisiennya bernilai nol, maka ditulis f(x) = 0
        - Jika koefisien dari suatu suku bernilai 0, sukunya tidak ditulis
        - Jika koefisien dari suatu suku bernilai positif, digunakan tanda +, jika negatif, digunakan tanda -
        - Jika koefisien bernilai 1 atau -1, koefisien tidak ditulis, kecuali jika koefisien tersebut adalah konstanta dari f(x)
        Aturan untuk x^:
        - Jika derajat x dari suatu suku bernilai 1, pangkat tidak ditulis
        - Jika derajat x dari suatu suku bernilai 0, x dan pangkatnya tidak ditulis */

        System.out.print("f(x) =");

        int lastNonZeroIdx;
        boolean found;

        if (ai.isAllZero()) {
            /* Jika semua koefisiennya bernilai nol, maka ditulis f(x) = 0 */
            System.out.print(" 0");
        } else {
            /* Mencari indeks baris terakhir di matriks ai yang tidak bernilai nol */
            lastNonZeroIdx = ai.jumlahBaris - 1;
            found = false;
            for (int a = lastNonZeroIdx; a >= 0 && !found; a--) {
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
                    if (lastNonZeroIdx == 0) {
                        System.out.print(" " + ai.Mat[lastNonZeroIdx][0]);
                    } else {
                        System.out.print(" ");
                    }
                }
            } else {
                if (ai.Mat[lastNonZeroIdx][0] != -1) {
                    System.out.print(" - " + (-1) * ai.Mat[lastNonZeroIdx][0]);
                } else {
                    if (lastNonZeroIdx == 0) {
                        System.out.print(" - " + (-1) * ai.Mat[lastNonZeroIdx][0]);
                    } else {
                        System.out.print(" - ");
                    }
                }    
            }
            
            /* Print x^ dari suku pertama */
            if (lastNonZeroIdx == 1) {
                System.out.print("x");
            } else if (lastNonZeroIdx != 0) {
                System.out.print("x^" + lastNonZeroIdx);
            }

            /* Print suku-suku selanjutnya */
            for (int i = lastNonZeroIdx - 1; i >= 0; i--) {
                if (ai.Mat[i][0] != 0) {
                    /* Print koefisien dari suku */
                    if (ai.Mat[i][0] > 0) {
                        if (ai.Mat[i][0] != 1) {
                            System.out.print(" + " + ai.Mat[i][0]);
                        } else {
                            if (i == 0) {
                                System.out.print(" + " + ai.Mat[i][0]);
                            } else {
                                System.out.print(" + ");
                            }
                        }
                    } else {
                        if (ai.Mat[i][0] != -1) {
                            System.out.print(" - " + (-1) * ai.Mat[i][0]);
                        } else {
                            if (i == 0) {
                                System.out.print(" - " + (-1) * ai.Mat[i][0]);
                            } else {
                                System.out.print(" - ");
                            }
                        }
                    }

                    /* Print x^ dari suku-suku selanjutnya */
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
}