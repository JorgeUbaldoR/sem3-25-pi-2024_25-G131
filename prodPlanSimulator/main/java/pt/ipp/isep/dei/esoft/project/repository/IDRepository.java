package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;

import java.util.*;

/**
 * Repository class for managing ID objects.
 * This class allows adding and retrieving IDs in a collection.
 */
public class IDRepository {
    private Map<String, ID> ids;

    /**
     * Constructs a new IDRepository instance.
     * Initializes an empty HashMap to store ID objects.
     */
    public IDRepository() {
        ids = new HashMap<>();
    }

    /**
     * Adds a new ID to the repository if it does not already exist.
     *
     * @param idToAdd the ID to be added to the repository
     * @return an Optional containing a clone of the added ID if successful,
     *         or an empty Optional if the ID already exists
     */
    public Optional<ID> addID(ID idToAdd) {
        Optional<ID> newID = Optional.empty();

        if(!this.ids.containsKey(idToAdd.getKeyID())){
            this.ids.put(idToAdd.getKeyID(), idToAdd);
            newID = Optional.of(idToAdd.clone());
        }
        return newID;
    }

    /**
     * Retrieves all IDs from the repository.
     *
     * @return a List containing all ID objects in the repository
     */
    public List<ID> getIds() {
        return new ArrayList<>(this.ids.values());
    }
}
