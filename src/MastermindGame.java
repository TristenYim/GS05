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

public class MastermindGame extends MastermindEngine.MyEngine {
    private final Random R = new Random();
    private final int ALLOWED_ATTEMPTS = 13;
    // Rather than making a new interface for the engine, I simply added a cheat mode to the original one since the original interface is okay
    private final boolean CHEAT_MODE = true;
    public MastermindGame(int colors) {
        super(colors);
    }
    public void main (String[] args) {
        new MastermindGame(10);
        Scanner consoleInput = new Scanner(System.in);
        String secretCode = String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10)) +
                String.valueOf(R.nextInt(10)) + String.valueOf(R.nextInt(10));
        int attempt = 0;
        while (attempt < ALLOWED_ATTEMPTS) {
            System.out.println("You have " + (ALLOWED_ATTEMPTS - attempt) + " attempts left!");
            if (guess(consoleInput, secretCode, attempt)) {
                System.out.println("You win!");
                return;
            }
            attempt++;
        }
        System.out.println("You lose! The code was " + secretCode);
    }
    private boolean guess(Scanner consoleInput, String secretCode, int attempt) {
        while (true) {
            System.out.print("Please input your guess: ");
            if (CHEAT_MODE) {
                if (attempt == 0) {
                    System.out.println("[o_o]: Guess two sets of two numbers, such as 1122");
                } else {
                    System.out.println("[o_o]: Try " + recommendGuess());
                }
            }
            String guessedCode = consoleInput.next();
            String convertedCode = toCode(guessedCode);
            if (convertedCode.equals("")) {
                continue;
            }
            scoreCodewords(secretCode, convertedCode);
            System.out.println(Arrays.toString(score));
            if (CHEAT_MODE) {
                if (attempt == 0) {
                    firstGuess(convertedCode);
                } else {
                    eliminateImpossibleCodes();
                }
            }
            if (score[0] == 4) {
                return true;
            }
            return false;
        }
    }
    private String toCode(String stringToTest) {
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
