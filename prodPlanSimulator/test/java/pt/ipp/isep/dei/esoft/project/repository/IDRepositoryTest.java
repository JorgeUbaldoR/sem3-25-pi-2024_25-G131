package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.enumclasses.TypeID;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
class IDRepositoryTest {
    IDRepository repository = new IDRepository();

    @Test
    void addID() {
        ID id1 = new ID(100, TypeID.ITEM);
        Optional<ID> result = repository.addID(id1);

        assertTrue(result.isPresent());

        ID id2 = result.get();
        Optional<ID> result2 = repository.addID(id2);
        assertFalse(result2.isPresent());

    }

    @Test
    void getIds() {
        ID id1 = new ID(100, TypeID.ITEM);
        ID id2 = new ID(100, TypeID.ITEM);
        repository.addID(id1);
        repository.addID(id2);
        List<ID> ids = repository.getIds();
        assertTrue(ids.contains(id1));
        assertTrue(ids.contains(id2));
    }
}