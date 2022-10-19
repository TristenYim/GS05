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
*/

public class TicTacToe {
    private static final char X = 'X', Y = 'Y';
    public static void main(String[] args) {
        char[][] gameBoard = new char[3][3];

        //...

    }
    private static void testForDraw(char[][] gameBoard) {
        for (char[] i : gameBoard) {
            for (char j: i) {
                if ('\0' == j) { return; }
            }
        }
        endCondition('\0');
    }
    private static void testForWin(char playerToTest, char[][] gameBoard) {
        // Using brute force to test the diagonals since it is only 2 tests
        if (playerToTest == gameBoard[0][0] && playerToTest == gameBoard[1][1] && playerToTest == gameBoard[2][2] ||
                playerToTest == gameBoard[0][2] && playerToTest == gameBoard[1][1] && playerToTest == gameBoard[2][0]) {
            endCondition(playerToTest);
        }
        // Using a loop to test the horizontals and verticals since it requires 6 tests
        for (int i = 0; i < 3; i++) {
            if (playerToTest == gameBoard[i][0] && playerToTest == gameBoard[i][1] && playerToTest == gameBoard[i][2]) {
                endCondition(playerToTest);
            } else if (playerToTest == gameBoard[0][i] && playerToTest == gameBoard[1][i] && playerToTest == gameBoard[2][i]) {
                endCondition(playerToTest);
            }
        }
        return;
    }
    private static void endCondition(char cause) {
    }
}
