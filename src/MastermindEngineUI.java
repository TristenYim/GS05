/*
Author: Tristen Yim
Project Name: Mastermind (GS05-03)
Purpose:
    To play multiple games of mastermind using the engine and analyze the results
Pseudocode:
    playGameWithHeading plays takes the initial guess, amount of colors, and engine as input
        Using the given information, the engine plays a game of Mastermind and displays what it guesses each time, what feedback is given to it, and how many possibilities it can narrow down
    playHeadlessGame takes the initial guess, amount of colors, and engine as input
        In a very similar fashion to playGameWithHeading, this method plays a game but without displaying information. Instead, it returns the rounds it took to win
    testInitialGuess takes an initial guess input and color input
        Plays one game with heading with the secret code being 2521
        Plays all games headlessly, keeping track of and eventually displaying the max rounds to win and averaging the amount of rounds it takes to win every game
        The colors in the game is determined based on the color input
    main
        main currently tests both 6 color and 10 color games with the starting guesses 0011, 0012, and 0123
Maintenance Log:
    Engine UI is completely broken (14 Nov 2022 10:41)
    Engine UI displays useful information, but is only meant to play one game (16 Nov 2022 10:56)
    Set up the engine so it now will play all games with 6 colors and 10 colors with 3 starting guesses, awaiting results (17 Nov 2022 10:19)
        Removed guess pool size call it no longer displays useful info (17 Nov 2022 10:26)
        Updated the heading (17 Nov 2022 10:36)
    Ran the engine and added results (24 Nov 2022 22:24)
Results:
Console Output from playing a few thousand games
C:\Users\tyim7\.jdks\openjdk-19.0.1\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.3\lib\idea_rt.jar=50736:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2022.2.3\bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath C:\Users\tyim7\IdeaProjects\GS05\out\production\GS05 MastermindEngineUI
    [0_o] Starting with: [0011]
    [o_o] Max possibilities left: 256
    [o_o] Received 1 black pins and 0 white pins.
    [o_o] After 1 rounds, guessing [0233]
    [o_o] Max possibilities left: 44
    [o_o] Received 0 black pins and 1 white pins.
    [o_o] After 2 rounds, guessing [2415]
    [o_o] Max possibilities left: 7
    [o_o] Received 1 black pins and 2 white pins.
    [o_o] After 3 rounds, guessing [0351]
    [o_o] Max possibilities left: 1
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 4 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 5!
    [0_0] Played 1296 games, with the max rounds being 5 and the average rounds being 4.4760802469135825
    Time: 97993939871500
    [0_o] Starting with: [0011]
    [o_o] Max possibilities left: 4096
    [o_o] Received 1 black pins and 0 white pins.
    [o_o] After 1 rounds, guessing [0213]
    [o_o] Max possibilities left: 432
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 2 rounds, guessing [2045]
    [o_o] Max possibilities left: 75
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 3 rounds, guessing [6027]
    [o_o] Max possibilities left: 14
    [o_o] Received 1 black pins and 0 white pins.
    [o_o] After 4 rounds, guessing [3484]
    [o_o] Max possibilities left: 2
    [o_o] Received 0 black pins and 0 white pins.
    [o_o] After 5 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 6!
    [0_0] Played 10000 games, with the max rounds being 7 and the average rounds being 5.991499999999997
    Time: 246589192980100
    [0_o] Starting with: [0012]
    [o_o] Max possibilities left: 276
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 1 rounds, guessing [1103]
    [o_o] Max possibilities left: 39
    [o_o] Received 0 black pins and 1 white pins.
    [o_o] After 2 rounds, guessing [2450]
    [o_o] Max possibilities left: 7
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 3 rounds, guessing [2241]
    [o_o] Max possibilities left: 1
    [o_o] Received 2 black pins and 1 white pins.
    [o_o] After 4 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 5!
    [0_0] Played 1296 games, with the max rounds being 6 and the average rounds being 4.4776234567901225
    Time: 246623970198200
    [0_o] Starting with: [0012]
    [o_o] Max possibilities left: 3052
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 1 rounds, guessing [1223]
    [o_o] Max possibilities left: 199
    [o_o] Received 1 black pins and 2 white pins.
    [o_o] After 2 rounds, guessing [3231]
    [o_o] Max possibilities left: 12
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 3 rounds, guessing [4156]
    [o_o] Max possibilities left: 3
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 4 rounds, guessing [0405]
    [o_o] Max possibilities left: 1
    [o_o] Received 0 black pins and 1 white pins.
    [o_o] After 5 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 6!
    [0_0] Played 10000 games, with the max rounds being 8 and the average rounds being 5.931600000000008
    Time: 272203966703400
    [0_o] Starting with: [0123]
    [o_o] Max possibilities left: 312
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 1 rounds, guessing [0024]
    [o_o] Max possibilities left: 47
    [o_o] Received 1 black pins and 0 white pins.
    [o_o] After 2 rounds, guessing [0255]
    [o_o] Max possibilities left: 6
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 3 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 4!
    [0_0] Played 1296 games, with the max rounds being 6 and the average rounds being 4.47762345679012
    Time: 272241283088999
    [0_o] Starting with: [0123]
    [o_o] Max possibilities left: 3048
    [o_o] Received 1 black pins and 1 white pins.
    [o_o] After 1 rounds, guessing [0145]
    [o_o] Max possibilities left: 286
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 2 rounds, guessing [4621]
    [o_o] Max possibilities left: 26
    [o_o] Received 2 black pins and 0 white pins.
    [o_o] After 3 rounds, guessing [5017]
    [o_o] Max possibilities left: 5
    [o_o] Received 0 black pins and 2 white pins.
    [o_o] After 4 rounds, guessing [0181]
    [o_o] Max possibilities left: 2
    [o_o] Received 1 black pins and 0 white pins.
    [o_o] After 5 rounds, guessing [2521]
    [o_o] Max possibilities left: 1
    [0_0] Won with guess [2521] on round 6!
    [0_0] Played 10000 games, with the max rounds being 7 and the average rounds being 5.810299999999996
    Time: 603621732967600

    Process finished with exit code 0
*/

public class MastermindEngineUI {
    public static void main(String[] args) {
        String[] initialGuesses = {"0011", "0012", "0123"};
        for (int i = 0; i < initialGuesses.length; i++) {
            testInitialGuess(initialGuesses[i], 6);
            System.out.println("Time: " + System.nanoTime());
            testInitialGuess(initialGuesses[i], 10);
            System.out.println("Time: " + System.nanoTime());
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
