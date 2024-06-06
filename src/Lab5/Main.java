package Lab5;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.PI;
import static java.lang.Math.sin;

public class Main {
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
        double fs = 2000;
        double tc = 1.0;
        double tb = tc / bit.length;
        int N = (int) (fs * tc);
        int w = 2;
        double a1 = 0.5;
        double a2 = 1.0;
        double fn = w * (1 / tb);
        int tbp = (int) (tb * fs);

        double[] za = new double[N];
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
        double[] zp = new double[N];
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
        double[] zf = new double[N];
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


        XYChart chartA = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartA.addSeries("Wartości", null, za).setMarker(SeriesMarkers.NONE);


        XYChart chartP = new XYChartBuilder().width(1920).height(1080).title("Sygnały zp").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartP.addSeries("Wartości", null, zp).setMarker(SeriesMarkers.NONE);


        XYChart chartF = new XYChartBuilder().width(1920).height(1080).title("Sygnały zf").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartF.addSeries("Wartości", null, zf).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartA, "src/Lab5/za.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartP, "src/Lab5/zp.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartF, "src/Lab5/zf.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
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

        List<Integer> counterASK = new ArrayList<>();
        int oneCounterASK = 0;
        for (int i = 0; i < bit.length; i++) {
            for (int j = i*tbp; j < tbp+tbp*i; j++) {
                if (cASK[j] == 1) oneCounterASK++;
            }
            if (oneCounterASK>=tbp/2-1) counterASK.add(1);
            else counterASK.add(0);

            oneCounterASK = 0;
        }
        System.out.println(counterASK);

        XYChart chartZASK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartZASK.addSeries("Wartości", null, zASK).setMarker(SeriesMarkers.NONE);

        XYChart chartPASK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartPASK.addSeries("Wartości", null, pASK).setMarker(SeriesMarkers.NONE);

        XYChart chartCASK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartCASK.addSeries("Wartości", null, cASK).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartZASK, "src/Lab5/zASK.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartPASK, "src/Lab5/pASK.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartCASK, "src/Lab5/cASK.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] zPSK = new double[N];
        for (int i = 0; i < zPSK.length; i++) {
            zPSK[i] = sin(2 * PI * fn * i / fs) * zp[i];
        }

        double[] pPSK = new double[N];
        for (int i = 0; i < N; i++) {
            double s = 0;
            for (int j = 0; j < tb*fs; j++) {
                int index = (int) (i * tb*fs + j);
                if (index < N) {
                    s += zPSK[index];
                    pPSK[index] = s;
                }
            }
        }

        double[] cPSK = new double[N];
        for (int i = 0; i < cPSK.length; i++) {
            cPSK[i] = pPSK[i] < 0 ? 1 : 0;
        }

        List<Integer> counterPSK = new ArrayList<>();
        int oneCounterPSK = 0;
        for (int i = 0; i < bit.length; i++) {
            for (int j = i*tbp; j < tbp+tbp*i; j++) {
                if (cPSK[j] == 1) oneCounterPSK++;
            }
            if (oneCounterPSK>=tbp/2-1) counterPSK.add(1);
            else counterPSK.add(0);

            oneCounterPSK = 0;
        }
        System.out.println(counterPSK);

        XYChart chartZPSK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartZPSK.addSeries("Wartości", null, zPSK).setMarker(SeriesMarkers.NONE);

        XYChart chartPPSK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartPPSK.addSeries("Wartości", null, pPSK).setMarker(SeriesMarkers.NONE);

        XYChart chartCPSK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartCPSK.addSeries("Wartości", null, cPSK).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartZPSK, "src/Lab5/zPSK.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartPPSK, "src/Lab5/pPSK.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartCPSK, "src/Lab5/cPSK.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        double[] zFSK1 = new double[N];
        for (int i = 0; i < zFSK1.length; i++) {
            zFSK1[i] = sin(2 * PI * fn1 * i / fs) * zf[i];
        }

        double[] pFSK1 = new double[N];
        for (int i = 0; i < N; i++) {
            double s = 0;
            for (int j = 0; j < tb*fs; j++) {
                int index = (int) (i * tb*fs + j);
                if (index < N) {
                    s += zFSK1[index];
                    pFSK1[index] = s;
                }
            }
        }

        double[] zFSK2 = new double[N];
        for (int i = 0; i < zFSK2.length; i++) {
            zFSK2[i] = sin(2 * PI * fn2 * i / fs) * zf[i];
        }

        double[] pFSK2 = new double[N];
        for (int i = 0; i < N; i++) {
            double s = 0;
            for (int j = 0; j < tb*fs; j++) {
                int index = (int) (i * tb*fs + j);
                if (index < N) {
                    s += zFSK2[index];
                    pFSK2[index] = s;
                }
            }
        }

        double[] pFSK = new double[N];
        for (int i = 0; i < N; i++) {
            pFSK[i] = pFSK2[i] - pFSK1[i];
        }

        double[] cFSK = new double[N];
        for (int i = 0; i < cFSK.length; i++) {
            cFSK[i] = pFSK[i] > 0 ? 1 : 0;
        }

        List<Integer> counterFSK = new ArrayList<>();
        int oneCounterFSK = 0;
        for (int i = 0; i < bit.length; i++) {
            for (int j = i*tbp; j < tbp+tbp*i; j++) {
                if (cFSK[j] == 1) oneCounterFSK++;
            }
            if (oneCounterFSK>=tbp/2-1) counterFSK.add(1);
            else counterFSK.add(0);

            oneCounterFSK = 0;
        }
        System.out.println(counterFSK);

        XYChart chartZFSK1 = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartZFSK1.addSeries("Wartości", null, zFSK1).setMarker(SeriesMarkers.NONE);

        XYChart chartZFSK2 = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartZFSK2.addSeries("Wartości", null, zFSK2).setMarker(SeriesMarkers.NONE);

        XYChart chartPFSK1 = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartPFSK1.addSeries("Wartości", null, pFSK1).setMarker(SeriesMarkers.NONE);

        XYChart chartPFSK2 = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartPFSK2.addSeries("Wartości", null, pFSK2).setMarker(SeriesMarkers.NONE);

        XYChart chartPFSK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartPFSK.addSeries("Wartości", null, pFSK).setMarker(SeriesMarkers.NONE);

        XYChart chartCFSK = new XYChartBuilder().width(1920).height(1080).title("Sygnały za").xAxisTitle("Czas").yAxisTitle("Wartość").build();
        chartCFSK.addSeries("Wartości", null, cFSK).setMarker(SeriesMarkers.NONE);

        try {
            BitmapEncoder.saveBitmap(chartZFSK1, "src/Lab5/zFSK1.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartZFSK2, "src/Lab5/zFSK2.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartPFSK1, "src/Lab5/pFSK1.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartPFSK2, "src/Lab5/pFSK2.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartPFSK, "src/Lab5/pFSK.png", BitmapEncoder.BitmapFormat.PNG);
            BitmapEncoder.saveBitmap(chartCFSK, "src/Lab5/cFSK.png", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        plot1();
    }
}
