package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.io.IOException;
import java.util.*;

public class MachineRepository {

    private final Map<ID, Machine> machineList;


    public MachineRepository() {
        this.machineList = new HashMap<>();
//        fillMachinery();
    }

    //------------ Add Machine ---------------
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


    //---------------------------------------------

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

    public List<Machine> getMachineList() {
        return new ArrayList<>(machineList.values());
    }

}
