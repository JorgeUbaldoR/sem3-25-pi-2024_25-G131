package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDataReader {
    private static final int NUMBER_OF_DETAILS = 3;


    public static List<String[]> getMachinesDetails() throws IOException {
        Scanner scanner = new Scanner(new File("maquinas.csv"));
        List<String[]> machineDetails = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length == NUMBER_OF_DETAILS) {
                machineDetails.add(parts);
            }
        }
        scanner.close();
        return machineDetails;
    }

    public static List<String[]> getItemsDetails() throws IOException {
        Scanner scanner = new Scanner(new File("artigos.csv"));
        List<String[]> itemsDetails = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length >= NUMBER_OF_DETAILS) {
                itemsDetails.add(parts);
            }
        }

        scanner.close();
        return itemsDetails;
    }
}
