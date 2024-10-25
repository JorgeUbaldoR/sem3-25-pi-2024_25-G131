# USEI01 - File data importation

### **Class FileDateReader**

| Method               | Complexity |
|----------------------|------------|
| getMachinesDetails() | O(N)       |
| getItemsDetails()    | O(N)       |

### **Class ItemRepository**

| Method             | Complexity |
|--------------------|------------|
| fillInventory()    | O(N^2)     |
| getOperationList() | O(1)       |
| getItemList()      | O(N)       |

### **Class MachineRepository**

| Method          | Complexity |
|-----------------|------------|
| fillMachinery() | O(N)       |

### **Class OperationRepository**

| Method               | Complexity |
|----------------------|------------|
| fillOperations(List) | O(N^2)     |
| getItemsDetails()    | O(N)       |



**- USEI01 complexity** O(N^2)