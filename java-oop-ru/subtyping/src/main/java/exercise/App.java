package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {

    public static void swapKeyValue(KeyValueStorage storage) {
        Map<String, String> originalMap = storage.toMap();
        Map<String, String> swappedMap = new HashMap<>();

        for (Map.Entry<String, String> entry : originalMap.entrySet()) {
            swappedMap.put(entry.getValue(), entry.getKey());
        }
        originalMap.keySet().forEach(storage::unset);
        swappedMap.forEach(storage::set);
    }
}
// END
