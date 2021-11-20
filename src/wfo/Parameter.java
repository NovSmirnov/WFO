package wfo;

/**
 * Содержит значения параметров и их названия (не более 10 параметров)
 */
public class Parameter {
    /**
     * Здесь и далее значение параметра.
     */
    protected double par1;
    protected double par2;
    protected double par3;
    protected double par4;
    protected double par5;
    protected double par6;
    protected double par7;
    protected double par8;
    protected double par9;
    protected double par10;
    /**
     * Здесь и далее название параметра
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
     * Здесь и далее.
     * Сеттеры для параметров.
     * @param par1 Значение параметра 1.
     * @param parName1 Назавание параметра 1.
     */
    public void setPar(double par1, StringBuilder parName1) {
        this.par1 = par1;
        this.parName1 = parName1;
    }

    public void setPar(double par1, double par2, StringBuilder parName1, StringBuilder parName2) {
        this.par1 = par1;
        this.par2 = par2;
        this.parName1 = parName1;
        this.parName2 = parName2;
    }

    public void setPar(double par1, double par2, double par3,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
    }

    public void setPar(double par1, double par2, double par3, double par4,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
    }

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
    }

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       double par6,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                       StringBuilder parName6) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.par6 = par6;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
    }

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       double par6, double par7,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                       StringBuilder parName6, StringBuilder parName7) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.par6 = par6;
        this.par7 = par7;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
    }

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       double par6, double par7, double par8,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                       StringBuilder parName6, StringBuilder parName7, StringBuilder parName8) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.par6 = par6;
        this.par7 = par7;
        this.par8 = par8;
        this.parName1 = parName1;
        this.parName2 = parName2;
        this.parName3 = parName3;
        this.parName4 = parName4;
        this.parName5 = parName5;
        this.parName6 = parName6;
        this.parName7 = parName7;
        this.parName8 = parName8;

    }

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       double par6, double par7, double par8, double par9,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                       StringBuilder parName6, StringBuilder parName7, StringBuilder parName8, StringBuilder parName9) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.par6 = par6;
        this.par7 = par7;
        this.par8 = par8;
        this.par9 = par9;
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

    public void setPar(double par1, double par2, double par3, double par4, double par5,
                       double par6, double par7, double par8, double par9, double par10,
                       StringBuilder parName1, StringBuilder parName2, StringBuilder parName3, StringBuilder parName4, StringBuilder parName5,
                       StringBuilder parName6, StringBuilder parName7, StringBuilder parName8, StringBuilder parName9, StringBuilder parName10) {
        this.par1 = par1;
        this.par2 = par2;
        this.par3 = par3;
        this.par4 = par4;
        this.par5 = par5;
        this.par6 = par6;
        this.par7 = par7;
        this.par8 = par8;
        this.par9 = par9;
        this.par10 = par10;
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

    /**
     * Проверяет наличие хотя бы одного параметра в объекте класса.
     * @return True - объект содержит параметры, False  - не содержит.
     */
    public boolean checkPar() {
        double a = 0.0;
        boolean b = false;
        a = par1 + par2 + par3 + par4 + par5 + par6 + par7 + par8 + par9 + par10;
        if (a == 0.0) {
            b = false;
        } else {
            b = true;
        }
        return b;
    }
}
