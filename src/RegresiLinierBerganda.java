
import java.util.*;
import java.io.*; 

public class RegresiLinierBerganda {
    /* Problem Regresi Linear Berganda

    ISTILAH
    Tipe Integer
    n               : jumlah peubah x
    m               : jumlah sampel/observasi
    Tipe Matriks
    stdInput ((m + 1) x (n + 1)) : standar masukan pengguna, baris 0..(m - 1) berisi titik sampel, baris m berisi data yang akan diregresi
    xnm (m x (n + 1))            : data nilai x1, x2, ..., xn untuk setiap sampel, diconcat di depan dengan kolom berisi 1
    ym (m x 1)                   : data nilai y dari titik sampel
    xk (1 x n)                   : data nilai x1, x2, ..., xn yang akan diregresi
    b ((n + 1) x 1)              : koefisien 1, x1, x2, ..., xn
    stdInput = x11  x21  ...  xn1  y1      xnm = 1  x11  x21  ...  xn1      ym = y1      xk = x1  x2 ... xn      b = b0        
               x12  x22  ...  xn2  y2            1  x12  x22  ...  xn2           y2                                  b1
               ...  ...  ...  ...  ...           .. ...  ...  ...  ...           ...                                 ...
               x1m  x2m  ...  xnm  ym            1  x1m  x2m  ...  xnm           ym                                  bn
               x1   x2   ...  xn   *
    
    METODE
    Gauss, dengan matriks augmented (transpose(xnm)) x (xnm) | (transpose(xnm)) x (ym), dari persamaan (transpose(xnm)) x (xnm) x (b) = (transpose(xnm)) x (ym)
    
    /* INPUT */
    /* Input dari Keyboard */
    static Scanner in = new Scanner (System.in);

