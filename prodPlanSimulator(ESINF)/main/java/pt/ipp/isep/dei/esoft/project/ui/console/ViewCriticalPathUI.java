package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ProductionTreeController;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A user interface class responsible for displaying critical paths of a production tree.
 * Implements {@link Runnable} to allow execution in a thread.
 */
public class ViewCriticalPathUI implements Runnable {

    /**
     * Controller used to access and interact with the production tree.
     */
    private final ProductionTreeController controller;

    /**
     * Constructs the ViewCriticalPathUI and initializes the {@link ProductionTreeController}.
     */
    public ViewCriticalPathUI() {
        controller = new ProductionTreeController();
    }

    /**
     * Entry point for executing the UI logic. Prints the critical paths of the production tree.
     */
    @Override
    public void run() {
        printCriticalPaths();
    }

    /**
     * Retrieves the critical paths from the production tree via the controller.
     *
     * @return A {@link List} of critical paths, where each path is represented as a {@link List} of {@link String}.
     */
    private List<List<String>> getCriticalPaths() {
        return controller.getProductionTree().getCriticalPath();
    }

    /**
     * Prints the critical paths of the production tree to the console.
     * If no critical paths are found, displays an appropriate message.
     * Otherwise, the paths are formatted and displayed, highlighting the longest paths.
     * <p>
     * Features:
     * - Displays the critical paths in a structured box format.
     * - Highlights the longest paths by their operation names.
     * - Uses ANSI escape codes for colors and styling.
     */
    private void printCriticalPaths() {
        List<List<String>> paths = controller.getProductionTree().getCriticalPath();

        System.out.println();

        if (paths.isEmpty()) {
            System.out.println("No critical paths found.");
        } else {
            int boxWidth = 50;
            String title = "Critical Paths";
            int padding = (boxWidth - title.length()) / 2;

            // Print header box
            System.out.println("╔" + "═".repeat(boxWidth) + "╗");
            System.out.println("║" + " ".repeat(padding) + title + " ".repeat(boxWidth - title.length() - padding) + "║");
            System.out.println("╚" + "═".repeat(boxWidth) + "╝");

            System.out.println();

            // Find the maximum path length
            int maxLength = paths.stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);

            // Filter paths to only include the longest ones
            List<List<String>> longestPaths = paths.stream()
                    .filter(path -> path.size() == maxLength)
                    .collect(java.util.stream.Collectors.toList());

            for (int i = 0; i < longestPaths.size(); i++) {
                List<String> path = longestPaths.get(i);

                // Reverse the path for correct order display
                Collections.reverse(path);

                // Print path with indentation and styling
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

