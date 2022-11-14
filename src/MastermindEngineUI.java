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
        String startingGuess = "0011";
        String secretCode = "2521";
        int rounds = 1;
        MastermindEngine.MyEngine engine = new MastermindEngine.MyEngine(6, secretCode);
        System.out.println("[o_o] Starting with: " + startingGuess);
        int[] startingOutput = engine.scoreCodewords(secretCode, startingGuess);
        System.out.println("[o_o] After [" + rounds + "] round, received " + startingOutput[0] + "black pins and " + startingOutput[1] + "white pins.");
        while(true) {
            String guess = engine.recommendGuess();
            System.out.println("[o_o] After [" + rounds + "], guessing " + guess);
            int[] output = engine.scoreCodewords(secretCode, guess);
            if (output[0] == 4) {
                System.out.println("[o_o] Won with guess " + guess + " after [" + rounds + "] rounds!");
            }
            System.out.println("[o_o] After [" + rounds + "] round, received " + output[0] + "black pins and " + output[1] + "white pins.");
        }
    }
}
