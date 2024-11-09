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

    @Test
    void testGetMachineRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getMachineRepository());
    }

    @Test
    void testGetOperationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getOperationRepository());
    }

    @Test
    void testGetItemRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getItemRepository());
    }

    @Test
    void testGetIdRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getIDRepository());
    }

    @Test
    void testGetAuthenticationRepository() {
        Repositories instance = Repositories.getInstance();
        assertNotNull(instance.getAuthenticationRepository());
    }
}