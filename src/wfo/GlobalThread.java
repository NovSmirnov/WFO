package wfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class GlobalThread extends Thread {

    private String[] ticker;
    private int timeFrame;
    private ParRange paramRange;
    private int quWeeks;
    private int[] learnWeeksArr;
    private int testWeeks;
    private String pathToFolderIn;
    private String pathToFolderOut;

    private Semaphore sem;

    private double gSecurity;
    private double unitPrice;
    private boolean done = false;


    public GlobalThread(String[] ticker, int timeFrame, ParRange paramRange,
                        int quWeeks, int[] learnWeeksArr, int testWeeks, String pathToFolderIn, String pathToFolderOut, Semaphore sem) {
        this.ticker = ticker;
        this.timeFrame = timeFrame;
        this.paramRange = paramRange;
        this.quWeeks = quWeeks;
        this.learnWeeksArr = learnWeeksArr;
        this.testWeeks = testWeeks;
        this.pathToFolderIn = pathToFolderIn;
        this.pathToFolderOut = pathToFolderOut;
        this.sem = sem;
    }

    @Override
    public void run() {
        try {
            if (!this.done) {
                sem.acquire();
                this.runThread();
                this.done = true;
                sem.release();
            }

        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void runThread() throws IOException {
        long start = System.currentTimeMillis();
        int timeFrame = this.timeFrame;
        String ticker = this.ticker[0];
        System.out.println("Timeframe = " + timeFrame + " : Ticker = " + ticker);
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
            if (Settings.WFO_TO_FILE) {
                sample.simpleWFOToFile(this.pathToFolderOut);
            }
        } else {
            List<WFOTester> a = new ArrayList<>();
            a.add(sample);
            GlobalTest.setNewWfoList(a);
            if (Settings.WFO_TO_FILE) {
                sample.simpleWFOToFile(this.pathToFolderOut);
            }
        }
        this.done = true;

        long finish = System.currentTimeMillis();
        double elapsed = (finish - start) / 1000;
        System.out.println("Done! " + ticker + " : " + timeFrame + " Затраченно: " + elapsed + " сек");
    }
}
