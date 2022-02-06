package dhe;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Edentifyer a = new Edentifyer();
        a.setJData("J:\\ROB\\JavaProject\\Tests\\Equal\\robDeals.csv");
        a.setTData("J:\\ROB\\JavaProject\\Tests\\Equal\\trades.csv");
//        a.setJData("C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robDeals.csv");
//        a.setTData("C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TESTS\\CheckDeals\\trades.csv");

        a.tToConsole();
        System.out.println();
        a.jToConsole();
        System.out.println();
        a.algoEquals();
        System.out.println();
        a.findMistake();
    }
}
