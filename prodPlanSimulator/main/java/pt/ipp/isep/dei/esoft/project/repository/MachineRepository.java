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
     * Initializes an empty HashMap to store Machine objects.
     * Additionally, fills the repository with machines from an external data source
     * by invoking the fillMachinery() method.
     */
    public MachineRepository() {
        this.machineList = new HashMap<>();
        //fillMachinery();
    }

    /**
     * Adds a new machine to the repository if it does not already exist.
     *
     * @param machine the Machine to be added to the repository
     * @return an Optional containing a clone of the added machine if successful,
     * or an empty Optional if the machine already exists
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
                ID machineID = new ID(Integer.parseInt(reformatMachineId(importedItem[0])), TypeID.MACHINE);
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
     * The returned list is a new ArrayList containing the current machines, ensuring the original list is not modified.
     *
     * @return a List containing all Machine objects in the repository
     */
    public List<Machine> getMachineList() {
        return new ArrayList<>(machineList.values());
    }

    /**
     * Requests the list of machines in the repository, wrapped in an Optional.
     * If the repository is not empty, the method returns an Optional containing the list of machines.
     * If the repository is empty, an empty Optional is returned.
     *
     * @return an Optional containing a List of machines if available, or an empty Optional if the repository is empty
     */
    public Optional<List<Machine>> requestMachineList() {
        Optional<List<Machine>> optionalValue = Optional.empty();
        List<Machine> list = getMachineList();

        if (!machineList.isEmpty()) {
            optionalValue = Optional.of(list);
        }
        return optionalValue;
    }

    /**
     * Registers a new machine if the ID is unique.
     *
     * @param operation The operation to associate with the machine.
     * @param id The unique identifier for the machine.
     * @param exTime The execution time for the operation.
     * @return An Optional containing the registered machine if successful; otherwise, an empty Optional.
     */
    public Optional<Machine> registerMachine(Operation operation, int id, double exTime) {
        ID identification = new ID(id, TypeID.MACHINE);

        if (!machineList.containsKey(identification)) {
            Machine newMachine = new Machine(identification, operation, (float) exTime);
            machineList.put(identification, newMachine);

            return Optional.of(newMachine);
        }

        return Optional.empty();
    }


    /**
     * Reformats a machine ID by removing the non-numeric prefix and retaining only the numeric part.
     *
     * @param id The original machine ID as a string, e.g., "ws111".
     * @return A new string representing only the numeric part of the machine ID, e.g., "111".
     */
    public String reformatMachineId(String id) {
        int len = id.length();
        return id.substring(2, len);
    }
}


