package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MachineRepositoryTest {
    MachineRepository machineRepository = new MachineRepository();

    Operation cutting = new Operation("Cutting");
    Operation shrinking = new Operation("Shrinking");
    Operation swap = new Operation("Swap");

    Machine machine1 = new Machine(new ID(100, TypeID.MACHINE),cutting,2.5f);
    Machine machine2 = new Machine(new ID(200, TypeID.MACHINE),shrinking,4.5f);
    Machine machine3 = new Machine(new ID(300, TypeID.MACHINE),swap,1.5f);
    Machine machine4 = new Machine(new ID(100, TypeID.MACHINE),swap,2.5f);
    Machine machine5 = new Machine(new ID(101, TypeID.MACHINE),swap,2.5f);

    @Test
    void addMachine() {
        System.out.println("Test Add Machine");
        Optional<Machine> result1 = machineRepository.addMachine(machine1);
        Optional<Machine> result2 = machineRepository.addMachine(machine2);
        Optional<Machine> result3 = machineRepository.addMachine(machine3);
        Optional<Machine> result4 = machineRepository.addMachine(machine4);

        assertTrue(result1.isPresent());
        assertTrue(result2.isPresent());
        assertTrue(result3.isPresent());
        assertFalse(result4.isPresent());
    }

    @Test
    void getMachineList() {
        System.out.println("Test Get Machine List");
        machineRepository.addMachine(machine1);
        machineRepository.addMachine(machine2);
        machineRepository.addMachine(machine3);
        machineRepository.addMachine(machine4);

        List<Machine> result = machineRepository.getMachineList();
        List<Machine> expected = new ArrayList<>();

        expected.add(machine1);
        expected.add(machine2);
        expected.add(machine3);

        assertTrue(result.containsAll(expected));

    }

    @Test
    void requestMachineList() {
        System.out.println("Test Request Machine List");
        Optional<List<Machine>> result1 = machineRepository.requestMachineList();
        assertTrue(result1.isEmpty());

        machineRepository.addMachine(machine1);
        machineRepository.addMachine(machine2);
        machineRepository.addMachine(machine3);
        machineRepository.addMachine(machine5);
        Optional<List<Machine>> result2 = machineRepository.requestMachineList();

        assertTrue(result2.isPresent());


    }
}