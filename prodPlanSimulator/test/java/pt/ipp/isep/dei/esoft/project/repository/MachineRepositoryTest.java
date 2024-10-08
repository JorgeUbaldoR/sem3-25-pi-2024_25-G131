package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MachineRepositoryTest {

    @Test
    void testAddMachine() {
        MachineRepository machineRepository = new MachineRepository();
        Machine machine = new Machine(new ID(), new Operation(), 12);

        Optional<Machine> newMachine = machineRepository.addMachine(machine);
        assertEquals(machine,newMachine.get());
    }
}