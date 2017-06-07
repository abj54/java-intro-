import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class IndirectSort {
    // Is v < w?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    // Exchange a[i] and a[j] (for indirect sort)
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Indirectly sort a[] using insertion sort, ie, not by rearranging a[], 
    // but by returning an array perm[] such that perm[i] is the index of 
    // the ith smallest entry in a[].
    public static int[] indexSort(Comparable[] a) {
        int b = a.length;
        int [] perm = new int [b];
        for (int i = 0; i < a.length; i++) {
             perm[i] = i;
        }
        for (int m = 1; m < b; m++) {
            for (int n = m; n > 0 && less(a[perm[n]], 
                a[perm[n-1]]); n--) {
                    exch(perm, n-1, n);
            }
        }
        return perm;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int[] perm = indexSort(a);
        int i;
        for (i = 0; i < perm.length - 1; i++) {
            StdOut.print(a[perm[i]] + " ");
        }
        StdOut.println(a[perm[i]]);
    }
}
