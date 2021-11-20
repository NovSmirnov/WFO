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
 * ����� ������������ ��� �������� ����������� ���������� ������ ��������� ���������.
 */
public class FinRes extends Working {
    /**
     * ����� ������� ��� ������ �������� ����������� ����������
     */
    protected String[] finResHeader = new String[]{"������ �/�", "����. ��������", "����.������ ����������",
            "���-�� ������", "���-�� ��������� ������", "���-�� ���������� ������", "% ��������� ������",
            "����� �������", "����� ������", "������� ������� �� ���������� ������",
            "������� ������ �� ��������� ������", "������ ������", "������ ��������������", "����. ��������",
            "���-�� ������ ��� �������", "����������� ��������", "��������� �� �������� �������",
            "��������� �� �������� �������"};

    /**
     * ������ �������/������ (�/�).
     */
    protected double genRes;
    /**
     * ������������ �������� �����.
     */
    protected double maxLoss;
    /**
     * ����.������ ����������.
     */
    protected double maxProf;
    /**
     * ���-�� ������.
     */
    protected int quDe;
    /**
     * ���-�� ��������� ������
     */
    protected int quProfDe;
    /**
     * ���-�� ���������� ������
     */
    protected int quLossDe;
    /**
     * % ��������� ������
     */
    protected double procWinDe;
    /**
     * ����� �������
     */
    protected double genProf;
    /**
     * ����� ������
     */
    protected double genLoss;
    /**
     * ������� ������� �� ���� ���������� ������
     */
    protected double avProf;
    /**
     * ������� ������ �� ���� ��������� ������
     */
    protected double avLoss;
    /**
     * ������ ������
     */
    protected double profFac;
    /**
     * ������ ��������������
     */
    protected double recovFac;
    /**
     * ����. ��������
     */
    protected double winKoo;
    /**
     * ���-�� ������ ��� �������
     */
    protected int quDealFilter;
    /**
     * ����������� ��������
     */
    protected double comGen;
    /**
     * ����������� ��������� �� �������� �������� ��������
     */
    protected double curResLo;
    /**
     * ����������� ��������� �� �������� �������� ��������
     */
    protected double curResSh;
    /**
     * �������� �� ������ � �������� ���������, � ��� �� ����������� � ���������������
     */
    protected double com;

