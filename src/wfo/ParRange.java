package wfo;

/**
 * Объект класса принимает и хранит диапазоны параметров и их шаги для тестирования алгоритма с различными праметрами.
 */
public class ParRange {
    /**
     * Здесь и далее массив из трёх этементов, в котором превый элемент - начальное значение параметра, второй элемент -
     * конечное значение параметра, третий элемент - шаг изменения параметра.
     */
    protected double[] parRange1;
    protected double[] parRange2;
    protected double[] parRange3;
    protected double[] parRange4;
    protected double[] parRange5;
    protected double[] parRange6;
    protected double[] parRange7;
    protected double[] parRange8;
    protected double[] parRange9;
    protected double[] parRange10;

    /**
     * Здесь и далее название соотвествующего параметра (по имени)
     */
    protected StringBuilder parName1;
    protected StringBuilder parName2;
    protected StringBuilder parName3;
    protected StringBuilder parName4;
    protected StringBuilder parName5;
    protected StringBuilder parName6;
    protected StringBuilder parName7;
    protected StringBuilder parName8;
    protected StringBuilder parName9;
    protected StringBuilder parName10;

    /**
     * Здесь и далее перегруженный метод заполняет поля объекта.
     * @param parRange1 Диапазон значений параметра и шаг его изменения.
     * @param parName1 Название параметра.
     */
    public void setParRange(double[] parRange1, StringBuilder parName1) {
        this.parRange1 = parRange1;
        this.parName1 = parName1;
    }

    public void setParRange(double[] parRange1, double[] parRange2, StringBuilder parName1, StringBuilder parName2) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parName1 = parName1;
        this.parName2 = parName2;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            double[] parRange6,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                            StringBuilder parName6) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parRange6 = parRange6;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            double[] parRange6, double[] parRange7,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                            StringBuilder parName6, StringBuilder parName7) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parRange6 = parRange6;
        this.parRange7 = parRange7;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            double[] parRange6, double[] parRange7, double[] parRange8,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                            StringBuilder parName6, StringBuilder parName7, StringBuilder parName8) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parRange6 = parRange6;
        this.parRange7 = parRange7;
        this.parRange8 = parRange8;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
        this.parName8 = parName8;

    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            double[] parRange6, double[] parRange7, double[] parRange8, double[] parRange9,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                            StringBuilder parName6, StringBuilder parName7, StringBuilder parName8, StringBuilder parName9) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parRange6 = parRange6;
        this.parRange7 = parRange7;
        this.parRange8 = parRange8;
        this.parRange9 = parRange9;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
        this.parName8 = parName8;
        this.parName9 = parName9;
    }

    public void setParRange(double[] parRange1, double[] parRange2, double[] parRange3, double[] parRange4, double[] parRange5,
                            double[] parRange6, double[] parRange7, double[] parRange8, double[] parRange9, double[] parRange10,
                            StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                            StringBuilder parName6, StringBuilder parName7, StringBuilder parName8, StringBuilder parName9, StringBuilder parName10) {
        this.parRange1 = parRange1;
        this.parRange2 = parRange2;
        this.parRange3 = parRange3;
        this.parRange4 = parRange4;
        this.parRange5 = parRange5;
        this.parRange6 = parRange6;
        this.parRange7 = parRange7;
        this.parRange8 = parRange8;
        this.parRange9 = parRange9;
        this.parRange10 = parRange10;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
        this.parName8 = parName8;
        this.parName9 = parName9;
        this.parName10 = parName10;
    }

    public double[] getParRange1() {
        return this.parRange1;
    }

    public double[] getParRange2() {
        return this.parRange2;
    }

    public double[] getParRange3() {
        return this.parRange3;
    }

    public double[] getParRange4() {
        return this.parRange4;
    }

    public double[] getParRange5() {
        return this.parRange5;
    }

    public double[] getParRange6() {
        return this.parRange6;
    }

    public double[] getParRange7() {
        return this.parRange7;
    }

    public double[] getParRange8() {
        return this.parRange8;
    }

    public double[] getParRange9() {
        return this.parRange9;
    }

    public double[] getParRange10() {
        return this.parRange10;
    }

    public StringBuilder getParName1() {
        return this.parName1;
    }

    public StringBuilder getParName2() {
        return this.parName2;
    }

    public StringBuilder getParName3() {
        return this.parName3;
    }

    public StringBuilder getParName4() {
        return this.parName4;
    }

    public StringBuilder getParName5() {
        return this.parName5;
    }

    public StringBuilder getParName6() {
        return this.parName6;
    }

    public StringBuilder getParName7() {
        return this.parName7;
    }

    public StringBuilder getParName8() {
        return this.parName8;
    }

    public StringBuilder getParName9() {
        return this.parName9;
    }

    public StringBuilder getParName10() {
        return this.parName10;
    }

    /**
     * Проверяет, сколько диапазонов параметров содержит объект класса.
     * @return
     */
    public int checkQuPar() {
        int n = 0;
        int[] p = new int[10];

        if (this.parRange1 != null) {
            p[0] = 1;
        }
        if (this.parRange2 != null) {
            p[1] = 1;
        }
        if (this.parRange3 != null) {
            p[2] = 1;
        }
        if (this.parRange4 != null) {
            p[3] = 1;
        }
        if (this.parRange5 != null) {
            p[4] = 1;
        }
        if (this.parRange6 != null) {
            p[5] = 1;
        }
        if (this.parRange7 != null) {
            p[6] = 1;
        }
        if (this.parRange8 != null) {
            p[7] = 1;
        }
        if (this.parRange9 != null) {
            p[8] = 1;
        }
        if (this.parRange10 != null) {
            p[9] = 1;
        }
        for (int i = 0; i < 10; i++) {
            n += p[i];
        }
        return n;
    }
}
