package pt.ipp.isep.dei.esoft.project.domain.sprint2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadTreeInfo {
    private static final int NUMBER_OF_DETAILS = 2;

    public static List<String[]> getOpOrItem(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        List<String[]> itemsDetails = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");

            if (parts.length == NUMBER_OF_DETAILS) {
                itemsDetails.add(parts);
            }
        }
        scanner.close();
        return itemsDetails;
    }

    public static List<String[]> readBoo(String pathBoo) throws IOException{
        Scanner scanner = new Scanner(new File(pathBoo));
        List<String[]> leituras = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\(");
            String[] firstThreeValues = parts[0].split(";");

            ArrayList<String> operationList = new ArrayList<>();
            ArrayList<String> subItemList = new ArrayList<>();

            for (int i = 1; i < parts.length; i++) {
                String conteudo = parts[i].split("\\)")[0];
                String[] valores = conteudo.split(";");
                if (i == 1) {
                    for (String valor : valores) {
                        operationList.add(valor.trim());
                    }
                } else if (i == 2) {
                    for (String valor : valores) {
                        subItemList.add(valor.trim());
                    }
                }
            }
            String[] arrayOperations = operationList.toArray(new String[0]);
            String[] arrayItems = subItemList.toArray(new String[0]);

            leituras.add(firstThreeValues);
            leituras.add(arrayOperations);
            leituras.add(arrayItems);
        }
        return leituras;
    }

}
