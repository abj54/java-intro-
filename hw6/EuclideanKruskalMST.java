import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.UF;

public class EuclideanKruskalMST {
    private double weight; 
    private LinkedQueue<EuclideanEdge> mst; 

    // Compute a minimum spanning tree (or forest) of an Euclidean 
    // edge-weighted graph.
    public EuclideanKruskalMST(EuclideanEdgeWeightedGraph G) {
        MinPQ<EuclideanEdge> pq = new MinPQ<EuclideanEdge>();
        SeparateChainingHashST<Point2D, Integer> st 
            = new SeparateChainingHashST<Point2D, Integer>();
        for (EuclideanEdge e : G.edges()) {
            pq.insert(e);
            Point2D v = e.either();
            Point2D w = e.other(v);
            if (!st.contains(v))
                st.put(v, st.size());
            if (!st.contains(w))
                st.put(w, st.size());
        }
        UF uf = new UF(G.V());
        mst = new LinkedQueue<EuclideanEdge>();
        while (!pq.isEmpty() && mst.size() < G.V() -1) {
            EuclideanEdge e = pq.delMin();
            Point2D v = e.either();
            Point2D w = e.other(v);
            int vi = st.get(v);
            int wi = st.get(w);
            if (!uf.connected(vi, wi)) {
                uf.union(vi, wi);
                mst.enqueue(e);
                weight += e.weight();
            }
        }
        
    }

    // Edges in a minimum spanning tree (or forest).
    public Iterable<EuclideanEdge> edges() {
        return mst;
    }

    // Sum of the edge weights in a minimum spanning tree (or forest).
    public double weight() {
        return weight;
    }
    
    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        EuclideanEdgeWeightedGraph G = new EuclideanEdgeWeightedGraph(in);
        EuclideanKruskalMST mst = new EuclideanKruskalMST(G);
        for (EuclideanEdge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
    }
}
