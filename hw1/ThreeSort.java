// ThreeSort.java: Takes three integers as command-line arguments and writes
// them in ascending order, separated by spaces.

import edu.princeton.cs.algs4.StdOut;

public class ThreeSort {
    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        
        int min1 = Math.min(a, b);
        int min2 = Math.min(b, c);
        int max1 = Math.max(a, b); 
        int max2 = Math.max(b, c);
        int max3 = Math.max(a, c);

        int mini = Math.min(min1, min2);
        int maxi = Math.max(max1, max2);
        int med = Math.min(Math.min(max1, max2), max3);
        
        StdOut.println(mini +" " + med + " " + maxi);
        
    }
}
