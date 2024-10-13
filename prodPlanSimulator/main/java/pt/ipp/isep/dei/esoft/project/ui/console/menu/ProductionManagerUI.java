package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.console.AddOperationDescriptionUI;
import pt.ipp.isep.dei.esoft.project.ui.console.ViewOperationsUI;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProductionManagerUI implements Runnable {

    public ProductionManagerUI() {
    }


    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Request [Operation -> Machines] list", new ViewOperationsUI()));
        options.add(new MenuItem("Add/change operation description", new AddOperationDescriptionUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\n--- PRODUCTION MANAGER MENU -------------------------");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}

