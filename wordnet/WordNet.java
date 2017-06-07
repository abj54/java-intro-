import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdOut;

// An immutable WordNet data type.
public class WordNet {
    private RedBlackBST<Integer, String> bst; 
    private RedBlackBST<String, SET<Integer>> st;
    private ShortestCommonAncestor sca;

    // Construct a WordNet object given the names of the input (synset and
    // hypernym) files.
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new NullPointerException();
        }
        this.bst = new RedBlackBST<Integer, String>();
        this.st = new RedBlackBST<String, SET<Integer>>();
        In in1 = new In(synsets);
        
        int id = 0;
        while (!in1.isEmpty()) {
            String line = in1.readLine();
            String [] tokens = line.split(",");
            id = Integer.parseInt(tokens[0]);
            bst.put(id, tokens[1]);
            for (String noun: tokens[1].split(" ")) {
                SET<Integer> a = st.get(noun);
                if (a == null) {
                    a = new SET<Integer>();
                }
                a.add(Integer.parseInt(tokens[0]));
                st.put(noun, a);
            }
       }
       int V = bst.size();
       Digraph G = new Digraph(V);
       In in2 = new In(hypernyms);
       while (!in2.isEmpty()) {
           String [] token = in2.readLine().split(",");
           int i = Integer.parseInt(token[0]);
           for (int j = 1; j < token.length; j++) {
               int m = Integer.parseInt(token[j]);
               G.addEdge(i, m);
           }
       }
       this.sca = new ShortestCommonAncestor(G);
       
    }

    // All WordNet nouns.
    public Iterable<String> nouns() {
        return st.keys();
    }

    // Is the word a WordNet noun?
    public boolean isNoun(String word) {
         if (word == null) {
            throw new NullPointerException();
        }
        return st.contains(word);
    }

    // A synset that is a shortest common ancestor of noun1 and noun2.
    public String sca(String noun1, String noun2) {
       if (noun1 == null || noun2 == null) {
            throw new NullPointerException();
        }
        if (!isNoun(noun1) && !isNoun(noun2)) {
            throw new IllegalArgumentException();
        }
       int an = sca.ancestor(st.get(noun1), st.get(noun2));
       return bst.get(an);
    }

    // Distance between noun1 and noun2.
    public int distance(String noun1, String noun2) {
        if (noun1 == null || noun2 == null) {
            throw new NullPointerException();
        }
        if (!isNoun(noun1) && !isNoun(noun2)) {
            throw new IllegalArgumentException();
        }
        return sca.length(st.get(noun1), st.get(noun2));
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
        String word1 = args[2];
        String word2 = args[3];        
        int nouns = 0;
        for (String noun : wordnet.nouns()) {
            nouns++;
        }
        StdOut.println("# of nouns = " + nouns);
        StdOut.println("isNoun(" + word1 + ") = " + wordnet.isNoun(word1));
        StdOut.println("isNoun(" + word2 + ") = " + wordnet.isNoun(word2));
        StdOut.println("isNoun(" + (word1 + " " + word2) + ") = "
                       + wordnet.isNoun(word1 + " " + word2));
        StdOut.println("sca(" + word1 + ", " + word2 + ") = "
                       + wordnet.sca(word1, word2));
        StdOut.println("distance(" + word1 + ", " + word2 + ") = "
                       + wordnet.distance(word1, word2));
    }
}
