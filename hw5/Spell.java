// Spell.java: Takes a command-line argument specifying the name of the file
// containing common misspellings (a line-oriented file with each
// comma-separated line containing a misspelled word and the correct spelling),
// then reads text from standard input and prints out the misspelled words in
// the text along with the line numbers where they occurred and their correct
// spellings.

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Spell {
    public static void main(String[] args) {
       int k = 0;
       
       SeparateChainingHashST<String, String> st =
       new SeparateChainingHashST<String, String>();
       In in = new In(args[0]);  
       while (in.hasNextLine()) {
            String line = in.readLine();
            String [] tokens = line.split(",");
            String key = tokens [ 0 ];
            String value = tokens [ 1 ];
            st.put(key, value);
       }
       while (StdIn.hasNextLine()) {
           k++;
           String line = StdIn.readLine();
           String [] str = line.split("\\W");
           int b = str.length;
           for (int i = 0; i < str.length; i++) {
              String a = str[i];
              if (st.contains(a)) {
                  StdOut.println(a+":"+k+" -> "+st.get(a));
             
              }
             
           }
        }
}
}
