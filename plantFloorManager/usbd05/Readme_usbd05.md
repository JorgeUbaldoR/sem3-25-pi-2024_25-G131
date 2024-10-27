# USBD05 -  View Outstanding Product Orders by Delivery Date

### 1. User Story Description

>  As a Production Manager, I want to know, for each product, the orders
to be delivered (customer, product, quantity, date) within a given time frame.


### 2. Resolution
>**AC1:** Minimum expected requirement: demonstrated with data imported from the
   legacy system.

      select p.NAME as Product_Name, o.ORDER_ID as ORDER_ID, c.NAME as Costumer_Name, op.AMOUNT_PRODUCT, o.ORDER_DATE
      from Costumer c, "Order" o, Order_Products op, Product p, Prod_Family pf
      where p.Prod_FamilyFAMILY_ID = pf.FAMILY_ID
      and op.ProductPRODUCT_ID =  p.PRODUCT_ID
      and op.OrderORDER_ID = o.ORDER_ID
      and o.CostumerCOSTUMER_ID = c.COSTUMER_ID
      group by p.NAME, o.ORDER_ID, c.NAME, op.AMOUNT_PRODUCT, o.ORDER_DATE
      order by o.ORDER_ID asc;


### 3. Resolution

>![Results](img/USBD05.png)

>[See results in a CSV file](csv_result/USBD05.csv)


