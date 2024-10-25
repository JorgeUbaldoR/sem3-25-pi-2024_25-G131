import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SQLGenerator {
    private static final String BOM = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/bom.csv";
    private static final String BOO = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/boo.csv";
    private static final String COSTUMER = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/costumer.csv";
    private static final String OPERATIONS = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/operations.csv";
    private static final String ORDERS = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/orders.csv";
    private static final String PROD_FAMILY = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/product_family.csv";
    private static final String PRODUCTS = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/products.csv";
    private static final String WORKSTATIONS = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/workstation.csv";
    private static final String WORKSTATION_TYPES = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/data_base/workstation_types.csv";


    public static void main(String[] args) {

        costumer();
        order();
        prodFamily();
        product();
        orderProducts();
        workstationTypes();
        workstation();
        operation();
        bom();
        boo();
        workstationTypeOperation();
    }


    private static void order() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.ORDERS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String orderId = columns[0];
                String customerId = columns[1];
                String orderDate = columns[4];
                String deliveryDate = columns[5];


                String sql = "INSERT INTO \"Order\" (OrderORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES ("
                        + orderId + ", " + customerId + ", TO_DATE('" + orderDate + "', 'dd/mm/yyyy'), TO_DATE('" + deliveryDate + "', 'dd/mm/yyyy'));";


                System.out.println(sql);
            }
            System.out.println();

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static void costumer() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.COSTUMER));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String name = columns[1];
                String vat = columns[2];
                String address = columns[3];
                String zip = columns[4];
                String city = columns[5];
                String country = columns[6];
                String email = columns[7];
                String phone = columns[8];


                String sql = "INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY, EMAIL, PHONE) VALUES ("
                        + id + ", '" + name + "', '" + vat + "', '" + address + "', '" + zip + "', '" + city + "', '"
                        + country + "', '" + email + "', " + phone + ");";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void prodFamily() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.PROD_FAMILY));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String name = columns[1];


                String sql = "INSERT INTO prod_family (FAMILY_ID, NAME) VALUES ("
                        + id + ", '" + name + "');";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static void product() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.PRODUCTS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String name = columns[1];
                String description = columns[2];
                String family = columns[3];


                String sql = "INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('"
                        + id + "', '" + name + "', '" + description + "', " + family + ");";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void orderProducts() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.ORDERS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String order = columns[0];
                String product = columns[2];
                String quantity = columns[3];


                String sql = "INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES ("
                        + order + ", '" + product + "', " + quantity + ");";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void workstationTypes() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.WORKSTATION_TYPES));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String name = columns[1];


                String sql = "INSERT INTO workstation_type (WS_TYPE_ID, NAME) VALUES ('"
                        + id + "', '" + name + "');";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static void workstation() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.WORKSTATIONS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String typeId = columns[1];
                String name = columns[2];
                String description = columns[3];


                String sql = "INSERT INTO work_station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES ("
                        + id + ", '" + name + "', '" + typeId + "', '" + description + "');";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static void operation() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.OPERATIONS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String description = columns[1];


                String sql = "INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES ("
                        + id + ", '" + description + "');";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void bom() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.BOM));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String id = columns[0];
                String partNumber = columns[1];
                String description = columns[2];
                String quantity = columns[3];


                String sql = "INSERT INTO bom (ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('"
                        + id + "', '" + partNumber + "', '" + description + "', '" + quantity + "');";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void boo() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.BOO));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");


                String family = columns[0];
                String operation = columns[1];
                String opNumber = columns[2];


                String sql = "INSERT INTO boo (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ("
                        + family + ", " + operation + ", " + opNumber + ");";


                System.out.println(sql);
            }

            System.out.println();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    private static void workstationTypeOperation() {
        try {

            Scanner scanner = new Scanner(new File(SQLGenerator.OPERATIONS));

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] columns = line.split(";");
                if (columns.length == 3) {
                    String id = columns[0];
                    String wt = columns[2];
                    String sql = "INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES ("
                            + id + ", '" + wt + "');";
                    System.out.println(sql);
                } else if (columns.length > 3) {
                    for (int i = 2; i < columns.length; i++) {
                        String id = columns[0];
                        String wt = columns[i];
                        String sql = "INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES ("
                                + id + ", '" + wt + "');";
                        System.out.println(sql);
                    }
                }


            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

}

