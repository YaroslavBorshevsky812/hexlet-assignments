package exercise;

import java.io.IOException;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN

    @Test
    void testFileKVSetGet() throws IOException {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        assertEquals("value", storage.get("key", "default"));
        storage.set("newKey", "newValue");
        assertEquals("newValue", storage.get("newKey", "default"));

    }

    @Test
    void testFileKVUnset() throws IOException {
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        assertEquals("value", storage.get("key", "default"));
        storage.unset("key");
        assertEquals("default", storage.get("key", "default"));
    }

    @Test
    void testFileKVToMap() throws IOException {
        Map<String, String> initialMap = Map.of("key1", "value1", "key2", "value2");
        KeyValueStorage storage = new FileKV(filepath.toString(), initialMap);

        Map<String, String> resultMap = storage.toMap();

        assertThat(resultMap).containsAllEntriesOf(initialMap);
        assertEquals(resultMap.size(), initialMap.size());

    }


    @Test
    void mustBeImmutableTest() throws IOException {
        Map<String, String> initial = new HashMap<>();
        initial.put("key", "10");

        Map<String, String> clonedInitial = new HashMap<>();
        clonedInitial.putAll(initial);

        KeyValueStorage storage = new FileKV(filepath.toString(), initial);
        initial.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);

        Map<String, String> map = storage.toMap();
        map.put("key2", "value2");
        assertThat(storage.toMap()).isEqualTo(clonedInitial);
    }
    // END
}
