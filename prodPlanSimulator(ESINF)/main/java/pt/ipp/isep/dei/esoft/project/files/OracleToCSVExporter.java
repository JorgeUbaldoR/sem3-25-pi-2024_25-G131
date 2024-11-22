package pt.ipp.isep.dei.esoft.project.files;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OracleToCSVExporter {

    // Oracle DB credentials
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "C##manager";
    private static final String PASSWORD = "manager123";

    // Map of queries and their corresponding output file paths
    private static final Map<String, String> QUERIES_AND_FILES = new HashMap<>();

    static {
        // Add all queries and their output file paths
        QUERIES_AND_FILES.put("SELECT * FROM BOO", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/BOO.csv");
        QUERIES_AND_FILES.put("SELECT * FROM BOO_INPUT", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/BOO_INPUT.csv");
        QUERIES_AND_FILES.put("SELECT * FROM BOO_OUTPUT", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/BOO_OUTPUT.csv");
        QUERIES_AND_FILES.put("SELECT * FROM BOO_TEMPLATE", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/BOO_TEMPLATE.csv");
        QUERIES_AND_FILES.put("SELECT * FROM COMPONENT", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/COMPONENT.csv");
        QUERIES_AND_FILES.put("SELECT * FROM \"Deactivated Costumers\"", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/Deactivated Costumers.csv");
        QUERIES_AND_FILES.put("SELECT * FROM \"Intermediate Product\"", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/Intermediate Product.csv");
        QUERIES_AND_FILES.put("SELECT * FROM OPERATION", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/OPERATION.csv");
        QUERIES_AND_FILES.put("SELECT * FROM OPERATION_TYPE", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/OPERATION_TYPE.csv");
        QUERIES_AND_FILES.put("SELECT * FROM \"Order\"", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/Order.csv");
        QUERIES_AND_FILES.put("SELECT * FROM PART", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/PART.csv");
        QUERIES_AND_FILES.put("SELECT * FROM PROD_FAMILY", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/PART.csv");
        QUERIES_AND_FILES.put("SELECT * FROM PRODUCT", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/PRODUCT.csv");
        QUERIES_AND_FILES.put("SELECT * FROM PRODUCTION_ORDER", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/PRODUCTION_ORDER.csv");
        QUERIES_AND_FILES.put("SELECT * FROM PRODUCTION_ORDER_WORKSTATION", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/PRODUCTION_ORDER_WORKSTATION.csv");
        QUERIES_AND_FILES.put("SELECT * FROM \"Raw Material\"", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/RAW_MATERIAL.csv");
        QUERIES_AND_FILES.put("SELECT * FROM WORK_STATION", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/WORK_STATION.csv");
        QUERIES_AND_FILES.put("SELECT * FROM WORKSTATION_TYPE", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/WORKSTATION_TYPE.csv");
        QUERIES_AND_FILES.put("SELECT * FROM WORKSTATION_TYPE_OPERATION_TYPE", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/WORKSTATION_TYPE_OPERATION_TYPE.csv");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the Oracle database.");

            // Loop through each query and export its result to a CSV
            for (Map.Entry<String, String> entry : QUERIES_AND_FILES.entrySet()) {
                exportQueryToCSV(connection, entry.getKey(), entry.getValue());
            }

            System.out.println("All data exported successfully!");

        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    private static void exportQueryToCSV(Connection connection, String query, String outputPath) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);
             FileWriter csvWriter = new FileWriter(outputPath)) {

            System.out.println("Exporting data for query: " + query);

            // Write header row
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                csvWriter.append(metaData.getColumnName(i));
                if (i < columnCount) csvWriter.append(",");
            }
            csvWriter.append("\n");

            // Write data rows
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    csvWriter.append(resultSet.getString(i) != null ? resultSet.getString(i) : "");
                    if (i < columnCount) csvWriter.append(",");
                }
                csvWriter.append("\n");
            }

            System.out.println("Data exported successfully to " + outputPath);

        } catch (SQLException e) {
            System.err.println("Error executing query: " + query + ". Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File writing error for query: " + query + ". Error: " + e.getMessage());
        }
    }
}
