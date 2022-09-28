import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ImageUpsc {
    //BAKAL MAKE MEMORI BANYAK, KEEP IN MIND!
    static void upscaleBic(String filename, int precision, int multiplier) {
        //belom jadi
        BufferedImage image;
        int width = 0;
        int height = 0;
        try {
            image = ImageIO.read(new File(filename));
    
            width = image.getWidth()*multiplier;
            height = image.getHeight()*multiplier;
            BufferedImage imageUpscaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
            

            File outputFile = new File("/output.bmp");
            try{
                ImageIO.write(imageUpscaled, "bmp", outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            } 

        } catch (IOException e) {
            e.printStackTrace();
        } 
        
    }

    static matriks XiYj () {
        // Membuat matriks XiYj berdimensi 16 x 16
        matriks XiYj = new matriks();
        XiYj.jumlahBaris = 16;
        XiYj.jumlahKolom = 16;
        
        int y, x, i, j, baris, kolom;
        baris = 0;
        for (y = -1; y <= 2; y++){
            for (x = -1; x <= 2; x++) {
                kolom = 0;
                for (j = 0; j <= 3; j++) {
                    for (i = 0; i <= 3; i++) {
                        XiYj.Mat[baris][kolom] = Math.pow (x, i) * Math.pow (y, j);
                        kolom++;
                    }
                }
            baris++;
            }
        }
        return XiYj;
    }
    
    static matriks getfxy(String filename, int rgb){
        //mengambil nilai r/g/b dari file
        matriks fxy = new matriks();
        BufferedImage image;
        int width = 0;
        int height = 0;
        try {
            image = ImageIO.read(new File(filename));
    
            width = image.getWidth();
            height = image.getHeight();
            fxy.resetCap(2000);
            fxy.jumlahBaris = height;
            fxy.jumlahKolom = width;

            for(int i = 0; i < width; i++){
                for(int j = 0; j < height; j++){
                    Color color = new Color(image.getRGB(j, i));
                    if (rgb == 1){
                        fxy.Mat[i][j] = color.getRed();
                    }
                    else if (rgb == 2){
                        fxy.Mat[i][j] = color.getGreen();
                    }
                    else if (rgb == 3){
                        fxy.Mat[i][j] = color.getBlue();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
        return fxy;   
    }

    static matriks zoning(matriks MIn, int size, int startx, int starty){
        //memotong daerah matriks
        matriks temp = new matriks();
        int baris, kolom;
        temp.jumlahBaris = size;
        temp.jumlahKolom = size;
        baris = 0;
        for (int i = startx; i < startx+size; i ++){
            kolom = 0;
            for (int j = starty; j < starty+size; j ++){
                temp.Mat[baris][kolom] = MIn.Mat[i][j];
                kolom += 1;
            }
            baris += 1;
        }
        return temp;
    }

    static matriks linearize(matriks MIn){
        //mengambil fxy kurleb
        matriks temp = new matriks();
        temp.jumlahKolom = 1;
        temp.jumlahBaris = MIn.jumlahBaris*MIn.jumlahKolom;

        int counter = 0;
        for (int i = 0; i < MIn.jumlahBaris; i++){
            for (int j = 0; j < MIn.jumlahKolom; j++){
                temp.Mat[counter][0] = MIn.Mat[i][j];
                counter += 1;
            }
        }
        return temp;
    }

    static matriks interpolate2x(matriks MIn){
        //belom jadi
        matriks temp = new matriks();
        matriks slicer = new matriks();
        matriks XY = new matriks();
        matriks aij = new matriks();
        int i, j;
        int startx = 0;
        int starty = 0;
        int size = 4;
        double pointX, pointY;

        temp.resetCap(200);
        temp.jumlahBaris = MIn.jumlahBaris;
        temp.jumlahKolom = MIn.jumlahKolom;

        XY = XiYj();
        
        slicer = zoning(MIn, size, startx, starty);
        slicer = linearize(slicer);
        slicer.tulisMatriks();

        aij = aij(slicer, XY);

        
        pointY = -1;
        for (i = 0; i < size; i++){
            pointX = -1;
            for (j = 0; j < size; j++){
                temp.Mat[i][j] = bicIntpol(aij, 0.5, 0.5);
                pointX += 1;
            }
            pointY += 1;
        }
         
        return temp;
    }



    static matriks aij (matriks fxy, matriks XiYj) {
        return operasiMatriks.perkalianMatriks(operasiMatriks.invAdj(XiYj), fxy);
    }

    static double bicIntpol (matriks aij, double a, double b) {
        // Menghasilkan nilai f(a,b)
        int i, j, baris;
        double fab;
        fab = 0;
        baris = 0;
        for (j = 0; j <= 3; j++) {
            for (i = 0; i <= 3; i++) {
                fab += aij.Mat[baris][0] * Math.pow(a, i) * Math.pow(b, j);
                baris++;
            }
        }
        return fab;
    }
}
