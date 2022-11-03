/*
Author: Tristen Yim
Project Name: Mastermind (GS05-03)
Purpose:
    To let the user play mastermind
Pseudocode:
Maintenance Log:
    Started the game UI (31 Oct 2022 10:12)
    Started setting up the variables and methods but didn't get to make much actual code (31 Oct 2022 10:40)
    Now takes input but doesn't do error checking quite right yet (3 Nov 2022 10:56)
*/

import java.util.Random;
import java.util.Scanner;

public class MastermindGame {
    private static final Random R = new Random();
    private static final int allowedAttempts = 13;
    public static void main (String[] args) {
        Scanner consoleInput = new Scanner(System.in);
        String secretCode = String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10)) +
                String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10));
        int attempt = 1;
        while (true) {
            if (guess(consoleInput, secretCode)) {

            }
        }
    }
    private static boolean guess(Scanner consoleInput, String secretCode) {
        while (true) {
            System.out.print("Please input your guess: ");
            String guessedCode = consoleInput.next();
            if (guessedCode.length() < 4 ) {
                continue;
            }
            for (int i = 0; i < 3; i++) {
                if (guessedCode.charAt(i) < 48 || guessedCode.charAt(i) > 57) {
                    continue;
                }
            }
            System.out.println(guessedCode);
            MastermindTester.StudentAlgorithm score = new MastermindTester.TristenYim2(10);
            score.scoreCodewords(secretCode, guessedCode.substring(0, 4));
        }
    }
}
