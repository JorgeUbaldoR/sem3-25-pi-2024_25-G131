package pt.ipp.isep.dei.esoft.project.files;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class OracleToCSVExporter {

    // Oracle DB credentials
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "C##manager";
    private static final String PASSWORD = "manager123";

    // Query to fetch data
    private static final String BOO = "SELECT * FROM BOO";
    private static final String BOO_INPUT = "SELECT * FROM BOO_INPUT";
    private static final String BOO_OUTPUT = "SELECT * FROM BOO_OUTPUT";
    private static final String BOO_TEMPLATE = "SELECT * FROM BOO_TEMPLATE";
    private static final String COMPONENT = "SELECT * FROM COMPONENT";
    private static final String DEACTIVATED_COSTUMERS = "SELECT * FROM DEACTIVATED_COSTUMERS";
    private static final String INTERMEDIATE_PRODUCT = "SELECT * FROM INTERMEDIATE_PRODUCT";
    private static final String OPERATION = "SELECT * FROM OPERATION";
    private static final String OPERATION_TYPE = "SELECT * FROM OPERATION_TYPE";
    private static final String ORDER = "SELECT * FROM ORDER";
    private static final String ORDER_PRODUCTS = "SELECT * FROM ORDER_PRODUCTS";
    private static final String PART = "SELECT * FROM PART";
    private static final String PROD_FAMILY = "SELECT * FROM PROD_FAMILY";
    private static final String PRODUCT = "SELECT * FROM PRODUCT";
    private static final String PRODUCTION_ORDER = "SELECT * FROM PRODUCTION_ORDER";
    private static final String PRODUCTION_ORDER_WORKSTATION = "SELECT * FROM PRODUCTION_ORDER_WORKSTATION";
    private static final String RAW_MATERIAL = "SELECT * FROM \"Raw Material\"";
    private static final String WORK_STATION = "SELECT * FROM WORK_STATION";
    private static final String WORKSTATION_TYPE = "SELECT * FROM WORKSTATION_TYPE";
    private static final String WORKSTATION_TYPE_OPERATION_TYPE = "SELECT * FROM WORKSTATION_TYPE_OPERATION_TYPE";

    // Output file
    private static final String OUTPUT_FILE_BOO = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/SQL Developer/BOO.csv";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();

             ResultSet resultSet = statement.executeQuery(BOO);
             FileWriter csvWriter = new FileWriter(OUTPUT_FILE_BOO)) {

                 System.out.println("Connected to the Oracle database.");

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
                         csvWriter.append(resultSet.getString(i));
                         if (i < columnCount) csvWriter.append(",");
                     }
                     csvWriter.append("\n");
                 }

                System.out.println("Data exported successfully to " + OUTPUT_FILE_BOO);

             } catch (SQLException e) {
                 System.err.println("Database connection error: " + e.getMessage());
             } catch (IOException e) {
                 System.err.println("File writing error: " + e.getMessage());
             }
    }
}

