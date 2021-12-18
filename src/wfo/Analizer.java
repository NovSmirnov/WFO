package wfo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class Analizer {

    protected String[] robNames; // Названия алгоритмов
    protected String[] tickers; // Тикеры
    protected int[] timeFrames; // Таймфреймы
    protected String[] strateges; // Стратегии обучения
    protected double[] profits; // Доходность в единицах котировки
    protected double[] rubProfits; // Доходность в рублях
    protected double[] goProfits; // Доходность к ГО
    protected double[] avProfits; // Доходность к среднему
    protected double[] deviations; // Корректирующий коэфициент, указывающий на приближение к линейному графику
    protected double[] integratedInd; // Интегрированный показатель (пирбыль в % к ГО умноженная на корректирующий коэфициент
    protected double[][][] parRanges; // Диапазоны параметров
    protected int[] startDates; // Дата старта
    protected int[] finishDates; // Дата окончания
    protected String[] comments; // Коментарий к алгоритму

    /**
     * Сортированный массив из множества, содержащей тикеры участвующие в работе.
     */
    protected String[] setOfTickers;
    /**
     * Средняя доходность к гарантийному обеспечению по каждому тикеру.
     */
    protected double[] avGoProfits;
    /**
     * Средняя доходность к средней цене актива по каждому тикеру.
     */
    protected double[] avAvProfits;
    /**
     * Средний условный коэфициент детерминации по каждому тикеру.
     */
    protected double[] avDeviations;
    /**
     * Средний интегрированный показатель по каждому тикеру.
     */
    protected double[] avIntegr;
    /**
     * Масcив со значениями процента стратегий с положительной доходностью по каждому тикеру.
     */
    protected double[] perPositProf;


    protected int[] sortByIntegrated; // индексы отсортированные по убыванию для интегрированного показателя


    public void setData(WFOTester[] data) {
        List<String> robNames = new ArrayList<>();
        List<String> tickers = new ArrayList<>();
        List<Integer> timeFrames = new ArrayList<>();
        List<String> strateges = new ArrayList<>();
        List<Double> profits = new ArrayList<>();
        List<Double> rubProfits = new ArrayList<>();
        List<Double> goProfits = new ArrayList<>();
        List<Double> avProfits = new ArrayList<>();
        List<Double> deviations = new ArrayList<>();
        List<Double> integratedInd = new ArrayList<>();
        List<Double[][]> parRanges = new ArrayList<>();
        List<Integer> startDates = new ArrayList<>();
        List<Integer> finishDates = new ArrayList<>();
        List<String> comments = new ArrayList<>();

        for (WFOTester object : data) {
            for (int i = 0; i < 15; i++) {
                robNames.add(object.getRobName());
                tickers.add(object.getTicker());
                timeFrames.add(object.getTimeFrame());
                switch (i) {
                    case 0: {
                        strateges.add("W1_Profit");
                        profits.add(object.w1ProfProfRes[object.w1ProfProfRes.length - 1]);
                        rubProfits.add(object.w1ProfProfRes[object.w1ProfProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w1ProfProfRes[object.w1ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w1ProfProfRes[object.w1ProfProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w1ProfProfRes));
                        integratedInd.add((object.w1ProfProfRes[object.w1ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w1ProfProfRes));
                        break;
                    }
                    case 1: {
                        strateges.add("W1_Profit-Factor");
                        profits.add(object.w1ProfFacProfRes[object.w1ProfFacProfRes.length - 1]);
                        rubProfits.add(object.w1ProfFacProfRes[object.w1ProfFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w1ProfFacProfRes[object.w1ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w1ProfFacProfRes[object.w1ProfFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w1ProfFacProfRes));
                        integratedInd.add((object.w1ProfFacProfRes[object.w1ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w1ProfFacProfRes));
                        break;
                    }
                    case 2: {
                        strateges.add("W1_Recovery-Factor");
                        profits.add(object.w1RecovFacProfRes[object.w1RecovFacProfRes.length - 1]);
                        rubProfits.add(object.w1RecovFacProfRes[object.w1RecovFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w1RecovFacProfRes[object.w1RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w1RecovFacProfRes[object.w1RecovFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w1RecovFacProfRes));
                        integratedInd.add((object.w1RecovFacProfRes[object.w1RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w1RecovFacProfRes));
                        break;
                    }
                    case 3: {
                        strateges.add("W1_Win-Coef");
                        profits.add(object.w1WinKooProfRes[object.w1WinKooProfRes.length - 1]);
                        rubProfits.add(object.w1WinKooProfRes[object.w1WinKooProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w1WinKooProfRes[object.w1WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w1WinKooProfRes[object.w1WinKooProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w1WinKooProfRes));
                        integratedInd.add((object.w1WinKooProfRes[object.w1WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w1WinKooProfRes));
                        break;
                    }
                    case 4: {
                        strateges.add("W1_WinDeals-Percent");
                        profits.add(object.w1ProcWinDeProfRes[object.w1ProcWinDeProfRes.length - 1]);
                        rubProfits.add(object.w1ProcWinDeProfRes[object.w1ProcWinDeProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w1ProcWinDeProfRes[object.w1ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w1ProcWinDeProfRes[object.w1ProcWinDeProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w1ProcWinDeProfRes));
                        integratedInd.add((object.w1ProcWinDeProfRes[object.w1ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w1ProcWinDeProfRes));
                        break;
                    }
                    case 5: {
                        strateges.add("W2_Profit");
                        profits.add(object.w2ProfProfRes[object.w2ProfProfRes.length - 1]);
                        rubProfits.add(object.w2ProfProfRes[object.w2ProfProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w2ProfProfRes[object.w2ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w2ProfProfRes[object.w2ProfProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w2ProfProfRes));
                        integratedInd.add((object.w2ProfProfRes[object.w2ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w2ProfProfRes));
                        break;
                    }
                    case 6: {
                        strateges.add("W2_Profit-Factor");
                        profits.add(object.w2ProfFacProfRes[object.w2ProfFacProfRes.length - 1]);
                        rubProfits.add(object.w2ProfFacProfRes[object.w2ProfFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w2ProfFacProfRes[object.w2ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w2ProfFacProfRes[object.w2ProfFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w2ProfFacProfRes));
                        integratedInd.add((object.w2ProfFacProfRes[object.w2ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w2ProfFacProfRes));
                        break;
                    }
                    case 7: {
                        strateges.add("W2_Recovery-Factor");
                        profits.add(object.w2RecovFacProfRes[object.w2RecovFacProfRes.length - 1]);
                        rubProfits.add(object.w2RecovFacProfRes[object.w2RecovFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w2RecovFacProfRes[object.w2RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w2RecovFacProfRes[object.w2RecovFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w2RecovFacProfRes));
                        integratedInd.add((object.w2RecovFacProfRes[object.w2RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w2RecovFacProfRes));
                        break;
                    }
                    case 8: {
                        strateges.add("W2_Win-Coef");
                        profits.add(object.w2WinKooProfRes[object.w2WinKooProfRes.length - 1]);
                        rubProfits.add(object.w2WinKooProfRes[object.w2WinKooProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w2WinKooProfRes[object.w2WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w2WinKooProfRes[object.w2WinKooProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w2WinKooProfRes));
                        integratedInd.add((object.w2WinKooProfRes[object.w2WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w2WinKooProfRes));
                        break;
                    }
                    case 9: {
                        strateges.add("W2_WinDeals-Percent");
                        profits.add(object.w2ProcWinDeProfRes[object.w2ProcWinDeProfRes.length - 1]);
                        rubProfits.add(object.w2ProcWinDeProfRes[object.w2ProcWinDeProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w2ProcWinDeProfRes[object.w2ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w2ProcWinDeProfRes[object.w2ProcWinDeProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w2ProcWinDeProfRes));
                        integratedInd.add((object.w2ProcWinDeProfRes[object.w2ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w2ProcWinDeProfRes));
                        break;
                    }
                    case 10: {
                        strateges.add("W4_Profit");
                        profits.add(object.w4ProfProfRes[object.w4ProfProfRes.length - 1]);
                        rubProfits.add(object.w4ProfProfRes[object.w4ProfProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w4ProfProfRes[object.w4ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w4ProfProfRes[object.w4ProfProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w4ProfProfRes));
                        integratedInd.add((object.w4ProfProfRes[object.w4ProfProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w4ProfProfRes));
                        break;
                    }
                    case 11: {
                        strateges.add("W4_Profit-Factor");
                        profits.add(object.w4ProfFacProfRes[object.w4ProfFacProfRes.length - 1]);
                        rubProfits.add(object.w4ProfFacProfRes[object.w4ProfFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w4ProfFacProfRes[object.w4ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w4ProfFacProfRes[object.w4ProfFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w4ProfFacProfRes));
                        integratedInd.add((object.w4ProfFacProfRes[object.w4ProfFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w4ProfFacProfRes));
                        break;
                    }
                    case 12: {
                        strateges.add("W4_Recovery-Factor");
                        profits.add(object.w4RecovFacProfRes[object.w4RecovFacProfRes.length - 1]);
                        rubProfits.add(object.w4RecovFacProfRes[object.w4RecovFacProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w4RecovFacProfRes[object.w4RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w4RecovFacProfRes[object.w4RecovFacProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w4RecovFacProfRes));
                        integratedInd.add((object.w4RecovFacProfRes[object.w4RecovFacProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w4RecovFacProfRes));
                        break;
                    }
                    case 13: {
                        strateges.add("W4_Win-Coef");
                        profits.add(object.w4WinKooProfRes[object.w4WinKooProfRes.length - 1]);
                        rubProfits.add(object.w4WinKooProfRes[object.w4WinKooProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w4WinKooProfRes[object.w4WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w4WinKooProfRes[object.w4WinKooProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w4WinKooProfRes));
                        integratedInd.add((object.w4WinKooProfRes[object.w4WinKooProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w4WinKooProfRes));
                        break;
                    }
                    case 14: {
                        strateges.add("W4_WinDeals-Percent");
                        profits.add(object.w4ProcWinDeProfRes[object.w4ProcWinDeProfRes.length - 1]);
                        rubProfits.add(object.w4ProcWinDeProfRes[object.w4ProcWinDeProfRes.length - 1] * object.getUnitPrice());
                        goProfits.add((object.w4ProcWinDeProfRes[object.w4ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100);
                        avProfits.add((object.w4ProcWinDeProfRes[object.w4ProcWinDeProfRes.length - 1]) / object.avPrice * 100);
                        deviations.add(r2(object.w4ProcWinDeProfRes));
                        integratedInd.add((object.w4ProcWinDeProfRes[object.w4ProcWinDeProfRes.length - 1] * object.getUnitPrice()) / object.getGSecurity() * 100 * r2(object.w4ProcWinDeProfRes));
                        break;
                    }
                }
                parRanges.add(getParams(object.parRanges));
                startDates.add(object.getStartDate());
                finishDates.add(object.getFinishDate());
                comments.add(object.getComment());
            }


        }
        System.out.println(parRanges.size());
        String[] robNamesArr = new String[profits.size()];
        String[] tickersArr = new String[profits.size()];
        int[] timeFramesArr = new int[profits.size()];
        String[] strategesArr = new String[profits.size()];
        double[] profitsArr = new double[profits.size()];
        double[] rubProfitsArr = new double[profits.size()];
        double[] goProfitsArr = new double[profits.size()];
        double[] avProfitsArr = new double[profits.size()];
        double[] deviationsArr = new double[profits.size()];
        double[] integratedIndArr = new double[profits.size()];
        double[][][] parRangesArr = new double[parRanges.size()][parRanges.get(0).length][parRanges.get(0)[0].length];
        int[] startDatesArr = new int[profits.size()];
        int[] finishDatesArr = new int[profits.size()];
        String[] commentsArr = new String[profits.size()];


        for (int i = 0; i < profits.size(); i++) {
            robNamesArr[i] = robNames.get(i);
            tickersArr[i] = tickers.get(i);
            timeFramesArr[i] = timeFrames.get(i);
            strategesArr[i] = strateges.get(i);
            profitsArr[i] = profits.get(i);
            rubProfitsArr[i] = rubProfits.get(i);
            goProfitsArr[i] = goProfits.get(i);
            avProfitsArr[i] = avProfits.get(i);
            deviationsArr[i] = deviations.get(i);
            integratedIndArr[i] = integratedInd.get(i);
            startDatesArr[i] = startDates.get(i);
            finishDatesArr[i] = finishDates.get(i);
            commentsArr[i] = comments.get(i);
            for (int j = 0; j < getParams(data[i / 15].parRanges).length; j++) {
                for (int k = 0; k < 3; k++) {
                    parRangesArr[i][j][k] = parRanges.get(i)[j][k];
                }
            }
        }
        this.robNames = robNamesArr;
        this.tickers = tickersArr;
        this.timeFrames = timeFramesArr;
        this.strateges = strategesArr;
        this.profits = profitsArr;
        this.rubProfits = rubProfitsArr;
        this.goProfits = goProfitsArr;
        this.avProfits = avProfitsArr;
        this.deviations = deviationsArr;
        this.integratedInd = integratedIndArr;
        this.parRanges = parRangesArr;
        this.startDates = startDatesArr;
        this.finishDates = finishDatesArr;
        this.comments = commentsArr;
    }

    /**
     * @param data Числовой ряд для построения графика накопленного дохода
     * @return Коэфициент приближения графика накопленного дохода к линейной функции
     */
    public double r2(double[] data) {
        double[] func = new double[data.length];
        double[] differ2 = new double[data.length];
        double deter = 0.0;
        for (int i = 0; i < data.length; i++) {
            func[i] = data[data.length - 1] / data.length * i;
            differ2[i] = (func[i] - data[i]) * (func[i] - data[i]);
        }
        double sumDif = 0;
        for (int i = 0; i < data.length; i++) {
            sumDif += differ2[i];
        }
        deter = 1 - Math.abs(Math.sqrt(sumDif / data.length) / data[data.length - 1]);
        return deter;
    }

    public Double[][] getParams(ParRange params) {
        int n = params.checkQuPar();
        double[][] parArr = new double[n][3];
        switch (n) {
            case 1: {
                parArr[0] = params.getParRange1();
                break;
            }
            case 2: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                break;
            }
            case 3: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                break;
            }
            case 4: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                break;
            }
            case 5: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                break;
            }
            case 6: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                parArr[5] = params.getParRange6();
                break;
            }
            case 7: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                parArr[5] = params.getParRange6();
                parArr[6] = params.getParRange7();
                break;
            }
            case 8: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                parArr[5] = params.getParRange6();
                parArr[6] = params.getParRange7();
                parArr[7] = params.getParRange8();
                break;
            }
            case 9: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                parArr[5] = params.getParRange6();
                parArr[6] = params.getParRange7();
                parArr[7] = params.getParRange8();
                parArr[8] = params.getParRange9();
                break;
            }
            case 10: {
                parArr[0] = params.getParRange1();
                parArr[1] = params.getParRange2();
                parArr[2] = params.getParRange3();
                parArr[3] = params.getParRange4();
                parArr[4] = params.getParRange5();
                parArr[5] = params.getParRange6();
                parArr[6] = params.getParRange7();
                parArr[7] = params.getParRange8();
                parArr[8] = params.getParRange9();
                parArr[9] = params.getParRange10();
                break;
            }
        }
        Double[][] arr = new Double[n][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = parArr[i][j];
            }
        }
        return arr;
    }

    public void sortBy() {
        int[] indListIntegr = new int[this.integratedInd.length];
        double[] integratedInd = new double[this.integratedInd.length];

        for (int i = 0; i < this.integratedInd.length; i++) {
            indListIntegr[i] = i;
            integratedInd[i] = this.integratedInd[i];
        }
        for (int j = 0; j < indListIntegr.length - 1; j++) {
            for (int k = 0; k < indListIntegr.length - j - 1; k++) {
                if (integratedInd[k] < integratedInd[k + 1]) {
                    double temp1 = integratedInd[k];
                    integratedInd[k] = integratedInd[k + 1];
                    integratedInd[k + 1] = temp1;
                    int indexTemp1 = indListIntegr[k];
                    indListIntegr[k] = indListIntegr[k + 1];
                    indListIntegr[k + 1] = indexTemp1;
                }
            }
        }
        this.sortByIntegrated = indListIntegr;
    }

    public void sortedIntegrToFile(String pathToFile) throws IOException {
        StringBuilder parHeader = new StringBuilder();
        for (int i = 0; i < parRanges[0].length; i++) {
            parHeader.append(";").append("Набор параметров ").append(String.valueOf(i + 1));
        }
        String headLine = new String("");
        headLine = "Имя алгоритма" + ";" + "Тикер" + ";" + "Тайм фрейм" +
                ";" + "Стратегия переобучения" + ";" + "Доходность в единицах котировки" + ";" + "Доходность в рублях" +
                ";" + "Доходность к ГО в % " + ";" + "Доходность к средней цене в %" + ";" + "Корректирующий коэфициент" +
                ";" + "Интегрированный показатель" + ";" + "Дата старта" + ";" + "Дата окончания" +
                ";" + "Комментарий" + parHeader + "\n";
        String line = new String("");
        FileWriter writer = new FileWriter(pathToFile, Charset.forName("cp1251"));
        writer.write(headLine.toString());

        for (int i = 0; i < this.profits.length; i++) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat df = new DecimalFormat("########.##", symbols);
            StringBuilder parameters = new StringBuilder();
            for (int j = 0; j < parRanges[i].length; j++) {
                parameters.append(";").append(String.valueOf(parRanges[sortByIntegrated[i]][j][0]) + "-" + String.valueOf(parRanges[sortByIntegrated[i]][j][1]) + "-" + String.valueOf(parRanges[sortByIntegrated[i]][j][2]));
            }
            line = this.robNames[sortByIntegrated[i]] + ";" + this.tickers[sortByIntegrated[i]] + ";" + this.timeFrames[sortByIntegrated[i]] + ";" +
                    this.strateges[sortByIntegrated[i]] + ";" + df.format(this.profits[sortByIntegrated[i]]) + ";" + df.format(this.rubProfits[sortByIntegrated[i]]) + ";" + df.format(this.goProfits[sortByIntegrated[i]]) + ";" +
                    df.format(this.avProfits[sortByIntegrated[i]]) + ";" + df.format(this.deviations[sortByIntegrated[i]]) + ";" + df.format(this.integratedInd[sortByIntegrated[i]]) + ";" + this.startDates[sortByIntegrated[i]] +
                    ";" + this.finishDates[sortByIntegrated[i]] + ";" + this.comments[sortByIntegrated[i]] + parameters + "\n";
            writer.write(line.toString());

        }
        writer.close();
    }

    public void analitic() {
        SortedSet tickers = new TreeSet();
        for (int i = 0; i < this.tickers.length; i++) {
            tickers.add(this.tickers[i]);
        }
        this.setOfTickers = (String[])tickers.toArray(new String[tickers.size()]);
        this.avGoProfits = new double[setOfTickers.length];
        this.avAvProfits = new double[setOfTickers.length];
        this.avDeviations = new double[setOfTickers.length];
        this.avIntegr = new double[setOfTickers.length];
        this.perPositProf = new double[setOfTickers.length];
        for (int i = 0; i < this.setOfTickers.length; i++) {
            List<Double> goProfits = new ArrayList<>();
            List<Double> avProfits = new ArrayList<>();
            List<Double> deviations = new ArrayList<>();
            List<Double> integr = new ArrayList<>();
            double sumGoProfits = 0;
            double sumAvProfits = 0;
            double sumDeviations = 0;
            double sumIntegr = 0;
            int positProfCounter = 0;
            for (int j = 0; j < this.goProfits.length; j++) {
                if (this.setOfTickers[i].equals(this.tickers[j])) {
                    goProfits.add(this.goProfits[j]);
                    sumGoProfits += this.goProfits[j];
                    avProfits.add(this.avProfits[j]);
                    sumAvProfits += this.avProfits[j];
                    if (this.avProfits[j] >= 0) {
                        positProfCounter++;
                    }
                    deviations.add(this.deviations[j]);
                    sumDeviations += this.deviations[j];
                    integr.add(this.integratedInd[j]);
                    sumIntegr += this.integratedInd[j];
                }
            }
            this.perPositProf[i] = (double) positProfCounter / avProfits.size() * 100;
            this.avGoProfits[i] = sumGoProfits / goProfits.size();
            this.avAvProfits[i] =  sumAvProfits / avProfits.size();
            this.avDeviations[i] = sumDeviations / deviations.size();
            this.avIntegr[i] = sumIntegr / integr.size();
        }
    }

    public void analiticsToFile(String pathToFile) throws IOException {
        StringBuilder parHeader = new StringBuilder();
        for (int i = 0; i < parRanges[0].length; i++) {
            parHeader.append(";").append("Набор параметров ").append(String.valueOf(i + 1));
        }
        String headLine = new String("");
        headLine = "Тикер" + ";" + "Средняя доходность к ГО в % " + ";" + "Средняя доходность к средней цене в %" +
                ";" + "Средний корректирующий коэфициент" + ";" + "Средний интегрированный показатель" +
                ";" + "Процент положительной доходности" + "\n";
        String line = new String("");
        FileWriter writer = new FileWriter(pathToFile, Charset.forName("cp1251"));
        writer.write(headLine.toString());

        for (int i = 0; i < this.setOfTickers.length; i++) {
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat df = new DecimalFormat("########.##", symbols);
            StringBuilder parameters = new StringBuilder();
            line = this.setOfTickers[i] + ";" + df.format(this.avGoProfits[i]) + ";" + df.format(this.avAvProfits[i]) + ";" +
                    df.format(this.avDeviations[i]) + ";" + df.format(avIntegr[i]) + ";" + df.format(perPositProf[i]) + "\n";
            writer.write(line.toString());
        }
        writer.close();
    }
}
