package exercise;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final Path filePath;
    private Map<String, String> storage;


    public FileKV(String filePath, Map<String, String> initialStorage) {
        this.filePath = Paths.get(filePath);
        this.storage = new HashMap<>(initialStorage);
    }

    @Override
    public void set(String key, String value) {
        storage.put(key, value);
        save();
    }

    @Override
    public void unset(String key) {
        storage.remove(key);
        save();
    }

    @Override
    public String get(String key, String defaultValue) {
        return storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storage);
    }

    private void save() {
        Utils.writeFile(String.valueOf(filePath), Utils.serialize(storage));
    }
}
// END
