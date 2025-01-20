package exercise;

// BEGIN

public class ReversedSequence implements CharSequence {

    private final String sequence;

    public ReversedSequence(String text) {
        this.sequence = new StringBuilder(text).reverse().toString();
    }

    @Override
    public int length() {
        return sequence.length();
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length());
        }
        return sequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if (start < 0 || end > length() || start > end) {
            throw new IndexOutOfBoundsException("Start: " + start + ", End: " + end + ", Length: " + length());
        }

        return sequence.substring(start, end);
    }

    @Override
    public String toString() {
        return new StringBuilder(sequence).reverse().toString();
    }
}
// END
