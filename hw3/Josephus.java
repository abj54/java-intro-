// Josephus.java: Takes N and M from the command line and prints out the order 
// in which people are eliminated (and thus would show Josephus where to sit in 
// the circle).

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    public static void main(String[] args) {
      int n = Integer.parseInt(args[0]);
      int m = Integer.parseInt(args[1]);
      Queue<Integer> J = new Queue<Integer>();
      for (int i = 0; i < n; i++) {
          J.enqueue(i);
      }
      while (!J.isEmpty()) {
          for (int k = 0; k < m-1; k++) {
              J.enqueue(J.dequeue());
          }
          StdOut.print(J.dequeue()+" "); 
        }
      StdOut.println();
    }
}
