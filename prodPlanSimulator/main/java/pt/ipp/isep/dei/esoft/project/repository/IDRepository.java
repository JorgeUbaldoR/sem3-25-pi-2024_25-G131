package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;

import java.util.*;

public class IDRepository {
    private Map<String, ID> ids;

    public IDRepository() {
        ids = new HashMap<>();
    }

    //------------ Add ID ---------------
    public Optional<ID> addID(ID idToAdd) {
        Optional<ID> newID = Optional.empty();

        if(!this.ids.containsKey(idToAdd.getKeyID())){
            this.ids.put(idToAdd.getKeyID(), idToAdd);
            newID = Optional.of(idToAdd.clone());
        }
        return newID;
    }

    //---------------------------------------------


    public List<ID> getIds() {
        return new ArrayList<>(this.ids.values());
    }
}
