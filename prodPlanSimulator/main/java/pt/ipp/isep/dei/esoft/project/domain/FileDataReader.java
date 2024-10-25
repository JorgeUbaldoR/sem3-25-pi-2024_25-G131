package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileDataReader {
    private static final int NUMBER_OF_DETAILS = 3;
    private static final int BOM_DETAILS = 2;
    private static String fileName = null;
    private static final Scanner ler = new Scanner(System.in);
    private static final int ID_MACHINE = 0;
    private static final int ID_ITEM = 1;

    private static final Boolean LEITURA_AUTOMATICA = false;

    /**
     * Reads machine details from a CSV file and returns them as a list of string arrays
     *
     * @return List of string arrays containing machine details
     * @throws IOException if an error occurs while reading the file
     */
    public static List<String[]> getMachinesDetails() throws IOException {

        if (!LEITURA_AUTOMATICA) {
            System.out.print("Write the name of the MACHINES file: ");
        }

        if (isValidFile(LEITURA_AUTOMATICA, ID_MACHINE)) {
            Scanner scanner = new Scanner(new File(fileName));
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
        return null;
    }


    /**
     *
     * @return  List of string arrays containing product and component
     * @throws IOException if the file is not found or another I/O error occurs
     */
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

    /** Reads details from a CSV file and returns them as a list of string arrays

     * Reads item details from a CSV file and returns them as a list of string arrays
     *
     * @return List of string arrays containing item details
     * @throws IOException if the file is not found or another I/O error occurs
     */
    public static List<String[]> getItemsDetails() throws IOException {

        if (!LEITURA_AUTOMATICA) {
            System.out.print("Write the name of the ITEMS file: ");
        }

        if (isValidFile(LEITURA_AUTOMATICA, ID_ITEM)) {
            Scanner scanner = new Scanner(new File(fileName));
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
        return null;
    }

    private static boolean isValidFile(Boolean leituraAutomatica, int ID) {

    /**
     *
     * @return  List of string arrays containing product and component
     * @throws IOException if the file is not found or another I/O error occurs
     */
        if (!leituraAutomatica) {
            fileName = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/" + ler.nextLine();
        } else {

            if (ID == ID_MACHINE) {
                fileName = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/workstations.csv";
            } else {
                fileName = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/input/articlesFinal.csv";
            }

        }


        // Verifica se o arquivo existe
        try {
            new Scanner(new File(fileName)).close();
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find file: " + fileName);
            return false;
        }
        return true;
    }
}
