
public class operasiMatriks{

    // jujur yg dibawah ini bingung mau di operasi ato di matriks.java
    
    /*** VALIDASI MATRIKS ***/
    static boolean isEqual(matriks M1, matriks M2){
    /* Mendapatkan true jika Matriks 1 berdimensi sama dengan Matriks 2 */
        return ((M1.panjangCol == M2.panjangCol) && (M1.panjangRow == M2.panjangRow));
    }

    static matriks cloneMatriks(matriks MIn){
    /* Menduplikasi matriks */
        // Kamus Lokal
        matriks MOut = new matriks();
        MOut.panjangRow = MIn.panjangRow;
        MOut.panjangCol = MIn.panjangCol;
        // Algoritma
        for(int i=0; i<MIn.panjangCol; i++){
            for(int j=0; j<MIn.panjangRow; j++) {
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

        for(i = 0; i < M1.panjangCol; i++){
            for(j = 0; j < M1.panjangRow; j++) {
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
        for(i = 0; i < M1.panjangCol; i++){
            for(j = 0; j < M1.panjangRow; j++) {
                MOut.Mat[i][j] = M1.Mat[i][j] - M2.Mat[i][j];
            }
        }
        return MOut;
    }

    static matriks perkalianMatriks(matriks M1, matriks M2){
    /* Perkalian dua matriks*/
        //prekondisi ukuran M1 aXb == ukuran M2 cXa
        //jumlah kolom M1 = jumlah baris M2
        //Hasilnya matrix cXb
        
        // Kamus Lokal
        matriks MOut = new matriks();
        int i, j, k, sum;
        //Algoritma
        MOut.panjangRow = M2.panjangRow;
        MOut.panjangCol = M1.panjangCol;
        for (i = 0; i < M1.panjangCol; i++){
            for (j =0; j < M2.panjangRow; j++){
                sum = 0;

                for (k = 0; k < M1.panjangRow; k++){
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
        MOut.panjangRow = MIn.panjangRow;
        MOut.panjangCol = MIn.panjangCol;

        // Algoritma
        for(int i=0; i<MIn.panjangCol; i++){
            for(int j=0; j<MIn.panjangRow; j++) {
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

        for (int i = 0; i < MOut.panjangRow; i++){
            MOut.Mat[baris][i] *= konstanta;
        }

        return MOut;
    }

    static matriks barisMinKaliBaris(matriks MIn, int barisTujuan, int barisPengurang, double konstanta){
        matriks MOut = new matriks();

        MOut = cloneMatriks(MIn);

        for (int i = 0; i < MOut.panjangRow; i++){
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

        while ((lenNon0 < MOut.panjangCol) || (kolom < MOut.panjangRow)) {
            adaNon0 = false;

            if (MOut.Mat[lenNon0][kolom] == 0) {
                
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MOut.panjangCol) && (!adaNon0)) {
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

        while (kolom < MOut.panjangRow-1) {
            if (MOut.Mat[baris][kolom] == 0) {
                kolom += 1;
            }
            else{
                for(i = baris + 1; i < MOut.panjangCol; i++){
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

        while (kolom < MOut.panjangRow-1) {

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
        while ((lenNon0 < MTemp.panjangCol) || (kolom < MTemp.panjangRow)) {
            adaNon0 = false;

            if (MTemp.Mat[lenNon0][kolom] == 0) {
                
                kolomSearch = lenNon0 + 1;
                while ((kolomSearch < MTemp.panjangCol) && (!adaNon0)) {
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
            for (int i = 0; i < MTemp.panjangRow; i++){
                for (int j = i+1; j < MTemp.panjangCol; j++){                    
                    MTemp = barisMinKaliBaris(MTemp, j, i, MTemp.Mat[j][i]/MTemp.Mat[i][i]);
                }

                //Padetin 0 lagi
                kolom = 0;
                lenNon0 = 0;
                while ((lenNon0 < MTemp.panjangCol) || (kolom < MTemp.panjangRow)) {
                    adaNon0 = false;
                    if (MTemp.Mat[lenNon0][kolom] == 0) {
                        kolomSearch = lenNon0 + 1;
                        while ((kolomSearch < MTemp.panjangCol) && (!adaNon0)) {
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

    // for finding inverse w adj method and determinant w cofactor expansion method
    static matriks slice(matriks MIn, int i, int j) {
        // mengambil elemen matriks yang BUKAN berbaris i atau BUKAN berkolom j
        matriks MOut = new matriks();
        MOut.panjangRow = MIn.panjangRow - 1;
        MOut.panjangCol = MIn.panjangCol - 1;
        int count = 0;
        for (int a = 0; a < MIn.panjangRow; a++) {
            for (int b = 0; b < MIn.panjangCol; b++) {
                if (!(a == i || b == j)) {
                    count++;
                    MOut.Mat[(count - 1) / MOut.panjangCol][(count - 1) % MOut.panjangCol] = MIn.Mat[a][b];
                }
            }
        }
        return MOut;
    }
    
    static double cof(matriks MIn, int i, int j) {
        // cof dari mat minor, MIn harus matriks persegi
        double cof;
        cof = determinan(slice(MIn, i, j)); //ril quesyen: utk ekspansi kofaktor brti determinannya gblh pake ini
        if ((i + j) % 2 != 0) { // sbnrny i + 1 + j + 1 karna i j di mtk itu mulai dr 1, tp 2 % 2 = 0 jd g ngaruh
            cof *= (-1);
        }
        return cof;
    }

    static matriks matCof(matriks MIn) {
        matriks MOut = new matriks();
        MOut.panjangRow = MIn.panjangRow;
        MOut.panjangCol = MIn.panjangCol;
        for (int i = 0; i < MIn.panjangRow; i++){
            for (int j = 0; j < MIn.panjangCol; j++) {
                MOut.Mat[i][j] = cof(MIn, i, j);
            }
        }
        return MOut;
    }

    /* kenapa row 0 doang? jadi buat nyari determinan pake ekspansi kofaktor itu kan rekursif ya
       (coba aja sendiri kalo matriksnya 4 x 4), sedangkan di sini untuk setiap determinan harus pake ekspansi kofaktor.
       sedangkan si fungsi slice() itu bakal ngebuang bbrp baris dan kolom. satu2nya yang bisa dipastiin dari setiap matriks
       yang diinput ke fungsi ini ya yang pasti punya baris 0 (gabisa milih baris mana karna takutnya dia ngakses baris di luar 
       indeks yang valid) */
    static double detExCofRow0 (matriks MIn) {
        // PREKONDISI: MIn matriks persegi
        double det;
        if (MIn.panjangRow == 1) {
            det = MIn.Mat[0][0];
        } else if (MIn.panjangRow == 2) {
            det = MIn.Mat[0][0] * MIn.Mat[1][1] - MIn.Mat[1][0] * MIn.Mat[0][1];
        } else {
            det = 0;
            for (int j = 0; j < MIn.panjangRow; j++) {
                if (j % 2 == 0) {
                    det += MIn.Mat[0][j] * detExCofRow0(slice(MIn, 0, j));
                } else {
                    det += (-1) * MIn.Mat[0][j] * detExCofRow0(slice(MIn, 0, j));
                }
            }
        }
        return det;
    }

    static double detExCofCol0 (matriks MIn) {
        // PREKONDISI: MIn matriks persegi
        double det;
        if (MIn.panjangCol == 1) {
            det = MIn.Mat[0][0];
        } else if (MIn.panjangCol == 2) {
            det = MIn.Mat[0][0] * MIn.Mat[1][1] - MIn.Mat[1][0] * MIn.Mat[0][1];
        } else {
            det = 0;
            for (int i = 0; i < MIn.panjangCol; i++) {
                if (i % 2 == 0) {
                    det += MIn.Mat[i][0] * detExCofRow0(slice(MIn, i, 0));
                } else {
                    det += (-1) * MIn.Mat[i][0] * detExCofRow0(slice(MIn, i, 0));
                }
            }
        }
        return det;
    }

    /* buat detExCofRow sm detExCofCol ini gw masih bingung sebnernya harus bisa milih baris/kolom atau ngga,
    tp case terbaik ttp di kalo pilih baris 0 atau kolom 0 (ini sepertinya harus ditanyakan ke asisten)
    
    static double detExCofRow (matriks MIn, int rowIdx) {
        // PREKONDISI: MIn matriks persegi
        // rowIdx: [0..(nRow-1)]
        double det = 0;
        for (int j = 0; j < MIn.panjangCol; j++) {
            det += MIn.Mat[rowIdx][j] * cof(MIn, rowIdx, j);
        }
        return det;
    }

    static double detExCofCol (matriks MIn, int colIdx) {
        // PREKONDISI: MIn matriks persegi
        // colIdx: [0..(nCol-1)]
        double det = 0;
        for (int i = 0; i < MIn.panjangRow; i++) {
            det += MIn.Mat[i][colIdx] * cof(MIn, i, colIdx);
        }
        return det;
    }
    */

    static matriks inverseAdj(matriks MIn) {
        // PREKONDISI: MIn matriks persegi, DET MIn != 0
        matriks MOut = new matriks();
        MOut.panjangRow = MIn.panjangRow;
        MOut.panjangCol = MIn.panjangCol;
        MOut = transpose(matCof(MIn));
        for (int i = 0; i < MIn.panjangRow; i++){
            for (int j = 0; j < MIn.panjangCol; j++) {
                MOut.Mat[i][j] /= determinan(MIn);
            }
        }
        return MOut;
    }
    // nitip aja biar inget : static matriks inverseGJ(matriks MIn) {}
}
