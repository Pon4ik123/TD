package Lab6_1;

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

    public static void haming7_4(int n, int k, boolean triger) {
        List<Integer> originalBites = stringToBinary("zut");
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

        if (triger) {
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
        System.out.println("K: " + K);

        List<Integer> word = new ArrayList<>();
        word.addAll(M);
        word.addAll(K);
        System.out.println("Coded word: " + word);

        int[][] I = new int[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (i == j) {
                    I[i][j] = 1;
                }
            }
        }
//        for (int i = 0; i < 15; i++) {
//            for (int j = 0; j < 15; j++) {
//                System.out.print(I[i][j]);
//            }
//            System.out.println();
//        }

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

        int[][] G = new int[P.length + I.length][];
        for (int i = 0; i < G.length; i++) {
            G[i] = new int[P[0].length + I[0].length];
        }

        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[i].length; j++) {
                G[i][j] = P[i][j];
            }
        }

        for (int i = 0; i < I.length; i++) {
            for (int j = 0; j < I[i].length; j++) {
                G[i][j + P[0].length] = I[i][j];
            }
        }

        int[][] PT = new int[P[0].length][P.length];
        for (int i = 0; i < P.length; i++) {
            for (int j = 0; j < P[0].length; j++) {
                PT[j][i] = P[i][j];
            }
        }

        int[][] IN_K = new int[n-k][n-k];
        for (int i = 0; i < IN_K.length; i++) {
            for (int j = 0; j <IN_K.length; j++) {
                if (i == j) {
                    IN_K[i][j] = 1;
                }
            }
        }

        int[][] H = new int[IN_K.length + PT.length][IN_K[0].length];
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                if (i < IN_K.length) {
                    H[i][j] = IN_K[i][j];
                } else {
                    H[i][j] = PT[i - IN_K.length][j];
                }
            }
        }

        int[][] HT = new int[H[0].length][H.length];
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                HT[j][i] = H[i][j];
            }
        }

        System.out.println(K.size());

        int[] c = new int[G.length];
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G[0].length; j++) {
                c[i] = G[i][j] * K.get(i); // Accumulate the result of element-wise multiplication
            }
        }
        System.out.println(Arrays.toString(c));

        int[] s = new int[4];

    }


    public static void main(String[] args) {
//        haming7_4(7, 0, true);

        haming15_11(15, 11, false);
    }
}
