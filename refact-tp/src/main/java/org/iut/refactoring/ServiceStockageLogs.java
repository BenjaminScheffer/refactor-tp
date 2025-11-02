package org.iut.refactoring;

import java.util.ArrayList;

public interface ServiceStockageLogs {
    void add(String log);
    ArrayList<String> getLogs();
}
