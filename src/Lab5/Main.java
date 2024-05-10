package Lab5;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.log10;

public class Main {

    public static double function(double x) {
        return x;
    }

    public static double rectangularIntegration(double a, double b, int n) {
        double dx = (b - a) / n;
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            double xi = a + i * dx;
            sum += function(xi);
        }
        return sum * dx;
    }

    public static void plot1() {
        int[] bit = {1, 0, 0, 1, 1, 0, 0, 1, 1, 1};
        double fs = 500;
        double tc = 1.0;
        double tb = tc / bit.length;
        int N = (int) (fs * tc);
        int w = 2;
        double a1 = 0.5;
        double a2 = 1.0;
        double fn = w * (1 / tb);
        int tbp = (int) (tb * fs);

        double[] za = new double[N * 2];
        double[] zp = new double[N * 2];
        double[] zf = new double[N * 2];

        double[] xa = new double[N * 2];
        double[] xz = new double[N * 2];
        double[] xf = new double[N * 2];

        double[] pa = new double[N * 2];
        double[] pz = new double[N * 2];
        double[] pf = new double[N * 2];

        double[] ca = new double[N * 2];
        double[] cz = new double[N * 2];
        double[] cf = new double[N * 2];

        int ofs = 0;
        for (int i = 0; i < bit.length; i++) {
            int bitIndex = bit[i];
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

        int ofs1 = 0;
        for (int i = 0; i < bit.length; i++) {
            int bitIndex = bit[i];
            for (int j = 0; j < tbp; j++) {
                double t = (double) (i * ofs1 + j) / fs;

                if (bitIndex == 1) {
                    zp[j + ofs1] = sin(2 * PI * fn * t + PI);
                } else {
                    zp[j + ofs1] = sin(2 * PI * fn * t);
                }
            }

            ofs1 = ofs1 + tbp;
        }

        int ofs2 = 0;
        double fn1 = (w + 1) / tb;
        double fn2 = (w + 2) / tb;
        for (int i = 0; i < bit.length; i++) {
            int bitIndex = bit[i];
            for (int j = 0; j < tbp; j++) {
                double t = (double) (i * ofs2 + j) / fs;

                if (bitIndex == 1) {
                    zf[j + ofs2] = sin(2 * PI * fn2 * t);
                } else {
                    zf[j + ofs2] = sin(2 * PI * fn1 * t);
                }
            }
            ofs2 = ofs2 + tbp;
        }

        double[] halfZa = new double[N / 2];
        System.arraycopy(za, 0, halfZa, 0, N / 2);
        XYChart chartA = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartA.addSeries("Wartości", null, halfZa).setMarker(SeriesMarkers.NONE);

        double[] halfZp = new double[N / 2];
        System.arraycopy(zp, 0, halfZp, 0, N / 2);
        XYChart chartP = new XYChartBuilder().width(1920).height(1080).title("Sygnały zp").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartP.addSeries("Wartości", null, halfZp).setMarker(SeriesMarkers.NONE);

        double[] halfZf = new double[N / 2];
        System.arraycopy(zf, 0, halfZf, 0, N / 2);
        XYChart chartF = new XYChartBuilder().width(1920).height(1080).title("Sygnały zf").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartF.addSeries("Wartości", null, halfZf).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartA, "src/Lab5/za.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartP, "src/Lab5/zp.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartF, "src/Lab5/zf.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //plot1();
        String a = "", b = "1";
        System.out.println(addBinary(a, b));
    }

    public static String addBinary(String a, String b) {
        int first = Integer.parseInt(a, 2);
        int second = Integer.parseInt(b, 2);

        return Integer.toBinaryString(first + second);
    }
}
