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
    
    
    int panjangRow = 0;
    int panjangCol = 0;


    // Method:
    void bacaMatriks(int n, int m){
        /* Fungsi untuk mengisi elemen Matriks */
        // Kamus Lokal
        int i, j;
        // Algoritma
        
        this.panjangRow = n;
        this.panjangCol = m;

        for(i=0; i<m; i++){
            for(j=0; j<n; j++) {
                this.Mat[i][j] = in.nextDouble();
            }
        }
    }

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
            this.panjangRow++;
            Scanner bacakolom = new Scanner(bacafile.nextLine());
                while(bacakolom.hasNextDouble()){
                    this.panjangCol++;
                }
            }

        // close scanner
        bacafile.close();

        // Membaca integer dari file
        bacafile = new Scanner (file); // refresh dr atas
        for(i=0; i<this.panjangRow; i++){
            for(j=0; j<this.panjangCol; j++){
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
        return (this.panjangRow == CAPACITY);
    }
    boolean penuhCol() {
        return (this.panjangCol == CAPACITY);
    }
    
    void tulisMatriks(){
        /* I.S. Matriks terdefinisi */
        /* Menuliskan matriks pada layar */
        // Kamus Lokal
        int i, j;
        // Algoritma
        for(i = 0; i < this.panjangCol; i++) {
            System.out.print("| ");
            for (j = 0; j < this.panjangRow; j++) {
                System.out.print(this.Mat[i][j]);
                System.out.print(" ");
            }
            System.out.print("|\n");
        }  
    }  
}