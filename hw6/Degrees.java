import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

public class Degrees {
    private Digraph G;

    // Construct a Degrees object from a digraph G.
    public Degrees(Digraph G) {
        this.G = new Digraph(G);
    }
    
    // Sources of G.
    public Iterable<Integer> sources() {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        for (int i = 0; i < G.V(); i++)
            if (G.indegree(i) == 0)
                q.enqueue(i);
        return q;
    }

    // Sinks of G.
    public Iterable<Integer> sinks() {
        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        for (int i = 0; i < G.V(); i++)
            if (G.outdegree(i) == 0)
                q.enqueue(i);
        return q;
    }

    // Is G a map?
    public boolean isMap() {
        for (int i = 0; i < G.V(); i++)
            if (!(G.outdegree(i) == 1))
                return false;
        return true;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        Degrees degrees = new Degrees(G);
        StdOut.println("Sources = " + degrees.sources());
        StdOut.println("Sinks   = " + degrees.sinks());
        StdOut.println("Is Map  = " + degrees.isMap());
    }
}
