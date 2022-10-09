
// Import utilities
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageUtil {
    // matriks valuePixel = new matriks();

    // Load Image
    public static ImageUpscMatriks loadImage(String filename){
    /* Membaca Value dari setiap pixel dalam image */
    ImageUpscMatriks valuePixel = new ImageUpscMatriks();
        try{
            // Read file
            BufferedImage image = ImageIO.read(new File(filename));
            int width          = image.getWidth();
            int height         = image.getHeight();

            // Get Values
            if (height > width){
                valuePixel.resetCap(height+2);
            }
            else{
                valuePixel.resetCap(width+2);
            }
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

        } catch(IOException e){
            System.out.println("Error: " + e);
        }
        return (valuePixel);
    }

    public static void writeImage(String filename, ImageUpscMatriks m){
        /* Membaca matriks hasil perbesaran dan mewrite image berdasarkan matriks tersebut */
        // Kamus Lokal
        int height, width;

        // Algoritma
        // Inisialisasi height dan width
        height = m.jumlahBaris;
        width = m.jumlahKolom;
        // Inisialisasi buffer image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Rendering Image
        for(int x=0; x < height; x++){
            for(int y=0; y < width; y++){
                if (m.Mat[x][y] > 255){
                    m.Mat[x][y] = 255;
                }
                else if (m.Mat[x][y] < 0){
                    m.Mat[x][y] = 0;
                }
                Color color = new Color((int)(m.Mat[x][y]), (int)(m.Mat[x][y]), (int)(m.Mat[x][y]));
                image.setRGB(y,x, color.getRGB());
            }
        }

        try{
        // Saving file
        File output_file = new File(filename);
        ImageIO.write(image, "png", output_file);

        // Error Handling
        } catch(IOException e){
            System.out.println("Error: " + e);
        } 
    
} }
