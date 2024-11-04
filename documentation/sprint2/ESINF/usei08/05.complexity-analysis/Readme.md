# USEI08 - Processing order based on priority

### **Class SimulatorController**

| Method                           | Complexity |
|----------------------------------|------------|
| SimulatorController()            | O(1)       |
| getItemRepository()              | O(1)       |
| getMachineRepository()           | O(1)       |
| getOperationRepository()         | O(1)       |
| startSimulationWithOutPriority() | O(S)       |
| getMachinesMap()                 | O(N⋅log N) |
| getItemList()                    | O(N)       |
| getOperationList()               | O(N)       |

### **Class ItemRepository**

| Method        | Complexity |
|---------------|------------|
| getItemList() | O(N)       |

### **Class MachineRepository**

| Method           | Complexity |
|------------------|------------|
| getMachineList() | O(N)       |

### **Class OperationRepository**

| Method          | Complexity |
|-----------------|------------|
| getOperations() | O(N)       |

### **Class Simulator**

| Method                                                                                                                 | Complexity                   |
|------------------------------------------------------------------------------------------------------------------------|------------------------------|
| Simulator(Map<Operation, Queue<Machine>> machines, List<Item> items, List<Operation> operations, boolean priorityFlag) | O(1)                         |
| checkInformation(machines, operations, items)                                                                          | O(1)                         |
| addOperationToQueue(operations, priorityFlag)                                                                          | O(N)                         |
| createQueues(items)                                                                                                    | O(N⋅N)                       |
| checkOperationQueue()                                                                                                  | O(N)                         |
| checkTimeOperations()                                                                                                  | O(N*N)                       |
| updateMachines()                                                                                                       | O(N⋅N) + O(N⋅N) = O(N⋅N⋅N⋅N) |
| printQueue()                                                                                                           | O(N)                         |       
| printMachineStatus()                                                                                                   | O(N⋅N)                       |       
| assignItemToMachine(OperationQueue operationQueue, Queue<Machine> machineList)                                         | O(N)                         |       

### **Class Machine**

| Method                                                             | Complexity |
|--------------------------------------------------------------------|------------|
| Machine(ID id_machine, Operation operation, float processingSpeed) | O(1)       |
| processItem(Item item)                                             | O(1)       |
| updateMachine()                                                    | O(1)       |
| resetMachine()                                                     | O(1)       |
| printStatus()                                                      | O(1)       |
| resetMachine()                                                     | O(1)       |
| setCurrentProcessingItem(Item item)                                | O(1)       |
| setNotAvailable()                                                  | O(1)       |
| setTimeToFinish()                                                  | O(1)       |
| getOperation()                                                     | O(1)       |
| getId_machine()                                                    | O(1)       |
| getProcessingSpeed()                                               | O(1)       |
| isAvailable()                                                      | O(1)       |
| getTimeLeftToFinish()                                              | O(1)       |
| getCurrentProcessingItem()                                         | O(1)       |

