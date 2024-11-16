package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.QualityChecksController;
import pt.ipp.isep.dei.esoft.project.domain.ID;
import pt.ipp.isep.dei.esoft.project.domain.QualityChecks.QualityChecks;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class QualityChecksUI implements Runnable {

    private QualityChecksController controller;

    public QualityChecksUI() {
        controller = new QualityChecksController();
    }

    private QualityChecksController getController() {
        return controller;
    }

    @Override
    public void run() {

        System.out.println("\n\n══════════════════════════════════════════");
        System.out.println(ANSI_BRIGHT_WHITE + "               Quality Checks                 " + ANSI_RESET + "\n");
        printQualityChecks();

    }

    private PriorityQueue<Map<Integer, List<ID>>> getQualityChecks() {
        PriorityQueue<Map<Integer, List<ID>>> qualityChecks = new PriorityQueue<>();
        qualityChecks = controller.getQualityChecks().fillOperationsPriorityQueue();
        return qualityChecks;
    }

    private void printQualityChecks() {
        PriorityQueue<Map<Integer, List<ID>>> qualityChecks = getQualityChecks();
        qualityChecks.comparator().reversed();
        System.out.println(ANSI_DARK_ORANGE + "A higher number indicates a higher priority" + ANSI_RESET);
        while (!qualityChecks.isEmpty()) {
            Map<Integer, List<ID>> entry = qualityChecks.poll();
            for (Map.Entry<Integer, List<ID>> mapEntry : entry.entrySet()) {
                System.out.println(ANSI_BRIGHT_YELLOW + "Priority: " + mapEntry.getKey() + ANSI_RESET);
                for (ID id : mapEntry.getValue()) {
                    String name = controller.getNameByID(id);
                    System.out.printf("-> %s [%s]\n", name, id);
                }
            }
        }
    }


}
