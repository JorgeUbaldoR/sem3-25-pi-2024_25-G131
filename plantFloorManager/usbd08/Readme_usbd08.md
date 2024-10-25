# USBD08 - Show Operations.

### 1. User Story Description

> As a Plant Floor Manager, I want to know the different operations the
factory supports.


### 2. Customer Specifications and Clarifications

> None.



### 3. Acceptance Criteria

* **AC1:** Minimum acceptance criteria: only the User Stories with data allowing their
  proper functioning will be evaluated.
* **AC2:** Minimum expected requirement: demonstrated with data imported from the
  legacy system.
* **AC3:** Minimum requirement above the expected: demonstrated with data provided
  for Sprint 1 evaluation.

### 4. Result

    select distinct OPERATION_ID as ID, DESCRIPTION from Operation;

>![Results](img/USBD08.png)
>
>[See results in a CSV file](csv_result/USBD08.csv)





