package wfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GlobalTest {
    public static void robFunc(FinRes a, Parameter param) {
        a.robVoronySoldatyStopTake_v_0_1(param);
    }  // !!! «јѕќЋЌя≈ћ
    public final static String ROB_NAME = "robVoronySoldatyStopTake_v_0_1"; // !!! «јѕќЋЌя≈ћ
    public final static int FILT_1_WEEKS = 15;
    public final static int FILT_2_WEEKS = 30;
    public final static int FILT_4_WEEKS = 60;
	public final static boolean WFO_TO_FILE = false;
    
    protected static List<WFOTester> globList;
    
    public static void setNewWfoList(List<WFOTester> newObject) {
    	globList = newObject;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
//		ExecutorService executor = Executors.newFixedThreadPool(1);

//    	String[][] tickers = new String[][] {
//		    {"AFLT", "2", "985.21", "1"}, {"ALRS", "3", "1957.54", "1"},
//			{"BR", "0.006", "9766.5", "726.808"}, {"ED", "0.00006", "3377.09", "72680.8"},
//		    {"Eu", "3", "5440.09", "1"}, {"GAZR", "4", "5022.42", "1"},
//			{"GOLD", "0.2", "9321.08", "72.6808"}, {"LKOH", "6", "10422.17", "1"},
//		    {"MGNT", "2", "988.51", "1"}, {"MIX", "30", "45367.36", "1"},
//		    {"MXI", "0.3", "4536.65", "10"}, {"NG", "0.0005", "7761.09", "7268.08"},
//		    {"ROSN", "6", "9315.31", "1"}, {"RTS", "20", "34396.25", "1.453616"},
//			{"SBPR", "4", "4766.16", "1"}, {"SBRF", "4", "5256.34", "1"},
//		    {"Si", "3", "4753.62", "1"}, {"SILV", "0.004", "3403,67", "726.808"},
//			{"SNGP", "5", "6056.54", "1"}, {"SNGR", "5", "6845.65", "1"},
//		    {"VTBR", "2", "756.6", "1"}, {"YNDF", "6", "8793.98", "1"}
//	    };
//    	int[] timeFrames = new int[] {2, 4, 5, 6, 10, 12, 15, 30};
    	String[][] tickers = new String[][] {
		    {"AFLT", "2", "985.21", "1"}, {"ALRS", "3", "1957.54", "1"},
			{"BR", "0.006", "9766.5", "726.808"}, {"ED", "0.00006", "3377.09", "72680.8"},
		    {"Eu", "3", "5440.09", "1"}, {"GAZR", "4", "5022.42", "1"},
			{"GOLD", "0.2", "9321.08", "72.6808"}, {"LKOH", "6", "10422.17", "1"},
		    {"MGNT", "2", "988.51", "1"}, {"MIX", "30", "45367.36", "1"},
		    {"MXI", "0.3", "4536.65", "10"}, {"NG", "0.0005", "7761.09", "7268.08"},
		    {"ROSN", "6", "9315.31", "1"}, {"RTS", "20", "34396.25", "1.453616"},
			{"SBPR", "4", "4766.16", "1"}, {"SBRF", "4", "5256.34", "1"},
		    {"Si", "3", "4753.62", "1"}, {"SILV", "0.004", "3403.67", "726.808"},
			{"SNGP", "5", "6056.54", "1"}, {"SNGR", "5", "6845.65", "1"},
		    {"VTBR", "2", "756.6", "1"}, {"YNDF", "6", "8793.98", "1"}
    	};
		int[] timeFrames = new int[] {2, 4, 5, 6, 10, 12};
		int quWeeks = 72;
		int[] learnWeeksArr = new int[] {1, 2, 4};
		int testWeeks = 1;
		StringBuilder parName1 = new StringBuilder("StopLoss");
		StringBuilder parName2 = new StringBuilder("Multiplier");
//        double[][] steps = new double[][] {{5, 5, 0.3, 0.5}, {10, 10, 0.3, 0.5}, {20, 20, 0.3, 0.5}};
        String pathToFolderIn = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\";
        String pathToFolderOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TESTS\\ROB_JAVA\\";
//        String pathToFolderIn = "J:\\ROB\\TEST_FINAM\\";
//        String pathToFolderOut = "J:\\ROB\\JavaProject\\Tests\\";

		int emptyCores = 2; // количество €дер процессора, которое необходимо оставить свободными при работе программы
		int quCores = Runtime.getRuntime().availableProcessors(); // вычисление количества €дер (потоков) процессора
		int quThreads = quCores - emptyCores;
		double[][] steps = new double[][] {{0.3, 0.5}}; // Ўаги параметров.
    	for(int z = 0; z < steps.length; z++) {
			System.out.println(steps[z][0]);
			double[] parRange1 = new double[] {0.3, 1.5, steps[z][0]};
			double[] parRange2 = new double[] {1.5, 3.5, steps[z][1]};
			ParRange param = new ParRange();
			param.setParRange(parRange1, parRange2, parName1, parName2);
	    	for (int i = 0; i < timeFrames.length; i++) {
				for (int j = 0; j < tickers.length; j = j + quThreads) {
					GlobalThread[] trArray = new GlobalThread[quThreads];
					for (int k = 0; k < quThreads; k++) {
						if (j + k < tickers.length) {
							System.out.println("Timeframe = " + timeFrames[i] + " " + "Ticker = " + tickers[j + k][0]);
							GlobalThread myThread = new GlobalThread(tickers[j + k], timeFrames[i], param,
									quWeeks, learnWeeksArr, testWeeks, pathToFolderIn, pathToFolderOut);
							trArray[k] = myThread;
							myThread.start();
						}
					}
					for (int k = 0; k < quThreads; k++) {
						if (trArray[k] != null) {
							trArray[k].join();
						}
				    }
				}
			}
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
		System.out.println("«атраченное врем€ в секундах: " + elapsed);
    }
}
