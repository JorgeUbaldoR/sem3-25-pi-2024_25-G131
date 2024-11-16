package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.domain.sprint2.WriterTree;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import java.io.IOException;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class Main {
    private static final String UML_FILE_PATH = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/output/";
    private static final String UML_TREE_PROD_PATH = "prodPlanSimulator/main/java/pt/ipp/isep/dei/esoft/project/files/output/";

    public static void main(String[] args) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        //new FileWriter(UML_FILE_PATH);
        new WriterTree(UML_TREE_PROD_PATH);
        System.out.printf("%n%sDo login with:%n • %-9s -> %s%n • %-9s -> %s%s", ANSI_BRIGHT_BLACK, "User", "prodm@this.app", "Password", "manager", ANSI_RESET);


        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}