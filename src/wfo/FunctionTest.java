package wfo;

import com.tictactec.ta.lib.MAType;

import java.io.IOException;
import java.util.Arrays;

public class FunctionTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();



        FinRes a = new FinRes();
        a.setQuote("C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\1Eu.txt");
        a.workArr(1);
        a.compressorIn(5);
        double[] b = Indicators.parabolicSAR(a.getHigh(), a.getLow(), 0.02, 0.2);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(a.getHigh()));
        System.out.println(Arrays.toString(a.getLow()));

//        for (int i = 0; i < 30; i++) {
//            System.out.print(b[i] + "," + " ");
//        }
//        System.out.println(" ");
//        for (int i = b.length - 20; i < b.length; i++) {
//            System.out.print(b[i] + "," + " ");
//        }
//        System.out.println(" ");
//        System.out.println(b[2927]);





        long finish = System.currentTimeMillis();
        double elapsed = (finish - start) / 1000;
        System.out.println("Затраченное время в секундах: " + elapsed);
    }


}


