/*
Author: Tristen Yim
Project Name: Mastermind (GS05-03)
Filename: MastermindEngine.java
Purpose:
    To play mastermind in the most optimal way
Pseudocode:
    MastermindEngine stores an ArrayList of the possible codes and possible guesses
        possibleCodes contains all codes that have not been eliminated: possibleGuesses contains all codes
    The constructor takes a color input and sets up possibleCodes, possibleGuesses, and the scoring algorithm based on the number of colors
    eliminateImpossibleCodes takes a guess and the result of playing with that guess as input
        It tests all codes in the list of possible codes. Any codes that don't get the same output when scored against the guess as the inputted result are removed from the list
    recommendGuess takes no input and returns a String, the guess it recommends
        If there are 2 or less codes left, it recommends the first one in the list
        Otherwise, it tests all possible guesses
        In the loop that tests all possible guesses:
            It calls findMaxPossibilities with the guess its testing to determine how good of a guess it is (the lower the value returned the better)
            If this code returns the lowest value its found, it is recorded
            It also records if this code is a possible guess or not
            If the tested code returns a value equal to the lowest value found, it keeps whichever one is a possible guess. Otherwise it does nothing
        It returns the guess used to get the recorded possibilities
    findMaxPossibilities takes a code to test as input
        It creates an array which keeps track of the number of possibilities remaining for each output
        Then it tests all possible codes against the inputted code and adds one to whichever output it got in the array
        Then, every element of the array is compared at the end. The output with the most possible codes (This is an engine - we're looking for the best worst case) is returned.
Maintenance Log:
    Started (4 Nov 2022 9:42)
    Didn't get much done, though started working on making the program fill the arraylist with all possible codes (4 Nov 2022 9:56)
    Something about the engine doesn't work... I don't have time to figure out what it is yet. Right now the engine just recommends a random guess from the list of guesses (9 Nov 2022 10:58)
    The engine now recommends the best first case, except it doesn't do it properly because it doesn't recommend the right last code (10 Nov 2022 11:01)
    Renamed variables and methods so they use more descriptive names such as "maxPossibilities" rather than "worstCase" (14 Nov 2022 10:28)
    Modified the engine so it not only checks all possible codes but all possible guesses too
        Currently I have it eliminate guesses that give no information, but this seems to only eliminate about 16 guesses (16 Nov 2022 10:56)
    Removed the code that removes guesses and added a method that resets the possibleGuesses list (17 Nov 2022 10:19)
        Removed getGuessPoolSize since it's no longer used (17 Nov 2022 10:26)
        Updated the heading (17 Nov 2022 10:50)
*/

import java.util.ArrayList;
import java.util.Arrays;

public class MastermindEngine extends MastermindTester.TristenYim2 {
    private ArrayList<String> possibleCodes = new ArrayList<>();
    private ArrayList<String> possibleGuesses = new ArrayList<>();
    public MastermindEngine(int colors) {
        super(colors);
        for (int i = 0; i < COLORS_IN_GAME; i++) {
            for (int j = 0; j < COLORS_IN_GAME; j++) {
                for (int k = 0; k < COLORS_IN_GAME; k++) {
                    for (int l = 0; l < COLORS_IN_GAME; l++) {
                        String codeToAdd = Integer.toString(i) + j + k + l;
                        possibleCodes.add(codeToAdd);
                        possibleGuesses.add(codeToAdd);
                    }
                }
            }
        }
    }
    public String recommendGuess() {
        if (possibleCodes.size() <= 2) {
            return possibleCodes.get(0);
        }
        int lowestMaxPossibilities = 9999;
        int lowestMaxPossibilitiesIndex = 0;
        boolean isPossibleCode = false;
        for (int i = 0; i < possibleGuesses.size(); i++) {
            int maxPossibilities = findMaxPossibilities(possibleGuesses.get(i));
            if (maxPossibilities < lowestMaxPossibilities) {
                lowestMaxPossibilities = maxPossibilities;
                lowestMaxPossibilitiesIndex = i;
                isPossibleCode = possibleCodes.contains(possibleGuesses.get(i));
            } else if (maxPossibilities == lowestMaxPossibilities && !isPossibleCode) {
                if(possibleCodes.contains(possibleGuesses.get(i))) {
                    lowestMaxPossibilitiesIndex = i;
                    isPossibleCode = true;
                }
            }
        }
        return possibleGuesses.get(lowestMaxPossibilitiesIndex);
    }
    public int findMaxPossibilities(String codeToTest) {
        int maxPossibilities = 0;
        int[][] outputCount = new int[5][5];
        for (int i = 0; i < possibleCodes.size(); i++) {
            int[] output = scoreCodewords(codeToTest, possibleCodes.get(i));
            outputCount[output[0]][output[1]]++;
        }
        for (int i = 0; i < outputCount.length; i++) {
            for (int j = 0; j < outputCount.length; j++) {
                maxPossibilities = Integer.max(maxPossibilities, outputCount[i][j]);
            }
        }
        return maxPossibilities;
    }
    public void eliminateImpossibleCodes(String guessedCode, int[] pins) {
        for (int i = 0; i < possibleCodes.size(); i++) {
            if (!Arrays.equals(pins, scoreCodewords(guessedCode, possibleCodes.get(i)))) {
                possibleCodes.remove(i);
                i--;
            }
        }
    }
    public void resetCodePool() {
        possibleCodes.clear();
        for (int i = 0; i < COLORS_IN_GAME; i++) {
            for (int j = 0; j < COLORS_IN_GAME; j++) {
                for (int k = 0; k < COLORS_IN_GAME; k++) {
                    for (int l = 0; l < COLORS_IN_GAME; l++) {
                        possibleCodes.add(Integer.toString(i) + j + k + l);
                    }
                }
            }
        }
    }
}
