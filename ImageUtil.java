// Import utilities
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {
    // matriks valuePixel = new matriks();

    // Load Image
    static matriks loadImage(String filename){
    /* Membaca Value dari setiap pixel dalam image */
    matriks valuePixel = new matriks();
        try{
            // Read file
            BufferedImage image = ImageIO.read(new File(filename));
            int width          = image.getWidth();
            int height         = image.getHeight();

            // Get Values
            valuePixel.jumlahBaris = height;
            valuePixel.jumlahKolom = width;

            // Scanning Value
            for(int x = 0; x < height; x++){
                for(int y = 0; y < width; y++){
                    // Alternatif Satu
                    Color color = new Color(image.getRGB(y,x));
                    double gray = (color.getRed() + color.getGreen()+ color.getBlue())/3;
                    valuePixel.Mat[x][y] = (double) gray;
                }
            }
                System.out.println("Reading complete.");

        } catch(IOException e){
            System.out.println("Error: " + e);
        }
        return (valuePixel);
    }

    static void writeImage(String filename, matriks m){
        /* Membaca matriks hasil perbesaran dan mewrite image berdasarkan matriks tersebut */
        // Kamus Lokal
        int height, width;

        // Algoritma
        // Inisialisasi height dan width
        height = m.jumlahBaris;
        width = m.jumlahKolom;
        // Inisialisasi buffer image
        BufferedImage image = null;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Rendering Image
        for(int x=0; x < height; x++){
            for(int y=0; y < width; y++){
                Color color = new Color((int)(Math.abs(m.Mat[x][y]%252)), (int)(Math.abs(m.Mat[x][y]%252)), (int)(Math.abs(m.Mat[x][y]%252)));
                image.setRGB(y,x, color.getRGB());
            }
        }

        try{
        // Saving file
        File output_file = new File(filename);
        ImageIO.write(image, "png", output_file);
        System.out.println("Writing complete.");

        // Error Handling
        } catch(IOException e){
            System.out.println("Error: " + e);
        } 
    
} }
