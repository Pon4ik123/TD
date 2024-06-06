package Lab6;

import java.util.*;

public class Main {
    public static List<Integer> stringToBinary(String word){
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

    public static void haming(int n, int k, boolean triger, String word){
        List<Integer> originalBites = stringToBinary(word);
        List<Integer> tempBits = new ArrayList<>(originalBites);

        System.out.println("Entered bites: " + tempBits);

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

        if(triger) {
            int randomIndex = new Random().nextInt(result.size()-1);
            while(randomIndex == 0 || randomIndex == 1 || randomIndex == 3){
                randomIndex = new Random().nextInt(tempBits.size()-1);
            }
            if(result.get(randomIndex) == 1){
                result.set(randomIndex, 0);
            } else result.set(randomIndex, 1);
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
            System.out.println("The mistake is in: " + s + " bite");
            if(result.get(s-1) == 1) result.set(s-1, 0);
            else result.set(s-1, 1);
            System.out.println("After clearing error: " + result);
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the word for Haming(7, 4): ");
        String word = new Scanner(System.in).nextLine();
        haming(7, 4, true, word);

        System.out.print("Enter the word for Haming(15, 11): ");
        word = new Scanner(System.in).nextLine();

    }
}
