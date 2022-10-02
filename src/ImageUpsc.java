package src;

public class ImageUpsc {
    public static matriks zoning(matriks MIn, int sizex, int sizey, int startx, int starty){
        //memotong daerah matriks
        matriks temp = new matriks();
        int baris, kolom;
        temp.jumlahBaris = sizey;
        temp.jumlahKolom = sizex;
        baris = 0;
        for (int i = starty; i < starty+sizey; i ++){
            kolom = 0;
            for (int j = startx; j < startx+sizex; j ++){
                temp.Mat[baris][kolom] = MIn.Mat[i][j];
                kolom += 1;
            }
            baris += 1;
        }
        return temp;
    }

    //semua padding
    static void paddingAtas(matriks MIn){
        MIn.jumlahBaris +=1;
        for (int i=MIn.jumlahBaris-1; i > 0 ; i--){
            for (int j = 0; j < MIn.jumlahKolom; j++){
                MIn.Mat[i][j] = MIn.Mat[i-1][j];
            }
        }
    }

    static void paddingBawah(matriks MIn){
        MIn.jumlahBaris += 1;
        for (int i=0; i < MIn.jumlahBaris; i++){
            MIn.Mat[MIn.jumlahBaris-1][i] = MIn.Mat[MIn.jumlahBaris-2][i];
            }
    }

    static void paddingKanan(matriks MIn){
        MIn.jumlahKolom += 1;
        for (int i=0; i < MIn.jumlahBaris; i++){
            MIn.Mat[i][MIn.jumlahKolom-1] = MIn.Mat[i][MIn.jumlahKolom-2];
            }
    }

    static void paddingKiri(matriks MIn){
        MIn.jumlahKolom += 1;
        for (int j = MIn.jumlahKolom-1; j > 0; j--){
            for (int i=0; i < MIn.jumlahBaris; i++){
                MIn.Mat[i][j] = MIn.Mat[i][j-1];
            }
        }
    }
    
    static void padding(matriks MIn){
        paddingAtas(MIn);
        paddingBawah(MIn);
        paddingKiri(MIn);
        paddingKanan(MIn);
    }
    //akhir padding

    //library interpolasi
    static matriks interpolateStandar(matriks MIn, int startx, int starty, matriks inversedXiYj){
        matriks temp = new matriks();
        matriks fxy = new matriks();
        temp.jumlahBaris = 3;
        temp.jumlahKolom = 3;
        fxy = zoning(MIn, 4, 4, startx, starty);
        fxy = linearize(fxy);
        fxy = aij(fxy, inversedXiYj);
        float pointX = 0;
        float pointY = 0;
        for (int i = 0; i < temp.jumlahBaris; i++){
            for (int j = 0; j < temp.jumlahKolom; j++){
                temp.Mat[i][j] = bicIntpol(fxy, pointX, pointY);
                pointX += 0.5;
            }
            pointY += 0.5;
        }
        return temp;
    }

    static matriks interpolateTengahX(matriks MIn, int startx, int starty, matriks inversedXiYj){
        matriks temp = new matriks();
        matriks fxy = new matriks();
        temp.jumlahBaris = 3;
        temp.jumlahKolom = 4;
        fxy = zoning(MIn, 4, 4, startx, starty);
        fxy = linearize(fxy);
        fxy = aij(fxy, inversedXiYj);
        float pointX = 0;
        float pointY = 0;
        for (int i = 0; i < temp.jumlahBaris; i++){
            for (int j = 0; j < temp.jumlahKolom; j++){
                temp.Mat[i][j] = bicIntpol(fxy, pointX, pointY);
                pointX += 0.33;
            }
            pointY += 0.5;
        }
        return temp;
    }

    static matriks interpolateTengahY(matriks MIn, int startx, int starty, matriks inversedXiYj){
        matriks temp = new matriks();
        matriks fxy = new matriks();
        temp.jumlahBaris = 4;
        temp.jumlahKolom = 3;
        fxy = zoning(MIn, 4, 4, startx, starty);
        fxy = linearize(fxy);
        fxy = aij(fxy, inversedXiYj);
        float pointX = 0;
        float pointY = 0;
        for (int i = 0; i < temp.jumlahBaris; i++){
            for (int j = 0; j < temp.jumlahKolom; j++){
                temp.Mat[i][j] = bicIntpol(fxy, pointX, pointY);
                pointX += 0.5;
            }
            pointY += 0.33;
        }
        return temp;
    }

