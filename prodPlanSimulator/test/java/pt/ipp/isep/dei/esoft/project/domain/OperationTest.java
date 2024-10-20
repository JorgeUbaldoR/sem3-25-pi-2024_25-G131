package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_BRIGHT_RED;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class OperationTest {

    private Operation operation;

    @BeforeEach
    public void setUp() {
        operation = new Operation("Cutting", "This operation cuts materials", 10.0f);
    }

    @Test
    public void testConstructor_AllParameters() {
        assertEquals("Cutting", operation.getOperationName());
        assertEquals("This operation cuts materials", operation.getOperationDescription());
        assertEquals(10.0f, operation.getDuration(), 0.01);
    }

    @Test
    public void testConstructor_NameAndDuration() {
        Operation operation = new Operation("Welding", 5.0f);

        assertEquals("Welding", operation.getOperationName());
        assertNull(operation.getOperationDescription());
        assertEquals(5.0f, operation.getDuration(), 0.01);
    }

    @Test
    public void testConstructor_NameAndDescription() {
        Operation operation = new Operation("Assembling", "This operation assembles parts");

        assertEquals("Assembling", operation.getOperationName());
        assertEquals("This operation assembles parts", operation.getOperationDescription());
        assertEquals(0.0f, operation.getDuration(), 0.01);
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
    public void testSetDuration() {
        operation.setDuration(7.5f);

        assertEquals(7.5f, operation.getDuration(), 0.01);
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(operation.equals(operation));
    }

    @Test
    public void testEquals_DifferentObject_SameName() {
        Operation operation2 = new Operation("Cutting", "Different description", 5.0f);

        assertTrue(operation.equals(operation2));
    }

    @Test
    public void testEquals_DifferentObject_DifferentName() {
        Operation operation2 = new Operation("Drilling", "Different description", 5.0f);

        assertFalse(operation.equals(operation2));
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
        assertEquals("Cutting\n", operation.toString());
    }

    @Test
    public void testClone() {
        Operation clonedOperation = operation.clone();

        assertNotSame(operation, clonedOperation);
        assertEquals(operation, clonedOperation);
        assertEquals(operation.getOperationName(), clonedOperation.getOperationName());
        assertEquals(operation.getOperationDescription(), clonedOperation.getOperationDescription());
        assertEquals(operation.getDuration(), clonedOperation.getDuration(), 0.01);
    }
}