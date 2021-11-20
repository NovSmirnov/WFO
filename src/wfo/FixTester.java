package wfo;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 *
 */
public class FixTester {
    protected String[] finResHeader = new String[]{"Чистый П/У", "Макс. просадка", "Макс.выброс доходности",
            "Кол-во сделок", "Кол-во выиграных сделок", "Кол-во проиграных сделок", "% выиграных сделок",
            "Общая прибыль", "Общий убыток", "Средняя прибыль на прибыльную сделку",
            "Средний убыток на убыточную сделку", "Профит фактор", "Фактор восстановления", "Коэф. выигрыша",
            "Кол-во сделок для фильтра", "Накопленная комиссия", "Результат по лонговым сделкам",
            "Результат по шортовым сделкам"}; // Смисок названий колонок (заголовок)

    protected List<Double> genRes; // Чиспый П/У
    protected List<Double> maxLoss; // Максимальная просадка счёта
    protected List<Double> maxProf; // Макс.выброс доходности
    protected List<Integer> quDe; // Кол-во сделок
    protected List<Integer> quProfDe; // Кол-во выиграных сделок
    protected List<Integer> quLossDe; // Кол-во проиграных сделок
    protected List<Double> procWinDe; // % выиграных сделок
    protected List<Double> genProf; // Общая прибыль
    protected List<Double> genLoss; // Общий убыток
    protected List<Double> avProf; // Средняя прибыль на одну прибыльную сделку
    protected List<Double> avLoss; // Средний убыток на одну убыточную сделку
    protected List<Double> profFac; // Профит фактор
    protected List<Double> recovFac; // Фактор восстановления
    protected List<Double> winKoo; // Коэф. выигрыша
    protected List<Integer> quDealFilter; // Кол-во сделок для фильтра
    protected List<Double> comGen; // Накопленная комиссия
    protected List<Double> curResLo;  // накопленный результат по закрытым лонговым позициям
    protected List<Double> curResSh;  // накопленный результат по закрытым шортовым позициям

    protected List<Double[]> parameters; // список с параметрами робота на каждом прогоне
    protected StringBuilder[] paramNames; // Массив с названиями параметров робота
    protected int[] sortByGenRes; // Массив с отсортированными по убыванию индексами значений списка genRes
    protected int[] sortByProcWinDe; // Массив с отсортированными по убыванию индексами значений списка procWinDe
    protected int[] sortByProfFac; // Массив с отсортированными по убыванию индексами значений списка profFac
    protected int[] sortByRecovFac; // Массив с отсортированными по убыванию индексами значений списка recovFac
    protected int[] sortByWinKoo; // Массив с отсортированными по убыванию индексами значений списка winKoo

