package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.Machine;
import pt.ipp.isep.dei.esoft.project.domain.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class SimulatorUI implements Runnable {


    private SimulatorController controller;

    public SimulatorUI() {
        controller = new SimulatorController();
    }

    private SimulatorController getSimulationController() {
        return controller;
    }

    @Override
    public void run() {
        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Simulation                 " + ANSI_RESET + "\n");
        System.out.println(ANSI_BRIGHT_GREEN+"\n              START OF THE SIMULATION..."+ANSI_RESET);

        getSimulationController().startSimulation();
    }




}


