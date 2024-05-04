package Lab3;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;
import edu.emory.mathcs.jtransforms.fft.DoubleFFT_1D;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Main {

    public static void plot1(double fn, double fm, double ka, double kp, double kf){
        double fs = 1000;
        double tc = 1;
        int N = (int) (fs * tc);

        double[] tab_a = new double[N];
        double[] tab_p = new double[N];
        double[] tab_f = new double[N];

        double[] mt = new double[N];
        double[] za = new double[N*2];
        double[] zp = new double[N*2];
        double[] zf = new double[N*2];

        for (int i = 0; i < N; i++) {
            double t = (double) i / fs;
            mt[i] = sin(2 * PI * fm * t);

            za[i] = (ka * mt[i] + 1) * cos(2 * PI * fn * t);
            zp[i] = cos(2 * PI * fn * t + kp * mt[i]);
            zf[i] = cos(2 * PI * fn * t + (kf / fm) * mt[i]);

            tab_a[i] += za[i];
            tab_p[i] += zp[i];
            tab_f[i] += zf[i];
        }

        XYChart chart_a = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesA = (XYSeries) chart_a.addSeries("Wartości", null, tab_a).setMarker(SeriesMarkers.NONE);

        XYChart chart_p = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesP = (XYSeries) chart_p.addSeries("Wartości", null, tab_p).setMarker(SeriesMarkers.NONE);

        XYChart chart_f = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesF = (XYSeries) chart_f.addSeries("Wartości", null, tab_f).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chart_a, "src/Lab3/za_b.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart_p, "src/Lab3/zp_b.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart_f, "src/Lab3/zf_b.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] skalaDecA = new double[N];
        double[] skalaDecP = new double[N];
        double[] skalaDecF = new double[N];

        double[] widmaA = new double[N / 2 + 1];
        double[] widmaP = new double[N / 2 + 1];
        double[] widmaF = new double[N / 2 + 1];

        double[] fk = new double[N];

        DoubleFFT_1D fftA = new DoubleFFT_1D(N);
        fftA.realForward(za);
        DoubleFFT_1D fftP = new DoubleFFT_1D(N);
        fftP.realForward(zp);
        DoubleFFT_1D fftF = new DoubleFFT_1D(N);
        fftF.realForward(zf);

        for (int k = 0; k < widmaA.length; k++) {
            double realVal = za[2 * k];
            double imagVal = za[2 * k + 1];
            widmaA[k] = Math.sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecA[k] = 10 * log10(widmaA[k]);
            fk[k] = k * (fs/N);
        }

        for (int k = 0; k < widmaA.length; k++) {
            double realVal = zp[2 * k];
            double imagVal = zp[2 * k + 1];
            widmaP[k] = Math.sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecP[k] = 10 * log10(widmaP[k]);
            fk[k] = k * (fs/N);
        }

        for (int k = 0; k < widmaA.length; k++) {
            double realVal = zf[2 * k];
            double imagVal = zf[2 * k + 1];
            widmaF[k] = Math.sqrt(realVal * realVal + imagVal * imagVal);

            skalaDecF[k] = 10 * log10(widmaF[k]);
            fk[k] = k * (fs/N);
        }

        XYChart chart_widmaA = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesWidmaA = (XYSeries) chart_widmaA.addSeries("Wartości", null, skalaDecA).setMarker(SeriesMarkers.NONE);

        XYChart chart_widmaF = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesWidmaF = (XYSeries) chart_widmaF.addSeries("Wartości", null, skalaDecF).setMarker(SeriesMarkers.NONE);

        XYChart chart_widmaP = new XYChartBuilder().width(1920).height(1080).title("Wykres funkcji").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesWidmaP = (XYSeries) chart_widmaP.addSeries("Wartości", null, skalaDecP).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chart_widmaA, "src/Lab3/za_b_widmo.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart_widmaF, "src/Lab3/zf_b_widmo.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chart_widmaP, "src/Lab3/zp_b_widmo.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double width = 0;
        int BD3 = 3;
        for (int i = 0; i < widmaA.length; i++) {
            if (widmaA[i] >= Arrays.stream(widmaA).max().getAsDouble()-BD3){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD3 widma A = " + width);

        width = 0;
        for (int i = 0; i < widmaP.length; i++) {
            if (widmaP[i] >= Arrays.stream(widmaP).max().getAsDouble()-BD3){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD3 widma P = " + width);

        width = 0;
        for (int i = 0; i < widmaF.length; i++) {
            if (widmaF[i] >= Arrays.stream(widmaF).max().getAsDouble()-BD3){
                width = fk[i]*2;
            }
        }
        System.out.println("Width BD3 bd3 widma F = " + width);



        int BD6 = 6;
        width = 0;
        for (int i = 0; i < widmaA.length; i++) {
            if (widmaA[i] >= Arrays.stream(widmaA).max().getAsDouble()-BD6){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD6 widma A = " + width);

        width = 0;
        for (int i = 0; i < widmaP.length; i++) {
            if (widmaP[i] >= Arrays.stream(widmaP).max().getAsDouble()-BD6){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD6 widma P = " + width);

        width = 0;
        for (int i = 0; i < widmaF.length; i++) {
            if (widmaF[i] >= Arrays.stream(widmaF).max().getAsDouble()-BD6){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD6 widma F = " + width);


        int BD12 = 12;
        width = 0;
        for (int i = 0; i < widmaA.length; i++) {
            if (widmaA[i] >= Arrays.stream(widmaA).max().getAsDouble()-BD12){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD12 widma A = " + width);

        width = 0;
        for (int i = 0; i < widmaP.length; i++) {
            if (widmaP[i] >= Arrays.stream(widmaP).max().getAsDouble()-BD12){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD12 widma P = " + width);

        width = 0;
        for (int i = 0; i < widmaF.length; i++) {
            if (widmaF[i] >= Arrays.stream(widmaF).max().getAsDouble()-BD12){
                width = fk[i]*2;
            }
        }
        System.out.println("Width for BD12 widma F = " + width);

    }



    public static void main(String[] args) {
        plot1(20, 1, 8, 2, 2);
    }
}
