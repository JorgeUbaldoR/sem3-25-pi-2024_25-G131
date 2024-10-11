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

    /**
     * Sets up the test environment before each test.
     * Initializes the machine list and the operation repository.
     */
    @BeforeEach
    public void setUp() {
        machines = new ArrayList<>();
        operationRepository = new OperationRepository(machines);
    }

    /**
     * Tests the addition of an operation to the repository.
     * Verifies that the operation is added successfully.
     */
    @Test
    public void testAddOperation_Success() {
        Operation operation = new Operation("Operation Test");

        Optional<Operation> result = operationRepository.addOperation(operation);

        assertTrue(result.isPresent());
        assertEquals("Operation Test", result.get().getOperationName());
    }

    /**
     * Tests adding a duplicate operation to the repository.
     * Verifies that the duplicate is not added.
     */
    @Test
    public void testAddOperation_Duplicate() {
        Operation operation = new Operation("Operation Test");
        operationRepository.addOperation(operation);

        Optional<Operation> result = operationRepository.addOperation(operation);
        assertFalse(result.isPresent());
    }

    /**
     * Tests filling operations in the repository from the provided machine list.
     * Verifies that the operations are filled correctly.
     */
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

    /**
     * Tests retrieving operations from the repository.
     * Verifies that the correct operations are returned.
     */
    @Test
    public void testGetOperations() {
        Operation operation = new Operation("Operação 1");
        operationRepository.addOperation(operation);

        List<Operation> operations = operationRepository.getOperations();

        assertEquals(1, operations.size());
        assertTrue(operations.contains(operation));
    }
}