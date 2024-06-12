package Lab7;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.*;

import static java.lang.Math.PI;

public class Main {
    public static double fs = 2000;
    public static double tc = 6.0;
    public static int N = (int) (fs * tc);
    public static int w = 2;
    public static double a1 = 1;
    public static double a2 = 0.5;
    public static double tb;
    public static double fn;
    public static int tbp;

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

    public static List<Integer> haming7_4_coder(int n, List<Integer> tempBits) {
        int x1 = tempBits.get(0) ^ tempBits.get(1) ^ tempBits.get(3);
        int x2 = tempBits.get(0) ^ tempBits.get(2) ^ tempBits.get(3);
        int x4 = tempBits.get(1) ^ tempBits.get(2) ^ tempBits.get(3);

        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        result.set(0, x1);
        result.set(1, x2);
        result.set(2, tempBits.getFirst());
        result.set(3, x4);
        result.set(4, tempBits.get(1));
        result.set(5, tempBits.get(2));
        result.set(6, tempBits.get(3));

        return result;
    }

    public static List<Double> askModulation (List<Integer> bits){
        Main.tb = tc / bits.size();
        Main.fn = (1 / tb);
        Main.tbp = (int) (tb * fs);

        double[] za = new double[N];
        int ofs = 0;
        for (int i = 0; i < bits.size(); i++) {
            int bitIndex = bits.get(i);
            for (int j = 0; j < tbp; j++) {
                double t =(j + tbp*i)  / fs;

                if (bitIndex == 1) {
                    za[j + ofs] = a1 * Math.sin(2 * PI * fn * t);
                } else {
                    za[j + ofs] = a2 * Math.sin(2 * PI * fn * t);
                }
            }

            ofs = ofs + tbp;
        }

        return transmisionZad2(za, whiteNoise(za.length),0);
//        return transmisionZad3(za, whiteNoise(za.length),8);
//        return transmisionZad4_1(za, whiteNoise(za.length), 0, 8);
//        return transmisionZad4_2(za, whiteNoise(za.length), 0, 8);
    }

    public static List<Double> transmisionZad2(double[] ASK, double[] whiteNoise, int alfa) {
        List<Double> yASK = new ArrayList<>();
        for (int i = 0; i < ASK.length; i++) {
            yASK.add(ASK[i] + alfa * whiteNoise[i]);
        }

        return yASK;
    }

    public static List<Double> transmisionZad3(double[] ASK, double[] whiteNoise, int beta) {
        List<Double> yASK = new ArrayList<>();
        for (int i = 0; i < ASK.length; i++) {
            yASK.add(ASK[i] * Math.exp(-beta * i));
        }

        return yASK;
    }

    public static List<Double> transmisionZad4_1(double[] ASK, double[] whiteNoise, int alfa, int beta) {
        List<Double> yASK = new ArrayList<>();
        for (int i = 0; i < ASK.length; i++) {
            yASK.add(ASK[i] + alfa * whiteNoise[i] * Math.exp(-beta * i));
        }

        return yASK;
    }

    public static List<Double> transmisionZad4_2(double[] ASK, double[] whiteNoise, int alfa, int beta) {
        List<Double> yASK = new ArrayList<>();
        for (int i = 0; i < ASK.length; i++) {
            yASK.add(ASK[i] * Math.exp(-beta * i) + alfa * whiteNoise[i]);
        }

        return yASK;
    }

    public static List<Integer> demodulationASK(List<Double> za) {
        double[] zASK = new double[N];
        for (int i = 0; i < zASK.length; i++) {
            zASK[i] = za.get(i) * Math.sin(2 * PI * fn * i / fs) ;
        }

        double[] pASK = new double[N];
        for (int i = 0; i < N; i++) {
            double s = 0;
            for (int j = 0; j < tb*fs; j++) {
                int index = (int) (i * tb*fs + j);
                if (index < N) {
                    s += zASK[index];
                    pASK[index] = s;
                }
            }
        }

        double h = 30;

        double[] cASK = new double[N];
        for (int i = 0; i < N; i++) {
            cASK[i] = pASK[i] > h ? 1 : 0;
        }

        List<Integer> counterASK = new ArrayList<>();
        int oneCounterASK = 0;
        for (int i = 0; i < 84; i++) {
            for (int j = i*Main.tbp; j < Main.tbp+Main.tbp*i; j++) {

                if (cASK[j] == 1) oneCounterASK++;

            }
            if (oneCounterASK > Main.tbp/2-1) {
                counterASK.add(1);
            } else {counterASK.add(0);}
            oneCounterASK = 0;
        }

        return counterASK;
    }

