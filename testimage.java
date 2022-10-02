
import src.matriks;
import src.ImageUtil;
import src.ImageUpsc;
import java.util.*;

public class testimage {
    static Scanner in = new Scanner (System.in);
    public static void main(String[] args){
        // Kamus Lokal
        long startTime = System.nanoTime();
        String filename, filepath, newfilename;
        matriks MAwal = new matriks();
        matriks MAkhir = new matriks();

        // Algoritma
        System.out.println("\nHalo! Selamat datang ke Image Scaling.");
        System.out.println("Pastikan file gambar yang akan diperbesar sudah dimasukkan ke dalam folder test!");

        // Nama File yang ingin di upscale
        System.out.println("\nMasukkan nama file yang ingin di upscale: ");
        filename = in.nextLine();

        filepath = "./test/" + filename;
        MAwal = ImageUtil.loadImage(filepath);
        MAkhir = ImageUpsc.interpolate2x(MAwal);

        // Nama file yang telah diupscale
        newfilename = filename + "_upscaled2x.png";
        ImageUtil.writeImage(newfilename, MAkhir);

        // Menghitung time elapsed
        long elapsedTime = System.nanoTime() - startTime;
        System.out.println("Total execution time in millis: "
        + elapsedTime/1000000);
        
    }
}
