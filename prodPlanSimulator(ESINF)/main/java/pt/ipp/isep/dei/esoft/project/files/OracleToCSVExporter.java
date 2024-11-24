package pt.ipp.isep.dei.esoft.project.files;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class OracleToCSVExporter {

    // Oracle DB credentials
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "C##manager";
    private static final String PASSWORD = "manager123";

    // Map of queries and their corresponding output file paths
    private static final Map<String, String> QUERIES_AND_FILES = new HashMap<>();
    private static final Map<String, String> QUERIE_BOO_TESTE = new HashMap<>();


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


        QUERIES_AND_FILES.put(
                "SELECT OPERATION_ID AS \"op_id\", DESCRIPTION AS \"name\" FROM OPERATION",
                "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/files/OPERATION.csv"
        );
        QUERIES_AND_FILES.put("SELECT * FROM OPERATION_TYPE", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/OPERATION_TYPE.csv");
        QUERIES_AND_FILES.put("SELECT * FROM \"Order\"", "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/Order.csv");
        QUERIES_AND_FILES.put(
                "SELECT PARTNUMBER AS \"id_item\", DESCRIPTION AS \"item_name\" FROM Part",
                "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/files/ITEMS.csv"
        );
        QUERIE_BOO_TESTE.put("SELECT "
                        + "BO.OperationOPERATION_ID AS \"op_id\", "
                        + "BO.PartPARTNUMBER AS \"item_id\", "
                        + "SUM(BO.QUANTITY) AS \"item_qtd\", "
                        + "O.NEXTSTEP AS \"op1\", "
                        + "SUM(BO_NEXT.QUANTITY) AS \"op_qtd1\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 1 THEN BI.PartPARTNUMBER END) AS \"item_id1\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 1 THEN BI.QUANTITY END) AS \"item_qtd1\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 2 THEN BI.PartPARTNUMBER END) AS \"item_id2\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 2 THEN BI.QUANTITY END) AS \"item_qtd2\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 3 THEN BI.PartPARTNUMBER END) AS \"item_id3\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 3 THEN BI.QUANTITY END) AS \"item_qtd3\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 4 THEN BI.PartPARTNUMBER END) AS \"item_id4\", "
                        + "MAX(CASE WHEN BI.ROW_NUM = 4 THEN BI.QUANTITY END) AS \"item_qtd4\", "
                        + "O.BOOProductPRODUCT_ID AS \"product_id\" "
                        + "FROM Operation O "
                        + "JOIN BOO_OUTPUT BO ON O.OPERATION_ID = BO.OperationOPERATION_ID "
                        + "LEFT JOIN Operation O_NEXT ON O.NEXTSTEP = O_NEXT.OPERATION_ID "
                        + "LEFT JOIN BOO_OUTPUT BO_NEXT ON O_NEXT.OPERATION_ID = BO_NEXT.OperationOPERATION_ID "
                        + "LEFT JOIN (SELECT BI.OperationOPERATION_ID, BI.PartPARTNUMBER, BI.QUANTITY, "
                        + "ROW_NUMBER() OVER (PARTITION BY BI.OperationOPERATION_ID ORDER BY BI.PartPARTNUMBER) AS ROW_NUM "
                        + "FROM BOO_INPUT BI) BI ON O.OPERATION_ID = BI.OperationOPERATION_ID "
                        + "GROUP BY BO.OperationOPERATION_ID, BO.PartPARTNUMBER, O.NEXTSTEP, O.BOOProductPRODUCT_ID "
                        + "ORDER BY BO.OperationOPERATION_ID, O.NEXTSTEP, O.BOOProductPRODUCT_ID",
                "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/files/");
    }

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connected to the Oracle database.");

            // Loop through each query and export its result to a CSV
            for (Map.Entry<String, String> entry : QUERIES_AND_FILES.entrySet()) {
                exportQueryToCSV(connection, entry.getKey(), entry.getValue());
            }

            for (Map.Entry<String, String> entry : QUERIE_BOO_TESTE.entrySet()) {
                exportQueryToCSVBOO(connection, entry.getKey(), entry.getValue());
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

    private static void exportQueryToCSVBOO(Connection connection, String query, String outputPath) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("Exporting data for query: " + query);

            // Create a map to hold the groups, using product_id as the key
            Map<String, List<String>> groups = new LinkedHashMap<>();
            String currentGroup = null;

            // Read and group data rows by product_id
            while (resultSet.next()) {
                String productId = resultSet.getString("product_id");

                if (currentGroup == null || !currentGroup.equals(productId)) {
                    // Start a new group when product_id changes
                    currentGroup = productId;
                    groups.put(currentGroup, new ArrayList<>());
                }

                // Build the row
                StringBuilder row = new StringBuilder();
                row.append(resultSet.getString("op_id")).append(";")
                        .append(resultSet.getString("item_id")).append(";")
                        .append(resultSet.getString("item_qtd")).append(";")
                        .append("(;").append(resultSet.getString("op1") != null ? resultSet.getString("op1") : "").append(";")
                        .append(resultSet.getString("op_qtd1") != null ? resultSet.getString("op_qtd1") : "").append(";);")
                        .append("(;").append(resultSet.getString("item_id1") != null ? resultSet.getString("item_id1") : "").append(";")
                        .append(resultSet.getString("item_qtd1") != null ? resultSet.getString("item_qtd1") : "").append(";")
                        .append(resultSet.getString("item_id2") != null ? resultSet.getString("item_id2") : "").append(";")
                        .append(resultSet.getString("item_qtd2") != null ? resultSet.getString("item_qtd2") : "").append(";")
                        .append(resultSet.getString("item_id3") != null ? resultSet.getString("item_id3") : "").append(";")
                        .append(resultSet.getString("item_qtd3") != null ? resultSet.getString("item_qtd3") : "").append(";")
                        .append(resultSet.getString("item_id4") != null ? resultSet.getString("item_id4") : "").append(";")
                        .append(resultSet.getString("item_qtd4") != null ? resultSet.getString("item_qtd4") : "").append(";);");

                // Add the row to the corresponding group
                groups.get(currentGroup).add(row.toString());
            }

            // Write each group to a separate file
            int fileCounter = 1;
            for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
                String fileName = outputPath + "/BOO_" + entry.getKey() + ".csv";
                try (FileWriter csvWriter = new FileWriter(fileName)) {
                    // Write header
                    csvWriter.append("op_id;item_id;item_qtd;(;op1;op_qtd1;);(;item_id1;item_qtd1;item_id2;item_qtd2;item_id3;item_qtd3;item_id4;item_qtd4;);\n");

                    // Write rows for the current group
                    for (String row : entry.getValue()) {
                        csvWriter.append(row).append("\n");
                    }

                    System.out.println("Data for product " + entry.getKey() + " exported to " + fileName);
                    fileCounter++;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error executing query: " + query + ". Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("File writing error for query: " + query + ". Error: " + e.getMessage());
        }
    }

}