    static matriks interpolateTengahXY(matriks MIn, int startx, int starty, matriks inversedXiYj){
        matriks temp = new matriks();
        matriks fxy = new matriks();
        temp.jumlahBaris = 4;
        temp.jumlahKolom = 4;
        fxy = zoning(MIn, 4, 4, startx, starty);
        fxy = linearize(fxy);
        fxy = aij(fxy, inversedXiYj);
        float pointX = 0;
        float pointY = 0;
        for (int i = 0; i < temp.jumlahBaris; i++){
            for (int j = 0; j < temp.jumlahKolom; j++){
                temp.Mat[i][j] = bicIntpol(fxy, pointX, pointY);
                pointX += 0.33;
            }
            pointY += 0.33;
        }
        return temp;
    }
    //akhir library interpolasi
    //fungsi interpolasi utama
    public static matriks interpolate2x(matriks MIn){
        matriks temp = new matriks();
        matriks mover = new matriks();
        matriks inversedXiYj = new matriks();

        System.out.print("Initializing.");
        inversedXiYj = operasiMatriks.invIdentitas(BicubicInterpolation.xiyj());
        System.out.print(".");
        tidyUp(inversedXiYj);

        
        System.out.print(".\n");
        temp.resetCap(2*MIn.CAPACITY);
        temp.jumlahBaris = 2*MIn.jumlahBaris;
        temp.jumlahKolom = 2*MIn.jumlahKolom;

        int x = 0;
        int y = 0;
        int totalOperation = (MIn.jumlahBaris-1)*(MIn.jumlahKolom-1);
        int operationNum = 0;

        padding(MIn);

        for (int i = 0; i < MIn.jumlahBaris-3; i++){
            x = 0;
            for (int j = 0; j < MIn.jumlahKolom-3; j++){
                if (!((MIn.jumlahBaris % 2 == 0) && (i == (MIn.jumlahBaris-3)/2))){
                    if (!((MIn.jumlahKolom % 2 == 0) && (j == (MIn.jumlahKolom-3)/2))){
                        mover = interpolateStandar(MIn, j, i, inversedXiYj);
                        assignLocation(temp, mover, x, y);
                        x += 2;
                    }
                    else{
                        mover = interpolateTengahX(MIn, j, i, inversedXiYj);
                        assignLocation(temp, mover, x, y);
                        x += 3;
                    }
                }
                else{
                    if (!((MIn.jumlahKolom % 2 == 0) && (j == (MIn.jumlahKolom-3)/2))){
                        mover = interpolateTengahY(MIn, j, i, inversedXiYj);
                        assignLocation(temp, mover, x, y);
                        x += 2;
                    }
                    else{
                        mover = interpolateTengahXY(MIn, i, j, inversedXiYj);
                        assignLocation(temp, mover, x, y);
                        x += 3;
                    }
                }
                operationNum += 1;
                System.out.print(operationNum);
                System.out.print("/");
                System.out.print(totalOperation);
                System.out.print("\n");
            }
            if (!((MIn.jumlahBaris % 2 == 0) && (i == (MIn.jumlahBaris-3)/2))){
                y += 2;
            }
            else{
                y += 3;
            }
        }
        return temp;
    }

    static void assignLocation(matriks Main, matriks submatriks, int x, int y){
        for(int i = 0; i < submatriks.jumlahBaris; i++){
            for (int j = 0; j < submatriks.jumlahKolom; j++){
                Main.Mat[y+i][x+j] = submatriks.Mat[i][j];
            }
        }
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

    static matriks aij (matriks fxy, matriks inversedXiYj) {
        return operasiMatriks.perkalianMatriks(inversedXiYj, fxy);
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

    static void tidyUp(matriks MIn){
        for(int i =0; i < MIn.jumlahBaris; i++){
            for(int j = 0; j < MIn.jumlahKolom; j++){
                if (MIn.Mat[i][j] < 0.0000001 && MIn.Mat[i][j] > -0.0000001){
                    MIn.Mat[i][j] = 0;
                }
            }
        }
    }
}
