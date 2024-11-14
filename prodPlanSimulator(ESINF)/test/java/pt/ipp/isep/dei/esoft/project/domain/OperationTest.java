package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import static org.junit.jupiter.api.Assertions.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_BRIGHT_RED;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class OperationTest {

    private Operation operation;

    @BeforeEach
    public void setUp() {
        operation = new Operation("Cutting", new ID(1, TypeID.OPERATION),"This operation cuts materials");
    }

    @Test
    public void testConstructor_AllParameters() {
        assertEquals("Cutting", operation.getOperationName());
        assertEquals("This operation cuts materials", operation.getOperationDescription());
        assertEquals(1,operation.getOperationId().getSerial());
    }

    @Test
    public void testConstructor_NameAndDuration() {
        Operation operation = new Operation("Welding");

        assertEquals("Welding", operation.getOperationName());
        assertEquals(operation.getOperationDescription(),ANSI_BRIGHT_RED + "No description provided!" + ANSI_RESET);
    }

    @Test
    public void testConstructor_NameAndDescription() {
        Operation operation = new Operation("Assembling", new ID(1, TypeID.OPERATION),"This operation assembles parts");

        assertEquals("Assembling", operation.getOperationName());
        assertEquals("This operation assembles parts", operation.getOperationDescription());
    }

    @Test
    public void testConstructor_NameOnly() {
        Operation operation = new Operation("Polishing");

        assertEquals("Polishing", operation.getOperationName());
        assertEquals(ANSI_BRIGHT_RED + "No description provided!" + ANSI_RESET, operation.getOperationDescription());
    }

    @Test
    public void testSetOperationName() {
        operation.setOperationName("Drilling");

        assertEquals("Drilling", operation.getOperationName());
    }

    @Test
    public void testSetOperationDescription_Success() {
        boolean result = operation.setOperationDescription("New description");

        assertTrue(result);
        assertEquals("New description", operation.getOperationDescription());
    }

    @Test
    public void testSetOperationDescription_NoChange() {
        operation.setOperationDescription("This operation cuts materials");

        boolean result = operation.setOperationDescription("This operation cuts materials");

        assertFalse(result);
    }


    @Test
    public void testEquals_SameObject() {
        assertTrue(operation.equals(operation));
    }

    @Test
    public void testEquals_DifferentObject_SameName() {
        Operation operation2 = new Operation("Cutting", new ID(1, TypeID.OPERATION),"Different description");

        assertEquals(operation, operation2);
    }

    @Test
    public void testEquals_DifferentObject_DifferentName() {
        Operation operation2 = new Operation("Drilling", new ID(1, TypeID.OPERATION),"Different description");

        assertNotEquals(operation, operation2);
    }

    @Test
    public void testHashCode_SameName() {
        Operation operation2 = new Operation("Cutting");

        assertEquals(operation.hashCode(), operation2.hashCode());
    }

    @Test
    public void testHashCode_DifferentName() {
        Operation operation2 = new Operation("Drilling");

        assertNotEquals(operation.hashCode(), operation2.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals("Cutting", operation.toString().trim());
    }

    @Test
    public void testClone() {
        Operation clonedOperation = operation.clone();

        assertNotSame(operation, clonedOperation);
        assertEquals(operation, clonedOperation);
        assertEquals(operation.getOperationName(), clonedOperation.getOperationName());
        assertEquals(operation.getOperationDescription(), clonedOperation.getOperationDescription());
        assertEquals(operation.getOperationId(), clonedOperation.getOperationId());
    }
}