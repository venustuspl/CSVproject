import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;


public class Main {


    public static void main(String[] args) throws IOException {


        Reader in = new FileReader("happiness.csv");
        String nazwaPliku = "result.txt";

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        List<String> positiveCountryList = new ArrayList();
        Map<String, String> freedomMap = new TreeMap<>(Collections.reverseOrder());
        Map<String, String> corruptionMap = new TreeMap<>(Collections.reverseOrder());

        for (CSVRecord record : records) {

            freedomMap.put(record.get(6), record.get(0));
            corruptionMap.put(record.get(7), record.get(0));
            if (Integer.valueOf(record.get(2)) > 76) {
                positiveCountryList.add(record.get(0));

            }
        }
        try {
            PrintWriter out = new PrintWriter(nazwaPliku);
            out.append(" Pozytywne kraje: " + "\n");
            for (String entry : positiveCountryList) {
                out.append(entry + "\n");
            }

            out.append(" Wolne kraje: " + "\n");

            int i = 0;
            for (Map.Entry<String, String> entry : freedomMap.entrySet()) {
                out.append(entry.getValue() + entry.getKey() + "\n");
                i++;
                if (i == 5) {
                    i = 0;
                    break;

                }
            }
            out.append(" Kraje o dużej korupcji: " + "\n");

            for (Map.Entry<String, String> entry : corruptionMap.entrySet()) {
                out.append(entry.getValue() + entry.getKey() + "\n");
                i++;
                if (i == 5) {
                    i = 0;
                    break;

                }
            }
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Niestety, nie mogę utworzyć pliku!");
        }

    }
}