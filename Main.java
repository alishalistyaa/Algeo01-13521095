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
    public static void main(String[] args){
        boolean jalan = true;
        Scanner in = new Scanner (System.in);
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
                    if (input <= 0 && input > 7) {
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
                System.out.println("2. Metode Kofaktor");
                System.out.println("3. Kembali ke menu");

                do{
                    input = in.nextInt();
                    if (input <= 0 && input > 7) {
                        System.out.println("Input tidak valid");
                    } 
                } while (input <= 0 && input > 5);
                
                switch (input){
                    //Metode OBE
                    case 1:
                    DeterminanOBE();
                    break;

                    //Metode Kofaktor
                    case 2:
                    DeterminanCof();
                    break;

                    //Kembali ke menu
                    case 3:
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

    }

    public static void SPLGaussJordan(){
        
    }

    public static void SPLInverse(){

    }

    public static void SPLCramer(){

    }


    //DETERMINAN
    public static void DeterminanOBE(){

    }

    public static void DeterminanCof(){

    }


    //INVERSE
    public static void InverseId(){

    }

    public static void InverseAdj(){

    }

    //interpolasi polinom
    //interpolasi bicubic
    //regresi linear berganda
}
