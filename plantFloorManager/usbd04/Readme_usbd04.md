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
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY) VALUES (456, 'Carvalho & Carvalho, Lda', 'PT501245987', 'Tv. Augusto Lessa 23', '4200-047', 'Porto', 'Portugal');							
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY) VALUES (785, 'Tudo para a casa, Lda', 'PT501245488', 'R. Dr. Barros 93', '4465-219', 'São Mamede de Infesta', 'Portugal');							
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY) VALUES (657, 'Sair de Cena', 'PT501242417', 'EDIFICIO CRISTAL lj18, R. António Correia de Carvalho 88', '4400-023', 'Vila Nova de Gaia', 'Portugal');							
    INSERT INTO costumer (COSTUMER_ID, NAME, VAT, ADDRESS, ZIP, CITY, COUNTRY) VALUES (348, 'U Fleku', 'CZ6451237810', 'Křemencova 11', '110 00', 'Nové Město', 'Czechia');
    
    --Inserts of order
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (1, 785, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('23/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (2, 657, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (3, 348, TO_DATE('15/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (4, 785, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (5, 657, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('25/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (6, 348, TO_DATE('18/09/2024', 'dd/mm/yyyy'), TO_DATE('26/09/2024', 'dd/mm/yyyy'));
    INSERT INTO "Order" (ORDER_ID, CostumerCOSTUMER_ID, ORDER_DATE, DELIVERY_DATE) VALUES (7, 456, TO_DATE('21/09/2024', 'dd/mm/yyyy'), TO_DATE('26/09/2024', 'dd/mm/yyyy'));
    
    --Inserts of prod_family
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945T22');
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945S22');
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945S20');
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945S17');
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945S48');
    INSERT INTO Prod_Family (FAMILY_ID) VALUES ('AS12945G48');
    
    --Inserts of product
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945T22', 'La Belle 22 5l pot', '5l 22 cm aluminium and teflon non stick pot');
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945S22', 'Pro 22 5l pot', '5l 22 cm stainless steel pot');
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945S20', 'Pro 20 3l pot', '3l 20 cm stainless steel pot');
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945S17', 'Pro 17 2l sauce pan', '2l 17 cm stainless steel souce pan');
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945S48', 'Pro 17 lid', '17 cm stainless steel lid');
    INSERT INTO Product (Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('AS12945G48', 'Pro Clear 17 lid', '17 cm glass lid');
    
    --Inserts of order_products
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('1', 'AS12945S22', 5);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('1', 'AS12945S20', 15);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('2', 'AS12945S22', 10);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('3', 'AS12945S22', 10);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('3', 'AS12945S20', 10);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('4', 'AS12945S22', 4);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('5', 'AS12945S22', 12);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('6', 'AS12945S22', 8);								
    INSERT INTO Order_Products (OrderORDER_ID, ProductProd_FamilyFAMILY_ID, AMOUNT_PRODUCT) VALUES ('7', 'AS12945S22', 7);
    
    --Inserts of workstation_types
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('A4578','600t cold forging stamping press');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('A4588','600t cold forging precision stamping press');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('A4598','1000t cold forging precision stamping press');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('S3271','Handle rivet');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('K3675','Packaging');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('K3676','Packaging for large itens');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('C5637','Border trimming');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('D9123','Spot welding');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('Q5478','Teflon application station');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('Q3547','Stainless steel polishing');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('T3452','Assembly T1');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('G9273','Circular glass cutting');
    INSERT INTO Workstation_Type (WS_TYPE_ID, NAME) VALUES ('G9274','Glass trimming');
    
    --Inserts of workstation
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (9875, 'A4578', 'Press 01', '220-630t cold forging press');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (9886, 'A4578', 'Press 02', '220-630t cold forging press');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (9847, 'A4588', 'Press 03', '220-630t precision cold forging press');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (9855, 'A4588', 'Press 04', '160-1000t precison cold forging press');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (8541, 'S3271', 'Rivet 02', 'Rivet station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (8543, 'S3271', 'Rivet 03', 'Rivet station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (6814, 'K3675', 'Packaging 01', 'Packaging station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (6815, 'K3675', 'Packaging 02', 'Packaging station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (6816, 'K3675', 'Packaging 03', 'Packaging station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (6821, 'K3675', 'Packaging 04', 'Packaging station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (6822, 'K3676', 'Packaging 05', 'Packaging station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (8167, 'D9123', 'Welding 01', 'Spot welding staion');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (8170, 'D9123', 'Welding 02', 'Spot welding staion');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (8171, 'D9123', 'Welding 03', 'Spot welding staion');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (7235, 'T3452', 'Assembly 01', 'Product assembly station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (7236, 'T3452', 'Assembly 02', 'Product assembly station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (7238, 'T3452', 'Assembly 03', 'Product assembly station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (5124, 'C5637', 'Trimming 01', 'Metal trimming station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (4123, 'Q3547', 'Polishing 01', 'Metal polishing station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (4124, 'Q3547', 'Polishing 02', 'Metal polishing station');									
    INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION) VALUES (4125, 'Q3547', 'Polishing 03', 'Metal polishing station');
    
    --Inserts of operation
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'Disc cutting', 'A4578');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'Disc cutting', 'A4588');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5647, 'Disc cutting', 'A4598');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5649, 'Initial pot base pressing', 'A4588');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5649, 'Initial pot base pressing', 'A4598');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5651, 'Final pot base pressing', 'A4588');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5653, 'Pot base finishing', 'C5637');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5655, 'Lid pressing', 'A4588');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5655, 'Lid pressing', 'A4598');
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5657, 'Lid finishing', 'C5637');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5659, 'Pot handles riveting', 'S3271');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5661, 'Lid handle screw', 'T3452');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5663, 'Pot test and packaging', 'K3675');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5665, 'Handle welding', 'D9123');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5667, 'Lid polishing', 'Q3547');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5669, 'Pot base polishing', 'Q3547');						
    INSERT INTO Operation (OPERATION_ID, DESCRIPTION, Workstation_TypeWS_TYPE_ID) VALUES (5671, 'Teflon painting', 'Q5478');
    
    --Inserts of bom
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN12344A21', 'Screw M6 35 mm', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN52384R50', '300x300 mm 5mm stainless steel sheet', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN52384R10', '300x300 mm 1mm stainless steel sheet', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18544A21', 'Rivet 6 mm', 4);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18544C21', 'Stainless steel handle model U6', 2);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S22', 'PN18324C54', 'Stainless steel handle model R12', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN12344A21', 'Screw M6 35 mm', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN52384R50', '300x300 mm 5mm stainless steel sheet', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN52384R10', '300x300 mm 1mm stainless steel sheet', 1);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18544A21', 'Rivet 6 mm', 4);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18544C21', 'Stainless steel handle model U6', 2);								
    INSERT INTO BOM (ProductProd_FamilyFAMILY_ID, PARTNUMBER, DESCRIPTION, QUANTITY) VALUES ('AS12945S20', 'PN18324C51', 'Stainless steel handle model R11', 1);
    
    --Inserts of boo
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5647, 1);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5647, 2);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5649, 3);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5651, 4);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5653, 5);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5659, 6);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5669, 7);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5655, 8);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5657, 9);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5661, 10);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5667, 11);								
    INSERT INTO BOO (Prod_FamilyFAMILY_ID, OperationOPERATION_ID, OPNUMBER) VALUES ('AS12945S22', 5663, 12);
    

* **AC2:** Minimum requirement above the expected: automatic generation of SQL
  input code from the spreadsheet (e.g., Excel formulas, scripts in any other
  language, etc.).





