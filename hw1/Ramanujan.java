// Ramanujan.java: Prints the integers <= N (command-line argument) that
// can be expressed as the sum of two distinct cubes.

import edu.princeton.cs.algs4.StdOut;

public class Ramanujan {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        for (int a = 1; a*a*a <= M; a++) {
               for (int b = a+1; b*b*b <= M - a*a*a; b++) {
                   for (int c = a+1; c*c*c <= M; c++) {
                       for (int d = c+1; d*d*d <= M - c*c*c; d++) {
                            if (a*a*a + b*b*b == c*c*c + d*d*d) {
                               StdOut.println(a*a*a+b*b*b+" = " +a+ "^3 + "+b 
  +"^3 = "+c+ "^3 + "+d+"^3");
                            }
                       }
                   }
               }
        } 
    }
}
