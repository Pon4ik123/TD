package Lab1;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.XChartSeriesColors;

import java.awt.*;
import java.io.IOException;
import java.lang.Math.*;

import static java.lang.Math.*;

public class Main {

    public static void plot1(){
        int tc = 1;
        int fs = 8000;
        int N = fs*tc;
        int fi = 0;
        int f = 2;
        double[] tab = new double[N];
        for(int n = 0; n <= N-1; n++) {
            double t = (double) n / fs;
            double x = sin(2 * PI * f * cos(3 * PI * t) + t * fi);
            tab[n] = x;
            System.out.println("x " + n + ": " + x);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series = chart.addSeries("Wartości", null, tab);//widmaA);

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
            BitmapEncoder.saveBitmap(chart, "x.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void plot2(){
        int tc = 1;
        int fs = 8000;
        int N = fs*tc;
        int fi = 0;
        int f = 2;
        double[] tab_x = new double[N];
        double[] tab_y = new double[N];
        double[] tab_z = new double[N];
        double[] tab_v = new double[N];
        for(int n = 0; n <= N-1; n++) {
            double t = (double) n / fs;
            double x = sin(2 * PI * f * cos(3 * PI * t) + t * fi);
            double y = -pow(t, 2) * cos(t / 0.2) * x;
            double z = x * cos(2 * PI * pow(t, 2) + PI) + 0.276 * pow(t, 2) * x;
            double v = sqrt(abs(1.77 - y + z) * cos(5.2 * PI * t) + x + 4);
            tab_x[n] = x;
            tab_y[n] = y;
            tab_z[n] = z;
            tab_v[n] = v;
            System.out.println("x " + n + ": " + x);
            System.out.println("y " + n + ": " + y);
            System.out.println("z " + n + ": " + z);
            System.out.println("v " + n + ": " + v);
        }
        XYChart chart_x = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series_x = chart_x.addSeries("Wartości", null, tab_x);

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
            BitmapEncoder.saveBitmap(chart_x, "x.png", BitmapEncoder.BitmapFormat.PNG);
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


        XYSeries series_y = chart_y.addSeries("Wartości", null, tab_y);

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
            BitmapEncoder.saveBitmap(chart_y, "y.png", BitmapEncoder.BitmapFormat.PNG);
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


        XYSeries series_z = chart_z.addSeries("Wartości", null, tab_z);

        chart_z.getStyler().setMarkerSize(8);
        chart_z.getStyler().setCursorColor(XChartSeriesColors.BLUE);
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
            BitmapEncoder.saveBitmap(chart_z, "z.png", BitmapEncoder.BitmapFormat.PNG);
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


        XYSeries series_v = chart_v.addSeries("Wartości", null, tab_v);

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
            BitmapEncoder.saveBitmap(chart_v, "v.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void plot3(){
        int tc = 1;
        int fs = 8000;
        int N = fs*tc;
        int fi = 0;
        int f = 2;
        double[] tab = new double[N];
        for(int n = 0; n <= N-1; n++) {
            double t = (double) n / fs;
            if(0.6 > t && t >= 0) {
                double u = pow(t, 2) * sin(20 * PI * t);
                tab[n] = u;
                System.out.println("u " + n + ": " + u);
            } else if (1.5 > t && t >= 0.6) {
                double u = t * exp(t-0.6) * 0.8*sin(10 * PI * t);
                tab[n] = u;
                System.out.println("u " + n + ": " + u);
            } else if (2.4 > t && t >= 1.5) {
                double u = (1 + 0.4 * sin(2 * PI * t)) * sin(30 * PI * t);
                tab[n] = u;
                System.out.println("u " + n + ": " + u);
            } else if (3 > t && t >= 2.4) {
                double u = sqrt(t + cos(14 * t)) * sin(10 * PI * t);
                tab[n] = u;
                System.out.println("u " + n + ": " + u);
            }
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();



        XYSeries series = chart.addSeries("Wartości", null, tab);

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
            BitmapEncoder.saveBitmap(chart, "u.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void plot4_1(){
        int tc = 1;
        int fs = 22050;
        int N = fs*tc;
        double b1 = 0;
        int h = 1;
        double[] tab = new double[N];
        for(int i = 1; i <= h; i++) {
            for (int n = 0; n <= N - 1; n++) {
                double t = (double) n / fs;
                double up = cos(4 * PI * i * t);
                double down = 4 * h * (sin(8 * PI * h * t) + 2);
                b1 += up / down;
            }
            tab[i] = b1;
            System.out.println("B1 " + i + ": " + b1);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series = chart.addSeries("Wartości", null, tab);

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
            BitmapEncoder.saveBitmap(chart, "b1.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void plot4_2(){
        int tc = 1;
        int fs = 22050;
        int N = fs*tc;
        double b1 = 0;
        int h = 5;
        double[] tab = new double[N];
        for(int i = 1; i <= h; i++) {
            for (int n = 0; n <= N - 1; n++) {
                double t = (double) n / fs;
                double up = cos(4 * PI * i * t);
                double down = 4 * h * (sin(8 * PI * i * t) + 2);
                b1 += up / down;
            }
            tab[i] = b1;
            System.out.println("B1 " + i + ": " + b1);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series = chart.addSeries("Wartości", null, tab);

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
            BitmapEncoder.saveBitmap(chart, "b2.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void plot4_3(){
        int tc = 1;
        int fs = 22050;
        int N = fs*tc;
        double b1 = 0;
        int h = 50;
        double[] tab = new double[N];
        for(int i = 1; i <= h; i++) {
            for (int n = 0; n <= N - 1; n++) {
                double t = (double) n / fs;
                double up = cos(4 * PI * i * t);
                double down = 4 * h * (sin(8 * PI * i * t) + 2);
                b1 += up / down;
            }
            tab[i] = b1;
            System.out.println("B1 " + i + ": " + b1);
        }

        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series = chart.addSeries("Wartości", null, tab);

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
            BitmapEncoder.saveBitmap(chart, "b3.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Zadanie 1
        // 5. x(t) = sin(2 * pi * f * t * cos(3 * pi * t) + t * ⌀)
        //plot1();

        // Zadanie 2
        // 4. y(t) = -t^2 * cos(t/0.2) * x(t)
        //    z(t) = x(t) * cos(2*PI * t^2 + PI) + 0.276*t^2 * x(t)
        //    v(t) = sqrt(abs(1.77 - y(t) + z(t)) * cos(5.2 * PI * t) + x(t) + 4);
        //plot2();

        // Zadanie 3
        // 14.      --
        //          | t^2 * sin(20*PI*t)                     dla 0.6 > t >= 0
        //          |
        //          | r * e^t-0.6 * 0.8*sin(10*PI*t)         dla 1.5 > t >= 0.6
        //   u(t) = {
        //          | (1 + 0.4*sin(2*PI*t)) * sin(30*PI*t)   dla 2.4 > t >= 1.5
        //          |
        //          | sqrt(t + cos(14*t)) * sin(10*PI*t)     dla 3 > t >= 2.4
        //          --
        //plot3();

        // Zadanie 4
        // 3.                                                    H1, H2, H3
        //         H_K
        // b_k(t) = Σ (cos(4*PI*h*t)) / (4*h*(sin*(8*PI*h*t)+2))    1,  5, 50
        //         h=1
        //plot4_1();
        //plot4_2();
        //plot4_3();
    }
}