    public static List<Integer> decodeASK(List<Integer> demodulatedBits) {
        List<Integer> decodedBits = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (Integer demodulatedBit : demodulatedBits) {
            temp.add(demodulatedBit);
            if(temp.size() == 7) {
                int x1P = temp.get(2) ^ temp.get(4) ^ temp.get(6);
                int x2P = temp.get(2) ^ temp.get(5) ^ temp.get(6);
                int x4P = temp.get(4) ^ temp.get(5) ^ temp.get(6);

                int x1_ = temp.get(0) ^ x1P;
                int x2_ = temp.get(1) ^ x2P;
                int x4_ = temp.get(3) ^ x4P;

                int s = (int) (x1_ * Math.pow(2, 0) + x2_ * Math.pow(2, 1) + x4_ * Math.pow(2, 2));
                if (s == 0) System.out.println("There is no mistake");
                else {
                    System.out.println("The mistake is in: " + s + " bit");
                    System.out.println("Making changes...");
                    if (temp.get(s - 1) == 1) {
                        temp.set(s - 1, 0);
                    } else temp.set(s - 1, 1);
                    System.out.println("Chosen word: " + temp);
                }
                decodedBits.add(temp.get(2));
                decodedBits.add(temp.get(4));
                decodedBits.add(temp.get(5));
                decodedBits.add(temp.get(6));
                temp.clear();
            }
        }

        return decodedBits;
    }

    public static double bitErrorRate(List<Integer> codedBits, List<Integer> decodedBits) {
        int error = 0;
        for (int i = 0; i < codedBits.size(); i++) {
            if(!Objects.equals(codedBits.get(i), decodedBits.get(i))){
                error++;
            }
        }
        return (double) error / codedBits.size();
    }

    public static double[] whiteNoise(int length) {
        double[] whiteNoise = new double[length];

        Random random = new Random();

        for (int i = 0; i < length; i++) {
            whiteNoise[i] = 2 * random.nextDouble() - 1;
        }

        XYChart chartCASK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartCASK.addSeries("Wartości", null, whiteNoise).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartCASK, "src/Lab7/whiteNoise.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return whiteNoise;
    }

    public static void main(String[] args) {
        System.out.print("Enter the word, that would contain min 8 symbols: ");
        String string = new Scanner(System.in).nextLine();
        while(string.length() < 8){
            System.out.println("Error, try again");
            string = new Scanner(System.in).nextLine();
        }

        List<Integer> enteredBits = stringToBinary(string);
        List<Integer> bits = new ArrayList<>();
        int count = 0;
        while (count < 48){
            bits.add(enteredBits.get(count));
            count++;
        }
        System.out.println();
        System.out.println("Entered bits: " + enteredBits);
        System.out.println();
        System.out.println("bits: " + bits);

        List<Integer> codedBits = new ArrayList<>();
        List<Integer> current4bits = new ArrayList<>();
        count = 0;
        for (Integer bit : enteredBits) {
            current4bits.add(bit);
            if(current4bits.size() == 4){
                codedBits.addAll(haming7_4_coder(7, current4bits));
                current4bits.clear();
            }
            count++;
            if(count > 48){
                break;
            }
        }
        System.out.println();
        System.out.println("Coded bits: " + codedBits);

        List<Double> ASK = askModulation(codedBits);

        List<Integer> demodulatedBits = demodulationASK(ASK);
        System.out.println();
        System.out.println("Demodulated bits: " + demodulatedBits);

        System.out.println();
        System.out.println("BER: " + bitErrorRate(codedBits, demodulatedBits));

        List<Integer> decodedBits = decodeASK(demodulatedBits);
        System.out.println();
        System.out.println("Decoded bits: " + decodedBits);
    }
}
