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
*/

import java.util.ArrayList;

public class MastermindEngine {
    ArrayList<String> possibleCodes = new ArrayList<String>();
    public void MastermindEngine(int colors) {
        for (int i = 0; i < colors; i++) {
            for (int j = 0; j < colors; j++) {
                for (int k = 0; k < colors; k++) {
                    for (int l = 0; j < colors; l++) {
                        possibleCodes.add(i + j + k + l);
                    }
                }
            }
        }
    }
    public static void eliminatePossibleCodes(int[] pins) {

    }
}
