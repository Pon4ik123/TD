package Lab2;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import java.awt.*;
import java.io.IOException;

import static java.lang.Math.*;

public class Main {
    public static void plot1(){
        int N = 7;
        double [] realTab = new double[N];
        double [] imageTab = new double[N];
        int [] x = {-10, -7, -1, 0, 3, 6, 10};

        for (int k = 0; k <= N-1; k++){
            double real = 0;
            double image = 0;
            for (int n = 0; n <= N-1; n++){
                double fi = (-2 * PI * k * n) / N;
                real += x[n]*cos(fi);
                image += x[n]*sin(fi);
            }
            realTab[k] = real;
            imageTab[k] = image;

            System.out.println("a" + k + ": " + real);
            System.out.println("b" + k + ": " + image);
        }
    }

    public static void plot2() {
        int N = 7;
        double fs = 2205;

        double[] realTab = new double[N];
        double[] imageTab = new double[N];
        double[] Awidm = new double[N];
        double[] scalarDec = new double[N];
        double[] fk = new double[N];

        int[] x = {-10, -7, -1, 0, 3, 6, 10};

        for (int k = 0; k <= (N-1)/2; k++) {
            double real = 0;
            double image = 0;
            for (int n = 0; n <= N - 1; n++) {
                double fi = (-2 * PI * k * n) / N;
                real += x[n] * cos(fi);
                image += x[n] * sin(fi);
            }
            realTab[k] = real;
            imageTab[k] = image;

            System.out.println("a" + k + ": " + real);
            System.out.println("b" + k + ": " + image);

            Awidm[k] = sqrt(real*real + image*image);
            System.out.println("A: " + Awidm[k]);

            scalarDec[k] = 10 * log10(Awidm[k]);
            System.out.println("Scalar Dec: " + scalarDec[k]);

            fk[k] = k / (fs/N);
            System.out.println("fk: " + fk[k]);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();

        XYSeries series = chart.addSeries("Wartości", null, Awidm);

        chart.getStyler().setMarkerSize(8);
        chart.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart.getStyler().setXAxisTitleColor(Color.RED);
        chart.getStyler().setLegendBackgroundColor(Color.PINK);
        chart.getStyler().setChartTitleVisible(true);
        chart.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart.getStyler().setXAxisTickMarkSpacingHint(100);
        chart.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart, "Awidm.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void plot3_1() {
        int N = 100;
        double fs = 100;
        int f = 2;

        double[] realTab_x = new double[N];
        double[] imageTab_x = new double[N];
        double[] Awidm_x = new double[N];
        double[] scalarDec_x = new double[N];
        double[] fk_x = new double[N];

        double[] realTab_y = new double[N];
        double[] imageTab_y = new double[N];
        double[] Awidm_y = new double[N];
        double[] scalarDec_y = new double[N];
        double[] fk_y = new double[N];

        double[] realTab_z = new double[N];
        double[] imageTab_z = new double[N];
        double[] Awidm_z = new double[N];
        double[] scalarDec_z = new double[N];
        double[] fk_z = new double[N];

        double[] realTab_v = new double[N];
        double[] imageTab_v = new double[N];
        double[] Awidm_v = new double[N];
        double[] scalarDec_v = new double[N];
        double[] fk_v = new double[N];

        for (int k = 0; k <= (N-1)/2; k++) {
            double real_x = 0;
            double image_x = 0;
            double real_y = 0;
            double image_y = 0;
            double real_z = 0;
            double image_z = 0;
            double real_v = 0;
            double image_v = 0;
            for (int n = 0; n <= N - 1; n++) {
                double t = (double) k / fs;
                double fi = (-2 * PI * k * n) / N;

                double x = sin(2 * PI * f * cos(3 * PI * t) + t * fi);
                double y = -pow(t, 2) * cos(t / 0.2) * x;
                double z = x * cos(2 * PI * pow(t, 2) + PI) + 0.276 * pow(t, 2) * x;
                double v = sqrt(abs(1.77 - y + z) * cos(5.2 * PI * t) + x + 4);

                real_x += x * cos(fi);
                image_x += x * sin(fi);
                real_y += y * cos(fi);
                image_y += y * sin(fi);
                real_z += z * cos(fi);
                image_z += z * sin(fi);
                real_v += v * cos(fi);
                image_v += v * sin(fi);
            }
            realTab_x[k] = real_x;
            imageTab_x[k] = image_x;
            System.out.println("a" + k + ": " + real_x);
            System.out.println("b" + k + ": " + image_x);
            Awidm_x[k] = sqrt(real_x*real_x + image_x*image_x);
            System.out.println("A: " + Awidm_x[k]);
            scalarDec_x[k] = 10 * log10(Awidm_x[k]);
            System.out.println("Scalar Dec: " + scalarDec_x[k]);
            fk_x[k] = k / (fs/N);
            System.out.println("fk: " + fk_x[k]);

            realTab_y[k] = real_y;
            imageTab_y[k] = image_y;
            System.out.println("a" + k + ": " + real_y);
            System.out.println("b" + k + ": " + image_y);
            Awidm_y[k] = sqrt(real_y*real_y + image_y*image_y);
            System.out.println("A: " + Awidm_y[k]);
            scalarDec_y[k] = 10 * log10(Awidm_y[k]);
            System.out.println("Scalar Dec: " + scalarDec_y[k]);
            fk_y[k] = k / (fs/N);
            System.out.println("fk: " + fk_y[k]);

            realTab_z[k] = real_z;
            imageTab_z[k] = image_z;
            System.out.println("a" + k + ": " + real_z);
            System.out.println("b" + k + ": " + image_z);
            Awidm_z[k] = sqrt(real_z*real_z + image_z*image_z);
            System.out.println("A: " + Awidm_z[k]);
            scalarDec_z[k] = 10 * log10(Awidm_z[k]);
            System.out.println("Scalar Dec: " + scalarDec_z[k]);
            fk_z[k] = k / (fs/N);
            System.out.println("fk: " + fk_z[k]);

            realTab_v[k] = real_v;
            imageTab_v[k] = image_v;
            System.out.println("a" + k + ": " + real_v);
            System.out.println("b" + k + ": " + image_v);
            Awidm_x[k] = sqrt(real_v*real_v + image_v*image_v);
            System.out.println("A: " + Awidm_v[k]);
            scalarDec_v[k] = 10 * log10(Awidm_v[k]);
            System.out.println("Scalar Dec: " + scalarDec_v[k]);
            fk_v[k] = k / (fs/N);
            System.out.println("fk: " + fk_v[k]);
        }

        XYChart chart_x = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();

        XYSeries series_x = chart_x.addSeries("Wartości", null, Awidm_x);

        chart_x.getStyler().setMarkerSize(8);
        chart_x.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_x.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_x.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_x.getStyler().setPlotGridLinesVisible(true);
        chart_x.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_x.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_x.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_x.getStyler().setXAxisTitleColor(Color.RED);
        chart_x.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_x.getStyler().setChartTitleVisible(true);
        chart_x.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_x.getStyler().setLegendVisible(true);
        chart_x.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_x.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_x.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_x.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_x.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_x.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_x, "Awidm_x.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_y = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();

        XYSeries series_y = chart_y.addSeries("Wartości", null, Awidm_y);

        chart_y.getStyler().setMarkerSize(8);
        chart_y.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_y.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_y.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_y.getStyler().setPlotGridLinesVisible(true);
        chart_y.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_y.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_y.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_y.getStyler().setXAxisTitleColor(Color.RED);
        chart_y.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_y.getStyler().setChartTitleVisible(true);
        chart_y.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_y.getStyler().setLegendVisible(true);
        chart_y.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_y.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_y.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_y.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_y.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_y.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_x, "Awidm_y.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_z = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();

        XYSeries series_z = chart_z.addSeries("Wartości", null, Awidm_z);

        chart_z.getStyler().setMarkerSize(8);
        chart_y.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_z.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_z.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_z.getStyler().setPlotGridLinesVisible(true);
        chart_z.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_z.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_z.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_z.getStyler().setXAxisTitleColor(Color.RED);
        chart_z.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_z.getStyler().setChartTitleVisible(true);
        chart_z.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_z.getStyler().setLegendVisible(true);
        chart_z.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_z.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_z.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_z.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_z.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_z.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_x, "Awidm_z.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        XYChart chart_v = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();

        XYSeries series_v = chart_v.addSeries("Wartości", null, Awidm_v);

        chart_v.getStyler().setMarkerSize(8);
        chart_v.getStyler().setCursorColor(XChartSeriesColors.BLUE);
        chart_v.getStyler().setChartBackgroundColor(Color.WHITE);
        chart_v.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart_v.getStyler().setPlotGridLinesVisible(true);
        chart_v.getStyler().setAxisTickLabelsColor(Color.BLACK);
        chart_v.getStyler().setChartTitleBoxBackgroundColor(Color.GREEN);
        chart_v.getStyler().setChartTitleBoxBorderColor(Color.ORANGE);
        chart_v.getStyler().setXAxisTitleColor(Color.RED);
        chart_v.getStyler().setLegendBackgroundColor(Color.PINK);
        chart_v.getStyler().setChartTitleVisible(true);
        chart_v.getStyler().setChartTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        chart_v.getStyler().setLegendVisible(true);
        chart_v.getStyler().setLegendFont(new Font(Font.DIALOG, Font.ITALIC, 20));
        chart_v.getStyler().setLegendPosition(Styler.LegendPosition.InsideSW);
        chart_v.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_v.getStyler().setAxisTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        chart_v.getStyler().setXAxisTickMarkSpacingHint(100);
        chart_v.getStyler().setYAxisTickMarkSpacingHint(100);


        try {
            BitmapEncoder.saveBitmap(chart_x, "Awidm_v.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        plot2();
        plot3_1();
    }
}
