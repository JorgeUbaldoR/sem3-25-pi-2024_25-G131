package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepositoriesTest {

    @Test
    void testGetInstance() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance);
    }


    @Test
    void testGetIDRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getIDRepository());
    }
}