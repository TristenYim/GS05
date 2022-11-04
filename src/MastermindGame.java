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
    Finished (4 Nov 2022 9:56)
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MastermindGame {
    private static final Random R = new Random();
    private static final int ALLOWED_ATTEMPTS = 13;
    public static void main (String[] args) {
        Scanner consoleInput = new Scanner(System.in);
        String secretCode = String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10)) +
                String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10));
        int attempt = 0;
        while (attempt < ALLOWED_ATTEMPTS) {
            System.out.println("You have " + (ALLOWED_ATTEMPTS - attempt) + " attempts left!");
            if (guess(consoleInput, secretCode)) {
                System.out.println("You win!");
                return;
            }
            attempt++;
        }
        System.out.println("You lose! The code was " + secretCode);
    }
    private static boolean guess(Scanner consoleInput, String secretCode) {
        while (true) {
            System.out.print("Please input your guess: ");
            String guessedCode = consoleInput.next();
            String convertedCode = toCode(guessedCode);
            if (convertedCode.equals("")) {
                continue;
            }
            MastermindTester.StudentAlgorithm scoreAlgo = new MastermindTester.TristenYim2(10);
            int[] score = scoreAlgo.scoreCodewords(secretCode, convertedCode);
            System.out.println(Arrays.toString(score));
            if (score[0] == 4) {
                return true;
            }
            return false;
        }
    }
    private static String toCode(String stringToTest) {
        if (stringToTest.length() < 4) {
            return "";
        }
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < stringToTest.length() && i < 4; i++) {
            if (stringToTest.charAt(i) > 47 && stringToTest.charAt(i) < 58) {
                digits.append(stringToTest.charAt(i));
            }
        }
        if (digits.length() < 4) {
            return "";
        }
        return digits.toString();
    }
}
