import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;


public class Main {

    public static Integer findCountryPositiveAltitude(Iterable<CSVRecord> records, String country) {
        for (CSVRecord record : records) {

            if (record.get(0).equals(country)) {
                System.out.println(record.get(3));
                return Integer.valueOf(record.get(3));

            }
        }
        return 0;
    }


    public static void main(String[] args) throws IOException {

        Reader in = new FileReader("happiness.csv");
        String rusultFile = "result.txt";

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        List<String> positiveCountryList = new ArrayList();
        Map<String, String> freedomMap = new TreeMap<>(Collections.reverseOrder());
        Map<String, String> corruptionMap = new TreeMap<>(Collections.reverseOrder());
        Integer compareValue = Main.findCountryPositiveAltitude(records, "Poland");
        String trialRecord = "";
        for (CSVRecord record : records) {

            freedomMap.put(record.get(6), record.get(0));
            corruptionMap.put(record.get(7), record.get(0));
            trialRecord = record.get(3);
            if (trialRecord.equals("")) {
                trialRecord = "0";
            }
            if (Integer.valueOf(trialRecord) > compareValue) {
                positiveCountryList.add(record.get(0));

            }
        }
        try {
            PrintWriter out = new PrintWriter(rusultFile);
            out.append("\n" + "1. Pozytywne kraje: " + "\n");
            for (String entry : positiveCountryList) {
                out.append(entry + "\n");
            }

            out.append("\n" + "2. Najbardziej wolnościowe kraje: " + "\n");

            int i = 0;
            for (Map.Entry<String, String> entry : freedomMap.entrySet()) {
                out.append(entry.getValue() + " " + entry.getKey() + "\n");
                i++;
                if (i == 5) {
                    i = 0;
                    break;

                }
            }
            out.append("\n" + "3. Kraje o największej korupcji: " + "\n");

            for (Map.Entry<String, String> entry : corruptionMap.entrySet()) {
                out.append(entry.getValue() + " " + entry.getKey() + "\n");
                i++;
                if (i == 5) {
                    i = 0;
                    break;

                }
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Niestety, nie mogłem utworzyć pliku wyjściowego!");
        }

    }
}