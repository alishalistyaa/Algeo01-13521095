
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
        int i, j, k;
        double sum;
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

        while ((lenNon0 < MOut.jumlahBaris) && (kolom < MOut.jumlahKolom)) {
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


    static matriks invIdentitas(matriks MIn){
        matriks MTemp = new matriks();
        matriks MId = new matriks();
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


    static double detOBE(matriks MIn){
    /* Mengembalikan determinan matriks */
        //prekondisi berukuran aXa
        //determinan dicari menggunakan metode segitiga atas
        
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
        while ((lenNon0 < MTemp.jumlahBaris) && (kolom < MTemp.jumlahKolom)) {
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
                while ((lenNon0 < MTemp.jumlahBaris) && (kolom < MTemp.jumlahKolom)) {
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
        return det;
    }

    static matriks sliceLastCol(matriks MIn) {
        // buang kolom terakhir
        matriks MOut = new matriks();
        MOut.jumlahBaris = MIn.jumlahBaris;
        MOut.jumlahKolom = MIn.jumlahKolom - 1;
        for (int i = 0; i < MOut.jumlahBaris; i++) {
            for (int j = 0; j < MOut.jumlahKolom; j++) {
                MOut.Mat[i][j] = MIn.Mat[i][j];
            }
        }
        return MOut;
    }

    static matriks sliceLastRow(matriks MIn) {
        // buang kolom terakhir
        matriks MOut = new matriks();
        MOut.jumlahBaris = MIn.jumlahBaris - 1;
        MOut.jumlahKolom = MIn.jumlahKolom;
        for (int i = 0; i < MOut.jumlahBaris; i++) {
            for (int j = 0; j < MOut.jumlahKolom; j++) {
                MOut.Mat[i][j] = MIn.Mat[i][j];
            }
        }
        return MOut;
    }

    static matriks takeLastCol(matriks MIn) {
        // ambil kolom terakhir
        matriks MOut = new matriks();
        MOut.jumlahBaris = MIn.jumlahBaris;
        MOut.jumlahKolom = 1;
        for (int i = 0; i < MOut.jumlahBaris; i++) {
            MOut.Mat[i][0] = MIn.Mat[i][MIn.jumlahKolom-1];
        }
        return MOut;
    }
    
    // for finding inverse w adj method and determinant w cofactor expansion method
    static matriks slice(matriks MIn, int i, int j) {
        // mengambil elemen matriks yang BUKAN berbaris i atau BUKAN berkolom j
        matriks MOut = new matriks();
        MOut.jumlahBaris = MIn.jumlahBaris - 1;
        MOut.jumlahKolom = MIn.jumlahKolom - 1;
        int count = 0;
        for (int a = 0; a < MIn.jumlahBaris; a++) {
            for (int b = 0; b < MIn.jumlahKolom; b++) {
                if (!(a == i || b == j)) {
                    count++;
                    MOut.Mat[(count - 1) / MOut.jumlahKolom][(count - 1) % MOut.jumlahKolom] = MIn.Mat[a][b];
                }
            }
        }
        return MOut;
    }
    
    static double cof(matriks MIn, int i, int j) {
        // cof dari mat minor, MIn harus matriks persegi
        double cof;
        cof = detOBE(slice(MIn, i, j)); //ril quesyen: utk ekspansi kofaktor brti determinannya gblh pake ini
        if ((i + j) % 2 != 0) { // sbnrny i + 1 + j + 1 karna i j di mtk itu mulai dr 1, tp 2 % 2 = 0 jd g ngaruh
            cof *= (-1);
        }
        return cof;
    }

    static matriks matCof(matriks MIn) {
        // matriks isinya matriks kofaktor tiap elemen i, j
        matriks MOut = new matriks();
        MOut.jumlahBaris = MIn.jumlahBaris;
        MOut.jumlahKolom = MIn.jumlahKolom;
        for (int i = 0; i < MIn.jumlahBaris; i++){
            for (int j = 0; j < MIn.jumlahKolom; j++) {
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
        if (MIn.jumlahBaris == 1) {
            det = MIn.Mat[0][0];
        } else {
            det = 0;
            for (int j = 0; j < MIn.jumlahBaris; j++) {
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
        if (MIn.jumlahKolom == 1) {
            det = MIn.Mat[0][0];
        } else {
            det = 0;
            for (int i = 0; i < MIn.jumlahKolom; i++) {
                if (i % 2 == 0) {
                    det += MIn.Mat[i][0] * detExCofRow0(slice(MIn, i, 0));
                } else {
                    det += (-1) * MIn.Mat[i][0] * detExCofRow0(slice(MIn, i, 0));
                }
            }
        }
        return det;
    }

    static matriks invAdj(matriks MIn) {
        // PREKONDISI: MIn matriks persegi, DET MIn != 0
        matriks MOut = new matriks();
        MOut.jumlahKolom = MIn.jumlahKolom;
        MOut.jumlahBaris = MIn.jumlahBaris;
        MOut = transpose(matCof(MIn));
        for (int i = 0; i < MIn.jumlahKolom; i++){
            for (int j = 0; j < MIn.jumlahBaris; j++) {
                MOut.Mat[i][j] /= detOBE(MIn);
            }
        }
        return MOut;
    }

    static matriks cramerSwap(matriks a, matriks fx, int col){
        matriks temp = new matriks();
        temp = cloneMatriks(a);

        for (int i = 0; i < a.jumlahBaris; i++){
            temp.Mat[i][col] = fx.Mat[i][0];
        }

        return temp;
    }
}
