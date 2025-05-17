package memorygame;

public class Card {
    private String value;
    private boolean isFlipped;
    private boolean isMatched;

    public Card(String value) {
        this.value = value;
        this.isFlipped = false;
        this.isMatched = false;
    }

    public String getValue() {
        return value;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setFlipped(boolean flipped) {
        isFlipped = flipped;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}
