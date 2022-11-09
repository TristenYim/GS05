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
*/

import java.util.ArrayList;
import java.util.Random;

public class MastermindEngine extends MastermindTester.TristenYim2 {
    private final String SECRET_CODE;
    MastermindEngine(int colors) {
        super(colors);
        Random R = new Random();
        String secretCode = "";
        for (int i = 0; i < 4; i++) {
            secretCode += Integer.toString(R.nextInt(COLORS_IN_GAME));
        }
        SECRET_CODE = secretCode;
    }
    protected String getSecretCode() {
        return SECRET_CODE;
    }
    protected int[] scoreCodeword(String guess) {
        return scoreCodewords(SECRET_CODE, guess);
    }
    static class MyEngine extends MastermindEngine {
        private ArrayList<String> possibleCodes = new ArrayList<String>();
        public MyEngine(int colors) {
            super(colors);
        }
        public String recommendGuess() {
            Random R = new Random();
            return possibleCodes.get(R.nextInt(possibleCodes.size()));
        }
        public void firstGuess(String firstGuess) {
            int[] firstGuessOutput = scoreCodeword(firstGuess);
            for (int i = 0; i < COLORS_IN_GAME; i++) {
                for (int j = 0; j < COLORS_IN_GAME; j++) {
                    for (int k = 0; k < COLORS_IN_GAME; k++) {
                        for (int l = 0; l < COLORS_IN_GAME; l++) {
                            String codeToTest = Integer.toString(i) + Integer.toString(j) + Integer.toString(k) + Integer.toString(k);
                            if(firstGuessOutput == scoreCodewords(firstGuess, codeToTest)) {
                                possibleCodes.add(codeToTest);
                            }
                        }
                    }
                }
            }
        }
        public void eliminateImpossibleCodes(String guessedCode, int[] pins) {
            for (int i = 0; i < possibleCodes.size(); i++) {
                if (pins != scoreCodewords(guessedCode, possibleCodes.get(i))) {
                    possibleCodes.remove(i);
                    i--;
                }
            }
        }
    }
}