    public static matriks stdInputKeyboard() {
        /* Mengambil masukan dari keyboard, menghasilkan matriks stdInput */
        int n, m;

        System.out.print("Masukkan jumlah peubah x (n): ");
        n = in.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = in.nextInt();

        matriks stdInput = new matriks();
        stdInput.jumlahBaris = m + 1;
        stdInput.jumlahKolom = n + 1;

        for (int i = 0; i < stdInput.jumlahBaris; i++) {
            for (int j = 0; j < stdInput.jumlahKolom; j++) {
                if (i != stdInput.jumlahBaris - 1) {
                    if (j != stdInput.jumlahKolom - 1) {
                        System.out.print("Masukkan nilai x" + (j + 1) + " sampel ke-" + (i + 1) + ": ");
                    } else {
                        System.out.print("Masukkan nilai y sampel ke-" + (i + 1) + ": ");
                    }
                    stdInput.Mat[i][j] = in.nextDouble();
                } else {
                    if (j != stdInput.jumlahKolom - 1) {
                        System.out.print("Masukkan nilai x" + (j + 1) + " yang akan diregresi: ");
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
       F.S. dari stdInput, diekstrak matriks xnm, ym, dan xk */
    public static matriks xnm(matriks stdInput) {
        /* Mengekstrak matriks xnm dari matriks stdInput */
        matriks ones = new matriks();
        ones.jumlahBaris = stdInput.jumlahBaris - 1;
        ones.jumlahKolom = 1;

        for (int i = 0; i < ones.jumlahBaris; i++) {
            ones.Mat[i][0] = 1;
        }

        matriks xnm = operasiMatriks.sliceLastCol(operasiMatriks.concatKolom(ones, operasiMatriks.sliceLastRow(stdInput)));
        
        return xnm;
    }

    public static matriks ym (matriks stdInput) {
        /* Mengekstrak matriks ym dari matriks stdInput */
        return operasiMatriks.takeLastCol(operasiMatriks.sliceLastRow(stdInput));
    }
    
    public static matriks xk (matriks stdInput) {
        /* Mengekstrak matriks xk dari matriks stdInput */
        return operasiMatriks.takeLastRow(operasiMatriks.sliceLastCol(stdInput));
    }

    /* MATH */
    public static matriks b (matriks xnm, matriks ym) {
        /* Membuat matriks ai dengan metode gauss dari matriks augmented (transpose(xnm)) x (xnm) | (transpose(xnm)) x (ym) */
        matriks b = new matriks();
        b.jumlahBaris = xnm.jumlahKolom;
        b.jumlahKolom = 1;

        matriks augmented = operasiMatriks.concatKolom(operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnm), xnm),operasiMatriks.perkalianMatriks(operasiMatriks.transpose(xnm), ym));
        matriks gaussed = operasiMatriks.gauss(augmented);
        
        double cache;
        
        for(int i = 0; i < gaussed.jumlahKolom - 1; i++){
            b.Mat[i][0] = 0;
        }

        for(int i = gaussed.jumlahBaris - 1; i >= 0; i--){
            cache = gaussed.Mat[i][gaussed.jumlahKolom-1];
            for(int j = i; j < gaussed.jumlahKolom-1; j++){
                cache -= b.Mat[j][0] * gaussed.Mat[i][j];
            }
            b.Mat[i][0] = cache;
        }

        return b;
    }

    public static double fxk (matriks xk, matriks b) {
        /* Menghasilkan f(xk) */
        double hasil;
        hasil = b.Mat[0][0];

        for (int i = 0; i < xk.jumlahKolom; i++) {
            hasil += xk.Mat[0][i] * b.Mat[i + 1][0];
        }

        return hasil;
    }

    /* OUTPUT */
    public static String fxkString (matriks b) {
        /* Menghasilkan string penjabaran f(xk), contohnya "f(xk) = -0.0064 + 0.2266x1 - 0.6762x2", dengan beberapa aturan.
        Aturan untuk koefisien:
        - Jika semua koefisiennya bernilai nol, maka ditulis f(xk) = 0
        - Jika koefisien dari suatu suku bernilai 0, sukunya tidak ditulis
        - Jika koefisien dari suatu suku bernilai positif, digunakan tanda +, jika negatif, digunakan tanda -
        - Jika koefisien bernilai 1 atau -1, koefisien tidak ditulis, kecuali jika koefisien tersebut adalah konstanta dari f(xk) */
        String fxk = "f(xk) =";
        int firstNonZeroIdx;
        boolean found;

        if (b.isAllZero()) {
            /* Jika semua koefisiennya bernilai nol, maka ditulis f(xk) = 0 */
            fxk += " 0";
        } else {
            /* Mencari indeks baris pertama di matriks b yang tidak bernilai nol */
            firstNonZeroIdx = 0;
            found = false;
            for (int a = firstNonZeroIdx; a <= b.jumlahBaris - 1 && !found; a++) {
                if (b.Mat[a][0] != 0) {
                    found = true;
                    firstNonZeroIdx = a;
                }
            }

            /* Add koefisien dari suku pertama */
            if (b.Mat[firstNonZeroIdx][0] > 0) {
                if (b.Mat[firstNonZeroIdx][0] != 1) {
                    fxk += (" " + (b.Mat[firstNonZeroIdx][0]));
                } else {
                    if (firstNonZeroIdx == 0) {
                        fxk += (" " + (b.Mat[firstNonZeroIdx][0]));
                    } else {
                        fxk += (" ");
                    }
                }
            } else {
                if (b.Mat[firstNonZeroIdx][0] != -1) {
                    fxk += (" - " + ((-1) * b.Mat[firstNonZeroIdx][0]));
                } else {
                    if (firstNonZeroIdx == 0) {
                        fxk += (" - " + ((-1) * b.Mat[firstNonZeroIdx][0]));
                    } else {
                        fxk += (" - ");
                    }
                }    
            }
            
            /* Add xk dari suku pertama */
            if (firstNonZeroIdx != 0) {
                fxk += ("x" + (firstNonZeroIdx));
            }

            /* Add suku-suku selanjutnya */
            for (int i = firstNonZeroIdx + 1; i <= b.jumlahBaris - 1; i++) {
                if (b.Mat[i][0] != 0) {
                    /* Print koefisien dari suku */
                    if (b.Mat[i][0] > 0) {
                        if (b.Mat[i][0] != 1) {
                            fxk += (" + " + (b.Mat[i][0]));
                        } else {
                            if (i == 0) {
                                fxk += (" + " + (b.Mat[i][0]));
                            } else {
                                fxk += (" + ");
                            }
                        }
                    } else {
                        if (b.Mat[i][0] != -1) {
                            fxk += (" - " + ((-1) * b.Mat[i][0]));
                        } else {
                            if (i == 0) {
                                fxk += (" - " + ((-1) * b.Mat[i][0]));
                            } else {
                                fxk += (" - ");
                            }
                        }
                    }

                    /* Add xk dari suku-suku selanjutnya*/
                    if (i != 0) {
                        fxk += ("x" + (i));
                    }
                }
            }
        }
        return fxk;
    }
    
    public static void RLBFile(matriks xk, matriks b) {
        // Kamus Lokal
        String filename;

        // Algoritma
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write
            bw.write("Hasil Perhitungan Regresi Linear Berganda");
            bw.newLine();
            bw.write("Penjabaran f(xk):");
            bw.newLine();
            bw.write(fxkString(b));
            bw.newLine();
            bw.write("Hasil substitusi dengan nilai xk dari masukan:");
            bw.newLine();
            bw.write(("f(xk) = " + fxk(xk, b)));
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
