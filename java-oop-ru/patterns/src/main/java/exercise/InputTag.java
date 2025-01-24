package exercise;

// BEGIN
public class InputTag implements TagInterface {
    private String value;
    private String type;


    public InputTag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String render() {
        return "<input " + "type=" + "\""+  this.type + "\"" + " " + "value=" + "\"" + this.value + "\"" + ">";
    }
}
// END
