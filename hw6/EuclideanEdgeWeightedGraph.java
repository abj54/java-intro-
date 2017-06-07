import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedBag;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;

public class EuclideanEdgeWeightedGraph {
    private int V;
    private int E;
    private SeparateChainingHashST<Point2D, LinkedBag<EuclideanEdge>> adj;

    // Initialize an empty Euclidean edge-weighted graph from an input stream.
    public EuclideanEdgeWeightedGraph(In in) {
        V = in.readInt();
        E = in.readInt();
        adj = new SeparateChainingHashST<Point2D, LinkedBag<EuclideanEdge>>();
        for (int i = 0; i < E; i++) {
            Point2D v = new Point2D(in.readDouble(), in.readDouble());
            Point2D w = new Point2D(in.readDouble(), in.readDouble());
            EuclideanEdge e = new EuclideanEdge(v, w);
            addEdge(e);
        }
    }
    
    // Number of vertices in this Euclidean edge-weighted graph.
    public int V() {
        return V; 
    }

    // Number of edges in this Euclidean edge-weighted graph.
    public int E() {
        return E;
    }

    // Add an undirected edge to this Euclidean edge-weighted graph.
    public void addEdge(EuclideanEdge e) {
        Point2D v = e.either();
        Point2D w = e.other(v);
        if (!adj.contains(v))
            adj.put(v, new LinkedBag<EuclideanEdge>());
        if (!adj.contains(w))
            adj.put(w, new LinkedBag<EuclideanEdge>());
        adj.get(v).add(e);
        adj.get(w).add(e);
    }

    // Edges incident on vertex v.
    public Iterable<EuclideanEdge> adj(Point2D v) {
        return adj.get(v);
    }

    // All the edges in this Euclidean edge-weighted graph.
    public Iterable<EuclideanEdge> edges() {
        LinkedBag<EuclideanEdge> bag = new LinkedBag<EuclideanEdge>();
        for (Point2D v : adj.keys()) {
            int selfLoops = 0;
            for (EuclideanEdge e : adj(v)) {
                if (e.other(v).hashCode() > v.hashCode()) {
                    bag.add(e);
                }
                else if (e.other(v).equals(v)) {
                    if (selfLoops % 2 == 0) bag.add(e);
                    selfLoops++;
                }
            }
        }
        return bag;
    }

    // A string representation of this Euclidean edge-weighted graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (Point2D v : adj.keys()) {
            s.append(v + ": ");
            for (EuclideanEdge e : adj(v)) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        EuclideanEdgeWeightedGraph G = new EuclideanEdgeWeightedGraph(in);
        for (EuclideanEdge e : G.edges()) {
            StdOut.println(e);
        }
    }
}
