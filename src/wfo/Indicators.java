package wfo;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MInteger;

/**
 *  ласс содержит методы дл€ расчЄта индикаторов технического анализа с помощью библиотеки talib.
 */
public class Indicators {
    /**
     * –асчитывает скольз€щий максимум в массиве типа double с определенным периодом.
     *
     * @param closePrices        ћассив с ценами закрыти€ котировки.
     * @param OPT_IN_TIME_PERIOD —кольз€щий период по которому расчитываетс€ локальный максимум.
     * @return ћассив с локальными максимумами.
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
     * –асчитывает скольз€щий минимум в массиве типа double с определенным периодом.
     *
     * @param closePrices        ћассив с ценами закрыти€ котировки.
     * @param OPT_IN_TIME_PERIOD —кольз€щий период по которому расчитываетс€ локальный минимум.
     * @return ћассив с локальными минимумами.
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
     * –асчитывает скольз€щий показатель RSI с определенным периодом в массиве типа double.
     *
     * @param closePrices        ћассив с ценами закрыти€ котировки.
     * @param OPT_IN_TIME_PERIOD —кольз€щий период по которому расчитываетс€ индикатор RSI.
     * @return ћассив сo значени€ми индикатора RSI.
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
     * –асчЄт скольз€щего индикатора ADX
     * @param highPrices ћассив с максимумами свечей
     * @param lowPrices ћассив с минимумами свечей
     * @param closePrices ћассив с ценами закрыти€ свечей
     * @param period ѕериод расчЄта показател€
     * @return ћассив с расчитанными значени€ми показател€
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
     * –асчЄт скольз€щего индикатора EMA.
     * @param closePrices ћассив с ценами закрыти€ свечей (не об€зательно закрыти€, любыми).
     * @param period ѕериод расчЄта показател€.
     * @return ћассив с расчитанными значени€ми показател€.
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
     * ћетод дл€ переноса нулевых значений массива outReal из конца в начало.
     * @param outBegIdx число нулей, которое нужно перенести из конца в начало (разгонный отрезок индекса).
     * @param outNbElement количество заполненных значений индикатора в массиве.
     * @param outReal итоговый массив возвращаемый методом из библиотеки TA lib.
     * @return »тоговый массив с нул€ми переведенными из конца в начало.
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
