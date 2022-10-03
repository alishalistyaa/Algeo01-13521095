
import java.util.*;
import java.io.*; 

public class SPL {
    static Scanner in = new Scanner (System.in);
    public static void solveSPL(matriks m){
        //utama, menerima matriks gauss/gauss jordan spl (tidak terslice)
        int condition;
        operasiMatriks.tidyUp(m);
        condition = checkSPL(m);

        System.out.print("\n");
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

    public static void solveSPLFile(matriks m){
        // Buat output file
        int condition;
        String filename;

        operasiMatriks.tidyUp(m);
        condition = checkSPL(m);
        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";

        switch (condition){
            case 0:
            SolusiKosongFile(m, filename);
            break;

            case 1:
            SolusiUnikFile(m, filename);
            break;

            case 2:
            SolusiBanyakFile(m, filename);
            break;

            case 3:
            SolusiNoneFile(m, filename);
            break;
        }
    }

    /* Persamaan SPL dari Gauss atau Gauss Jordan */
    public static int checkSPL(matriks m) {
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

    public static void SolusiKosong(matriks MIn){
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

    public static void SolusiUnik(matriks MIn){
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


    public static double recursion(int toplimit, int bottomlimit, int row, int varCol, double arrayHasil[], String arrayString[], matriks MIn){
        double cacheConst = 0;
        for (int k = toplimit; k > bottomlimit; k--){
            if ((arrayHasil[k] != 0 || arrayString[k] != "") && MIn.Mat[row][k] != 0){
                
                int baris1 = MIn.jumlahBaris-1;
                while (MIn.Mat[baris1][k] != 1) {
                    baris1 -=1;
                }
    
                cacheConst += MIn.Mat[row][k]*(MIn.Mat[baris1][varCol]) + recursion(toplimit, cari1(MIn, baris1), baris1, varCol, arrayHasil, arrayString, MIn);
            }
        }
        return cacheConst;
    }

    public static void SolusiBanyak(matriks MIn){
    /* Mencari hasil solusi unik dari persamaan Gauss */
    //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
        // Kamus Lokal
        int i, j, k;
        boolean trivial;
        boolean nolbeneran;
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
                if (arrayHasil[j] == 0) {
                    nolbeneran = true;
                    for(k = j; k < MIn.jumlahKolom-1; k++){
                        if (arrayChar[k] != '/'){
                            nolbeneran = false;
                        }
                    }

                    if (j > 0){
                        for(k = j-1; k > -1; k--){
                            if (MIn.Mat[i][k] != 0){
                                nolbeneran = false;
                            }
                        }
                    }

                    if (arrayString[j] != ""){
                        nolbeneran = true;
                    }
                    if (j == MIn.jumlahKolom-2){
                        nolbeneran = false;
                    }

                    if (!nolbeneran){
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
                        cacheConst += recursion(MIn.jumlahKolom-2, cari1(MIn, i), i, j, arrayHasil, arrayString, MIn);
                        
                        if (cacheConst > 0){
                            arrayString[cari1(MIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                        }
                        else if (cacheConst < 0) {
                            arrayString[cari1(MIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                        }
                    }
                }
                else{
                    cache -= arrayHasil[j] * MIn.Mat[i][j];
                }
            }
            try {
                arrayHasil[cari1(MIn, i)] = cache;
            } catch (Exception e) {
            }
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
                nolbeneran = true;
                for(j = i; j < MIn.jumlahKolom-1; j++){
                    if (arrayChar[j] != '/'){
                        nolbeneran = false;
                    }
                }
                if (arrayString[i] != ""){
                    nolbeneran = true;
                }

                if (!nolbeneran){
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
                    System.out.print(arrayChar[i] + "");
                }
                if (nolbeneran && (arrayString[i] == "")){
                    System.out.print(arrayHasil[i]);
                }
            }
            else {
                System.out.print(arrayHasil[i]);
            }
            System.out.print(arrayString[i]);
            System.out.print(", \n");
        }
    }

    public static void SolusiNone(){
        // Kamus Lokal
        // Algoritma
        System.out.println("Persamaan linear tidak memiliki solusi.");
    }

    //output file
    
    public static void SolusiKosongFile(matriks MIn, String filename){
        char var = 'S';
        char arrayChar[] = new char[MIn.jumlahKolom-1];
        int i, j;
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

        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            //nulis hasil matriks
            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<MIn.jumlahBaris; i++){
                for (j=0; j<MIn.jumlahKolom; j++){
                    bw.write(MIn.Mat[i][j] + ((j == MIn.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            // Write Perline
            bw.write("Persamaan linear kosong, semua variabel nilai memenuhi.");
            bw.newLine();
            for (i = 0; i < MIn.jumlahKolom-1; i++){
                bw.write("x" + (i+1) + " = " + arrayChar[i]);
                if (i == MIn.jumlahKolom-2) {
                    bw.newLine();
                }
                else{
                    bw.write(", ");
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void SolusiUnikFile(matriks MIn, String filename){
    /* Mencari hasil solusi unik dari persamaan Gauss */
    //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
        // Kamus Lokal
        int i, j;
        double cache;
        double arrayHasil[] = new double[MIn.jumlahKolom-1];

        try {
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
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));


            //nulis hasil matriks
            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<MIn.jumlahBaris; i++){
                for (j=0; j<MIn.jumlahKolom; j++){
                    bw.write(MIn.Mat[i][j] + ((j == MIn.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            // Write Perline
            for (i = 0; i < MIn.jumlahKolom-1; i++){
                bw.write("x" + (i+1) + " = " + arrayHasil[i]);
                if (i == MIn.jumlahKolom-2) {
                    bw.newLine();
                }
                else{
                    bw.write(", ");
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    // blm jadi
    public static void SolusiBanyakFile(matriks MIn, String filename){
    /* Mencari hasil solusi unik dari persamaan Gauss */
    //prekondisi matriks yang masuk adalah matriks gauss/gauss jordan
        // Kamus Lokal
        int i, j, k;
        boolean trivial;
        boolean nolbeneran;
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
                if (arrayHasil[j] == 0) {
                    nolbeneran = true;
                    for(k = j; k < MIn.jumlahKolom-1; k++){
                        if (arrayChar[k] != '/'){
                            nolbeneran = false;
                        }
                    }

                    if (j > 0){
                        System.out.println("element " + i + " " + j);
                        for(k = j-1; k < -1; k--){
                            if (MIn.Mat[i][k] != 0){
                                nolbeneran = false;
                            }
                        }
                    }

                    if (arrayString[j] != ""){
                        nolbeneran = true;
                    }
                    if (j == MIn.jumlahKolom-2){
                        nolbeneran = false;
                    }

                    if (!nolbeneran){
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
                        cacheConst += recursion(MIn.jumlahKolom-2, cari1(MIn, i), i, j, arrayHasil, arrayString, MIn);
                        
                        if (cacheConst > 0){
                            arrayString[cari1(MIn, i)] += String.format("+%.2f%c", cacheConst, arrayChar[j]);
                        }
                        else if (cacheConst < 0) {
                            arrayString[cari1(MIn, i)] += String.format("%.2f%c", cacheConst, arrayChar[j]);
                        }
                    }
                }
                else{
                    cache -= arrayHasil[j] * MIn.Mat[i][j];
                }
            }
            try {
                arrayHasil[cari1(MIn, i)] = cache;
            } catch (Exception e) {
            }
            arrayHasil[cari1(MIn, i)] = cache;
        }
        
        //cek kalo ada jawaban trivial
        trivial = true;
        for(i = 0; i < MIn.jumlahKolom-1; i++){
            if (arrayHasil[i] != 0){
                trivial = false;
            }
        }

        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            //nulis hasil matriks
            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<MIn.jumlahBaris; i++){
                for (j=0; j<MIn.jumlahKolom; j++){
                    bw.write(MIn.Mat[i][j] + ((j == MIn.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();

            // Write Perline
            if (trivial) {
                for(i = 0; i < MIn.jumlahKolom-1; i++){
                    bw.write("x" + (i+1) + " = " + 0);
                }
                bw.newLine();
                bw.write("atau");
                bw.newLine();
            }

            for(i = 0; i < MIn.jumlahKolom-1; i++){
                bw.write("x" + (i+1) + " = ");
                if (arrayHasil[i] == 0){
                    nolbeneran = true;
                    for(j = i; j < MIn.jumlahKolom-1; j++){
                        if (arrayChar[j] != '/'){
                            nolbeneran = false;
                        }
                    }
                    if (arrayString[i] != ""){
                        nolbeneran = true;
                    }

                    if (i > 0){
                        for(j = i-1; j > -1; j--){
                            if (MIn.Mat[i][j] != 0){
                                nolbeneran = false;
                            }
                        }
                    }
    
                    if (!nolbeneran){
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
                        bw.write(arrayChar[i]);
                    }

                    if (nolbeneran && arrayString[i] == ""){
                        bw.write(arrayHasil[i] + "");
                    }
                }

                
                else {
                    bw.write(arrayHasil[i] + "");
                }
                bw.write(arrayString[i]);
                bw.write((i == MIn.jumlahKolom-2) ? "\n" : ", \n");
            }
            bw.flush();
            bw.close();
        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void SolusiNoneFile(matriks MIn, String filename){
        int i, j;

        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            //nulis hasil matriks
            bw.write("Hasil pengolahan matriks:");
            bw.newLine();
            for (i= 0; i<MIn.jumlahBaris; i++){
                for (j=0; j<MIn.jumlahKolom; j++){
                    bw.write(MIn.Mat[i][j] + ((j == MIn.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }
            bw.newLine();


            // Write
            bw.write("Persamaan linear tidak memiliki solusi.");
            bw.newLine();
            bw.flush();
            bw.close();
        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static int cari1(matriks MIn, int baris){
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

    public static void SolveInverse(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);

        System.out.print("\n");
        if (a.jumlahBaris != a.jumlahKolom){
            System.out.println("Matriks memerlukan " + a.jumlahKolom + " persamaan untuk dapat disolusikan.");
        }
        else if (operasiMatriks.detExCofRow0(a) == 0){
            System.out.println("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
        }
        else{
            a = operasiMatriks.invAdj(a);
            fx = operasiMatriks.perkalianMatriks(a, fx);
    
            for (int i = 0; i < fx.jumlahBaris; i++){
                System.out.print("x");
                System.out.print(i+1);
                System.out.print(" = ");
                System.out.print(fx.Mat[i][0]);
                System.out.print(", \n");
            }
        }
    }

    public static void SolveInverseFile(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();
        int i, j;
        String filename;

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write Perline
            bw.write("Matriks:");
            bw.newLine();
            for (i= 0; i<M.jumlahBaris; i++){
                for (j=0; j<M.jumlahKolom; j++){
                    bw.write(M.Mat[i][j] + ((j == M.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }

            bw.newLine();
            if (a.jumlahBaris != a.jumlahKolom){
                bw.write("Matriks memerlukan " + a.jumlahKolom + " persamaan untuk dapat disolusikan.");
                bw.newLine();
            }
            else if (operasiMatriks.detExCofRow0(a) == 0){
                bw.write("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
                bw.newLine();
            }
            else{
                a = operasiMatriks.invAdj(a);
                fx = operasiMatriks.perkalianMatriks(a, fx);

                for (i = 0; i < fx.jumlahBaris; i++){
                    bw.write("x" + (i+1) + " = " + fx.Mat[i][0]);
                    if (i == fx.jumlahBaris-1) {
                        bw.newLine();
                    }
                    else{
                        bw.write(", ");
                        bw.newLine();
                    }
                }
            }

            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void SolveCramer(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();
        matriks temp = new matriks();

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);
        temp = operasiMatriks.cloneMatriks(a);

        System.out.print("\n");
        if (a.jumlahBaris != a.jumlahKolom){
            System.out.println("Matriks memerlukan " + a.jumlahKolom + " persamaan untuk dapat disolusikan.");
        }
        else if (operasiMatriks.detExCofRow0(a) == 0){
            System.out.println("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
        }

        else{
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

    public static void SolveCramerFile(matriks M){
        matriks fx = new matriks();
        matriks a = new matriks();
        matriks temp = new matriks();
        int i, j;
        String filename;

        fx = operasiMatriks.takeLastCol(M);
        a = operasiMatriks.sliceLastCol(M);
        temp = operasiMatriks.cloneMatriks(a);

        System.out.print("\nMasukkan nama file: ");
        filename = in.nextLine() + ".txt";
        try {
            // Buat file
            BufferedWriter bw = new BufferedWriter(new FileWriter("./test/" + filename));

            // Write Perline
            bw.write("Matriks:");
            bw.newLine();
            for (i= 0; i<M.jumlahBaris; i++){
                for (j=0; j<M.jumlahKolom; j++){
                    bw.write(M.Mat[i][j] + ((j == M.jumlahKolom-1) ? "" : " "));
                }
            bw.newLine();
            }

            bw.newLine();
            if (a.jumlahBaris != a.jumlahKolom){
                bw.write("Matriks memerlukan " + a.jumlahKolom + " persamaan untuk dapat disolusikan.");
                bw.newLine();
            }
            else if (operasiMatriks.detExCofRow0(a) == 0){
                bw.write("Matriks tidak memiliki inverse sehingga tidak dapat disolusikan.");
                bw.newLine();
            }
            else{
                for (i = 0; i < fx.jumlahBaris; i++){
                    temp = operasiMatriks.cramerSwap(a, fx, i);

                    bw.write("x" + (i+1) + " = " + (operasiMatriks.detExCofRow0(temp)/operasiMatriks.detExCofRow0(a)));
                    if (i == fx.jumlahBaris-1) {
                        bw.newLine();
                    }
                    else{
                        bw.write(", ");
                        bw.newLine();
                    }
                }
            }

            bw.flush();
            bw.close();

        // Handling Error
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}