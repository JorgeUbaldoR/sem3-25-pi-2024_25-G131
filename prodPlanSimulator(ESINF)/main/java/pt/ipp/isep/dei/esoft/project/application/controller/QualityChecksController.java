package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.QualityChecks.QualityChecks;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Optional;


public class QualityChecksController {

    private final QualityChecks qualityChecks;
    private OperationRepository operationRepository;

    public QualityChecksController() {
        qualityChecks = new QualityChecks();
        operationRepository = getOperationRepository();
    }


    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repository = Repositories.getInstance();
            operationRepository = repository.getOperationRepository();
        }
        return operationRepository;
    }

    public QualityChecks getQualityChecks() {
        return qualityChecks;
    }

    public String getNameByID(ID id) {
        return operationRepository.getNameByID(id);
    }

}
