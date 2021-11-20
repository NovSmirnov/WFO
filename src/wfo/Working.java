package wfo;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

/**
 * Класс добавляет к родителю поля и методы, необходимые для работы торгового алгоритма, а так же сами торговые
 * алгоритмы.
 */
public class Working extends QuoteColArr {
    /**
     * Массив со значениями наличия или отсуствия позици в лонг или шорт.
     */
    protected int[] pos;
    /**
     * Массив со значениями количества лотов актива.
     */
    protected int[] quan;
    /**
     * Массив со значениями цены открытия или закрытия позиции
     */
    protected double[] price;
    /**
     * Массив со значениями цены стоплоса
     */
    protected double[] stopLoss;
    /**
     * Массив со значениями сигнала, по которому было открытие/закрытие позиции
     */
    protected String[] signal;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind1;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind2;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind3;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind4;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind5;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind6;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind7;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind8;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind9;
    /**
     * Массив со значениями индикатора теханализа для торговли
     */
    protected double[] ind10;
    /**
     * Массив с параметрами робота
     */
    protected double[] parameters;
    /**
     * Массив с названиями параметров робота
     */
    protected StringBuilder[] paramNames;
    /**
     * Часть заголовка при выведении результатов работы в файл.
     */
    protected String robHeader = "<TICKER>;<PER>;<DATE>;<TIME>;<OPEN>;<HIGH>;<LOW>;<CLOSE>;<VOL>;POS;QUAN;PRICE;StLoss; Signal";
    /**
     * Номер индекса, с которого начинает работу алгоритм и расчитывается результат работы
     */
    protected int startIndex;
    /**
     * Содержит описание работы торгового алгоритма.
     */
    protected String comment;


    // TODO создать перегруженный метод singleRunArr принимающий две даты, старта и финиша и создающий участок котировки между ними.

