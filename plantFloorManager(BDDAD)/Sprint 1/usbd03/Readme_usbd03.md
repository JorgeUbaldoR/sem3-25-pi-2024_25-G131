# USBD03 - Relational model (Physical Model).

### 1. User Story Description

> As a Product Owner, I want the relational model to be instantiated
(physical level).


### 2. Customer Specifications and Clarifications

> None.


### 3. Acceptance Criteria

* **AC1:** It will be shown on Oracle LiveSQL.
>* **AC2:** Minimum expected requirement: automatic generation from Visual Paradigm (centralized change management). 


    DROP TABLE BOM CASCADE CONSTRAINTS;
    DROP TABLE BOO CASCADE CONSTRAINTS;
    DROP TABLE Costumer CASCADE CONSTRAINTS;
    DROP TABLE Operation CASCADE CONSTRAINTS;
    DROP TABLE "Order" CASCADE CONSTRAINTS;
    DROP TABLE Order_Products CASCADE CONSTRAINTS;
    DROP TABLE Prod_Family CASCADE CONSTRAINTS;
    DROP TABLE Product CASCADE CONSTRAINTS;
    DROP TABLE Production_Order CASCADE CONSTRAINTS;
    DROP TABLE Production_Order_Operation CASCADE CONSTRAINTS;
    DROP TABLE Work_Station CASCADE CONSTRAINTS;
    DROP TABLE Workstation_Type CASCADE CONSTRAINTS;
    DROP TABLE Workstation_Type_Operation CASCADE CONSTRAINTS;
    
    
    
    CREATE TABLE BOM (PARTNUMBER varchar2(255) NOT NULL, ProductPRODUCT_ID varchar2(20) NOT NULL, DESCRIPTION varchar2(255) NOT NULL, QUANTITY number(10) NOT NULL, PRIMARY KEY (PARTNUMBER, ProductPRODUCT_ID));
    CREATE TABLE BOO (OPNUMBER number(10) NOT NULL, OperationOPERATION_ID number(10) NOT NULL, Prod_FamilyFAMILY_ID number(10) NOT NULL, PRIMARY KEY (OPNUMBER, OperationOPERATION_ID, Prod_FamilyFAMILY_ID));
    CREATE TABLE Costumer (COSTUMER_ID number(10), VAT varchar2(100) NOT NULL, NAME varchar2(255) NOT NULL, ADDRESS varchar2(255) NOT NULL, CITY varchar2(70) NOT NULL, COUNTRY varchar2(70) NOT NULL, ZIP varchar2(20) NOT NULL, PHONE number(15) NOT NULL, EMAIL varchar2(100) NOT NULL, PRIMARY KEY (COSTUMER_ID));
    CREATE TABLE Operation (OPERATION_ID number(10), DESCRIPTION varchar2(100) NOT NULL, PRIMARY KEY (OPERATION_ID));
    CREATE TABLE "Order" (ORDER_ID number(10), CostumerCOSTUMER_ID number(10) NOT NULL, DELIVERY_DATE date NOT NULL, ORDER_DATE date NOT NULL, PRIMARY KEY (ORDER_ID));
    CREATE TABLE Order_Products (OrderORDER_ID number(10) NOT NULL, ProductPRODUCT_ID varchar2(20) NOT NULL, AMOUNT_PRODUCT number(10) NOT NULL, PRIMARY KEY (OrderORDER_ID, ProductPRODUCT_ID));
    CREATE TABLE Prod_Family (FAMILY_ID number(10), NAME varchar2(100) NOT NULL, PRIMARY KEY (FAMILY_ID));
    CREATE TABLE Product (PRODUCT_ID varchar2(20) NOT NULL, Prod_FamilyFAMILY_ID number(10) NOT NULL, NAME varchar2(30) NOT NULL, DESCRIPTION varchar2(100) NOT NULL, PRIMARY KEY (PRODUCT_ID));
    CREATE TABLE Production_Order (PO_ID number(10), Order_ProducstOrderORDER_ID number(10) NOT NULL, Order_ProductsProductPRODUCT_ID varchar2(20), PRIMARY KEY (PO_ID));
    CREATE TABLE Production_Order_Operation (Production_OrderPO_ID number(10) NOT NULL, OperationOPERATION_ID number(10) NOT NULL, Work_StationWS_ID number(10) NOT NULL, PRIMARY KEY (Production_OrderPO_ID, OperationOPERATION_ID, Work_StationWS_ID));
    CREATE TABLE Work_Station (WS_ID number(10), Workstation_TypeWS_TYPE_ID varchar2(100) NOT NULL, NAME varchar2(30) NOT NULL, DESCRIPTION varchar2(255) NOT NULL, PRIMARY KEY (WS_ID));
    CREATE TABLE Workstation_Type (WS_TYPE_ID varchar2(100) NOT NULL, NAME varchar2(255) NOT NULL, PRIMARY KEY (WS_TYPE_ID));
    CREATE TABLE Workstation_Type_Operation (Workstation_TypeWS_TYPE_ID varchar2(100) NOT NULL, OperationOPERATION_ID number(10) NOT NULL, PRIMARY KEY (Workstation_TypeWS_TYPE_ID, OperationOPERATION_ID));
    
    ALTER TABLE "Order" ADD CONSTRAINT FKOrder227226 FOREIGN KEY (CostumerCOSTUMER_ID) REFERENCES Costumer (COSTUMER_ID);
    ALTER TABLE Order_Products ADD CONSTRAINT FKOrder_Prod1393 FOREIGN KEY (OrderORDER_ID) REFERENCES "Order" (ORDER_ID);
    ALTER TABLE Order_Products ADD CONSTRAINT FKOrder_Prod91128 FOREIGN KEY (ProductPRODUCT_ID) REFERENCES Product (PRODUCT_ID);
    ALTER TABLE BOM ADD CONSTRAINT FKBOM970166 FOREIGN KEY (ProductPRODUCT_ID) REFERENCES Product (PRODUCT_ID);
    ALTER TABLE Product ADD CONSTRAINT FKProduct726914 FOREIGN KEY (Prod_FamilyFAMILY_ID) REFERENCES Prod_Family (FAMILY_ID);
    ALTER TABLE BOO ADD CONSTRAINT FKBOO841530 FOREIGN KEY (Prod_FamilyFAMILY_ID) REFERENCES Prod_Family (FAMILY_ID);
    ALTER TABLE Work_Station ADD CONSTRAINT FKWork_Stati805971 FOREIGN KEY (Workstation_TypeWS_TYPE_ID) REFERENCES Workstation_Type (WS_TYPE_ID);
    ALTER TABLE Production_Order ADD CONSTRAINT FKProduction652997 FOREIGN KEY (Order_ProducstOrderORDER_ID, Order_ProductsProductPRODUCT_ID) REFERENCES Order_Products (OrderORDER_ID, ProductPRODUCT_ID);
    ALTER TABLE BOO ADD CONSTRAINT FKBOO282934 FOREIGN KEY (OperationOPERATION_ID) REFERENCES Operation (OPERATION_ID);
    ALTER TABLE Production_Order_Operation ADD CONSTRAINT FKProduction204794 FOREIGN KEY (Work_StationWS_ID) REFERENCES Work_Station (WS_ID);
    ALTER TABLE Production_Order_Operation ADD CONSTRAINT FKProduction749883 FOREIGN KEY (OperationOPERATION_ID) REFERENCES Operation (OPERATION_ID);
    ALTER TABLE Production_Order_Operation ADD CONSTRAINT FKProduction537364 FOREIGN KEY (Production_OrderPO_ID) REFERENCES Production_Order (PO_ID);
    ALTER TABLE Workstation_Type_Operation ADD CONSTRAINT FKWorkstatio436557 FOREIGN KEY (Workstation_TypeWS_TYPE_ID) REFERENCES Workstation_Type (WS_TYPE_ID);
    ALTER TABLE Workstation_Type_Operation ADD CONSTRAINT FKWorkstatio118801 FOREIGN KEY (OperationOPERATION_ID) REFERENCES Operation (OPERATION_ID);








