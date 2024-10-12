package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.repository.IDRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

/**
 * Controller class that manages the interaction with the ID repository.
 * This class provides functionality to add IDs to the repository.
 */
public class IDController {
    private IDRepository idRepository;

    /**
     * Constructs an IDController instance.
     * Initializes the IDRepository by retrieving it from the Repositories singleton.
     */
    public IDController() {
        getIDRepository();
    }

    /**
     * Retrieves the IDRepository instance.
     *
     * @return the IDRepository instance
     */
    private IDRepository getIDRepository() {
        if(idRepository == null) {
            Repositories repositories = Repositories.getInstance();
            this.idRepository = repositories.getIDRepository();
        }
        return idRepository;
    }

    /**
     * Adds a new ID to the repository.
     *
     * @param newID the ID to be added
     * @return an Optional containing the newly added ID if successful, or empty if the ID already exists
     */
    public Optional<ID> addID(ID newID) {
        return getIDRepository().addID(newID);
    }
}
