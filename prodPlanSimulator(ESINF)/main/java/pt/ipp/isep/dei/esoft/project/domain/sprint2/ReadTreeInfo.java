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
}
