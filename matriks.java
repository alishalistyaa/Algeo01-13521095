/* Kelas ADT Matriks */
import java.util.*;

public class matriks {
    
    /* CreateMatriks dengan maximal CAPACITY */
    // Atribut
    Scanner in = new Scanner (System.in);
    int CAPACITY = 100;
    int[][] Mat = new int[CAPACITY][CAPACITY];
    
    
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
                this.Mat[i][j] = in.nextInt();
            }
        }
    }

    /* sebenernya ini gaperlu sih */
    int getComponent(int n, int m){
        /* Fungsi untuk mendapatkan komponen matriks */
        // Kamus Lokal

        // Algoritma
        return this.Mat[n][m];
    }

    /* Apakah matriks penuh */
    boolean penuhRow() {
        return (panjangRow == CAPACITY);
    }
    boolean penuhCol() {
        return (panjangCol == CAPACITY);
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

    
    void pertambahanMatriks(matriks M1, matriks M2){
        //prekondisi ukuran M1 == ukuran M2
        // Kamus Lokal
        int i,j;
        // Algoritma
        for(i = 0; i < M1.panjangCol; i++){
            for(j = 0; j < M1.panjangRow; j++) {
                this.Mat[i][j] = M1.Mat[i][j] + M2.Mat[i][j];
            }
        }
    }

    void penguranganMatriks(matriks M1, matriks M2){
        //prekondisi ukuran M1 == ukuran M2
        // Kamus Lokal
        int i,j;
        // Algoritma
        for(i = 0; i < M1.panjangCol; i++){
            for(j = 0; j < M1.panjangRow; j++) {
                this.Mat[i][j] = M1.Mat[i][j] - M2.Mat[i][j];
            }
        }
    }

    void perkalianMatriks(matriks M1, matriks M2){

    }

    // boolean isEqual(){
    //     // Kamus Lokal
    //     boolean equal;
    //     int i;
    //     // Algoritma
    //         for(i = 0; i<10; i++){

    //         }
    //     return equal;
    // }
}