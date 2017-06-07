import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class CertifyHeap {
    // Return true of v is less than w and false otherwise.
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Return true if a[] represents a maximum-ordered heap and false 
    // otherwise. Remember to index from 1.
    private static boolean maxOrderedHeap(Comparable[] a) {
        int N = a.length;
        int b = 1;
        for (int i = 1; 2 * i <= N; i++) {
            int l = 2*i;
            int r = 2*i + 1; 
             if ((l < N && less(a[i], a[l])) || (r < N && less(a[i], a[r]))) {
                 return false;
             }
              
             
        }
            return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        StdOut.println(maxOrderedHeap(a));
    }
}
