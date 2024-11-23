package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.QualityChecks;
import pt.ipp.isep.dei.esoft.project.domain.Simulator;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


public class QualityChecksController {

    private QualityChecks qualityChecks;
    private OperationRepository operationRepository;

    public QualityChecksController() {
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
        qualityChecks = new QualityChecks();
        return qualityChecks;
    }

    public String getNameByID(ID id) {
        return operationRepository.getNameByID(id);
    }

    public void askQualityChecksView(String confirmation) {
        qualityChecks = new QualityChecks();
        try {
            Simulator simulator = SimulatorController.getSharedSimulator();
            qualityChecks.performQualityChecks(confirmation, simulator.wasActivated());
        } catch (NullPointerException e) {
            qualityChecks.performQualityChecks(confirmation, false);
        }
    }

}
