package Lab6;

import java.util.*;

public class Main {
    public static List<Integer> stringToBinary(String word) {
        StringBuilder binaryString = new StringBuilder();
        for (char ch : word.toCharArray()) {
            binaryString.append(String.format("%8s", Integer.toBinaryString(ch)).replace(' ', '0'));
        }

        List<Integer> bin = new ArrayList<>();
        for (char ch : binaryString.toString().toCharArray()) {
            if (ch == '0') bin.add(0);
            else bin.add(1);
        }

        return bin;
    }

    public static void haming7_4(int n, int k, String word, int triger) {
        List<Integer> originalBites = stringToBinary(word);
        List<Integer> tempBits = new ArrayList<>(originalBites);

        System.out.print("Entered bites: " + tempBits + "\n");

        int x1 = tempBits.get(0) ^ tempBits.get(1) ^ tempBits.get(3);
        int x2 = tempBits.get(0) ^ tempBits.get(2) ^ tempBits.get(3);
        int x4 = tempBits.get(1) ^ tempBits.get(2) ^ tempBits.get(3);
        System.out.println("Even bites: " + x1 + x2 + x4);

        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        result.set(0, x1);
        result.set(1, x2);
        result.set(2, tempBits.getFirst());
        result.set(3, x4);
        result.set(4, tempBits.get(1));
        result.set(5, tempBits.get(2));
        result.set(6, tempBits.get(3));
        System.out.println("Chosen word: " + result);

        if (triger == 1) {
            int randIndex = new Random().nextInt(result.size() - 1);
            while (randIndex == 0 || randIndex == 1 || randIndex == 3) {
                randIndex = new Random().nextInt(result.size() - 1);
            }
            if (result.get(randIndex) == 1) {
                result.set(randIndex, 0);
            } else result.set(randIndex, 1);
            System.out.println("Chosen word after error: " + result);
        }

        int x1P = result.get(2) ^ result.get(4) ^ result.get(6);
        int x2P = result.get(2) ^ result.get(5) ^ result.get(6);
        int x4P = result.get(4) ^ result.get(5) ^ result.get(6);

        int x1_ = result.get(0) ^ x1P;
        int x2_ = result.get(1) ^ x2P;
        int x4_ = result.get(3) ^ x4P;

        int s = (int) (x1_ * Math.pow(2, 0) + x2_ * Math.pow(2, 1) + x4_ * Math.pow(2, 2));
        if (s == 0) System.out.println("There is no mistake");
        else {
            System.out.println("The mistake is in: " + s + " bit");
            System.out.println("Making changes...");
            if (result.get(s - 1) == 1) {
                result.set(s - 1, 0);
            } else result.set(s - 1, 1);
            System.out.println("Chosen word: " + result);
            System.out.println("Done: " + result.get(2) + result.get(4) + result.get(5) + result.get(6));
        }
    }

    public static void haming15_11(int n, int k, boolean triger) {
        List<Integer> originalBites = stringToBinary("hello");
        List<Integer> tempBits = new ArrayList<>(originalBites);

        System.out.print("Entered bites: " + tempBits + "\n");

        List<Integer> M = new ArrayList<>();
        List<Integer> K = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (i == 0 || i == 1 || i == 3 || i == 7) {
                M.add(tempBits.get(i));
            } else {
                K.add(tempBits.get(i));
            }
        }
        System.out.println("M: " + M);
        System.out.println("Size of M: " + M.size());
        System.out.println();;
        System.out.println("K: " + K);
        System.out.println("Size of K: " + K.size());
        System.out.println();

        List<Integer> word = new ArrayList<>();
        word.addAll(M);
        word.addAll(K);
        System.out.println("Coded word: " + word);
        System.out.println("Size of coded word: " + word.size());
        System.out.println();

