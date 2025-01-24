package exercise;

public class Main {

    public static void main(String[] args) {
        var input = new InputTag("submit", "Save");
        var labelTag = new LabelTag("Press Submit", input);
        System.out.println(labelTag.render());
    }
}
