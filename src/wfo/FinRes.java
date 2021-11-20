package wfo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

/**
 * Класс предназначен для рассчёта финансового результата работы торгового алгоритма.
 */
public class FinRes extends Working {
    /**
     * Шапка таблицы для вывода значений финансового результата
     */
    protected String[] finResHeader = new String[]{"Чистый П/У", "Макс. просадка", "Макс.выброс доходности",
            "Кол-во сделок", "Кол-во выиграных сделок", "Кол-во проиграных сделок", "% выиграных сделок",
            "Общая прибыль", "Общий убыток", "Средняя прибыль на прибыльную сделку",
            "Средний убыток на убыточную сделку", "Профит фактор", "Фактор восстановления", "Коэф. выигрыша",
            "Кол-во сделок для фильтра", "Накопленная комиссия", "Результат по лонговым сделкам",
            "Результат по шортовым сделкам"};

    /**
     * Чистые прибыль/убыток (П/У).
     */
    protected double genRes;
    /**
     * Максимальная просадка счёта.
     */
    protected double maxLoss;
    /**
     * Макс.выброс доходности.
     */
    protected double maxProf;
    /**
     * Кол-во сделок.
     */
    protected int quDe;
    /**
     * Кол-во выиграных сделок
     */
    protected int quProfDe;
    /**
     * Кол-во проиграных сделок
     */
    protected int quLossDe;
    /**
     * % выиграных сделок
     */
    protected double procWinDe;
    /**
     * Общая прибыль
     */
    protected double genProf;
    /**
     * Общий убыток
     */
    protected double genLoss;
    /**
     * Средняя прибыль на одну прибыльную сделку
     */
    protected double avProf;
    /**
     * Средний убыток на одну убыточную сделку
     */
    protected double avLoss;
    /**
     * Профит фактор
     */
    protected double profFac;
    /**
     * Фактор восстановления
     */
    protected double recovFac;
    /**
     * Коэф. выигрыша
     */
    protected double winKoo;
    /**
     * Кол-во сделок для фильтра
     */
    protected int quDealFilter;
    /**
     * Накопленная комиссия
     */
    protected double comGen;
    /**
     * Накопленный результат по закрытым лонговым позициям
     */
    protected double curResLo;
    /**
     * Накопленный результат по закрытым шортовым позициям
     */
    protected double curResSh;
    /**
     * Комиссия за сделку в единицах котировки, в ней же учитывается и проскальзывание
     */
    protected double com;

