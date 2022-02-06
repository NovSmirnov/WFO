package wfo;

import com.tictactec.ta.lib.MAType;

import java.io.IOException;
import java.util.Arrays;

public class FunctionTest {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        double[] a = {87886, 87876, 87895, 87881, 87888, 87902, 87920, 87910, 87922, 87920, 87937, 87918, 87909, 87928, 87942, 87794};
        double[] b = Indicators.rsi(a, 9);
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(a));

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


