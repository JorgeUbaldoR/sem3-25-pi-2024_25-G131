# USBD15 -  Register a workstation in the system

### 1. User Story Description

>  As a Plant Floor Manager, I want to register a workstation in the system


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
   legacy system.
> 
>**AC2:** A function should be used to create the workstation and
to return success or an error.

>This script defines a PL/SQL function called register_workstation, which registers a new workstation in the Work_Station table. The function accepts four parameters: workstation ID, workstation type ID, name, and description.
>
>The first step in the function is to validate if the provided workstation type ID exists in the Workstation_Type table. If the type ID does not exist, the function returns an error message indicating that the workstation type is invalid. If the type ID is valid, the function proceeds to the next step.
>
>The second step is to insert the new workstation details into the Work_Station table. If the insertion is successful, a success message is returned, confirming the workstation has been registered. The function also includes error handling: if a workstation with the same ID already exists, it returns an error message indicating a duplicate ID. If any other error occurs, a generic error message is returned.

      CREATE OR REPLACE FUNCTION register_workstation (
         p_ws_id Work_Station.WS_ID%TYPE, 
         p_ws_type_id Workstation_Type.WS_TYPE_ID%TYPE, 
         p_name Work_Station.NAME%TYPE,
         p_description Work_Station.DESCRIPTION%TYPE 
      )
      RETURN VARCHAR2
      AS
         result_message VARCHAR2(255);
         v_exists NUMBER(1);
      BEGIN
         SELECT COUNT(1)
         INTO v_exists
         FROM Workstation_Type
         WHERE WS_TYPE_ID = p_ws_type_id;
   
         IF v_exists = 0 THEN
            RETURN 'Error: Workstation Type with ID ' || p_ws_type_id || ' does not exist.';
         END IF;
   
         BEGIN
            INSERT INTO Work_Station (WS_ID, Workstation_TypeWS_TYPE_ID, NAME, DESCRIPTION)
            VALUES (p_ws_id, p_ws_type_id, p_name, p_description);
      
            result_message := 'Success: Workstation with ID ' || p_ws_id || ' registered successfully.';
            RETURN result_message;
      
         EXCEPTION
             WHEN DUP_VAL_ON_INDEX THEN
                 RETURN 'Error: Workstation with ID ' || p_ws_id || ' already exists.';
             WHEN OTHERS THEN
                 RETURN 'Error: ' || SQLERRM;
         END;
      END;
      /
   
   
      DECLARE
         result_message VARCHAR2(255); -- Variable to store the output message
      BEGIN
         result_message := register_workstation (9999, 'A4578', 'Test', 'ID test.');
         DBMS_OUTPUT.PUT_LINE(result_message);
      END;
      /


### 3. Resolution

>![Results](img/USBD15.png)

>[See results in a CSV file](csv_result/USBD015.csv)


