import java.util.*;

import src.BicubicInterpolation;
import src.InterpolasiPolinom;
import src.RegresiLinierBerganda;
import src.SPL;
import src.matriks;
import src.operasiMatriks;
import src.ImageUtil;
import src.ImageUpsc;

/*
 * Ini buat main program utamanya
 * Inputtan masuk ke variabel input
 * 
 * Buat prosesnya biar gak pusing dipisah aja tiap subproses
 * Misal SPL bagian yang gauss itu bakalan make fungsi antara SPLGauss Dst Dst.
 * Maksudnya bukan fungsi buat nyari nilainya yah, itu make yang udah dibuat
 * Tapi maksudnya buat input matriks, validasi, sama output
 * 
 * Buat sekarang fungsi antaranya ada di bawah, kalo perlu dibikin file baru gimana nanti
 * 
 * Btw yang basreng itu buat test potongan code aja, yang main utamanya yang ini.
 */


public class Main {
    static Scanner in = new Scanner (System.in);
    public static void main(String[] args){
        boolean jalan = true;
        int input = 0;
        String line;
        String[] row = new String[100];

        // Pilih Menu
        while (jalan) {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Perbesar Gambar");
            System.out.println("8. Keluar");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 8) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 8);

            switch (input) {
                //SPL
                case 1:
                System.out.println("\nPilih metode:");
                System.out.println("1. Metode Eliminasi Gauss");
                System.out.println("2. Metode Eliminasi Gauss Jordan");
                System.out.println("3. Metode Matriks Balikan");
                System.out.println("4. Kaidah Cramer");
                System.out.println("5. Kembali ke menu");

                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 5) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 || input > 5);
                
                switch (input){
                    //Metode Eliminasi Gauss
                    case 1:
                    SPLGauss();
                    break;

                    //Metode Eliminasi Gauss Jordan
                    case 2:
                    SPLGaussJordan();
                    break;

                    //Metode Matriks Balikan
                    case 3:
                    SPLInverse();
                    break;

                    //Kaidah Cramer
                    case 4:
                    SPLCramer();
                    break;

                    //Kembali ke menu
                    case 5:
                    System.out.println("\nKembali ke menu utama...\n");
                    break;
                }
                break;

                //Determinan
                case 2:
                System.out.println("\nPilih metode:");
                System.out.println("1. Metode OBE (Segitiga Atas)");
                System.out.println("2. Metode Kofaktor baris 0");
                System.out.println("3. Metode Kofaktor kolom 0");
                System.out.println("4. Kembali ke menu");

                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 4) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 || input > 4);
                
                switch (input){
                    //Metode OBE
                    case 1:
                    DeterminanOBE();
                    break;

                    //Metode Kofaktor baris 0
                    case 2:
                    DeterminanCofRow0();
                    break;

                    //Metode Kofaktor kolom 0
                    case 3:
                    DeterminanCofCol0();
                    break;

                    //Kembali ke menu
                    case 4:
                    System.out.println("\nKembali ke menu utama...\n");
                    break;
                }
                break;

                //Inverse
                case 3:
                System.out.println("\nPilih metode:");
                System.out.println("1. Metode Identitas");
                System.out.println("2. Metode Adjoint");
                System.out.println("3. Kembali ke menu");

                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 3) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 || input > 3);
                
                switch (input){
                    //Metode Identitas
                    case 1:
                    InverseId();
                    break;

                    //Metode Adjoint
                    case 2:
                    InverseAdj();
                    break;

                    //Kembali ke menu
                    case 3:
                    System.out.println("\nKembali ke menu utama...\n");
                    break;
                }
                break;

                //Interpolasi Polinom
                case 4:
                InterPolin();
                break;

                //Interpolasi Bicubic
                case 5:
                BicInter();
                break;

                //Regresi Linear Berganda
                case 6:
                RegLinBerganda();
                break;

                //Keluar
                case 7:
                ImageScaling();
                break;

                //Keluar
                case 8:
                jalan = false;
                System.out.println("\nTerima kasih sudah menggunakan aplikasi.\nSalam basreng xixixixi <3 <3\n");
                break;
            }
            
        }

        // Menutup scanner
        in.close();
    }

    




    //Nah ini fungsi antara yang diomongin di atas
    //SPL
    public static void SPLGauss(){
        String line;
        String[] row;
        matriks M = new matriks();
        int baris, kolom, input = 0;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    baris = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    baris = 0;
                }
                if (baris <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    kolom = 0;
                }
                if (kolom <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;

            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.bacaMatriks(baris , kolom);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){

            M = operasiMatriks.gauss(M);
            SPL.solveSPL(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.solveSPLFile(M);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLGaussJordan(){
        String line;
        String[] row;
        matriks M = new matriks();
        int baris, kolom, input;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    baris = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    baris = 0;
                }
                if (baris <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    kolom = 0;
                }
                if (kolom <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;
    
            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.bacaMatriks(baris , kolom);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            M = operasiMatriks.gaussJordan(M);
            SPL.solveSPL(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.solveSPLFile(M);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLInverse(){
        String line;
        String[] row;
        matriks M = new matriks();
        int input, baris, kolom;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    baris = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    baris = 0;
                }
                if (baris <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    kolom = 0;
                }
                if (kolom <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;
    
            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.bacaMatriks(baris, kolom);
            break;
        }
        
        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            SPL.SolveInverse(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.SolveInverseFile(M);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void SPLCramer(){
        String line;
        String[] row;
        matriks M = new matriks();
        int input, baris, kolom;
        Boolean notFirst = false;

        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan jumlah persamaan: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    baris = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    baris = 0;
                }
                if (baris <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (baris <= 0);

            do{
                System.out.print("Masukkan jumlah variabel: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    kolom = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    kolom = 0;
                }
                if (kolom <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (kolom <= 0);
            kolom = kolom + 1;
    
            System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
            M.bacaMatriks(baris, kolom);
            break;
        }


        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            SPL.SolveCramer(M);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");

            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if ((input <= 0 || input > 2) && notFirst) {
                    System.out.println("Input tidak valid");
                }
                notFirst = true;
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                SPL.SolveCramerFile(M);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    //DETERMINAN
    public static void DeterminanOBE(){
        String line;
        String[] row;
        matriks M = new matriks();
        int dimensi, input;
        double det;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.bacaMatriks(dimensi, dimensi);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            det = operasiMatriks.detOBE(M);
            System.out.print("\nDeterminannya adalah: ");
            System.out.println(det);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                operasiMatriks.detFile(M, det);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void DeterminanCofRow0(){
        String line;
        String[] row;
        matriks M = new matriks();
        int dimensi, input;
        double det;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.bacaMatriks(dimensi, dimensi);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            det = operasiMatriks.detExCofRow0(M);
            System.out.print("\nDeterminannya adalah: ");
            System.out.println(det);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                operasiMatriks.detFile(M, det);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void DeterminanCofCol0(){
        String line;
        String[] row;
        matriks M = new matriks();
        int dimensi, input;
        double det;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.bacaMatriks(dimensi, dimensi);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            det = operasiMatriks.detExCofCol0(M);
            System.out.print("\nDeterminannya adalah: ");
            System.out.println(det);

            System.out.println("Apakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);

            switch (input){
                case 1:
                operasiMatriks.detFile(M, det);

                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    //INVERSE
    public static void InverseId(){
        String line;
        String[] row;
        matriks M = new matriks();
        matriks inverse = new matriks();
        int dimensi, input = 0;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.bacaMatriks(dimensi, dimensi);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            if (operasiMatriks.detExCofRow0(M) == 0){
                System.out.println("Matriks tidak memiliki balikan.");
            }
            else{
                inverse = operasiMatriks.invIdentitas(M);
                System.out.print("\nBalikannya adalah: \n");
                inverse.tulisMatriks();
        
                System.out.println("Apakah ingin dalam bentuk file?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 2) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 || input > 2);
        
                switch (input){
                    case 1:
                    inverse.writeMatrixFile(inverse);
        
                    case 2:
                    System.out.println("\nOk! Kembali ke menu utama...");
                    break;
                }
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    public static void InverseAdj(){
        String line;
        String[] row;
        matriks M = new matriks();
        matriks inverse = new matriks();
        int dimensi, input;
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");
        
        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        switch (input){
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            namaFile = "./test/" + namaFile;
            M.bacaFileMatriks(namaFile);
            break;

            case 2:
            do{
                System.out.print("\nMasukkan dimensi matriks: ");
                line = in.nextLine();
                row = line.split(" ");
                try {
                    dimensi = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    dimensi = 0;
                }
                if (dimensi <= 0) {
                    System.out.println("Input tidak valid");
                } 
            } while (dimensi <= 0);
    
            System.out.print("Masukkan nilai elemen pada matriks: \n");
            M.bacaMatriks(dimensi, dimensi);
            break;
        }

        if (M.jumlahBaris > 0 && M.jumlahKolom > 0){
            if (operasiMatriks.detExCofRow0(M) == 0){
                System.out.println("Matriks tidak memiliki balikan.");
            }
            else{
                inverse = operasiMatriks.invAdj(M);
                System.out.print("\nBalikannya adalah: \n");
                inverse.tulisMatriks();
        
                System.out.println("Apakah ingin dalam bentuk file?");
                System.out.println("1. Ya");
                System.out.println("2. Tidak");
                do{
                    line = in.nextLine();
                    row = line.split(" ");
                    try {
                        input = Integer.parseInt(row[0]);
                    } catch (NumberFormatException e) {
                        input = 0;
                    }
                    if (input <= 0 || input > 2) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 || input > 2);
        
                switch (input){
                    case 1:
                    // Menyimpan file
                    inverse.writeMatrixFile(inverse);
        
                    case 2:
                    System.out.println("\nOk! Kembali ke menu utama...");
                    break;
                }
            }
        }
        else{
            System.out.println("Operasi gagal, kembali ke menu utama...");
        }
    }

    //INTERPOLASI POLINOM
    public static void InterPolin() {
        String line;
        String[] row;

        /* Memilih metode input */
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        int input;
        matriks stdInput = new matriks();

        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        /* Memberi masukan */
        switch (input) {
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            String pathFile = "./test/" + namaFile;
            stdInput.bacaFileMatriksBolong(pathFile, 1);
            break;

            case 2:
            stdInput = InterpolasiPolinom.stdInputKeyboard();
            break;
        }

        /* Proses */
        if (!(stdInput.jumlahBaris == 0 || stdInput.jumlahKolom == 0)) {
            matriks ai = InterpolasiPolinom.ai(InterpolasiPolinom.xi(InterpolasiPolinom.x(stdInput)),InterpolasiPolinom.fx(stdInput));
            double a = InterpolasiPolinom.a(stdInput);
            
            /* Output terminal */
            System.out.println("\nHasil Interpolasi Polinom");
            System.out.println(InterpolasiPolinom.fxString(ai));
            System.out.println("f("+ a +") = " + InterpolasiPolinom.fa(ai, a));
            
            /* Output file */
            System.out.println("\nApakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);
        
            switch (input){
                case 1:
                InterpolasiPolinom.IPFile(ai, a);
                break;
        
                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }

        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }

    //INTERPOLASI BICUBIC
    public static void BicInter() {
        String line;
        String[] row;
        int input;

        matriks stdInput = new matriks();
    
        /* Menerima masukan dari file */
        System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
        System.out.print("\nNama file (.txt): ");
        String namaFile = in.nextLine();
        String pathFile = "./test/" + namaFile;
        stdInput.bacaFileMatriksBolong(pathFile, 2);

        /* Proses */
        if (!(stdInput.jumlahBaris == 0 || stdInput.jumlahKolom == 0)) {
            matriks aij = BicubicInterpolation.aij(BicubicInterpolation.fxy(BicubicInterpolation.fxy4x4(stdInput)));
            double a = BicubicInterpolation.a(stdInput);
            double b = BicubicInterpolation.b(stdInput);

            /* Output terminal */
            System.out.println("\nHasil Bicubic Interpolation");
            System.out.println("f(" + a + "," + b + ") = " + BicubicInterpolation.bicIntpol(aij, a, b));
            
            /* Output file */
            System.out.println("\nApakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;
                }
                if (input <= 0 || input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 || input > 2);
        
            switch (input){
                case 1:
                BicubicInterpolation.BIFile(aij, a, b);
                break;
        
                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }

        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }

    //REGRESI LINEAR BERGANDA
    public static void RegLinBerganda() {
        String line;
        String[] row;
        int input;

        matriks stdInput = new matriks();

        /* Pilih metode masukan */
        System.out.println("\nPilih metode masukan:");
        System.out.println("1. Dari file");
        System.out.println("2. Dari keyboard");

        do{
            line = in.nextLine();
            row = line.split(" ");
            try {
                input = Integer.parseInt(row[0]);
            } catch (NumberFormatException e) {
                input = 0;
            }
            if (input <= 0 || input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 || input > 2);

        /* Menerima masukan */
        switch (input) {
            case 1:
            System.out.print("\nPastikan file masukan sudah dimasukkan ke folder test.");
            System.out.print("\nNama file (.txt): ");
            String namaFile = in.nextLine();
            String pathFile = "./test/" + namaFile;
            stdInput.bacaFileMatriksBolong(pathFile, 1);
            break;

            case 2:
            stdInput = RegresiLinierBerganda.stdInputKeyboard();
            break;
        }

        /* Proses */
        if (!(stdInput.jumlahBaris == 0 || stdInput.jumlahKolom == 0)) {
            matriks xk = RegresiLinierBerganda.xk(stdInput);
            matriks b = RegresiLinierBerganda.b(RegresiLinierBerganda.xnm(stdInput), RegresiLinierBerganda.ym(stdInput));
            
            /* Output terminal */
            System.out.println("\nHasil Perhitungan Regresi Linear Berganda");
            System.out.println("Penjabaran f(xk):");
            System.out.println(RegresiLinierBerganda.fxkString(b));
            System.out.println("Hasil substitusi dengan nilai xk dari masukan:");
            System.out.println("f(xk) = " + RegresiLinierBerganda.fxk(RegresiLinierBerganda.xk(stdInput), RegresiLinierBerganda.b(RegresiLinierBerganda.xnm(stdInput), RegresiLinierBerganda.ym(stdInput))));
            
            /* Output file */
            System.out.println("\nApakah ingin dalam bentuk file?");
            System.out.println("1. Ya");
            System.out.println("2. Tidak");
            do{
                line = in.nextLine();
                row = line.split(" ");
                try {
                    input = Integer.parseInt(row[0]);
                } catch (NumberFormatException e) {
                    input = 0;                    }
                    if (input <= 0 || input > 2) {
                        System.out.println("Input tidak valid");
                    } 
            } while (input <= 0 || input > 2);
    
            switch (input){
                case 1:
                RegresiLinierBerganda.RLBFile(xk, b);;
                break;
    
                case 2:
                System.out.println("\nOk! Kembali ke menu utama...");
                break;
            }
        } else {
            System.out.println("\nOperasi gagal, kembali ke menu utama...");
        }
    }

    // Image Scaling
    public static void ImageScaling(){
        
        // Kamus Lokal
        long startTime = System.nanoTime();
        String filename, filepath, newfilename;
        matriks MAwal = new matriks();
        matriks MAkhir = new matriks();

        // Algoritma
        System.out.println("\nHalo! Selamat datang ke Image Scaling.");
        System.out.println("\nPastikan file gambar yang akan diperbesar sudah dimasukkan ke dalam folder test!");

        // Nama File yang ingin di upscale
        System.out.println("\nMasukkan nama file yang ingin di upscale: ");
        filename = in.nextLine();

        filepath = "./test/" + filename;
        MAwal = ImageUtil.loadImage(filepath);
        MAkhir = ImageUpsc.interpolate2x(MAwal);

        // Nama file yang telah diupscale
        newfilename = filepath + "_upscaled2x.png";
        ImageUtil.writeImage(newfilename, MAkhir);

        // Menghitung time elapsed
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: "
        + elapsedTime/1000000);
        
    }
}
