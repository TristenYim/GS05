/*
Author: Tristen Yim
Project Name: Tic-Tac-Toe (GS05-01)
Purpose:
    To let the user play a game of tic-tac-toe against another user
Pseudocode:
    Characters X and O are constants and are used to refer to player 1 and player 2. If you want to, you can change the characters to be something else!
    testForDraw takes the board as input
        In a for-each loop the program traverses the board and returns true if any value is its default. Otherwise it returns false
    testForWin takes the board as input and a character, either X or O
        testForWin checks the diagonals by brute force
        Then, in a loop, it checks all the verticals and horizontals
            If any of these result in a win, it returns true. Otherwise it returns false
    printBoard takes the gameBoard as input
        printBoard prints out the board in a more readable format, reading the gameBoard array to do so
        Whenever it sees the '\0' character, it replaces it with a number representing its location
            This number is what the user inputs when they want to make a move
    makeMove takes the player character input, the gameBoard, and console Scanner input
        Indefinitely:
            printBoard is called
            The user is prompted to input a location as the place they want to go
            If this input actually corresponds to an empty spot on the board, that player's symbol is placed there and the methods returns, breaking out of the loop
    fancyPrintLn is like printLn but with a little bit of delay between each character. It takes only a string as input
    main
        Declares the console input and the gameBoard
        In a loop, it runs makeMove for both X and O
        Then it runs testForDraw, and testForWin for both X and O
            If any of these are true, it states the outcome (X won, O won, or it's a draw)
Maintenance Log:
    Started (19 Oct 2022 10:19)
    Finished testForWin and testForDraw but main doesn't do much of anything yet (19 Oct 2022 10:55)
    Worked on the method to make a move. Currently the user input doesn't quite work because the book isn't good at explaining things, but it in theory works (20 Oct 2022 10:55)
    Finished (21 Oct 2022 10:16)
*/

import java.util.Scanner;

public class TicTacToe {
    private static final char X = 'X', O = 'O';
    public static void main(String[] args)
            throws InterruptedException {
        Scanner consoleInput = new Scanner(System.in);
        char[][] gameBoard = new char[3][3];
        int turn = 1;
        while (true) {
            if (turn % 2 == 1) {
                makeMove(X, gameBoard, consoleInput);
            } else {
                makeMove(O, gameBoard, consoleInput);
            }
            if (testForDraw(gameBoard)) {
                printBoard(gameBoard);
                fancyPrintLn("It's a draw!");
                return;
            } else if (testForWin(X, gameBoard)) {
                printBoard(gameBoard);
                fancyPrintLn(X + ", you win!");
                return;
            } else if (testForWin(O, gameBoard)) {
                printBoard(gameBoard);
                fancyPrintLn(O + ", you win!");
                return;
            }
            turn++;
        }
    }
    private static void makeMove(char player, char[][] gameBoard, Scanner input)
            throws InterruptedException {
        while (true) {
            printBoard(gameBoard);
            fancyPrintLn(player + ", make your move:");
            if (input.hasNextInt()) {
                int position = input.nextInt();
                if (position < 10 && position > 0 && gameBoard[(position - 1) / 3][(position - 1) % 3] == '\0') {
                    gameBoard[(position - 1) / 3][(position - 1) % 3] = player;
                    return;
                }
            }
            input.nextLine();
        }
    }
    private static boolean testForDraw(char[][] gameBoard) {
        for (char[] i : gameBoard) {
            for (char j: i) {
                if ('\0' == j) { return false; }
            }
        }
        return true;
    }
    private static boolean testForWin(char playerToTest, char[][] gameBoard) {
        // Using brute force to test the diagonals since it is only 2 tests
        if (playerToTest == gameBoard[0][0] && playerToTest == gameBoard[1][1] && playerToTest == gameBoard[2][2] ||
                playerToTest == gameBoard[0][2] && playerToTest == gameBoard[1][1] && playerToTest == gameBoard[2][0]) {
            return true;
        }
        // Using a loop to test the horizontals and verticals since it requires 6 tests
        for (int i = 0; i < 3; i++) {
            if (playerToTest == gameBoard[i][0] && playerToTest == gameBoard[i][1] && playerToTest == gameBoard[i][2] ||
                    playerToTest == gameBoard[0][i] && playerToTest == gameBoard[1][i] && playerToTest == gameBoard[2][i]) {
                return true;
            }
        }
        return false;
    }
    private static void printBoard(char[][] gameBoard)
            throws InterruptedException {
        StringBuilder boardToPrint = new StringBuilder();
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                if (gameBoard[i][j] == '\0') {
                    boardToPrint.append(i * 3 + j + 1);
                } else {
                    boardToPrint.append(gameBoard[i][j]);
                }
                if (j < 2) {
                    boardToPrint.append(" | ");
                }
            }
            if (i < 2) {
                boardToPrint.append("\n---------\n");
            }
        }
        fancyPrintLn(boardToPrint.toString());
    }
    private static void fancyPrintLn(String stringToPrint)
            throws InterruptedException {
        for (int i = 0; i < stringToPrint.length(); i++) {
            System.out.print(stringToPrint.charAt(i));
            Thread.sleep(10);
        }
        Thread.sleep(500);
        System.out.println();
    }
}
