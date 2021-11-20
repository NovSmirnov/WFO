package wfo;

import java.io.IOException;

public class AlgoTest {
    public static void main(String[] args) throws IOException {
        StringBuilder parName1 = new StringBuilder("StopLoss"); // ��������� ��������� 1
        StringBuilder parName2 = new StringBuilder("Multiplier"); // ��������� ��������� 2
//        StringBuilder parName3 = new StringBuilder("StopLoss"); // ��������� ��������� 3
//        StringBuilder parName4 = new StringBuilder("Multiplier"); // ��������� ��������� 4
        double par1 = 0.6; // �������� 1
        double par2 = 2.5; // �������� 2
//        double par3 = 0.6; // �������� 3
//        double par4 = 2.5; // �������� 3
        int quWeeks = 10; // ���������� ������ ��� ������������ ��������� �� ��������� ���� ����������� ���������.
        double comission = 3; // �������� � �������� ��������� ���������.
//        String pathToFileIn = "J:\\ROB\\TEST_FINAM\\1Eu.txt";
//        String pathToFileOut = "J:\\ROB\\JavaProject\\Tests\\testfile1.csv";
        String pathToFileIn = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\FINAM_TXT\\1Eu.txt";
        String pathToFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robTest.csv";
        String dealsFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robDeals.csv";
        String finResFileOut = "C:\\Users\\SMIRNOV_AV\\Desktop\\PY\\TEST_FINAM\\robFinRes.csv";

        Parameter parameters = new Parameter();
        parameters.setPar(par1, par2, parName1, parName2);
        FinRes a = new FinRes();
        a.setQuote(pathToFileIn);
        a.workArr(quWeeks + 5);
        a.compressorIn(5);
        FinRes b = a.singleRunArr(quWeeks);
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
