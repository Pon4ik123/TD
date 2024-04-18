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

    public static void plot1(int fn, int fm, double ka, double kp, double kf){
        int fs = 8000;
        int tc = 2;
        int N = fs*tc;
//        int fm = 2;
//        int fn = 2;
//        int ka = 5;
        double[] tab_a = new double[N];
        double[] tab_p = new double[N];
        double[] tab_f = new double[N];

        for (int i = 0; i < N; i++) {
            double t = (double) i / fs;
            double mt = sin(2 * PI * fm * t);

            double za = (ka * mt + 1) * cos(2 * PI * fn * t);
            double zp = cos(2 * PI * fn * t + kp * mt);
            double zf = cos(2 * PI * fn * t + (kf /fm) * mt);

            tab_a[i] += za;
            tab_p[i] += zp;
            tab_f[i] += zf;
        }

        XYChart chart_a = new XYChartBuilder()
                .width(800)
                .height(600)
                .title("Wykres funkcji")
                .xAxisTitle("Czas")
                .yAxisTitle("Wartość")
                .build();


        XYSeries series_a = chart_a.addSeries("Wartości", null, tab_p);//widmaA);

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
            BitmapEncoder.saveBitmap(chart_a, "zp.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        plot1(5, 1, 1, 0, 0);
    }
}
