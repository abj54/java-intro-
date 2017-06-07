import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Parentheses {
    // Return true if s has matching parentheses, and false otherwise.
    private static boolean match(String s) {
        
       Stack<String> p = new Stack<String>();
       for (int i = 0; i < s.length(); i++) {
          if (s.charAt(i) == '[') {
              p.push("[");
          }
          else if (s.charAt(i) == '{') {
              p.push("{");
          }
          else if (s.charAt(i) == '(') {
              p.push("(");
          }
          
          else if (s.charAt(i) == ')') {
             if (p.isEmpty()) {
                 return false;
             }
             if (!p.pop().equals("(")) {
                 return false;
             }
          }
          else if (s.charAt(i) == '}') {
             if (p.isEmpty()) {
                 return false;
             }
             if (!p.pop().equals("{")) {
                 return false;
             }
          }
          else if (s.charAt(i) == ']') {
             
             if (p.isEmpty()) {
                 return false;
             }
             
             if (!p.pop().equals("[")) {
                 return false;
             }
            }
       }
       return p.isEmpty();
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        StdOut.println(match(StdIn.readAll().trim()));
    }
}
