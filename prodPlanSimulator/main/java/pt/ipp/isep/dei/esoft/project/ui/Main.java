package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.application.controller.SimulatorController;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        System.out.printf("%n%sDo login with:%n • %-9s -> %s%n • %-9s -> %s%s", ANSI_BRIGHT_BLACK, "User", "prodm@this.app", "Password", "manager", ANSI_RESET);


        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}