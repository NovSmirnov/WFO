package dhe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Edentifyer {
    private int jQuDeals;
    private String[][] jDatesTimes;
    private int tQuDeals;
    private String[][] tDatesTimes;

    public String[][] gettDatesTimes() {
        return tDatesTimes;
    }

    public void setJData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Scanner scanner = null;
        String line;
        int index = 0;
        List<String> log = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            log.add(line);
        }
        reader.close();
        this.jQuDeals = (log.size() - 1) / 2;

        this.jDatesTimes = new String[log.size() - 1][2];

        for (int i = 1; i < log.size(); i++) {
            scanner = new Scanner(log.get(i));
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                if (index == 1) {
                    jDatesTimes[i - 1][0] = data;
                } else if (index == 2) {
                    jDatesTimes[i - 1][1] = data;
                }
                index++;
            }
            index = 0;
        }
    }

    public void setTData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Scanner scanner = null;
        String line;
        int index = 0;
        List<String> log = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            log.add(line);
        }
        reader.close();
        this.tQuDeals = log.size() - 1;

        this.tDatesTimes = new String[(log.size() - 1) * 2][2];

        int step = 0;
        for (int i = 1; i < log.size(); i++) {
            scanner = new Scanner(log.get(i));
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                String data = scanner.next();
                String realData;
                if (index == 8) {
                    realData = convDate(data);
                    tDatesTimes[step][0] = realData;
                } else if (index == 9) {
                    realData = convTime(data);
                    tDatesTimes[step][1] = realData;
                } else if (index == 15) {
                    step++;
                    realData = convDate(data);
                    tDatesTimes[step][0] = realData;
                } else if (index == 16) {
                    realData = convTime(data);
                    tDatesTimes[step][1] = realData;
                }
                index++;
            }
            step++;
            index = 0;
        }

    }

    public void tToConsole() {
        System.out.println("Количество сделок TSLab алгоритма: " + tQuDeals);
        for (int i = 0; i < tDatesTimes.length; i++) {
            System.out.println(tDatesTimes[i][0].toString() + " " + tDatesTimes[i][1].toString());
        }
    }

    public void jToConsole() {
        System.out.println("Количество сделок Java алгоритма: " + jQuDeals);
        for (int i = 0; i < jDatesTimes.length; i++) {
            System.out.println(jDatesTimes[i][0].toString() + " " + jDatesTimes[i][1].toString());
        }
    }

    public void algoEquals() {
        if (jDatesTimes.equals(tDatesTimes)) {
            System.out.println("Алгоритмы скорее всего идентичны в работе");
        } else {
            System.out.println("Алгоритмы не идентичны в работе");
        }
    }

    public void findMistake() {
        int len = 0;
        if (jDatesTimes.length >= tDatesTimes.length) {
            len = jDatesTimes.length;
        } else {
            len = tDatesTimes.length;
        }
        boolean allRight = true;
        for (int i = 0; i < len; i++) {
            if (!(jDatesTimes[i][0].equals(gettDatesTimes()[i][0])) || !(jDatesTimes[i][1].equals(gettDatesTimes()[i][1]))) {
                System.out.println("Расхождение начинается со сделки № " + i + 1);
                System.out.println("Сделка на Java алгоритме происходит " + jDatesTimes[i][0] + " числа, в " + jDatesTimes[i][1] +
                " по времени");
                System.out.println("Сделка на TSLab алгоритме происходит " + tDatesTimes[i][0] + " числа, в " + tDatesTimes[i][1] +
                        " по времени");
                allRight = false;
                break;
            }
        }
        if (allRight) {
            System.out.println("Анализ идентичности работы алгоритмов не нашёл различий");
        }

    }

    private static String convDate(String date) {
        String yyyy = date.substring(6, 10);
        String mm = date.substring(3, 5);
        String dd = date.substring(0, 2);
        return yyyy + mm + dd;
    }

    private static String convTime(String time) {
        String hh = time.substring(0, 2);
        String mm = time.substring(3, 5);
        return hh + mm + "00";
    }
}
