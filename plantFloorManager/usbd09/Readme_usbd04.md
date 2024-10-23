# USBD09 -  View Operations Sequence and Workstation Types from Bill of Operations

### 1. User Story Description

> As a Plant Floor Manager, I want to get the operations sequence as
well as get the respective type of workstation, from a BOO of a given product.


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
   legacy system.

    select p.NAME as Product_Name, b.OPNUMBER as Operation_Number, wt.WS_TYPE_ID
      from product p, Prod_family pf, BOO b, Operation op, Workstation_Type_Operation wto, Workstation_Type wt
      where p.Prod_FamilyFAMILY_ID = pf.FAMILY_ID
      and b.Prod_FamilyFAMILY_ID = pf.FAMILY_ID
      and b.OperationOPERATION_ID = op.OPERATION_ID
      and wto.OperationOPERATION_ID = op.OPERATION_ID
      and wto.Workstation_TypeWS_TYPE_ID = wt.WS_TYPE_ID
      group by p.NAME, b.OPNUMBER, wt.WS_TYPE_ID
      order by p.NAME asc, b.OPNUMBER asc;



