package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_BRIGHT_WHITE;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.ANSI_RESET;

public class ExtrasUI implements Runnable {
    public ExtrasUI() {
    }


    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Add a Machine", new AddMachineUI()));
        options.add(new MenuItem("Add an Operation", new AddOperationUI()));
        options.add(new MenuItem("Change operation description", new AddOperationDescriptionUI()));


        int option = 0;
        do {
            System.out.println("\n\n╔════════════════════════════════════════╗");
            option = Utils.showAndSelectIndex(options, "║" + ANSI_BRIGHT_WHITE + "          PM MENU | EXTRAS UI    " + ANSI_RESET + "       ║");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
