import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// An immutable data type for computing shortest common ancestors.
public class ShortestCommonAncestor {
    private Digraph G;
    
    // Construct a ShortestCommonAncestor object given a rooted DAG.
    public ShortestCommonAncestor(Digraph G) {
        if (G == null) {
            throw new NullPointerException();
        }
        this.G = new Digraph(G);
    }

    // Length of the shortest ancestral path between v and w.
    public int length(int v, int w) {
        if (!((v >= 0 && v <= G.V() - 1) && (w >= 0 && w <= G.V() - 1))) {
            throw new IndexOutOfBoundsException();
        }
        int ca = ancestor(v, w);
        
        SeparateChainingHashST<Integer, Integer> vst = distFrom(v);
        SeparateChainingHashST<Integer, Integer> wst = distFrom(w);
        if (ca == -1) {
           return -1;
        }
        else {
          return vst.get(ca) + wst.get(ca);
        }
    }

    // Shortest common ancestor of vertices v and w.
    public int ancestor(int v, int w) {
        if (!((v >= 0 && v <= G.V() - 1) && (w >= 0 && w <= G.V() - 1))) {
            throw new IndexOutOfBoundsException();
        }
        SeparateChainingHashST<Integer, Integer> st = new 
           SeparateChainingHashST<Integer, Integer>();
        SeparateChainingHashST<Integer, Integer> vst = distFrom(v);
        SeparateChainingHashST<Integer, Integer> wst = distFrom(w);
        int sd =  Integer.MAX_VALUE;
        int ca = -1;
        for (int a: vst.keys()) {
            if (wst.contains(a)) {
                st.put(a, vst.get(a) + wst.get(a));
            }
        }
        for (int b: st.keys()) {
            if (sd > st.get(b)) {
                sd = st.get(b);
                ca = b;
            }
        }
        return ca;
    }

    // Length of the shortest ancestral path of vertex subsets A and B.
    public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) {
            throw new NullPointerException();
        }
        int [] an = triad(subsetA, subsetB);
        SeparateChainingHashST<Integer, Integer> vst = distFrom(an[0]);
        SeparateChainingHashST<Integer, Integer> wst = distFrom(an[2]);
        if (an[1] == -1) {
            return -1;
        }
        else {
            return vst.get(an[1]) + wst.get(an[1]);
        }
    }

    // A shortest common ancestor of vertex subsets A and B.
    public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) {
            throw new NullPointerException();
        }
        
        
        int [] an = triad(subsetA, subsetB);
        return an[1];
    }

    // Helper: Return a map of vertices reachable from v and their 
    // respective shortest distances from v.
    private SeparateChainingHashST<Integer, Integer> distFrom(int v) {
        if (!((v >= 0 && v <= G.V() - 1))) {
            throw new IndexOutOfBoundsException();
        }
        SeparateChainingHashST<Integer, Integer> st = new 
           SeparateChainingHashST<Integer, Integer>();
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        st.put(v, 0);
        q.enqueue(v);
        while (!q.isEmpty()) {
            int x = q.dequeue();
            for (int w: G.adj(x)) {
                if (!st.contains(w)) { 
                    st.put(w, st.get(x)+1);
                    q.enqueue(w);    
                }
            }
       
        }
        return st;
    } 
    // Helper: Return an array consisting of a shortest common ancestor a 
    // of vertex subsets A and B, and vertex v from A and vertex w from B 
    // such that the path v-a-w is the shortest ancestral path of A and B.
    private int[] triad(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
        if (subsetA == null || subsetB == null) {
            throw new NullPointerException();
        }
        int l = Integer.MAX_VALUE;
        int a = -1;
        int []t = new int [3];
        for (int v: subsetA) {
            for (int w: subsetB) {
                int lt = length(v, w);
                if (lt < l) {
                    l = lt;
                    a = ancestor(v, w);
                    t = new int[] {v, a, w};
                }
            }    
        }
        return t;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sca.length(v, w);
            int ancestor = sca.ancestor(v, w);
       
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}

