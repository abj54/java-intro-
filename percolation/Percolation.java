import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// Models an N-by-N percolation system.
public class Percolation {
    private int size;
    private boolean[][] perc;
    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF twqu;
    private int count;

    // Create an N-by-N grid, with all sites blocked.
    public Percolation(int N) {
        size = N;
        count = 0;
        wqu = new WeightedQuickUnionUF((size) * (size)+2);
        twqu = new WeightedQuickUnionUF((size) * (size)+1);
        perc = new boolean[size][size];
        if (size <= 0) {
            throw new IllegalArgumentException();
        } 
        else {
            for (int a = 0; a < size; a++) {
                for (int b = 0; b < size; b++) {
                    perc[a][b] = false;
                }
            }
        }
    }

    // Open site (i, j) if it is not open already.
    public void open(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
            if (!isOpen(i, j)) {
                perc[i][j] = true;
                count++;
                if (i == 0) {                   //If the site belongs in top,     
                    wqu.union(encode(i, j), 0);  //Connect source to open sites
                    twqu.union(encode(i, j), 0); 
                }
                if (i == size - 1) {             //If sites belong at the bottom'
                    wqu.union(encode(i, j), (size*size+1));  //Cennect sink to open sites
                }
             
        
                if (i > 0 && isOpen(i - 1, j)) {       
                    wqu.union(encode(i, j), encode(i - 1, j));
                    twqu.union(encode(i, j), encode(i - 1, j));
  
                }
        	 
			    if (i < size-1 && isOpen(i + 1, j)) {
            	    wqu.union(encode(i, j), encode(i + 1, j));
            		twqu.union(encode(i, j), encode(i + 1, j));
        	    }
                if (j > 0 && isOpen(i, j - 1)) {
                    wqu.union(encode(i, j), encode(i, j - 1));
                    twqu.union(encode(i, j), encode(i, j - 1));
                }
                if (j < size-1 && isOpen(i, j + 1)) {
                    wqu.union(encode(i, j), encode(i, j + 1));
                    twqu.union(encode(i, j), encode(i, j + 1));
                }
            }
        }
    }

    // Is site (i, j) open?
    public boolean isOpen(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
            return perc[i][j];
        }

    }

    // Is site (i, j) full?
    public boolean isFull(int i, int j) {
        if (i >= size || j >= size || i < 0 || j < 0) {
            throw new IndexOutOfBoundsException();
        }
        else {
           if (isOpen(i, j)) { 
              return (twqu.connected(encode(i, j), 0)); 
           }
        }
        return false;   
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return count;
    }

    // Does the system percolate?
    public boolean percolates() { 
        return wqu.connected(0, (size*size + 1));            
    }

    // An integer ID (1...N) for site (i, j).
    private int encode(int i, int j) {
        int k = size * i + j + 1;
        return k;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);
        int N = in .readInt();
        Percolation perc = new Percolation(N);
        while (!in .isEmpty()) {
            int i = in .readInt();
            int j = in .readInt();
            perc.open(i, j);
        }
        StdOut.println(perc.numberOfOpenSites() + " open sites");
        if (perc.percolates()) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

        // Check if site (i, j) optionally specified on the command line
        // is full.
        if (args.length == 3) {
            int i = Integer.parseInt(args[1]);
            int j = Integer.parseInt(args[2]);
            StdOut.println(perc.isFull(i, j));
        }
    }
}
