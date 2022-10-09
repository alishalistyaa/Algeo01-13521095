import java.util.stream.IntStream;

public class ImageUpsc {
    //semua padding
    static void padding(ImageUpscMatriks MIn){
        MIn.jumlahBaris +=1;
        for (int i=MIn.jumlahBaris-1; i > 0 ; i--){
            for (int j = 0; j < MIn.jumlahKolom; j++){
                MIn.Mat[i][j] = MIn.Mat[i-1][j];
            }
        }
        MIn.jumlahBaris += 1;
        for (int i=0; i < MIn.jumlahBaris; i++){
            MIn.Mat[MIn.jumlahBaris-1][i] = MIn.Mat[MIn.jumlahBaris-2][i];
            }
        MIn.jumlahKolom += 1;
        for (int i=0; i < MIn.jumlahBaris; i++){
            MIn.Mat[i][MIn.jumlahKolom-1] = MIn.Mat[i][MIn.jumlahKolom-2];
            }
        MIn.jumlahKolom += 1;
        for (int j = MIn.jumlahKolom-1; j > 0; j--){
            for (int i=0; i < MIn.jumlahBaris; i++){
                MIn.Mat[i][j] = MIn.Mat[i][j-1];
            }
        }
    }
    //akhir padding
    //library interpolasi
    static void interpolateStandar(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.5);
    }

    static void interpolateTengahX(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.33, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 0.66, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.33, 0.5);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 0.66, 0.5);
    }

    static void interpolateTengahY(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.33);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.33);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 0.66);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.5, 0.66);
    }

    static void interpolateTengahXY(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.33, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 0.66, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.33);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.33, 0.33);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 0.66, 0.33);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 0.66);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.33, 0.66);
        mainMat.Mat[y + 2][x + 2] = bicIntpol(fxy, 0.66, 0.66);
    }

    static void interpolateUjungX(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 1, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.5);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 1, 0.5);

    }

    static void interpolateUjungXTengahY(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 1, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.33);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.33);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 1, 0.33);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.5, 0.66);
        mainMat.Mat[y + 2][x + 2] = bicIntpol(fxy, 1, 0.66);
    }

    static void interpolateUjungY(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.5);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 1);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.5, 1);
    }

    static void interpolateUjungYTengahX(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.33, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 0.66, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.33, 0.5);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 0.66, 0.5);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 1);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.33, 1);
        mainMat.Mat[y + 2][x + 2] = bicIntpol(fxy, 0.66, 1);
    }

    static void interpolatePojok(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj, ImageUpscMatriks mainMat, int x, int y){

        ImageUpscMatriks16x1 fxy = new ImageUpscMatriks16x1();
        fxy = fxyOptimized(MIn, startx, starty, inversedXiYj);

        mainMat.Mat[y][x] = bicIntpol(fxy, 0, 0);
        mainMat.Mat[y][x + 1] = bicIntpol(fxy, 0.5, 0);
        mainMat.Mat[y][x + 2] = bicIntpol(fxy, 1, 0);
        mainMat.Mat[y + 1][x] = bicIntpol(fxy, 0, 0.5);
        mainMat.Mat[y + 1][x + 1] = bicIntpol(fxy, 0.5, 0.5);
        mainMat.Mat[y + 1][x + 2] = bicIntpol(fxy, 1, 0.5);
        mainMat.Mat[y + 2][x] = bicIntpol(fxy, 0, 1);
        mainMat.Mat[y + 2][x + 1] = bicIntpol(fxy, 0.5, 1);
        mainMat.Mat[y + 2][x + 2] = bicIntpol(fxy, 1, 1);
    }

    public static double bicIntpol (ImageUpscMatriks16x1 aij, double a, double b) {
        /* Menghasilkan nilai f(a,b) */ 
        double fab = 0;

        fab += aij.Mat[0][0];
        fab += aij.Mat[1][0] * (a);
        fab += aij.Mat[2][0] * (a + a);
        fab += aij.Mat[3][0] * (a + a + a);
        fab += aij.Mat[4][0] * b;
        fab += aij.Mat[5][0] * (a) * b;
        fab += aij.Mat[6][0] * (a + a) * b;
        fab += aij.Mat[7][0] * (a + a + a) * b;
        fab += aij.Mat[8][0] * (b + b);
        fab += aij.Mat[9][0] * (a) * (b + b);
        fab += aij.Mat[10][0] * (a + a) * (b + b);
        fab += aij.Mat[11][0] * (a + a + a) * (b + b);
        fab += aij.Mat[12][0] * (b + b + b);
        fab += aij.Mat[13][0] * (a) * (b + b + b);
        fab += aij.Mat[14][0] * (a + a) * (b + b + b);
        fab += aij.Mat[15][0] * (a + a + a) * (b + b + b);

        return fab;
    }

    public static ImageUpscMatriks16 xiyj () {
        /* Membuat matriks xiyj */
        ImageUpscMatriks16 xiyj = new ImageUpscMatriks16();
        int y, x, i, j, baris, kolom;
        baris = 0;
        for (y = -1; y <= 2; y++){
            for (x = -1; x <= 2; x++) {
                kolom = 0;
                for (j = 0; j <= 3; j++) {
                    for (i = 0; i <= 3; i++) {
                        xiyj.Mat[baris][kolom] = Math.pow (x, i) * Math.pow (y, j);
                        kolom++;
                    }
                }
            baris++;
            }
        }
        return xiyj;
    }

    //akhir library interpolasi
    //fungsi interpolasi utama
    public static ImageUpscMatriks interpolate2x(ImageUpscMatriks MIn){
        ImageUpscMatriks temp = new ImageUpscMatriks();
        ImageUpscMatriks16 inversedXiYj;
        boolean genapX = (MIn.jumlahKolom % 2 == 0);
        boolean genapY = (MIn.jumlahBaris % 2 == 0);

        inversedXiYj = invIdentitas(xiyj());
        tidyUp(inversedXiYj);

        temp.resetCap(2*MIn.CAPACITY);
        if (genapX){
            temp.jumlahKolom = 2*MIn.jumlahKolom;
        }
        else{
            temp.jumlahKolom = 2*MIn.jumlahKolom - 1;
        }

        if (genapY){
            temp.jumlahBaris = 2*MIn.jumlahBaris;
        }
        else{
            temp.jumlahBaris = 2*MIn.jumlahBaris - 1;
        }
        

        int i, j;

        padding(MIn);

        int ujungX = MIn.jumlahKolom-4;
        int ujungY = MIn.jumlahBaris-4;
        int separoX = ujungX / 2;
        int separoY = ujungY / 2;
        int tengahX = (MIn.jumlahKolom-3)/2;
        int tengahY = (MIn.jumlahBaris-3)/2;

        if (genapX && genapY){
            for (i = 0; i < separoY; i++){
                for (j = 0; j < separoX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                }
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, j+j, i+i);
                for (j = tengahX + 1 ; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i);
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j+1, i+i);
            }

            for (j = 0; j < separoX; j++){
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j, i+i);
            }
            interpolateTengahXY(MIn, tengahX, tengahY, inversedXiYj, temp, j+j, i+i);
            for (j = tengahX + 1 ; j < ujungX; j++){
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j+1, i+i);
            }
            interpolateUjungXTengahY(MIn, ujungX, tengahY, inversedXiYj, temp, j+j+1, i+i);

            for (i = tengahY + 1; i < ujungY; i++){
                for (j = 0; j < separoX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i+1);
                }
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, j+j, i+i+1);
                for (j = tengahX + 1 ; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i+1);
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j+1, i+i+1);
            }

            for (j = 0; j < separoX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, i+i+1);
            }
            interpolateUjungYTengahX(MIn, tengahX, ujungY, inversedXiYj, temp, j+j, i+i+1);
            for (j = tengahX + 1 ; j < ujungX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j+1, i+i+1);
            }
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, j+j+1, i+i+1);
        }

        else if (genapX){
            for (i = 0; i < ujungY; i++){
                for (j = 0; j < separoX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                }
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, j+j, i+i);
                for (j = tengahX + 1 ; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i);
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j+1, i+i);
            }

            for (j = 0; j < separoX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, i+i);
            }
            interpolateUjungYTengahX(MIn, tengahX, ujungY, inversedXiYj, temp, j+j, i+i);
            for (j = tengahX + 1 ; j < ujungX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j+1, i+i);
            }
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, j+j+1, i+i);
        }

        else if (genapY){
            for (i = 0; i < separoY; i++){
                for (j = 0; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j, i+i);
            }

            for (j = 0; j < ujungX; j++){
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j, i+i);
            }
            interpolateUjungXTengahY(MIn, ujungX, tengahY, inversedXiYj, temp, j+j, i+i);

            for (i = tengahY + 1; i < ujungY; i++){
                for (j = 0; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i+1);
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j, i+i+1);
            }

            for (j = 0; j < ujungX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, i+i+1);
            }
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, j+j, i+i+1);
            
        }

        else{
            for (i = 0; i < ujungY; i++){
                for (j = 0; j < ujungX; j++){
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                    
                }
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, j+j, i+i);
            }

            for (j = 0; j < ujungX; j++){
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, i+i);
                
            }
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, j+j, i+i);
        }        

        return temp;
    }


    public static ImageUpscMatriks interpolate2xParallel(ImageUpscMatriks MIn){
        ImageUpscMatriks temp = new ImageUpscMatriks();
        ImageUpscMatriks16 inversedXiYjTemp = new ImageUpscMatriks16();
        ImageUpscMatriks16 inversedXiYj;
        boolean genapX = (MIn.jumlahKolom % 2 == 0);
        boolean genapY = (MIn.jumlahBaris % 2 == 0);

        inversedXiYjTemp = invIdentitas(xiyj());
        tidyUp(inversedXiYjTemp);

        inversedXiYj = inversedXiYjTemp;
        temp.resetCap(2*MIn.CAPACITY);
        if (genapX){
            temp.jumlahKolom = 2*MIn.jumlahKolom;
        }
        else{
            temp.jumlahKolom = 2*MIn.jumlahKolom - 1;
        }

        if (genapY){
            temp.jumlahBaris = 2*MIn.jumlahBaris;
        }
        else{
            temp.jumlahBaris = 2*MIn.jumlahBaris - 1;
        }
        
        padding(MIn);
        IntStream stream1;
        int ujungX = MIn.jumlahKolom-4;
        int ujungY = MIn.jumlahBaris-4;
        int separoX = ujungX / 2;
        int separoY = ujungY / 2;
        int tengahX = (MIn.jumlahKolom-3)/2;
        int tengahY = (MIn.jumlahBaris-3)/2;

        if (genapX && genapY){
            stream1 = IntStream.range(0, separoY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, separoX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                });
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, separoX+separoX, i+i);
                stream2 = IntStream.range(tengahX + 1, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX+1, i+i);
            });

            stream1 = IntStream.range(0, separoX);
            stream1.parallel().forEach((j) -> {
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j, separoY+separoY);
            });
            interpolateTengahXY(MIn, tengahX, tengahY, inversedXiYj, temp, separoX+separoX, separoY+separoY);
            stream1 = IntStream.range(tengahX + 1, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j, separoY+separoY);
            });
            interpolateUjungXTengahY(MIn, ujungX, tengahY, inversedXiYj, temp, ujungX+ujungX+1, separoY+separoY);

            stream1 = IntStream.range(tengahY + 1, ujungY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, separoX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i+1);
                });
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, separoX+separoX, i+i+1);
                stream2 = IntStream.range(tengahX + 1, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i+1);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX+1, i+i+1);
            });

            stream1 = IntStream.range(0, separoX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, ujungY+ujungY+1);
            });
            interpolateUjungYTengahX(MIn, tengahX, ujungY, inversedXiYj, temp, separoX+separoX, ujungY+ujungY+1);
            stream1 = IntStream.range(tengahX + 1, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j+1, ujungY+ujungY+1);
            });
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, ujungX+ujungX+1, ujungY+ujungY+1);
        }

        else if (genapX){
            stream1 = IntStream.range(0, ujungY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, separoX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                });
                interpolateTengahX(MIn, tengahX, i, inversedXiYj, temp, separoX+separoX, i+i);
                stream2 = IntStream.range(tengahX + 1, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j+1, i+i);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX+1, i+i);
            });

            stream1 = IntStream.range(0, separoX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, ujungY+ujungY);
            });
            interpolateUjungYTengahX(MIn, tengahX, ujungY, inversedXiYj, temp, separoX+separoX, ujungY+ujungY);
            stream1 = IntStream.range(tengahX + 1, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j+1, ujungY+ujungY);
            });
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, ujungX+ujungX+1, ujungY+ujungY);
        }

        else if (genapY){
            stream1 = IntStream.range(0, separoY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX, i+i);
            });

            stream1 = IntStream.range(0, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateTengahY(MIn, j, tengahY, inversedXiYj, temp, j+j, separoY+separoY);
            });
            interpolateUjungXTengahY(MIn, ujungX, tengahY, inversedXiYj, temp, ujungX+ujungX, separoY+separoY);

            stream1 = IntStream.range(tengahY + 1, ujungY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i+1);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX, i+i+1);
            });

            stream1 = IntStream.range(0, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, ujungY+ujungY+1);
            });
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, ujungX+ujungX, ujungY+ujungY+1);
        }

        else{
            stream1 = IntStream.range(0, ujungY);
            stream1.parallel().forEach((i) -> {
                IntStream stream2;
                stream2 = IntStream.range(0, ujungX);
                stream2.parallel().forEach((j) -> {
                    interpolateStandar(MIn, j, i, inversedXiYj, temp, j+j, i+i);
                });
                interpolateUjungX(MIn, ujungX, i, inversedXiYj, temp, ujungX+ujungX, i+i);
            });

            stream1 = IntStream.range(0, ujungX);
            stream1.parallel().forEach((j) -> {
                interpolateUjungY(MIn, j, ujungY, inversedXiYj, temp, j+j, ujungY+ujungY);
            });
            interpolatePojok(MIn, ujungX, ujungY, inversedXiYj, temp, ujungX+ujungX, ujungY+ujungY);
        }

        return temp;
    }
    
    public static ImageUpscMatriks16x1 fxyOptimized(ImageUpscMatriks MIn, int startx, int starty, ImageUpscMatriks16 inversedXiYj){
        //memotong daerah matriks
        ImageUpscMatriks16x1 temp = new ImageUpscMatriks16x1();
        ImageUpscMatriks16x1 temp2 = new ImageUpscMatriks16x1();

        int starty1 = starty + 1;
        int starty2 = starty + 2;
        int starty3 = starty + 3;
        int startx1 = startx + 1;
        int startx2 = startx + 2;
        int startx3 = startx + 3;
        int k, i;
        
        temp.Mat[0][0] = MIn.Mat[starty][startx];
        temp.Mat[1][0] = MIn.Mat[starty][startx1];
        temp.Mat[2][0] = MIn.Mat[starty][startx2];
        temp.Mat[3][0] = MIn.Mat[starty][startx3];
        temp.Mat[4][0] = MIn.Mat[starty1][startx];
        temp.Mat[5][0] = MIn.Mat[starty1][startx1];
        temp.Mat[6][0] = MIn.Mat[starty1][startx2];
        temp.Mat[7][0] = MIn.Mat[starty1][startx3];
        temp.Mat[8][0] = MIn.Mat[starty2][startx];
        temp.Mat[9][0] = MIn.Mat[starty2][startx1];
        temp.Mat[10][0] = MIn.Mat[starty2][startx2];
        temp.Mat[11][0] = MIn.Mat[starty2][startx3];
        temp.Mat[12][0] = MIn.Mat[starty3][startx];
        temp.Mat[13][0] = MIn.Mat[starty3][startx1];
        temp.Mat[14][0] = MIn.Mat[starty3][startx2];
        temp.Mat[15][0] = MIn.Mat[starty3][startx3];

        for (i = 0; i<inversedXiYj.jumlahBaris;i++){
            for (k = 0; k < inversedXiYj.jumlahKolom; ++k){
                temp2.Mat[i][0] += inversedXiYj.Mat[i][k] * temp.Mat[k][0];
            }
        }

        return temp2;
    }


    //util
    public static ImageUpscMatriks16 invIdentitas(ImageUpscMatriks16 MIn){
        ImageUpscMatriks16 MTemp = new ImageUpscMatriks16();
        ImageUpscMatriks16 MId = new ImageUpscMatriks16();
        double cache = 0;
        int lenNon0 = 0;
        int kolom = 0;
        int kolom2 = 0;
        int kolomSearch = 0;
        int baris = 0;
        int i = 0;
        int j = 0;
        boolean adaNon0;

        MTemp = cloneMatriks(MIn);

        //Bikin matriks identitas
        MId.jumlahBaris = MIn.jumlahBaris;
        MId.jumlahKolom = MIn.jumlahKolom;
        for (i = 0; i <MId.jumlahBaris; i++){
            for (j = 0; j < MId.jumlahKolom; j++){
                if (i == j){
                    MId.Mat[i][j] = 1;
                }
                else {
                    MId.Mat[i][j] = 0;
                }
            }
        }

        //gauss
        //compact 0 pertama
        while ((lenNon0 < MTemp.jumlahBaris) && (kolom < MTemp.jumlahKolom)) {
            adaNon0 = false;
            if (MTemp.Mat[lenNon0][kolom] == 0) {
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MTemp.jumlahBaris) && (!adaNon0)) {
                    if (MTemp.Mat[kolomSearch][kolom] != 0) {
                        adaNon0 = true;
                        MTemp = swapBaris(MTemp, kolomSearch, lenNon0);
                        MId = swapBaris(MId, kolomSearch, lenNon0);
                        lenNon0 += 1;
                    }
                    else{
                        kolomSearch += 1;
                    }
                }
                if (!adaNon0) {
                    kolom += 1;
                }
            }
            else{
                lenNon0 += 1;
            }
        }
        //endcompact 0 pertama

        kolom = 0;
        baris = 0;
        while (kolom < MTemp.jumlahKolom) {
            if (MTemp.Mat[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = baris + 1; i < MTemp.jumlahBaris; i++){
                    cache = MTemp.Mat[i][kolom]/MTemp.Mat[baris][kolom];
                    MTemp = barisMinKaliBaris(MTemp, i, baris, cache);
                    MId = barisMinKaliBaris(MId, i, baris, cache);
                }

                cache = 1/MTemp.Mat[baris][kolom];

                MTemp = barisXkonstanta(MTemp, baris, cache);
                MId = barisXkonstanta(MId, baris, cache);

                //compact 0 kedua
                lenNon0 = 0;
                kolom2 = 0;
                kolomSearch = 0;
                while ((lenNon0 < MTemp.jumlahBaris) && (kolom2 < MTemp.jumlahKolom)) {
                    adaNon0 = false;
                    if (MTemp.Mat[lenNon0][kolom2] == 0) {
                        kolomSearch = lenNon0 + 1;
                        while ((kolomSearch < MTemp.jumlahBaris) && (!adaNon0)) {
                            if (MTemp.Mat[kolomSearch][kolom2] != 0) {
                                adaNon0 = true;
                                MTemp = swapBaris(MTemp, kolomSearch, lenNon0);
                                MId = swapBaris(MId, kolomSearch, lenNon0);
                                lenNon0 += 1;
                            }
                            else{
                                kolomSearch += 1;
                            }
                        }
                        if (!adaNon0) {
                            kolom2 += 1;
                        }
                    }
                    else{
                        lenNon0 += 1;
                    }
                }
                //endcompact 0 kedua
                kolom += 1;
                baris += 1;
            }
        }
        //endgauss

        //jordan
        kolom = 0;
        baris = 0;
        while (kolom < MTemp.jumlahKolom) {
            if (MTemp.Mat[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = 0; i < baris; i++){
                    if (i != baris){
                        cache = MTemp.Mat[i][kolom]/MTemp.Mat[baris][kolom];
                        MTemp = barisMinKaliBaris(MTemp, i, baris, cache);
                        MId = barisMinKaliBaris(MId, i, baris, cache);
                    }
                }
                kolom += 1;
                baris += 1;
            }
        }
        //endjordan

        return MId;
    }


    public static ImageUpscMatriks16 cloneMatriks(ImageUpscMatriks16 MIn){
        /* Menduplikasi matriks */
            // Kamus Lokal
            ImageUpscMatriks16 MOut = new ImageUpscMatriks16();
            MOut.jumlahKolom = MIn.jumlahKolom;
            MOut.jumlahBaris = MIn.jumlahBaris;
            // Algoritma
            for(int i=0; i<MIn.jumlahBaris; i++){
                for(int j=0; j<MIn.jumlahKolom; j++) {
                    MOut.Mat[i][j] = MIn.Mat[i][j];
                }
            }
            return MOut;
        }
    
        public static ImageUpscMatriks16 swapBaris(ImageUpscMatriks16 MIn, int b1, int b2){
            ImageUpscMatriks16 MOut = new ImageUpscMatriks16();
            MOut = cloneMatriks(MIn);
            MOut.Mat[b1] = MIn.Mat[b2];
            MOut.Mat[b2] = MIn.Mat[b1];
            return MOut;
        }
    
        public static ImageUpscMatriks16 barisXkonstanta(ImageUpscMatriks16 MIn, int baris, double konstanta){
            ImageUpscMatriks16 MOut = new ImageUpscMatriks16();
            MOut = cloneMatriks(MIn);
            for (int i = 0; i < MOut.jumlahKolom; i++){
                MOut.Mat[baris][i] *= konstanta;
            }
            return MOut;
        }
    
        public static ImageUpscMatriks16 barisMinKaliBaris(ImageUpscMatriks16 MIn, int barisTujuan, int barisPengurang, double konstanta){
            ImageUpscMatriks16 MOut = new ImageUpscMatriks16();
            MOut = cloneMatriks(MIn);
            for (int i = 0; i < MOut.jumlahKolom; i++){
                MOut.Mat[barisTujuan][i] -= konstanta*MOut.Mat[barisPengurang][i];
            }
            return MOut;
        }

        static void tidyUp(ImageUpscMatriks16 MIn){
            for(int i =0; i < MIn.jumlahBaris; i++){
                for(int j = 0; j < MIn.jumlahKolom; j++){
                    if (MIn.Mat[i][j] < 0.00000000001 && MIn.Mat[i][j] > -0.00000000001){
                        MIn.Mat[i][j] = 0;
                    }
                    else if (MIn.Mat[i][j] < 1.00000000001 && MIn.Mat[i][j] > 0.99999999999){
                        MIn.Mat[i][j] = 1;
                    }
                }
            }
        }

        
}
