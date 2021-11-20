package wfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SingleTest {

    public static void main(String[] args) throws IOException {
        StringBuilder parName1 = new StringBuilder("StopLoss");
        StringBuilder parName2 = new StringBuilder("Multiplier");
//        StringBuilder parName3 = new StringBuilder("StopLoss");
//        StringBuilder parName4 = new StringBuilder("Multiplier");
        double[] parRange1 = new double[] {0.3, 1.5, 0.3};
        double[] parRange2 = new double[] {1.5, 3.5, 0.5};
//        double[] parRange3 = new double[] {0.3, 1.5, 0.3};
//        double[] parRange4 = new double[] {1.5, 3.5, 0.5};
        int quWeeks = 10;
        double comission = 3;
//        String pathToFileIn = "J:\\ROB\\TEST_FINAM\\1Eu.txt";
//        String pathToFileOut = "J:\\ROB\\JavaProject\\Tests\\testfile1.csv";
        String pathToFileIn = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\1Eu.txt";
        String pathToFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\testfile50.csv";


        // TODO после создания перегруженного метода singleRunArr в классе Working создать соотвествующую функциональность.

        ParRange param = new ParRange();
        param.setParRange(parRange1, parRange2, parName1, parName2);
        FixTester sample = new FixTester();
        FinRes a = new FinRes();
        a.setQuote(pathToFileIn);
        a.workArr(quWeeks + 5);
        a.compressorIn(5);
        FinRes b = a.singleRunArr(quWeeks);
        b.setCom(comission);
        sample.learning(param, b);
        sample.oneTestToFile(pathToFileOut);
        System.out.println("done!");
    }
}