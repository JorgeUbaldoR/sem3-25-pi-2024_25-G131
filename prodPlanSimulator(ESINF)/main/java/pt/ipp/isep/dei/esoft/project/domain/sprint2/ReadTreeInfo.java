package pt.ipp.isep.dei.esoft.project.domain.sprint2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadTreeInfo {
    private static final int NUMBER_OF_DETAILS = 2;
    private static final Scanner ler = new Scanner(System.in);


    public static List<String[]> getDetails(String path) throws IOException {
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
