package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_BRIGHT_WHITE;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class DataBaseUI implements Runnable {


    public DataBaseUI() {
    }


    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("List [Operation->Machines]", new ViewOperationsUI()));
        options.add(new MenuItem("Show Information", new ShowInformationUI()));
        options.add(new MenuItem("Show Production Tree", new ProductionTreeUI()));
        options.add(new MenuItem("Search Operation/Material", new SearchProductionTreeUI()));
        options.add(new MenuItem("Generate Diagram", new DiagramGenerationUI()));
        options.add(new MenuItem("Show Quality Checks", new QualityChecksUI()));
        options.add(new MenuItem("Quantity BY Material", new BSTUI()));

        int option = 0;
        do {
            System.out.println("\n\n╔════════════════════════════════════════╗");
            option = Utils.showAndSelectIndex(options, "║" + ANSI_BRIGHT_WHITE + "         PM MENU | DATABASE UI    " + ANSI_RESET + "      ║");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

}
