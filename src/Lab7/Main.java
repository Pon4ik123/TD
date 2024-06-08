package Lab7;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.time.Year;
import java.util.*;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Main {
    public static double fs = 2000;
    public static double tc = 1.0;
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

    public static List<Integer> askModulation (List<Integer> bits){
        double tb = tc / bits.size();
        double fn = w * (1 / tb);
        int tbp = (int) (tb * fs);

        double[] za = new double[N];
        int ofs = 0;
        for (int i = 0; i < bits.size(); i++) {
            int bitIndex = bits.get(i);
            for (int j = 0; j < tbp; j++) {
                double t = (double) (i * ofs + j) / fs;

                if (bitIndex == 1) {
                    za[j + ofs] = a2 * sin(2 * PI * fn * t);
                } else {
                    za[j + ofs] = a1 * sin(2 * PI * fn * t);
                }
            }

            ofs = ofs + tbp;
        }

        double[] zASK = new double[N];
        for (int i = 0; i < zASK.length; i++) {
            zASK[i] = sin(2 * PI * fn * i / fs) * za[i];
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

        double h = 0;
        for (int i = 0; i < tb*fs && i < N; i++) {
            h += pASK[i];
        }
        h /= tb*fs;

        double[] cASK = new double[N];
        for (int i = 0; i < N; i++) {
            cASK[i] = pASK[i] > h ? 1 : 0;
        }

        List<Integer> c = new ArrayList<>();
        for (double v : cASK) {
            c.add((int) v);
        }

        XYChart chartCASK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartCASK.addSeries("Wartości", null, cASK).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartCASK, "src/Lab7/ASK.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return c;
    }

    public static List<Double> transmisionZad1(List<Integer> ASK, double[] whiteNoise, int alfa) {
        List<Double> yASK = new ArrayList<>();
        for (int i = 0; i < ASK.size(); i++) {
            yASK.add(ASK.get(i) + alfa * whiteNoise[i]);
        }

        return yASK;
    }

    public static List<Integer> demodulationASK(List<Double> yASK) {
        List<Integer> counterASK = new ArrayList<>();
        int oneCounterASK = 0;
        for (int i = 0; i < 84; i++) {
            for (int j = i*Main.tbp; j < Main.tbp+Main.tbp*i; j++) {
                if (yASK.get(i) == 1) oneCounterASK++;
            }
            if (oneCounterASK >= Main.tbp/2-1) counterASK.add(1);
            else counterASK.add(0);

            oneCounterASK = 0;
        }

        return counterASK;
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
        System.out.println("Entered bits: " + enteredBits);
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

        Main.tb = Main.tc / codedBits.size();
        Main.fn = Main.w * (1 / Main.tb);
        Main.tbp = (int) (tb * fs);

        List<Integer> ASK = askModulation(codedBits);

        double[] whiteNoise = whiteNoise(ASK.size());

        List<Double> yASK = transmisionZad1(ASK, whiteNoise, 0);

        System.out.println();
        System.out.println(demodulationASK(yASK));
    }
}