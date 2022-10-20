/*
Author: Tristen Yim
Project Name: Tic-Tac-Toe
Purpose:
    To let the user play a game of tic-tac-toe against another user
Pseudocode:
    Characters X and O are constants and are used to refer to player 1 and player 2. If you want to, you can change the characters to be something else!
    testForDraw takes the board as input
        In a for-each loop the program traverses the board and returns if any value is its default
        Otherwise it executes endCondition with '\0' being the input
    testForWin takes the board as input and a character, either X or O
        testForWin checks the top left corner, then the middle, then the bottom to see if the inputted character is in any of those locations
            If it is in none of them, it returns
            Otherwise, for each of the three characters that match the inputted character, it tests the characters around them for a win and executes endCondition
            If it reaches this point, it returns
Maintenance Log:
    Started (19 Oct 2022 10:19)
    Finished testForWin and testForDraw but main doesn't do much of anything yet (19 Oct 2022 10:55)
    Worked on the method to make a move. Currently the user input doesn't quite work because the book isn't good at explaining things, but it in theory works (20 Oct 2022 10:55)
*/

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static final char X = 'X', O = 'O';
    public static void main(String[] args)
            throws InterruptedException {
        char[][] gameBoard = new char[3][3];
        int turn = 1;
        while (true) {
            if (turn % 2 == 1) {
                makeMove(X, gameBoard);
            } else {
                makeMove(O, gameBoard);
            }
            if (testForDraw(gameBoard)) {
                endCondition('\0');
                return;
            } else if (testForWin(X, gameBoard)) {
                endCondition(X);
                return;
            } else if (testForWin(O, gameBoard)) {
                endCondition(O);
                return;
            }
            turn++;
        }
    }
    private static void makeMove(char player, char[][] gameBoard)
            throws InterruptedException {
        Scanner input = new Scanner(System.in);
        while (true) {
            printBoard(gameBoard);
            fancyPrintLn("Make your move:");
            if (input.hasNextInt()) {
                int position = input.nextInt();
                if (position < 10 && position > 0 && gameBoard[(position - 1) % 3][(position - 1) / 3] != '\0') {
                    input.close();
                    gameBoard[(position - 1) % 3][(position - 1) / 3] = player;
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
    private static void endCondition(char cause) {
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
            Thread.sleep(20);
        }
        Thread.sleep(500);
        System.out.println();
    }
}