    /**
     * Расчитывает финансовый результат работы торгового алгоритма и заполняет поля объекта класса.
     */
    public void counter() {
        double curAvPos = 0.0;  // Переменная ведущая учёт прибыльности или убыточности текущей открытой позиции
        double avPrice = 0.0;  // Средняя цена открытия
        double curRes = 0.0;  // Уже зафиксированный результат на данный момент
        double genRes = 0.0;  // Переменная учитывающая зафиксированный результат и результат текущей позиции
        int quDe = 0;  // Общее количество сделок
        int quLossDe = 0;  // Количество убытоных сделок
        int quProfDe = 0;  // Количество прибыльных сделок
        int quDealFilter = 0;  // Количество сделок для фильтрации
        double maxLoss = 0.0;  // Максимальная просадка счёта
        double maxProf = 0.0;  // Максимальное состояние счёта
        double genProf = 0.0;  // Общая прибыль (учитываются только прибыльные сделки)
        double genLoss = 0.0;  // Общий убыток (учитываются только убыточные сделки)
        double avProf = 0.0;  // Средняя прибыль на одну прибыльную сделку
        double avLoss = 0.0;  // Средний убыток на одну убыточную сделку
        double profFac = 0.0;  // Профит-фактор
        double recovFac = 0.0; // Фактор восстановления
        double winKoo = 0.0;  // Коэфициент выигрыша
        double procWinDe = 0.0;  // Процент выигранных сделок
        double comGen = 0.0;  // накопленная комиссия
        double curResLo = 0.0;  // накопленный результат по закрытым лонговым позициям
        double curResSh = 0.0;  // накопленный результат по закрытым шортовым позициям

        int quanSum = Arrays.stream(this.quan).sum();
        if (quanSum != 0) {
            List<Double> generalResult = new ArrayList<>();
            for (int i = startIndex; i < this.close.length; i++) {
                if (this.pos[i - 1] == 0 && this.pos[i] == 1) { // Открытие длинной позиции
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (this.close[i] - avPrice) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == 0 && this.pos[i] == -1) { // Открытие длинной позиции
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (avPrice - this.close[i]) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == 1 && this.pos[i] == 0) { // Одномоментное закрытие длинной позиции
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i - 1];
                    curAvPos = (this.price[i] - avPrice) * this.quan[i - 1];
                    if (curAvPos > 0) {
                        quProfDe = quProfDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genProf = genProf + curAvPos;
                    } else {
                        quLossDe = quLossDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genLoss = genLoss + curAvPos;
                    }
                    curResLo = curResLo + curAvPos;
                    curRes = curRes + curAvPos;
                    curAvPos = 0.0;
                    genRes = curRes - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == -1 && this.pos[i] == 0) { // Одномоментное закрытие короткой позиции
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i - 1];
                    curAvPos = (avPrice - this.price[i]) * this.quan[i - 1];
                    if (curAvPos > 0) {
                        quProfDe = quProfDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genProf = genProf + curAvPos;
                    } else {
                        quLossDe = quLossDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genLoss = genLoss + curAvPos;
                    }
                    curResSh = curResSh + curAvPos;
                    curRes = curRes + curAvPos;
                    curAvPos = 0.0;
                    genRes = curRes - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if ((this.pos[i - 1] == 1 && this.pos[i] == 1) && (this.quan[i - 1] != this.quan[i])) { // частичное открытие/закрытие длинной позиции
                    if (this.quan[i - 1] < this.quan[i]) { // Частичный добор позиции в лонг
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i] - this.quan[i - 1]);
                        avPrice = (((avPrice * this.quan[i - 1]) + (this.quan[i] * (this.quan[i] - this.quan[i - 1]))) / this.quan[i]);
                        curAvPos = (this.close[i] - avPrice) * this.quan[i];
                        genRes = curRes + curAvPos - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    } else if (this.quan[i - 1] > this.quan[i]) { // частичное закрытие лонговой позиции
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i - 1] - this.quan[i]);
                        curAvPos = (this.price[i] - avPrice) * this.quan[i - 1];
                        if (curAvPos > 0) {
                            quProfDe = quProfDe + (this.quan[i - 1] - this.quan[i]);
                            quDe = quDe + (this.quan[i - 1] - this.quan[i]);
                            genProf = genProf + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        } else {
                            quLossDe = quLossDe + (this.quan[i - 1] - this.quan[i]);
                            quDe = quDe + (this.quan[i - 1] - this.quan[i]);
                            genLoss = genLoss + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        }
                        curResLo = curResLo + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        curRes = curRes + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        curAvPos = curAvPos * (double) (this.quan[i] / this.quan[i - 1]);
                        genRes = curRes - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    }
                } else if ((this.pos[i - 1] == -1 && this.pos[i] == -1) && (this.quan[i - 1] != this.quan[i])) { // частичное открытие/закрытие короткой позиции
                    if (this.quan[i - 1] < this.quan[i]) { // Частичный добор позиции в шорт
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i] - this.quan[i - 1]);
                        avPrice = (((avPrice * this.quan[i - 1]) + (this.quan[i] * (this.quan[i] - this.quan[i - 1]))) / this.quan[i]);
                        curAvPos = (avPrice - this.quan[i]) * this.quan[i];
                        genRes = curRes + curAvPos - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    } else if (this.quan[i - 1] > this.quan[i]) { // частичное закрытие короткой позиции
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i - 1] - this.quan[i]);
                        curAvPos = (avPrice - this.price[i]) * this.quan[i - 1];
                        if (curAvPos > 0) {
                            quProfDe = quProfDe + (this.quan[i - 1] - this.quan[i]);
                            quDe = quDe + (this.quan[i - 1] - this.quan[i]);
                            genProf = genProf + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        } else {
                            quLossDe = quLossDe + (this.quan[i - 1] - this.quan[i]);
                            quDe = quDe + (this.quan[i - 1] - this.quan[i]);
                            genLoss = genLoss + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        }
                        curResSh = curResSh + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        curRes = curRes + curAvPos / this.quan[i - 1] * (this.quan[i - 1] - this.quan[i]);
                        curAvPos = curAvPos * (double) (this.quan[i] / this.quan[i - 1]);
                        genRes = curRes - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    }
                } else if (this.pos[i - 1] == 1 && this.pos[i] == -1) { // переворот позиции из лонга в шорт
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i - 1];
                    curAvPos = (this.price[i] - avPrice) * this.quan[i - 1];
                    if (curAvPos > 0) {
                        quProfDe = quProfDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genProf = genProf + curAvPos;
                    } else {
                        quLossDe = quLossDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genLoss = genLoss + curAvPos;
                    }
                    curResLo = curResLo + curAvPos;
                    curRes = curRes + curAvPos;
                    curAvPos = 0.0;
                    genRes = curRes - comGen;
                    generalResult.add(genRes);
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (avPrice - this.close[i]) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == -1 && this.pos[i] == 1) { // переворот позиции из шорта в лонг
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i - 1];
                    curAvPos = (avPrice - this.price[i]) * this.quan[i - 1];
                    if (curAvPos > 0) {
                        quProfDe = quProfDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genProf = genProf + curAvPos;
                    } else {
                        quLossDe = quLossDe + this.quan[i - 1];
                        quDe = quDe + this.quan[i - 1];
                        genLoss = genLoss + curAvPos;
                    }
                    curResSh = curResSh + curAvPos;
                    curRes = curRes + curAvPos;
                    curAvPos = 0.0;
                    genRes = curRes - comGen;
                    generalResult.add(genRes);
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (this.close[i] - avPrice) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == 1 && this.pos[i] == 1 && this.quan[i - 1] == this.quan[i]) { // учёт открытой лонговой позиции
                    curAvPos = (this.close[i] - avPrice) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                } else if (this.pos[i - 1] == -1 && this.pos[i] == -1 && this.quan[i - 1] == this.quan[i]) {  // учёт открытой шортовой позиции
                    curAvPos = (avPrice - this.close[i]) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                } else if (this.pos[i - 1] == 0 && this.pos[i] == 0) { // учёт отсутсвия позиции
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                }
            }
            maxLoss = Collections.min(generalResult);
            maxProf = Collections.max(generalResult);
            if (quDe != 0) {
                procWinDe = (double) quProfDe / quDe * 100;
            } else {
                procWinDe = 10000;
            }
            if (quProfDe != 0) {
                avProf = genProf / quProfDe;
            } else {
                avProf = 0;
            }
            if (quLossDe != 0) {
                avLoss = genLoss / quLossDe;
            } else {
                avLoss = 0;
            }
            if (genLoss != 0) {
                profFac = genProf / Math.abs(genLoss);  // профит-фактор
            } else if (genProf == 0) {
                profFac = 0;
            } else {
                profFac = 10000;
            }
            if (maxLoss != 0) {
                recovFac = genRes / Math.abs(maxLoss); // фактор восстановления
            } else {
                recovFac = 10000;
            }
            if (avLoss != 0) {
                winKoo = avProf / Math.abs(avLoss);  // коэфициент выигрыша
            } else if (avProf == 0) {
                winKoo = 0;
            } else {
                winKoo = 10000;
            }
            this.genRes = genRes; // Чиспый П/У
            this.maxLoss = maxLoss; // Максимальная просадка счёта
            this.maxProf = maxProf; // Макс.выброс доходности
            this.quDe = quDe; // Кол-во сделок
            this.quProfDe = quProfDe; // Кол-во выиграных сделок
            this.quLossDe = quLossDe; // Количество прибыльных сделок
            this.quDealFilter = quDealFilter; // Количество сделок для фильтрации
            this.maxLoss = maxLoss;  // Максимальная просадка счёта
            this.maxProf = maxProf;  // Максимальное состояние счёта
            this.genProf = genProf;  // Общая прибыль (учитываются только прибыльные сделки)
            this.genLoss = genLoss;  // Общий убыток (учитываются только убыточные сделки)
            this.avProf = avProf;  // Средняя прибыль на одну прибыльную сделку
            this.avLoss = avLoss;  // Средний убыток на одну убыточную сделку
            this.profFac = profFac;  // Профит-фактор
            this.recovFac = recovFac; // Фактор восстановления
            this.winKoo = winKoo;  // Коэфициент выигрыша
            this.procWinDe = procWinDe;  // Процент выигранных сделок
            this.comGen = comGen;  // накопленная комиссия
            this.curResLo = curResLo;  // накопленный результат по закрытым лонговым позициям
            this.curResSh = curResSh;  // накопленный результат по закрытым шортовым позициям
        } else {
            this.genRes = 0.0; // Чиспый П/У
            this.maxLoss = 0.0; // Максимальная просадка счёта
            this.maxProf = 0.0; // Макс.выброс доходности
            this.quDe = 0; // Кол-во сделок
            this.quProfDe = 0; // Кол-во выиграных сделок
            this.quLossDe = 0; // Количество прибыльных сделок
            this.quDealFilter = 0; // Количество сделок для фильтрации
            this.maxLoss = 0.0;  // Максимальная просадка счёта
            this.maxProf = 0.0;  // Максимальное состояние счёта
            this.genProf = 0.0;  // Общая прибыль (учитываются только прибыльные сделки)
            this.genLoss = 0.0;  // Общий убыток (учитываются только убыточные сделки)
            this.avProf = 0.0;  // Средняя прибыль на одну прибыльную сделку
            this.avLoss = 0.0;  // Средний убыток на одну убыточную сделку
            this.profFac = 0.0;  // Профит-фактор
            this.recovFac = 0.0; // Фактор восстановления
            this.winKoo = 0.0;  // Коэфициент выигрыша
            this.procWinDe = 0.0;  // Процент выигранных сделок
            this.comGen = 0.0;  // накопленная комиссия
            this.curResLo = 0.0;  // накопленный результат по закрытым лонговым позициям
            this.curResSh = 0.0;  // накопленный результат по закрытым шортовым позициям
        }
    }

    /**
     *  Выводит полученный финансовый результат работы торгового алгоритма в файл в виде строки
     * @param pathToFile Путь к итоговому файлу
     * @throws IOException Выкидываем исключения
     */
    public void finResToFile(String pathToFile) throws IOException {
        StringBuilder parHeader = new StringBuilder();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("########.##", symbols);

        for (StringBuilder paramName : this.paramNames) {
            parHeader.append(";").append(paramName);
        }
        StringBuilder parameters = new StringBuilder();
        for (double parameter : this.parameters) {
            parameters.append(";").append(df.format(parameter));
        }
        String oneString = new String("");
        oneString = finResHeader[0] + ";" + finResHeader[1] + ";" + finResHeader[2] +
                ";" + finResHeader[3] + ";" + finResHeader[4] + ";" + finResHeader[5] +
                ";" + finResHeader[6] + ";" + finResHeader[7] + ";" + finResHeader[8] +
                ";" + finResHeader[9] + ";" + finResHeader[10] + ";" + finResHeader[11] +
                ";" + finResHeader[12] + ";" + finResHeader[13] + ";" + finResHeader[14] +
                ";" + finResHeader[15] + ";" + finResHeader[16] + ";" + finResHeader[17] +
                parHeader + "\n" + df.format(genRes) + ";" + df.format(maxLoss) + ";" + df.format(maxProf) + ";" +
                quDe + ";" + quProfDe + ";" + quLossDe + ";" + df.format(procWinDe) + ";" +
                df.format(genProf) + ";" + df.format(genLoss) + ";" + df.format(avProf) + ";" + df.format(avLoss) +
                ";" + df.format(profFac) + ";" + df.format(recovFac) + ";" + df.format(winKoo) + ";" +
                quDealFilter + ";" + df.format(comGen) + ";" + df.format(curResLo) + ";" + df.format(curResSh) + parameters + "\n";
        FileWriter writer = new FileWriter(pathToFile);
        writer.write(oneString.toString());
        writer.close();
    }

    public double getGenRes() {
        return this.genRes;
    }

    public double getMaxLoss() {
        return this.maxLoss;
    }

    public double getMaxProf() {
        return this.maxProf;
    }

    public int getQuDe() {
        return this.quDe;
    }

    public int getQuProfDe() {
        return this.quProfDe;
    }

    public int getQuLossDe() {
        return this.quLossDe;
    }

    public double getProcWinDe() {
        return this.procWinDe;
    }

    public double getGenProf() {
        return this.genProf;
    }

    public double getGenLoss() {
        return this.genLoss;
    }

    public double getAvProf() {
        return this.avProf;
    }

    public double getAvLoss() {
        return this.avLoss;
    }

    public double getProfFac() {
        return this.profFac;
    }

    public double getRecovFac() {
        return this.recovFac;
    }

    public double getWinKoo() {
        return this.winKoo;
    }

    public int getQuDealFilter() {
        return this.quDealFilter;
    }

    public double getComGen() {
        return this.comGen;
    }

    public double getCurResLo() {
        return this.curResLo;
    }

    public double getCurResSh() {
        return this.curResSh;
    }

    public void setCom(double com) {
        this.com = com;
    }
}
