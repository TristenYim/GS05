/*
Author: Tristen Yim
Project Name: Mastermind (GS05-03)
Filename: MastermindEngine.java
Purpose:
    To play mastermind in the most optimal way
Pseudocode:
Maintenance Log:
    Started (4 Nov 2022 9:42)
    Didn't get much done, though started working on making the program fill the arraylist with all possible codes (4 Nov 2022 9:56)
    Something about the engine doesn't work... I don't have time to figure out what it is yet. Right now the engine just recommends a random guess from the list of guesses (9 Nov 2022 10:58)
    The engine now recommends the best first case, except it doesn't do it properly because it doesn't recommend the right last code (10 Nov 2022 11:01)
    Renamed variables and methods so they use more descriptive names such as "maxPossibilities" rather than "worstCase" (14 Nov 2022 10:28)
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
                if(possibleCodes.contains(possibleGuesses.get(i))) {
                    isPossibleCode = true;
                } else {
                    isPossibleCode = false;
                }
            } else if (maxPossibilities == lowestMaxPossibilities && isPossibleCode == false) {
                if(possibleCodes.contains(possibleGuesses.get(i))) {
                    lowestMaxPossibilities = maxPossibilities;
                    lowestMaxPossibilitiesIndex = i;
                    isPossibleCode = true;
                }
            } else if (maxPossibilities == possibleCodes.size()) {
                possibleGuesses.remove(i);
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
    public int getGuessPoolSize() {
        return possibleGuesses.size();
    }
}
