package Lab6;

public class Yarik {

    public static void main(String[] args) {
        int[] data = {1, 1, 0, 1};
        int[] daneDokodowania = danePrzedKodowaniem(data);

        System.out.print("Zakodowane dane: ");
        for (int bit : daneDokodowania) {
            System.out.print(bit);
        }
        System.out.println();

        daneDokodowania[3] = daneDokodowania[3] == 0 ? 1 : 0;

        int[] poKodowaniu = decode(daneDokodowania);

        System.out.print("Dekodowane dane: ");
        for (int bit : poKodowaniu) {
            System.out.print(bit);
        }
        System.out.println();
    }

    public static int[] danePrzedKodowaniem(int[] data) {
        int[] daneDokodowania = new int[7];

        daneDokodowania[0] = data[0] ^ data[1] ^ data[3];
        daneDokodowania[1] = data[0] ^ data[2] ^ data[3];
        daneDokodowania[3] = data[1] ^ data[2] ^ data[3];

        daneDokodowania[2] = data[0];
        daneDokodowania[4] = data[1];
        daneDokodowania[5] = data[2];
        daneDokodowania[6] = data[3];

        return daneDokodowania;
    }
    public static int[] decode(int[] daneDokodowania) {
        int[] poKodowaniu = new int[4];

        int s1 = daneDokodowania[0] ^ daneDokodowania[2] ^ daneDokodowania[4] ^ daneDokodowania[6];
        int s2 = daneDokodowania[1] ^ daneDokodowania[2] ^ daneDokodowania[5] ^ daneDokodowania[6];
        int s3 = daneDokodowania[3] ^ daneDokodowania[4] ^ daneDokodowania[5] ^ daneDokodowania[6];
        int znalezienieBledu = s1 + s2 * 2 + s3 * 4;

        if (znalezienieBledu != 0) {
            daneDokodowania[znalezienieBledu - 1] = 1 - daneDokodowania[znalezienieBledu - 1];
        }
        poKodowaniu[0] = daneDokodowania[2];
        poKodowaniu[1] = daneDokodowania[4];
        poKodowaniu[2] = daneDokodowania[5];
        poKodowaniu[3] = daneDokodowania[6];

        return poKodowaniu;
    }
}
