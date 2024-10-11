# USEI01 - File data importation

## 4. Tests 

**Test 1:** Tests getting machine details from a valid file and verifies that the correct number of details is returned and checks specific entries.

	@Test public void testGetMachinesDetails_ValidFile() throws IOException {
        List<String[]> details = FileDataReader.getMachinesDetails();
        assertEquals(18, details.size());
        assertArrayEquals(new String[]{"1", "Drill", "1.5"}, details.get(0));
        assertArrayEquals(new String[]{"18", "Polish", "0.75"}, details.get(17)); 
    }
	

**Test 2:** Tests getting item details from a valid file and verifies that the correct number of details is returned and checks specific entries.


	@Test public void testGetItemsDetails_ValidFile() throws IOException {
        List<String[]> details = FileDataReader.getItemsDetails();
        assertEquals(20, details.size());
        assertArrayEquals(new String[]{"101", "HIGH", "Drill", "Cut", "Polish", "Assemble"}, details.get(0));
        assertArrayEquals(new String[]{"120", "HIGH", "Drill", "Test", "Polish", "Cut", "Grind"}, details.get(19)); 
    }

**Test 3:** Tests the behavior when trying to read machine details from a non-existent file and verifies that an IOException is thrown.

          @Test public void testGetMachinesDetails_FileNotFound() {
        new File(TEST_MACHINES_FILE).delete();
        assertThrows(IOException.class, () -> FileDataReader.getMachinesDetails());
    }

**Test 4:** Tests the behavior when trying to read item details from a non-existent file and verifies that an IOException is thrown.

    @Test public void testGetMachinesDetails_FileNotFound() {
    new File(TEST_MACHINES_FILE).delete();
    assertThrows(IOException.class, () -> FileDataReader.getMachinesDetails());
}


## 5. Construction (Implementation)

### Class FileDataReader 

```java
public static List<String[]> getItemsDetails() throws IOException {
    File file = new File("prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/artigos.cvs");
    if (!file.exists()) {
        throw new IOException("File not found: " + file.getAbsolutePath());
    }

    Scanner scanner = new Scanner(file);
    List<String[]> itemsDetails = new ArrayList<>();

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");

        if (parts.length >= NUMBER_OF_DETAILS) {
            itemsDetails.add(parts);
        }
    }

    scanner.close();
    return itemsDetails;
}

```

```java
  public static List<String[]> getMachinesDetails() throws IOException {
    Scanner scanner = new Scanner(new File("prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/maquinas.cvs"));
    List<String[]> machineDetails = new ArrayList<>();

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(",");

        if (parts.length == NUMBER_OF_DETAILS) {
            machineDetails.add(parts);
        }
    }
    scanner.close();
    return machineDetails;
}

```

### Class ItemRepository

```java
private void fillInventory() {
    try {

        List<String[]> importedItems = FileDataReader.getItemsDetails();
        for (String[] importedItem : importedItems) {
            ID newId = new ID(Integer.parseInt(importedItem[0]), TypeID.ITEM);
            Priority priority = Priority.fromString(importedItem[1]);
            Queue<Operation> operationQueue = new LinkedList<>();

            for (int i = 2; i < importedItem.length; i++) {
                Operation operation = new Operation(importedItem[i]);
                operationQueue.add(operation);

            }

            itemList.put(newId, new Item(newId, priority, operationQueue));
        }

    } catch (IOException e) {
        System.out.println("Error reading items details");
    }
}

```


### Class MachineRepository

```java
   private void fillMachinery() {
    try {

        List<String[]> importedItems = FileDataReader.getMachinesDetails();
        for (String[] importedItem : importedItems) {
            ID machineID = new ID(Integer.parseInt(importedItem[0]), TypeID.MACHINE);
            Operation operation = new Operation(importedItem[1]);
            float duration = Float.parseFloat(importedItem[2]);

            Machine machine = new Machine(machineID, operation, duration);

            machineList.put(machineID, machine);

        }

    } catch (IOException e) {
        System.out.println("Error reading machines from file");
    }

}

```

```java
public List<Machine> getMachineList() {
return new ArrayList<>(machineList.values());
}
```

### Class OperationRepository

```java
public void fillOperations(List<Machine> machines) {
    for (Machine machine : machines) {
        operations.add(machine.getOperation());
    }
}

```

```java
 public OperationRepository(List<Machine> machines) {
    this.operations = new HashSet<>();
    fillOperations(machines);
}

```

