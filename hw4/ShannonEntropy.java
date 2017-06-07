import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class ShannonEntropy {
    // Returns lg(x), ie, base 2 logarithm of x.
    private static double lg(double x) {
        return Math.log(x) / Math.log(2.0);
    }

    // Returns the Shannon entropy of the items in a[].
    private static double entropy(Comparable[] a) {
        Arrays.sort(a);
        double N = a.length;
        double c = 1.0;
        double k = 0.0;
        double f = 0.0;
        double result; 
        for (int i = 1; i < N; i++) {
               if (a[i].compareTo(a[i-1]) == 0) {
                   c++;
               }
               else {
                   f = c / N;
                   k += f * lg(f);
                   c = 1.0;
               }
             
        } 
        f = c / N; 
        k += f * lg(f);    
        result = -(k / lg(N));
        if (result == 0.0)
            result = -result;
        return result;
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int x = Integer.parseInt(args[1]);
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++) {
            a[i] = i % x;
        }
        StdOut.printf("H = %4.2f\n", entropy(a));
    }
}
