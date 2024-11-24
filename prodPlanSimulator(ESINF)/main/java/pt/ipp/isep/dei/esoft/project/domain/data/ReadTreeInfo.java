package pt.ipp.isep.dei.esoft.project.domain.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Utility class for reading tree information from files.
 * Provides methods to parse data related to operations and items.
 */
public class ReadTreeInfo {

    /** Constant representing the expected number of details in a line for items or operations. */
    private static final int NUMBER_OF_DETAILS = 2;

    /**
     * Reads a file containing operations or item details and returns them as a list of string arrays.
     * Each array represents a line split by semicolons (`;`).
     *
     * @param path the path to the file to be read.
     * @return a list of string arrays where each array represents an item's or operation's details.
     * @throws IOException if there is an error accessing or reading the file.
     */
    public static List<String[]> getOpOrItem(String path) throws IOException {
        Scanner scanner = new Scanner(new File(path));
        List<String[]> itemsDetails = new ArrayList<>();

//         Skip the header line if it exists.
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        // Read each line, split by semicolon, and validate the length before adding to the list.
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

    /**
     * Reads a file containing data in a specific format and returns a list of string arrays.
     * Parses details about operations, sub-items, and other related information.
     *
     * The file format assumes:
     * - The first line is a header and is skipped.
     * - Subsequent lines have a main part split by semicolon (`;`) and other sections wrapped in parentheses `()`.
     *
     * @param pathBoo the path to the file to be read.
     * @return a list of string arrays where:
     *         - The first array contains the primary details (e.g., operation ID, item ID, etc.).
     *         - The second array contains operation details extracted from parentheses.
     *         - The third array contains sub-item details extracted from parentheses.
     * @throws IOException if there is an error accessing or reading the file.
     */
    public static List<String[]> readBoo(String pathBoo) throws IOException {
        Scanner scanner = new Scanner(new File(pathBoo));
        List<String[]> leituras = new ArrayList<>();

        // Skip the header line if it exists.
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        // Read each line and process its structure.
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\("); // Split line into sections by `(`.
            String[] firstThreeValues = parts[0].split(";"); // Main part before the first `(`.

            ArrayList<String> operationList = new ArrayList<>();
            ArrayList<String> subItemList = new ArrayList<>();

            // Process each section wrapped in parentheses.
            for (int i = 1; i < parts.length; i++) {
                String conteudo = parts[i].split("\\)")[0]; // Extract content within parentheses.
                String[] valores = conteudo.split(";"); // Split by semicolon within parentheses.

                // Add to operation list or sub-item list based on the section index.
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

            // Convert lists to arrays for consistency.
            String[] arrayOperations = operationList.toArray(new String[0]);
            String[] arrayItems = subItemList.toArray(new String[0]);

            // Add parsed components to the output list.
            leituras.add(firstThreeValues);
            leituras.add(arrayOperations);
            leituras.add(arrayItems);
        }

        scanner.close();
        return leituras;
    }
}
