package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static pt.ipp.isep.dei.esoft.project.domain.more.ColorfulOutput.*;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {
    final static String COLOR_OPTIONS = ANSI_BRIGHT_BLACK;

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.printf("%s : ", prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List<?> list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List<?> list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public int showAndSelectIndexDataBase(List<?> list, String header) {
        showListDataBase(list, header);
        return selectsIndex(list);
    }

    static public void showListDataBase(List<?> list, String header) {
        System.out.println(header);
        System.out.println("╚═════════════════════════════════════════════════╝");

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.printf("║    %s(%d)%s -  %-37s %-2s%n",COLOR_OPTIONS,index,ANSI_RESET,o.toString(),"║");
        }
        System.out.printf("║    %s(0)%s -  %-29s %9s%n",COLOR_OPTIONS,ANSI_RESET,"Cancel","║");
        System.out.println("╚═════════════════════════════════════════════════╝");
    }

    static public void showList(List<?> list, String header) {
        System.out.println(header);
        System.out.println("╚════════════════════════════════════════╝");

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.printf("║    %s(%d)%s -  %-28s %-2s%n",COLOR_OPTIONS,index,ANSI_RESET,o.toString(),"║");
        }
        System.out.printf("║    %s(0)%s -  %-20s %9s%n",COLOR_OPTIONS,ANSI_RESET,"Cancel","║");
        System.out.println("╚════════════════════════════════════════╝");
    }

    static public Object selectsObject(List<?> list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input;
        int value;
        do {
            input = Utils.readLineFromConsole("Type your option");
            try {
                value = Integer.valueOf(input);
            } catch (NumberFormatException ex) {
                value = -1;
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }
}