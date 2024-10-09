package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;

public class Repositories {

    private static Repositories instance;
    private final AuthenticationRepository authenticationRepository;
    private final MachineRepository machineRepository;
    private final ItemRepository itemRepository;

    private Repositories() {
        authenticationRepository = new AuthenticationRepository();
        machineRepository = new MachineRepository();
        itemRepository = new ItemRepository();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }



    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public MachineRepository getMachineRepository() {
        return machineRepository;
    }

    public ItemRepository getItemRepository() {
        return itemRepository;
    }
}