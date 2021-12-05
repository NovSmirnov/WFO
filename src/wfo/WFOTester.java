package wfo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class WFOTester {
    // Блок полей первой таблицы тестирования (75 граф, каждое поле - одна графа)
    protected double[] w1ProfProf; // Прибыль на 1 неделе обучения по максимальной прибыли
    protected double[] w1ProfMinDown; // Максимальная просадка на 1 неделе обучения по максимальной прибыли
    protected double[] w1ProfMaxUp; // Максимальный выброс вверх на 1 неделе обучения по максимальной прибыли
    protected double[] w1ProfProfFac; // Профит фактор на 1 неделе обучения по максимальной прибыли
    protected double[] w1ProfRecovFac; // Фактор восстановления на 1 неделе обучения по максимальной прибыли
    protected double[] w1ProfFacProf; // Прибыль на 1 неделе обучения по профит фактору
    protected double[] w1ProfFacMinDown; // Максимальная просадка на 1 неделе обучения по профит фактору
    protected double[] w1ProfFacMaxUp; // Максимальный выброс вверх на 1 неделе обучения по профит фактору
    protected double[] w1ProfFacProfFac; // Профит фактор на 1 неделе обучения по профит фактору
    protected double[] w1ProfFacRecovFac; // Фактор восстановления на 1 неделе обучения по профит фактору
    protected double[] w1RecovFacProf; // Прибыль на 1 неделе обучения по фактору восстановления
    protected double[] w1RecovFacFacMinDown; // Максимальная просадка на 1 неделе обучения по фактору восстановления
    protected double[] w1RecovFacFacMaxUp; // Максимальный выброс вверх на 1 неделе обучения по фактору восстановления
    protected double[] w1RecovFacFacProfFac; // Профит фактор на 1 неделе обучения по фактору восстановления
    protected double[] w1RecovFacFacRecovFac; // Фактор восстановления на 1 неделе обучения по фактору восстановления
    protected double[] w1WinKooProf; // Прибыль на 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1WinKooMinDown; // Максимальная просадка на 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1WinKooMaxUp; // Максимальный выброс вверх на 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1WinKooProfFac; // Профит фактор на 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1WinKooRecovFac; // Фактор восстановления на 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1ProcWinDeProf; // Прибыль на 1 неделе обучения по проценту выиграных сделок
    protected double[] w1ProcWinDeMinDown; // Максимальная просадка на 1 неделе обучения по проценту выиграных сделок
    protected double[] w1ProcWinDeMaxUp; // Максимальный выброс вверх на 1 неделе обучения по проценту выиграных сделок
    protected double[] w1ProcWinDeProfFac; // Профит фактор на 1 неделе обучения по проценту выиграных сделок
    protected double[] w1ProcWinDeRecovFac; // Фактор восстановления на 1 неделе обучения по проценту выиграных сделок
    protected double[] w2ProfProf; // Прибыль на 2 неделях обучения по максимальной прибыли
    protected double[] w2ProfMinDown; // Максимальная просадка на 2 неделях обучения по максимальной прибыли
    protected double[] w2ProfMaxUp; // Максимальный выброс вверх на 2 неделях обучения по максимальной прибыли
    protected double[] w2ProfProfFac; // Профит фактор на 2 неделях обучения по максимальной прибыли
    protected double[] w2ProfRecovFac; // Фактор восстановления на 2 неделях обучения по максимальной прибыли
    protected double[] w2ProfFacProf; // Прибыль на 2 неделях обучения по профит фактору
    protected double[] w2ProfFacMinDown; // Максимальная просадка на 2 неделях обучения по профит фактору
    protected double[] w2ProfFacMaxUp; // Максимальный выброс вверх на 2 неделях обучения по профит фактору
    protected double[] w2ProfFacProfFac; // Профит фактор на 2 неделях обучения по профит фактору
    protected double[] w2ProfFacRecovFac; // Фактор восстановления на 2 неделях обучения по профит фактору
    protected double[] w2RecovFacProf; // Прибыль на 2 неделях обучения по фактору восстановления
    protected double[] w2RecovFacFacMinDown; // Максимальная просадка на 2 неделях обучения по фактору восстановления
    protected double[] w2RecovFacFacMaxUp; // Максимальный выброс вверх на 2 неделях обучения по фактору восстановления
    protected double[] w2RecovFacFacProfFac; // Профит фактор на 2 неделях обучения по фактору восстановления
    protected double[] w2RecovFacFacRecovFac; // Фактор восстановления на 2 неделях обучения по фактору восстановления
    protected double[] w2WinKooProf; // Прибыль на 2 неделях обучения по коэфициенту выигрыша
    protected double[] w2WinKooMinDown; // Максимальная просадка на 2 неделях обучения по коэфициенту выигрыша
    protected double[] w2WinKooMaxUp; // Максимальный выброс вверх на 2 неделях обучения по коэфициенту выигрыша
    protected double[] w2WinKooProfFac; // Профит фактор на 2 неделях обучения по коэфициенту выигрыша
    protected double[] w2WinKooRecovFac; // Фактор восстановления на 2 неделях обучения по коэфициенту выигрыша
    protected double[] w2ProcWinDeProf; // Прибыль на 2 неделях обучения по проценту выиграных сделок
    protected double[] w2ProcWinDeMinDown; // Максимальная просадка на 2 неделях обучения по проценту выиграных сделок
    protected double[] w2ProcWinDeMaxUp; // Максимальный выброс вверх на 2 неделях обучения по проценту выиграных сделок
    protected double[] w2ProcWinDeProfFac; // Профит фактор на 2 неделях обучения по проценту выиграных сделок
    protected double[] w2ProcWinDeRecovFac; // Фактор восстановления на 2 неделях обучения по проценту выиграных сделок
    protected double[] w4ProfProf; // Прибыль на 4 неделях обучения по максимальной прибыли
    protected double[] w4ProfMinDown; // Максимальная просадка на 4 неделях обучения по максимальной прибыли
    protected double[] w4ProfMaxUp; // Максимальный выброс вверх на 4 неделях обучения по максимальной прибыли
    protected double[] w4ProfProfFac; // Профит фактор на 4 неделях обучения по максимальной прибыли
    protected double[] w4ProfRecovFac; // Фактор восстановления на 4 неделях обучения по максимальной прибыли
    protected double[] w4ProfFacProf; // Прибыль на 4 неделях обучения по профит фактору
    protected double[] w4ProfFacMinDown; // Максимальная просадка на 4 неделях обучения по профит фактору
    protected double[] w4ProfFacMaxUp; // Максимальный выброс вверх на 4 неделях обучения по профит фактору
    protected double[] w4ProfFacProfFac; // Профит фактор на 4 неделях обучения по профит фактору
    protected double[] w4ProfFacRecovFac; // Фактор восстановления на 4 неделях обучения по профит фактору
    protected double[] w4RecovFacProf; // Прибыль на 4 неделях обучения по фактору восстановления
    protected double[] w4RecovFacFacMinDown; // Максимальная просадка на 4 неделях обучения по фактору восстановления
    protected double[] w4RecovFacFacMaxUp; // Максимальный выброс вверх на 4 неделях обучения по фактору восстановления
    protected double[] w4RecovFacFacProfFac; // Профит фактор на 4 неделях обучения по фактору восстановления
    protected double[] w4RecovFacFacRecovFac; // Фактор восстановления на 4 неделях обучения по фактору восстановления
    protected double[] w4WinKooProf; // Прибыль на 4 неделях обучения по коэфициенту выигрыша
    protected double[] w4WinKooMinDown; // Максимальная просадка на 4 неделях обучения по коэфициенту выигрыша
    protected double[] w4WinKooMaxUp; // Максимальный выброс вверх на 4 неделях обучения по коэфициенту выигрыша
    protected double[] w4WinKooProfFac; // Профит фактор на 4 неделях обучения по коэфициенту выигрыша
    protected double[] w4WinKooRecovFac; // Фактор восстановления на 4 неделях обучения по коэфициенту выигрыша
    protected double[] w4ProcWinDeProf; // Прибыль на 4 неделях обучения по проценту выиграных сделок
    protected double[] w4ProcWinDeMinDown; // Максимальная просадка на 4 неделях обучения по проценту выиграных сделок
    protected double[] w4ProcWinDeMaxUp; // Максимальный выброс вверх на 4 неделях обучения по проценту выиграных сделок
    protected double[] w4ProcWinDeProfFac; // Профит фактор на 4 неделях обучения по проценту выиграных сделок
    protected double[] w4ProcWinDeRecovFac; // Фактор восстановления на 4 неделях обучения по проценту выиграных сделок

    //Блок полей накапливаемой прибыли и максимальной просадки для графика
    protected double[] w1ProfProfRes; // Накопленная прибыль по 1 неделе обучения по прибыли
    protected double[] w1ProfProfMin; // Максимальная просадка по 1 неделе обучения по прибыли
    protected double[] w1ProfFacProfRes; // Накопленная прибыль по 1 неделе обучения по профит фактору
    protected double[] w1ProfFacProfMin; // Максимальная просадка по 1 неделе обучения по профит фактору
    protected double[] w1RecovFacProfRes; // Накопленная прибыль по 1 неделе обучения по фактору восстановления
    protected double[] w1RecovFacProfMin; // Максимальная просадка по 1 неделе обучения по фактору восстановления
    protected double[] w1WinKooProfRes; // Накопленная прибыль по 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1WinKooProfMin; // Максимальная просадка по 1 неделе обучения по коэфициенту выигрыша
    protected double[] w1ProcWinDeProfRes; // Накопленная прибыль по 1 неделе обучения по проценту выиграных сделок
    protected double[] w1ProcWinDeProfMin; // Максимальная просадка по 1 неделе обучения по проценту выиграных сделок
    protected double[] w2ProfProfRes; // Накопленная прибыль по 2 неделям обучения по прибыли
    protected double[] w2ProfProfMin; // Максимальная просадка по 2 неделям обучения по прибыли
    protected double[] w2ProfFacProfRes; // Накопленная прибыль по 2 неделям обучения по профит фактору
    protected double[] w2ProfFacProfMin; // Максимальная просадка по 2 неделям обучения по профит фактору
    protected double[] w2RecovFacProfRes; // Накопленная прибыль по 2 неделям обучения по фактору восстановления
    protected double[] w2RecovFacProfMin; // Максимальная просадка по 2 неделям обучения по фактору восстановления
    protected double[] w2WinKooProfRes; // Накопленная прибыль по 2 неделям обучения по коэфициенту выигрыша
    protected double[] w2WinKooProfMin; // Максимальная просадка по 2 неделям обучения по коэфициенту выигрыша
    protected double[] w2ProcWinDeProfRes; // Накопленная прибыль по 2 неделям обучения по проценту выиграных сделок
    protected double[] w2ProcWinDeProfMin; // Максимальная просадка по 2 неделям обучения по проценту выиграных сделок
    protected double[] w4ProfProfRes; // Накопленная прибыль по 4 неделям обучения по прибыли
    protected double[] w4ProfProfMin; // Максимальная просадка по 4 неделям обучения по прибыли
    protected double[] w4ProfFacProfRes; // Накопленная прибыль по 4 неделям обучения по профит фактору
    protected double[] w4ProfFacProfMin; // Максимальная просадка по 4 неделям обучения по профит фактору
    protected double[] w4RecovFacProfRes; // Накопленная прибыль по 4 неделям обучения по фактору восстановления
    protected double[] w4RecovFacProfMin; // Максимальная просадка по 4 неделям обучения по фактору восстановления
    protected double[] w4WinKooProfRes; // Накопленная прибыль по 4 неделям обучения по коэфициенту выигрыша
    protected double[] w4WinKooProfMin; // Максимальная просадка по 4 неделям обучения по коэфициенту выигрыша
    protected double[] w4ProcWinDeProfRes; // Накопленная прибыль по 4 неделям обучения по проценту выиграных сделок
    protected double[] w4ProcWinDeProfMin; // Максимальная просадка по 4 неделям обучения по проценту выиграных сделок

    // Прочие поля
    protected String comment;
    protected String ticker;
    protected String robName;
    protected int timeFrame;
    protected int startDate;
    protected int finishDate;
    protected double gSecurity; // Размер гарантийного обеспечения в рублях
    protected double unitPrice; // Стоимость единицы цены в рублях
    protected double avPrice; // Средняя цена котировок по закрытию
    protected ParRange parRanges;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void robName(String robName) {
        this.robName = robName;
    }

    public void setTimeFrame(int timeFrame) {
        this.timeFrame = timeFrame;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public void setFinishDate(int finishDate) {
        this.finishDate = finishDate;
    }

    public void setGSecurity(double gSecurity) {
        this.gSecurity = gSecurity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getComment() {
        return this.comment;
    }

    public String getTicker() {
        return this.ticker;
    }

    public String getRobName() {
        return this.robName;
    }

    public int getTimeFrame() {
        return this.timeFrame;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public int getFinishDate() {
        return this.finishDate;
    }

    public double getGSecurity() {
        return this.gSecurity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }


    public void wfoTest(String ticker, int timeFrame, ParRange paramRange, int quWeeks, int[] learnWeeksArr, int testWeeks, double comission, double gSecurity, double unitPrice, String pathToFolder) throws IOException {
        this.robName = GlobalTest.ROB_NAME;
        this.ticker = ticker;
        this.timeFrame = timeFrame;
        this.setGSecurity(gSecurity);
        this.setUnitPrice(unitPrice);
        this.parRanges = paramRange;
        String fNameConstructer = new String();
        fNameConstructer = String.format("1%s.txt", ticker);
        String pathToFile = new String();
        pathToFile = pathToFolder + fNameConstructer;
        FinRes instr = new FinRes();
        instr.setQuote(pathToFile);
        instr.setCom(comission);
        instr.workArr(quWeeks);
        instr.compressorIn(timeFrame);
        this.startDate = instr.getDate()[0];
        this.finishDate = instr.getDate()[instr.getDate().length - 1];
        double summa = 0.0;
        for (int i = 0; i < instr.getClose().length; i++) {
            summa += instr.getClose()[i];
        }
        this.avPrice = summa / instr.getClose().length;


        this.w1ProfProf = new double[quWeeks];
        this.w1ProfMinDown = new double[quWeeks];
        this.w1ProfMaxUp = new double[quWeeks];
        this.w1ProfProfFac = new double[quWeeks];
        this.w1ProfRecovFac = new double[quWeeks];
        this.w2ProfProf = new double[quWeeks];
        this.w2ProfMinDown = new double[quWeeks];
        this.w2ProfMaxUp = new double[quWeeks];
        this.w2ProfProfFac = new double[quWeeks];
        this.w2ProfRecovFac = new double[quWeeks];
        this.w4ProfProf = new double[quWeeks];
        this.w4ProfMinDown = new double[quWeeks];
        this.w4ProfMaxUp = new double[quWeeks];
        this.w4ProfProfFac = new double[quWeeks];
        this.w4ProfRecovFac = new double[quWeeks];

        this.w1ProcWinDeProf = new double[quWeeks];
        this.w1ProcWinDeMinDown = new double[quWeeks];
        this.w1ProcWinDeMaxUp = new double[quWeeks];
        this.w1ProcWinDeProfFac = new double[quWeeks];
        this.w1ProcWinDeRecovFac = new double[quWeeks];
        this.w2ProcWinDeProf = new double[quWeeks];
        this.w2ProcWinDeMinDown = new double[quWeeks];
        this.w2ProcWinDeMaxUp = new double[quWeeks];
        this.w2ProcWinDeProfFac = new double[quWeeks];
        this.w2ProcWinDeRecovFac = new double[quWeeks];
        this.w4ProcWinDeProf = new double[quWeeks];
        this.w4ProcWinDeMinDown = new double[quWeeks];
        this.w4ProcWinDeMaxUp = new double[quWeeks];
        this.w4ProcWinDeProfFac = new double[quWeeks];
        this.w4ProcWinDeRecovFac = new double[quWeeks];

        this.w1ProfFacProf = new double[quWeeks];
        this.w1ProfFacMinDown = new double[quWeeks];
        this.w1ProfFacMaxUp = new double[quWeeks];
        this.w1ProfFacProfFac = new double[quWeeks];
        this.w1ProfFacRecovFac = new double[quWeeks];
        this.w2ProfFacProf = new double[quWeeks];
        this.w2ProfFacMinDown = new double[quWeeks];
        this.w2ProfFacMaxUp = new double[quWeeks];
        this.w2ProfFacProfFac = new double[quWeeks];
        this.w2ProfFacRecovFac = new double[quWeeks];
        this.w4ProfFacProf = new double[quWeeks];
        this.w4ProfFacMinDown = new double[quWeeks];
        this.w4ProfFacMaxUp = new double[quWeeks];
        this.w4ProfFacProfFac = new double[quWeeks];
        this.w4ProfFacRecovFac = new double[quWeeks];

        this.w1RecovFacProf = new double[quWeeks];
        this.w1RecovFacFacMinDown = new double[quWeeks];
        this.w1RecovFacFacMaxUp = new double[quWeeks];
        this.w1RecovFacFacProfFac = new double[quWeeks];
        this.w1RecovFacFacRecovFac = new double[quWeeks];
        this.w2RecovFacProf = new double[quWeeks];
        this.w2RecovFacFacMinDown = new double[quWeeks];
        this.w2RecovFacFacMaxUp = new double[quWeeks];
        this.w2RecovFacFacProfFac = new double[quWeeks];
        this.w2RecovFacFacRecovFac = new double[quWeeks];
        this.w4RecovFacProf = new double[quWeeks];
        this.w4RecovFacFacMinDown = new double[quWeeks];
        this.w4RecovFacFacMaxUp = new double[quWeeks];
        this.w4RecovFacFacProfFac = new double[quWeeks];
        this.w4RecovFacFacRecovFac = new double[quWeeks];

        this.w1WinKooProf = new double[quWeeks];
        this.w1WinKooMinDown = new double[quWeeks];
        this.w1WinKooMaxUp = new double[quWeeks];
        this.w1WinKooProfFac = new double[quWeeks];
        this.w1WinKooRecovFac = new double[quWeeks];
        this.w2WinKooProf = new double[quWeeks];
        this.w2WinKooMinDown = new double[quWeeks];
        this.w2WinKooMaxUp = new double[quWeeks];
        this.w2WinKooProfFac = new double[quWeeks];
        this.w2WinKooRecovFac = new double[quWeeks];
        this.w4WinKooProf = new double[quWeeks];
        this.w4WinKooMinDown = new double[quWeeks];
        this.w4WinKooMaxUp = new double[quWeeks];
        this.w4WinKooProfFac = new double[quWeeks];
        this.w4WinKooRecovFac = new double[quWeeks];

        for (int i = 1; i <= quWeeks; i++) {
            for (int learnWeeks : learnWeeksArr) {
                FinRes instrLearn = instr.getLearnExt(quWeeks, i, learnWeeks);
                FinRes instrTest = instr.getTestExt(quWeeks, i, testWeeks);
                FixTester sample = new FixTester();
                sample.learning(paramRange, instrLearn);
                sample.sortBy();
                Parameter[] parameters = sample.theBestPar(learnWeeks);
                if (parameters[0].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[0]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfProf[i - 1] = instrTest.getGenRes();
                        this.w1ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfProf[i - 1] = instrTest.getGenRes();
                        this.w2ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfProf[i - 1] = instrTest.getGenRes();
                        this.w4ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfProf[i - 1] = 0;
                        this.w1ProfMinDown[i - 1] = 0;
                        this.w1ProfMaxUp[i - 1] = 0;
                        this.w1ProfProfFac[i - 1] = 0;
                        this.w1ProfRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfProf[i - 1] = 0;
                        this.w2ProfMinDown[i - 1] = 0;
                        this.w2ProfMaxUp[i - 1] = 0;
                        this.w2ProfProfFac[i - 1] = 0;
                        this.w2ProfRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfProf[i - 1] = 0;
                        this.w4ProfMinDown[i - 1] = 0;
                        this.w4ProfMaxUp[i - 1] = 0;
                        this.w4ProfProfFac[i - 1] = 0;
                        this.w4ProfRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[1].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[1]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w1ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w2ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w4ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProcWinDeProf[i - 1] = 0;
                        this.w1ProcWinDeMinDown[i - 1] = 0;
                        this.w1ProcWinDeMaxUp[i - 1] = 0;
                        this.w1ProcWinDeProfFac[i - 1] = 0;
                        this.w1ProcWinDeRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProcWinDeProf[i - 1] = 0;
                        this.w2ProcWinDeMinDown[i - 1] = 0;
                        this.w2ProcWinDeMaxUp[i - 1] = 0;
                        this.w2ProcWinDeProfFac[i - 1] = 0;
                        this.w2ProcWinDeRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProcWinDeProf[i - 1] = 0;
                        this.w4ProcWinDeMinDown[i - 1] = 0;
                        this.w4ProcWinDeMaxUp[i - 1] = 0;
                        this.w4ProcWinDeProfFac[i - 1] = 0;
                        this.w4ProcWinDeRecovFac[i - 1] = 0;
                    }

                }
                if (parameters[2].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[2]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w1ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w2ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w4ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    }

                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfFacProf[i - 1] = 0;
                        this.w1ProfFacMinDown[i - 1] = 0;
                        this.w1ProfFacMaxUp[i - 1] = 0;
                        this.w1ProfFacProfFac[i - 1] = 0;
                        this.w1ProfFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfFacProf[i - 1] = 0;
                        this.w2ProfFacMinDown[i - 1] = 0;
                        this.w2ProfFacMaxUp[i - 1] = 0;
                        this.w2ProfFacProfFac[i - 1] = 0;
                        this.w2ProfFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfFacProf[i - 1] = 0;
                        this.w4ProfFacMinDown[i - 1] = 0;
                        this.w4ProfFacMaxUp[i - 1] = 0;
                        this.w4ProfFacProfFac[i - 1] = 0;
                        this.w4ProfFacRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[3].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[3]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w1RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w1RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w2RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w2RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w4RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w4RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1RecovFacProf[i - 1] = 0;
                        this.w1RecovFacFacMinDown[i - 1] = 0;
                        this.w1RecovFacFacMaxUp[i - 1] = 0;
                        this.w1RecovFacFacProfFac[i - 1] = 0;
                        this.w1RecovFacFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2RecovFacProf[i - 1] = 0;
                        this.w2RecovFacFacMinDown[i - 1] = 0;
                        this.w2RecovFacFacMaxUp[i - 1] = 0;
                        this.w2RecovFacFacProfFac[i - 1] = 0;
                        this.w2RecovFacFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4RecovFacProf[i - 1] = 0;
                        this.w4RecovFacFacMinDown[i - 1] = 0;
                        this.w4RecovFacFacMaxUp[i - 1] = 0;
                        this.w4RecovFacFacProfFac[i - 1] = 0;
                        this.w4RecovFacFacRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[4].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[4]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w1WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w1WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w2WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w2WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w4WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w4WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    }

                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1WinKooProf[i - 1] = 0;
                        this.w1WinKooMinDown[i - 1] = 0;
                        this.w1WinKooMaxUp[i - 1] = 0;
                        this.w1WinKooProfFac[i - 1] = 0;
                        this.w1WinKooRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2WinKooProf[i - 1] = 0;
                        this.w2WinKooMinDown[i - 1] = 0;
                        this.w2WinKooMaxUp[i - 1] = 0;
                        this.w2WinKooProfFac[i - 1] = 0;
                        this.w2WinKooRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4WinKooProf[i - 1] = 0;
                        this.w4WinKooMinDown[i - 1] = 0;
                        this.w4WinKooMaxUp[i - 1] = 0;
                        this.w4WinKooProfFac[i - 1] = 0;
                        this.w4WinKooRecovFac[i - 1] = 0;
                    }
                }
            }
        }
        this.w1ProfProfRes = new double[quWeeks];
        this.w1ProfProfMin = new double[quWeeks];
        this.w1ProfFacProfRes = new double[quWeeks];
        this.w1ProfFacProfMin = new double[quWeeks];
        this.w1RecovFacProfRes = new double[quWeeks];
        this.w1RecovFacProfMin = new double[quWeeks];
        this.w1WinKooProfRes = new double[quWeeks];
        this.w1WinKooProfMin = new double[quWeeks];
        this.w1ProcWinDeProfRes = new double[quWeeks];
        this.w1ProcWinDeProfMin = new double[quWeeks];
        this.w2ProfProfRes = new double[quWeeks];
        this.w2ProfProfMin = new double[quWeeks];
        this.w2ProfFacProfRes = new double[quWeeks];
        this.w2ProfFacProfMin = new double[quWeeks];
        this.w2RecovFacProfRes = new double[quWeeks];
        this.w2RecovFacProfMin = new double[quWeeks];
        this.w2WinKooProfRes = new double[quWeeks];
        this.w2WinKooProfMin = new double[quWeeks];
        this.w2ProcWinDeProfRes = new double[quWeeks];
        this.w2ProcWinDeProfMin = new double[quWeeks];
        this.w4ProfProfRes = new double[quWeeks];
        this.w4ProfProfMin = new double[quWeeks];
        this.w4ProfFacProfRes = new double[quWeeks];
        this.w4ProfFacProfMin = new double[quWeeks];
        this.w4RecovFacProfRes = new double[quWeeks];
        this.w4RecovFacProfMin = new double[quWeeks];
        this.w4WinKooProfRes = new double[quWeeks];
        this.w4WinKooProfMin = new double[quWeeks];
        this.w4ProcWinDeProfRes = new double[quWeeks];
        this.w4ProcWinDeProfMin = new double[quWeeks];

        for (int j = 0; j < quWeeks; j++) {
            if (j == 0) {
                this.w1ProfProfRes[j] = this.w1ProfProf[j];
                this.w1ProfProfMin[j] = this.w1ProfMinDown[j];
                this.w1ProfFacProfRes[j] = this.w1ProfFacProf[j];
                this.w1ProfFacProfMin[j] = this.w1ProfFacMinDown[j];
                this.w1RecovFacProfRes[j] = this.w1RecovFacProf[j];
                this.w1RecovFacProfMin[j] = this.w1RecovFacFacMinDown[j];
                this.w1WinKooProfRes[j] = this.w1WinKooProf[j];
                this.w1WinKooProfMin[j] = this.w1WinKooMinDown[j];
                this.w1ProcWinDeProfRes[j] = this.w1ProcWinDeProf[j];
                this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeMinDown[j];
                this.w2ProfProfRes[j] = this.w2ProfProf[j];
                this.w2ProfProfMin[j] = this.w2ProfMinDown[j];
                this.w2ProfFacProfRes[j] = this.w2ProfFacProf[j];
                this.w2ProfFacProfMin[j] = this.w2ProfFacMinDown[j];
                this.w2RecovFacProfRes[j] = this.w2RecovFacProf[j];
                this.w2RecovFacProfMin[j] = this.w2RecovFacFacMinDown[j];
                this.w2WinKooProfRes[j] = this.w2WinKooProf[j];
                this.w2WinKooProfMin[j] = this.w2WinKooMinDown[j];
                this.w2ProcWinDeProfRes[j] = this.w2ProcWinDeProf[j];
                this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeMinDown[j];
                this.w4ProfProfRes[j] = this.w4ProfProf[j];
                this.w4ProfProfMin[j] = this.w4ProfMinDown[j];
                this.w4ProfFacProfRes[j] = this.w4ProfFacProf[j];
                this.w4ProfFacProfMin[j] = this.w4ProfFacMinDown[j];
                this.w4RecovFacProfRes[j] = this.w4RecovFacProf[j];
                this.w4RecovFacProfMin[j] = this.w4RecovFacFacMinDown[j];
                this.w4WinKooProfRes[j] = this.w4WinKooProf[j];
                this.w4WinKooProfMin[j] = this.w4WinKooMinDown[j];
                this.w4ProcWinDeProfRes[j] = this.w4ProcWinDeProf[j];
                this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeMinDown[j];

            } else {
                this.w1ProfProfRes[j] = this.w1ProfProf[j] + this.w1ProfProfRes[j - 1];
                if (this.w1ProfMinDown[j] < this.w1ProfMinDown[j - 1]) {
                    this.w1ProfProfMin[j] = this.w1ProfMinDown[j];
                } else {
                    this.w1ProfProfMin[j] = this.w1ProfProfMin[j - 1];
                }
                this.w1ProfFacProfRes[j] = this.w1ProfFacProf[j] + this.w1ProfFacProfRes[j - 1];
                if (this.w1ProfFacMinDown[j] < this.w1ProfFacMinDown[j - 1]) {
                    this.w1ProfFacProfMin[j] = this.w1ProfFacMinDown[j];
                } else {
                    this.w1ProfFacProfMin[j] = this.w1ProfFacProfMin[j - 1];
                }
                this.w1RecovFacProfRes[j] = this.w1RecovFacProf[j] + this.w1RecovFacProfRes[j - 1];
                if (this.w1RecovFacFacMinDown[j] < this.w1RecovFacFacMinDown[j - 1]) {
                    this.w1RecovFacProfMin[j] = this.w1RecovFacFacMinDown[j];
                } else {
                    this.w1RecovFacProfMin[j] = this.w1RecovFacProfMin[j - 1];
                }
                this.w1WinKooProfRes[j] = this.w1WinKooProf[j];
                if (this.w1WinKooMinDown[j] < this.w1WinKooMinDown[j - 1]) {
                    this.w1WinKooProfMin[j] = this.w1WinKooMinDown[j];
                } else {
                    this.w1WinKooProfMin[j] = this.w1WinKooProfMin[j - 1];
                }
                this.w1ProcWinDeProfRes[j] = this.w1ProcWinDeProf[j];
                if (this.w1ProcWinDeMinDown[j] < this.w1ProcWinDeMinDown[j - 1]) {
                    this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeMinDown[j];
                } else {
                    this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeProfMin[j - 1];
                }
                this.w2ProfProfRes[j] = this.w2ProfProf[j];
                if (this.w2ProfMinDown[j] < this.w2ProfMinDown[j - 1]) {
                    this.w2ProfProfMin[j] = this.w2ProfMinDown[j];
                } else {
                    this.w2ProfProfMin[j] = this.w2ProfProfMin[j - 1];
                }
                this.w2ProfFacProfRes[j] = this.w2ProfFacProf[j];
                if (this.w2ProfFacMinDown[j] < this.w2ProfFacMinDown[j - 1]) {
                    this.w2ProfFacProfMin[j] = this.w2ProfFacMinDown[j];
                } else {
                    this.w2ProfFacProfMin[j] = this.w2ProfFacProfMin[j - 1];
                }
                this.w2RecovFacProfRes[j] = this.w2RecovFacProf[j];
                if (this.w2RecovFacFacMinDown[j] < this.w2RecovFacFacMinDown[j - 1]) {
                    this.w2RecovFacProfMin[j] = this.w2RecovFacFacMinDown[j];
                } else {
                    this.w2RecovFacProfMin[j] = this.w2RecovFacProfMin[j - 1];
                }
                this.w2WinKooProfRes[j] = this.w2WinKooProf[j];
                if (this.w2WinKooMinDown[j] < this.w2WinKooMinDown[j - 1]) {
                    this.w2WinKooProfMin[j] = this.w2WinKooMinDown[j];
                } else {
                    this.w2WinKooProfMin[j] = this.w2WinKooProfMin[j - 1];
                }
                this.w2WinKooProfMin[j] = this.w2WinKooMinDown[j];
                this.w2ProcWinDeProfRes[j] = this.w2ProcWinDeProf[j];
                if (this.w2ProcWinDeMinDown[j] < this.w2ProcWinDeMinDown[j - 1]) {
                    this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeMinDown[j];
                } else {
                    this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeProfMin[j - 1];
                }
                this.w4ProfProfRes[j] = this.w4ProfProf[j];
                if (this.w4ProfMinDown[j] < this.w4ProfMinDown[j - 1]) {
                    this.w4ProfProfMin[j] = this.w4ProfMinDown[j];
                } else {
                    this.w4ProfProfMin[j] = this.w4ProfProfMin[j - 1];
                }
                this.w4ProfFacProfRes[j] = this.w4ProfFacProf[j];
                if (this.w4ProfFacMinDown[j] < this.w4ProfFacMinDown[j - 1]) {
                    this.w4ProfFacProfMin[j] = this.w4ProfFacMinDown[j];
                } else {
                    this.w4ProfFacProfMin[j] = this.w4ProfFacProfMin[j - 1];
                }
                this.w4RecovFacProfRes[j] = this.w4RecovFacProf[j];
                if (this.w4RecovFacFacMinDown[j] < this.w4RecovFacFacMinDown[j - 1]) {
                    this.w4RecovFacProfMin[j] = this.w4RecovFacFacMinDown[j];
                } else {
                    this.w4RecovFacProfMin[j] = this.w4RecovFacProfMin[j - 1];
                }
                this.w4WinKooProfRes[j] = this.w4WinKooProf[j];
                if (this.w4WinKooMinDown[j] < this.w4WinKooMinDown[j - 1]) {
                    this.w4WinKooProfMin[j] = this.w4WinKooMinDown[j];
                } else {
                    this.w4WinKooProfMin[j] = this.w4WinKooProfMin[j - 1];
                }
                this.w4ProcWinDeProfRes[j] = this.w4ProcWinDeProf[j];
                if (this.w4ProcWinDeMinDown[j] < this.w4ProcWinDeMinDown[j - 1]) {
                    this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeMinDown[j];
                } else {
                    this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeProfMin[j - 1];
                }
            }
        }
    }

    public void wfoTestGlobal(String ticker, int timeFrame, ParRange paramRange, int quWeeks, int[] learnWeeksArr, int testWeeks, double comission, double gSecurity, double unitPrice, String pathToFolder) throws IOException {
        this.robName = GlobalTest.ROB_NAME;
        this.ticker = ticker;
        this.timeFrame = timeFrame;
        this.setGSecurity(gSecurity);
        this.setUnitPrice(unitPrice);
        this.parRanges = paramRange;
        String fNameConstructer = new String();
        fNameConstructer = String.format("1%s.txt", ticker);
        String pathToFile = new String();
        pathToFile = pathToFolder + fNameConstructer;
        FinRes instr = new FinRes();
        instr.setQuote(pathToFile);
        instr.setCom(comission);
        instr.workArr(quWeeks);
        instr.compressorIn(timeFrame);
        this.startDate = instr.getDate()[0];
        this.finishDate = instr.getDate()[instr.getDate().length - 1];
        double summa = 0.0;
        for (int i = 0; i < instr.getClose().length; i++) {
            summa += instr.getClose()[i];
        }
        this.avPrice = summa / instr.getClose().length;

        this.w1ProfProf = new double[quWeeks];
        this.w1ProfMinDown = new double[quWeeks];
        this.w1ProfMaxUp = new double[quWeeks];
        this.w1ProfProfFac = new double[quWeeks];
        this.w1ProfRecovFac = new double[quWeeks];
        this.w2ProfProf = new double[quWeeks];
        this.w2ProfMinDown = new double[quWeeks];
        this.w2ProfMaxUp = new double[quWeeks];
        this.w2ProfProfFac = new double[quWeeks];
        this.w2ProfRecovFac = new double[quWeeks];
        this.w4ProfProf = new double[quWeeks];
        this.w4ProfMinDown = new double[quWeeks];
        this.w4ProfMaxUp = new double[quWeeks];
        this.w4ProfProfFac = new double[quWeeks];
        this.w4ProfRecovFac = new double[quWeeks];

        this.w1ProcWinDeProf = new double[quWeeks];
        this.w1ProcWinDeMinDown = new double[quWeeks];
        this.w1ProcWinDeMaxUp = new double[quWeeks];
        this.w1ProcWinDeProfFac = new double[quWeeks];
        this.w1ProcWinDeRecovFac = new double[quWeeks];
        this.w2ProcWinDeProf = new double[quWeeks];
        this.w2ProcWinDeMinDown = new double[quWeeks];
        this.w2ProcWinDeMaxUp = new double[quWeeks];
        this.w2ProcWinDeProfFac = new double[quWeeks];
        this.w2ProcWinDeRecovFac = new double[quWeeks];
        this.w4ProcWinDeProf = new double[quWeeks];
        this.w4ProcWinDeMinDown = new double[quWeeks];
        this.w4ProcWinDeMaxUp = new double[quWeeks];
        this.w4ProcWinDeProfFac = new double[quWeeks];
        this.w4ProcWinDeRecovFac = new double[quWeeks];

        this.w1ProfFacProf = new double[quWeeks];
        this.w1ProfFacMinDown = new double[quWeeks];
        this.w1ProfFacMaxUp = new double[quWeeks];
        this.w1ProfFacProfFac = new double[quWeeks];
        this.w1ProfFacRecovFac = new double[quWeeks];
        this.w2ProfFacProf = new double[quWeeks];
        this.w2ProfFacMinDown = new double[quWeeks];
        this.w2ProfFacMaxUp = new double[quWeeks];
        this.w2ProfFacProfFac = new double[quWeeks];
        this.w2ProfFacRecovFac = new double[quWeeks];
        this.w4ProfFacProf = new double[quWeeks];
        this.w4ProfFacMinDown = new double[quWeeks];
        this.w4ProfFacMaxUp = new double[quWeeks];
        this.w4ProfFacProfFac = new double[quWeeks];
        this.w4ProfFacRecovFac = new double[quWeeks];

        this.w1RecovFacProf = new double[quWeeks];
        this.w1RecovFacFacMinDown = new double[quWeeks];
        this.w1RecovFacFacMaxUp = new double[quWeeks];
        this.w1RecovFacFacProfFac = new double[quWeeks];
        this.w1RecovFacFacRecovFac = new double[quWeeks];
        this.w2RecovFacProf = new double[quWeeks];
        this.w2RecovFacFacMinDown = new double[quWeeks];
        this.w2RecovFacFacMaxUp = new double[quWeeks];
        this.w2RecovFacFacProfFac = new double[quWeeks];
        this.w2RecovFacFacRecovFac = new double[quWeeks];
        this.w4RecovFacProf = new double[quWeeks];
        this.w4RecovFacFacMinDown = new double[quWeeks];
        this.w4RecovFacFacMaxUp = new double[quWeeks];
        this.w4RecovFacFacProfFac = new double[quWeeks];
        this.w4RecovFacFacRecovFac = new double[quWeeks];

        this.w1WinKooProf = new double[quWeeks];
        this.w1WinKooMinDown = new double[quWeeks];
        this.w1WinKooMaxUp = new double[quWeeks];
        this.w1WinKooProfFac = new double[quWeeks];
        this.w1WinKooRecovFac = new double[quWeeks];
        this.w2WinKooProf = new double[quWeeks];
        this.w2WinKooMinDown = new double[quWeeks];
        this.w2WinKooMaxUp = new double[quWeeks];
        this.w2WinKooProfFac = new double[quWeeks];
        this.w2WinKooRecovFac = new double[quWeeks];
        this.w4WinKooProf = new double[quWeeks];
        this.w4WinKooMinDown = new double[quWeeks];
        this.w4WinKooMaxUp = new double[quWeeks];
        this.w4WinKooProfFac = new double[quWeeks];
        this.w4WinKooRecovFac = new double[quWeeks];

        for (int i = 1; i <= quWeeks; i++) {
            for (int learnWeeks : learnWeeksArr) {
                FinRes instrLearn = instr.getLearnExt(quWeeks, i, learnWeeks);
                FinRes instrTest = instr.getTestExt(quWeeks, i, testWeeks);
                FixTester sample = new FixTester();
                sample.learning(paramRange, instrLearn);
                sample.sortBy();
                Parameter[] parameters = sample.theBestPar(learnWeeks);
                if (parameters[0].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[0]);
                    if (this.comment == null) {
                        this.setComment(instrTest.getComment());
                    }
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfProf[i - 1] = instrTest.getGenRes();
                        this.w1ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfProf[i - 1] = instrTest.getGenRes();
                        this.w2ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfProf[i - 1] = instrTest.getGenRes();
                        this.w4ProfMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProfMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProfProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProfRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfProf[i - 1] = 0;
                        this.w1ProfMinDown[i - 1] = 0;
                        this.w1ProfMaxUp[i - 1] = 0;
                        this.w1ProfProfFac[i - 1] = 0;
                        this.w1ProfRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfProf[i - 1] = 0;
                        this.w2ProfMinDown[i - 1] = 0;
                        this.w2ProfMaxUp[i - 1] = 0;
                        this.w2ProfProfFac[i - 1] = 0;
                        this.w2ProfRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfProf[i - 1] = 0;
                        this.w4ProfMinDown[i - 1] = 0;
                        this.w4ProfMaxUp[i - 1] = 0;
                        this.w4ProfProfFac[i - 1] = 0;
                        this.w4ProfRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[1].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[1]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w1ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w2ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProcWinDeProf[i - 1] = instrTest.getGenRes();
                        this.w4ProcWinDeMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProcWinDeMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProcWinDeProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProcWinDeRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProcWinDeProf[i - 1] = 0;
                        this.w1ProcWinDeMinDown[i - 1] = 0;
                        this.w1ProcWinDeMaxUp[i - 1] = 0;
                        this.w1ProcWinDeProfFac[i - 1] = 0;
                        this.w1ProcWinDeRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProcWinDeProf[i - 1] = 0;
                        this.w2ProcWinDeMinDown[i - 1] = 0;
                        this.w2ProcWinDeMaxUp[i - 1] = 0;
                        this.w2ProcWinDeProfFac[i - 1] = 0;
                        this.w2ProcWinDeRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProcWinDeProf[i - 1] = 0;
                        this.w4ProcWinDeMinDown[i - 1] = 0;
                        this.w4ProcWinDeMaxUp[i - 1] = 0;
                        this.w4ProcWinDeProfFac[i - 1] = 0;
                        this.w4ProcWinDeRecovFac[i - 1] = 0;
                    }

                }
                if (parameters[2].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[2]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w1ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w1ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w2ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w2ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfFacProf[i - 1] = instrTest.getGenRes();
                        this.w4ProfFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4ProfFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4ProfFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w4ProfFacRecovFac[i - 1] = instrTest.getRecovFac();
                    }

                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1ProfFacProf[i - 1] = 0;
                        this.w1ProfFacMinDown[i - 1] = 0;
                        this.w1ProfFacMaxUp[i - 1] = 0;
                        this.w1ProfFacProfFac[i - 1] = 0;
                        this.w1ProfFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2ProfFacProf[i - 1] = 0;
                        this.w2ProfFacMinDown[i - 1] = 0;
                        this.w2ProfFacMaxUp[i - 1] = 0;
                        this.w2ProfFacProfFac[i - 1] = 0;
                        this.w2ProfFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4ProfFacProf[i - 1] = 0;
                        this.w4ProfFacMinDown[i - 1] = 0;
                        this.w4ProfFacMaxUp[i - 1] = 0;
                        this.w4ProfFacProfFac[i - 1] = 0;
                        this.w4ProfFacRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[3].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[3]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w1RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w1RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w2RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w2RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4RecovFacProf[i - 1] = instrTest.getGenRes();
                        this.w4RecovFacFacMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4RecovFacFacMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4RecovFacFacProfFac[i - 1] = instrTest.getProfFac();
                        this.w4RecovFacFacRecovFac[i - 1] = instrTest.getRecovFac();
                    }
                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1RecovFacProf[i - 1] = 0;
                        this.w1RecovFacFacMinDown[i - 1] = 0;
                        this.w1RecovFacFacMaxUp[i - 1] = 0;
                        this.w1RecovFacFacProfFac[i - 1] = 0;
                        this.w1RecovFacFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2RecovFacProf[i - 1] = 0;
                        this.w2RecovFacFacMinDown[i - 1] = 0;
                        this.w2RecovFacFacMaxUp[i - 1] = 0;
                        this.w2RecovFacFacProfFac[i - 1] = 0;
                        this.w2RecovFacFacRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4RecovFacProf[i - 1] = 0;
                        this.w4RecovFacFacMinDown[i - 1] = 0;
                        this.w4RecovFacFacMaxUp[i - 1] = 0;
                        this.w4RecovFacFacProfFac[i - 1] = 0;
                        this.w4RecovFacFacRecovFac[i - 1] = 0;
                    }
                }
                if (parameters[4].checkPar()) {
                    GlobalTest.robFunc(instrTest, parameters[4]);
                    instrTest.counter();
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w1WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w1WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w1WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w1WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w2WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w2WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w2WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w2WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4WinKooProf[i - 1] = instrTest.getGenRes();
                        this.w4WinKooMinDown[i - 1] = instrTest.getMaxLoss();
                        this.w4WinKooMaxUp[i - 1] = instrTest.getMaxProf();
                        this.w4WinKooProfFac[i - 1] = instrTest.getProfFac();
                        this.w4WinKooRecovFac[i - 1] = instrTest.getRecovFac();
                    }

                } else {
                    if (learnWeeks == learnWeeksArr[0]) {
                        this.w1WinKooProf[i - 1] = 0;
                        this.w1WinKooMinDown[i - 1] = 0;
                        this.w1WinKooMaxUp[i - 1] = 0;
                        this.w1WinKooProfFac[i - 1] = 0;
                        this.w1WinKooRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[1]) {
                        this.w2WinKooProf[i - 1] = 0;
                        this.w2WinKooMinDown[i - 1] = 0;
                        this.w2WinKooMaxUp[i - 1] = 0;
                        this.w2WinKooProfFac[i - 1] = 0;
                        this.w2WinKooRecovFac[i - 1] = 0;
                    } else if (learnWeeks == learnWeeksArr[2]) {
                        this.w4WinKooProf[i - 1] = 0;
                        this.w4WinKooMinDown[i - 1] = 0;
                        this.w4WinKooMaxUp[i - 1] = 0;
                        this.w4WinKooProfFac[i - 1] = 0;
                        this.w4WinKooRecovFac[i - 1] = 0;
                    }
                }
            }
        }
        this.w1ProfProfRes = new double[quWeeks];
        this.w1ProfProfMin = new double[quWeeks];
        this.w1ProfFacProfRes = new double[quWeeks];
        this.w1ProfFacProfMin = new double[quWeeks];
        this.w1RecovFacProfRes = new double[quWeeks];
        this.w1RecovFacProfMin = new double[quWeeks];
        this.w1WinKooProfRes = new double[quWeeks];
        this.w1WinKooProfMin = new double[quWeeks];
        this.w1ProcWinDeProfRes = new double[quWeeks];
        this.w1ProcWinDeProfMin = new double[quWeeks];
        this.w2ProfProfRes = new double[quWeeks];
        this.w2ProfProfMin = new double[quWeeks];
        this.w2ProfFacProfRes = new double[quWeeks];
        this.w2ProfFacProfMin = new double[quWeeks];
        this.w2RecovFacProfRes = new double[quWeeks];
        this.w2RecovFacProfMin = new double[quWeeks];
        this.w2WinKooProfRes = new double[quWeeks];
        this.w2WinKooProfMin = new double[quWeeks];
        this.w2ProcWinDeProfRes = new double[quWeeks];
        this.w2ProcWinDeProfMin = new double[quWeeks];
        this.w4ProfProfRes = new double[quWeeks];
        this.w4ProfProfMin = new double[quWeeks];
        this.w4ProfFacProfRes = new double[quWeeks];
        this.w4ProfFacProfMin = new double[quWeeks];
        this.w4RecovFacProfRes = new double[quWeeks];
        this.w4RecovFacProfMin = new double[quWeeks];
        this.w4WinKooProfRes = new double[quWeeks];
        this.w4WinKooProfMin = new double[quWeeks];
        this.w4ProcWinDeProfRes = new double[quWeeks];
        this.w4ProcWinDeProfMin = new double[quWeeks];

        for (int j = 0; j < quWeeks; j++) {
            if (j == 0) {
                this.w1ProfProfRes[j] = this.w1ProfProf[j];
                this.w1ProfProfMin[j] = this.w1ProfMinDown[j];
                this.w1ProfFacProfRes[j] = this.w1ProfFacProf[j];
                this.w1ProfFacProfMin[j] = this.w1ProfFacMinDown[j];
                this.w1RecovFacProfRes[j] = this.w1RecovFacProf[j];
                this.w1RecovFacProfMin[j] = this.w1RecovFacFacMinDown[j];
                this.w1WinKooProfRes[j] = this.w1WinKooProf[j];
                this.w1WinKooProfMin[j] = this.w1WinKooMinDown[j];
                this.w1ProcWinDeProfRes[j] = this.w1ProcWinDeProf[j];
                this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeMinDown[j];
                this.w2ProfProfRes[j] = this.w2ProfProf[j];
                this.w2ProfProfMin[j] = this.w2ProfMinDown[j];
                this.w2ProfFacProfRes[j] = this.w2ProfFacProf[j];
                this.w2ProfFacProfMin[j] = this.w2ProfFacMinDown[j];
                this.w2RecovFacProfRes[j] = this.w2RecovFacProf[j];
                this.w2RecovFacProfMin[j] = this.w2RecovFacFacMinDown[j];
                this.w2WinKooProfRes[j] = this.w2WinKooProf[j];
                this.w2WinKooProfMin[j] = this.w2WinKooMinDown[j];
                this.w2ProcWinDeProfRes[j] = this.w2ProcWinDeProf[j];
                this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeMinDown[j];
                this.w4ProfProfRes[j] = this.w4ProfProf[j];
                this.w4ProfProfMin[j] = this.w4ProfMinDown[j];
                this.w4ProfFacProfRes[j] = this.w4ProfFacProf[j];
                this.w4ProfFacProfMin[j] = this.w4ProfFacMinDown[j];
                this.w4RecovFacProfRes[j] = this.w4RecovFacProf[j];
                this.w4RecovFacProfMin[j] = this.w4RecovFacFacMinDown[j];
                this.w4WinKooProfRes[j] = this.w4WinKooProf[j];
                this.w4WinKooProfMin[j] = this.w4WinKooMinDown[j];
                this.w4ProcWinDeProfRes[j] = this.w4ProcWinDeProf[j];
                this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeMinDown[j];

            } else {
                this.w1ProfProfRes[j] = this.w1ProfProf[j] + this.w1ProfProfRes[j - 1];
                if (this.w1ProfMinDown[j] < this.w1ProfMinDown[j - 1]) {
                    this.w1ProfProfMin[j] = this.w1ProfMinDown[j];
                } else {
                    this.w1ProfProfMin[j] = this.w1ProfProfMin[j - 1];
                }
                this.w1ProfFacProfRes[j] = this.w1ProfFacProf[j] + this.w1ProfFacProfRes[j - 1];
                if (this.w1ProfFacMinDown[j] < this.w1ProfFacMinDown[j - 1]) {
                    this.w1ProfFacProfMin[j] = this.w1ProfFacMinDown[j];
                } else {
                    this.w1ProfFacProfMin[j] = this.w1ProfFacProfMin[j - 1];
                }
                this.w1RecovFacProfRes[j] = this.w1RecovFacProf[j] + this.w1RecovFacProfRes[j - 1];
                if (this.w1RecovFacFacMinDown[j] < this.w1RecovFacFacMinDown[j - 1]) {
                    this.w1RecovFacProfMin[j] = this.w1RecovFacFacMinDown[j];
                } else {
                    this.w1RecovFacProfMin[j] = this.w1RecovFacProfMin[j - 1];
                }
                this.w1WinKooProfRes[j] = this.w1WinKooProf[j] + this.w1WinKooProfRes[j - 1];
                if (this.w1WinKooMinDown[j] < this.w1WinKooMinDown[j - 1]) {
                    this.w1WinKooProfMin[j] = this.w1WinKooMinDown[j];
                } else {
                    this.w1WinKooProfMin[j] = this.w1WinKooProfMin[j - 1];
                }
                this.w1ProcWinDeProfRes[j] = this.w1ProcWinDeProf[j] + w1ProcWinDeProfRes[j - 1];
                if (this.w1ProcWinDeMinDown[j] < this.w1ProcWinDeMinDown[j - 1]) {
                    this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeMinDown[j];
                } else {
                    this.w1ProcWinDeProfMin[j] = this.w1ProcWinDeProfMin[j - 1];
                }
                this.w2ProfProfRes[j] = this.w2ProfProf[j] + this.w2ProfProfRes[j - 1];
                if (this.w2ProfMinDown[j] < this.w2ProfMinDown[j - 1]) {
                    this.w2ProfProfMin[j] = this.w2ProfMinDown[j];
                } else {
                    this.w2ProfProfMin[j] = this.w2ProfProfMin[j - 1];
                }
                this.w2ProfFacProfRes[j] = this.w2ProfFacProf[j] + this.w2ProfFacProfRes[j - 1];
                if (this.w2ProfFacMinDown[j] < this.w2ProfFacMinDown[j - 1]) {
                    this.w2ProfFacProfMin[j] = this.w2ProfFacMinDown[j];
                } else {
                    this.w2ProfFacProfMin[j] = this.w2ProfFacProfMin[j - 1];
                }
                this.w2RecovFacProfRes[j] = this.w2RecovFacProf[j] + this.w2RecovFacProfRes[j - 1];
                if (this.w2RecovFacFacMinDown[j] < this.w2RecovFacFacMinDown[j - 1]) {
                    this.w2RecovFacProfMin[j] = this.w2RecovFacFacMinDown[j];
                } else {
                    this.w2RecovFacProfMin[j] = this.w2RecovFacProfMin[j - 1];
                }
                this.w2WinKooProfRes[j] = this.w2WinKooProf[j] + this.w2WinKooProfRes[j - 1];
                if (this.w2WinKooMinDown[j] < this.w2WinKooMinDown[j - 1]) {
                    this.w2WinKooProfMin[j] = this.w2WinKooMinDown[j];
                } else {
                    this.w2WinKooProfMin[j] = this.w2WinKooProfMin[j - 1];
                }
                this.w2ProcWinDeProfRes[j] = this.w2ProcWinDeProf[j] + w2ProcWinDeProfRes[j - 1];
                if (this.w2ProcWinDeMinDown[j] < this.w2ProcWinDeMinDown[j - 1]) {
                    this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeMinDown[j];
                } else {
                    this.w2ProcWinDeProfMin[j] = this.w2ProcWinDeProfMin[j - 1];
                }
                this.w4ProfProfRes[j] = this.w4ProfProf[j] + this.w4ProfProfRes[j - 1];
                if (this.w4ProfMinDown[j] < this.w4ProfMinDown[j - 1]) {
                    this.w4ProfProfMin[j] = this.w4ProfMinDown[j];
                } else {
                    this.w4ProfProfMin[j] = this.w4ProfProfMin[j - 1];
                }
                this.w4ProfFacProfRes[j] = this.w4ProfFacProf[j] + this.w4ProfFacProfRes[j - 1];
                if (this.w4ProfFacMinDown[j] < this.w4ProfFacMinDown[j - 1]) {
                    this.w4ProfFacProfMin[j] = this.w4ProfFacMinDown[j];
                } else {
                    this.w4ProfFacProfMin[j] = this.w4ProfFacProfMin[j - 1];
                }
                this.w4RecovFacProfRes[j] = this.w4RecovFacProf[j] + this.w4RecovFacProfRes[j - 1];
                if (this.w4RecovFacFacMinDown[j] < this.w4RecovFacFacMinDown[j - 1]) {
                    this.w4RecovFacProfMin[j] = this.w4RecovFacFacMinDown[j];
                } else {
                    this.w4RecovFacProfMin[j] = this.w4RecovFacProfMin[j - 1];
                }
                this.w4WinKooProfRes[j] = this.w4WinKooProf[j] + this.w4WinKooProfRes[j - 1];
                if (this.w4WinKooMinDown[j] < this.w4WinKooMinDown[j - 1]) {
                    this.w4WinKooProfMin[j] = this.w4WinKooMinDown[j];
                } else {
                    this.w4WinKooProfMin[j] = this.w4WinKooProfMin[j - 1];
                }
                this.w4ProcWinDeProfRes[j] = this.w4ProcWinDeProf[j] + this.w4ProcWinDeProfRes[j - 1];
                if (this.w4ProcWinDeMinDown[j] < this.w4ProcWinDeMinDown[j - 1]) {
                    this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeMinDown[j];
                } else {
                    this.w4ProcWinDeProfMin[j] = this.w4ProcWinDeProfMin[j - 1];
                }
            }
        }
    }

    public void simpleWFOToFile(String pathToFolder) throws IOException {
        StringBuilder wholeTable = new StringBuilder();
        String fileName = String.format("rob%s%s.csv", this.timeFrame, this.ticker);
        Path path = Paths.get(pathToFolder + GlobalTest.ROB_NAME);
        if (!Files.exists(path)) {
            new File(pathToFolder + GlobalTest.ROB_NAME).mkdir();
        }
        FileWriter writer = new FileWriter(pathToFolder + GlobalTest.ROB_NAME + "\\" + fileName);
        for (int i = 0; i < this.w1ProfProf.length; i++) {
            //String line = new String("");
            DecimalFormatSymbols symbols = new DecimalFormatSymbols();
            symbols.setDecimalSeparator(',');
            DecimalFormat df1 = new DecimalFormat("########.##", symbols);
            DecimalFormat df2 = new DecimalFormat("########.###", symbols);
            String n1 = df1.format(w1ProfProf[i]) + ";" + df1.format(w1ProfMinDown[i]) + ";" + df1.format(w1ProfMaxUp[i]) + ";" + df2.format(w1ProfProfFac[i]) + ";" + df2.format(w1ProfRecovFac[i]);
            String n2 = df1.format(w1ProfFacProf[i]) + ";" + df1.format(w1ProfFacMinDown[i]) + ";" + df1.format(w1ProfFacMaxUp[i]) + ";" + df2.format(w1ProfFacProfFac[i]) + ";" + df2.format(w1ProfFacRecovFac[i]);
            String n3 = df1.format(w1RecovFacProf[i]) + ";" + df1.format(w1RecovFacFacMinDown[i]) + ";" + df1.format(w1RecovFacFacMaxUp[i]) + ";" + df2.format(w1RecovFacFacProfFac[i]) + ";" + df2.format(w1RecovFacFacRecovFac[i]);
            String n4 = df1.format(w1WinKooProf[i]) + ";" + df1.format(w1WinKooMinDown[i]) + ";" + df1.format(w1WinKooMaxUp[i]) + ";" + df2.format(w1WinKooProfFac[i]) + ";" + df2.format(w1WinKooRecovFac[i]);
            String n5 = df1.format(w1ProcWinDeProf[i]) + ";" + df1.format(w1ProcWinDeMinDown[i]) + ";" + df1.format(w1ProcWinDeMaxUp[i]) + ";" + df2.format(w1ProcWinDeProfFac[i]) + ";" + df2.format(w1ProcWinDeRecovFac[i]);
            String n6 = df1.format(w2ProfProf[i]) + ";" + df1.format(w2ProfMinDown[i]) + ";" + df1.format(w2ProfMaxUp[i]) + ";" + df2.format(w2ProfProfFac[i]) + ";" + df2.format(w2ProfRecovFac[i]);
            String n7 = df1.format(w2ProfFacProf[i]) + ";" + df1.format(w2ProfFacMinDown[i]) + ";" + df1.format(w2ProfFacMaxUp[i]) + ";" + df2.format(w2ProfFacProfFac[i]) + ";" + df2.format(w2ProfFacRecovFac[i]);
            String n8 = df1.format(w2RecovFacProf[i]) + ";" + df1.format(w2RecovFacFacMinDown[i]) + ";" + df1.format(w2RecovFacFacMaxUp[i]) + ";" + df2.format(w2RecovFacFacProfFac[i]) + ";" + df2.format(w2RecovFacFacRecovFac[i]);
            String n9 = df1.format(w2WinKooProf[i]) + ";" + df1.format(w2WinKooMinDown[i]) + ";" + df1.format(w2WinKooMaxUp[i]) + ";" + df2.format(w2WinKooProfFac[i]) + ";" + df2.format(w2WinKooRecovFac[i]);
            String n10 = df1.format(w2ProcWinDeProf[i]) + ";" + df1.format(w2ProcWinDeMinDown[i]) + ";" + df1.format(w2ProcWinDeMaxUp[i]) + ";" + df2.format(w2ProcWinDeProfFac[i]) + ";" + df2.format(w2ProcWinDeRecovFac[i]);
            String n11 = df1.format(w4ProfProf[i]) + ";" + df1.format(w4ProfMinDown[i]) + ";" + df1.format(w4ProfMaxUp[i]) + ";" + df2.format(w4ProfProfFac[i]) + ";" + df2.format(w4ProfRecovFac[i]);
            String n12 = df1.format(w4ProfFacProf[i]) + ";" + df1.format(w4ProfFacMinDown[i]) + ";" + df1.format(w4ProfFacMaxUp[i]) + ";" + df2.format(w4ProfFacProfFac[i]) + ";" + df2.format(w4ProfFacRecovFac[i]);
            String n13 = df1.format(w4RecovFacProf[i]) + ";" + df1.format(w4RecovFacFacMinDown[i]) + ";" + df1.format(w4RecovFacFacMaxUp[i]) + ";" + df2.format(w4RecovFacFacProfFac[i]) + ";" + df2.format(w4RecovFacFacRecovFac[i]);
            String n14 = df1.format(w4WinKooProf[i]) + ";" + df1.format(w4WinKooMinDown[i]) + ";" + df1.format(w4WinKooMaxUp[i]) + ";" + df2.format(w4WinKooProfFac[i]) + ";" + df2.format(w4WinKooRecovFac[i]);
            String n15 = df1.format(w4ProcWinDeProf[i]) + ";" + df1.format(w4ProcWinDeMinDown[i]) + ";" + df1.format(w4ProcWinDeMaxUp[i]) + ";" + df2.format(w4ProcWinDeProfFac[i]) + ";" + df2.format(w4ProcWinDeRecovFac[i]);

            String line = n1 + ";" + n2 + ";" + n3 + ";" + n4 + ";" + n5 + ";" + n6 + ";" +
                    n7 + ";" + n8 + ";" + n9 + ";" + n10 + ";" + n11 + ";" + n12 + ";" +
                    n13 + ";" + n14 + ";" + n15 + "\n";

            wholeTable.append(line);

        }
        writer.write(wholeTable.toString());
        writer.close();

    }
}
