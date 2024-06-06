package Lab6;

public class Jarek {
    public static void main(String[] args) {
        int[] data = {1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0};
        int[][] matrixG = {
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0}
        };

        int[] encodedData = wiadomosc(data, matrixG);

        System.out.print("Zakodowane dane: ");
        for (int bit : encodedData) {
            System.out.print(bit);
        }
        System.out.println();

        encodedData[3] = encodedData[3] == 0 ? 1 : 0;

        int[] decodedData = decodeWithSyndrome(encodedData);

        System.out.print("Dekodowane dane: ");
        for (int bit : decodedData) {
            System.out.print(bit);
        }
        System.out.println();
    }


    public static int[] danePrzedKodowaniem(int[] data) {
        int[] daneDokodowania = new int[7];

        daneDokodowania[2] = data[0];
        daneDokodowania[4] = data[1];
        daneDokodowania[5] = data[2];
        daneDokodowania[6] = data[3];

        daneDokodowania[0] = data[0] ^ data[1] ^ data[3];
        daneDokodowania[1] = data[0] ^ data[2] ^ data[3];
        daneDokodowania[3] = data[1] ^ data[2] ^ data[3];

        return daneDokodowania;
    }
    public static int[] decode(int[] daneDokodowania) {
        int[] poKodowaniu = new int[4];

        int s1 = daneDokodowania[0] ^ daneDokodowania[2] ^ daneDokodowania[4] ^ daneDokodowania[6];
        int s2 = daneDokodowania[1] ^ daneDokodowania[2] ^ daneDokodowania[5] ^ daneDokodowania[6];
        int s3 = daneDokodowania[3] ^ daneDokodowania[4] ^ daneDokodowania[5] ^ daneDokodowania[6];
        int znalezienieBledu = s1 * 1 + s2 * 2 + s3 * 4;

        if (znalezienieBledu != 0) {
            daneDokodowania[znalezienieBledu - 1] = 1 - daneDokodowania[znalezienieBledu - 1];
        }
        poKodowaniu[0] = daneDokodowania[2];
        poKodowaniu[1] = daneDokodowania[4];
        poKodowaniu[2] = daneDokodowania[5];
        poKodowaniu[3] = daneDokodowania[6];

        return poKodowaniu;
    }

    public static int[] wiadomosc(int[] data, int[][] matrixG){
        int[] wiadomosc = new int[matrixG.length];

        for(int i = 0; i < matrixG.length; i++){
            int all = 0;
            for(int j = 0; j < data.length; j++){
                all += data[j] * matrixG[i][j];
            }
            wiadomosc[i] = all % 2;
        }
        return wiadomosc;
    }
    public static int[] decodeWithSyndrome(int[] daneDokodowania) {
        int[][] parityCheckMatrix = {
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
                {0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1},
                {0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1}
        };

        int[] syndrome = new int[parityCheckMatrix.length];
        for (int i = 0; i < parityCheckMatrix.length; i++) {
            int sum = 0;
            for (int j = 0; j < daneDokodowania.length; j++) {
                sum += daneDokodowania[j] * parityCheckMatrix[i][j];
            }
            syndrome[i] = sum % 2;
        }
        int znalezienieBledu = 0;
        for (int i = 0; i < syndrome.length; i++) {
            if (syndrome[i] == 1) {
                znalezienieBledu += Math.pow(2, i);
            }
        }
        if (znalezienieBledu != 0) {
            daneDokodowania[znalezienieBledu - 1] = 1 - daneDokodowania[znalezienieBledu - 1];
        }
        int[] decodedData = new int[11];
        int k = 0;
        for (int i = 0; i < daneDokodowania.length; i++) {
            if (i != 0 && i != 1 && i != 3 && i != 7) {
                decodedData[k++] = daneDokodowania[i];
            }
        }
        return decodedData;
    }
}
