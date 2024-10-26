package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileDataReaderTest {

    private static final String TEST_WORKSTATIONS_FILE_PATH = "test_workstations.csv";
    private static final String TEST_ITEMS_FILE_PATH = "test_items.csv";
    private static final int NUMBER_OF_DETAILS = 3;
    private static final String WORKSTATIONS_CSV_CONTENT = "workstation;name_oper;time\n" +
            "ws11;CUT;21\n" +
            "ws12;CUT;25\n" +
            "ws21;DRILL;36\n" +
            "ws31;SCREW;50\n";
    private static final String ITEMS_CSV_CONTENT = "item_code;item_name;item_price\n" +
            "001;Item A;10.0\n" +
            "002;Item B;20.5\n" +
            "003;Item C;15.75\n";




    @BeforeEach
    void setUp() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_WORKSTATIONS_FILE_PATH)) {
            writer.write(WORKSTATIONS_CSV_CONTENT);
        }
        try (FileWriter writer = new FileWriter(TEST_ITEMS_FILE_PATH)) {
            writer.write(ITEMS_CSV_CONTENT);
        }
    }

    @AfterEach
    void tearDown() {
        new File(TEST_WORKSTATIONS_FILE_PATH).delete();
        new File(TEST_ITEMS_FILE_PATH).delete();
    }

    @Test
    void testGetMachinesDetails() throws IOException {
        List<String[]> machineDetails = getMachinesDetails();

        assertEquals(4, machineDetails.size());
        assertArrayEquals(new String[]{"ws11", "CUT", "21"}, machineDetails.get(0));
        assertArrayEquals(new String[]{"ws12", "CUT", "25"}, machineDetails.get(1));
        assertArrayEquals(new String[]{"ws21", "DRILL", "36"}, machineDetails.get(2));
        assertArrayEquals(new String[]{"ws31", "SCREW", "50"}, machineDetails.get(3));
    }

    @Test
    void testGetMachinesDetailsFileNotFound() {
        File file = new File(TEST_WORKSTATIONS_FILE_PATH);
        file.delete();

        IOException exception = assertThrows(IOException.class, () -> {
            getMachinesDetails();
        });

        String expectedMessage = TEST_WORKSTATIONS_FILE_PATH + " (No such file or directory)";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testGetMachinesDetailsEmptyFile() throws IOException {
        Files.write(Paths.get(TEST_WORKSTATIONS_FILE_PATH), "".getBytes());

        List<String[]> machineDetails = getMachinesDetails();
        assertTrue(machineDetails.isEmpty());
    }

    @Test
    void testGetMachinesDetailsIncorrectFormat() throws IOException {
        Files.write(Paths.get(TEST_WORKSTATIONS_FILE_PATH), "ws11;CUT\nws12;CUT;25\n".getBytes());

        List<String[]> machineDetails = getMachinesDetails();
        assertEquals(1, machineDetails.size());
        assertArrayEquals(new String[]{"ws12", "CUT", "25"}, machineDetails.get(0));
    }

    @Test
    void testGetItemsDetails() throws IOException {
        List<String[]> itemsDetails = getItemsDetails();

        assertEquals(3, itemsDetails.size());
        assertArrayEquals(new String[]{"001", "Item A", "10.0"}, itemsDetails.get(0));
        assertArrayEquals(new String[]{"002", "Item B", "20.5"}, itemsDetails.get(1));
        assertArrayEquals(new String[]{"003", "Item C", "15.75"}, itemsDetails.get(2));
    }

    @Test
    void testGetItemsDetailsFileNotFound() {
        File file = new File(TEST_ITEMS_FILE_PATH);
        file.delete();

        IOException exception = assertThrows(IOException.class, () -> {
            getItemsDetails();
        });

        String expectedMessage = "File not found: " + file.getAbsolutePath();
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void testGetItemsDetailsEmptyFile() throws IOException {
        Files.write(Paths.get(TEST_ITEMS_FILE_PATH), "".getBytes());

        List<String[]> itemsDetails = getItemsDetails();
        assertTrue(itemsDetails.isEmpty());
    }

    @Test
    void testGetItemsDetailsIncorrectFormat() throws IOException {
        Files.write(Paths.get(TEST_ITEMS_FILE_PATH), "item_code;item_name\n001;Item A\n002;Item B;20.5\n".getBytes());

        List<String[]> itemsDetails = getItemsDetails();
        assertEquals(1, itemsDetails.size());
        assertArrayEquals(new String[]{"002", "Item B", "20.5"}, itemsDetails.get(0));
    }

    public List<String[]> getMachinesDetails() throws IOException {
        Scanner scanner = new Scanner(new File(TEST_WORKSTATIONS_FILE_PATH));
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


    public List<String[]> getItemsDetails() throws IOException {
        File file = new File(TEST_ITEMS_FILE_PATH);
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

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

    @Test
    void testGetBomDetails() {
        try {
            List<String[]> bomDetails = FileDataReader.getBomDetails();

            assertNotNull(bomDetails, "BOM Details should not be null");
            assertFalse(bomDetails.isEmpty(), "BOM Details list should not be empty");

            assertTrue(bomDetails.stream()
                            .anyMatch(entry -> List.of(entry).equals(List.of("Pro 17 2l pot", "PN12344A21"))),
                    "Should contain 'Pro 17 2l pot, PN12344A21'");

            assertTrue(bomDetails.stream()
                            .anyMatch(entry -> List.of(entry).equals(List.of("Pro 20 3l pot", "PN18544A21"))),
                    "Should contain 'Pro 20 3l pot, PN18544A21'");

            for (String[] entry : bomDetails) {
                assertEquals(2, entry.length, "each bom entry should contain 2 fields");
            }
        } catch (IOException e) {
            fail("IOException should not happen in getBomDetails");
        }
    }

    @Test
    void testIsValidFile_machinePath() {
        int machineID = 0;
        boolean isValid = FileDataReader.isValidFile(machineID);
        assertTrue(isValid, "Machine file should be valid and accessible");
    }

    @Test
    void testIsValidFile_itemPath() {
        int itemID = 1;
        boolean isValid = FileDataReader.isValidFile(itemID);
        assertTrue(isValid, "Item file should be valid and accessible");
    }


}