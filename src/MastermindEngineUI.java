/*
Author: Tristen Yim
Project Name:
Purpose:
Pseudocode:
Maintenance Log:
    Engine UI is completely broken (14 Nov 2022 10:41)
*/

public class MastermindEngineUI {
    public static void main(String[] args) {
        String secretCode = "2521";
        String guess = "0011";
        int[] output;
        int rounds = 0;
        MastermindEngine engine = new MastermindEngine(6);
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
    }
}
