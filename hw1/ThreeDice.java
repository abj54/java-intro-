// ThreeDice.java: Writes the sum of three random integers between 1 and 6, such
// as you might get when rolling three dice.

import edu.princeton.cs.algs4.StdOut;


public class ThreeDice {
    public static void main(String[] args) {
        int k = 0;
        for (int i = 1; i <= 3; i++) {
           int rand = (int) ((Math.random() * 6) + 1);
           k += rand;
        }
        StdOut.println(k);
    }
}
