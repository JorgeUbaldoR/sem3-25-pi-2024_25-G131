package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDataReader {
    private static final int NUMBER_OF_DETAILS = 3;
    private static final int BOM_DETAILS = 2;

    /**
     * Reads machine details from a CSV file and returns them as a list of string arrays
     *
     * @return List of string arrays containing machine details
     * @throws IOException if an error occurs while reading the file
     */
    public static List<String[]> getMachinesDetails() throws IOException {
        Scanner scanner = new Scanner(new File("prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/workstations.csv"));
        List<String[]> machineDetails = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");

            if (parts.length == NUMBER_OF_DETAILS) {
                machineDetails.add(parts);
            }
        }
        scanner.close();
        return machineDetails;
    }

    /**
     * Reads item details from a CSV file and returns them as a list of string arrays
     *
     * @return List of string arrays containing item details
     * @throws IOException if the file is not found or another I/O error occurs
     */
    public static List<String[]> getItemsDetails() throws IOException {
        File file = new File("prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/articlesFinal.csv");

        Scanner scanner = new Scanner(file);
        List<String[]> itemsDetails = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");

            if (parts.length >= NUMBER_OF_DETAILS) {
                itemsDetails.add(parts);
            }
        }

        scanner.close();
        return itemsDetails;
    }



    public static List<String[]> getBomDetails() throws IOException {
        File file = new File("prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/productStructure.csv");

        Scanner scanner = new Scanner(file);
        List<String[]> bom = new ArrayList<>();

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");

            if (parts.length == (BOM_DETAILS)) {
                bom.add(parts);
            }
        }

        scanner.close();
        return bom;
    }
}