    public void oneTest(ParRange paramRange, int quWeeks, double comission, String pathToFile) throws IOException {
        this.genRes = new ArrayList<>();
        this.maxLoss = new ArrayList<>();
        this.maxProf = new ArrayList<>();
        this.quDe = new ArrayList<>();
        this.quProfDe = new ArrayList<>();
        this.quLossDe = new ArrayList<>();
        this.procWinDe = new ArrayList<>();
        this.genProf = new ArrayList<>();
        this.genLoss = new ArrayList<>();
        this.avProf = new ArrayList<>();
        this.avLoss = new ArrayList<>();
        this.profFac = new ArrayList<>();
        this.recovFac = new ArrayList<>();
        this.winKoo = new ArrayList<>();
        this.quDealFilter = new ArrayList<>();
        this.comGen = new ArrayList<>();
        this.curResLo = new ArrayList<>();
        this.curResSh = new ArrayList<>();
        this.parameters = new ArrayList<>();
        StringBuilder[] paramNames = new StringBuilder[10];
        paramNames[0] = paramRange.parName1;
        paramNames[1] = paramRange.parName2;
        paramNames[2] = paramRange.parName3;
        paramNames[3] = paramRange.parName4;
        paramNames[4] = paramRange.parName5;
        paramNames[5] = paramRange.parName6;
        paramNames[6] = paramRange.parName7;
        paramNames[7] = paramRange.parName8;
        paramNames[8] = paramRange.parName9;
        paramNames[9] = paramRange.parName10;
        this.paramNames = paramNames;
        int parNum = 0;
        if (this.paramNames[0] != null) {
            parNum++;
        }
        if (this.paramNames[1] != null) {
            parNum++;
        }
        if (this.paramNames[2] != null) {
            parNum++;
        }
        if (this.paramNames[3] != null) {
            parNum++;
        }
        if (this.paramNames[4] != null) {
            parNum++;
        }
        if (this.paramNames[5] != null) {
            parNum++;
        }
        if (this.paramNames[6] != null) {
            parNum++;
        }
        if (this.paramNames[7] != null) {
            parNum++;
        }
        if (this.paramNames[8] != null) {
            parNum++;
        }
        if (this.paramNames[9] != null) {
            parNum++;
        }
        FinRes a = new FinRes();
        Parameter b = new Parameter();
        a.setQuote(pathToFile);
        a.setCom(comission);
        a.workArr(quWeeks);

        if (parNum == 1) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                Double[] parameters = new Double[1];
                System.out.println(i);
                b.setPar(i, paramRange.getParName1());
                GlobalTest.robFunc(a, b);
                a.counter();
                this.genRes.add(a.getGenRes());
                this.maxLoss.add(a.getMaxLoss());
                this.maxProf.add(a.getMaxProf());
                this.quDe.add(a.getQuDe());
                this.quProfDe.add(a.getQuProfDe());
                this.quLossDe.add(a.getQuLossDe());
                this.procWinDe.add(a.getProcWinDe());
                this.genProf.add(a.getGenProf());
                this.genLoss.add(a.getGenLoss());
                this.avProf.add(a.getAvProf());
                this.avLoss.add(a.getAvLoss());
                this.profFac.add(a.getProfFac());
                this.recovFac.add(a.getRecovFac());
                this.winKoo.add(a.getWinKoo());
                this.quDealFilter.add(a.getQuDealFilter());
                this.comGen.add(a.getComGen());
                this.curResLo.add(a.getCurResLo());
                this.curResSh.add(a.getCurResSh());
                parameters[0] = i;
                this.parameters.add(parameters);
            }
        } else if (parNum == 2) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    Double[] parameters = new Double[2];
                    System.out.println(i + " " + j);
                    b.setPar(i, j, paramRange.getParName1(), paramRange.getParName2());
                    GlobalTest.robFunc(a, b);
                    a.counter();
                    this.genRes.add(a.getGenRes());
                    this.maxLoss.add(a.getMaxLoss());
                    this.maxProf.add(a.getMaxProf());
                    this.quDe.add(a.getQuDe());
                    this.quProfDe.add(a.getQuProfDe());
                    this.quLossDe.add(a.getQuLossDe());
                    this.procWinDe.add(a.getProcWinDe());
                    this.genProf.add(a.getGenProf());
                    this.genLoss.add(a.getGenLoss());
                    this.avProf.add(a.getAvProf());
                    this.avLoss.add(a.getAvLoss());
                    this.profFac.add(a.getProfFac());
                    this.recovFac.add(a.getRecovFac());
                    this.winKoo.add(a.getWinKoo());
                    this.quDealFilter.add(a.getQuDealFilter());
                    this.comGen.add(a.getComGen());
                    this.curResLo.add(a.getCurResLo());
                    this.curResSh.add(a.getCurResSh());
                    parameters[0] = i;
                    parameters[1] = j;
                    this.parameters.add(parameters);
                }
            }
        } else if (parNum == 3) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        Double[] parameters = new Double[3];
                        b.setPar(i, j, k, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3());
                        GlobalTest.robFunc(a, b);
                        a.counter();
                        this.genRes.add(a.getGenRes());
                        this.maxLoss.add(a.getMaxLoss());
                        this.maxProf.add(a.getMaxProf());
                        this.quDe.add(a.getQuDe());
                        this.quProfDe.add(a.getQuProfDe());
                        this.quLossDe.add(a.getQuLossDe());
                        this.procWinDe.add(a.getProcWinDe());
                        this.genProf.add(a.getGenProf());
                        this.genLoss.add(a.getGenLoss());
                        this.avProf.add(a.getAvProf());
                        this.avLoss.add(a.getAvLoss());
                        this.profFac.add(a.getProfFac());
                        this.recovFac.add(a.getRecovFac());
                        this.winKoo.add(a.getWinKoo());
                        this.quDealFilter.add(a.getQuDealFilter());
                        this.comGen.add(a.getComGen());
                        this.curResLo.add(a.getCurResLo());
                        this.curResSh.add(a.getCurResSh());
                        parameters[0] = i;
                        parameters[1] = j;
                        parameters[2] = k;
                        this.parameters.add(parameters);
                    }
                }
            }
        } else if (parNum == 4) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            Double[] parameters = new Double[4];
                            b.setPar(i, j, k, l, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4());
                            GlobalTest.robFunc(a, b);
                            a.counter();
                            this.genRes.add(a.getGenRes());
                            this.maxLoss.add(a.getMaxLoss());
                            this.maxProf.add(a.getMaxProf());
                            this.quDe.add(a.getQuDe());
                            this.quProfDe.add(a.getQuProfDe());
                            this.quLossDe.add(a.getQuLossDe());
                            this.procWinDe.add(a.getProcWinDe());
                            this.genProf.add(a.getGenProf());
                            this.genLoss.add(a.getGenLoss());
                            this.avProf.add(a.getAvProf());
                            this.avLoss.add(a.getAvLoss());
                            this.profFac.add(a.getProfFac());
                            this.recovFac.add(a.getRecovFac());
                            this.winKoo.add(a.getWinKoo());
                            this.quDealFilter.add(a.getQuDealFilter());
                            this.comGen.add(a.getComGen());
                            this.curResLo.add(a.getCurResLo());
                            this.curResSh.add(a.getCurResSh());
                            parameters[0] = i;
                            parameters[1] = j;
                            parameters[2] = k;
                            parameters[3] = l;
                            this.parameters.add(parameters);
                        }
                    }
                }
            }
        } else if (parNum == 5) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                Double[] parameters = new Double[5];
                                b.setPar(i, j, k, l, m, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5());
                                GlobalTest.robFunc(a, b);
                                a.counter();
                                this.genRes.add(a.getGenRes());
                                this.maxLoss.add(a.getMaxLoss());
                                this.maxProf.add(a.getMaxProf());
                                this.quDe.add(a.getQuDe());
                                this.quProfDe.add(a.getQuProfDe());
                                this.quLossDe.add(a.getQuLossDe());
                                this.procWinDe.add(a.getProcWinDe());
                                this.genProf.add(a.getGenProf());
                                this.genLoss.add(a.getGenLoss());
                                this.avProf.add(a.getAvProf());
                                this.avLoss.add(a.getAvLoss());
                                this.profFac.add(a.getProfFac());
                                this.recovFac.add(a.getRecovFac());
                                this.winKoo.add(a.getWinKoo());
                                this.quDealFilter.add(a.getQuDealFilter());
                                this.comGen.add(a.getComGen());
                                this.curResLo.add(a.getCurResLo());
                                this.curResSh.add(a.getCurResSh());
                                parameters[0] = i;
                                parameters[1] = j;
                                parameters[2] = k;
                                parameters[3] = l;
                                parameters[4] = m;
                                this.parameters.add(parameters);
                            }
                        }
                    }
                }
            }
        } else if (parNum == 6) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    Double[] parameters = new Double[6];
                                    b.setPar(i, j, k, l, m, n, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6());
                                    GlobalTest.robFunc(a, b);
                                    a.counter();
                                    this.genRes.add(a.getGenRes());
                                    this.maxLoss.add(a.getMaxLoss());
                                    this.maxProf.add(a.getMaxProf());
                                    this.quDe.add(a.getQuDe());
                                    this.quProfDe.add(a.getQuProfDe());
                                    this.quLossDe.add(a.getQuLossDe());
                                    this.procWinDe.add(a.getProcWinDe());
                                    this.genProf.add(a.getGenProf());
                                    this.genLoss.add(a.getGenLoss());
                                    this.avProf.add(a.getAvProf());
                                    this.avLoss.add(a.getAvLoss());
                                    this.profFac.add(a.getProfFac());
                                    this.recovFac.add(a.getRecovFac());
                                    this.winKoo.add(a.getWinKoo());
                                    this.quDealFilter.add(a.getQuDealFilter());
                                    this.comGen.add(a.getComGen());
                                    this.curResLo.add(a.getCurResLo());
                                    this.curResSh.add(a.getCurResSh());
                                    parameters[0] = i;
                                    parameters[1] = j;
                                    parameters[2] = k;
                                    parameters[3] = l;
                                    parameters[4] = m;
                                    parameters[5] = n;
                                    this.parameters.add(parameters);
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 7) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        Double[] parameters = new Double[7];
                                        b.setPar(i, j, k, l, m, n, o, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7());
                                        GlobalTest.robFunc(a, b);
                                        a.counter();
                                        this.genRes.add(a.getGenRes());
                                        this.maxLoss.add(a.getMaxLoss());
                                        this.maxProf.add(a.getMaxProf());
                                        this.quDe.add(a.getQuDe());
                                        this.quProfDe.add(a.getQuProfDe());
                                        this.quLossDe.add(a.getQuLossDe());
                                        this.procWinDe.add(a.getProcWinDe());
                                        this.genProf.add(a.getGenProf());
                                        this.genLoss.add(a.getGenLoss());
                                        this.avProf.add(a.getAvProf());
                                        this.avLoss.add(a.getAvLoss());
                                        this.profFac.add(a.getProfFac());
                                        this.recovFac.add(a.getRecovFac());
                                        this.winKoo.add(a.getWinKoo());
                                        this.quDealFilter.add(a.getQuDealFilter());
                                        this.comGen.add(a.getComGen());
                                        this.curResLo.add(a.getCurResLo());
                                        this.curResSh.add(a.getCurResSh());
                                        parameters[0] = i;
                                        parameters[1] = j;
                                        parameters[2] = k;
                                        parameters[3] = l;
                                        parameters[4] = m;
                                        parameters[5] = n;
                                        parameters[6] = o;
                                        this.parameters.add(parameters);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 8) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            Double[] parameters = new Double[8];
                                            b.setPar(i, j, k, l, m, n, o, p, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8());
                                            GlobalTest.robFunc(a, b);
                                            a.counter();
                                            this.genRes.add(a.getGenRes());
                                            this.maxLoss.add(a.getMaxLoss());
                                            this.maxProf.add(a.getMaxProf());
                                            this.quDe.add(a.getQuDe());
                                            this.quProfDe.add(a.getQuProfDe());
                                            this.quLossDe.add(a.getQuLossDe());
                                            this.procWinDe.add(a.getProcWinDe());
                                            this.genProf.add(a.getGenProf());
                                            this.genLoss.add(a.getGenLoss());
                                            this.avProf.add(a.getAvProf());
                                            this.avLoss.add(a.getAvLoss());
                                            this.profFac.add(a.getProfFac());
                                            this.recovFac.add(a.getRecovFac());
                                            this.winKoo.add(a.getWinKoo());
                                            this.quDealFilter.add(a.getQuDealFilter());
                                            this.comGen.add(a.getComGen());
                                            this.curResLo.add(a.getCurResLo());
                                            this.curResSh.add(a.getCurResSh());
                                            parameters[0] = i;
                                            parameters[1] = j;
                                            parameters[2] = k;
                                            parameters[3] = l;
                                            parameters[4] = m;
                                            parameters[5] = n;
                                            parameters[6] = o;
                                            parameters[7] = p;
                                            this.parameters.add(parameters);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 9) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            for (double q = paramRange.getParRange4()[0]; q <= paramRange.getParRange4()[1]; q = q + paramRange.getParRange4()[2]) {
                                                Double[] parameters = new Double[9];
                                                b.setPar(i, j, k, l, m, n, o, p, q, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8(), paramRange.getParName9());
                                                GlobalTest.robFunc(a, b);
                                                a.counter();
                                                this.genRes.add(a.getGenRes());
                                                this.maxLoss.add(a.getMaxLoss());
                                                this.maxProf.add(a.getMaxProf());
                                                this.quDe.add(a.getQuDe());
                                                this.quProfDe.add(a.getQuProfDe());
                                                this.quLossDe.add(a.getQuLossDe());
                                                this.procWinDe.add(a.getProcWinDe());
                                                this.genProf.add(a.getGenProf());
                                                this.genLoss.add(a.getGenLoss());
                                                this.avProf.add(a.getAvProf());
                                                this.avLoss.add(a.getAvLoss());
                                                this.profFac.add(a.getProfFac());
                                                this.recovFac.add(a.getRecovFac());
                                                this.winKoo.add(a.getWinKoo());
                                                this.quDealFilter.add(a.getQuDealFilter());
                                                this.comGen.add(a.getComGen());
                                                this.curResLo.add(a.getCurResLo());
                                                this.curResSh.add(a.getCurResSh());
                                                parameters[0] = i;
                                                parameters[1] = j;
                                                parameters[2] = k;
                                                parameters[3] = l;
                                                parameters[4] = m;
                                                parameters[5] = n;
                                                parameters[6] = o;
                                                parameters[7] = p;
                                                parameters[8] = q;
                                                this.parameters.add(parameters);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 10) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    System.out.println(i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            for (double q = paramRange.getParRange4()[0]; q <= paramRange.getParRange4()[1]; q = q + paramRange.getParRange4()[2]) {
                                                for (double r = paramRange.getParRange4()[0]; r <= paramRange.getParRange4()[1]; r = r + paramRange.getParRange4()[2]) {
                                                    Double[] parameters = new Double[10];
                                                    b.setPar(i, j, k, l, m, n, o, p, q, r, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8(), paramRange.getParName9(), paramRange.getParName10());
                                                    GlobalTest.robFunc(a, b);
                                                    a.counter();
                                                    this.genRes.add(a.getGenRes());
                                                    this.maxLoss.add(a.getMaxLoss());
                                                    this.maxProf.add(a.getMaxProf());
                                                    this.quDe.add(a.getQuDe());
                                                    this.quProfDe.add(a.getQuProfDe());
                                                    this.quLossDe.add(a.getQuLossDe());
                                                    this.procWinDe.add(a.getProcWinDe());
                                                    this.genProf.add(a.getGenProf());
                                                    this.genLoss.add(a.getGenLoss());
                                                    this.avProf.add(a.getAvProf());
                                                    this.avLoss.add(a.getAvLoss());
                                                    this.profFac.add(a.getProfFac());
                                                    this.recovFac.add(a.getRecovFac());
                                                    this.winKoo.add(a.getWinKoo());
                                                    this.quDealFilter.add(a.getQuDealFilter());
                                                    this.comGen.add(a.getComGen());
                                                    this.curResLo.add(a.getCurResLo());
                                                    this.curResSh.add(a.getCurResSh());
                                                    parameters[0] = i;
                                                    parameters[1] = j;
                                                    parameters[2] = k;
                                                    parameters[3] = l;
                                                    parameters[4] = m;
                                                    parameters[5] = n;
                                                    parameters[6] = o;
                                                    parameters[7] = p;
                                                    parameters[8] = q;
                                                    parameters[9] = r;
                                                    this.parameters.add(parameters);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void oneTestToFile(String pathToFile) throws IOException {
        StringBuilder parHeader = new StringBuilder();
        for (StringBuilder parName : this.paramNames) {
            parHeader.append(";").append(parName);
        }
        String headLine = new String("");
        headLine = finResHeader[0] + ";" + finResHeader[1] + ";" + finResHeader[2] +
                ";" + finResHeader[3] + ";" + finResHeader[4] + ";" + finResHeader[5] +
                ";" + finResHeader[6] + ";" + finResHeader[7] + ";" + finResHeader[8] +
                ";" + finResHeader[9] + ";" + finResHeader[10] + ";" + finResHeader[11] +
                ";" + finResHeader[12] + ";" + finResHeader[13] + ";" + finResHeader[14] +
                ";" + finResHeader[15] + ";" + finResHeader[16] + ";" + finResHeader[17] +
                parHeader + "\n";
        String line = new String("");
        FileWriter writer = new FileWriter(pathToFile, Charset.forName("cp1251"));
        writer.write(headLine.toString());
        for (int i = 0; i < this.genRes.size(); i++) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat df = new DecimalFormat("########.##", symbols);
            StringBuilder parameters = new StringBuilder();
            for (Double parameter : this.parameters.get(i)) {
                parameters.append(";").append(df.format(parameter));
            }
            line = df.format(this.genRes.get(i)) + ";" + df.format(this.maxLoss.get(i)) + ";" + df.format(this.maxProf.get(i)) + ";" +
                    df.format(this.quDe.get(i)) + ";" + df.format(this.quProfDe.get(i)) + ";" + df.format(this.quLossDe.get(i)) + ";" + df.format(this.procWinDe.get(i)) + ";" +
                    df.format(this.genProf.get(i)) + ";" + df.format(this.genLoss.get(i)) + ";" + df.format(this.avProf.get(i)) + ";" + df.format(this.avLoss.get(i)) +
                    ";" + df.format(this.profFac.get(i)) + ";" + df.format(this.recovFac.get(i)) + ";" + df.format(this.winKoo.get(i)) + ";" +
                    df.format(this.quDealFilter.get(i)) + ";" + df.format(this.comGen.get(i)) + ";" + df.format(this.curResLo.get(i)) + ";" +
                    df.format(this.curResSh.get(i)) + parameters + "\n";
            writer.write(line.toString());

        }
        writer.close();
    }

    public void sortBy() {
        int[] indListGenRes = new int[this.genRes.size()];
        int[] indListProcWinDe = new int[this.procWinDe.size()];
        int[] indListProfFac = new int[this.profFac.size()];
        int[] indListRecovFac = new int[this.recovFac.size()];
        int[] indListWinKoo = new int[this.winKoo.size()];
        double[] genRes = new double[this.genRes.size()];
        double[] procWinDe = new double[this.procWinDe.size()];
        double[] profFac = new double[this.profFac.size()];
        double[] recovFac = new double[this.recovFac.size()];
        double[] winKoo = new double[this.winKoo.size()];

        for (int i = 0; i < this.genRes.size(); i++) {
            indListGenRes[i] = i;
            indListProcWinDe[i] = i;
            indListProfFac[i] = i;
            indListRecovFac[i] = i;
            indListWinKoo[i] = i;
            genRes[i] = this.genRes.get(i);
            procWinDe[i] = this.procWinDe.get(i);
            profFac[i] = this.profFac.get(i);
            recovFac[i] = this.recovFac.get(i);
            winKoo[i] = this.winKoo.get(i);
        }
        for (int j = 0; j < indListGenRes.length - 1; j++) {
            for (int k = 0; k < indListGenRes.length - j - 1; k++) {
                if (genRes[k] < genRes[k + 1]) {
                    double temp1 = genRes[k];
                    genRes[k] = genRes[k + 1];
                    genRes[k + 1] = temp1;
                    int indexTemp1 = indListGenRes[k];
                    indListGenRes[k] = indListGenRes[k + 1];
                    indListGenRes[k + 1] = indexTemp1;
                }
                if (procWinDe[k] < procWinDe[k + 1]) {
                    double temp2 = procWinDe[k];
                    procWinDe[k] = procWinDe[k + 1];
                    procWinDe[k + 1] = temp2;
                    int indexTemp2 = indListProcWinDe[k];
                    indListProcWinDe[k] = indListProcWinDe[k + 1];
                    indListProcWinDe[k + 1] = indexTemp2;
                }
                if (profFac[k] < profFac[k + 1]) {
                    double temp3 = profFac[k];
                    profFac[k] = profFac[k + 1];
                    profFac[k + 1] = temp3;
                    int indexTemp3 = indListProfFac[k];
                    indListProfFac[k] = indListProfFac[k + 1];
                    indListProfFac[k + 1] = indexTemp3;
                }
                if (recovFac[k] < recovFac[k + 1]) {
                    double temp4 = recovFac[k];
                    recovFac[k] = recovFac[k + 1];
                    recovFac[k + 1] = temp4;
                    int indexTemp4 = indListRecovFac[k];
                    indListRecovFac[k] = indListRecovFac[k + 1];
                    indListRecovFac[k + 1] = indexTemp4;
                }
                if (winKoo[k] < winKoo[k + 1]) {
                    double temp5 = winKoo[k];
                    winKoo[k] = winKoo[k + 1];
                    winKoo[k + 1] = temp5;
                    int indexTemp5 = indListWinKoo[k];
                    indListWinKoo[k] = indListWinKoo[k + 1];
                    indListWinKoo[k + 1] = indexTemp5;
                }
            }
        }
        this.sortByGenRes = indListGenRes;
        this.sortByProcWinDe = indListProcWinDe;
        this.sortByProfFac = indListProfFac;
        this.sortByRecovFac = indListRecovFac;
        this.sortByWinKoo = indListWinKoo;
    }

    public void sortedGenResToFile(String pathToFile) throws IOException {
        StringBuilder parHeader = new StringBuilder();
        for (StringBuilder parName : this.paramNames) {
            parHeader.append(";").append(parName);
        }
        String headLine = new String("");
        headLine = finResHeader[0] + ";" + finResHeader[1] + ";" + finResHeader[2] +
                ";" + finResHeader[3] + ";" + finResHeader[4] + ";" + finResHeader[5] +
                ";" + finResHeader[6] + ";" + finResHeader[7] + ";" + finResHeader[8] +
                ";" + finResHeader[9] + ";" + finResHeader[10] + ";" + finResHeader[11] +
                ";" + finResHeader[12] + ";" + finResHeader[13] + ";" + finResHeader[14] +
                ";" + finResHeader[15] + ";" + finResHeader[16] + ";" + finResHeader[17] +
                parHeader + "\n";
        String line = new String("");
        FileWriter writer = new FileWriter(pathToFile, Charset.forName("cp1251"));
        writer.write(headLine.toString());
        for (int i = 0; i < this.genRes.size(); i++) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat df = new DecimalFormat("########.##", symbols);
            StringBuilder parameters = new StringBuilder();
            for (Double parameter : this.parameters.get(sortByGenRes[i])) {
                parameters.append(";").append(df.format(parameter));
            }
            line = df.format(this.genRes.get(sortByGenRes[i])) + ";" + df.format(this.maxLoss.get(sortByGenRes[i])) + ";" + df.format(this.maxProf.get(sortByGenRes[i])) + ";" +
                    df.format(this.quDe.get(sortByGenRes[i])) + ";" + df.format(this.quProfDe.get(sortByGenRes[i])) + ";" + df.format(this.quLossDe.get(sortByGenRes[i])) + ";" + df.format(this.procWinDe.get(sortByGenRes[i])) + ";" +
                    df.format(this.genProf.get(sortByGenRes[i])) + ";" + df.format(this.genLoss.get(sortByGenRes[i])) + ";" + df.format(this.avProf.get(sortByGenRes[i])) + ";" + df.format(this.avLoss.get(sortByGenRes[i])) +
                    ";" + df.format(this.profFac.get(sortByGenRes[i])) + ";" + df.format(this.recovFac.get(sortByGenRes[i])) + ";" + df.format(this.winKoo.get(sortByGenRes[i])) + ";" +
                    df.format(this.quDealFilter.get(sortByGenRes[i])) + ";" + df.format(this.comGen.get(sortByGenRes[i])) + ";" + df.format(this.curResLo.get(sortByGenRes[i])) + ";" +
                    df.format(this.curResSh.get(sortByGenRes[i])) + parameters + "\n";
            writer.write(line.toString());

        }
        writer.close();
    }

    public void learning(ParRange paramRange, FinRes a) throws IOException {
        this.genRes = new ArrayList<>();
        this.maxLoss = new ArrayList<>();
        this.maxProf = new ArrayList<>();
        this.quDe = new ArrayList<>();
        this.quProfDe = new ArrayList<>();
        this.quLossDe = new ArrayList<>();
        this.procWinDe = new ArrayList<>();
        this.genProf = new ArrayList<>();
        this.genLoss = new ArrayList<>();
        this.avProf = new ArrayList<>();
        this.avLoss = new ArrayList<>();
        this.profFac = new ArrayList<>();
        this.recovFac = new ArrayList<>();
        this.winKoo = new ArrayList<>();
        this.quDealFilter = new ArrayList<>();
        this.comGen = new ArrayList<>();
        this.curResLo = new ArrayList<>();
        this.curResSh = new ArrayList<>();
        this.parameters = new ArrayList<>();
        StringBuilder[] paramNames = new StringBuilder[10];
        paramNames[0] = paramRange.parName1;
        paramNames[1] = paramRange.parName2;
        paramNames[2] = paramRange.parName3;
        paramNames[3] = paramRange.parName4;
        paramNames[4] = paramRange.parName5;
        paramNames[5] = paramRange.parName6;
        paramNames[6] = paramRange.parName7;
        paramNames[7] = paramRange.parName8;
        paramNames[8] = paramRange.parName9;
        paramNames[9] = paramRange.parName10;
        this.paramNames = paramNames;
        int parNum = 0;
        if (this.paramNames[0] != null) {
            parNum++;
        }
        if (this.paramNames[1] != null) {
            parNum++;
        }
        if (this.paramNames[2] != null) {
            parNum++;
        }
        if (this.paramNames[3] != null) {
            parNum++;
        }
        if (this.paramNames[4] != null) {
            parNum++;
        }
        if (this.paramNames[5] != null) {
            parNum++;
        }
        if (this.paramNames[6] != null) {
            parNum++;
        }
        if (this.paramNames[7] != null) {
            parNum++;
        }
        if (this.paramNames[8] != null) {
            parNum++;
        }
        if (this.paramNames[9] != null) {
            parNum++;
        }
        Parameter b = new Parameter();

        if (parNum == 1) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                Double[] parameters = new Double[1];
//				System.out.println(a.getTicker()[1] + " " + weekNum + " " + i);
                b.setPar(i, paramRange.getParName1());
                GlobalTest.robFunc(a, b);
                a.counter();
                this.genRes.add(a.getGenRes());
                this.maxLoss.add(a.getMaxLoss());
                this.maxProf.add(a.getMaxProf());
                this.quDe.add(a.getQuDe());
                this.quProfDe.add(a.getQuProfDe());
                this.quLossDe.add(a.getQuLossDe());
                this.procWinDe.add(a.getProcWinDe());
                this.genProf.add(a.getGenProf());
                this.genLoss.add(a.getGenLoss());
                this.avProf.add(a.getAvProf());
                this.avLoss.add(a.getAvLoss());
                this.profFac.add(a.getProfFac());
                this.recovFac.add(a.getRecovFac());
                this.winKoo.add(a.getWinKoo());
                this.quDealFilter.add(a.getQuDealFilter());
                this.comGen.add(a.getComGen());
                this.curResLo.add(a.getCurResLo());
                this.curResSh.add(a.getCurResSh());
                parameters[0] = i;
                this.parameters.add(parameters);
            }
        } else if (parNum == 2) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
                    Double[] parameters = new Double[2];
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    b.setPar(i, j, paramRange.getParName1(), paramRange.getParName2());
                    GlobalTest.robFunc(a, b);
                    a.counter();
                    this.genRes.add(a.getGenRes());
                    this.maxLoss.add(a.getMaxLoss());
                    this.maxProf.add(a.getMaxProf());
                    this.quDe.add(a.getQuDe());
                    this.quProfDe.add(a.getQuProfDe());
                    this.quLossDe.add(a.getQuLossDe());
                    this.procWinDe.add(a.getProcWinDe());
                    this.genProf.add(a.getGenProf());
                    this.genLoss.add(a.getGenLoss());
                    this.avProf.add(a.getAvProf());
                    this.avLoss.add(a.getAvLoss());
                    this.profFac.add(a.getProfFac());
                    this.recovFac.add(a.getRecovFac());
                    this.winKoo.add(a.getWinKoo());
                    this.quDealFilter.add(a.getQuDealFilter());
                    this.comGen.add(a.getComGen());
                    this.curResLo.add(a.getCurResLo());
                    this.curResSh.add(a.getCurResSh());
                    parameters[0] = i;
                    parameters[1] = j;
                    this.parameters.add(parameters);
                }
            }
        } else if (parNum == 3) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        Double[] parameters = new Double[3];
                        b.setPar(i, j, k, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3());
                        GlobalTest.robFunc(a, b);
                        a.counter();
                        this.genRes.add(a.getGenRes());
                        this.maxLoss.add(a.getMaxLoss());
                        this.maxProf.add(a.getMaxProf());
                        this.quDe.add(a.getQuDe());
                        this.quProfDe.add(a.getQuProfDe());
                        this.quLossDe.add(a.getQuLossDe());
                        this.procWinDe.add(a.getProcWinDe());
                        this.genProf.add(a.getGenProf());
                        this.genLoss.add(a.getGenLoss());
                        this.avProf.add(a.getAvProf());
                        this.avLoss.add(a.getAvLoss());
                        this.profFac.add(a.getProfFac());
                        this.recovFac.add(a.getRecovFac());
                        this.winKoo.add(a.getWinKoo());
                        this.quDealFilter.add(a.getQuDealFilter());
                        this.comGen.add(a.getComGen());
                        this.curResLo.add(a.getCurResLo());
                        this.curResSh.add(a.getCurResSh());
                        parameters[0] = i;
                        parameters[1] = j;
                        parameters[2] = k;
                        this.parameters.add(parameters);
                    }
                }
            }
        } else if (parNum == 4) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            Double[] parameters = new Double[4];
                            b.setPar(i, j, k, l, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4());
                            GlobalTest.robFunc(a, b);
                            a.counter();
                            this.genRes.add(a.getGenRes());
                            this.maxLoss.add(a.getMaxLoss());
                            this.maxProf.add(a.getMaxProf());
                            this.quDe.add(a.getQuDe());
                            this.quProfDe.add(a.getQuProfDe());
                            this.quLossDe.add(a.getQuLossDe());
                            this.procWinDe.add(a.getProcWinDe());
                            this.genProf.add(a.getGenProf());
                            this.genLoss.add(a.getGenLoss());
                            this.avProf.add(a.getAvProf());
                            this.avLoss.add(a.getAvLoss());
                            this.profFac.add(a.getProfFac());
                            this.recovFac.add(a.getRecovFac());
                            this.winKoo.add(a.getWinKoo());
                            this.quDealFilter.add(a.getQuDealFilter());
                            this.comGen.add(a.getComGen());
                            this.curResLo.add(a.getCurResLo());
                            this.curResSh.add(a.getCurResSh());
                            parameters[0] = i;
                            parameters[1] = j;
                            parameters[2] = k;
                            parameters[3] = l;
                            this.parameters.add(parameters);
                        }
                    }
                }
            }
        } else if (parNum == 5) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                Double[] parameters = new Double[5];
                                b.setPar(i, j, k, l, m, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5());
                                GlobalTest.robFunc(a, b);
                                a.counter();
                                this.genRes.add(a.getGenRes());
                                this.maxLoss.add(a.getMaxLoss());
                                this.maxProf.add(a.getMaxProf());
                                this.quDe.add(a.getQuDe());
                                this.quProfDe.add(a.getQuProfDe());
                                this.quLossDe.add(a.getQuLossDe());
                                this.procWinDe.add(a.getProcWinDe());
                                this.genProf.add(a.getGenProf());
                                this.genLoss.add(a.getGenLoss());
                                this.avProf.add(a.getAvProf());
                                this.avLoss.add(a.getAvLoss());
                                this.profFac.add(a.getProfFac());
                                this.recovFac.add(a.getRecovFac());
                                this.winKoo.add(a.getWinKoo());
                                this.quDealFilter.add(a.getQuDealFilter());
                                this.comGen.add(a.getComGen());
                                this.curResLo.add(a.getCurResLo());
                                this.curResSh.add(a.getCurResSh());
                                parameters[0] = i;
                                parameters[1] = j;
                                parameters[2] = k;
                                parameters[3] = l;
                                parameters[4] = m;
                                this.parameters.add(parameters);
                            }
                        }
                    }
                }
            }
        } else if (parNum == 6) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    Double[] parameters = new Double[6];
                                    b.setPar(i, j, k, l, m, n, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6());
                                    GlobalTest.robFunc(a, b);
                                    a.counter();
                                    this.genRes.add(a.getGenRes());
                                    this.maxLoss.add(a.getMaxLoss());
                                    this.maxProf.add(a.getMaxProf());
                                    this.quDe.add(a.getQuDe());
                                    this.quProfDe.add(a.getQuProfDe());
                                    this.quLossDe.add(a.getQuLossDe());
                                    this.procWinDe.add(a.getProcWinDe());
                                    this.genProf.add(a.getGenProf());
                                    this.genLoss.add(a.getGenLoss());
                                    this.avProf.add(a.getAvProf());
                                    this.avLoss.add(a.getAvLoss());
                                    this.profFac.add(a.getProfFac());
                                    this.recovFac.add(a.getRecovFac());
                                    this.winKoo.add(a.getWinKoo());
                                    this.quDealFilter.add(a.getQuDealFilter());
                                    this.comGen.add(a.getComGen());
                                    this.curResLo.add(a.getCurResLo());
                                    this.curResSh.add(a.getCurResSh());
                                    parameters[0] = i;
                                    parameters[1] = j;
                                    parameters[2] = k;
                                    parameters[3] = l;
                                    parameters[4] = m;
                                    parameters[5] = n;
                                    this.parameters.add(parameters);
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 7) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        Double[] parameters = new Double[7];
                                        b.setPar(i, j, k, l, m, n, o, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7());
                                        GlobalTest.robFunc(a, b);
                                        a.counter();
                                        this.genRes.add(a.getGenRes());
                                        this.maxLoss.add(a.getMaxLoss());
                                        this.maxProf.add(a.getMaxProf());
                                        this.quDe.add(a.getQuDe());
                                        this.quProfDe.add(a.getQuProfDe());
                                        this.quLossDe.add(a.getQuLossDe());
                                        this.procWinDe.add(a.getProcWinDe());
                                        this.genProf.add(a.getGenProf());
                                        this.genLoss.add(a.getGenLoss());
                                        this.avProf.add(a.getAvProf());
                                        this.avLoss.add(a.getAvLoss());
                                        this.profFac.add(a.getProfFac());
                                        this.recovFac.add(a.getRecovFac());
                                        this.winKoo.add(a.getWinKoo());
                                        this.quDealFilter.add(a.getQuDealFilter());
                                        this.comGen.add(a.getComGen());
                                        this.curResLo.add(a.getCurResLo());
                                        this.curResSh.add(a.getCurResSh());
                                        parameters[0] = i;
                                        parameters[1] = j;
                                        parameters[2] = k;
                                        parameters[3] = l;
                                        parameters[4] = m;
                                        parameters[5] = n;
                                        parameters[6] = o;
                                        this.parameters.add(parameters);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 8) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            Double[] parameters = new Double[8];
                                            b.setPar(i, j, k, l, m, n, o, p, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8());
                                            GlobalTest.robFunc(a, b);
                                            a.counter();
                                            this.genRes.add(a.getGenRes());
                                            this.maxLoss.add(a.getMaxLoss());
                                            this.maxProf.add(a.getMaxProf());
                                            this.quDe.add(a.getQuDe());
                                            this.quProfDe.add(a.getQuProfDe());
                                            this.quLossDe.add(a.getQuLossDe());
                                            this.procWinDe.add(a.getProcWinDe());
                                            this.genProf.add(a.getGenProf());
                                            this.genLoss.add(a.getGenLoss());
                                            this.avProf.add(a.getAvProf());
                                            this.avLoss.add(a.getAvLoss());
                                            this.profFac.add(a.getProfFac());
                                            this.recovFac.add(a.getRecovFac());
                                            this.winKoo.add(a.getWinKoo());
                                            this.quDealFilter.add(a.getQuDealFilter());
                                            this.comGen.add(a.getComGen());
                                            this.curResLo.add(a.getCurResLo());
                                            this.curResSh.add(a.getCurResSh());
                                            parameters[0] = i;
                                            parameters[1] = j;
                                            parameters[2] = k;
                                            parameters[3] = l;
                                            parameters[4] = m;
                                            parameters[5] = n;
                                            parameters[6] = o;
                                            parameters[7] = p;
                                            this.parameters.add(parameters);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 9) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            for (double q = paramRange.getParRange4()[0]; q <= paramRange.getParRange4()[1]; q = q + paramRange.getParRange4()[2]) {
                                                Double[] parameters = new Double[9];
                                                b.setPar(i, j, k, l, m, n, o, p, q, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8(), paramRange.getParName9());
                                                GlobalTest.robFunc(a, b);
                                                a.counter();
                                                this.genRes.add(a.getGenRes());
                                                this.maxLoss.add(a.getMaxLoss());
                                                this.maxProf.add(a.getMaxProf());
                                                this.quDe.add(a.getQuDe());
                                                this.quProfDe.add(a.getQuProfDe());
                                                this.quLossDe.add(a.getQuLossDe());
                                                this.procWinDe.add(a.getProcWinDe());
                                                this.genProf.add(a.getGenProf());
                                                this.genLoss.add(a.getGenLoss());
                                                this.avProf.add(a.getAvProf());
                                                this.avLoss.add(a.getAvLoss());
                                                this.profFac.add(a.getProfFac());
                                                this.recovFac.add(a.getRecovFac());
                                                this.winKoo.add(a.getWinKoo());
                                                this.quDealFilter.add(a.getQuDealFilter());
                                                this.comGen.add(a.getComGen());
                                                this.curResLo.add(a.getCurResLo());
                                                this.curResSh.add(a.getCurResSh());
                                                parameters[0] = i;
                                                parameters[1] = j;
                                                parameters[2] = k;
                                                parameters[3] = l;
                                                parameters[4] = m;
                                                parameters[5] = n;
                                                parameters[6] = o;
                                                parameters[7] = p;
                                                parameters[8] = q;
                                                this.parameters.add(parameters);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else if (parNum == 10) {
            for (double i = paramRange.getParRange1()[0]; i <= paramRange.getParRange1()[1]; i = i + paramRange.getParRange1()[2]) {
                for (double j = paramRange.getParRange2()[0]; j <= paramRange.getParRange2()[1]; j = j + paramRange.getParRange2()[2]) {
//					System.out.println(a.getTicker()[1] + " " + weekNum + " " + i + " " + j);
                    for (double k = paramRange.getParRange3()[0]; k <= paramRange.getParRange3()[1]; k = k + paramRange.getParRange3()[2]) {
                        for (double l = paramRange.getParRange4()[0]; l <= paramRange.getParRange4()[1]; l = l + paramRange.getParRange4()[2]) {
                            for (double m = paramRange.getParRange4()[0]; m <= paramRange.getParRange4()[1]; m = m + paramRange.getParRange4()[2]) {
                                for (double n = paramRange.getParRange4()[0]; n <= paramRange.getParRange4()[1]; n = n + paramRange.getParRange4()[2]) {
                                    for (double o = paramRange.getParRange4()[0]; o <= paramRange.getParRange4()[1]; o = o + paramRange.getParRange4()[2]) {
                                        for (double p = paramRange.getParRange4()[0]; p <= paramRange.getParRange4()[1]; p = p + paramRange.getParRange4()[2]) {
                                            for (double q = paramRange.getParRange4()[0]; q <= paramRange.getParRange4()[1]; q = q + paramRange.getParRange4()[2]) {
                                                for (double r = paramRange.getParRange4()[0]; r <= paramRange.getParRange4()[1]; r = r + paramRange.getParRange4()[2]) {
                                                    Double[] parameters = new Double[10];
                                                    b.setPar(i, j, k, l, m, n, o, p, q, r, paramRange.getParName1(), paramRange.getParName2(), paramRange.getParName3(), paramRange.getParName4(), paramRange.getParName5(), paramRange.getParName6(), paramRange.getParName7(), paramRange.getParName8(), paramRange.getParName9(), paramRange.getParName10());
                                                    GlobalTest.robFunc(a, b);
                                                    a.counter();
                                                    this.genRes.add(a.getGenRes());
                                                    this.maxLoss.add(a.getMaxLoss());
                                                    this.maxProf.add(a.getMaxProf());
                                                    this.quDe.add(a.getQuDe());
                                                    this.quProfDe.add(a.getQuProfDe());
                                                    this.quLossDe.add(a.getQuLossDe());
                                                    this.procWinDe.add(a.getProcWinDe());
                                                    this.genProf.add(a.getGenProf());
                                                    this.genLoss.add(a.getGenLoss());
                                                    this.avProf.add(a.getAvProf());
                                                    this.avLoss.add(a.getAvLoss());
                                                    this.profFac.add(a.getProfFac());
                                                    this.recovFac.add(a.getRecovFac());
                                                    this.winKoo.add(a.getWinKoo());
                                                    this.quDealFilter.add(a.getQuDealFilter());
                                                    this.comGen.add(a.getComGen());
                                                    this.curResLo.add(a.getCurResLo());
                                                    this.curResSh.add(a.getCurResSh());
                                                    parameters[0] = i;
                                                    parameters[1] = j;
                                                    parameters[2] = k;
                                                    parameters[3] = l;
                                                    parameters[4] = m;
                                                    parameters[5] = n;
                                                    parameters[6] = o;
                                                    parameters[7] = p;
                                                    parameters[8] = q;
                                                    parameters[9] = r;
                                                    this.parameters.add(parameters);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public Parameter[] theBestPar(int learnWeeks) {
        Parameter[] params = new Parameter[5];
        int quDealFilter = 0;
        Parameter bestGenRes = new Parameter();
        Parameter bestProcWinDe = new Parameter();
        Parameter bestProfFac = new Parameter();
        Parameter bestRecovFac = new Parameter();
        Parameter bestWinKoo = new Parameter();
        if (learnWeeks == 1) {
            quDealFilter = GlobalTest.FILT_1_WEEKS;
        } else if (learnWeeks == 2) {
            quDealFilter = GlobalTest.FILT_2_WEEKS;
        } else if (learnWeeks == 4) {
            quDealFilter = GlobalTest.FILT_4_WEEKS;
        }

        for (int i = 0; i < this.sortByGenRes.length; i++) {
            if (this.quDealFilter.get(sortByGenRes[i]) >= quDealFilter) {
                switch (this.parameters.get(0).length) {
                    case 1:
                        Double[] pars0 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars0[0], this.paramNames[0]);
                        break;
                    case 2:
                        Double[] pars1 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars1[0], pars1[1], this.paramNames[0], this.paramNames[1]);
                        break;
                    case 3:
                        Double[] pars2 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars2[0], pars2[1], pars2[2], this.paramNames[0], this.paramNames[1], this.paramNames[2]);
                        break;
                    case 4:
                        Double[] pars3 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars3[0], pars3[1], pars3[2], pars3[3], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3]);
                        break;
                    case 5:
                        Double[] pars4 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars4[0], pars4[1], pars4[2], pars4[3], pars4[4], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4]);
                        break;
                    case 6:
                        Double[] pars5 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars5[0], pars5[1], pars5[2], pars5[3], pars5[4], pars5[5],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5]);
                        break;
                    case 7:
                        Double[] pars6 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars6[0], pars6[1], pars6[2], pars6[3], pars6[4], pars6[5], pars6[6],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6]);
                        break;
                    case 8:
                        Double[] pars7 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars7[0], pars7[1], pars7[2], pars7[3], pars7[4], pars7[5], pars7[6], pars7[7],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7]);
                        break;
                    case 9:
                        Double[] pars8 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars8[0], pars8[1], pars8[2], pars8[3], pars8[4], pars8[5], pars8[6], pars8[7], pars8[8],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8]);
                        break;
                    case 10:
                        Double[] pars9 = this.parameters.get(sortByGenRes[i]);
                        bestGenRes.setPar(pars9[0], pars9[1], pars9[2], pars9[3], pars9[4], pars9[5], pars9[6], pars9[7], pars9[8], pars9[9],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8], this.paramNames[9]);
                        break;
                }
                break;
            }
        }
        for (int i = 0; i < this.sortByProcWinDe.length; i++) {
            if (this.quDealFilter.get(sortByProcWinDe[i]) >= quDealFilter) {
                switch (this.parameters.get(0).length) {
                    case 1:
                        Double[] pars0 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars0[0], this.paramNames[0]);
                        break;
                    case 2:
                        Double[] pars1 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars1[0], pars1[1], this.paramNames[0], this.paramNames[1]);
                        break;
                    case 3:
                        Double[] pars2 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars2[0], pars2[1], pars2[2], this.paramNames[0], this.paramNames[1], this.paramNames[2]);
                        break;
                    case 4:
                        Double[] pars3 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars3[0], pars3[1], pars3[2], pars3[3], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3]);
                        break;
                    case 5:
                        Double[] pars4 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars4[0], pars4[1], pars4[2], pars4[3], pars4[4], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4]);
                        break;
                    case 6:
                        Double[] pars5 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars5[0], pars5[1], pars5[2], pars5[3], pars5[4], pars5[5],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5]);
                        break;
                    case 7:
                        Double[] pars6 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars6[0], pars6[1], pars6[2], pars6[3], pars6[4], pars6[5], pars6[6],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6]);
                        break;
                    case 8:
                        Double[] pars7 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars7[0], pars7[1], pars7[2], pars7[3], pars7[4], pars7[5], pars7[6], pars7[7],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7]);
                        break;
                    case 9:
                        Double[] pars8 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars8[0], pars8[1], pars8[2], pars8[3], pars8[4], pars8[5], pars8[6], pars8[7], pars8[8],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8]);
                        break;
                    case 10:
                        Double[] pars9 = this.parameters.get(sortByProcWinDe[i]);
                        bestProcWinDe.setPar(pars9[0], pars9[1], pars9[2], pars9[3], pars9[4], pars9[5], pars9[6], pars9[7], pars9[8], pars9[9],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8], this.paramNames[9]);
                        break;
                }
                break;
            }
        }
        for (int i = 0; i < this.sortByProfFac.length; i++) {
            if (this.quDealFilter.get(sortByProfFac[i]) >= quDealFilter) {
                switch (this.parameters.get(0).length) {
                    case 1:
                        Double[] pars0 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars0[0], this.paramNames[0]);
                        break;
                    case 2:
                        Double[] pars1 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars1[0], pars1[1], this.paramNames[0], this.paramNames[1]);
                        break;
                    case 3:
                        Double[] pars2 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars2[0], pars2[1], pars2[2], this.paramNames[0], this.paramNames[1], this.paramNames[2]);
                        break;
                    case 4:
                        Double[] pars3 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars3[0], pars3[1], pars3[2], pars3[3], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3]);
                        break;
                    case 5:
                        Double[] pars4 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars4[0], pars4[1], pars4[2], pars4[3], pars4[4], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4]);
                        break;
                    case 6:
                        Double[] pars5 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars5[0], pars5[1], pars5[2], pars5[3], pars5[4], pars5[5],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5]);
                        break;
                    case 7:
                        Double[] pars6 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars6[0], pars6[1], pars6[2], pars6[3], pars6[4], pars6[5], pars6[6],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6]);
                        break;
                    case 8:
                        Double[] pars7 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars7[0], pars7[1], pars7[2], pars7[3], pars7[4], pars7[5], pars7[6], pars7[7],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7]);
                        break;
                    case 9:
                        Double[] pars8 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars8[0], pars8[1], pars8[2], pars8[3], pars8[4], pars8[5], pars8[6], pars8[7], pars8[8],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8]);
                        break;
                    case 10:
                        Double[] pars9 = this.parameters.get(sortByProfFac[i]);
                        bestProfFac.setPar(pars9[0], pars9[1], pars9[2], pars9[3], pars9[4], pars9[5], pars9[6], pars9[7], pars9[8], pars9[9],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8], this.paramNames[9]);
                        break;
                }
                break;
            }
        }
        for (int i = 0; i < this.sortByRecovFac.length; i++) {
            if (this.quDealFilter.get(sortByRecovFac[i]) >= quDealFilter) {
                switch (this.parameters.get(0).length) {
                    case 1:
                        Double[] pars0 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars0[0], this.paramNames[0]);
                        break;
                    case 2:
                        Double[] pars1 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars1[0], pars1[1], this.paramNames[0], this.paramNames[1]);
                        break;
                    case 3:
                        Double[] pars2 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars2[0], pars2[1], pars2[2], this.paramNames[0], this.paramNames[1], this.paramNames[2]);
                        break;
                    case 4:
                        Double[] pars3 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars3[0], pars3[1], pars3[2], pars3[3], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3]);
                        break;
                    case 5:
                        Double[] pars4 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars4[0], pars4[1], pars4[2], pars4[3], pars4[4], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4]);
                        break;
                    case 6:
                        Double[] pars5 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars5[0], pars5[1], pars5[2], pars5[3], pars5[4], pars5[5],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5]);
                        break;
                    case 7:
                        Double[] pars6 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars6[0], pars6[1], pars6[2], pars6[3], pars6[4], pars6[5], pars6[6],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6]);
                        break;
                    case 8:
                        Double[] pars7 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars7[0], pars7[1], pars7[2], pars7[3], pars7[4], pars7[5], pars7[6], pars7[7],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7]);
                        break;
                    case 9:
                        Double[] pars8 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars8[0], pars8[1], pars8[2], pars8[3], pars8[4], pars8[5], pars8[6], pars8[7], pars8[8],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8]);
                        break;
                    case 10:
                        Double[] pars9 = this.parameters.get(sortByRecovFac[i]);
                        bestRecovFac.setPar(pars9[0], pars9[1], pars9[2], pars9[3], pars9[4], pars9[5], pars9[6], pars9[7], pars9[8], pars9[9],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8], this.paramNames[9]);
                        break;
                }
                break;
            }

        }
        for (int i = 0; i < this.sortByWinKoo.length; i++) {
            if (this.quDealFilter.get(sortByWinKoo[i]) >= quDealFilter) {
                switch (this.parameters.get(0).length) {
                    case 1:
                        Double[] pars0 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars0[0], this.paramNames[0]);
                        break;
                    case 2:
                        Double[] pars1 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars1[0], pars1[1], this.paramNames[0], this.paramNames[1]);
                        break;
                    case 3:
                        Double[] pars2 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars2[0], pars2[1], pars2[2], this.paramNames[0], this.paramNames[1], this.paramNames[2]);
                        break;
                    case 4:
                        Double[] pars3 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars3[0], pars3[1], pars3[2], pars3[3], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3]);
                        break;
                    case 5:
                        Double[] pars4 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars4[0], pars4[1], pars4[2], pars4[3], pars4[4], this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4]);
                        break;
                    case 6:
                        Double[] pars5 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars5[0], pars5[1], pars5[2], pars5[3], pars5[4], pars5[5],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5]);
                        break;
                    case 7:
                        Double[] pars6 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars6[0], pars6[1], pars6[2], pars6[3], pars6[4], pars6[5], pars6[6],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6]);
                        break;
                    case 8:
                        Double[] pars7 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars7[0], pars7[1], pars7[2], pars7[3], pars7[4], pars7[5], pars7[6], pars7[7],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7]);
                        break;
                    case 9:
                        Double[] pars8 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars8[0], pars8[1], pars8[2], pars8[3], pars8[4], pars8[5], pars8[6], pars8[7], pars8[8],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8]);
                        break;
                    case 10:
                        Double[] pars9 = this.parameters.get(sortByWinKoo[i]);
                        bestWinKoo.setPar(pars9[0], pars9[1], pars9[2], pars9[3], pars9[4], pars9[5], pars9[6], pars9[7], pars9[8], pars9[9],
                                this.paramNames[0], this.paramNames[1], this.paramNames[2], this.paramNames[3], this.paramNames[4], this.paramNames[5], this.paramNames[6], this.paramNames[7], this.paramNames[8], this.paramNames[9]);
                        break;
                }
                break;
            }
        }
        params[0] = bestGenRes;
        params[1] = bestProcWinDe;
        params[2] = bestProfFac;
        params[3] = bestRecovFac;
        params[4] = bestWinKoo;

        return params;
    }



}
