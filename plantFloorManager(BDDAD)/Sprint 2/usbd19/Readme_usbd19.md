# USBD19 -  View product has more operations in its BOO

### 1. User Story Description

>  As a Production Manager, I want to know which product has more operations in its BOO.


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
   legacy system.

      SELECT ProductPRODUCT_ID,
      COUNT(O.OPERATION_ID) AS operation_count
      FROM BOO B
      JOIN Operation O ON B.ProductPRODUCT_ID = O.BOOProductPRODUCT_ID
      GROUP BY ProductPRODUCT_ID
      HAVING COUNT(O.OPERATION_ID) = (
         SELECT MAX(operation_count)
         FROM (
            SELECT COUNT(OPERATION_ID) AS operation_count
            FROM Operation
            GROUP BY BOOProductPRODUCT_ID
         )
      );


### 3. Resolution

>![Results](img/USBD19.png)

>[See results in a CSV file](csv_result/USBD19.csv)


