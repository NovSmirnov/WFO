package wfo;
import java.time.LocalDate;

/**
 * ������������ ��� ���������� �������� � ������.
 */
public class Dates {

        /**
         * �������� �������� ����, ������� ���������� �������� � ���������� ���������� ��� ��������� ����.
         */
	private int finDate;

        /**
         * �������� �� ���� ������������ ���������� ����.
         * @param date ��������� ����.
         * @param days ���������� ����, ������� ���������� �������.
         */
	public void daysMinus(int date, int days) {
        String str = String.valueOf(date);
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        int iyyyy = Integer.parseInt(yyyy);
        int imm = Integer.parseInt(mm);
        int idd = Integer.parseInt(dd);
        LocalDate b = LocalDate.of(iyyyy, imm, idd).minusDays(days);
        String vv = String.valueOf(b);
        yyyy = vv.substring(0, 4);
        mm = vv.substring(5, 7);
        dd = vv.substring(8, 10);
        String fullDate = (yyyy + mm + dd);
        int finDate = Integer.parseInt(fullDate);
        this.finDate = finDate;
	}

        /**
         * �������� �� ���� ������������ ���������� ������.
         * @param date ��������� ����.
         * @param weeks ���������� ������, ������� ���������� �������
         */
	public void weeksMinus(int date, int weeks) {
        String str = String.valueOf(date);
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        int iyyyy = Integer.parseInt(yyyy);
        int imm = Integer.parseInt(mm);
        int idd = Integer.parseInt(dd);
        LocalDate b = LocalDate.of(iyyyy, imm, idd).minusWeeks(weeks);
        String vv = String.valueOf(b);
        yyyy = vv.substring(0, 4);
        mm = vv.substring(5, 7);
        dd = vv.substring(8, 10);
        String fullDate = (yyyy + mm + dd);
        int finDate = Integer.parseInt(fullDate);
        this.finDate = finDate;
	}

        /**
         * ���������� � ���� ������������ ���������� ����.
         * @param date ��������� ����.
         * @param days ���������� ����, ������� ���������� ���������.
         */
	public void daysPlus(int date, int days) {
        String str = String.valueOf(date);
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        int iyyyy = Integer.parseInt(yyyy);
        int imm = Integer.parseInt(mm);
        int idd = Integer.parseInt(dd);
        LocalDate b = LocalDate.of(iyyyy, imm, idd).plusDays(days);
        String vv = String.valueOf(b);
        yyyy = vv.substring(0, 4);
        mm = vv.substring(5, 7);
        dd = vv.substring(8, 10);
        String fullDate = (yyyy + mm + dd);
        int finDate = Integer.parseInt(fullDate);
        this.finDate = finDate;
	}

        /**
         * ���������� � ���� ������������ ���������� ������.
         * @param date ��������� ����.
         * @param weeks ���������� ������, ������� ���������� ���������.
         */
	public void weeksPlus(int date, int weeks) {
        String str = String.valueOf(date);
        String yyyy = str.substring(0, 4);
        String mm = str.substring(4, 6);
        String dd = str.substring(6, 8);
        int iyyyy = Integer.parseInt(yyyy);
        int imm = Integer.parseInt(mm);
        int idd = Integer.parseInt(dd);
        LocalDate b = LocalDate.of(iyyyy, imm, idd).plusWeeks(weeks);
        String vv = String.valueOf(b);
        yyyy = vv.substring(0, 4);
        mm = vv.substring(5, 7);
        dd = vv.substring(8, 10);
        String fullDate = (yyyy + mm + dd);
        int finDate = Integer.parseInt(fullDate);
        this.finDate = finDate;
	}

        /**
         * ���������� �������� ����.
         * @return �������� ����.
         */
	public int getDate() {
		return finDate;
	}
}
