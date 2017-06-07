// KthString.java: Takes a command-line argument k and prints 
// the kth string from the end found on standard input, 
// assuming that standard input has k or more strings.

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class KthString {
    public static void main(String[] args) {
        int P = Integer.parseInt(args[0]);
        Queue<String> K = new Queue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals(" ")) {
                K.enqueue(item);
            }
        }
        int j = K.size();
        for (int i = 0; i < j-P; i++) {
            K.dequeue();
        }
        StdOut.println(K.dequeue());
    }
}