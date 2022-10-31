/*
Author: Tristen Yim
Project Name: Mastermind (GS05-03)
Purpose:
    To let the user play mastermind
Pseudocode:
Maintenance Log:
    Started the game UI (31 Oct 2022 10:12)
    Started setting up the variables and methods but didn't get to make much actual code (31 Oct 2022 10:40)
*/

import java.util.Random;

public class MastermindGame {
    private static final Random R = new Random();
    private static final int allowedAttempts = 13;
    public static void main (String[] args) {
        int[] secretCode = { R.nextInt(10), R.nextInt(10), R.nextInt(10), R.nextInt(10) };
        int attempt = 1;
        while (true) {
            if (guess()) {

            }
        }
    }
    private static boolean guess() {

    }
}
