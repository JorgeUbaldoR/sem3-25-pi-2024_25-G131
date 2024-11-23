# USBD14 -  List product that uses all types of machines available in the factory

### 1. User Story Description

>  As a Plant Manager, I want to know which product uses all types of machines available in the factory.

### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
   legacy system.

>This function, called list_product_haveALL_operations, is created to identify products that have every type of operation available in the system. The function starts by defining a variable to hold the results, called product_haveAll_operations_cursor. This variable will store the list of product IDs once the function has identified them.
>
>Inside the function, the main work is done by a query. The query looks at the Product table, which contains all the products, and the Operation table, which records operations linked to each product. The query checks each product and groups the operations it has. Then, it compares the number of operations linked to the product with the total number of operation types defined in another table called Operation_TYPE.
>
>If a product has operations matching every type in Operation_TYPE, it is included in the result. Finally, the function opens the cursor with this list of product IDs and returns it to whoever called the function. This allows the user to see all the products that are associated with every type of operation in the system.

      CREATE OR REPLACE FUNCTION list_product_haveALL_operations
      RETURN SYS_REFCURSOR
      AS
         product_haveAll_operations_cursor SYS_REFCURSOR;
      BEGIN
         OPEN product_haveAll_operations_cursor FOR
            SELECT P.PRODUCT_ID
            FROM Product P
            JOIN Operation O ON P.PRODUCT_ID = O.BOOProductPRODUCT_ID
            GROUP BY P.PRODUCT_ID
            HAVING COUNT(O.OPERATION_ID) = (
               SELECT COUNT(OPTYPE_ID)
               FROM Operation_TYPE
            );
         RETURN product_haveAll_operations_cursor;
      END;
      /
      
      --special Part insert
      INSERT INTO Part (PARTNUMBER, DESCRIPTION, TYPE) VALUES ('A99999S99', 'TESTE', 'Product');
      --special Product insert
      INSERT INTO Product (PRODUCT_ID, Prod_FamilyFAMILY_ID, NAME, DESCRIPTION) VALUES ('A99999S99', 130, 'TESTE', 'Teste');
      -- special BOO insert
      INSERT INTO BOO (ProductPRODUCT_ID) VALUES ('A99999S99');
      --special Operation insert
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (1, 'Disc cutting', 'A99999S99', 5647, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (2, 'Initial pot base pressing', 'A99999S99', 5649, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (3, 'Final pot base pressing', 'A99999S99', 5651, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (4, 'Pot base finishing', 'A99999S99', 5653, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (5, 'Lid pressing', 'A99999S99', 5655, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (6, 'Lid finishing', 'A99999S99', 5657, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (7, 'Pot handles riveting', 'A99999S99', 5659, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (8, 'Lid handle screw', 'A99999S99', 5661, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (9, 'Pot test and packaging', 'A99999S99', 5663, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (10, 'Handle welding', 'A99999S99', 5665, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (11, 'Lid polishing', 'A99999S99', 5667, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (12, 'Pot base polishing', 'A99999S99', 5669, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (13, 'Teflon painting', 'A99999S99', 5671, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (14, 'Initial pan base pressing', 'A99999S99', 5681, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (15, 'Final pan base pressing', 'A99999S99', 5682, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (16, 'Pan base finishing', 'A99999S99', 5683, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (17, 'Handle gluing', 'A99999S99', 5685, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (18, 'Pan test and packaging', 'A99999S99', 5688, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (19, 'Operation type 5672 description', 'A99999S99', 5672, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (20, 'Operation type 5673 description', 'A99999S99', 5673, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (21, 'Operation type 5674 description', 'A99999S99', 5674, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (22, 'Operation type 5675 description', 'A99999S99', 5675, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (23, 'Operation type 5684 description', 'A99999S99', 5684, NULL);
      INSERT INTO Operation (OPERATION_ID, DESCRIPTION, BOOProductPRODUCT_ID, Operation_TYPEOPTYPE_ID, NEXTSTEP)
      VALUES (24, 'Operation type 5686 description', 'A99999S99', 5686, NULL);
      
      DECLARE
          product_haveAll_operations_cursor SYS_REFCURSOR;
          p Product.PRODUCT_ID%TYPE;
      BEGIN
          product_haveAll_operations_cursor := list_product_haveALL_operations();
          
          LOOP
              FETCH product_haveAll_operations_cursor INTO p;
              EXIT WHEN product_haveAll_operations_cursor%NOTFOUND;
      
              DBMS_OUTPUT.PUT_LINE('Product: ' || p);
          END LOOP;
      
          CLOSE product_haveAll_operations_cursor;
      END;
      /


### 3. Resolution

>![Results](img/USBD14.png)

>[See results in a CSV file](csv_result/USBD14.csv)


