package wfo;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;

/**
 * ����� �������� ������ ��� ������� ����������� ������������ ������� � ������� ���������� talib.
 */
public class Indicators {
    /**
     * ����������� ���������� �������� � ������� ���� double � ������������ ��������.
     *
     * @param closePrices        ������ � ������ �������� ���������.
     * @param OPT_IN_TIME_PERIOD ���������� ������ �� �������� ������������� ��������� ��������.
     * @return ������ � ���������� �����������.
     */
    public static double[] max(double[] closePrices, int OPT_IN_TIME_PERIOD) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.max(startIdx, endIdx, closePrices, OPT_IN_TIME_PERIOD, outBegIdx, outNbElement, outReal);

        double[] newArr = new double[outReal.length];
        for (int i = 0; i < outReal.length; i++) {
            if (i < OPT_IN_TIME_PERIOD - 1) {
                newArr[i] = 0;
            } else {
                newArr[i] = outReal[i - OPT_IN_TIME_PERIOD + 1];
            }
        }
        return newArr;
    }

    /**
     * ����������� ���������� ������� � ������� ���� double � ������������ ��������.
     *
     * @param closePrices        ������ � ������ �������� ���������.
     * @param OPT_IN_TIME_PERIOD ���������� ������ �� �������� ������������� ��������� �������.
     * @return ������ � ���������� ����������.
     */
    public static double[] min(double[] closePrices, int OPT_IN_TIME_PERIOD) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.min(startIdx, endIdx, closePrices, OPT_IN_TIME_PERIOD, outBegIdx, outNbElement, outReal);

        double[] newArr = new double[outReal.length];
        for (int i = 0; i < outReal.length; i++) {
            if (i < OPT_IN_TIME_PERIOD - 1) {
                newArr[i] = 0;
            } else {
                newArr[i] = outReal[i - OPT_IN_TIME_PERIOD + 1];
            }
        }
        return newArr;
    }

    /**
     * ����������� ���������� ���������� RSI � ������������ �������� � ������� ���� double.
     *
     * @param closePrices        ������ � ������ �������� ���������.
     * @param OPT_IN_TIME_PERIOD ���������� ������ �� �������� ������������� ��������� RSI.
     * @return ������ �o ���������� ���������� RSI.
     */
    public static double[] rsi(double[] closePrices, int OPT_IN_TIME_PERIOD) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.rsi(startIdx, endIdx, closePrices, OPT_IN_TIME_PERIOD, outBegIdx, outNbElement, outReal);

        int index = outReal.length - 1;
        double[] newArr = new double[index + 1];
        for (double z : outReal) {
            newArr[index] = z;
            index--;
        }
        return newArr;
    }

    /**
     * ������ ����������� ���������� ADX
     * @param highPrices ������ � ����������� ������
     * @param lowPrices ������ � ���������� ������
     * @param closePrices ������ � ������ �������� ������
     * @param period ������ ������� ����������
     * @return ������ � ������������ ���������� ����������
     */
    public static double[] adx(double[] highPrices, double[] lowPrices, double[] closePrices, int period) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.adx(startIdx, endIdx, highPrices, lowPrices, closePrices, period, outBegIdx, outNbElement, outReal);
        return normalizer(outBegIdx, outNbElement, outReal);
    }

    /**
     * ������ ����������� ���������� EMA.
     * @param closePrices ������ � ������ �������� ������ (�� ����������� ��������, ������).
     * @param period ������ ������� ����������.
     * @return ������ � ������������ ���������� ����������.
     */
    public static double[] ema(double[] closePrices, int period) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.ema(startIdx, endIdx, closePrices, period, outBegIdx, outNbElement, outReal);
        return normalizer(outBegIdx, outNbElement, outReal);
    }

    /**
     * ������ ������� ���������� ������� SMA.
     * @param closePrices ������ � ������ �������� ������ (�� ����������� ��������, ������).
     * @param period ������ ������� ����������.
     * @return ������ � ������������ ���������� ����������.
     */
    public static double[] sma(double[] closePrices, int period) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.sma(startIdx, endIdx, closePrices, period, outBegIdx, outNbElement, outReal);
        return normalizer(outBegIdx, outNbElement, outReal);
    }

    /**
     * ������ ����� ����������
     * @param closePrices ����� � ������ �������� ������ (�� ����������� ��������, ������).
     * @param period ������ ������� ����������.
     * @param stDevUp ����� ������� ���������� ��� ������� ������� ������.
     * @param stDevDn ����� ������� ���������� ��� ������ ������� ������.
     * @param maType ��� ���������� �������.
     * @return ��������� ������ �� ��� ���������� �������� [0] - ������� ������� ������, [1] - ����������� ���������� �������, [2] - ������� ������ ������.
     */
    public static double[][] bbands(double[] closePrices, int period, double stDevUp, double stDevDn, MAType maType) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outRealUpperBand = new double[closePrices.length];
        double[] outRealMiddleBand = new double[closePrices.length];
        double[] outRealLowerBand = new double[closePrices.length];
        int startIdx = 0;
        int endIdx = closePrices.length - 1;
        Core core = new Core();
        core.bbands(startIdx, endIdx, closePrices, period, stDevUp, stDevDn, maType, outBegIdx, outNbElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
        double[][] bbandsArr = new double[3][outRealUpperBand.length];
        bbandsArr[0] = normalizer(outBegIdx, outNbElement, outRealUpperBand);
        bbandsArr[1] = normalizer(outBegIdx, outNbElement, outRealMiddleBand);
        bbandsArr[2] = normalizer(outBegIdx, outNbElement, outRealLowerBand);
        return bbandsArr;
    }

    /**
     * ��������� SAR ��� ���������� �� ����������.
     * @param highPrices ��������� ������.
     * @param lowPrices �������� ������.
     * @param accel ��������� ����������
     * @param max ������������ �������� ����������
     * @return ���������� ������ �� ���������� ���������� SAR.
     */
    public static double[] parabolicSAR(double[] highPrices, double[] lowPrices, double accel, double max) {
        MInteger outBegIdx = new MInteger();
        MInteger outNbElement = new MInteger();
        double[] outReal = new double[highPrices.length];
        int startIdx = 0;
        int endIdx = highPrices.length - 1;
        Core core = new Core();
        core.sar(startIdx, endIdx, highPrices, lowPrices, accel, max, outBegIdx, outNbElement, outReal);
//        return outReal;
        return normalizer(outBegIdx, outNbElement, outReal);

    }

    /**
     * ����� ��� �������� ������� �������� ������� outReal �� ����� � ������.
     * @param outBegIdx ����� �����, ������� ����� ��������� �� ����� � ������ (��������� ������� �������).
     * @param outNbElement ���������� ����������� �������� ���������� � �������.
     * @param outReal �������� ������ ������������ ������� �� ���������� TA lib.
     * @return �������� ������ � ������ ������������� �� ����� � ������.
     */
    private static double[] normalizer(MInteger outBegIdx, MInteger outNbElement, double[] outReal) {
        double[] newArr = new double[outReal.length];
        for (int i = 0; i < outBegIdx.value; i++) {
            newArr[i] = outReal[outNbElement.value + i];
        }
        for (int i = outBegIdx.value; i < outReal.length; i++) {
            newArr[i] = outReal[i - outBegIdx.value];
        }
        return newArr;
    }
}
