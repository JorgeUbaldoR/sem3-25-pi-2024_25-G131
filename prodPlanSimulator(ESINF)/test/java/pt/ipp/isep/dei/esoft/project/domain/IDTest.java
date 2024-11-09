package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import static org.junit.jupiter.api.Assertions.*;

class IDTest {

    private ID item1;
    private ID item2;
    private ID item3;
    private ID item4;
    private ID item5;

    @BeforeEach
    void setUp() {
        item1 = new ID(100, TypeID.ITEM);
        item2 = new ID(101, TypeID.ITEM);
        item3 = new ID(102, TypeID.MACHINE);
        item4 = new ID(103, TypeID.MACHINE);
        item5 = new ID(103, TypeID.MACHINE);

    }

    @Test
    void getTypeID() {
        System.out.println("Test Get Type ID");
        assertEquals(TypeID.ITEM, item1.getTypeID());
        assertEquals(TypeID.ITEM, item2.getTypeID());
        assertEquals(TypeID.MACHINE, item3.getTypeID());
        assertEquals(TypeID.MACHINE, item4.getTypeID());
    }

    @Test
    void getSerial() {
        System.out.println("Test Get Serial ID");
        assertEquals(100, item1.getSerial());
        assertEquals(101, item2.getSerial());
        assertEquals(102, item3.getSerial());
        assertEquals(103, item4.getSerial());
    }

    @Test
    void testEquals() {
        System.out.println("Test Equals");
        assertNotEquals(item1, item2);
        assertEquals(item1, item1);
        assertNotEquals(item2, null);
        assertNotEquals(item3, new Object());
        assertEquals(item4, item5);
    }

    @Test
    void compareTo() {
        System.out.println("Test CompareTo");
        assertEquals(-1, item1.compareTo(item2));
        assertEquals(1, item2.compareTo(item1));
        assertEquals(0, item5.compareTo(item4));
        assertThrows(IllegalArgumentException.class, () -> item1.compareTo(item5));
    }

    @Test
    void testClone() {
        System.out.println("Test Clone");
        ID clone = item1.clone();
        assertNotSame(item1, clone);
    }

}