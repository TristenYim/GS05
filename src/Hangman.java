/*
Author: Tristen Yim
Project Name: Hangman
Purpose:
    To let the user play hangman
Pseudocode:
    main
        An int for the incorrectCharacterCount is declared and initialized to be zero
        A char array for the wordToGuess in declared and randomized
        A char array for guessedCharacters is declared to be the length of wordToGuess
        Until incorrectCharacterCount reaches 8:
            guessAndCheckCorrectness is called. If it returns false, 1 is added to incorrectCharacterCount
            checkAndDisplayProgress is called. If it returns true, the game is over
        A losing message is displayed if incorrectCharacterCount reaches this point
    guessAndCheckCorrectness takes a console input Scanner, wordToGuess, and guessedCharacters char array
        Until the user inputs a valid character that has not been guessed yet, the user keeps getting prompted to input a character.
            This character is added to the back of guessedCharacters
            If the character is not in the wordToGuess, the method returns false. Otherwise it returns true
    displayGameState takes the wordToGuess, guessedCharacters char array, and incorrectCharacterCount as input

Maintenance Log:
    Started (24 Oct 2022 10:20)
    Worked on guessAndCheckCorrectness. Little is finished but a lot is partially completed (24 Oct 2022 10:40)
*/

import java.util.Locale;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner consoleInput = new Scanner(System.in);
        int incorrectCharacterCount = 0;
        char[] wordToGuess;
        char[] guessedCharacters = wordToGuess.length;
        while (incorrectCharacterCount < 8) {
            if (!guessAndCheckCorrectness(consoleInput, wordToGuess, guessedCharacters)) { incorrectCharacterCount++; }
            if (checkAndDisplayProgress(wordToGuess, guessedCharacters, incorrectCharacterCount)) { return; }
        }
    }
    // Since I didn't want to make too many methods with only a few lines in them, I combined the guess and check if that guess was correct into one method
    public static boolean guessAndCheckCorrectness(Scanner consoleInput, char[] wordToGuess, char[] guessedCharacters) {
        while (true) {
            char inputtedChar = consoleInput.next().toString().toUpperCase().charAt(0);
            // Checks if the character is an uppercase letter or a '-'
            if (inputtedChar > 64 && inputtedChar < 91 || inputtedChar == '-') {

            }
        }
    }
    // Also since I didn't want too many methods with only a few lines, I combined the display progress and check for win or loss into one method
    public static boolean checkAndDisplayProgress(char[] wordToGuess, char[] guessedCharacters, int incorrectCharacterCount) {

    }
}
