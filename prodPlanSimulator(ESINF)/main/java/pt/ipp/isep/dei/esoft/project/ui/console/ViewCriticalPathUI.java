package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;
import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ViewCriticalPathUI implements Runnable {

    private final ProductionTreeController controller;

    public ViewCriticalPathUI() {
        controller = new ProductionTreeController();
    }


    @Override
    public void run() {
        printCriticalPaths();
    }

    private List<List<String>> getCriticalPaths() {
        return controller.getProductionTree().getCriticalPath();
    }

    private void printCriticalPaths() {
        List<List<String>> paths = controller.getProductionTree().getCriticalPath();

        System.out.println();

        if (paths.isEmpty()) {
            System.out.println("No critical paths found.");
        } else {
            int boxWidth = 50;
            String title = "Critical Paths";
            int padding = (boxWidth - title.length()) / 2;

            System.out.println("╔" + "═".repeat(boxWidth) + "╗");
            System.out.println("║" + " ".repeat(padding) + title + " ".repeat(boxWidth - title.length() - padding) + "║");
            System.out.println("╚" + "═".repeat(boxWidth) + "╝");

            System.out.println();

            // Encontrar o tamanho máximo das listas
            int maxLength = paths.stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            // Filtrar apenas as listas que têm o tamanho máximo
            List<List<String>> longestPaths = paths.stream()
                    .filter(path -> path.size() == maxLength)
                    .collect(java.util.stream.Collectors.toList()); // Substituído por Collectors.toList()

            for (int i = 0; i < longestPaths.size(); i++) {
                List<String> path = longestPaths.get(i);

                Collections.reverse(path);

                System.out.println(ANSI_BRIGHT_BLACK + "Path " + (i + 1) + ":" + ANSI_RESET);
                for (int j = 0; j < path.size(); j++) {
                    if (j == path.size() - 1) {
                        System.out.println(" ".repeat(j * 4) + ANSI_BRIGHT_BLACK + "└─  " + ANSI_RESET + ANSI_INDIAN_RED + path.get(j) + ANSI_RESET);
                    } else {
                        System.out.println(" ".repeat(j * 4) + ANSI_BRIGHT_BLACK + "├─ " + ANSI_RESET + path.get(j));
                    }
                }
                System.out.println();
            }
        }
    }
}
