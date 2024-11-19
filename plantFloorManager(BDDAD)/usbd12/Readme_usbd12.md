# USBD12 - List of parts used in a product

### 1. User Story Description

>  As a Production Manager, I want to get the list of parts used in a product.


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
legacy system.
> 
>**AC2:** A function should return a cursor with all the product
parts and their quantity. The individual components should be included when a
part is a subproduct made at the factory

    CREATE OR REPLACE FUNCTION list_parts_used_product(
        product_id IN Operation.BOOProductPRODUCT_ID%TYPE
    )
    RETURN SYS_REFCURSOR
    IS
        list_parts_cursor SYS_REFCURSOR;
    BEGIN
        OPEN list_parts_cursor FOR
            SELECT BI.PartPARTNUMBER, SUM(BI.QUANTITY) AS QUANTITY
            FROM BOO_INPUT BI
            JOIN Operation O ON BI.OperationOPERATION_ID = O.OPERATION_ID
            WHERE O.BOOProductPRODUCT_ID = product_id
            AND NOT EXISTS (
                SELECT 1
                FROM Component C
                WHERE C.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            AND NOT EXISTS (
                SELECT 1
                FROM "Intermediate Product" IP
                WHERE IP.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            AND NOT EXISTS (
                SELECT 1
                FROM "Raw Material" RM
                WHERE RM.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            GROUP BY BI.PartPARTNUMBER
    
            UNION ALL
            
            SELECT BI.PartPARTNUMBER, SUM(BI.QUANTITY * COALESCE(SUB.QUANTITY, 1)) AS QUANTITY
            FROM BOO_INPUT BI
            JOIN Operation O ON BI.OperationOPERATION_ID = O.OPERATION_ID
            JOIN BOO_OUTPUT BOO ON BOO.OperationOPERATION_ID = O.OPERATION_ID
            JOIN BOO_INPUT SUB ON BOO.PartPARTNUMBER = SUB.PartPARTNUMBER
            WHERE BOO.PartPARTNUMBER IN (
                SELECT PartPARTNUMBER 
                FROM BOO_INPUT 
                WHERE OperationOPERATION_ID IN (
                    SELECT OPERATION_ID 
                    FROM Operation 
                    WHERE BOOProductPRODUCT_ID = product_id
                )
            )
            AND NOT EXISTS (
                SELECT 1
                FROM Component C
                WHERE C.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            AND NOT EXISTS (
                SELECT 1
                FROM "Intermediate Product" IP
                WHERE IP.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            AND NOT EXISTS (
                SELECT 1
                FROM "Raw Material" RM
                WHERE RM.PartPARTNUMBER = BI.PartPARTNUMBER
            )
            GROUP BY BI.PartPARTNUMBER;

        RETURN list_parts_cursor;
    END;
    /
    
    
    DECLARE
        parts_cursor	SYS_REFCURSOR;
        part_number	BOO_INPUT.PartPARTNUMBER%TYPE;
        quantity	BOO_INPUT.QUANTITY%TYPE;
    BEGIN
        -- Call the function with product 'AS12945S22'
        parts_cursor := list_parts_used_product('AS12945S22');
        
        -- Loop through the result set and display parts and quantities
        LOOP
            FETCH parts_cursor INTO part_number, quantity;
            EXIT WHEN parts_cursor%NOTFOUND;
            
            DBMS_OUTPUT.PUT_LINE('Part Number: ' || part_number || ', Quantity: ' || quantity);
        END LOOP;
        
        -- Close the cursor after processing
        CLOSE parts_cursor;
    END;
    /


### 3. Resolution

>![Results](img/USBD12.png)

>[See results in a CSV file](csv_result/USBD12.csv)


