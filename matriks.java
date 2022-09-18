/* Kelas ADT Matriks */
// Import library
import java.util.*;
import java.io.*; 

public class matriks {
    
    /* CreateMatriks dengan maximal CAPACITY */
    // Atribut
    Scanner in = new Scanner (System.in);
    int CAPACITY = 100;
    double[][] Mat = new double[CAPACITY][CAPACITY];
    
    
    int jumlahKolom = 0;
    int jumlahBaris = 0;


    // Method:
    void bacaMatriks(int m, int n){
        /* Fungsi untuk mengisi elemen Matriks */
        // Kamus Lokal
        int i, j;
        // Algoritma
        
        this.jumlahBaris = m;
        this.jumlahKolom = n;

        for(i=0; i<m; i++){
            for(j=0; j<n; j++) {
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

    // blm jadi
    /* Baca Matriks dari File */
    void bacaFileMatriks(String filename){
        // Kamus Lokal
        File file = new File(filename);
        int i,j;

        // Algoritma
        
        try{ // Untuk validasi dan dapat error message
        Scanner bacafile = new Scanner (file);

        // Menghitung banyaknya kolom dan baris
        while(bacafile.hasNextLine()){
            this.jumlahKolom++;
            Scanner bacakolom = new Scanner(bacafile.nextLine());
                while(bacakolom.hasNextDouble()){
                    this.jumlahBaris++;
                }
            }

        // close scanner
        bacafile.close();

        // Membaca integer dari file
        bacafile = new Scanner (file); // refresh dr atas
        for(i=0; i<this.jumlahKolom; i++){
            for(j=0; j<this.jumlahBaris; j++){
                if(bacafile.hasNextDouble()){
                    this.Mat[i][j] = bacafile.nextDouble();
                }
            }
        }

        // Jika file tidak ditemukan, maka output error mess
        } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());}
    }

    /* sebenernya ini gaperlu sih */
    double getComponent(int n, int m){
        /* Fungsi untuk mendapatkan komponen matriks */
        // Kamus Lokal

        // Algoritma
        return this.Mat[n][m];
    }

    /* Apakah matriks penuh */
    boolean penuhRow() {
        return (this.jumlahKolom == CAPACITY);
    }
    boolean penuhCol() {
        return (this.jumlahBaris == CAPACITY);
    }
    
    void tulisMatriks(){
        /* I.S. Matriks terdefinisi */
        /* Menuliskan matriks pada layar */
        // Kamus Lokal
        int i, j;
        // Algoritma
        for(i = 0; i < this.jumlahBaris; i++) {
            System.out.print("| ");
            for (j = 0; j < this.jumlahKolom; j++) {
                System.out.print(this.Mat[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }  
    }  
}