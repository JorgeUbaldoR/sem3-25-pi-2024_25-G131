package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.*;

public class MachineRepository {

    private final Map<Operation, Map< ID, Machine> > machineList;

    public MachineRepository() {
        this.machineList = new HashMap<>();
    }

    //------------ Add Machine ---------------
    public Optional<Machine> addMachine(Machine machine) {
        Optional<Machine> newMachine = Optional.empty();
        boolean operationSuccess = noFoundMachine(machine);

        if(operationSuccess){
        newMachine = Optional.of(machine.clone());
        machineList.put(machine.getOperation(), getMap(machine)).put(machine.getId_machine(),machine);
        }else{
            return newMachine;
        }
        return newMachine;
    }

    private Map<ID, Machine> getMap(Machine machine) {
        return machineList.get(machine.getOperation());
    }

    public List<Machine> getMachineList() {
        List<Machine> list = new ArrayList<>();
        for (Map<ID,Machine> mapMachine : machineList.values()) {
            list.addAll(mapMachine.values());
        }
        return list;
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
