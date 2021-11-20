package wfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * Класс содержащий котировку какого-либо инструмента (актива) и методы работы с ней.
 */
public class QuoteColArr {
    /**
     * Массив с тикерами котировки.
     */
    protected String[] ticker;
    /**
     * Массив с таймфреймом котировки.
     */
    protected int[] timeframe;
    /**
     * Массив с датами котировки
     */
    protected int[] date;
    /**
     * Массив со временем котировки
     */
    protected String[] time;
    /**
     * Массив ценами открытия котировки.
     */
    protected double[] open;
    /**
     * Массив с наибольшими значениями цены свеч котировки.
     */
    protected double[] high;
    /**
     * Массив с наименьшими значениями цены свеч котировки.
     */
    protected double[] low;
    /**
     * Массив с ценами закрытия свеч котировки.
     */
    protected double[] close;
    /**
     * Массив с объёмами свеч котировки
     */
    protected int[] volume;
    /**
     * Заголовок файла котировки для вывода
     */
    protected String quoteHeader = "<TICKER>,<PER>,<DATE>,<TIME>,<OPEN>,<HIGH>,<LOW>,<CLOSE>,<VOL>";


    /**
     * Заполняет объект этого или дочерних классов сведениями о котировке определенного инструмента из текстового файла.
     *
     * @param filePath Путь к текстовому файлу с котировкой.
     * @throws IOException Искючение
     */
    public void setQuote(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Scanner scanner = null;
        String lineQuote = null;
        int index = 0;
        List<String> quote = new ArrayList<>();
        while ((lineQuote = reader.readLine()) != null) {
            quote.add(lineQuote);
        }
        int lenQuote = quote.size() - 1;
        reader.close();

        String[] tickArray = new String[lenQuote];
        int[] tfArray = new int[lenQuote];
        int[] dateArray = new int[lenQuote];
        String[] timeArray = new String[lenQuote];
        double[] openArray = new double[lenQuote];
        double[] highArray = new double[lenQuote];
        double[] lowArray = new double[lenQuote];
        double[] closeArray = new double[lenQuote];
        int[] volumeArray = new int[lenQuote];

        for (int i = 1; i <= lenQuote; i++) {
            scanner = new Scanner(quote.get(i));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 0)
                    tickArray[i - 1] = data;
                else if (index == 1)
                    tfArray[i - 1] = (Integer.parseInt(data));
                else if (index == 2)
                    dateArray[i - 1] = (Integer.parseInt(data));
                else if (index == 3)
                    timeArray[i - 1] = data;
                else if (index == 4)
                    openArray[i - 1] = (Double.parseDouble(data));
                else if (index == 5)
                    highArray[i - 1] = (Double.parseDouble(data));
                else if (index == 6)
                    lowArray[i - 1] = (Double.parseDouble(data));
                else if (index == 7)
                    closeArray[i - 1] = (Double.parseDouble(data));
                else if (index == 8)
                    volumeArray[i - 1] = (Integer.parseInt(data));
                else
                    System.out.println("Некорректные данные::" + data);
                index++;
            }
            index = 0;
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

    public String[] getTicker() {
        return ticker;
    }

    public int[] getTimeframe() {
        return timeframe;
    }

    public int[] getDate() {
        return date;
    }

    public String[] getTime() {
        return time;
    }

    public double[] getOpen() {
        return open;
    }

    public double[] getHigh() {
        return high;
    }

    public double[] getLow() {
        return low;
    }

    public double[] getClose() {
        return close;
    }

    public int[] getVolume() {
        return volume;
    }

    public void setTicker(String[] ticker) {
        this.ticker = ticker;
    }

    public void setTimeframe(int[] timeframe) {
        this.timeframe = timeframe;
    }

