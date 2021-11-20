package wfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GlobalThread extends Thread {

    private String[] ticker;
    private int timeFrame;
    private ParRange paramRange;
    private int quWeeks;
    private int[] learnWeeksArr;
    private int testWeeks;
    private String pathToFolderIn;
    private String pathToFolderOut;

    private double gSecurity;
    private double unitPrice;


    public GlobalThread(String[] ticker, int timeFrame, ParRange paramRange,
                        int quWeeks, int[] learnWeeksArr, int testWeeks, String pathToFolderIn, String pathToFolderOut) {
        this.ticker = ticker;
        this.timeFrame = timeFrame;
        this.paramRange = paramRange;
        this.quWeeks = quWeeks;
        this.learnWeeksArr = learnWeeksArr;
        this.testWeeks = testWeeks;
        this.pathToFolderIn = pathToFolderIn;
        this.pathToFolderOut = pathToFolderOut;
    }

    @Override
    public void run() {
        try {
            this.runThread();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void runThread() throws IOException {
        int timeFrame = this.timeFrame;
        String ticker = this.ticker[0];
        double comission = Double.parseDouble(this.ticker[1]);
        int quWeeks = this.quWeeks;
        int[] learnWeeksArr = this.learnWeeksArr;
        int testWeeks = this.testWeeks;
        double gSecurity = Double.parseDouble(this.ticker[2]);
        double unitPrice = Double.parseDouble(this.ticker[3]);
        String pathToFolderIn = this.pathToFolderIn;
        WFOTester sample = new WFOTester();

        sample.wfoTestGlobal(ticker, timeFrame, this.paramRange, quWeeks, learnWeeksArr, testWeeks, comission, gSecurity, unitPrice, pathToFolderIn);
        if (GlobalTest.globList != null) {
            GlobalTest.globList.add(sample);
            if (GlobalTest.WFO_TO_FILE) {
                sample.simpleWFOToFile(this.pathToFolderOut);
            }
        } else {
            List<WFOTester> a = new ArrayList<>();
            a.add(sample);
            GlobalTest.setNewWfoList(a);
            if (GlobalTest.WFO_TO_FILE) {
                sample.simpleWFOToFile(this.pathToFolderOut);
            }
        }
        System.out.println("done!");
    }
}
