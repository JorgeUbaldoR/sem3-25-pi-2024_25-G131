# USBD04 -  Import data and deliver it on a spreadsheet.

### 1. User Story Description

> As a Product Owner, I want to import data from a legacy system and
deliver it on a spreadsheet.


### 2. Customer Specifications and Clarifications

> **Question nº1:** Should we be capable of exporting the DB contents to excel/spreadsheet? And i do not understand with "Automatic generation of SQL input code from the spreadsheet", are we supposed to import data from or to a spreadsheet?
>
> **Answer:**
This user story covers an ETL process.
You will be provided a spreadsheet with data from a legacy system, and you will have to import that data into the new database model you are developing. The data will not be normalized and will not fit directly into the new model. You will be required to process that data in order to be able to load it into the new system.




### 3. Acceptance Criteria

>* **AC1:** Minimum expected requirement: manual creation of the data input scripts.


    --Inserts of costumer							
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY, EMAIL, PHONE) VALUES (456, 'Carvalho & Carvalho, Lda', 'PT501245987', 'Tv. Augusto Lessa 23', '4200-047', 'Porto', 'Portugal', 'idont@care.com', 3518340500);										
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY, EMAIL, PHONE) VALUES (785, 'Tudo para a casa, Lda', 'PT501245488', 'R. Dr. Barros 93', '4465-219', 'São Mamede de Infesta', 'Portugal', 'me@neither.com', 003518340500);										
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY, EMAIL, PHONE) VALUES (657, 'Sair de Cena', 'PT501242417', 'EDIFICIO CRISTAL lj18, R. António Correia de Carvalho 88', '4400-023', 'Vila Nova de Gaia', 'Portugal', 'some@email.com', 003518340500);										
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY, EMAIL, PHONE) VALUES (348, 'U Fleku', 'CZ6451237810', 'Křemencova 11', '110 00', 'Nové Město', 'Czechia', 'some.random@email.cz', 004201234567);
    
    --Inserts of order
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (1, 785, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('23/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (2, 657, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (3, 348, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (4, 785, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (5, 657, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (6, 348, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('26/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (7, 456, TO_DATE('21/09/2024', 'dd/mm/yyyy'), TO_DATE('26/09/2024', 'dd/mm/yyyy'));
    
    --Inserts of prod_family
    INSERT INTO prod_family (FAMILY_ID, NAME) VALUES (125, 'Pro Line pots');					
    INSERT INTO prod_family (FAMILY_ID, NAME) VALUES (130, 'La Belle pots');					
    INSERT INTO prod_family (FAMILY_ID, NAME) VALUES (132, 'Pro Line pans');					
    INSERT INTO prod_family (FAMILY_ID, NAME) VALUES (145, 'Pro Line lids');					
    INSERT INTO prod_family (FAMILY_ID, NAME) VALUES (146, 'Pro Clear lids');
    
    --Inserts of product
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945T22', 'La Belle 22 5l pot', '5l 22 cm aluminium and teflon non stick pot', 130);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945S22', 'Pro 22 5l pot', '5l 22 cm stainless steel pot', 125);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945S20', 'Pro 20 3l pot', '3l 20 cm stainless steel pot', 125);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945S17', 'Pro 17 2l pot', '2l 17 cm stainless steel pot', 125);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945P17', 'Pro 17 2l sauce pan', '2l 17 cm stainless steel souce pan', 132);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945S48', 'Pro 17 lid', '17 cm stainless steel lid', 145);									
    INSERT INTO product (PRODUCT_ID, NAME, DESCRIPTION, Prod_FamilyFAMILY_ID) VALUES ('AS12945G48', 'Pro Clear 17 lid', '17 cm glass lid', 146);
    
    --Inserts of order_products
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (1, 'AS12945S22', 5);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (1, 'AS12945S20', 15);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (2, 'AS12945S22', 10);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (2, 'AS12945P17', 20);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (3, 'AS12945S22', 10);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (3, 'AS12945S20', 10);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (4, 'AS12945S20', 24);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (4, 'AS12945S22', 16);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (4, 'AS12945S17', 8);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (5, 'AS12945S22', 12);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (6, 'AS12945S17', 8);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (6, 'AS12945P17', 16);								
    INSERT INTO order_products (OrderORDER_ID, ProductPRODUCT_ID, AMOUNT_PRODUCT) VALUES (7, 'AS12945S22', 8);
    
    
    --Inserts of workstation_types
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('A4578', '600t cold forging stamping press');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('A4588', '600t cold forging precision stamping press');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('A4598', '1000t cold forging precision stamping press');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('S3271', 'Handle rivet');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('K3675', 'Packaging');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('K3676', 'Packaging for large itens');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('C5637', 'Border trimming');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('D9123', 'Spot welding');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('Q5478', 'Teflon application station');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('Q3547', 'Stainless steel polishing');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('T3452', 'Assembly T1');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('G9273', 'Circular glass cutting');						
    INSERT INTO workstation_type(WS_TYPE_ID, NAME) VALUES ('G9274', 'Glass trimming');
    
    
    --Inserts of workstation
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (9875, 'A4578', 'Press 01', '220-630t cold forging press');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (9886, 'A4578', 'Press 02', '220-630t cold forging press');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (9847, 'A4588', 'Press 03', '220-630t precision cold forging press');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (9855, 'A4588', 'Press 04', '160-1000t precison cold forging press');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (8541, 'S3271', 'Rivet 02', 'Rivet station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (8543, 'S3271', 'Rivet 03', 'Rivet station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (6814, 'K3675', 'Packaging 01', 'Packaging station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (6815, 'K3675', 'Packaging 02', 'Packaging station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (6816, 'K3675', 'Packaging 03', 'Packaging station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (6821, 'K3675', 'Packaging 04', 'Packaging station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (6822, 'K3676', 'Packaging 05', 'Packaging station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (8167, 'D9123', 'Welding 01', 'Spot welding staion');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (8170, 'D9123', 'Welding 02', 'Spot welding staion');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (8171, 'D9123', 'Welding 03', 'Spot welding staion');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (7235, 'T3452', 'Assembly 01', 'Product assembly station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (7236, 'T3452', 'Assembly 02', 'Product assembly station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (7238, 'T3452', 'Assembly 03', 'Product assembly station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (5124, 'C5637', 'Trimming 01', 'Metal trimming station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (4123, 'Q3547', 'Polishing 01', 'Metal polishing station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (4124, 'Q3547', 'Polishing 02', 'Metal polishing station');									
    INSERT INTO work_station(WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION ) VALUES (4125, 'Q3547', 'Polishing 03', 'Metal polishing station');
    
    
    --Inserts of operation
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5647, 'Disc cutting');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5649, 'Initial pot base pressing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5651, 'Final pot base pressing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5653, 'Pot base finishing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5655, 'Lid pressing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5657, 'Lid finishing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5659, 'Pot handles riveting');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5661, 'Lid handle screw');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5663, 'Pot test and packaging');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5665, 'Handle welding');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5667, 'Lid polishing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5669, 'Pot base polishing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5671, 'Teflon painting');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5681, 'Initial pan base pressing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5682, 'Final pan base pressing');				
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5683, 'Pan base finishing');							
    INSERT INTO operation (OPERATION_ID, DESCRIPTION) VALUES (5688, 'Pan test and packaging');
    
    --Inserts of bom
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN12344A21', 'Screw M6 35 mm', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN52384R50', '300x300 mm 5mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN52384R10', '300x300 mm 1mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18544A21', 'Rivet 6 mm', 4);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18544C21', 'Stainless steel handle model U6', 2);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18324C54', 'Stainless steel handle model R12', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN12344A21', 'Screw M6 35 mm', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN52384R50', '300x300 mm 5mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN52384R10', '300x300 mm 1mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18544A21', 'Rivet 6 mm', 4);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18544C21', 'Stainless steel handle model U6', 2);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18324C51', 'Stainless steel handle model R11', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN12344A21', 'Screw M6 35 mm', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN52384R45', '250x250 mm 5mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN52384R12', '250x250 mm 1mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN18544A21', 'Rivet 6 mm', 4);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN18544C21', 'Stainless steel handle model U6', 2);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S17', 'PN18324C51', 'Stainless steel handle model R11', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945P17', 'PN52384R45', '250x250 mm 5mm stainless steel sheet', 1);								
    INSERT INTO bom(ProductPRODUCT_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945P17', 'PN18324C91', 'Stainless steel handle model S26', 1);
    
    --Inserts of boo
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5647, 1);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5647, 2);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5649, 3);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5651, 4);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5653, 5);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5659, 6);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5669, 7);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5655, 8);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5657, 9);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5661, 10);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5667, 11);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (125, 5663, 12);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (132, 5681, 1);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (132, 5682, 2);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (132, 5683, 3);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (132, 5665, 4);								
    INSERT INTO boo(Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER ) VALUES (132, 5688, 5);
    
    --Inserts of Workstation_Type_Operation
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'A4578');		
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'A4588');		
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'A4598');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5649, 'A4588');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5649, 'A4598');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5651, 'A4588');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5653, 'C5637');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5655, 'A4588');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5655, 'A4598');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5657, 'C5637');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5659, 'S3271');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5661, 'T3452');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5663, 'K3675');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5665, 'D9123');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5667, 'Q3547');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5669, 'Q3547');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5671, 'Q5478');										
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5681, 'A4588');		
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5681, 'A4598');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5682, 'A4588');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5682, 'A4598');
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5683, 'C5637');																				
    INSERT INTO workstation_type_operation (OperationOPERATION_ID, Workstation_TypeWS_TYPE_ID) VALUES (5688, 'K3675');





>* **AC2:** Minimum requirement above the expected: automatic generation of SQL
  input code from the spreadsheet (e.g., Excel formulas, scripts in any other
  language, etc.).

```java
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
```





