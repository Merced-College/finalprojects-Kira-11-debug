package memorygame;

import java.util.Scanner;
import java.util.Stack;

public class Game {
    private Board board;
    private Stack<int[]> moveStack;
    private int moves;
    private Scanner scanner;

    public Game(int size) {
        board = new Board(size);
        moveStack = new Stack<>();
        moves = 0;
        scanner = new Scanner(System.in);
    }

    public void playGame() {
        System.out.println("Welcome to the Memory Matching Game!");

        while (!board.allMatched()) {
            board.displayBoard();
            int[] firstPick = pickCard();
            int[] secondPick = pickCard();

            board.displayBoard();

            Card firstCard = board.getCard(firstPick[0], firstPick[1]);
            Card secondCard = board.getCard(secondPick[0], secondPick[1]);

            if (firstCard.getValue().equals(secondCard.getValue())) {
                System.out.println("Match found!");
                board.matchCards(firstPick[0], firstPick[1], secondPick[0], secondPick[1]);
            } else {
                System.out.println("Not a match!");
                pause();
                board.hideCard(firstPick[0], firstPick[1]);
                board.hideCard(secondPick[0], secondPick[1]);
            }

            moveStack.push(firstPick);
            moveStack.push(secondPick);

            moves++;
        }

        System.out.println("Congratulations! You completed the game in " + moves + " moves.");
    }

    private int[] pickCard() {
        int row, col;
        while (true) {
            System.out.print("Enter row: ");
            row = scanner.nextInt();
            System.out.print("Enter column: ");
            col = scanner.nextInt();
            if (board.flipCard(row, col)) {
                return new int[]{row, col};
            } else {
                System.out.println("Invalid selection. Try again.");
            }
        }
    }

    private void pause() {
        try {
            Thread.sleep(1500); // 1.5 second pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Game game = new Game(4); // 4x4 grid
        game.playGame();
    }
}

