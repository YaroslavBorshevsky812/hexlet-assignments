package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("<" + name);

        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(" ").append(key).append("=\"").append(value).append("\"");
        }
        sb.append(">");

        sb.append(body);

        children.forEach(ch -> {
            sb.append(ch.toString());
        });

        sb.append("</" + name + ">");
        return sb.toString();
    }
}
// END
