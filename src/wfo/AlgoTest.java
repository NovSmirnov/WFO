package wfo;

import java.io.IOException;

public class AlgoTest {
    public static void main(String[] args) throws IOException {
        StringBuilder parName1 = new StringBuilder("MaxPeriod");
        StringBuilder parName2 = new StringBuilder("MinPeriod");
        StringBuilder parName3 = new StringBuilder("StopLoss");
        StringBuilder parName4 = new StringBuilder("Multiplier");
        double par1 = 100;
        double par2 = 100;
        double par3 = 0.3;
        double par4 = 2;

//        double par1 = 0.6; // �������� 1
//        double par2 = 2.5; // �������� 2
//        double par3 = 0.6; // �������� 3
//        double par4 = 2.5; // �������� 3
        int quWeeks = 2; // ���������� ������ ��� ������������ ��������� �� ��������� ���� ����������� ���������.
        double comission = 3; // �������� � �������� ��������� ���������.
        String pathToFileIn = "J:\\ROB\\TEST_FINAM\\1Eu.txt";
        String pathToFileOut = "J:\\ROB\\JavaProject\\Tests\\testfile1.csv";
        String dealsFileOut = "J:\\ROB\\JavaProject\\Tests\\robDeals.csv";
        String finResFileOut = "J:\\ROB\\JavaProject\\Tests\\robFinRes.csv";

//        String pathToFileIn = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\1Eu.txt";
//        String pathToFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robTest.csv";
//        String dealsFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robDeals.csv";
//        String finResFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robFinRes.csv";

        Parameter parameters = new Parameter();
        parameters.setPar(par1, par2, par3, par4, parName1, parName2, parName3, parName4);
        FinRes a = new FinRes();
        a.setQuote(pathToFileIn);
        a.workArr(quWeeks + 5);
        a.compressorIn(5);
        FinRes b = a.singleRunArr(20211011, 20211111);
        System.out.println("������ ������ ������ ���������:  " + b.startIndex);
        b.setCom(comission);
        GlobalTest.robFunc(b, parameters); // !!! ����������� ������� ��������� � GlobalTest.
//        b.robVoronySoldatyStopTake_v_0_1(parameters); // !!! ������������ �������� ��� ������������ !!! ������� �����
        b.robToFile(pathToFileOut);
        b.dealsToFile(dealsFileOut);
        b.counter();
        b.finResToFile(finResFileOut);
        System.out.println("Done!");
    }
}
