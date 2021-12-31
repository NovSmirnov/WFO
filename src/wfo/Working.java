package wfo;

import com.tictactec.ta.lib.MAType;

import javax.xml.stream.FactoryConfigurationError;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Scanner;

/**
 * ����� ��������� � �������� ���� � ������, ����������� ��� ������ ��������� ���������, � ��� �� ���� ��������
 * ���������.
 */
public class Working extends QuoteColArr {
    /**
     * ������ �� ���������� ������� ��� ��������� ������ � ���� ��� ����.
     */
    protected int[] pos;
    /**
     * ������ �� ���������� ���������� ����� ������.
     */
    protected int[] quan;
    /**
     * ������ �� ���������� ���� �������� ��� �������� �������
     */
    protected double[] price;
    /**
     * ������ �� ���������� ���� ��������
     */
    protected double[] stopLoss;
    /**
     * ������ �� ���������� �������, �� �������� ���� ��������/�������� �������
     */
    protected String[] signal;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind1;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind2;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind3;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind4;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind5;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind6;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind7;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind8;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind9;
    /**
     * ������ �� ���������� ���������� ���������� ��� ��������
     */
    protected double[] ind10;
    /**
     * ������ � ����������� ������
     */
    protected double[] parameters;
    /**
     * ������ � ���������� ���������� ������
     */
    protected StringBuilder[] paramNames;
    /**
     * ����� ��������� ��� ��������� ����������� ������ � ����.
     */
    protected String robHeader = "<TICKER>;<PER>;<DATE>;<TIME>;<OPEN>;<HIGH>;<LOW>;<CLOSE>;<VOL>;POS;QUAN;PRICE;StLoss; Signal";
    /**
     * ����� �������, � �������� �������� ������ �������� � ������������� ��������� ������
     */
    protected int startIndex;
    /**
     * �������� �������� ������ ��������� ���������.
     */
    protected String comment;



