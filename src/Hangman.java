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
            displayProgress is called
            guess is called. If it returns false, 1 is added to incorrectGuessCount
            hasWonOrLost is called. If it returns true, the program ends
    guess takes a console input Scanner, wordToGuess, and guessedLetters char array
        Until the user inputs a valid character that has not been guessed yet or a word, the user keeps getting prompted to input
            If it is a character, the method returns the same return value as a call of checkLetter
            If it is a word, a check is made to see if the guessed word is the same as wordToGuess
                If it is, guessedLetters is set to wordToGuess and true is returned
                Otherwise false is returned
    checkLetter takes the wordToGuess, guessedLetters char arrays , and guessedLetter char as input
        wordToGuess is traversed. If guessedLetter is equal to any of the characters in wordToGuess, that character is copied to guessedLetters and true is returned
        Otherwise false is returned
    hasWonOrLost takes the wordToGuess, guessedLetters char arrays, and incorrectGuessCount as input
        If the wordToGuess is the same as guessedLetters, the user is told they won
        If incorrectGuessCount has reached 8, the user is told they lost
        If they either won or lost, true is returned. Otherwise false is returned
    displayProgress takes the guessedLetters char array and incorrectGuessCount as input
        guessedLetters is printed but with each '\0' being replaced with an underscore to indicate an unknown character
        The number of incorrect guesses is displayed
Maintenance Log:
    Started (24 Oct 2022 10:20)
    Worked on guessAndCheckCorrectness. Little is finished but a lot is partially completed (24 Oct 2022 10:40)
    Rewrote the entire guess method to have multiple separate check methods. Currently guess can determine if the user lost but not if they won (26 Oct 2022 10:55)
    It now generates a random word, though there are only 9 words and it chooses a random word from an array, not from a list of hundreds of words on a separate file (27 Oct 2022 10:45)
*/

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner consoleInput = new Scanner(System.in);
        Random R = new Random();
        String[] words = {"HANGMAN", "BOULEVARD", "CONTROL", "CORVETTE", "FANTASY", "BROKEN", "LONDON", "COMMITMENT", "GOODBYE"};
        char[] wordToGuess = words[R.nextInt(words.length)].toCharArray();
        char[] guessedLetters = new char[wordToGuess.length];
        int incorrectGuessCount = 0;
        while (true) {
            displayProgress(guessedLetters, incorrectGuessCount);
            if (!guess(consoleInput, wordToGuess, guessedLetters)) {
                incorrectGuessCount++;
            }
            if (hasWonOrLost(wordToGuess, guessedLetters, incorrectGuessCount)) {
                return;
            }
        }
    }
    protected static boolean guess(Scanner consoleInput, char[] wordToGuess, char[] guessedLetters) {
        while (true) {
            System.out.print("Please input a word or letter guess: ");
            String inputtedString = consoleInput.next().toUpperCase();
            if (inputtedString.length() == 1) {
                // Checks if the character is an uppercase letter or a hyphen
                char guessedLetter = inputtedString.charAt(0);
                if (guessedLetter > 64 && guessedLetter < 91 || guessedLetter == '-') {
                    for (char i : guessedLetters) {
                        if (i == guessedLetter) {
                            continue;
                        }
                    }
                    return checkLetter(wordToGuess, guessedLetters, guessedLetter);
                }
            } else {
                for (int i = 0; i < inputtedString.length(); i++) {
                    // Checks if each character is not an uppercase letter or a hyphen
                    if (inputtedString == null || inputtedString.charAt(i) < 65 && inputtedString.charAt(i) > 90 && inputtedString.charAt(i) != '-') {
                        continue;
                    }
                }
                if (Arrays.equals(inputtedString.toCharArray(), wordToGuess)) {
                    for (int i = 0; i < guessedLetters.length; i++) {
                        guessedLetters[i] = wordToGuess[i];
                    }
                    return true;
                }
                return false;
            }
        }
    }
    protected static boolean checkLetter(char[] wordToGuess, char[] guessedLetters, char guessedLetter) {
        boolean charIsInWord = false;
        for (int i = 0; i < wordToGuess.length; i++) {
            if (wordToGuess[i] == guessedLetter && wordToGuess[i] != guessedLetters[i]) {
                guessedLetters[i] = guessedLetter;
                charIsInWord = true;
            }
        }
        return charIsInWord;
    }
    protected static boolean hasWonOrLost(char[] wordToGuess, char[] guessedLetters, int incorrectGuessCount) {
        if (incorrectGuessCount == 8) {
            System.out.println();
            for (char i : wordToGuess) {
                System.out.print(i);
            }
            System.out.println(" was the word! You lost!");
            return true;
        } else if (Arrays.equals(wordToGuess, guessedLetters)) {
            System.out.println();
            for (char i : wordToGuess) {
                System.out.print(i);
            }
            System.out.println(" was the word! You won with " + incorrectGuessCount + " incorrect guesses!");
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
