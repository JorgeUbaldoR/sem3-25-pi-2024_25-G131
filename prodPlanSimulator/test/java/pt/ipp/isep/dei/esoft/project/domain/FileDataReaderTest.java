package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileDataReaderTest {

    private static final String TEST_MACHINES_FILE = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/maquinas.cvs";
    private static final String TEST_ITEMS_FILE = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/artigos.cvs";

    @BeforeEach
    public void setUp() throws IOException {
        createTestMachinesFile();
        createTestItemsFile();
    }

    @Test
    public void testGetMachinesDetails_ValidFile() throws IOException {
        List<String[]> details = FileDataReader.getMachinesDetails();
        assertEquals(18, details.size());
        assertArrayEquals(new String[]{"1", "Drill", "1.5"}, details.get(0));
        assertArrayEquals(new String[]{"18", "Polish", "0.75"}, details.get(17)); // Última linha
    }

    @Test
    public void testGetItemsDetails_ValidFile() throws IOException {
        List<String[]> details = FileDataReader.getItemsDetails();
        assertEquals(20, details.size());
        assertArrayEquals(new String[]{"101", "HIGH", "Drill", "Cut", "Polish", "Assemble"}, details.get(0));
        assertArrayEquals(new String[]{"120", "HIGH", "Drill", "Test", "Polish", "Cut", "Grind"}, details.get(19)); // Última linha
    }

    @Test
    public void testGetMachinesDetails_FileNotFound() {
        new File(TEST_MACHINES_FILE).delete();
        assertThrows(IOException.class, () -> FileDataReader.getMachinesDetails());
    }

    @Test
    public void testGetItemsDetails_FileNotFound() {
        new File(TEST_ITEMS_FILE).delete();
        assertThrows(IOException.class, () -> FileDataReader.getItemsDetails());
    }

    @Test
    public void testGetMachinesDetails_InvalidFormat() throws IOException {
        createInvalidFormatMachinesFile();
        List<String[]> details = FileDataReader.getMachinesDetails();
        assertEquals(0, details.size());
    }

    @Test
    public void testGetItemsDetails_InvalidFormat() throws IOException {
        createInvalidFormatItemsFile();
        List<String[]> details = FileDataReader.getItemsDetails();
        assertEquals(0, details.size());
    }

    private void createTestMachinesFile() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_MACHINES_FILE)) {
            writer.write("1,Drill,1.5\n");
            writer.write("2,Cut,2.25\n");
            writer.write("3,Weld,0.75\n");
            writer.write("4,Assemble,1.0\n");
            writer.write("5,Cut,1.5\n");
            writer.write("6,Paint,2.5\n");
            writer.write("7,Grind,1.75\n");
            writer.write("8,Test,2.0\n");
            writer.write("9,Polish,0.5\n");
            writer.write("10,Inspect,1.25\n");
            writer.write("11,Drill,2.0\n");
            writer.write("12,Assemble,3.0\n");
            writer.write("13,Paint,1.75\n");
            writer.write("14,Test,2.5\n");
            writer.write("15,Grind,2.0\n");
            writer.write("16,Inspect,1.5\n");
            writer.write("17,Weld,1.25\n");
            writer.write("18,Polish,0.75\n");
        }
    }

    private void createTestItemsFile() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_ITEMS_FILE)) {
            writer.write("101,HIGH,Drill,Cut,Polish,Assemble\n");
            writer.write("102,MEDIUM,Assemble,Weld,Test,Paint\n");
            writer.write("103,LOW,Grind,Cut,Test\n");
            writer.write("104,HIGH,Drill,Assemble,Paint\n");
            writer.write("105,MEDIUM,Weld,Grind,Polish,Inspect\n");
            writer.write("106,LOW,Test,Assemble\n");
            writer.write("107,HIGH,Cut,Weld,Polish,Assemble,Test\n");
            writer.write("108,MEDIUM,Grind,Drill,Inspect\n");
            writer.write("109,LOW,Test,Paint\n");
            writer.write("110,HIGH,Drill,Cut,Polish,Test,Inspect\n");
            writer.write("111,HIGH,Cut,Weld,Paint,Grind,Test\n");
            writer.write("112,MEDIUM,Drill,Polish,Weld,Assemble\n");
            writer.write("113,LOW,Cut,Test,Inspect\n");
            writer.write("114,HIGH,Assemble,Grind,Weld,Polish\n");
            writer.write("115,MEDIUM,Cut,Test,Drill,Inspect\n");
            writer.write("116,LOW,Test,Polish,Grind\n");
            writer.write("117,HIGH,Paint,Assemble,Drill,Cut\n");
            writer.write("118,MEDIUM,Weld,Cut,Polish,Test\n");
            writer.write("119,LOW,Grind,Inspect,Assemble\n");
            writer.write("120,HIGH,Drill,Test,Polish,Cut,Grind\n");
        }
    }

    private void createInvalidFormatMachinesFile() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_MACHINES_FILE)) {
            writer.write("1,Drill\n"); // Falta um campo
        }
    }

    private void createInvalidFormatItemsFile() throws IOException {
        try (FileWriter writer = new FileWriter(TEST_ITEMS_FILE)) {
            writer.write("Artigo 1,Descrição A\n"); // Falta um campo
        }
    }
}