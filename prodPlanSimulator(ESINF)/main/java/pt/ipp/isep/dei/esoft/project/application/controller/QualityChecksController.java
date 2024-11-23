package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.QualityChecks;
import pt.ipp.isep.dei.esoft.project.domain.Simulator;
import pt.ipp.isep.dei.esoft.project.repository.OperationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;


public class QualityChecksController {

    private QualityChecks qualityChecks;
    private OperationRepository operationRepository;

    /**
     * Constructor initializes the operation repository.
     */
    public QualityChecksController() {
        operationRepository = getOperationRepository();
    }

    /**
     * Retrieves the OperationRepository instance.
     *
     * @return The OperationRepository instance.
     */
    private OperationRepository getOperationRepository() {
        if (operationRepository == null) {
            Repositories repository = Repositories.getInstance();
            operationRepository = repository.getOperationRepository();
        }
        return operationRepository;
    }

    /**
     * Retrieves or initializes the QualityChecks instance.
     *
     * @return A new or existing instance of QualityChecks.
     */
    public QualityChecks getQualityChecks() {
        qualityChecks = new QualityChecks();
        return qualityChecks;
    }

    /**
     * Retrieves the name of an operation using its ID.
     *
     * @param id The ID of the operation.
     * @return The name of the operation corresponding to the provided ID.
     */
    public String getNameByID(ID id) {
        return operationRepository.getNameByID(id);
    }

    /**
     * Handles the execution of quality checks, interacting with the simulator if available.
     *
     * @param confirmation A string input ("y" for yes, otherwise no) indicating user confirmation.
     */
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
