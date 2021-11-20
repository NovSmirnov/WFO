package wfo;
import java.time.LocalDate;

/**
 * ѕредназначен дл€ выполенни€ операций с датами.
 */
public class Dates {

        /**
         * —одержит итоговую дату, которую необходимо получить в результате прибвлени€ или вычитани€ дней.
         */
	private int finDate;

        /**
         * ¬ычитает из даты определенное количество дней.
         * @param date стартова€ дата.
         * @param days количество дней, которое необходимо вычесть.
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
         * ¬ычитает из даты определенное количество недель.
         * @param date стартова€ дата.
         * @param weeks количество недель, которое необходимо вычесть
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
         * ѕрибавл€ет к дате определенное количество дней.
         * @param date стартова€ дата.
         * @param days количество дней, которое необходимо прибавить.
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
         * ѕрибавл€ет к дате определенное количество недель.
         * @param date стартова€ дата.
         * @param weeks количество недель, которое необходимо прибавить.
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
         * ¬озвращает итоговую дату.
         * @return итоговую дату.
         */
	public int getDate() {
		return finDate;
	}
}
