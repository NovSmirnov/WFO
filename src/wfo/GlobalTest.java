package wfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class GlobalTest {
    public static void robFunc(FinRes a, Parameter param) {
        a.robRsiTrStop_v_0_1(param);
    }  // !!! «¿œŒÀÕﬂ≈Ã

    public final static String ROB_NAME = "robRsiTrStop_v_0_1"; // !!! «¿œŒÀÕﬂ≈Ã

    protected static List<WFOTester> globList;
    
    public static void setNewWfoList(List<WFOTester> newObject) {
    	globList = newObject;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
//		ExecutorService executor = Executors.newFixedThreadPool(1);

//    	String[][] tickers = new String[][] {
//		    {"AFLT", "2", "1172.05", "1"}, {"ALRS", "3", "2213.08", "1"},
//			{"BR", "0.006", "10999.07", "772.09"}, {"ED", "0.00006", "3774.66", "77209"},
//		    {"Eu", "3", "7236.3", "1"}, {"GAZR", "4", "6442.25", "1"},
//			{"GOLD", "0.2", "10186.04", "77.209"}, {"LKOH", "6", "13333.8", "1"},
//		    {"MGNT", "2", "1041.39", "1"}, {"MIX", "30", "71532.5", "1"},
//		    {"MXI", "0.3", "7153.97", "10"}, {"NG", "0.0005", "6474.86", "7720.9"},
//		    {"ROSN", "6", "15202.08", "1"}, {"RTS", "20", "45883.73", "1.54418"},
//			{"SBPR", "4", "5926.79", "1"}, {"SBRF", "4", "6112.68", "1"},
//		    {"Si", "3", "6378.62", "1"}, {"SILV", "0.004", "3624.37", "772.09"},
//			{"SNGP", "4", "9486.04", "1"}, {"SNGR", "4", "9154.71", "1"},
//		    {"VTBR", "2", "1097.52", "1"}, {"YNDF", "5", "7493.83", "1"}
//	    };
    	String[][] tickers = new String[][] {
		    {"AFLT", "2", "1172.05", "1"}, {"ALRS", "3", "2213.08", "1"},
			{"BR", "0.006", "10999.07", "772.09"}, {"ED", "0.00006", "3774.66", "77209"},
		    {"Eu", "3", "7236.3", "1"}, {"GAZR", "4", "6442.25", "1"},
			{"GOLD", "0.2", "10186.04", "77.209"}, {"LKOH", "6", "13333.8", "1"},
		    {"MGNT", "2", "1041.39", "1"}, {"MIX", "30", "71532.5", "1"},
		    {"MXI", "0.3", "7153.97", "10"}, {"NG", "0.0005", "6474.86", "7720.9"},
		    {"ROSN", "6", "15202.08", "1"}, {"RTS", "20", "45883.73", "1.54418"},
			{"SBPR", "4", "5926.79", "1"}, {"SBRF", "4", "6112.68", "1"},
		    {"Si", "3", "6378.62", "1"}, {"SILV", "0.004", "3624.37", "772.09"},
			{"SNGP", "4", "9486.04", "1"}, {"SNGR", "4", "9154.71", "1"},
		    {"VTBR", "2", "1097.52", "1"}, {"YNDF", "5", "7493.83", "1"}
    	};
//		  int[] timeFrames = new int[] {2, 4, 5, 6, 10, 12, 15, 30};
		int[] timeFrames = new int[] {2, 4, 5, 6, 10, 12, 15};

//        String pathToFolderIn = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\";
//        String pathToFolderOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TESTS\\ROB_JAVA\\";
        String pathToFolderIn = "J:\\ROB\\TEST_FINAM\\";
        String pathToFolderOut = "J:\\ROB\\JavaProject\\Tests\\";

		StringBuilder parName1 = new StringBuilder("MaxPeriod");
		StringBuilder parName2 = new StringBuilder("MinPeriod");
		StringBuilder parName3 = new StringBuilder("StopLoss");
		StringBuilder parName4 = new StringBuilder("Multiplier");

//        double[][] steps = new double[][] {{5, 5, 0.3, 0.5}, {10, 10, 0.3, 0.5}, {20, 20, 0.3, 0.5}};
		double[][] steps = new double[][] {{10, 10, 0.3, 0.5}};
		int quTreads = tickers.length * timeFrames.length * steps.length;
		GlobalThread[] threads = new GlobalThread[quTreads];
		int threadIndex = 0;

    	for(int z = 0; z < steps.length; z++) {
			System.out.println(steps[z][0]);
			double[] parRange1 = new double[] {10, 150, steps[z][0]};
			double[] parRange2 = new double[] {10, 150, steps[z][1]};
			double[] parRange3 = new double[] {0.3, 1.5, steps[z][2]};
			double[] parRange4 = new double[] {1.5, 3.5, steps[z][3]};
			ParRange param = new ParRange();
			param.setParRange(parRange1, parRange2, parRange3, parRange4, parName1, parName2, parName3, parName4);
			Semaphore sem = new Semaphore(Settings.quThreads);
	    	for (int i = 0; i < timeFrames.length; i++) {
				for (int j = 0; j < tickers.length; j++) {
//					System.out.println("Timeframe = " + timeFrames[i] + " " + "Ticker = " + tickers[j][0]);
					GlobalThread myThread = new GlobalThread(tickers[j], timeFrames[i], param,
							Settings.QU_WEEKS, Settings.LEARN_WEEKS_ARR, Settings.TEST_WEEKS, pathToFolderIn, pathToFolderOut, sem);
					myThread.start();
					threads[threadIndex] = myThread;
					threadIndex++;
				}
			}
    	}
		for (GlobalThread thread : threads) {
			thread.join();
		}
    	WFOTester[] finalList = globList.toArray(new WFOTester[globList.size()]);
    	Analizer summary = new Analizer();
    	summary.setData(finalList);
    	summary.sortBy();
    	summary.sortedIntegrToFile(pathToFolderOut + ROB_NAME + ".csv");
		summary.analitic();
		summary.analiticsToFile(pathToFolderOut + "ANLZ_" + ROB_NAME + ".csv");
    	
		long finish = System.currentTimeMillis();
		double elapsed = (finish - start) / 1000;
		System.out.println("«‡Ú‡˜ÂÌÌÓÂ ‚ÂÏˇ ‚ ÒÂÍÛÌ‰‡ı: " + elapsed);
    }
}
