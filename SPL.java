public class SPL {
    static void solveSPL(matriks m){
        //utama, menerima matriks gauss/gauss jordan spl (tidak terslice)
        int condition;
        condition = checkSPL(m);

        switch (condition){
            case 0:
            SolusiKosong(m);
            break;

            case 1:
            SolusiUnik(m);
            break;

            case 2:
            SolusiBanyak(m);
            break;

            case 3:
            SolusiNone();
            break;
        }
    }

    /* Persamaan SPL dari Gauss atau Gauss Jordan */
    static int checkSPL(matriks m) {
        // PREKONDISI: matriks m adalah matriks gauss/gauss jordan spl (tidak terslice)
        // 0 = Matriks kosong (semua 0)
        // 1 = Solusi unik
        // 2 = Solusi banyak
        // 3 = Solusi tidak ada
        int x = -999;
        boolean unik;

        if (m.isAllZero()) {
            x = 0;
        }
        else if (!solvable(m)){
            x = 3;
        }
        else if (m.jumlahBaris == m.jumlahKolom-1){
            unik = true;
            for (int i = 0; i < m.jumlahBaris; i++){
                if (m.Mat[i][i] != 1){
                    unik = false;
                }
            }
            if (unik){
                x = 1;
            }
            else x = 2;
        }
        else{
            x = 2;
        }

        return x;
    }

    static void SolusiKosong(matriks MIn){
        char var = 'S';
        char arrayChar[] = new char[MIn.jumlahKolom-1];
        int i;
        for(i = MIn.jumlahKolom-2; i > -1; i--){
            arrayChar[i] = var;
            if (var == 'Z'){
                var = 'A';
            }
            else if (var == 'R'){
                var = 'a';
            }
            else var += 1;
        }

        System.out.println("Persamaan linear kosong, semua variabel nilai memenuhi.");
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            System.out.print("x");
            System.out.print(i+1);
            System.out.print(" = ");
            System.out.print(arrayChar[i]);
            System.out.print(", \n");
        }
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

            arrayHasil[i] = Math.round(cache *10000) / 10000;
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
        boolean trivial;
        double cache;
        double arrayHasil[] = new double[MIn.jumlahKolom-1];
        char arrayChar[] = new char[MIn.jumlahKolom-1];
        String arrayString[] = new String[MIn.jumlahKolom-1];
        char var;
        // Algoritma
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayHasil[i] = 0;
        }for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayChar[i] = '/';
        } for(i = 0; i < MIn.jumlahKolom-1; i++){
            arrayString[i] = "";
        }

        var = 'S';
        MIn = removebaris0(MIn);

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

                    cacheConst = Math.round(cacheConst *10000) / 10000;
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
            arrayHasil[cari1(MIn, i)] = Math.round(cache *10000) / 10000;;
        }
        
        //cek kalo ada jawaban trivial
        trivial = true;
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            if (arrayHasil[i] != 0){
                trivial = false;
            }
        }

        //printing
        //print trivial
        if (trivial) {
            for(i = 0; i < MIn.jumlahKolom-1; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
            }
            System.out.print(0);
            System.out.print("\n");
            System.out.println("atau");
        }

        //print utama
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

    static void SolusiNone(){
        // Kamus Lokal
        // Algoritma
        System.out.println("Persamaan linear tidak memiliki solusi.");
    }

    static int cari1(matriks MIn, int baris){
        //buat nyari 1 pertama di suatu baris
        //dipake di solusi banyak
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


    //Fungi antara buat di splnya
    static matriks removebaris0 (matriks MIn){
        //buat ngilangin baris 0 semua kalo ada
        //dipake di solusi banyak & solvable
        matriks temp = new matriks();
        boolean adabaris0 = true;
        int i, j;

        temp = operasiMatriks.cloneMatriks(MIn);

        i = MIn.jumlahBaris-1;
        while (adabaris0 && i > -1){
            while (adabaris0 && i > -1) {

                j = 0;
                while (adabaris0 && j < MIn.jumlahKolom){
                    if(MIn.Mat[i][j] != 0){
                        adabaris0 = false;
                    }
                    j += 1;
                }

                if (adabaris0){
                    temp = operasiMatriks.sliceLastRow(temp);
                    i = temp.jumlahBaris-1;
                }
            }
        }

        return temp;
    }

    static boolean solvable(matriks MIn){
        //buat deteksi apa matriks punya solusi atau tidak
        //kepake di check
        matriks temp = new matriks();
        boolean solvable = false;
        int j;

        temp = removebaris0(MIn);

        j = 0;
        while (!solvable && j < temp.jumlahKolom-1){
            if (temp.Mat[temp.jumlahBaris-1][j] != 0){
                solvable = true;
            }
            else{
                j += 1;
            }
        }

        return solvable;
    }

    static void SolveInverse(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);

        if (operasiMatriks.detExCofRow0(a) == 0){
            System.out.println("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
        }
        else{
            a = operasiMatriks.invAdj(a);
            fx = operasiMatriks.perkalianMatriks(a, fx);
    
            System.out.print("\n");
            for (int i = 0; i < fx.jumlahBaris; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
                System.out.print(fx.Mat[i][0]);
                System.out.print(", \n");
            }
        }
    }

    static void SolveCramer(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();
        matriks temp = new matriks();

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);
        temp = operasiMatriks.cloneMatriks(a);

        if (operasiMatriks.detExCofRow0(a) == 0){
            System.out.println("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
        }

        else{
            System.out.print("\n");
            for (int i = 0; i < fx.jumlahBaris; i++){
                temp = operasiMatriks.cramerSwap(a, fx, i);

                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
                System.out.print(operasiMatriks.detExCofRow0(temp)/operasiMatriks.detExCofRow0(a));
                System.out.print(", \n");
            }
        }
    }
}