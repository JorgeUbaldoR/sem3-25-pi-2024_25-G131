package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.ID;
import pt.ipp.isep.dei.esoft.project.Machine;
import pt.ipp.isep.dei.esoft.project.Operation;

import java.util.*;

public class MachineRepository {

    private final Map<Operation, Map< ID, Machine> > machineList;

    public MachineRepository() {
        this.machineList = new HashMap<>();
    }

    //------------ Add Machine ---------------
    public Optional<Machine> addMachine(Machine machine) {
        Optional<Machine> newMachine;
        boolean operationSuccess = noFoundMachine(machine);

        if(operationSuccess){
        newMachine = Optional.of(machine.clone());
        machineList.put(machine.getOperation(), getMap(machine)).put(machine.getId_machine(),machine);
        }else{
            return Optional.empty();
        }
        return newMachine;
    }

    private Map<ID, Machine> getMap(Machine machine) {
        return machineList.get(machine.getOperation());
    }

    private boolean noFoundMachine(Machine machine) {
        if(machineList.get(machine.getOperation()) == null){
            machineList.put(machine.getOperation(),new HashMap<>());
            return true;
        }
       return !machineList.get(machine.getOperation()).containsKey(machine.getId_machine());
    }

    //---------------------------------------------

}
