package memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private Card[][] grid;
    private int size;

    public Board(int size) {
        this.size = size;
        grid = new Card[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        List<String> values = new ArrayList<>();
        int numPairs = (size * size) / 2;
        for (int i = 1; i <= numPairs; i++) {
            values.add(String.valueOf(i));
            values.add(String.valueOf(i));
        }
        Collections.shuffle(values);

        int index = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Card(values.get(index));
                index++;
            }
        }
    }

    public void displayBoard() {
        System.out.print("   ");
        for (int col = 0; col < size; col++) {
            System.out.print(col + " ");
        }
        System.out.println();
        for (int row = 0; row < size; row++) {
            System.out.print(row + " ");
            if (row < 10) System.out.print(" ");
            for (int col = 0; col < size; col++) {
                Card card = grid[row][col];
                if (card.isMatched() || card.isFlipped()) {
                    System.out.print(card.getValue() + " ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    public boolean flipCard(int row, int col) {
        if (isValidPosition(row, col)) {
            Card card = grid[row][col];
            if (!card.isMatched() || !card.isFlipped()) {
                card.setFlipped(true);
                return true;
            }
        }
        return false;
    }

    public void hideCard(int row, int col) {
        if (isValidPosition(row, col)) {
            grid[row][col].setFlipped(false);
        }
    }

    public void matchCards(int row1, int col1, int row2, int col2) {
        grid[row1][col1].setMatched(true);
        grid[row2][col2].setMatched(true);
    }

    public boolean allMatched() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (!grid[row][col].isMatched()) {
                    return false;
                }
            }
        }
        return true;
    }

    public Card getCard(int row, int col) {
        if (isValidPosition(row, col)) {
            return grid[row][col];
        }
        return null;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }
}
