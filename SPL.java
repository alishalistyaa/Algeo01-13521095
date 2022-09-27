public class SPL {
    /* Persamaan SPL dari Gauss atau Gauss Jordan */
    
    static int checkSPL(matriks m) {
        // PREKONDISI: matriks m adalah matriks gauss/gauss jordan spl (tidak terslice)
        int x = 1;
        if (m.Mat[m.jumlahBaris - 1][m.jumlahKolom - 2] == 0)
        for (int i = 0; i < m.jumlahKolom; i++) {
            if (i == 0) {}
        }
        return x;
    }

    static void SolusiUnik(matriks MIn){
    /* Mencari hasil solusi unik dari persamaan Gauss */
    //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
        // Kamus Lokal
        int i, j;
        double cache;
        double arrayHasil[] = new double[MIn.jumlahKolom-1];
        
        // Algoritma
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayHasil[i] = 0;
        }

        for(i = MIn.jumlahBaris-1; i > -1; i--){

            cache = MIn.Mat[i][MIn.jumlahKolom-1];
            for(j = i; j < MIn.jumlahKolom-1; j++){
                cache -= arrayHasil[j] * MIn.Mat[i][j];
            }

            arrayHasil[i] = cache;
        }

                
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(arrayHasil[i]);
            System.out.print(", \n");
        }
    }
    // blm jadi
    static void SolusiBanyak(matriks MIn){
    /* Mencari hasil solusi unik dari persamaan Gauss */
    //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
        // Kamus Lokal
        int i, j;
        double cache;
        double arrayHasil[] = new double[MIn.jumlahKolom-1];
        char arrayChar[] = new char[MIn.jumlahKolom-1];
        String arrayString[] = new String[MIn.jumlahKolom-1];
        char var;
        // Algoritma
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayHasil[i] = 0;
        }

        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayChar[i] = '/';
        }

        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayString[i] = "";
        }


        /*
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayVar[i] = var;
            if (var == 'Z'){
                var = 'A';
            }
            else if (var == 'T'){
                var = 'a';
            }
            else if (var == 'z'){
                var = ' ';
            }
        } */


        var = 'S';
        for(i = MIn.jumlahBaris-1; i > -1; i--){

            cache = MIn.Mat[i][MIn.jumlahKolom-1];

            for(j = cari1(MIn, i) + 1; j < MIn.jumlahKolom-1; j++){

                if (arrayHasil[j] == 0 && MIn.Mat[i][j] != 0) {
                    if  (arrayChar[j] == '/'){
                        arrayChar[j] = var;
                        if (var == 'Z'){
                            var = 'A';
                        }
                        else if (var == 'R'){
                            var = 'a';
                        }
                        else var += 1;
                    }

                    double cacheConst = (-1)*MIn.Mat[i][j];

                    for (int k = MIn.jumlahKolom-2; k > cari1(MIn, i); k--){
                        if (arrayHasil[k] != 0){

                            int baris1 = MIn.jumlahBaris-1;
                            while (MIn.Mat[baris1][k] != 1) {
                                baris1 -=1;
                            }
                            cacheConst += (MIn.Mat[i][k])*(MIn.Mat[baris1][j]);
                        }
                    }

                    if (cacheConst > 0){
                        arrayString[cari1(MIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                    }
                    else if (cacheConst < 0) {
                        arrayString[cari1(MIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                    }
                }

                else{
                    cache -= arrayHasil[j] * MIn.Mat[i][j];
                }
            }
            arrayHasil[cari1(MIn, i)] = cache;
        }

                
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            if (arrayHasil[i] == 0){
                if (arrayChar[i] == '/'){
                    arrayChar[i] = var;
                    if (var == 'Z'){
                        var = 'A';
                    }
                    else if (var == 'R'){
                        var = 'a';
                    }
                    else var += 1;
                }
                System.out.print(arrayChar[i]);
            }
            else {
                System.out.print(arrayHasil[i]);
            }
            System.out.print(arrayString[i]);
            System.out.print(", \n");
        }
    }


    static int cari1(matriks MIn, int baris){
        //buat nyari 1 pertama di suatu baris
        int kolom;
        boolean ada = false;

        kolom = 0;
        while ((!ada) && (kolom < MIn.jumlahKolom)){
            if (MIn.Mat[baris][kolom] == 1){
                ada = true;
            }
            else{
                kolom += 1;
            }
        }

        return kolom;
    }


    // blm jadi
    static void SolusiNone(matriks MIn){
        /* Mencari hasil dari persamaan Gauss */
        // Kamus Lokal

        // Algoritma
        
    }
}