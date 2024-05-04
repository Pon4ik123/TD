package Lab4;

import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.Arrays;
import java.util.OptionalDouble;

import static java.lang.Math.*;

public class Main {

//    public static double width(double[] mp, double[] fk, int N){
//        OptionalDouble max3dbOptional = Arrays.stream(mp).max();
//        double max3db = max3dbOptional.getAsDouble() - 3;
//
//        double fmax = 0;
//        for (int i = N - 1; i >= 0; i--) {
//            if (mp[i] >= max3db && max3db >= mp[i-1]){
//                fmax =;
//                break;
//            }
//        }
//    }
public static double width(double[] fk, double[] widma, double bdb) {
    double maxAmplitude = Arrays.stream(widma).max().getAsDouble();
    double max_bdb = maxAmplitude - bdb;

    double fmin = 0, fmax = 0;

    for (int i = 0; i < widma.length; i++) {
        if (widma[i] >= max_bdb) {
            fmin = fk[i];
            break;
        }
    }

    for (int i = widma.length - 1; i >= 0; i--) {
        if (widma[i] >= max_bdb) {
            fmax = fk[i];
            break;
        }
    }

    return fmax - fmin;
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
        double[] zp = new double[N * 2];
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
        double[] zf = new double[N * 2];
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
            BitmapEncoder.saveBitmap(chartA, "src/Lab4/za.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartP, "src/Lab4/zp.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartF, "src/Lab4/zf.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DoubleFFT_1D fftA = new DoubleFFT_1D(N);
        fftA.realForward(za);

        DoubleFFT_1D fftP = new DoubleFFT_1D(N);
        fftP.realForward(zp);

        DoubleFFT_1D fftF = new DoubleFFT_1D(N);
        fftF.realForward(zf);

        double[] widmaA = new double[N / 2];
        double[] widmaP = new double[N / 2];
        double[] widmaF = new double[N / 2];

        double[] skalaDecA = new double[N / 2];
        double[] skalaDecP = new double[N / 2];
        double[] skalaDecF = new double[N / 2];

        double[] fkA = new double[N / 2];
        double[] fkP = new double[N / 2];
        double[] fkF = new double[N / 2];


        for (int k = 0; k < widmaP.length; k++) {
            double realVal = za[2 * k];
            double imagVal = za[2 * k + 1];
            widmaA[k] = sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecA[k] = 10 * log10(widmaA[k]);
            fkA[k] = k * (fs/N);
        }

        for (int k = 0; k < widmaP.length; k++) {
            double realVal = zp[2 * k];
            double imagVal = zp[2 * k + 1];
            widmaP[k] = sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecP[k] = 10 * log10(widmaP[k]);
            fkP[k] = k * (fs/N);
        }

        for (int k = 0; k < widmaF.length; k++) {
            double realVal = zf[2 * k];
            double imagVal = zf[2 * k + 1];
            widmaF[k] = sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecF[k] = 10 * log10(widmaF[k]);
            fkF[k] = k * (fs/N);
        }
        double widthA = width(fkA, skalaDecA, 3);
        double widthA1 = width(fkA, skalaDecA, 6);
        double widthA2 = width(fkA, skalaDecA, 12);
        double widthP = width(fkP, skalaDecP, 3);
        double widthP1 = width(fkP, skalaDecP, 6);
        double widthP2 = width(fkP, skalaDecP, 12);
        double widthF = width(fkF, skalaDecF, 3);
        double widthF1 = width(fkF, skalaDecF, 6);
        double widthF2 = width(fkF, skalaDecF, 12);

        System.out.println("Szerokosc pasma za: " + widthA);
        System.out.println("Szerokosc pasma za: " + widthA1);
        System.out.println("Szerokosc pasma za: " + widthA2);
        System.out.println("Szerokosc pasma zp: " + widthP);
        System.out.println("Szerokosc pasma zp: " + widthP1);
        System.out.println("Szerokosc pasma zp: " + widthP2);
        System.out.println("Szerokosc pasma zf: " + widthF);
        System.out.println("Szerokosc pasma zf: " + widthF1);
        System.out.println("Szerokosc pasma zf: " + widthF2);
        XYChart chartWiadmaA = new XYChartBuilder().width(1920).height(1080).title("Sygnały za_widmo").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesWidmaA = (XYSeries) chartWiadmaA.addSeries("Wartości", null, skalaDecA).setMarker(SeriesMarkers.NONE);

        XYChart chartWidmaP = new XYChartBuilder().width(1920).height(1080).title("Sygnały zp_widmo").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartWidmaP.addSeries("Wartości", null, skalaDecP).setMarker(SeriesMarkers.NONE);

        XYChart chartWidmaF = new XYChartBuilder().width(1920).height(1080).title("Sygnały zf_widmo").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartWidmaF.addSeries("Wartości", null, skalaDecF).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartWiadmaA, "src/Lab4/za_widmo.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartWidmaP, "src/Lab4/zp_widmo.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartWidmaF, "src/Lab4/zf_widmo.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        plot1();
    }
}
