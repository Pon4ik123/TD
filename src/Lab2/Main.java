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
import static java.lang.Number.*;

public class Main {
    public static void plot1(){
        int tc = 1;
        int fs = 8000;
        int N = fs*tc;
        int fi = 0;
        int f = 2;
        double[] tab = new double[N];

        for(int k = 0; k < N; k++) {
            for(int n = 0; n < N; n++) {
                double t = (double) n / fs;
                double x = sin(2 * PI * f * t);

                tab[k] += x * exp((-sqrt(-1)*(2*PI*k*n))/N);
            }
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
            BitmapEncoder.saveBitmap(chart, "dft.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        plot1();
    }
}
