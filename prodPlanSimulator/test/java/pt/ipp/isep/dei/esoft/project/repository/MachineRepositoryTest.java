package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
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

    MachineRepository machineRepository;

    Operation cutting = new Operation("Cutting");
    Operation shrinking = new Operation("Shrinking");
    Operation swap = new Operation("Swap");

    Machine machine1 = new Machine(new ID(1000, TypeID.MACHINE), cutting, 2.5f);
    Machine machine2 = new Machine(new ID(2000, TypeID.MACHINE), shrinking, 4.5f);
    Machine machine3 = new Machine(new ID(3000, TypeID.MACHINE), swap, 1.5f);
    Machine machine4 = new Machine(new ID(1000, TypeID.MACHINE), swap, 2.5f);
    Machine machine5 = new Machine(new ID(1010, TypeID.MACHINE), swap, 2.5f);

    @BeforeEach
    void setUp() {
        machineRepository = new MachineRepository();
    }

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
        int size = machineRepository.getMachineList().size();

        List<Machine> result = machineRepository.getMachineList();
        List<Machine> expected = new ArrayList<>();
        System.out.println(size);
        expected.add(machine1);
        expected.add(machine2);
        expected.add(machine3);

        assertTrue(result.containsAll(expected));
        assertEquals(size,  result.size());
    }

    @Test
    void requestMachineList() {
        System.out.println("Test Request Machine List");
        Optional<List<Machine>> result1 = machineRepository.requestMachineList();
        assertFalse(result1.isEmpty());
        int size = result1.get().size();
        machineRepository.addMachine(machine1);
        machineRepository.addMachine(machine2);
        machineRepository.addMachine(machine3);
        machineRepository.addMachine(machine5);
        Optional<List<Machine>> result2 = machineRepository.requestMachineList();

        assertTrue(result2.isPresent());
        assertEquals(4 + size, result2.get().size());
    }

    @Test
    void testFillMachinery() {
        System.out.println("Test Fill Machinery");
        List<Machine> machines = machineRepository.getMachineList();
        assertFalse(machines.isEmpty(), "The machine list should not be empty after filling from file");
    }

    @Test
    void testMachineRepositoryInitialization() {
        System.out.println("Test Machine Repository Initialization");
        List<Machine> machines = machineRepository.getMachineList();
        assertNotNull(machines);
        assertTrue(machines.size() > 0, "Repository should contain machines upon initialization");
    }

    @Test
    void testReformatMachineId() {
        System.out.println("Test Reformat Machine ID");
        String reformattedId = machineRepository.reformatMachineId("ws100");
        assertEquals("100", reformattedId);
    }
}