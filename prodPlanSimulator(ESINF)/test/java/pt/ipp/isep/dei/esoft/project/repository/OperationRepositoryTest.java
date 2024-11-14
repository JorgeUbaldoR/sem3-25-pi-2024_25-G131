package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Operation;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.Priority;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class OperationRepositoryTest {

    private OperationRepository operationRepository;
    private List<Item> items;
    private final ID id1 = new ID(1,TypeID.OPERATION);
    private final ID id2 = new ID(2,TypeID.OPERATION);
    private final ID id3 = new ID(3,TypeID.OPERATION);
    private final Operation opCutting = new Operation("Cutting", id1,"Cutting raw materials");
    private final Operation opWelding = new Operation("Welding", id2,"Welding metal parts");
    private final Operation opPainting = new Operation("Painting", id3,"Painting the surface");
    private final Queue<Operation> operationListItem1 = new LinkedList<>();
    private final Queue<Operation> operationListItem2 = new LinkedList<>();

    @BeforeEach
    public void setUp() {
        items = new ArrayList<>();
        operationRepository = new OperationRepository(items);
    }

    @Test
    public void testAddOperation_Success() {
        Operation operation = new Operation("Operation Test", id1);

        Optional<Operation> result = operationRepository.addOperation(operation);

        assertTrue(result.isPresent());
        assertEquals("Operation Test", result.get().getOperationName());
    }

    @Test
    public void testAddOperation_Duplicate() {
        Operation operation = new Operation("Operation Test",id1);
        operationRepository.addOperation(operation);

        Optional<Operation> result = operationRepository.addOperation(operation);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFillOperationsFromItems() {
        // Create operations
        operationListItem1.add(opCutting);
        operationListItem1.add(opWelding);
        operationListItem1.add(opPainting);
        operationListItem2.add(opCutting);
        operationListItem2.add(opWelding);

        // Create items with operation lists
         ID idItem1 = new ID(101, TypeID.ITEM);
         ID idItem2 = new ID(102, TypeID.ITEM);
         Item item1 = new Item(idItem1, Priority.LOW, operationListItem1);
         Item item2 = new Item(idItem2, Priority.NORMAL, operationListItem2);
        items.add(item1);
        items.add(item2);

        operationRepository.fillOperations(items);

        List<Operation> operations = operationRepository.getOperations();
        assertEquals(3, operations.size());
        assertTrue(operations.contains(opCutting));
        assertTrue(operations.contains(opPainting));
        assertTrue(operations.contains(opWelding));

    }

    @Test
    public void testGetOperations_EmptyRepository() {
        List<Operation> operations = operationRepository.getOperations();

        assertTrue(operations.isEmpty());
    }

    @Test
    public void testGetAllOperations_NonEmpty() {
        Operation operation1 = new Operation("Operation1",new ID(4,TypeID.OPERATION));
        Operation operation2 = new Operation("Operation2",new ID(5,TypeID.OPERATION));

        operationRepository.addOperation(operation1);
        operationRepository.addOperation(operation2);

        Optional<List<Operation>> operations = operationRepository.getAllOperations();

        assertTrue(operations.isPresent());
        assertEquals(2, operations.get().size());
    }

    @Test
    public void testRegisterOperation_Success() {
        ID id = new ID(6,TypeID.OPERATION);
        Optional<Operation> result = operationRepository.registerOperation("Cutting", "This operation cuts materials",id);

        assertTrue(result.isPresent());
        assertEquals("Cutting", result.get().getOperationName());
        assertEquals(id,result.get().getOperationId());
        assertEquals("This operation cuts materials", result.get().getOperationDescription());
    }

    @Test
    public void testRegisterOperation_Duplicate() {
        ID id = new ID(7,TypeID.OPERATION);
        operationRepository.registerOperation("Cutting","First operation",id);
        Optional<Operation> result = operationRepository.registerOperation("Cutting", "Second operation",id);

        assertFalse(result.isPresent());
    }

    @Test
    public void testRegisterOperation_Duplicate2() {
        ID id = new ID(8,TypeID.OPERATION);
        operationRepository.registerOperation("Cutting",id);
        Optional<Operation> result = operationRepository.registerOperation("Cutting",id);

        assertFalse(result.isPresent());
    }
}