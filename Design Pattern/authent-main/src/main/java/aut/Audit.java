package aut;

import java.time.Instant;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Audit {

    private Map<Instant, String> logTable = new HashMap<>();

    public void insertLog(String user, String message) {
        logTable.put(Instant.now(), user + ": " + message);
    }

    public void dumpLogTable() {
        System.out.println("\nLog Table");
        logTable.entrySet().stream().sorted(Comparator.comparing(c -> c.getKey()))
                .forEach(c -> System.out.println(String.format("%-30s", c.getKey()) + " " + c.getValue()));
    }

}
