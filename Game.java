import java.util.Scanner;

public class Game {
    private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';

    public static void main(String[] args) {
        initializeBoard();
        boolean YouWin = false;

        while (!YouWin) {
            displayBoard();
            int[] move = getPlayerMove();
            makeMove(move[0], move[1]);

            if (checkWin()) {
                YouWin = true;
                displayBoard();
                System.out.println(currentPlayer + " YouWin!");
            } else if (isBoardFull()) {
                YouWin = true;
                displayBoard();
                System.out.println("Draw!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    private static void displayBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
    }

    private static int[] getPlayerMove() {
        Scanner scanner = new Scanner(System.in);
        int[] move = new int[2];
        boolean validMove = false;

        while (!validMove) {
            System.out.print("Player " + currentPlayer + ", enter row (0-2) and column (0-2): ");
            move[0] = scanner.nextInt();
            move[1] = scanner.nextInt();

            if (isValidMove(move[0], move[1])) {
                validMove = true;
            } else {
                System.out.println("Gerakan salah, coba lagi.");
            }
        }

        return move;
    }

    private static boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
    }

    private static void makeMove(int row, int col) {
        board[row][col] = currentPlayer;
    }

    private static boolean checkWin() {
        // Memeriksa baris, kolom, dan diagonal untuk mendapatkan kemenangan
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true; // Baris menang
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true; // Kolom menang
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true; // Kemenangan diagonal (kiri atas ke kanan bawah)
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true; // Kemenangan diagonal (kanan atas ke kiri bawah)
        }
        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Ruang kosong, jadi papannya tidak penuh
                }
            }
        }
        return true; // Papan penuh
    }
}
