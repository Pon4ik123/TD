package Lab3;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Main {

    public static void plot1(double fn, double fm, double ka, double kp, double kf){
        double fs = 8000;
        double tc = 1;
        int N = (int) (fs * tc);
//        int fm = 2;
//        int fn = 2;
//        int ka = 5;
        double[] tab_a = new double[N];
        double[] tab_p = new double[N];
        double[] tab_f = new double[N];

        double[] mt = new double[N];
        double[] za = new double[N];
        double[] zp = new double[N];
        double[] zf = new double[N];

        for (int i = 0; i < N; i++) {
            double t = (double) i / fs;
            mt[i] = sin(2 * PI * fm * t);

            za[i] = (ka * mt[i] + 1) * cos(2 * PI * fn * t);
            zp[i] = cos(2 * PI * fn * t + kp * mt[i]);
            zf[i] = cos(2 * PI * fn * t + (kf /fm) * mt[i]);

            tab_a[i] += za[i];
            tab_p[i] += zp[i];
            tab_f[i] += zf[i];
        }

        XYChart chart_a = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_a.addSeries("Wartości", null, tab_a);

        chart_a.getStyler().setMarkerSize(8);
        chart_a.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_a.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_a.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_a.getStyler().setPlotGridLinesVisible(true);
        chart_a.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_a.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_a.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_a.getStyler().setXAxisTitleColor(Color.RED);
        chart_a.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_a.getStyler().setChartTitleVisible(true);
        chart_a.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_a.getStyler().setLegendVisible(true);
        chart_a.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_a.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_a.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_a.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_a.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_a.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_a, "za.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_p = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_p.addSeries("Wartości", null, tab_p);

        chart_p.getStyler().setMarkerSize(8);
        chart_p.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_p.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_p.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_p.getStyler().setPlotGridLinesVisible(true);
        chart_p.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_p.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_p.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_p.getStyler().setXAxisTitleColor(Color.RED);
        chart_p.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_p.getStyler().setChartTitleVisible(true);
        chart_p.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_p.getStyler().setLegendVisible(true);
        chart_p.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_p.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_p.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_p.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_p.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_p.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_p, "zp.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_f = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_f.addSeries("Wartości", null, tab_f);

        chart_f.getStyler().setMarkerSize(8);
        chart_f.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_f.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_f.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_f.getStyler().setPlotGridLinesVisible(true);
        chart_f.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_f.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_f.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_f.getStyler().setXAxisTitleColor(Color.RED);
        chart_f.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_f.getStyler().setChartTitleVisible(true);
        chart_f.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_f.getStyler().setLegendVisible(true);
        chart_f.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_f.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_f.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_f.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_f.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_f.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_f, "zf.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] skalaDecA = new double[N];
        double[] skalaDecP = new double[N];
        double[] skalaDecF = new double[N];

        double[] widmaA = new double[N];
        double[] widmaP = new double[N];
        double[] widmaF = new double[N];


        for (int k = 0; k < N; k++) {
            double realVal = 0;
            double imageValue = 0;
            for (int n = 0; n < za.length; n++) {
                double fi = (-2 * Math.PI * k * n) / N;
                realVal += za[n] * Math.cos(fi);
                imageValue += za[n] * Math.sin(fi);
            }

            widmaA[k] = sqrt(realVal*realVal + imageValue*imageValue);
            skalaDecA[k] = 10 * log10(widmaA[k]);
        }

        for (int k = 0; k < N; k++) {
            double realVal = 0;
            double imageValue = 0;
            for (int n = 0; n < zp.length; n++) {
                double fi = (-2 * Math.PI * k * n) / N;
                realVal += zp[n] * Math.cos(fi);
                imageValue += zp[n] * Math.sin(fi);
            }

            widmaP[k] = sqrt(realVal*realVal + imageValue*imageValue);
            skalaDecP[k] = 10 * log10(widmaP[k]);
        }

        for (int k = 0; k < N; k++) {
            double realVal = 0;
            double imageValue = 0;
            for (int n = 0; n < zf.length; n++) {
                double fi = (-2 * Math.PI * k * n) / N;
                realVal += zf[n] * Math.cos(fi);
                imageValue += zf[n] * Math.sin(fi);
            }

            widmaF[k] = sqrt(realVal*realVal + imageValue*imageValue);
            skalaDecF[k] = 10 * log10(widmaF[k]);
        }

        XYChart chart_widmaA = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_widmaA.addSeries("Wartości", null, widmaA);

        chart_widmaA.getStyler().setMarkerSize(8);
        chart_widmaA.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_widmaA.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_widmaA.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_widmaA.getStyler().setPlotGridLinesVisible(true);
        chart_widmaA.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_widmaA.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_widmaA.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_widmaA.getStyler().setXAxisTitleColor(Color.RED);
        chart_widmaA.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_widmaA.getStyler().setChartTitleVisible(true);
        chart_widmaA.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_widmaA.getStyler().setLegendVisible(true);
        chart_widmaA.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_widmaA.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_widmaA.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaA.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaA.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_widmaA.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_widmaA, "widmaA.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_widmaF = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_widmaF.addSeries("Wartości", null, widmaF);

        chart_widmaF.getStyler().setMarkerSize(8);
        chart_widmaF.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_widmaF.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_widmaF.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_widmaF.getStyler().setPlotGridLinesVisible(true);
        chart_widmaF.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_widmaF.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_widmaF.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_widmaF.getStyler().setXAxisTitleColor(Color.RED);
        chart_widmaF.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_widmaF.getStyler().setChartTitleVisible(true);
        chart_widmaF.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_widmaF.getStyler().setLegendVisible(true);
        chart_widmaF.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_widmaF.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_widmaF.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaF.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaF.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_widmaF.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_widmaF, "widmaF.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_widmaP = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        chart_widmaP.addSeries("Wartości", null, widmaP);

        chart_widmaP.getStyler().setMarkerSize(8);
        chart_widmaP.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_widmaP.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_widmaP.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_widmaP.getStyler().setPlotGridLinesVisible(true);
        chart_widmaP.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_widmaP.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_widmaP.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_widmaP.getStyler().setXAxisTitleColor(Color.RED);
        chart_widmaP.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_widmaP.getStyler().setChartTitleVisible(true);
        chart_widmaP.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_widmaP.getStyler().setLegendVisible(true);
        chart_widmaP.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_widmaP.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_widmaP.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaP.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_widmaP.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_widmaP.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_widmaF, "widmaP.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        plot1(20, 1, 0.5, 0.5, 0.5);
    }
}