    public void setDate(int[] date) {
        this.date = date;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public void setOpen(double[] open) {
        this.open = open;
    }

    public void setHigh(double[] high) {
        this.high = high;
    }

    public void setLow(double[] low) {
        this.low = low;
    }

    public void setClose(double[] close) {
        this.close = close;
    }

    public void setVolume(int[] volume) {
        this.volume = volume;
    }

    /**
     * Выводит всю котироку на консоль построчно
     */
    public void quoteOut() {
        int len = ticker.length;
        for (int i = 0; i < len; i++) {
            System.out.print("\n" + ticker[i] + ";" + timeframe[i] + ";" + date[i] + ";" +
                    time[i] + ";" + open[i] + ";" + high[i] + ";" + low[i] + ";" +
                    close[i] + ";" + volume[i]);
        }
    }

    /**
     * Выводит конкретную строку котировки на консоль по индексу
     *
     * @param str Индекс строки (свечи)
     */
    public void stringOut(int str) {
        System.out.print("\n" + ticker[str] + ";" + timeframe[str] + ";" + date[str] + ";" +
                time[str] + ";" + open[str] + ";" + high[str] + ";" + low[str] + ";" +
                close[str] + ";" + volume[str]);
    }

    /**
     * Выводит котироку в файл с разделителями в виде ";"
     *
     * @param pathToFile Путь к файлу
     * @throws IOException Исключение
     */
    public void quoteToFile(String pathToFile) throws IOException {
        StringBuilder finalInfo = new StringBuilder(new String(""));
        String oneString = new String("");
        FileWriter writer = new FileWriter(pathToFile);
        finalInfo = new StringBuilder(this.quoteHeader + "\n");
        for (int i = 0; i < this.close.length; i++) {
            oneString = ticker[i] + ";" + timeframe[i] + ";" + date[i] + ";" +
                    time[i] + ";" + open[i] + ";" + high[i] + ";" + low[i] + ";" +
                    close[i] + ";" + volume[i] + "\n";
            finalInfo.append(oneString);
        }
        writer.write(finalInfo.toString());
        writer.close();
    }

    /**
     * Выводит котироку в файл с разделителями в виде ","
     *
     * @param pathToFile Путь к файлу
     * @throws IOException Исключение
     */
    public void quoteToFileTxt(String pathToFile) throws IOException {
        StringBuilder finalInfo = new StringBuilder(new String(""));
        String oneString = new String("");
        FileWriter writer = new FileWriter(pathToFile);
        finalInfo = new StringBuilder(this.quoteHeader + "\n");
        for (int i = 0; i < this.close.length; i++) {
            oneString = ticker[i] + "," + timeframe[i] + "," + date[i] + "," +
                    time[i] + "," + open[i] + "," + high[i] + "," + low[i] + "," +
                    close[i] + "," + volume[i] + "\n";
            finalInfo.append(oneString);
        }
        writer.write(finalInfo.toString());
        writer.close();
    }

    //TODO доделать метод, исключить ввод непредусмотренных значений таймфрейма, пока могут введены некорректные данные.

    /**
     * Конвертирует котировку из таймфейма 1 минута в указанный в качестве параметра таймфрейм.
     *
     * @param timeFrame Значение целевого таймфрейма в целых числах
     * @return Возвращает новый объект настоящего класса с целевым таймфреймом
     */
    public QuoteColArr compressorOut(int timeFrame) {
        QuoteColArr finQuote = new QuoteColArr();
        SortedSet<Integer> datesList = new TreeSet<>();
        for (int i = 0; i < this.getDate().length; i++) {
            datesList.add(this.getDate()[i]);
        }
        Integer[] dates = datesList.toArray(new Integer[0]);

        String[] times = new String[1440];
        int countMinutes = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                if (i < 10) {
                    if (j < 10) {
                        String line = "0" + Integer.toString(i) + "0" + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    } else {
                        String line = "0" + Integer.toString(i) + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    }
                } else {
                    if (j < 10) {
                        String line = Integer.toString(i) + "0" + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    } else {
                        String line = Integer.toString(i) + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    }
                }
            }
        }
        List<String> ticker = new ArrayList();
        List<Integer> timeframe = new ArrayList();
        List<Integer> date = new ArrayList();
        List<String> time = new ArrayList();
        List<Double> open = new ArrayList();
        List<Double> high = new ArrayList();
        List<Double> low = new ArrayList();
        List<Double> close = new ArrayList();
        List<Integer> volume = new ArrayList();

