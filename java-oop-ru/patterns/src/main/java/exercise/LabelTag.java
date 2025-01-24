package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private String value;
    private String text;
    private TagInterface inputTag;

    public LabelTag(String text, TagInterface inputTag) {
        this.text = text;
        this.inputTag = inputTag;
    }

    @Override
    public String render() {
        return "<label>" + this.text + this.inputTag.render() + "</label>";
    }
}
// END
