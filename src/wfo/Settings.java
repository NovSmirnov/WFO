package wfo;

public class Settings {
    public final static int FILT_1_WEEKS = 15;
    public final static int FILT_2_WEEKS = 30;
    public final static int FILT_4_WEEKS = 60;

    public final static boolean WFO_TO_FILE = false;
    public final static boolean TIME_LIMITS = true;

    public final static int QU_WEEKS = 72;
    public final static int[] LEARN_WEEKS_ARR = new int[] {1, 2, 4};
    public final static int TEST_WEEKS = 1;

    static int emptyCores = 4; // количество ядер процессора, которое необходимо оставить свободными при работе программы
    static int quCores = Runtime.getRuntime().availableProcessors(); // вычисление количества ядер (потоков) процессора
    public static int quThreads = quCores - emptyCores;


    public final static int START_TIME_MONDAY = 90000;
    public final static int START_EVERYDAY = 71500;
    public final static int AFTER_JOINT_START = 120000;
    public final static int FINISH_TIME_FRIDAY = 180000;
    public final static int[] PRE_HOLIDAYS = {20200611, 20201103, 20201230, 20210106, 20211103, 20213012};
    public final static int[] AFTER_HOLIDAYS_DAYS = {20200615, 20201105, 20210104, 20210111, 20210309, 20210614, 20211108,20220103};
    public final static int[] PRE_JOINT_DAYS = {20200306, 20200609, 20200909, 20201209, 20210309,
            20210609, 20210909, 20211209, 20220309,	20220609, 20220909, 20221209};
    public final static int[] AFTER_JOINT_DAYS = {20200310, 20200610, 20200910, 20201210, 20210310,
            20210610, 20210910, 20211210, 20220310,	20220610, 20220912, 20221212};
    public final static int[] PRE_JOINT_BR_DAYS = {20200124, 20200221, 20200324, 20200424, 20200522, 20200624,
            20200724, 20200824, 20200924, 20201023, 20201124, 20201224, 20210122, 20210224, 20210324, 20210423,
            20210524, 20210624, 20210723, 20210824, 20210924, 20211022, 20211124, 20211224, 20220124, 20220224,
            20220324, 20220422, 20220524, 20220624};
    public final static int[] AFTER_JOINT_BR_DAYS = {20200127, 20200225, 20200325, 20200427, 20200525, 20200625,
            20200727, 20200825, 20200925, 20201026, 20201225, 20210125, 20210225, 20210325, 20210426, 20210525,
            20210625, 20210726, 20210825, 20210927, 20211025, 20211125, 20211227, 20220125, 20220225, 20220325,
            20220425, 20220525, 20220627};

}