    /**
     * ������ ����� ������ ������ FinRes ��� ���������� ������������ ��������� �������� ��� ������ �������� ������
     * ��������� ���������.
     * @param quWeeks ���������� ������ ��� ������� ������� ��������� �� ��������� ���� � �����.
     * @return ����� ������ ������ FinRes ��� ���������� ������������ ��������� �������� ��� ������ �������� ������.
     */
    public FinRes singleRunArr (int quWeeks) {
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, quWeeks + 2);
        int begining = startDate.getDate() + 1;
        int counter = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining) {
                counter++;
            }
        }
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        FinRes newArr = new FinRes();
        Dates realStart = new Dates();
        realStart.weeksMinus(lastDate, quWeeks);
        int newBegining = realStart.getDate() + 1;
        boolean indicator = true;
        int newIndex = 0;
        for (int i = this.getDate().length - counter; i < this.getDate().length; i++) {
            tickArray[newIndex] = this.getTicker()[i];
            tfArray[newIndex] = this.getTimeframe()[i];
            dateArray[newIndex] = this.getDate()[i];
            timeArray[newIndex] = this.getTime()[i];
            openArray[newIndex] = this.getOpen()[i];
            highArray[newIndex] = this.getHigh()[i];
            lowArray[newIndex] = this.getLow()[i];
            closeArray[newIndex] = this.getClose()[i];
            volumeArray[newIndex] = this.getVolume()[i];
            if (this.getDate()[i] >= newBegining && indicator) {
                newArr.startIndex = newIndex;
                indicator = false;
            }
            newIndex++;
        }
        newArr.ticker = tickArray;
        newArr.timeframe = tfArray;
        newArr.date = dateArray;
        newArr.time = timeArray;
        newArr.open = openArray;
        newArr.high = highArray;
        newArr.low = lowArray;
        newArr.close = closeArray;
        newArr.volume = volumeArray;

        return newArr;
    }

    /**
     * ������ ����� ������ ������ FinRes ��� ���������� ������������ ��������� �������� ��� ������ �������� ������
     * ��������� ���������, ������� ����������� ������ �� �������������� ���.
     * @param firstDate ���� ������ ������ ���������. (��������)
     * @param secondDate ���� ��������� ������ ��������� (��������)
     * @return ����� ������ ������ FinRes ��� ���������� ������������ ��������� �������� ��� ������ �������� ������.
     */
    public FinRes singleRunArr (int firstDate, int secondDate) {
        Dates startDate = new Dates();
        startDate.daysMinus(firstDate, 7);
        int begining = startDate.getDate();
        int counter1 = 0;
        int counter2 = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining && this.getDate()[i] <= secondDate) {
                counter1++;
            }
            if (this.getDate()[i] >= begining) {
                counter2++;
            }
        }
        String[] tickArray = new String[counter1];
        int[] tfArray = new int[counter1];
        int[] dateArray = new int[counter1];
        String[] timeArray = new String[counter1];
        double[] openArray = new double[counter1];
        double[] highArray = new double[counter1];
        double[] lowArray = new double[counter1];
        double[] closeArray = new double[counter1];
        int[] volumeArray = new int[counter1];

        FinRes newArr = new FinRes();
        boolean indicator = true;
        int newIndex = 0;
        for (int i = this.getDate().length - counter2; i < this.getDate().length - counter2 + counter1; i++) {
            tickArray[newIndex] = this.getTicker()[i];
            tfArray[newIndex] = this.getTimeframe()[i];
            dateArray[newIndex] = this.getDate()[i];
            timeArray[newIndex] = this.getTime()[i];
            openArray[newIndex] = this.getOpen()[i];
            highArray[newIndex] = this.getHigh()[i];
            lowArray[newIndex] = this.getLow()[i];
            closeArray[newIndex] = this.getClose()[i];
            volumeArray[newIndex] = this.getVolume()[i];
            if (this.getDate()[i] >= firstDate && indicator) {
                newArr.startIndex = newIndex;
                indicator = false;
            }
            newIndex++;
        }
        newArr.ticker = tickArray;
        newArr.timeframe = tfArray;
        newArr.date = dateArray;
        newArr.time = timeArray;
        newArr.open = openArray;
        newArr.high = highArray;
        newArr.low = lowArray;
        newArr.close = closeArray;
        newArr.volume = volumeArray;

        return newArr;
    }

    /**
     * @param quWeeks ���������� ������ ��� ������ ������ ���������.
     *                ��������� ���������� ��������� ����� �������������� ������, � ������� ��������� ������� �� ���������� ��������� ��
     *                ������������ ���������� � ����������� � ����������� ������ ��� ������ ���������.
     */
    public void workArr(int quWeeks) {
        int weeks = quWeeks + 5;
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, weeks);
        int begining = startDate.getDate() + 1;
        int counter = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining) {
                counter++;
            }
        }
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        int newIndex = 0;
        for (int i = this.getDate().length - counter; i < this.getDate().length; i++) {
            tickArray [newIndex] = this.getTicker()[i];
            tfArray[newIndex] = this.getTimeframe()[i];
            dateArray[newIndex] = this.getDate()[i];
            timeArray[newIndex] = this.getTime()[i];
            openArray[newIndex] = this.getOpen()[i];
            highArray[newIndex] = this.getHigh()[i];
            lowArray[newIndex] = this.getLow()[i];
            closeArray[newIndex] = this.getClose()[i];
            volumeArray[newIndex] = this.getVolume()[i];
            newIndex++;
        }
        this.ticker = tickArray;
        this.timeframe = tfArray;
        this.date = dateArray;
        this.time = timeArray;
        this.open = openArray;
        this.high = highArray;
        this.low = lowArray;
        this.close = closeArray;
        this.volume = volumeArray;
    }

    /**
     * @param quWeeks    ���������� ������ ��� ������ ������ ���������.
     * @param weekNum    ����� ������� ������.
     * @param learnWeeks ���������� ������ ��� �������� ���������.
     * @return ������ ������ FinRes ���������� ������� ��������� ��� ������� �� ��
     * ������ ���������� ��������� .
     * �� ������������� ����� ������ ������ ��������� (this.startIndex)
     * TODO ����� �� ������������, ���� ������ ������������� �������������, �� �������.
     */
    public FinRes getLearnArr(int quWeeks, int weekNum, int learnWeeks) {
        FinRes learnArr = new FinRes();
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, quWeeks + learnWeeks - weekNum + 1);
        Dates finishDate = new Dates();
        finishDate.weeksMinus(lastDate, quWeeks - weekNum + 1);
        int begining = startDate.getDate() + 1;
        int finish = finishDate.getDate() + 1;
        int counter = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining && this.getDate()[i] < finish) {
                counter++;
            }
        }
        //System.out.println(counter);
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        int newIndex = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining && this.getDate()[i] < finish) {
                tickArray[newIndex] = this.getTicker()[i];
                tfArray[newIndex] = this.getTimeframe()[i];
                dateArray[newIndex] = this.getDate()[i];
                timeArray[newIndex] = this.getTime()[i];
                openArray[newIndex] = this.getOpen()[i];
                highArray[newIndex] = this.getHigh()[i];
                lowArray[newIndex] = this.getLow()[i];
                closeArray[newIndex] = this.getClose()[i];
                volumeArray[newIndex] = this.getVolume()[i];
                newIndex++;
            }
        }
        learnArr.setTicker(tickArray);
        learnArr.setTimeframe(tfArray);
        learnArr.setDate(dateArray);
        learnArr.setTime(timeArray);
        learnArr.setOpen(openArray);
        learnArr.setHigh(highArray);
        learnArr.setLow(lowArray);
        learnArr.setClose(closeArray);
        learnArr.setVolume(volumeArray);
        //System.out.println(dateArray[0]);
        return learnArr;
    }

    /**
     * @param quWeeks    ���������� ������ ��� ������ ������ ���������.
     * @param weekNum    ����� ������� ������.
     * @param learnWeeks ���������� ������ ��� �������� ���������.
     * @return ������ ������ FinRes ���������� ������� ��������� ��� ������� �� ��
     * ������ ���������� ��������� .
     * ����� ������������� ����� ������ ������ ��������� � ���������� ������� (this.startIndex) ����� �����������
     * ���������� ������� ��� ���������� ������ �����������.
     */
    public FinRes getLearnExt(int quWeeks, int weekNum, int learnWeeks) {
        FinRes learnArr = new FinRes();
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, quWeeks + learnWeeks - weekNum + 1);
        Dates preStartDate = new Dates();
        preStartDate.daysMinus(lastDate, (quWeeks + learnWeeks - weekNum + 1) * 7 + 4);
        Dates finishDate = new Dates();
        finishDate.weeksMinus(lastDate, quWeeks - weekNum + 1);
        int preBegining = preStartDate.getDate();
        int begining = startDate.getDate() + 1;
        int finish = finishDate.getDate() + 1;
        int counter = 0;

        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= preBegining && this.getDate()[i] < finish) {
                counter++;
            }
        }
        //System.out.println(counter);
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        boolean indicator = true;
        int newIndex = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= preBegining && this.getDate()[i] < finish) {
                tickArray[newIndex] = this.getTicker()[i];
                tfArray[newIndex] = this.getTimeframe()[i];
                dateArray[newIndex] = this.getDate()[i];
                timeArray[newIndex] = this.getTime()[i];
                openArray[newIndex] = this.getOpen()[i];
                highArray[newIndex] = this.getHigh()[i];
                lowArray[newIndex] = this.getLow()[i];
                closeArray[newIndex] = this.getClose()[i];
                volumeArray[newIndex] = this.getVolume()[i];
                if (this.getDate()[i] >= begining && indicator) {
                    learnArr.startIndex = newIndex;
                    indicator = false;
                }
                newIndex++;

            }
        }
        learnArr.setTicker(tickArray);
        learnArr.setTimeframe(tfArray);
        learnArr.setDate(dateArray);
        learnArr.setTime(timeArray);
        learnArr.setOpen(openArray);
        learnArr.setHigh(highArray);
        learnArr.setLow(lowArray);
        learnArr.setClose(closeArray);
        learnArr.setVolume(volumeArray);
        //System.out.println(dateArray[0]);
        return learnArr;
    }

    /**
     * @param quWeeks   ���������� ������ ��� ������ ������ ���������.
     * @param weekNum   ����� ������� ������.
     * @param testWeeks ���������� ������ ��� �����.
     * @return ������ ������ FinRes ���������� ������� ��������� ��� ������������ �� ��
     * ���������� ��������� ���������� � ���������� �������� �� ���������� �������.
     * �� ������������� ����� ������ ������ ��������� (this.startIndex)
     */
    public FinRes getTestArr(int quWeeks, int weekNum, int testWeeks) {
        FinRes testArr = new FinRes();
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, quWeeks - weekNum + 1);
        Dates finishDate = new Dates();
        finishDate.weeksMinus(lastDate, quWeeks - weekNum - testWeeks + 1);
        int begining = startDate.getDate() + 1;
        int finish = finishDate.getDate() + 1;
        int counter = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining && this.getDate()[i] < finish) {
                counter++;
            }
        }
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        int newIndex = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= begining && this.getDate()[i] < finish) {
                tickArray[newIndex] = this.getTicker()[i];
                tfArray[newIndex] = this.getTimeframe()[i];
                dateArray[newIndex] = this.getDate()[i];
                timeArray[newIndex] = this.getTime()[i];
                openArray[newIndex] = this.getOpen()[i];
                highArray[newIndex] = this.getHigh()[i];
                lowArray[newIndex] = this.getLow()[i];
                closeArray[newIndex] = this.getClose()[i];
                volumeArray[newIndex] = this.getVolume()[i];
                newIndex++;
            }
        }
        testArr.setTicker(tickArray);
        testArr.setTimeframe(tfArray);
        testArr.setDate(dateArray);
        testArr.setTime(timeArray);
        testArr.setOpen(openArray);
        testArr.setHigh(highArray);
        testArr.setLow(lowArray);
        testArr.setClose(closeArray);
        testArr.setVolume(volumeArray);

        return testArr;
    }

    /**
     * @param quWeeks   ���������� ������ ��� ������ ������ ���������.
     * @param weekNum   ����� ������� ������.
     * @param testWeeks ���������� ������ ��� �����.
     * @return ������ ������ FinRes ���������� ������� ��������� ��� ������������ �� ��
     * ���������� ��������� ���������� � ���������� �������� �� ���������� �������.
     * ����� ������������� ����� ������ ������ ��������� (this.startIndex) ����� ����������� ���������� �������
     * ��� ���������� ������ �����������.
     */
    public FinRes getTestExt(int quWeeks, int weekNum, int testWeeks) {
        FinRes testArr = new FinRes();
        int lastDate = this.getDate()[getDate().length - 1];
        Dates startDate = new Dates();
        startDate.weeksMinus(lastDate, quWeeks - weekNum + 1);
        Dates preStartDate = new Dates();
        preStartDate.daysMinus(lastDate, (quWeeks + testWeeks - weekNum + 1) * 7 + 4);
        Dates finishDate = new Dates();
        finishDate.weeksMinus(lastDate, quWeeks - weekNum - testWeeks + 1);
        int preBegining = preStartDate.getDate();
        int begining = startDate.getDate() + 1;
        int finish = finishDate.getDate() + 1;
        int counter = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= preBegining && this.getDate()[i] < finish) {
                counter++;
            }
        }
        String[] tickArray = new String[counter];
        int[] tfArray = new int[counter];
        int[] dateArray = new int[counter];
        String[] timeArray = new String[counter];
        double[] openArray = new double[counter];
        double[] highArray = new double[counter];
        double[] lowArray = new double[counter];
        double[] closeArray = new double[counter];
        int[] volumeArray = new int[counter];

        boolean indicator = true;
        int newIndex = 0;
        for (int i = 0; i < this.getDate().length; i++) {
            if (this.getDate()[i] >= preBegining && this.getDate()[i] < finish) {
                tickArray[newIndex] = this.getTicker()[i];
                tfArray[newIndex] = this.getTimeframe()[i];
                dateArray[newIndex] = this.getDate()[i];
                timeArray[newIndex] = this.getTime()[i];
                openArray[newIndex] = this.getOpen()[i];
                highArray[newIndex] = this.getHigh()[i];
                lowArray[newIndex] = this.getLow()[i];
                closeArray[newIndex] = this.getClose()[i];
                volumeArray[newIndex] = this.getVolume()[i];
                if (this.getDate()[i] >= begining && indicator) {
                    testArr.startIndex = newIndex;
                    indicator = false;
                }
                newIndex++;
            }
        }
        testArr.setTicker(tickArray);
        testArr.setTimeframe(tfArray);
        testArr.setDate(dateArray);
        testArr.setTime(timeArray);
        testArr.setOpen(openArray);
        testArr.setHigh(highArray);
        testArr.setLow(lowArray);
        testArr.setClose(closeArray);
        testArr.setVolume(volumeArray);

        return testArr;
    }

    /**
     * ������� ��������� ������ ��������� ��������� � ����.
     * @param pathToFile ���� � �����, � ������� ��������� ��������� ������ ��������� ���������,
     *                   ������ ��������� ��� �������� ������������ ������ ���������.
     * @throws IOException ����������
     */
    public void robToFile(String pathToFile) throws IOException {
        StringBuilder finalInfo = new StringBuilder(new String(""));
        String oneString = new String("");
        FileWriter writer = new FileWriter(pathToFile);
        StringBuilder parHeader = new StringBuilder(); // ������ ���������� �������� ��������� �������;
        for (StringBuilder paramName : this.paramNames) {
            parHeader.append(";").append(paramName);
        }
        finalInfo = new StringBuilder(this.robHeader + parHeader + "\n");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("########.##", symbols);

        for (int i = this.startIndex; i < this.close.length; i++) {
            oneString = ticker[i] + ";" + timeframe[i] + ";" + df.format(date[i]) + ";" +
                    time[i] + ";" + df.format(open[i]) + ";" + df.format(high[i]) + ";" + df.format(low[i]) + ";" +
                    df.format(close[i]) + ";" + volume[i] + ";" + pos[i] + ";" + quan[i] +
                    ";" + df.format(price[i]) + ";" + df.format(stopLoss[i]) + ";" + signal[i] + ";" +
                    idicToSring(i) + "\n";
            finalInfo.append(oneString);
        }
        writer.write(finalInfo.toString());
        writer.close();
    }

    public void dealsToFile(String pathToFile) throws IOException {
        StringBuilder finalInfo = new StringBuilder(new String("Ticker;Date;Time;Signal;Price;Lots\n"));
        String oneString = new String("");
        FileWriter writer = new FileWriter(pathToFile);
        StringBuilder parHeader = new StringBuilder(); // ������ ���������� �������� ��������� �������;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("########.##", symbols);
        for (int i = this.startIndex; i < this.close.length; i++) {
            if (signal[i] != null && signal[i] != "0") {
                oneString = this.ticker[i] + ";" + this.date[i] + ";" + this.time[i] + ";" + this.signal[i] +
                        ";" + df.format(this.price[i]) + ";" + this.quan[i] + "\n";
                finalInfo.append(oneString);
            }
        }
        writer.write(finalInfo.toString());
        writer.close();
    }

    /**
     * �������� � ���� ������ �������� ����������� �� ������� �������� �������� �������� �� �������� ������� (�����).
     *
     * @param index ���������� ����� ����� ���������.
     * @return ������, ������� �������� ���������, �� ������� �������� ��������.
     */
    private String idicToSring(int index) {
        String score = "";
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("########.##", symbols);

        if (this.ind1 != null) {
            score += df.format(ind1[index]);
        }
        if (this.ind2 != null) {
            score += ";" + df.format(ind2[index]);
        }
        if (this.ind3 != null) {
            score += ";" + df.format(ind3[index]);
        }
        if (this.ind4 != null) {
            score += ";" + df.format(ind4[index]);
        }
        if (this.ind5 != null) {
            score += ";" + df.format(ind5[index]);
        }
        if (this.ind6 != null) {
            score += ";" + df.format(ind6[index]);
        }
        if (this.ind7 != null) {
            score += ";" + df.format(ind7[index]);
        }
        if (this.ind8 != null) {
            score += ";" + df.format(ind8[index]);
        }
        if (this.ind9 != null) {
            score += ";" + df.format(ind9[index]);
        }
        if (this.ind10 != null) {
            score += ";" + df.format(ind10[index]);
        }
        return score;
    }

    public String getComment() {
        return this.comment;
    }

    /**
     * ���������� ��� � �����, ����� ����� ������������� ��������� �������.
     * @param i ������ �������� ���������.
     * @return ���� true �� ����� ������� �������, false - �� ���������.
     */
    protected boolean isToClosePos(int i) {
        if (Settings.TIME_LIMITS) {
            int date = this.date[i];
            int time = Integer.parseInt(this.time[i]);
            int dayOfWeek = Dates.dayOfWeek(date);
            int[] preHolidays = Settings.PRE_HOLIDAYS;
            int[] preJointDays = Settings.PRE_JOINT_DAYS;
            if ((this.ticker[i].contains("BR") || this.ticker[i].contains("NG"))) {
                preJointDays = Settings.PRE_JOINT_BR_DAYS; // ������ ��� ����� �������� ��������� �� ������� BR � NG
            }
            boolean isPreHolidays = false;
            boolean isPreJointDay = false;
            for (int day : preHolidays) {
                if (day == date) {
                    isPreHolidays = true;
                    break;
                }
            }
            for (int day : preJointDays) {
                if (day == date) {
                    isPreJointDay = true;
                    break;
                }
            }
            boolean isClosePosition = false;
            if ((dayOfWeek == 5) && (time >= Settings.FINISH_TIME_FRIDAY)) {
                isClosePosition = true;
            } else if ((isPreHolidays || isPreJointDay) && (time >= Settings.FINISH_TIME_FRIDAY)) {
                isClosePosition = true;
            }
            return isClosePosition;
        } else {
            return false;
        }



    }

    /**
     * ����������� ��� � �����, ����� ������ ��������� �������.
     * @param i ������ �������� ���������.
     * @return ���� false �� ������ ��������� �������, true - �����.
     */
    protected boolean isToNotOpenPos(int i) {
        if (Settings.TIME_LIMITS) {
            int date = this.date[i];
            int time = Integer.parseInt(this.time[i]);
            int dayOfWeek = Dates.dayOfWeek(date);
            int[] preHolidays = Settings.PRE_HOLIDAYS; // ������ ��� ����� �����������
            int[] preJointDays = Settings.PRE_JOINT_DAYS; // ������ ��� ����� �������� ���������
            int[] afterJointDays = Settings.AFTER_JOINT_DAYS; // ������ �������� ���� ������ ����� ����� ������� ��������
            if ((this.ticker[i].contains("BR") || this.ticker[i].contains("NG"))) {
                preJointDays = Settings.PRE_JOINT_BR_DAYS; // ������ ��� ����� �������� ��������� �� ������� BR � NG
                afterJointDays = Settings.AFTER_JOINT_BR_DAYS; // ������ �������� ���� ������ ����� ����� ������� ��������� BR � NG.
            }
            int[] afterHolidaysDays = Settings.AFTER_HOLIDAYS_DAYS; // ������ �������� ���� ������ ����� ����� ����������.
            boolean isPreHolidays = false;
            boolean isPreJointDay = false;
//        boolean isPreJointBrDay = false;
            boolean isAfterJointDay = false;
//        boolean isAfterJointBrDay = false;
            boolean isAfterHolidaystDay = false;
            for (int day : preHolidays) {
                if (day == date) {
                    isPreHolidays = true;
                    break;
                }
            }
            for (int day : preJointDays) {
                if (day == date) {
                    isPreJointDay = true;
                    break;
                }
            }
            for (int day : afterJointDays) {
                if (day == date) {
                    isAfterJointDay = true;
                    break;
                }
            }
            for (int day : afterHolidaysDays) {
                if (day == date) {
                    isAfterHolidaystDay = true;
                    break;
                }
            }
            boolean isToNotOpenPos = false;
            if (time <= Settings.START_EVERYDAY) {
                isToNotOpenPos = true;
            } else if ((dayOfWeek == 5) && (time >= Settings.FINISH_TIME_FRIDAY)) {
                isToNotOpenPos = true;
            } else if ((isPreHolidays || isPreJointDay) && (time >= Settings.FINISH_TIME_FRIDAY)) {
                isToNotOpenPos = true;
            } else if (isAfterJointDay && (time <= Settings.AFTER_JOINT_START)) {
                isToNotOpenPos = true;
            } else if (isAfterHolidaystDay && (time <= Settings.START_TIME_MONDAY)) {
                isToNotOpenPos = true;
            } else if ((dayOfWeek == 1) && (time <= Settings.START_TIME_MONDAY)) {
                isToNotOpenPos = true;
            }
            return !isToNotOpenPos;

        }
        return true;
    }

    /**
     * ���������� �����, ����� ������ ��������� �������
     * @param i ������ �������� ���������.
     * @return ���� false �� ������ ��������� �������, true - �����.
     */
    protected boolean isNotToClosePos(int i) {
        if (Settings.TIME_LIMITS) {
            int time = Integer.parseInt(this.time[i]);
            return time > Settings.START_EVERYDAY;
        }
        return true;
    }

    // �  ����� ����� ���������� ������ �������� ����������.

    /**
     * �������� �������� ���������� �� ������ � ����������� ��� ��� ��� ������������� ������� ��������,
     * � �������������� ����-������ � ����-��������. ����������� ���������� �� 1 ����� �� ��������.
     *
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ������� ������� ������ ��������
     *              2) ������ ������ ������� ������ ��������
     *              3) ������ ����-����� � ��������� �� ���� ��������
     *              4) �������������� ��� ������� ���� ����-������� �� ���� ����-�����
     */
    public void rob_donch_zakrep_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "MaxPeriod" - ������ ������� ������� ������ ��������.
        2 - "MinPeriod" - ������ ������ ������� ������ ��������.
        3 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        4 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.

        StringBuilder parName1 = new StringBuilder("MaxPeriod");
        StringBuilder parName2 = new StringBuilder("MinPeriod");
        StringBuilder parName3 = new StringBuilder("StopLoss");
        StringBuilder parName4 = new StringBuilder("Multiplier");

        double[][] steps = new double[][] {{10, 10, 0.3, 0.5}};
        double[] parRange1 = new double[] {10, 150, steps[z][0]};
	    double[] parRange2 = new double[] {10, 150, steps[z][1]};
	    double[] parRange3 = new double[] {0.3, 1.5, steps[z][2]};
	    double[] parRange4 = new double[] {1.5, 3.5, steps[z][3]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parRange4, parName1, parName2, parName3, parName4);
         */
        this.comment = "�������� ���������� �� ������ � ����������� �������������� ������ ��������";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4};
        this.ind1 = Indicators.max(this.high, (int) param.par1); // ������� ������� ������ ��������
        this.ind2 = Indicators.min(this.low, (int) param.par2); // ������ ������� ������ ��������
        double stopLim = param.par3; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par4; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double trStopLossShift = 0.5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trailStopStart = 0.6; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.close[i - 1] > this.ind1[i - 2]) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.close[i - 1] < this.ind2[i - 2]) && this.ind2[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind2[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind2[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.signal = signal;
    }

    /**
     * �������� �������� ���������� �� �������� ������� � ���� ��� ���� �� ����� �����, ���� ������ ����� �� ��
     * ����� ��������� �������: ��� ����� - ���� ������ - ���� � ��������. ��� ������� �������������� �������.
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ����-����� � ��������� �� ���� ��������
     *              2) �������������� ��� ������� ���� ����-������� �� ���� ����-�����
     */
    public void robVoronySoldatyStopTake_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        2 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.

        StringBuilder parName1 = new StringBuilder("StopLoss");
        StringBuilder parName2 = new StringBuilder("Multiplier");

        double[][] steps = new double[][] {{0.3, 0.5}};
	    double[] parRange1 = new double[] {0.3, 1.5, steps[z][0]};
	    double[] parRange2 = new double[] {1.5, 3.5, steps[z][1]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parName1, parName2);
         */
        this.comment = "�������� ���������� �� ��������� ������-�������, ���� � ����, ���� ��� ����� ����� � " +
                "���� ������, ���� � ����, ���� ��� ������ ����� � ���� �����. ����-������ � ����-���� �����������.";
        this.parameters = new double[]{param.par1, param.par2};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2};
        double stopLim = param.par1; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par2; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double trStopLossShift = 0.5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trailStopStart = 0.6; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        for (int i = startIndex; i < this.close.length; i++) {
            boolean le = (this.close[i - 4] > this.open[i - 4]) &&
                    (this.close[i - 3] > this.open[i - 3]) &&
                    (this.close[i - 2] > this.open[i - 2]) &&
                    (this.close[i - 1] < this.open[i - 1]);// ������ ����� � ����
            boolean se = (this.close[i - 4] < this.open[i - 4]) &&
                    (this.close[i - 3] < this.open[i - 3]) &&
                    (this.close[i - 2] < this.open[i - 2]) &&
                    (this.close[i - 1] > this.open[i - 1]);// ������ ����� � ����
            boolean lxt = this.open[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.open[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.open[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.open[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if (le && nop) {  // ������ �������� � ����
                    curPos = 1;
                    pos[i] = curPos;
                    quPos += quLots;
                    quan[i] = quPos;
                    stopLoss = this.open[i] * ((100 - stopLim) / 100);
                    takeProf = this.open[i] * ((100 + stopLim * mult) / 100);
                    opCl = this.open[i];
                    price[i] = opCl;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "le";
                } else if (se && nop) { // ������ �������� � ����
                    curPos = -1;
                    pos[i] = curPos;
                    quPos += quLots;
                    quan[i] = quPos;
                    stopLoss = this.open[i] * ((100 + stopLim) / 100);
                    takeProf = this.open[i] * ((100 - stopLim * mult) / 100);
                    opCl = this.open[i];
                    price[i] = opCl;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "se";
                } else { //  �� ���� ��������� �������
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == 1) { // ���� ���� �������� �������
                if (lxt && ncp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxt";
                } else if (lxs && ncp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxs";
                } else if (cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "cp";
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                if (sxt && ncp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxt";
                } else if (sxs && ncp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxs";
                } else if (cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "cp";
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind1 = new double[getClose().length];
        Arrays.fill(this.ind1, stopLim);
        this.ind2 = new double[getClose().length];
        Arrays.fill(this.ind2, mult);
        this.signal = signal;
    }

    /**
     * �������� �������� ���������� �� ������ � ����������� ��� ��� ��� ������������� ������� ��������,
     * � �����-����-������ � ����-��������. ����������� ���������� �� 1 ����� �� ��������.
     *
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ������� ������� ������ ��������
     *              2) ������ ������ ������� ������ ��������
     *              3) ������ ����-����� � ��������� �� ���� ��������
     *              4) �������������� ��� ������� ���� ����-������� �� ���� ����-�����
     *              5) % �� ������� ������ ������� �������, ����� ������������ �����-����.
     *              6) "���������" �� ������� ��������� �����-���� �� ������� ����.
     */
    public void robDonchZakrepAsimTrStop_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "MaxPeriod" - ������ ������� ������� ������ ��������.
        2 - "MinPeriod" - ������ ������ ������� ������ ��������.
        3 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        4 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.

        StringBuilder parName1 = new StringBuilder("MaxPeriod");
        StringBuilder parName2 = new StringBuilder("MinPeriod");
        StringBuilder parName3 = new StringBuilder("StopLoss");
        StringBuilder parName4 = new StringBuilder("Multiplier");

        double[][] steps = new double[][] {{10, 10, 0.3, 0.5}};
        double[] parRange1 = new double[] {10, 150, steps[z][0]};
	    double[] parRange2 = new double[] {10, 150, steps[z][1]};
	    double[] parRange3 = new double[] {0.3, 1.5, steps[z][2]};
	    double[] parRange4 = new double[] {1.5, 3.5, steps[z][3]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parRange4, parName1, parName2, parName3, parName4);
         */
        this.comment = "�������� ���������� �� ������ � ����������� �������������� ������ ��������";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4};
        this.ind1 = Indicators.max(this.high, (int) param.par1); // ������� ������� ������ ��������
        this.ind2 = Indicators.min(this.low, (int) param.par2); // ������ ������� ������ ��������
        double stopLim = param.par3; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par4; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double trStopLossShift = 0.5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trailStopStart = 0.6; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.close[i - 1] > this.ind1[i - 2]) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.close[i - 1] < this.ind2[i - 2]) && this.ind2[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind2[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind2[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP) {
                        activeTrailStopLong = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopLong) { // ���� �����-���� � ���� �������, ��...
                            if (this.high[i] * ((100 - trStopLossShift) / 100) > stopLoss ) {
                                stopLoss = this.high[i] * ((100 - trStopLossShift) / 100);
                            }
                        } else if (this.high[i] >= opCl * ((100 + trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopLong = true; // ���������� �����-���� � ����
                            stopLoss = this.high[i] * ((100 - trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP){
                        activeTrailStopShort = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopShort) { // ���� �����-���� � ���� �������, ��...
                            if (this.low[i] * ((100 + trStopLossShift) / 100) < stopLoss) {
                                stopLoss = this.low[i] * ((100 + trStopLossShift) / 100);
                            }
                        } else if (this.low[i] <= opCl * ((100 - trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopShort = true; // ���������� �����-���� � ����
                            stopLoss = this.low[i] * ((100 + trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.signal = signal;
    }

    /**
     * �������� �������� ���������� �� ������ � ����������� ��� ��� ��� ����������� ������� ����� ����������,
     * � �����-����-������ � ����-��������. ����������� ���������� �� 1 ����� �� ��������.
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ������� ������� ������ ��������
     *              2) ������ ������ ������� ������ ��������
     *              3) ������ ����-����� � ��������� �� ���� ��������
     *              4) �������������� ��� ������� ���� ����-������� �� ���� ����-�����
     *              5) "���������" �� ������� ��������� �����-���� �� ������� ����.
     *              -) ��������� �����-����� ���������� �� ��������� 3.
     */
    public void robBbandsZakpepSimTrStop_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "Period" - ������ ������� ����� ����������.
        2 - "StandDeviation" - ���������� ����������� ���������� � ��� �������, ����� ������������.
        3 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        4 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.
        5 - "trStopLossShift" - ������� �����-����� �� ������� ���� � ���������(�������� ������ �����).

        StringBuilder parName1 = new StringBuilder("Period");
        StringBuilder parName2 = new StringBuilder("StandDeviation");
        StringBuilder parName3 = new StringBuilder("StopLoss");
        StringBuilder parName4 = new StringBuilder("Multiplier");
        StringBuilder parName5 = new StringBuilder("trStopLossShift");

        double[][] steps = new double[][] {{10, 0.5, 0.3, 0.5, 0.3}};
        double[] parRange1 = new double[] {10, 150, steps[z][0]};
	    double[] parRange2 = new double[] {1, 2.5, steps[z][1]};
	    double[] parRange3 = new double[] {0.3, 1.5, steps[z][2]};
	    double[] parRange4 = new double[] {1.5, 3.5, steps[z][3]};
	    double[] parRange5 = new double[] {0.3, 0.9, steps[z][4]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parRange4, parRange5, parName1, parName2, parName3, parName4, parName5);
         */
        this.comment = "�������� ���������� �� ������ � ����������� ����������� ����� ���������� � ������������ ����� �����";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4, param.par5};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4, param.parName5};
        double[][] bBands = Indicators.bbands(this.close, (int) param.par1, param.par2, param.par2, MAType.Ema);
        this.ind1 = bBands[0]; // ������� ������ ����������
        this.ind2 = bBands[2]; // ������ ������ ����������
        double stopLim = param.par3; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par4; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trailStopStart = param.par3; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        double trStopLossShift = param.par5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.close[i - 1] > this.ind1[i - 2]) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.close[i - 1] < this.ind2[i - 2]) && this.ind2[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind2[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind2[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP) {
                        activeTrailStopLong = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopLong) { // ���� �����-���� � ���� �������, ��...
                            if (this.high[i] * ((100 - trStopLossShift) / 100) > stopLoss ) {
                                stopLoss = this.high[i] * ((100 - trStopLossShift) / 100);
                            }
                        } else if (this.high[i] >= opCl * ((100 + trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopLong = true; // ���������� �����-���� � ����
                            stopLoss = this.high[i] * ((100 - trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP){
                        activeTrailStopShort = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopShort) { // ���� �����-���� � ���� �������, ��...
                            if (this.low[i] * ((100 + trStopLossShift) / 100) < stopLoss) {
                                stopLoss = this.low[i] * ((100 + trStopLossShift) / 100);
                            }
                        } else if (this.low[i] <= opCl * ((100 - trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopShort = true; // ���������� �����-���� � ����
                            stopLoss = this.low[i] * ((100 + trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.signal = signal;

    }

    /**
     * ������� �������� ����������� �� ������ ���������� SAR � ������ �� ������� �� ������������� �����-����� � ����-�������.
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ��� (�����������) ����������.
     *              3) ������ ����-����� � ��������� �� ���� ��������.
     *              4) �������������� ��� ������� ���� ����-������� �� ���� ����-�����.
     */
    public void robParSarSimple_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "Acceleration" - ���������, ���.
        2 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        3 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.

        StringBuilder parName1 = new StringBuilder("Acceleration");
        StringBuilder parName2 = new StringBuilder("StopLoss");
        StringBuilder parName3 = new StringBuilder("Multiplier");

        double[][] steps = new double[][] {{0.001, 0.3, 0.5}};

        double[] parRange1 = new double[] {0.02, 0.03, steps[z][0]};
        double[] parRange2 = new double[] {0.3, 1.5, steps[z][1]};
	    double[] parRange3 = new double[] {1.5, 3.5, steps[z][2]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parName1, parName2, parName3,);
         */
        this.comment = "�������� ���������� �� ���������� SAR";
        this.parameters = new double[]{param.par1, param.par2, param.par3};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3};
        this.ind1 = Indicators.parabolicSAR(this.high, this.low, param.par1, 0.2); // SAR
        double stopLim = param.par2; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par3; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
//        double trailStopStart = param.par3; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
//        double trStopLossShift = param.par5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.ind1[i - 2] > this.getHigh()[i - 2]) && (this.ind1[i - 1] < this.getLow()[i - 1]) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.ind1[i - 2] < this.getLow()[i - 2]) && (this.ind1[i - 1] > this.getHigh()[i - 1]) && this.ind1[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind1[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind1[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
//                    if (Settings.TRAIL_STOP) {
//                        activeTrailStopLong = false;
//                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
//                    if (Settings.TRAIL_STOP) {
//                        if (activeTrailStopLong) { // ���� �����-���� � ���� �������, ��...
//                            if (this.high[i] * ((100 - trStopLossShift) / 100) > stopLoss ) {
//                                stopLoss = this.high[i] * ((100 - trStopLossShift) / 100);
//                            }
//                        } else if (this.high[i] >= opCl * ((100 + trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
//                            activeTrailStopLong = true; // ���������� �����-���� � ����
//                            stopLoss = this.high[i] * ((100 - trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
//                        }
//                    }
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
//                    if (Settings.TRAIL_STOP){
//                        activeTrailStopShort = false;
//                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
//                    if (Settings.TRAIL_STOP) {
//                        if (activeTrailStopShort) { // ���� �����-���� � ���� �������, ��...
//                            if (this.low[i] * ((100 + trStopLossShift) / 100) < stopLoss) {
//                                stopLoss = this.low[i] * ((100 + trStopLossShift) / 100);
//                            }
//                        } else if (this.low[i] <= opCl * ((100 - trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
//                            activeTrailStopShort = true; // ���������� �����-���� � ����
//                            stopLoss = this.low[i] * ((100 + trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
//                        }
//                    }
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind2 = new double[getClose().length];
        Arrays.fill(this.ind2, stopLim);
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, mult);
        this.signal = signal;
    }


    /**
     * ������� �������� ���������� �� �������� �������� ������� ���������� RSI . �������� ������ 30 � ������� ������ - ����,
     * �������� ������ 70 � ������� ������ - ����.
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ������� ���������� RSI
     *              2) ����-���� � ��������� �� ���� ��������
     *              3) �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.
     *              4) ������� �����-����� �� ������� ���� � ���������(�������� ������ �����).
     */
    public void robRsiTrStop_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "Period" - ������ ������� ���������� RSI.
        2 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        3 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.
        4 - "trStopLossShift" - ������� �����-����� �� ������� ���� � ���������(�������� ������ �����).

        StringBuilder parName1 = new StringBuilder("Period");
        StringBuilder parName2 = new StringBuilder("StopLoss");
        StringBuilder parName3 = new StringBuilder("Multiplier");
        StringBuilder parName4 = new StringBuilder("trStopLossShift");

        double[][] steps = new double[][] {{10, 0.3, 0.5, 0.3}};

        double[] parRange1 = new double[] {10, 150, steps[z][0]};
	    double[] parRange2 = new double[] {0.3, 1.5, steps[z][1]};
	    double[] parRange3 = new double[] {.5, 3.5, steps[z][2]};
	    double[] parRange4 = new double[] {0.3, 0.9, steps[z][3]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parRange4, parName1, parName2, parName3, parName4);
         */
        this.comment = "�������� ���������� �� ��������� ������� 30 � 70 �� RSI � ������� ������";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4};
        this.ind1 = Indicators.rsi(this.high, (int) param.par1); // ������� ������� ������ ��������
//        this.ind2 = Indicators.min(this.low, (int) param.par2); // ������ ������� ������ ��������
        double stopLim = param.par2; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par3; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trStopLossShift = param.par2; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        double trailStopStart = param.par4; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.ind1[i - 2] < 30 && this.ind1[i - 1] > 30) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.ind1[i - 2] > 70 && this.ind1[i - 1] < 70) && this.ind1[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind1[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind1[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP) {
                        activeTrailStopLong = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopLong) { // ���� �����-���� � ���� �������, ��...
                            if (this.high[i] * ((100 - trStopLossShift) / 100) > stopLoss ) {
                                stopLoss = this.high[i] * ((100 - trStopLossShift) / 100);
                            }
                        } else if (this.high[i] >= opCl * ((100 + trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopLong = true; // ���������� �����-���� � ����
                            stopLoss = this.high[i] * ((100 - trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP){
                        activeTrailStopShort = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopShort) { // ���� �����-���� � ���� �������, ��...
                            if (this.low[i] * ((100 + trStopLossShift) / 100) < stopLoss) {
                                stopLoss = this.low[i] * ((100 + trStopLossShift) / 100);
                            }
                        } else if (this.low[i] <= opCl * ((100 - trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopShort = true; // ���������� �����-���� � ����
                            stopLoss = this.low[i] * ((100 + trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.signal = signal;
    }

    /**
     * �������� ������������ �� ������ ������������� �������� ������ �� ��� � ��� �������, ����� ���� ����������� ��� �������
     * ����-����, ��� � ����� ����. ���� ��������� ��� �������������� �� ������� �����.
     * @param param ������ ������ Parameter, ���������� ��������� ��� ������ ������� ��������� ���������:
     *              1) ������ ������� ����������� EMA.
     *              2) ������ ������ �� ������ � ���� � ���������.
     *              3) ����-���� � ��������� �� ���� ��������.
     *              4) �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.
     *              5) ������� �����-����� �� ������� ���� � ���������(�������� ������ �����).
     */
    public void robPrChEmaSimTrStop_v_0_1(Parameter param) {
        /*
        ��������� ����������:
        1 - "Period" - ������ ������� ����������� EMA.
        2 - "Channel" - ������ ������ �� ������ � ���� � ���������.
        3 - "StopLoss" - ����-���� � ��������� �� ���� ��������.
        4 - "Multiplier" - �������������� ��� ������� ���� ����-������� �� ������� ����-����� � ���������.
        5 - "trStopLossShift" - ������� �����-����� �� ������� ���� � ���������(�������� ������ �����).

        StringBuilder parName1 = new StringBuilder("Period");
        StringBuilder parName2 = new StringBuilder("Channel");
        StringBuilder parName3 = new StringBuilder("StopLoss");
        StringBuilder parName4 = new StringBuilder("Multiplier");
        StringBuilder parName5 = new StringBuilder("trStopLossShift");

        double[][] steps = new double[][] {{10, 0.5, 0.3, 0.5, 0.3}};
        double[] parRange1 = new double[] {10, 200, steps[z][0]};
	    double[] parRange2 = new double[] {0.5, 2.5, steps[z][1]};
	    double[] parRange3 = new double[] {0.3, 1.5, steps[z][2]};
	    double[] parRange4 = new double[] {1.5, 3.5, steps[z][3]};
	    double[] parRange5 = new double[] {0.3, 0.9, steps[z][4]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parRange3, parRange4, parRange5, parName1, parName2, parName3, parName4, parName5);
         */
        this.comment = "�������� ���������� �� ������ ������������� �������� ������ � ����������� �� ���� ��������";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4, param.par5};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4, param.parName5};
        double[] ema = Indicators.ema(this.close, (int) param.par1);
        this.ind1 = Indicators.upperChnlBorder(ema, param.par2); // ������� ������� �������� ������.
        this.ind2 = Indicators.lowerChnlBorder(ema, param.par2); // ������ ������� �������� ������.
        double stopLim = param.par3; // ������� ����� � ��������� �� ���� ��������
        double mult = param.par4; // ��������� ������ ����� � � ������ ����� (���)
        int curPos = 0;  // ������� ��� ��������� �������, ������������ � [9] 0 ��� 1
        int quPos = 0;  // ���������� ����� � �������� �������, ������ ������������� ����� �����
        int iInp = 0;  // ������ ������ ����� � �������
        int quLots = 1;  // ������������� ���������� �����, ������� ������� � ����� ������
        double stopLoss = -1; // ������� ����-�����
        double takeProf = -1; // ������� ����-�������
        double opCl = 0;  // ��������� �������� / �������� �������
        boolean activeTrailStopLong = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        boolean activeTrailStopShort = false; //  ���������� ������� - ������� �� ����� ��� ����� ���� False - ���, True - ��
        double trailStopStart = param.par3; // ������� �� ������� ������ ������� �������, ����� ������������ �����-����
        double trStopLossShift = param.par5; // ������� �����-����� �� ������� ���� � ���������(�������� ������ �����)
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.close[i - 1] > this.ind1[i - 2]) && this.ind1[i - 2] != 0.0;// ������ ����� � ����
            boolean se = (this.close[i - 1] < this.ind2[i - 2]) && this.ind2[i - 2] != 0.0; // ������ ����� � ����
            boolean lxt = this.high[i] >= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean lxs = this.low[i] <= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean sxt = this.low[i] <= takeProf; // ������ ������ �� ����� �� ����-�������
            boolean sxs = this.high[i] >= stopLoss; // ������ ������ �� ����� �� ����-����� (�� �� �����)
            boolean cp = isToClosePos(i); // ���� true, �� ��������� ������� �������������.
            boolean nop = isToNotOpenPos(i); // ���� false, �� ������ ��������� �������.
            boolean ncp = isNotToClosePos(i); // ���� false, �� ������ ��������� �������.
            if (curPos == 0 && quPos == 0) {  // ���� ��� �������
                if ((le && this.ind1[i - 1] != 0.0 && nop) || (se && this.ind2[i - 1] != 0.0  && nop)) {
                    quPos += quLots;
                    opCl = this.open[i];
                    price[i] = opCl;
                    if (le && this.ind1[i - 1] != 0.0) {  // ������ �������� � ����
                        curPos = 1;
                        stopLoss = opCl * ((100 - stopLim) / 100);
                        takeProf = opCl * ((100 + stopLim * mult) / 100);
                        signal[i] = "le";
                    } else if (se && this.ind2[i - 1] != 0.0) {
                        curPos = -1;
                        stopLoss = opCl * ((100 + stopLim) / 100);
                        takeProf = opCl * ((100 - stopLim * mult) / 100);
                        signal[i] = "se";
                    }
                } else {
                    price[i] = 0;
                    signal[i] = "0";
                }
                pos[i] = curPos;
                quan[i] = quPos;
                stopLossArr[i] = stopLoss;

            } else if (curPos == 1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((lxt && ncp) || (lxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if (lxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "lxt";
                    } else if (lxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "lxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP) {
                        activeTrailStopLong = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopLong) { // ���� �����-���� � ���� �������, ��...
                            if (this.high[i] * ((100 - trStopLossShift) / 100) > stopLoss ) {
                                stopLoss = this.high[i] * ((100 - trStopLossShift) / 100);
                            }
                        } else if (this.high[i] >= opCl * ((100 + trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopLong = true; // ���������� �����-���� � ����
                            stopLoss = this.high[i] * ((100 - trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            } else if (curPos == -1) { // ���� ���� �������� �������
                stopLossArr[i] = stopLoss;
                if ((sxt && ncp) || (sxs && ncp) || cp) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    if(sxt && ncp) {
                        price[i] = takeProf;
                        signal[i] = "sxt";
                    } else if (sxs && ncp) {
                        price[i] = stopLoss;
                        signal[i] = "sxs";
                    } else if (cp) {
                        price[i] = this.open[i];
                        signal[i] = "cp";
                    }
                    stopLoss = -1;
                    takeProf = -1;
                    if (Settings.TRAIL_STOP){
                        activeTrailStopShort = false;
                    }

                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    signal[i] = "0";
                    if (Settings.TRAIL_STOP) {
                        if (activeTrailStopShort) { // ���� �����-���� � ���� �������, ��...
                            if (this.low[i] * ((100 + trStopLossShift) / 100) < stopLoss) {
                                stopLoss = this.low[i] * ((100 + trStopLossShift) / 100);
                            }
                        } else if (this.low[i] <= opCl * ((100 - trailStopStart) / 100)) { // ����� ���� �����-���� � ���� �� �������
                            activeTrailStopShort = true; // ���������� �����-���� � ����
                            stopLoss = this.low[i] * ((100 + trStopLossShift) / 100); // ��������� ����-���� �� ����� �������
                        }
                    }
                }
            }
        }
        this.pos = pos;
        this.quan = quan;
        this.price = price;
        this.stopLoss = stopLossArr;
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.ind5 = new double[getClose().length];
        Arrays.fill(this.ind5, trStopLossShift);

        this.signal = signal;


    }


}









