/*
Author: Tristen Yim
Project Name: Hangman
Purpose:
    To let the user play hangman
Pseudocode:
    main
        An int for the incorrectGuessCount is declared and initialized to be zero
        A char array for the wordToGuess in declared and randomized
        A char array for guessedLetters is declared to be the length of wordToGuess
        Until incorrectGuessCount reaches 8:
            guessAndCheckCorrectness is called. If it returns false, 1 is added to incorrectGuessCount
            displayProgress is called. If it returns true, the game is over
        A losing message is displayed if incorrectGuessCount reaches this point
    guessAndCheckCharacter takes a console input Scanner, wordToGuess, and guessedLetters char array
        Until the user inputs a valid character that has not been guessed yet, the user keeps getting prompted to input a character.
            This character is added to the back of guessedLetters
            If the character is not in the wordToGuess, the method returns false. Otherwise it returns true
    displayGameState takes the wordToGuess, guessedLetters char array, and incorrectGuessCount as input

Maintenance Log:
    Started (24 Oct 2022 10:20)
    Worked on guessAndCheckCorrectness. Little is finished but a lot is partially completed (24 Oct 2022 10:40)
    Rewrote the entire guess method to have multiple separate check methods. Currently guess can determine if the user lost but not if they won (26 Oct 2022 10:55)
*/

import java.util.Locale;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner consoleInput = new Scanner(System.in);
        int incorrectGuessCount = 0;
        char[] wordToGuess = "HANGMAN".toCharArray();
        char[] guessedLetters = new char[wordToGuess.length];
        while (incorrectGuessCount < 8) {
            if (!guess(consoleInput, wordToGuess, guessedLetters, incorrectGuessCount)) {
                incorrectGuessCount++;
            }
            if (hasWonOrLost(wordToGuess, guessedLetters, incorrectGuessCount)) {
                return;
            }
            displayProgress(guessedLetters, incorrectGuessCount);
        }
        hasWonOrLost(wordToGuess, guessedLetters, incorrectGuessCount);
    }
    protected static boolean guess(Scanner consoleInput, char[] wordToGuess, char[] guessedLetters, Integer incorrectGuessCount) {
        while (true) {
            System.out.print("Please input a word or letter guess: ");
            String inputtedString = consoleInput.next().toUpperCase();
            if (inputtedString.length() < 2 && inputtedString.length() > 0) {
                // Checks if the character is an uppercase letter or a hyphen
                char guessedLetter = inputtedString.charAt(0);
                if (guessedLetter > 64 && guessedLetter < 91 || guessedLetter == '-') {
                    return checkLetter(wordToGuess, guessedLetters, guessedLetter);
                } else {
                    continue;
                }
            } else {
                for (int i = 0; i < inputtedString.length(); i++) {
                    // Checks if each character is not an uppercase letter or a hyphen
                    if (inputtedString == null || inputtedString.charAt(i) < 65 && inputtedString.charAt(i) > 90 || inputtedString.charAt(i) == '-') {
                        continue;
                    }
                }
                if (inputtedString.toCharArray().equals(wordToGuess)) {
                    guessedLetters = wordToGuess;
                    return true;
                }
                return false;
            }
        }
    }
    protected static boolean checkLetter(char[] wordToGuess, char[] guessedLetters, char guessedLetter) {
        boolean charIsInWord = false;
        for (int i = 0; i < wordToGuess.length; i++) {
            if (wordToGuess[i] == guessedLetters[i]) {
                continue;
            } else if (wordToGuess[i] == guessedLetter) {
                guessedLetters[i] = guessedLetter;
                charIsInWord = true;
            }
        }
        return charIsInWord;
    }
    protected static boolean hasWonOrLost(char[] wordToGuess, char[] guessedLetters, int incorrectGuessCount) {
        if (incorrectGuessCount == 8) {
            for (char i : wordToGuess) {
                System.out.print(i);
            }
            System.out.println(" was the word! You lost!");
        } else if (wordToGuess.toString().equals(guessedLetters.toString())) {
            for (char i : wordToGuess) {
                System.out.print(i);
            }
            System.out.println(wordToGuess.toString() + " was the word! You won with " + incorrectGuessCount + " incorrect guesses!");
            return true;
        }
        return false;
    }
    protected static void displayProgress(char[] guessedLetters, Integer incorrectGuessCount) {
        for (char i : guessedLetters) {
            if (i == '\0') {
                System.out.print('_');
            } else {
                System.out.print(i);
            }
        }
        System.out.println("\nIncorrect Guesses: " + incorrectGuessCount);
    }
}