        int candleCounter = 0;
        for (int i : dates) {
            for (int j = 0; j < 1440; j = j + timeFrame) {
                int vol = 0;
                List<Double> highs = new ArrayList<>();
                List<Double> lows = new ArrayList<>();
                for (int s = 0; s < timeFrame; s++) {
                    if (i == this.getDate()[candleCounter] && times[j + s].equals(this.getTime()[candleCounter])) {
                        ticker.add(this.getTicker()[candleCounter]);
                        timeframe.add(timeFrame);
                        date.add(i);
                        time.add(times[j]);
                        open.add(this.getOpen()[candleCounter]);
                        if (s < timeFrame - 1) {
                            for (int k = s; k < timeFrame; k++) {
                                if (i == this.getDate()[candleCounter] && times[j + k].equals(this.getTime()[candleCounter])) {
                                    vol += this.getVolume()[candleCounter];
                                    highs.add(this.getHigh()[candleCounter]);
                                    lows.add((this.getLow()[candleCounter]));
                                    if (candleCounter < this.getOpen().length - 1) {
                                        candleCounter++;
                                    }
                                }
                            }
                            volume.add(vol);
                            high.add(Collections.max(highs));
                            low.add(Collections.min(lows));
                            close.add(this.getClose()[candleCounter - 1]);
                        } else {
                            high.add(this.getHigh()[candleCounter]);
                            low.add(this.getLow()[candleCounter]);
                            close.add(this.getClose()[candleCounter]);
                            volume.add(getVolume()[candleCounter]);
                            candleCounter++;
                        }
                        break;
                    }
                }
            }
        }
//		System.out.println(ticker.size());
//		System.out.println(timeframe.size());
//		System.out.println(date.size());
//		System.out.println(time.size());
//		System.out.println(open.size());
//		System.out.println(high.size());
//		System.out.println(low.size());
//		System.out.println(close.size());
//		System.out.println(volume.size());

        String[] tickerArr = new String[open.size()];
        int[] timeframeArr = new int[open.size()];
        int[] dateArr = new int[open.size()];
        String[] timeArr = new String[open.size()];
        double[] openArr = new double[open.size()];
        double[] highArr = new double[open.size()];
        double[] lowArr = new double[open.size()];
        double[] closeArr = new double[open.size()];
        int[] volumeArr = new int[open.size()];

        for (int i = 0; i < open.size(); i++) {
            tickerArr[i] = ticker.get(i);
            timeframeArr[i] = timeframe.get(i);
            dateArr[i] = date.get(i);
            timeArr[i] = time.get(i);
            openArr[i] = open.get(i);
            highArr[i] = high.get(i);
            lowArr[i] = low.get(i);
            closeArr[i] = close.get(i);
            volumeArr[i] = volume.get(i);
        }
        finQuote.setTicker(tickerArr);
        finQuote.setTimeframe(timeframeArr);
        finQuote.setDate(dateArr);
        finQuote.setTime(timeArr);
        finQuote.setOpen(openArr);
        finQuote.setHigh(highArr);
        finQuote.setLow(lowArr);
        finQuote.setClose(closeArr);
        finQuote.setVolume(volumeArr);