        int[][] I = new int[k][k];
        for (int i = 0; i < I.length; i++) {
            I[i][i] = 1;
        }
        System.out.println("Matrix I: ");
        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[0].length; j++) {
                System.out.print(I[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix I size: " + I.length + "x" + I[0].length);
        System.out.println();

        int [][] P = {{1, 1, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {1, 1, 1, 0},
                {1, 0, 0, 1},
                {0, 1, 0, 1},
                {1, 1, 0, 1},
                {0, 0, 1, 1},
                {1, 0, 1, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1}};
        System.out.println("Matrix P: ");
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                System.out.print(P[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix P size: " + P.length + "x" + P[0].length);
        System.out.println();

//        int[][] G = new int[P.length + I.length][];
//        for (int i = 0; i < G.length; i++) {
//            G[i] = new int[P[0].length + I[0].length];
//        }
//
//        for (int i = 0; i < P.length; i++) {
//            for (int j = 0; j < P[i].length; j++) {
//                G[i][j] = P[i][j];
//            }
//        }
//
//        for (int i = 0; i < I.length; i++) {
//            for (int j = 0; j < I[i].length; j++) {
//                G[i][j + P[0].length] = I[i][j];
//            }
//        }
        int[][] G = new int[k][k + P[0].length];

        // Fill matrix G with values from P and I
        for (int i = 0; i < k; i++) {
            // Copy values from P to G
            for (int j = 0; j < P[0].length; j++) {
                G[i][j] = P[i][j];
            }
            // Copy values from I to G
            for (int j = 0; j < k; j++) {
                G[i][j + P[0].length] = I[i][j];
            }
        }
        System.out.println("Matrix G: ");
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[0].length; j++) {
                System.out.print(G[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix G size: " + G.length + "x" + G[0].length);
        System.out.println();

        int[][] PTranspose = new int[P[0].length][P.length];
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                PTranspose[j][i] = P[i][j];
            }
        }
        System.out.println("Matrix PTranspose: ");
        for (int i = 0; i < PTranspose.length; i++) {
            for (int j = 0; j < PTranspose[0].length; j++) {
                System.out.print(PTranspose[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix PTranspose size: " + PTranspose.length + "x" + PTranspose[0].length);
        System.out.println();

        int[][] IN_K = new int[n-k][n-k];
        for (int i = 0; i < IN_K.length; i++) {
            for (int j = 0; j <IN_K[0].length; j++) {
                if (i == j) {
                    IN_K[i][j] = 1;
                }
            }
        }
        System.out.println("Matrix IN_K: ");
        for (int i = 0; i < IN_K.length; i++) {
            for (int j = 0; j < IN_K[0].length; j++) {
                System.out.print(IN_K[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix IN_K size: " + IN_K.length + "x" + IN_K[0].length);
        System.out.println();

        int[][] H = new int[IN_K.length + PTranspose.length][];
        for (int i = 0; i < H.length; i++) {
            H[i] = new int[IN_K[0].length + PTranspose[0].length];
        }

        for (int i = 0; i < IN_K.length; i++) {
            for (int j = 0; j < IN_K[i].length; j++) {
                H[i][j] = IN_K[i][j];
            }
        }

        for (int i = 0; i < PTranspose.length; i++) {
            for (int j = 0; j < PTranspose[i].length; j++) {
                H[i][j + IN_K[0].length] = PTranspose[i][j];
            }
        }
        System.out.println("Matrix H: ");
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                System.out.print(H[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix H size: " + H.length + "x" + H[0].length);
        System.out.println();

        int[][] HTranspose = new int[H[0].length][H.length];
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                HTranspose[j][i] = H[i][j];
            }
        }
        System.out.println("Matrix HTranspose: ");
        for (int i = 0; i < HTranspose.length; i++) {
            for (int j = 0; j < HTranspose[0].length; j++) {
                System.out.print(HTranspose[i][j]);
            }
            System.out.println();
        }
        System.out.println("Matrix HTranspose size: " + HTranspose.length + "x" + HTranspose[0].length);
        System.out.println();

        int[] c = new int[G[0].length];
//        for (int i = 0; i < G.length; i++) {
//            for (int j = 0; j < G[0].length; j++) {
//                c[i] += G[i][j] * K.get(i);
//            }
//        }
        for (int i = 0; i < 15; i++) {
            int sum = 0;
            for (int j = 0; j < 11; j++) {
                sum += G[j][i] * K.get(j);
            }
            c[i] = sum % 2;
        }
        System.out.println("Array c: ");
        System.out.println(Arrays.toString(c));
        System.out.println("Size of c: " + c.length);
//
//        int[] s = new int[4];

    }


    public static void main(String[] args) {
//        System.out.print("Enter the word for Haming(7, 4): ");
//        String string = new Scanner(System.in).nextLine();
//        System.out.print("Enter [1] if you want to make change one bit, [0] - don't want to: ");
//        int choice = new Scanner(System.in).nextInt();
//        haming7_4(7, 0, string, choice);

        haming15_11(15, 11, false);
    }
}
