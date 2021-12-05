package wfo;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;

/**
 * Класс содержит методы для расчёта индикаторов технического анализа с помощью библиотеки talib.
 */
public class Indicators {
    /**
     * Расчитывает скользящий максимум в массиве типа double с определенным периодом.
     *
     * @param closePrices        Массив с ценами закрытия котировки.
     * @param OPT_IN_TIME_PERIOD Скользящий период по которому расчитывается локальный максимум.
     * @return Массив с локальными максимумами.
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
     * Расчитывает скользящий минимум в массиве типа double с определенным периодом.
     *
     * @param closePrices        Массив с ценами закрытия котировки.
     * @param OPT_IN_TIME_PERIOD Скользящий период по которому расчитывается локальный минимум.
     * @return Массив с локальными минимумами.
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
     * Расчитывает скользящий показатель RSI с определенным периодом в массиве типа double.
     *
     * @param closePrices        Массив с ценами закрытия котировки.
     * @param OPT_IN_TIME_PERIOD Скользящий период по которому расчитывается индикатор RSI.
     * @return Массив сo значениями индикатора RSI.
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
     * Расчёт скользящего индикатора ADX
     * @param highPrices Массив с максимумами свечей
     * @param lowPrices Массив с минимумами свечей
     * @param closePrices Массив с ценами закрытия свечей
     * @param period Период расчёта показателя
     * @return Массив с расчитанными значениями показателя
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
     * Расчёт скользящего индикатора EMA.
     * @param closePrices Массив с ценами закрытия свечей (не обязательно закрытия, любыми).
     * @param period Период расчёта показателя.
     * @return Массив с расчитанными значениями показателя.
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
     * Расчёт простой скользящей средней SMA.
     * @param closePrices Массив с ценами закрытия свечей (не обязательно закрытия, любыми).
     * @param period Период расчёта показателя.
     * @return Массив с расчитанными значениями показателя.
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
     * Расчёт полос Болинджера
     * @param closePrices ассив с ценами закрытия свечей (не обязательно закрытия, любыми).
     * @param period Период расчёта показателя.
     * @param stDevUp Число средних отклонений для верхней границы полосы.
     * @param stDevDn Число средних отклонений для нижней границы полосы.
     * @param maType Тип скользящей средней.
     * @return Двумерный массив из трёх одномерных массивов [0] - граница верхней полосы, [1] - центральная скользящая средняя, [2] - граница нижней полосы.
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
     * Индикатор SAR для торголовли по параболику.
     * @param highPrices Максимумы свечей.
     * @param lowPrices Минимумы свечей.
     * @param accel Ускорение индикатора
     * @param max Максимальное значение индикатора
     * @return Одномерный массив со значениями индикатора SAR.
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
     * Метод для переноса нулевых значений массива outReal из конца в начало.
     * @param outBegIdx число нулей, которое нужно перенести из конца в начало (разгонный отрезок индекса).
     * @param outNbElement количество заполненных значений индикатора в массиве.
     * @param outReal итоговый массив возвращаемый методом из библиотеки TA lib.
     * @return Итоговый массив с нулями переведенными из конца в начало.
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
