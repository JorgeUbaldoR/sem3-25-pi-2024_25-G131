package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.io.IOException;
import java.util.*;

/**
 * Repository class for managing Machine objects.
 * This class allows adding machines to the repository and retrieving them.
 */
public class MachineRepository {

    private final Map<ID, Machine> machineList;

    /**
     * Constructs a new MachineRepository instance.
     * Initializes an empty HashMap to store Machine objects and fills the machinery
     * from a data source.
     */
    public MachineRepository() {
        this.machineList = new HashMap<>();
//       fillMachinery();
    }

    /**
     * Adds a new machine to the repository if it does not already exist.
     *
     * @param machine the Machine to be added to the repository
     * @return an Optional containing a clone of the added machine if successful,
     *         or an empty Optional if the machine already exists
     */
    public Optional<Machine> addMachine(Machine machine) {
        Optional<Machine> newMachine = Optional.empty();

        if (!machineList.containsKey(machine.getId_machine())) {
            newMachine = Optional.of(machine.clone());
            machineList.put(machine.getId_machine(), machine);
        } else {
            return newMachine;
        }
        return newMachine;
    }

    /**
     * Fills the machinery list with machines from a data source.
     * This method reads machine details from a file and populates the machine list.
     * It handles IOExceptions that may occur during file reading.
     */
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

    /**
     * Retrieves all machines from the repository.
     *
     * @return a List containing all Machine objects in the repository
     */
    public List<Machine> getMachineList() {
        return new ArrayList<>(machineList.values());
    }

}
