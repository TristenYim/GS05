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
    Added a cheat mode to this - Cheat mode uses the the MasterMindEngine to recommend guesses to the player (9 Nov 2022 10:57)
    Added methods to sync the secret code here with the one in the engine - To make randomization work (10 Nov 2022 11:02)
    Changed the UI so it says what pin each number is rather than assuming you know, and made the engine look confused if you ignore it's recommendation (10 Nov 2022 12:16)
*/

import java.util.Arrays;
import java.util.Scanner;

public class MastermindGame {
    private static final int ALLOWED_ATTEMPTS = 13;
    // Rather than making a new interface for the engine, I simply added a cheat mode to the original one since the original interface is okay
    private static final boolean CHEAT_MODE = true;
    public static void main (String[] args) {
        //final Random R = new Random();
        //String secretCode = Integer.toString(R.nextInt(10)) + R.nextInt(10) + R.nextInt(10) + R.nextInt(10);
        String secretCode = "2521";
        MastermindEngine engine = new MastermindEngine(6);
        Scanner consoleInput = new Scanner(System.in);
        int attempt = 0;
        while (attempt < ALLOWED_ATTEMPTS) {
            System.out.println("You have " + (ALLOWED_ATTEMPTS - attempt) + " attempts left!");
            if (guess(consoleInput, secretCode, attempt, engine)) {
                System.out.println("You win!");
                return;
            }
            attempt++;
        }
        System.out.println("You lose! The code was " + secretCode);
    }
    private static boolean guess(Scanner consoleInput, String secretCode, int attempt, MastermindEngine engine) {
        while (true) {
            System.out.print("Please input your guess: ");
            if (CHEAT_MODE) {
                if (attempt == 0) {
                    System.out.println("\n[o_o]: Guess two sets of two numbers, such as 1122");
                } else {
                    System.out.println("\n[o_o]: Try " + engine.recommendGuess());
                }
            }
            String guessedCode = toCode(consoleInput.next());
            if (guessedCode.equals("")) {
                continue;
            }
            int[] score = engine.scoreCodewords(secretCode, guessedCode);
            if (CHEAT_MODE) {
                engine.eliminateImpossibleCodes(guessedCode, score);
            }
            System.out.println("Black Pins: " + score[0] + "\nWhite Pins: " + score[1]);
            if (score[0] == 4) {
                return true;
            }
            return false;
        }
    }
    private static String toCode(String stringToTest) {
        // The empty string is used as a value to mark a guess that doesn't contain four numbers
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
