/*
Author: Tristen Yim
Project Name:
Purpose:
Pseudocode:
Maintenance Log:
    Engine UI is completely broken (14 Nov 2022 10:41)
    Engine UI displays useful information, but is only meant to play one game (16 Nov 2022 10:56)
    Set up the engine so it now will play all games with 6 colors and 10 colors with 3 starting guesses, awaiting results (17 Nov 2022 10:19)
Notes:
    Initial code: 0011, max 5 rounds avg 4.476, 6 colors
    Initial Game: 256 combos, 0233, 44 combos, 2415, 7 combos, 0351, 1 combo, 2521
    Initial code: 0011, max _ rounds avg ____, 10 colors
    Initial Game: 4096 combos, 0213, 432 combos, 2045, 75 combos, 6027, 14 combos, 3484, 2 combos, 2521
*/

public class MastermindEngineUI {
    public static void main(String[] args) {
        String[] initialGuesses = {"0011", "0012", "0123"};
        for (int i = 0; i < initialGuesses.length; i++) {
            testInitialGuess(initialGuesses[i], 6);
            testInitialGuess(initialGuesses[i], 10);
        }
    }
    private static void testInitialGuess(String initialGuess, int colors) {
        MastermindEngine engine = new MastermindEngine(colors);
        playGameWithHeading("2521", initialGuess, engine);
        int gamesPlayed = 0;
        double averageRounds = 0.0;
        int maxRounds = 0;
        for (int i = 0; i < colors; i++) {
            for (int j = 0; j < colors; j++) {
                for (int k = 0; k < colors; k++) {
                    for (int l = 0; l < colors; l++) {
                        int rounds = playHeadlessGame(Integer.toString(i) + j + k + l, initialGuess, engine);
                        if (averageRounds == 0.0) {
                            averageRounds = rounds;
                        } else {
                            averageRounds = (averageRounds * gamesPlayed + rounds)/(gamesPlayed + 1);
                        }
                        maxRounds = Integer.max(maxRounds, rounds);
                        gamesPlayed++;
                    }
                }
            }
        }
        System.out.println("[0_0] Played " + gamesPlayed + " games, with the max rounds being " + maxRounds + " and the average rounds being " + averageRounds);
    }
    private static void playGameWithHeading(String secretCode, String guess, MastermindEngine engine) {
        int[] output;
        int rounds = 0;
        System.out.println("[0_o] Starting with: [" + guess + "]");
        while(true) {
            if (rounds != 0) {
                guess = engine.recommendGuess();
                System.out.println("[o_o] After " + rounds + " rounds, guessing [" + guess + "]");
            }
            output = engine.scoreCodewords(secretCode, guess);
            System.out.println("[o_o] Guess pool size: " + engine.getGuessPoolSize());
            System.out.println("[o_o] Max possibilities left: " + engine.findMaxPossibilities(guess));
            if (output[0] == 4) {
                System.out.println("[0_0] Won with guess [" + guess + "] on round " + (rounds + 1) + "!");
                break;
            }
            engine.eliminateImpossibleCodes(guess, output);
            System.out.println("[o_o] Received " + output[0] + " black pins and " + output[1] + " white pins.");
            rounds++;
        }
        engine.resetCodePool();
    }
    private static int playHeadlessGame(String secretCode, String guess, MastermindEngine engine) {
        int[] output;
        int rounds = 0;
        while(true) {
            if (rounds != 0) {
                guess = engine.recommendGuess();
            }
            output = engine.scoreCodewords(secretCode, guess);
            if (output[0] == 4) {
                break;
            }
            engine.eliminateImpossibleCodes(guess, output);
            rounds++;
        }
        engine.resetCodePool();
        return ++rounds;
    }
}
