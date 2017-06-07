// Transpose.java: reads a square matrix of doubles from standard input, and
// the computes and writes its transpose.

import edu.princeton.cs.algs4.StdArrayIO;

public class Transpose {
    // Transposes the square matrix x in place.
    private static void transpose(double[][] x) {
        int m = x.length;
        for (int i = 0; i < m; i++) {
           for (int j = i+1; j < m; j++) {
             double k = x[i][j];
             x[i][j] = x[j][i];
             x[j][i] = k;  
           } 
        } 
    }

    // Entry point. [DO NOT EDIT]
    public static void main(String[] args) {
        double[][] x = StdArrayIO.readDouble2D();
        transpose(x);
        StdArrayIO.print(x);
    }
}
