package repository;

import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;
import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class OperationRepositoryTest {

    private OperationRepository operationRepository;
    private List<Machine> machines;

    @BeforeEach
    public void setUp() {
        machines = new ArrayList<>();
        operationRepository = new OperationRepository(machines);
    }

    @Test
    public void testAddOperation_Success() {
        Operation operation = new Operation("Operation Test");

        Optional<Operation> result = operationRepository.addOperation(operation);

        assertTrue(result.isPresent());
        assertEquals("Operation Test", result.get().getOperationName());
    }

    @Test
    public void testAddOperation_Duplicate() {
        Operation operation = new Operation("Operation Test");
        operationRepository.addOperation(operation);

        Optional<Operation> result = operationRepository.addOperation(operation);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFillOperations() {
        Operation operation1 = new Operation("First Operation");
        Operation operation2 = new Operation("Second Operation");

        ID id1 = new ID(1, TypeID.MACHINE);
        ID id2 = new ID(2, TypeID.MACHINE);
        Machine machine1 = new Machine(id1, operation1, 1.5f);
        Machine machine2 = new Machine(id2, operation2, 2.7f);

        machines.add(machine1);
        machines.add(machine2);

        operationRepository.fillOperations(machines);

        List<Operation> operations = operationRepository.getOperations();
        assertEquals(2, operations.size());
        assertTrue(operations.contains(operation1));
        assertTrue(operations.contains(operation2));
    }

    @Test
    public void testGetOperations() {
        Operation operation = new Operation("Operação 1");
        operationRepository.addOperation(operation);

        List<Operation> operations = operationRepository.getOperations();

        assertEquals(1, operations.size());
        assertTrue(operations.contains(operation));
    }
}