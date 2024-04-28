package Lab4;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Main {
    public static void plot1(){
        int[] bit = {1, 0, 0, 1, 1, 0, 0, 1, 1, 1};
        double fs = 8000;
        double tc = 1.0;
        double tb = tc / bit.length;
        int N = (int) (fs * tc);
        int w = 2;
        double a1 =  0.2;
        double a2 =  0.5;
        double fn = w *(1/tb);
        int tbp = (int) (tb * fs);

        double[] za = new double[N];
        int ofs = 0;
        for (int i = 0; i <bit.length; i++) {
            int bitIndex = bit[i];
            for(int j = 0;j<tbp;j++) {
                double t = (double) (i * ofs +j) / fs;

                if (bitIndex == 1) {
                    za[j+ofs] = a2 * sin(2 * PI * fn * t);
                } else {
                    za[j+ofs] = a1 * sin(2 * PI * fn * t);
                }
            }

            ofs = ofs + tbp;
        }

        int ofs1 = 0;
        double[] zp = new double[N];
        for (int i = 0; i <bit.length; i++) {
            int bitIndex = bit[i];
            for(int j = 0;j<tbp;j++) {
                double t = (double) (i * ofs1 +j)/fs;

                if (bitIndex == 1) {
                    zp[j+ofs1] = sin(2 * PI * fn * t + PI);
                } else {
                    zp[j+ofs1] = sin(2 * PI * fn * t);
                }
            }

            ofs1 = ofs1 + tbp;
        }

        int ofs2 = 0;
        double[] zf = new double[N];
        double fn1 = (w + 1) / tb;
        double fn2 = (w + 2) / tb;
        for (int i = 0; i <bit.length; i++) {
            int bitIndex = bit[i];
            for(int j = 0;j<tbp;j++) {
                double t = (double) (i * ofs2 +j)/fs;

                if (bitIndex == 1) {
                    zf[j+ofs2] = sin(2 * PI * fn2 * t);
                } else {
                    zf[j+ofs2] = sin(2 * PI * fn1 * t);
                }
            }

            ofs2 = ofs2 + tbp;
        }

        XYChart chartA = new XYChartBuilder().width(1920).height(1080).title("Sygnały zat").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesA = (XYSeries) chartA.addSeries("Wartości", null, za).setMarker(SeriesMarkers.NONE);

        XYChart chartP = new XYChartBuilder().width(1920).height(1080).title("Sygnały zpt").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesP = (XYSeries) chartP.addSeries("Wartości", null, zp).setMarker(SeriesMarkers.NONE);

        XYChart chartF = new XYChartBuilder().width(1920).height(1080).title("Sygnały zft").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        XYSeries seriesF = (XYSeries) chartF.addSeries("Wartości", null, zf).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartA, "za.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartP, "zp.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartF, "zf.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        plot1();
    }
}
