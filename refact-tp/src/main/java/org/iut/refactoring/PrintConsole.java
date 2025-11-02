package org.iut.refactoring;

import java.util.ArrayList;

public class PrintConsole implements ServicePrintLogs {

    @Override
    public void print(ArrayList<String> logs) {
        System.out.println("=== LOGS ===");
        logs.forEach(System.out::println);
    }
}