        return finQuote;
    }

    //TODO доделать метод, исключить ввод непредусмотренных значений таймфрейма, пока могут введены некорректные данные.

    /**
     * Конвертирует котировку из таймфейма 1 минута в указанный в качестве параметра таймфрейм
     * и перезаписывает поля данного объекта данного класса.
     *
     * @param timeFrame Значение целевого таймфрейма в целых числах
     */
    public void compressorIn(int timeFrame) {
        QuoteColArr finQuote = new QuoteColArr();
        SortedSet<Integer> datesList = new TreeSet<>();
        for (int i = 0; i < this.getDate().length; i++) {
            datesList.add(this.getDate()[i]);
        }
        Integer[] dates = datesList.toArray(new Integer[0]);

        String[] times = new String[1440];
        int countMinutes = 0;
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 60; j++) {
                if (i < 10) {
                    if (j < 10) {
                        String line = "0" + Integer.toString(i) + "0" + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    } else {
                        String line = "0" + Integer.toString(i) + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    }
                } else {
                    if (j < 10) {
                        String line = Integer.toString(i) + "0" + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    } else {
                        String line = Integer.toString(i) + Integer.toString(j) + "00";
                        times[countMinutes] = line;
                        countMinutes++;
                    }
                }
            }
        }
        List<String> ticker = new ArrayList();
        List<Integer> timeframe = new ArrayList();
        List<Integer> date = new ArrayList();
        List<String> time = new ArrayList();
        List<Double> open = new ArrayList();
        List<Double> high = new ArrayList();
        List<Double> low = new ArrayList();
        List<Double> close = new ArrayList();
        List<Integer> volume = new ArrayList();

        int candleCounter = 0;
        for (int i : dates) {
            for (int j = 0; j < 1440; j = j + timeFrame) {
                int vol = 0;
                List<Double> highs = new ArrayList<>();
                List<Double> lows = new ArrayList<>();
                for (int s = 0; s < timeFrame; s++) {
                    if (i == this.getDate()[candleCounter] && times[j + s].equals(this.getTime()[candleCounter])) {
                        ticker.add(this.getTicker()[candleCounter]);
                        timeframe.add(timeFrame);
                        date.add(i);
                        time.add(times[j]);
                        open.add(this.getOpen()[candleCounter]);
                        if (s < timeFrame - 1) {
                            for (int k = s; k < timeFrame; k++) {
                                if (i == this.getDate()[candleCounter] && times[j + k].equals(this.getTime()[candleCounter])) {
                                    vol += this.getVolume()[candleCounter];
                                    highs.add(this.getHigh()[candleCounter]);
                                    lows.add((this.getLow()[candleCounter]));
                                    if (candleCounter < this.getOpen().length - 1) {
                                        candleCounter++;
                                    }
                                }
                            }
                            volume.add(vol);
                            high.add(Collections.max(highs));
                            low.add(Collections.min(lows));
                            close.add(this.getClose()[candleCounter - 1]);
                        } else {
                            high.add(this.getHigh()[candleCounter]);
                            low.add(this.getLow()[candleCounter]);
                            close.add(this.getClose()[candleCounter]);
                            volume.add(getVolume()[candleCounter]);
                            candleCounter++;
                        }
                        break;
                    }
                }
            }
        }
        String[] tickerArr = new String[open.size()];
        int[] timeframeArr = new int[open.size()];
        int[] dateArr = new int[open.size()];
        String[] timeArr = new String[open.size()];
        double[] openArr = new double[open.size()];
        double[] highArr = new double[open.size()];
        double[] lowArr = new double[open.size()];
        double[] closeArr = new double[open.size()];
        int[] volumeArr = new int[open.size()];

        for (int i = 0; i < open.size(); i++) {
            tickerArr[i] = ticker.get(i);
            timeframeArr[i] = timeframe.get(i);
            dateArr[i] = date.get(i);
            timeArr[i] = time.get(i);
            openArr[i] = open.get(i);
            highArr[i] = high.get(i);
            lowArr[i] = low.get(i);
            closeArr[i] = close.get(i);
            volumeArr[i] = volume.get(i);
        }
        this.setTicker(tickerArr);
        this.setTimeframe(timeframeArr);
        this.setDate(dateArr);
        this.setTime(timeArr);
        this.setOpen(openArr);
        this.setHigh(highArr);
        this.setLow(lowArr);
        this.setClose(closeArr);
        this.setVolume(volumeArr);
    }
}
