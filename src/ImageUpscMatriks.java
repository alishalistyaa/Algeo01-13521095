
/* Kelas ADT Matriks khusus untuk upscaling */

public class ImageUpscMatriks{
    
    /* CreateMatriks dengan maximal CAPACITY */
    // Atribut
    int CAPACITY = 1;
    double[][] Mat = new double[1][1];
    
    public int jumlahKolom = 0;
    public int jumlahBaris = 0;

    public void resetCap(int newCap){
        //mengubah kapasitas matrix
        //matrix dikosongkan
        this.CAPACITY = newCap;
        this.Mat = new double[CAPACITY][CAPACITY];
        this.jumlahKolom = 0;
        this.jumlahBaris = 0;
    }
}