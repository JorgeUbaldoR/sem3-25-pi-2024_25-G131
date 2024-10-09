package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.FileDataReader;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.more.Operation;

import java.io.IOException;
import java.util.*;

public class MachineRepository {

    private final Map<Operation, Map<ID, Machine>> machineList;


    public MachineRepository() {
        this.machineList = new HashMap<>();
        fillMachinery();
    }

    //------------ Add Machine ---------------
    public Optional<Machine> addMachine(Machine machine) {
        Optional<Machine> newMachine = Optional.empty();
        boolean operationSuccess = noFoundMachine(machine);

        if (operationSuccess) {
            newMachine = Optional.of(machine.clone());
            machineList.put(machine.getOperation(), getMap(machine)).put(machine.getId_machine(), machine);
        } else {
            return newMachine;
        }
        return newMachine;
    }

    private Map<ID, Machine> getMap(Machine machine) {
        return machineList.get(machine.getOperation());
    }

    public List<Machine> getMachineList() {
        List<Machine> list = new ArrayList<>();
        for (Map<ID, Machine> mapMachine : machineList.values()) {
            list.addAll(mapMachine.values());
        }
        return list;
    }

    private boolean noFoundMachine(Machine machine) {
        if (machineList.get(machine.getOperation()) == null) {
            machineList.put(machine.getOperation(), new HashMap<>());
            return true;
        }
        return !machineList.get(machine.getOperation()).containsKey(machine.getId_machine());
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

                Map<ID, Machine> machineMap = new HashMap<>();
                machineMap.put(machineID, machine);
                machineList.put(operation, machineMap);

            }

        } catch (IOException e) {
            System.out.println("Error reading machines from file");
        }

    }

}
