package org.iut.refactoring;

import java.util.ArrayList;

public class ArrayListLogs implements  ServiceStockageLogs {
    private ArrayList<String> logs = new ArrayList<>();
    @Override
    public void add(String log) {
        logs.add(log);
    }

    @Override
    public ArrayList<String> getLogs() {
        return logs;
    }
}
