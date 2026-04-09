import java.util.LinkedHashMap;
import java.util.Map;

// Checkpoint B: Single responsibility = parse a raw semicolon-delimited string.
// Knows nothing about validation, saving, or printing.
public class RawInputParser {

    public ParsedInput parse(String raw) {
        Map<String, String> kv = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] t = p.split("=", 2);
            if (t.length == 2) kv.put(t[0].trim(), t[1].trim());
        }
        return new ParsedInput(
            kv.getOrDefault("name",    ""),
            kv.getOrDefault("email",   ""),
            kv.getOrDefault("phone",   ""),
            kv.getOrDefault("program", "")
        );
    }
}