    /**
     * Создаёт новый объект класса FinRes для одиночного тестирования множества прогонов или вывода значений работы.
     * торгового алгоритма.
     * @param quWeeks количество недель для создаия отрезка котировки от последней даты в файле.
     * @return Новый объект класса FinRes для одиночного тестирования множества прогонов или вывода значений работы.
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
     * @param quWeeks Количество недель для полной работы алгоритма.
     *                Подрезает количество элементов полей родительсткого класса, в которых находятся массивы со значениями котировки до
     *                необходимого количества в соотвествии с количеством недель для работы алгоритма.
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
     * @param quWeeks    Количество недель для полной работы алгоритма.
     * @param weekNum    Номер текущей недели.
     * @param learnWeeks Количество нелель для обучения алгоритма.
     * @return Объект класса FinRes содержащий отрезок котировки для подбора на нём
     * лучших параметров алгоритма .
     * Не устанавливает точку старта работы алгоритма (this.startIndex)
     * TODO метод не используется, если отпадёт теоритическая необходимость, то удалить.
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
     * @param quWeeks    Количество недель для полной работы алгоритма.
     * @param weekNum    Номер текущей недели.
     * @param learnWeeks Количество нелель для обучения алгоритма.
     * @return Объект класса FinRes содержащий отрезок котировки для подбора на нём
     * лучших параметров алгоритма .
     * Также устанавливает точку старта работы алгоритма в полученном объекте (this.startIndex) после прохождения
     * разгонного участка для корректной работы индикаторов.
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
     * @param quWeeks   Количество недель для полной работы алгоритма.
     * @param weekNum   Номер текущей недели.
     * @param testWeeks Количество нелель для теста.
     * @return Объект класса FinRes содержащий отрезок котировки для тестирования на нём
     * параметров алгоритма полученных в результате обучения на предыдущем отрезке.
     * Не устанавливает точку старта работы алгоритма (this.startIndex)
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
     * @param quWeeks   Количество недель для полной работы алгоритма.
     * @param weekNum   Номер текущей недели.
     * @param testWeeks Количество нелель для теста.
     * @return Объект класса FinRes содержащий отрезок котировки для тестирования на нём
     * параметров алгоритма полученных в результате обучения на предыдущем отрезке.
     * Также устанавливает точку старта работы алгоритма (this.startIndex) после прохождения разгонного участка
     * для корректной работы индикаторов.
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
     * Выводит результат работы торгового алгоритма в файл.
     * @param pathToFile Путь к файлу, в который выводится результат работы торгового алгоритма,
     *                   обычно необходим для проверки правильности работы алгоритма.
     * @throws IOException Исключение
     */
    public void robToFile(String pathToFile) throws IOException {
        StringBuilder finalInfo = new StringBuilder(new String(""));
        String oneString = new String("");
        FileWriter writer = new FileWriter(pathToFile);
        StringBuilder parHeader = new StringBuilder(); // Строка содержащая названия параметра роботов;
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
        StringBuilder parHeader = new StringBuilder(); // Строка содержащая названия параметра роботов;
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
     * Собирает в одну строку значения индикаторов по которым работает торговый алгоритм по значению индекса (свече).
     *
     * @param index порядковый номер свечи котировки.
     * @return Строку, которая содержит параметры, по которым работает алгоритм.
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

    // С  ЭТОГО МЕСТА НАЧИНАЮТСЯ МЕТОДЫ ТОРГОВЫХ АЛГОРИТМОВ.

    /**
     * Торговый алгоритм основанный на пробое и закреплении над или под ассиметричным каналом Дончиана,
     * с фиксированными стоп-лоссом и тейк-профитом. Закрепление происходит на 1 свече по закрытию.
     *
     * @param param Объект класса Parameter, содержащий параметры для работы данного торгового алгоритма:
     *              1) Период верхней границы канала Дончиана
     *              2) Период нижней границы канала Дончиана
     *              3) Размер стол-лосса в процентах от цены открытия
     *              4) Мультипликатор для расчёта цены тейк-профита от цены стоп-лосса
     */
    public void rob_donch_zakrep_v_0_1(Parameter param) {
        /*
        Назавания параметров:
        1 - "MaxPeriod" - Период верхней границы канала Дончиана.
        2 - "MinPeriod" - Период нижней границы канала Дончиана.
        3 - "StopLoss" - Стоп-лосс в процентах от цены открытия.
        4 - "Multiplier" - Мультипликатор для расчёта цены тейк-профита от размера стоп-лосса в процентах.

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
        this.comment = "Алгоритм основанный на пробое и закреплении ассиметричного канала Дончиана";
        this.parameters = new double[]{param.par1, param.par2, param.par3, param.par4};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2, param.parName3, param.parName4};
        this.ind1 = Indicators.max(this.high, (int) param.par1); // Верхняя граница канала Дончиана
        this.ind2 = Indicators.min(this.low, (int) param.par2); // Нижняя граница канала Дончиана
        double stopLim = param.par3; // уровень стопа в процентах от цены открытия
        double mult = param.par4; // отношение уровня тэйка к с уровню стопа (раз)
        int curPos = 0;  // наличие или отсутсвие позиции, записывается в [9] 0 или 1
        int quPos = 0;  // количество лотов в открытой позиции, только положительное целое число
        int iInp = 0;  // индекс строки входа в позицию
        int quLots = 1;  // фиксированное количество лотов, которым торгуем в одной сделке
        double stopLoss = -1; // Уровень стоп-лосса
        double takeProf = -1; // Уровень тейк-профита
        double trStopLossShift = 0.5; // Уровень трейл-стопа от текущей цены в процентах(пересчёт каждую свечу)
        double opCl = 0;  // котировка открытия / закрытия позиции
        boolean activeTrailStopLong = false; //  переменная условие - активен ли трэйл для лонга стоп False - нет, True - да
        boolean activeTrailStopShort = false; //  переменная условие - активен ли трэйл для шорта стоп False - нет, True - да
        double trailStopStart = 0.6; // процент на который должна вырасти позиция, чтобы активировать трейл-стоп
        for (int i = startIndex + 2; i < this.close.length; i++) {
            boolean le = (this.close[i - 1] > this.ind1[i - 2]) && this.ind1[i - 2] != 0.0;// сигнал входа в лонг
            boolean se = (this.close[i - 1] < this.ind2[i - 2]) && this.ind2[i - 2] != 0.0; // сигнал входа в шорт
            boolean lxt = this.open[i] >= takeProf; // сигнал выхода из лонга по тейк-профиту
            boolean lxs = this.open[i] <= stopLoss; // сигнал выхода из лонга по стоп-лоссу (то же самое)
            boolean sxt = this.open[i] <= takeProf; // сигнал выхода из шорта по тейк-профиту
            boolean sxs = this.open[i] >= stopLoss; // сигнал выхода из шорта по стоп-лоссу (то же самое)
            if (curPos == 0 && quPos == 0) {  // если нет позиции
                if (le && this.ind1[i - 1] != 0.0) {  // сигнал открытия в лонг
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
                } else if (se && this.ind2[i - 1] != 0.0) { // сигнал открытия в шорт
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
                } else { //  во всех остальных случаях
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == 1) { // если есть лонговая позиция
                if (lxt) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxt";
                } else if (lxs) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxs";
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == -1) { // если есть шортовая позиция
                if (sxt) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxt";
                } else if (sxs) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxs";
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
        this.ind3 = new double[getClose().length];
        Arrays.fill(this.ind3, stopLim);
        this.ind4 = new double[getClose().length];
        Arrays.fill(this.ind4, mult);
        this.signal = signal;
    }

    /**
     * Торговый алгоритм основанный на открытии позиции в лонг или шорт на пятой свече, если четыре свечи до неё
     * имели следующий паттерн: три белых - одна чёрная - лонг и наоборот. Нет никаких дополнительных условий.
     * @param param Объект класса Parameter, содержащий параметры для работы данного торгового алгоритма:
     *              1) Размер стол-лосса в процентах от цены открытия
     *              2) Мультипликатор для расчёта цены тейк-профита от цены стоп-лосса
     */
    public void robVoronySoldatyStopTake_v_0_1(Parameter param) {
        /*
        Назавания параметров:
        1 - "StopLoss" - Стоп-лосс в процентах от цены открытия.
        2 - "Multiplier" - Мультипликатор для расчёта цены тейк-профита от размера стоп-лосса в процентах.

        StringBuilder parName1 = new StringBuilder("StopLoss");
        StringBuilder parName2 = new StringBuilder("Multiplier");

        double[][] steps = new double[][] {{0.3, 0.5}};
	    double[] parRange1 = new double[] {0.3, 1.5, steps[z][0]};
	    double[] parRange2 = new double[] {1.5, 3.5, steps[z][1]};
	    ParRange param = new ParRange();
	    param.setParRange(parRange1, parRange2, parName1, parName2);
         */
        this.comment = "Алгоритм основанный на стратегии Вороны-Солдаты, вход в лонг, если три белых свечи и " +
                "одна чёрная, вход в шорт, если три чёрных свечи и одна белая. Тейк-профит и стоп-лосс фиксированы.";
        this.parameters = new double[]{param.par1, param.par2};
        int[] pos = new int[this.getClose().length];
        int[] quan = new int[this.getClose().length];
        double[] price = new double[this.getClose().length];
        double[] stopLossArr = new double[this.getClose().length];
        String[] signal = new String[this.getClose().length];
        this.paramNames = new StringBuilder[]{param.parName1, param.parName2};
        double stopLim = param.par1; // уровень стопа в процентах от цены открытия
        double mult = param.par2; // отношение уровня тэйка к с уровню стопа (раз)
        int curPos = 0;  // наличие или отсутсвие позиции, записывается в [9] 0 или 1
        int quPos = 0;  // количество лотов в открытой позиции, только положительное целое число
        int iInp = 0;  // индекс строки входа в позицию
        int quLots = 1;  // фиксированное количество лотов, которым торгуем в одной сделке
        double stopLoss = -1; // Уровень стоп-лосса
        double takeProf = -1; // Уровень тейк-профита
        double trStopLossShift = 0.5; // Уровень трейл-стопа от текущей цены в процентах(пересчёт каждую свечу)
        double opCl = 0;  // котировка открытия / закрытия позиции
        boolean activeTrailStopLong = false; //  переменная условие - активен ли трэйл для лонга стоп False - нет, True - да
        boolean activeTrailStopShort = false; //  переменная условие - активен ли трэйл для шорта стоп False - нет, True - да
        double trailStopStart = 0.6; // процент на который должна вырасти позиция, чтобы активировать трейл-стоп
        for (int i = startIndex; i < this.close.length; i++) {
            boolean le = (this.close[i - 4] > this.open[i - 4]) &&
                    (this.close[i - 3] > this.open[i - 3]) &&
                    (this.close[i - 2] > this.open[i - 2]) &&
                    (this.close[i - 1] < this.open[i - 1]);// сигнал входа в лонг
            boolean se = (this.close[i - 4] < this.open[i - 4]) &&
                    (this.close[i - 3] < this.open[i - 3]) &&
                    (this.close[i - 2] < this.open[i - 2]) &&
                    (this.close[i - 1] > this.open[i - 1]);// сигнал входа в шорт
            boolean lxt = this.open[i] >= takeProf; // сигнал выхода из лонга по тейк-профиту
            boolean lxs = this.open[i] <= stopLoss; // сигнал выхода из лонга по стоп-лоссу (то же самое)
            boolean sxt = this.open[i] <= takeProf; // сигнал выхода из шорта по тейк-профиту
            boolean sxs = this.open[i] >= stopLoss; // сигнал выхода из шорта по стоп-лоссу (то же самое)
            if (curPos == 0 && quPos == 0) {  // если нет позиции
                if (le) {  // сигнал открытия в лонг
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
                } else if (se) { // сигнал открытия в шорт
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
                } else { //  во всех остальных случаях
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == 1) { // если есть лонговая позиция
                if (lxt) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxt";
                } else if (lxs) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "lxs";
                } else {
                    pos[i] = curPos;
                    quan[i] = quPos;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "0";
                }
            } else if (curPos == -1) { // если есть шортовая позиция
                if (sxt) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = takeProf;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxt";
                } else if (sxs) {
                    curPos = 0;
                    pos[i] = curPos;
                    quPos -= quLots;
                    quan[i] = quPos;
                    price[i] = stopLoss;
                    stopLoss = -1;
                    takeProf = -1;
                    stopLossArr[i] = stopLoss;
                    signal[i] = "sxs";
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



}









