
public class operasiMatriks{

    // jujur yg dibawah ini bingung mau di operasi ato di matriks.java
    
    /*** VALIDASI MATRIKS ***/
    static boolean isEqual(matriks M1, matriks M2){
    /* Mendapatkan true jika Matriks 1 berdimensi sama dengan Matriks 2 */
        return ((M1.jumlahBaris == M2.jumlahBaris) && (M1.jumlahKolom == M2.jumlahKolom));
    }

    static matriks cloneMatriks(matriks MIn){
    /* Menduplikasi matriks */
        // Kamus Lokal
        matriks MOut = new matriks();
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

    /*** OPERASI MATRIKS ***/
    static matriks pertambahanMatriks(matriks M1, matriks M2){
    /* Menambahkan matriks*/
        //prekondisi ukuran M1 == ukuran M2
        // Kamus Lokal
        matriks MOut = new matriks();
        int i,j;
        // Algoritma
        MOut = cloneMatriks(M1);

        for(i = 0; i < M1.jumlahBaris; i++){
            for(j = 0; j < M1.jumlahKolom; j++) {
                MOut.Mat[i][j] = M1.Mat[i][j] + M2.Mat[i][j];
            }
        }
        return MOut;
    }

    static matriks penguranganMatriks(matriks M1, matriks M2){
    /* Pengurangan dua matriks*/
        //prekondisi ukuran M1 == ukuran M2
        // Kamus Lokal
        matriks MOut = new matriks();
        int i,j;
        // Algoritma
        for(i = 0; i < M1.jumlahBaris; i++){
            for(j = 0; j < M1.jumlahKolom; j++) {
                MOut.Mat[i][j] = M1.Mat[i][j] - M2.Mat[i][j];
            }
        }
        return MOut;
    }

    static matriks perkalianMatriks(matriks M1, matriks M2){
    /* Perkalian dua matriks*/
        //prekondisi ukuran M1 aXb == ukuran M2 bXc
        //jumlah kolom M1 = jumlah baris M2
        //Hasilnya matrix aXc
        
        // Kamus Lokal
        matriks MOut = new matriks();
        int i, j, k, sum;
        //Algoritma
        MOut.jumlahKolom = M2.jumlahKolom;
        MOut.jumlahBaris = M1.jumlahBaris;
        for (i = 0; i < M1.jumlahBaris; i++){
            for (j =0; j < M2.jumlahKolom; j++){
                sum = 0;

                for (k = 0; k < M1.jumlahKolom; k++){
                    sum += M1.Mat[i][k] * M2.Mat[k][j];
                }   
                MOut.Mat[i][j] = sum;
            }
        }
        return MOut;
    }

    static matriks transpose(matriks MIn){
    /* Mengeluarkan matriks transpose */
        // Kamus Lokal
        matriks MOut = new matriks();
        MOut.jumlahKolom = MIn.jumlahKolom;
        MOut.jumlahBaris = MIn.jumlahBaris;

        // Algoritma
        for(int i=0; i<MIn.jumlahBaris; i++){
            for(int j=0; j<MIn.jumlahKolom; j++) {
                MOut.Mat[j][i] = MIn.Mat[i][j];
            }
        }
        return MOut;
    }

    /*** OPERASI BARIS ELEMENTER (OBE) ***/
    static matriks swapBaris(matriks MIn, int b1, int b2){
        matriks MOut = new matriks();

        MOut = cloneMatriks(MIn);

        MOut.Mat[b1] = MIn.Mat[b2];
        MOut.Mat[b2] = MIn.Mat[b1];

        return MOut;
    }

    static matriks barisXkonstanta(matriks MIn, int baris, double konstanta){
        matriks MOut = new matriks();


        MOut = cloneMatriks(MIn);

        for (int i = 0; i < MOut.jumlahKolom; i++){
            MOut.Mat[baris][i] *= konstanta;
        }

        return MOut;
    }

    static matriks barisMinKaliBaris(matriks MIn, int barisTujuan, int barisPengurang, double konstanta){
        matriks MOut = new matriks();

        MOut = cloneMatriks(MIn);

        for (int i = 0; i < MOut.jumlahKolom; i++){
            MOut.Mat[barisTujuan][i] -= konstanta*MOut.Mat[barisPengurang][i];
        }

        return MOut;
    }


    static matriks compact0(matriks MIn){
        //Madetin 0 ke bagian bawah
        matriks MOut = new matriks();
        int kolom = 0;
        int lenNon0 = 0;
        int kolomSearch;
        boolean adaNon0;

        MOut = cloneMatriks(MIn);

        while ((lenNon0 < MOut.jumlahBaris) || (kolom < MOut.jumlahKolom)) {
            adaNon0 = false;

            if (MOut.Mat[lenNon0][kolom] == 0) {
                
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MOut.jumlahBaris) && (!adaNon0)) {
                    if (MOut.Mat[kolomSearch][kolom] != 0) {
                        adaNon0 = true;
                        MOut = swapBaris(MOut, kolomSearch, lenNon0);
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
        return MOut;
    }


    static matriks gauss(matriks MIn){
        matriks MOut = new matriks();
        int kolom = 0;
        int baris = 0;
        int i;

        MOut = compact0(MIn);

        while (kolom < MOut.jumlahKolom-1) {
            if (MOut.Mat[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = baris + 1; i < MOut.jumlahBaris; i++){
                    MOut = barisMinKaliBaris(MOut, i, baris, MOut.Mat[i][kolom]/MOut.Mat[baris][kolom]);
                }

                MOut = barisXkonstanta(MOut, baris, 1/MOut.Mat[baris][kolom]);

                MOut = compact0(MOut);
                kolom += 1;
                baris += 1;
            }
        }

        return MOut;
    }


    static matriks gaussJordan(matriks MIn){
        matriks MOut = new matriks();
        int kolom = 0;
        int baris = 0;
        int i;

        MOut = gauss(MIn);

        while (kolom < MOut.jumlahKolom-1) {

            if (MOut.Mat[baris][kolom] == 0) {
                kolom += 1;
            }

            else{
                for(i = 0; i < baris; i++){
                    if (i != baris){
                        MOut = barisMinKaliBaris(MOut, i, baris, MOut.Mat[i][kolom]/MOut.Mat[baris][kolom]);
                    }
                }

                kolom += 1;
                baris += 1;
            }
        }

        return MOut;
    }


    static double determinan(matriks MIn){
    /* Mengembalikan determinan matriks */
        //prekondisi berukuran aXa
        
        // Kamus Lokal
        double det = 1;
        int kolom = 0;
        int lenNon0 = 0;
        int kolomSearch;
        boolean adaNon0;
        matriks MTemp = new matriks();

        // Algoritma


        MTemp = cloneMatriks(MIn);
        
        //Padetin 0 dulu
        while ((lenNon0 < MTemp.jumlahBaris) || (kolom < MTemp.jumlahKolom)) {
            adaNon0 = false;

            if (MTemp.Mat[lenNon0][kolom] == 0) {
                
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MTemp.jumlahBaris) && (!adaNon0)) {
                    if (MTemp.Mat[kolomSearch][kolom] != 0) {
                        adaNon0 = true;
                        MTemp = swapBaris(MTemp, kolomSearch, lenNon0);
                        det *= -1;
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


        //Kalo awal 0 ya udah 0
        if (MTemp.Mat[0][0] == 0){
            det = 0;
        }

        //Kalo engga diubah jadi matriks segitiga
        else{
            for (int i = 0; i < MTemp.jumlahKolom; i++){
                for (int j = i+1; j < MTemp.jumlahBaris; j++){                    
                    MTemp = barisMinKaliBaris(MTemp, j, i, MTemp.Mat[j][i]/MTemp.Mat[i][i]);
                }

                //Padetin 0 lagi
                kolom = 0;
                lenNon0 = 0;
                while ((lenNon0 < MTemp.jumlahBaris) || (kolom < MTemp.jumlahKolom)) {
                    adaNon0 = false;
                    if (MTemp.Mat[lenNon0][kolom] == 0) {
                        kolomSearch = lenNon0 + 1;
                        while ((kolomSearch < MTemp.jumlahBaris) && (!adaNon0)) {
                            if (MTemp.Mat[kolomSearch][kolom] != 0) {
                                adaNon0 = true;
                                MTemp = swapBaris(MTemp, kolomSearch, lenNon0);
                                det *= -1;
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

                //diagonal dikaliin
                det *= MTemp.Mat[i][i];
            }
        }

        //dibuletin 5 angka dibelakang koma
        det = Math.round(det *10000) / 10000;
        return det;
    }
 
}