    /**
     * ����������� ���������� ��������� ������ ��������� ��������� � ��������� ���� ������� ������.
     */
    public void counter() {
        double curAvPos = 0.0;  // ���������� ������� ���� ������������ ��� ����������� ������� �������� �������
        double avPrice = 0.0;  // ������� ���� ��������
        double curRes = 0.0;  // ��� ��������������� ��������� �� ������ ������
        double genRes = 0.0;  // ���������� ����������� ��������������� ��������� � ��������� ������� �������
        int quDe = 0;  // ����� ���������� ������
        int quLossDe = 0;  // ���������� �������� ������
        int quProfDe = 0;  // ���������� ���������� ������
        int quDealFilter = 0;  // ���������� ������ ��� ����������
        double maxLoss = 0.0;  // ������������ �������� �����
        double maxProf = 0.0;  // ������������ ��������� �����
        double genProf = 0.0;  // ����� ������� (����������� ������ ���������� ������)
        double genLoss = 0.0;  // ����� ������ (����������� ������ ��������� ������)
        double avProf = 0.0;  // ������� ������� �� ���� ���������� ������
        double avLoss = 0.0;  // ������� ������ �� ���� ��������� ������
        double profFac = 0.0;  // ������-������
        double recovFac = 0.0; // ������ ��������������
        double winKoo = 0.0;  // ���������� ��������
        double procWinDe = 0.0;  // ������� ���������� ������
        double comGen = 0.0;  // ����������� ��������
        double curResLo = 0.0;  // ����������� ��������� �� �������� �������� ��������
        double curResSh = 0.0;  // ����������� ��������� �� �������� �������� ��������

        int quanSum = Arrays.stream(this.quan).sum();
        if (quanSum != 0) {
            List<Double> generalResult = new ArrayList<>();
            for (int i = startIndex; i < this.close.length; i++) {
                if (this.pos[i - 1] == 0 && this.pos[i] == 1) { // �������� ������� �������
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (this.close[i] - avPrice) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == 0 && this.pos[i] == -1) { // �������� ������� �������
                    quDealFilter += 1;
                    comGen = comGen + this.com * this.quan[i];
                    avPrice = this.price[i];
                    curAvPos = (avPrice - this.close[i]) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                    //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                } else if (this.pos[i - 1] == 1 && this.pos[i] == 0) { // ������������� �������� ������� �������
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
                } else if (this.pos[i - 1] == -1 && this.pos[i] == 0) { // ������������� �������� �������� �������
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
                } else if ((this.pos[i - 1] == 1 && this.pos[i] == 1) && (this.quan[i - 1] != this.quan[i])) { // ��������� ��������/�������� ������� �������
                    if (this.quan[i - 1] < this.quan[i]) { // ��������� ����� ������� � ����
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i] - this.quan[i - 1]);
                        avPrice = (((avPrice * this.quan[i - 1]) + (this.quan[i] * (this.quan[i] - this.quan[i - 1]))) / this.quan[i]);
                        curAvPos = (this.close[i] - avPrice) * this.quan[i];
                        genRes = curRes + curAvPos - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    } else if (this.quan[i - 1] > this.quan[i]) { // ��������� �������� �������� �������
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
                } else if ((this.pos[i - 1] == -1 && this.pos[i] == -1) && (this.quan[i - 1] != this.quan[i])) { // ��������� ��������/�������� �������� �������
                    if (this.quan[i - 1] < this.quan[i]) { // ��������� ����� ������� � ����
                        quDealFilter += 1;
                        comGen = comGen + this.com * (this.quan[i] - this.quan[i - 1]);
                        avPrice = (((avPrice * this.quan[i - 1]) + (this.quan[i] * (this.quan[i] - this.quan[i - 1]))) / this.quan[i]);
                        curAvPos = (avPrice - this.quan[i]) * this.quan[i];
                        genRes = curRes + curAvPos - comGen;
                        generalResult.add(genRes);
                        //System.out.println(this.date[i] + "; " + this.time[i] + "; " + this.signal[i]);
                    } else if (this.quan[i - 1] > this.quan[i]) { // ��������� �������� �������� �������
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
                } else if (this.pos[i - 1] == 1 && this.pos[i] == -1) { // ��������� ������� �� ����� � ����
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
                } else if (this.pos[i - 1] == -1 && this.pos[i] == 1) { // ��������� ������� �� ����� � ����
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
                } else if (this.pos[i - 1] == 1 && this.pos[i] == 1 && this.quan[i - 1] == this.quan[i]) { // ���� �������� �������� �������
                    curAvPos = (this.close[i] - avPrice) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                } else if (this.pos[i - 1] == -1 && this.pos[i] == -1 && this.quan[i - 1] == this.quan[i]) {  // ���� �������� �������� �������
                    curAvPos = (avPrice - this.close[i]) * this.quan[i];
                    genRes = curRes + curAvPos - comGen;
                    generalResult.add(genRes);
                } else if (this.pos[i - 1] == 0 && this.pos[i] == 0) { // ���� ��������� �������
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
                profFac = genProf / Math.abs(genLoss);  // ������-������
            } else if (genProf == 0) {
                profFac = 0;
            } else {
                profFac = 10000;
            }
            if (maxLoss != 0) {
                recovFac = genRes / Math.abs(maxLoss); // ������ ��������������
            } else {
                recovFac = 10000;
            }
            if (avLoss != 0) {
                winKoo = avProf / Math.abs(avLoss);  // ���������� ��������
            } else if (avProf == 0) {
                winKoo = 0;
            } else {
                winKoo = 10000;
            }
            this.genRes = genRes; // ������ �/�
            this.maxLoss = maxLoss; // ������������ �������� �����
            this.maxProf = maxProf; // ����.������ ����������
            this.quDe = quDe; // ���-�� ������
            this.quProfDe = quProfDe; // ���-�� ��������� ������
            this.quLossDe = quLossDe; // ���������� ���������� ������
            this.quDealFilter = quDealFilter; // ���������� ������ ��� ����������
            this.maxLoss = maxLoss;  // ������������ �������� �����
            this.maxProf = maxProf;  // ������������ ��������� �����
            this.genProf = genProf;  // ����� ������� (����������� ������ ���������� ������)
            this.genLoss = genLoss;  // ����� ������ (����������� ������ ��������� ������)
            this.avProf = avProf;  // ������� ������� �� ���� ���������� ������
            this.avLoss = avLoss;  // ������� ������ �� ���� ��������� ������
            this.profFac = profFac;  // ������-������
            this.recovFac = recovFac; // ������ ��������������
            this.winKoo = winKoo;  // ���������� ��������
            this.procWinDe = procWinDe;  // ������� ���������� ������
            this.comGen = comGen;  // ����������� ��������
            this.curResLo = curResLo;  // ����������� ��������� �� �������� �������� ��������
            this.curResSh = curResSh;  // ����������� ��������� �� �������� �������� ��������
        } else {
            this.genRes = 0.0; // ������ �/�
            this.maxLoss = 0.0; // ������������ �������� �����
            this.maxProf = 0.0; // ����.������ ����������
            this.quDe = 0; // ���-�� ������
            this.quProfDe = 0; // ���-�� ��������� ������
            this.quLossDe = 0; // ���������� ���������� ������
            this.quDealFilter = 0; // ���������� ������ ��� ����������
            this.maxLoss = 0.0;  // ������������ �������� �����
            this.maxProf = 0.0;  // ������������ ��������� �����
            this.genProf = 0.0;  // ����� ������� (����������� ������ ���������� ������)
            this.genLoss = 0.0;  // ����� ������ (����������� ������ ��������� ������)
            this.avProf = 0.0;  // ������� ������� �� ���� ���������� ������
            this.avLoss = 0.0;  // ������� ������ �� ���� ��������� ������
            this.profFac = 0.0;  // ������-������
            this.recovFac = 0.0; // ������ ��������������
            this.winKoo = 0.0;  // ���������� ��������
            this.procWinDe = 0.0;  // ������� ���������� ������
            this.comGen = 0.0;  // ����������� ��������
            this.curResLo = 0.0;  // ����������� ��������� �� �������� �������� ��������
            this.curResSh = 0.0;  // ����������� ��������� �� �������� �������� ��������
        }
    }

    /**
     *  ������� ���������� ���������� ��������� ������ ��������� ��������� � ���� � ���� ������
     * @param pathToFile ���� � ��������� �����
     * @throws IOException ���������� ����������
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
