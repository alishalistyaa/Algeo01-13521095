// Import utilities
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScalingImage {
    matriks valuePixel = new matriks();

    // Load Image
    matriks loadImage(String filename){
    /* Membaca Value dari setiap pixel dalam image */
        try{
            // Read file
            BufferedImage image = ImageIO.read(new File(filename));
            int width          = image.getWidth();
            int height         = image.getHeight();

            // Get Values
            valuePixel.jumlahBaris = height;
            valuePixel.jumlahKolom = width;

            // Scanning Value
            for(int x = 0; x < width; x++){
                for(int y = 0; y < height; y++){
                    Color color = new Color(image.getRGB(y,x));
                    double gray = color.getRed();
                    valuePixel.Mat[x][y] = (gray / 255d);
                }
            }
                System.out.println("Reading complete.");

        } catch(IOException e){
            System.out.println("Error: " + e);
        }
        return (valuePixel);
    }

    matriks scaleImage(int perbesaran){
        /* Menscaling image menggunakan bicubic interpolation */
        // Kamus Lokal

        // Algoritma
        return(null);
    }

    void writeImage(String filename, matriks m){
        // /* Membaca matriks hasil perbesaran dan mewrite image berdasarkan matriks tersebut */
        // // Kamus Lokal
        // int height, width;
        // matriks m

        // // Algoritma
        // // Inisialisasi height dan width
        // height = m.jumlahBaris;
        // width = m.jumlahKolom;
        // // Inisialisasi buffer image
        // BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // for(int x=0; x<height; x++){
        //     for(int y=0; y<width; y++){
        //         Color color = new Color(m.Mat[x][y]);
        //         image.setRGB(x,y, color.getRGB());
        //     }
        // }
        // ImageIO.write(image, "jpg", new File("////image path.jpg"));

        // // Algoritma
        
    
} }
