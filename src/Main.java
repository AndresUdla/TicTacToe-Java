import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static final Scanner scanner = new Scanner(System.in);

    //Main method that initializes and manages the game loop.
    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe!");

        do {
            initializeBoard();
            boolean gameFinished = false;
            showBoard();

            while (!gameFinished) {
                play();
                showBoard();
                if (verifyWinner()) {
                    System.out.println("Congratulations! Player " + currentPlayer + " has won.");
                    gameFinished = true;
                } else if (isDraw()) {
                    System.out.println("It's a draw!");
                    gameFinished = true;
                } else {
                    toggleShift();
                }
            }
        } while (playAgain());

        System.out.println("Thanks for playing. See you next time!");
        scanner.close();
    }

    /**
     * Initializes the game board by filling it with dashes ('-').
     * Resets the current player to 'X'.
     */
    public static void initializeBoard() {
        for (char[] chars : board) {
            Arrays.fill(chars, '-');
        }
        currentPlayer = 'X';
    }

    //Displays the current state of the game board.
    public static void showBoard() {
        System.out.println("    1   2   3");
        System.out.println("  -------------");
        for (int i = 0; i < board.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
            System.out.println("  -------------");
        }
    }

    /**
     * Prompts the user for valid input (row or column) within the range of 1 to 3.
     * @param message The message displayed to the user.
     * @return The validated input (0-based index).
     */
    public static int getValidInput(String message) {
        int input;
        while (true) {
            System.out.println(message);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt() - 1;
                if (input >= 0 && input < 3) {
                    return input;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input. Please enter a number between 1 and 3.");
        }
    }

    //Handles the player's turn, ensuring they make a valid move on the board.
    public static void play() {
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + "'s turn.");
            row = getValidInput("Enter row (1-3):");
            col = getValidInput("Enter column (1-3):");

            if (board[row][col] == '-') {
                board[row][col] = currentPlayer;
                break;
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    /**
     * Checks if the current player has won the game.
     * @return true if the current player has a winning combination, false otherwise.
     */
    public static boolean verifyWinner() {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) ||
                    (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer)) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the game has ended in a draw (no empty spaces left and no winner).
     * @return true if the game is a draw, false otherwise.
     */
    public static boolean isDraw() {
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    //Switches the turn to the next player.
    public static void toggleShift() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Asks the player if they want to play again.
     * @return true if the player wants to restart, false otherwise.
     */
    public static boolean playAgain() {
        System.out.println("Do you want to play again? (y/n)");
        char response = scanner.next().toLowerCase().charAt(0);
        return response == 'y';
    }
}
