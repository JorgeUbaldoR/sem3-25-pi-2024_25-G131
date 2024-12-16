import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SQLGenerator {
    /* EXEMPLE
    private static final String BOM = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/bom.csv";
    */
    private static final String COSTUMER = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/costumer.csv";
    private static final String ORDER = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/orders.csv";
    private static final String PROD_FAMILY = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/product_family.csv";
    private static final String PRODUCT = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/products.csv";
    private static final String PART = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/part.csv";
    private static final String ORDER_PRODUCTS = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/order_products.csv";
    private static final String WORKSTATION_TYPES = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/workstation_types.csv";
    private static final String WORKSTATIONS = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/workstation.csv";
    private static final String OPERATION = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/operations.csv";
    private static final String BOO = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/boo.csv";
    private static final String BOO_INPUT = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/boo_input.csv";
    private static final String BOO_OUTPUT = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/boo_output.csv";
    private static final String BOO_TEMPLATE = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/boo_template.csv";
    private static final String COMPONENT = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/component.csv";
    private static final String INTERMEDIATE_PRODUCT = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/intermediate_product.csv";
    private static final String OPERATION_TYPE = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/operation_type.csv";
    private static final String RAW_MATERIAL = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/raw_material.csv";
    private static final String WORKSTATION_TYPE_OPERATION_TYPE = "prodPlanSimulator(ESINF)/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/workstation_type_operation.csv";

    public static void main(String[] args) {
        costumer();
        prodFamily();
        part();
        product();
        order();
        orderProducts();
        operationType();
        booTemplate();
        workstationType();
        workStation();
        workstationTypeOperationType();
        boo();
        component();
        intermediateProduct();
        rawMaterial();
        operation();
        booInput();
        booOutput();
    }



    private static void boo() {
        processCSV(BOO, "--Inserts for boo", columns -> {
            String sql = "INSERT INTO boo (ProductPRODUCT_ID) VALUES ('"
                    + columns[0] + "');";
            System.out.println(sql);
        });
    }

    private static void booInput() {
        processCSV(BOO_INPUT, "--Inserts for boo_input", columns -> {
            String sql = "INSERT INTO boo_input (OperationOPERATION_ID, PartPARTNUMBER, QUANTITY, UNIT) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', " + columns[2] + ", '" + columns[3] + "');";
            System.out.println(sql);
        });
    }

    private static void booOutput() {
        processCSV(BOO_OUTPUT, "--Inserts for boo_output", columns -> {
            String sql = "INSERT INTO boo_output (OperationOPERATION_ID, PartPARTNUMBER, QUANTITY, UNIT) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', " + columns[2] + ", '" + columns[3] + "');";
            System.out.println(sql);
        });
    }

    private static void booTemplate() {
        processCSV(BOO_TEMPLATE, "--Inserts for boo_template", columns -> {
            String sql = "INSERT INTO boo_template (OPNUMBER, OperationOPERATION_ID, Prod_FamilyFAMILY_ID) VALUES ("
                    + columns[0] + ", " + columns[1] + ", " + columns[2] + ");";
            System.out.println(sql);
        });
    }

    private static void component() {
        processCSV(COMPONENT, "--Inserts for component", columns -> {
            String sql = "INSERT INTO component (PartPARTNUMBER) VALUES ('"
                    + columns[0] + "');";
            System.out.println(sql);
        });
    }

    private static void costumer() {
        processCSV(COSTUMER, "--Inserts for costumer", columns -> {
            String sql = "INSERT INTO costumer (COSTUMER_ID, VAT, NAME, ADDRESS, CITY, COUNTRY, ZIP, PHONE, EMAIL) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', '" + columns[2] + "', '" + columns[3] + "', '"
                    + columns[4] + "', '" + columns[5] + "', '" + columns[6] + "', " + columns[7] + ", '"
                    + columns[8] + "');";
            System.out.println(sql);
        });
    }

    private static void intermediateProduct() {
        processCSV(INTERMEDIATE_PRODUCT, "--Inserts for intermediate product", columns -> {
            String sql = "INSERT INTO intermediate_product (PartPARTNUMBER) VALUES ('"
                    + columns[0] + "');";
            System.out.println(sql);
        });
    }

    private static void operation() {
        processCSV(OPERATION, "--Inserts for operation", columns -> {
            String sql = "INSERT INTO operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', '" + columns[2] + "', " + columns[3] + ", " + columns[4] + ");";
            System.out.println(sql);
        });
    }

    private static void operationType() {
        processCSV(OPERATION_TYPE, "--Inserts for operation_type", columns -> {
            String sql = "INSERT INTO operation_type (OPTYPE_ID, DESCRIPTION) VALUES ("
                    + columns[0] + ", '" + columns[1] + "');";
            System.out.println(sql);
        });
    }

    private static void order() {
        processCSV(ORDER, "--Inserts for order", columns -> {
            String sql = "INSERT INTO \"Order\" (ORDER_ID, CostumerCOSTUMER_ID, DELIVERY_DATE, ORDER_DATE, STATUS) VALUES ("
                    + columns[0] + ", " + columns[1] + ", TO_DATE('" + columns[2] + "', 'dd/mm/yyyy'), TO_DATE('"
                    + columns[3] + "', 'dd/mm/yyyy') " + columns[4] + "');";
            System.out.println(sql);
        });
    }

    private static void orderProducts() {
        processCSV(ORDER_PRODUCTS, "--Inserts for order_products", columns -> {
            String sql = "INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', " + columns[2] + ");";
            System.out.println(sql);
        });
    }

    private static void part() {
        processCSV(PART, "--Inserts for part", columns -> {
            String sql = "INSERT INTO part (PARTNUMBER, DESCRIPTION, TYPE) VALUES ('"
                    + columns[0] + "', '" + columns[1] + "', '" + columns[2] + "');";
            System.out.println(sql);
        });
    }

    private static void prodFamily() {
        processCSV(PROD_FAMILY, "--Inserts for prod_family", columns -> {
            String sql = "INSERT INTO prod_family (FAMILY_ID, NAME) VALUES ("
                    + columns[0] + ", '" + columns[1] + "');";
            System.out.println(sql);
        });
    }

    private static void product() {
        processCSV(PRODUCT, "--Inserts for product", columns -> {
            String sql = "INSERT INTO product (PRODUCT_ID, Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('"
                    + columns[0] + "', " + columns[1] + ", '" + columns[2] + "', '" + columns[3] + "');";
            System.out.println(sql);
        });
    }

    private static void rawMaterial() {
        processCSV(RAW_MATERIAL, "--Inserts for raw_material", columns -> {
            String sql = "INSERT INTO raw_material (PartPARTNUMBER) VALUES ('"
                    + columns[0] + "');";
            System.out.println(sql);
        });
    }

    private static void workStation() {
        processCSV(WORKSTATIONS, "--Inserts for work_station", columns -> {
            String sql = "INSERT INTO work_station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES ("
                    + columns[0] + ", '" + columns[1] + "', '" + columns[2] + "', '" + columns[3] + "');";
            System.out.println(sql);
        });
    }

    private static void workstationType() {
        processCSV(WORKSTATION_TYPES, "--Inserts for workstation_type", columns -> {
            String sql = "INSERT INTO workstation_type (WS_TYPE_ID, NAME) VALUES ('"
                    + columns[0] + "', '" + columns[1] + "');";
            System.out.println(sql);
        });
    }

    private static void workstationTypeOperationType() {
        processCSV(WORKSTATION_TYPE_OPERATION_TYPE, "--Inserts for workstation_type_operation_type", columns -> {
            String sql = "INSERT INTO workstation_type_operation_type (Workstation_TypeWS_TYPE_ID, Operation_TYPEOPTYPE_ID) VALUES ('"
                    + columns[0] + "', " + columns[1] + ");";
            System.out.println(sql);
        });
    }


    /* EXEMPLO
    private static void bom() {
        processCSV(BOM, "--Inserts for bom", columns -> {
            String sql = "INSERT INTO bom (ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('"
                    + columns[0] + "', '" + columns[1] + "', '" + columns[2] + "', " + columns[3] + ");";
            System.out.println(sql);
        });
    }*/

    private static void processCSV(String filePath, String header, CSVProcessor processor) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            if (scanner.hasNextLine()) scanner.nextLine(); // Skip header
            System.out.println(header);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] columns = line.split(";");
                processor.process(columns);
            }
            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    @FunctionalInterface
    private interface CSVProcessor {
        void process(String[] columns);
    }
}
