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
import java.util.Random;

public class MastermindEngine extends MastermindTester.TristenYim2 {
    private final String SECRET_CODE;
    MastermindEngine(int colors, String secretCode) {
        super(colors);
        Random R = new Random();
        SECRET_CODE = secretCode;
    }
    protected int[] scoreCodeword(String guess) {
        return scoreCodewords(SECRET_CODE, guess);
    }
    static class MyEngine extends MastermindEngine {
        private ArrayList<String> possibleCodes = new ArrayList<>();
        public MyEngine(int colors, String secretCode) {
            super(colors, secretCode);
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
        public String recommendGuess() {
            if (possibleCodes.size() <= 2) {
                return possibleCodes.get(0);
            }
            int minGuaranteedPossibilities = 9999;
            int minGuaranteedPossibilitiesIndex = 0;
            for (int i = 0; i < possibleCodes.size(); i++) {
                int maxPossibilities = findMaxPossibilities(possibleCodes.get(i));
                if (maxPossibilities < minGuaranteedPossibilities) {
                    minGuaranteedPossibilities = maxPossibilities;
                    minGuaranteedPossibilitiesIndex = i;
                }
            }
            return possibleCodes.get(minGuaranteedPossibilitiesIndex);
        }
        private int findMaxPossibilities(String codeToTest) {
            int worstCase = 0;
            int[] outputToTest = {0, 0};
            while (outputToTest[0] < 4) {
                while (outputToTest[1] < 4) {
                    int possibilitiesOfThisCase = 0;
                    for (int i = 0; i < possibleCodes.size(); i++) {
                        if (!codeToTest.equals(possibleCodes.get(i)) &&
                                Arrays.equals(outputToTest, scoreCodewords(codeToTest, possibleCodes.get(i)))) {
                            possibilitiesOfThisCase++;
                        }
                    }
                    if (possibilitiesOfThisCase > worstCase) {
                        worstCase = possibilitiesOfThisCase;
                    }
                    outputToTest[1]++;
                }
                outputToTest[0]++;
                outputToTest[1] = 0;
            }
            return worstCase;
        }
        public void eliminateImpossibleCodes(String guessedCode, int[] pins) {
            for (int i = 0; i < possibleCodes.size(); i++) {
                if (!Arrays.equals(pins, scoreCodewords(guessedCode, possibleCodes.get(i)))) {
                    possibleCodes.remove(i);
                    i--;
                } else {
                    //System.out.println(possibleCodes.get(i));
                }
            }
        }
    }
}
