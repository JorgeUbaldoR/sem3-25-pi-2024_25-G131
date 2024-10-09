package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.more.ID;
import pt.ipp.isep.dei.esoft.project.repository.IDRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;

public class IDController {
    private IDRepository idRepository;

    public IDController() {
        getIDRepository();
    }

    private IDRepository getIDRepository() {
        if(idRepository == null) {
            Repositories repositories = Repositories.getInstance();
            this.idRepository = repositories.getIDRepository();
        }
        return idRepository;
    }

    public Optional<ID> addID(ID newID) {
        return getIDRepository().addID(newID);
    }
}
