package pt.ipp.isep.dei.esoft.project.files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class ArrangeData {


    private static Map<String, Integer> changeItems(String fileName) {
        Map<String, Integer> items = new HashMap<>();
        int pos = 2;
        try {

            Scanner scanner = new Scanner(new File(fileName));
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                items.put(parts[0], pos);
                pos++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);

        }
        return items;
    }

    private static List<String> getChangedBoo(String booFile, String itemFile) {
        Map<String, Integer> items = changeItems(itemFile);
        int i = 0;
        List<String> newLines = new ArrayList<>();


        try {
            Scanner scanner = new Scanner(new File(booFile));
            List<String> saveLines = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                saveLines.add(line);
            }

            for (String line : saveLines) {
                int firstParenIndex = line.indexOf("(");
                int lastParenIndex = line.lastIndexOf(")");
                if (i > 0) {
                    String part1 = line.substring(0, firstParenIndex).trim();
                    String[] parts1 = part1.split(";");


                    for (int j = 0; j < parts1.length; j++) {
                        String part = parts1[j];
                        if (items.containsKey(part)) {
                            parts1[j] = String.valueOf(items.get(part));
                        }
                    }
                    String updatedPart1 = String.join(";", parts1);


                    String part2 = line.substring(firstParenIndex, line.indexOf(")", firstParenIndex) + 1).trim();
                    part2 = part2.replace("(", "(;");
                    String[] parts2 = part2.split(";");
                    for (int j = 0; j < parts2.length; j++) {
                        String part = parts2[j];
                        if (items.containsKey(part)) {
                            parts2[j] = String.valueOf(items.get(part));
                        }
                    }
                    String updatedPart2 = String.join(";", parts2);

                    String part3 = line.substring(line.lastIndexOf("(") + 1, lastParenIndex).trim();
                    String[] parts3 = part3.split(";");
                    for (int j = 0; j < parts3.length; j++) {
                        String part = parts3[j];
                        if (items.containsKey(part)) {
                            parts3[j] = String.valueOf(items.get(part));
                        }
                    }
                    String updatedPart3 = String.join(";", parts3);

                    String updatedLine = updatedPart1 + ";" + updatedPart2 + ";" + "(;" + updatedPart3 + ";)";
                    newLines.add(updatedLine);

                }
                i++;
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + booFile);
        }
        return newLines;
    }

    public static void createChangedBoo(String booFile, String itemFile, String newBoo) {
        List<String> lines = getChangedBoo(booFile, itemFile);
        try {
            PrintWriter writer = new PrintWriter(new File(newBoo));
            writer.println("op_id;item_id;item_qtd;(;op1;op_qtd1;op2;op_qtd2;opN;op_qtdN;);(;item_id1;item_qtd1;item_id1;item_qtd1;item_id1;item_qtd1;)");
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File not found: " + booFile);
        }
    }

    public static void createChangedItem(String itemFile, String newItem) {
        Map<Integer, String> items = new HashMap<>();
        int pos = 2;
        try {
            Scanner scanner = new Scanner(new File(itemFile));
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                items.put((pos), parts[1]);
                pos++;

            }

        } catch (IOException e) {
            System.out.println("File not found: " + itemFile);
        }

        try {
            PrintWriter writer = new PrintWriter(newItem);
            writer.println("id_item;item_name");
            for (Map.Entry<Integer, String> entry : items.entrySet()) {
                writer.println(entry.getKey() + ";" + entry.getValue());
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("File not found: " + newItem);
        }
    }
}
