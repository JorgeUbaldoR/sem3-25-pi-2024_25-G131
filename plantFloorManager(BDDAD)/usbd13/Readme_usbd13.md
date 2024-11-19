# USBD13 -  list of operations involved in the production of a product and each workstation type

### 1. User Story Description

>  As a Production Manager, I want to get a list of operations involved in the production of a product, as well as each workstation type.


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
legacy system.
> 
>**AC2:** A function should return a cursor with all product operations. When a part is a subproduct made at the factory, its list of operations
should be included. For each operation, the inputs and outputs should be included.


    CREATE OR REPLACE FUNCTION list_operations_and_workstationTypes(
        product_id IN Product.PRODUCT_ID%TYPE
    )
    RETURN SYS_REFCURSOR
    AS
        operation_worstationTypes_cursor SYS_REFCURSOR;
    BEGIN
        OPEN operation_worstationTypes_cursor FOR
            SELECT O.OPERATION_ID, WtOp.Workstation_TypeWS_TYPE_ID
            FROM Operation O
            JOIN Workstation_Type_Operation_TYPE WtOp
            ON WtOp.Operation_TYPEOPTYPE_ID = O.Operation_TYPEOPTYPE_ID
            WHERE O.BOOProductPRODUCT_ID = product_id
    
            UNION ALL
            
            SELECT O.OPERATION_ID, WtOp.Workstation_TypeWS_TYPE_ID
            FROM Operation O
            JOIN BOO_INPUT BI ON O.OPERATION_ID = BI.OperationOPERATION_ID
            JOIN Workstation_Type_Operation_TYPE WtOp 
                ON WtOp.Operation_typeOPTYPE_ID = O.Operation_TYPEOPTYPE_ID
            WHERE BI.PartPARTNUMBER IN (
                SELECT PartPARTNUMBER FROM BOO_INPUT WHERE OperationOPERATION_ID = (SELECT OPERATION_ID FROM Operation WHERE BOOProductPRODUCT_ID = product_id)
            );
        RETURN operation_worstationTypes_cursor;
    END;
    /
    
    DECLARE
        operation_workstationTypes_cursor SYS_REFCURSOR;
        op Operation.OPERATION_ID%TYPE;
        wT Workstation_Type_Operation_TYPE.Workstation_TypeWS_TYPE_ID%TYPE;
    BEGIN
        operation_workstationTypes_cursor := list_operations_and_workstationTypes('AS12945S22');
    
        LOOP
            FETCH operation_workstationTypes_cursor INTO op, wT;
            EXIT WHEN operation_workstationTypes_cursor%NOTFOUND;
    
            DBMS_OUTPUT.PUT_LINE('Operation: ' || op || ', Workstation Type: ' || wT);
        END LOOP;
    
        CLOSE operation_workstationTypes_cursor;
    END;
    /


### 3. Resolution

>![Results](img/USBD13.png)

>[See results in a CSV file](csv_result/USBD13.csv)


