package pt.ipp.isep.dei.esoft.project.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileWriter {
    static PrintWriter graphPrintWriter;


    public FileWriter(String path) throws IOException {
        writeToUmlFile(path);
    }

    public static void writeToUmlFile(String path) throws IOException {
        Map<String, List<String>> bom = getProductBoo();
        try {

            for (Map.Entry<String, List<String>> entry : bom.entrySet()) {
                graphPrintWriter = new PrintWriter(path + entry.getKey() + ".puml");
                graphPrintWriter.println("@startuml");
                graphPrintWriter.printf("graph GraphRep {%n");
                for (String line : entry.getValue()) {
                    graphPrintWriter.printf("\"%s\" -- \"%s\"%n", entry.getKey(), line);
                }
                graphPrintWriter.printf("}%n");
                graphPrintWriter.println("@enduml");
                graphPrintWriter.close();

            }

        } catch (FileNotFoundException exception) {
            System.out.println("File write error: " + exception.getMessage());
        }
    }

    private static Map<String, List<String>> getProductBoo() throws IOException {
        Map<String, List<String>> bom = new HashMap<>();
        List<String[]> data = FileDataReader.getBomDetails();
        System.out.println(data.size());
        for (String[] line : data) {
            String productName = line[0];
            String componentName = line[1];

            if (!bom.containsKey(productName)) {
                bom.put(productName, new ArrayList<>());
                bom.get(productName).add(componentName);
            } else {
                bom.get(productName).add(componentName);
            }

        }
        return bom;
    }


}
