import java.util.*;

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

        while (jalan) {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linear");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Interpolasi Bicubic");
            System.out.println("6. Regresi Linear Berganda");
            System.out.println("7. Keluar");

            do{
                input = in.nextInt();
                if (input <= 0 && input > 7) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 && input > 7);

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
                    input = in.nextInt();
                    if (input <= 0 && input > 5) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 && input > 5);
                
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
                    input = in.nextInt();
                    if (input <= 0 && input > 4) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 && input > 4);
                
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
                    input = in.nextInt();
                    if (input <= 0 && input > 7) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 && input > 5);
                
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
                break;

                //Interpolasi Bicubic
                case 5:
                break;

                //Regresi Linear Berganda
                case 6:
                break;

                //Keluar
                case 7:
                jalan = false;
                System.out.println("\nTerima kasih sudah menggungakan aplikasi.");
                break;
            }
            
        }

        // Menutup scanner
        in.close();
    }

    
    //Nah ini fungsi antara yang diomongin di atas
    //SPL
    public static void SPLGauss(){
        matriks M = new matriks();
        int baris, kolom, input;

        System.out.print("\nMasukkan jumlah persamaan: ");
        baris = in.nextInt();

        System.out.print("Masukkan jumlah variabel: ");
        kolom = in.nextInt() + 1;

        System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
        M.bacaMatriks(baris , kolom);

        M = operasiMatriks.gauss(M);
        SPL.solveSPL(M);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    public static void SPLGaussJordan(){
        matriks M = new matriks();
        int baris, kolom, input;

        System.out.print("\nMasukkan jumlah persamaan: ");
        baris = in.nextInt();

        System.out.print("Masukkan jumlah variabel: ");
        kolom = in.nextInt() + 1;

        System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
        M.bacaMatriks(baris , kolom);

        M = operasiMatriks.gaussJordan(M);
        SPL.solveSPL(M);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    public static void SPLInverse(){
        matriks M = new matriks();
        int dimensi, input;

        System.out.print("\nMasukkan jumlah persamaan: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
        M.bacaMatriks(dimensi, dimensi+1);

        SPL.SolveInverse(M);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    public static void SPLCramer(){
        matriks M = new matriks();
        int dimensi, input;

        System.out.print("\nMasukkan jumlah persamaan: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai koefisien dan hasil dari tiap variabel di tiap persamaan: \n");
        M.bacaMatriks(dimensi, dimensi+1);

        SPL.SolveCramer(M);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }


    //DETERMINAN
    public static void DeterminanOBE(){
        matriks M = new matriks();
        int dimensi, input;
        double det;

        System.out.print("\nMasukkan dimensi matriks: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai elemen pada matriks: \n");
        M.bacaMatriks(dimensi, dimensi);

        det = operasiMatriks.detOBE(M);
        System.out.print("\nDeterminannya adalah: ");
        System.out.println(det);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    public static void DeterminanCofRow0(){
        matriks M = new matriks();
        int dimensi, input;
        double det;

        System.out.print("\nMasukkan dimensi matriks: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai elemen pada matriks: \n");
        M.bacaMatriks(dimensi, dimensi);

        det = operasiMatriks.detExCofRow0(M);
        System.out.print("\nDeterminannya adalah: ");
        System.out.println(det);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    public static void DeterminanCofCol0(){
        matriks M = new matriks();
        int dimensi, input;
        double det;

        System.out.print("\nMasukkan dimensi matriks: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai elemen pada matriks: \n");
        M.bacaMatriks(dimensi, dimensi);

        det = operasiMatriks.detExCofCol0(M);
        System.out.print("\nDeterminannya adalah: ");
        System.out.println(det);

        System.out.println("Apakah ingin dalam bentuk file?");
        System.out.println("1. Ya");
        System.out.println("2. Tidak");
        do{
            input = in.nextInt();
            if (input <= 0 && input > 2) {
                System.out.println("Input tidak valid");
            } 
        } while (input <= 0 && input > 2);

        switch (input){
            case 1:
            //belum jadi
            break;

            case 2:
            break;
        }
    }

    //INVERSE
    public static void InverseId(){
        matriks M = new matriks();
        matriks inverse = new matriks();
        int dimensi, input;

        System.out.print("\nMasukkan dimensi matriks: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai elemen pada matriks: \n");
        M.bacaMatriks(dimensi, dimensi);

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
                input = in.nextInt();
                if (input <= 0 && input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 && input > 2);
    
            switch (input){
                case 1:
                //belum jadi
                break;
    
                case 2:
                break;
            }
        }
    }

    public static void InverseAdj(){
        matriks M = new matriks();
        matriks inverse = new matriks();
        int dimensi, input;

        System.out.print("\nMasukkan dimensi matriks: ");
        dimensi = in.nextInt();

        System.out.print("Masukkan nilai elemen pada matriks: \n");
        M.bacaMatriks(dimensi, dimensi);

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
                input = in.nextInt();
                if (input <= 0 && input > 2) {
                    System.out.println("Input tidak valid");
                } 
            } while (input <= 0 && input > 2);
    
            switch (input){
                case 1:
                //belum jadi
                break;
    
                case 2:
                break;
            }
        }
    }

    //interpolasi polinom
    //interpolasi bicubic
    //regresi linear berganda
}
