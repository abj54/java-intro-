import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.LinkedQueue;
import edu.princeton.cs.algs4.StdOut;

// Models a board in the 8-puzzle game or its generalization.
public class Board {
    private int N;
    private int [][] tiles;
    private int ham;
    private int manha;
    // Construct a board from an N-by-N array of tiles, where 
    // tiles[i][j] = tile at row i and column j, and 0 represents the blank 
    // square.
    public Board(int[][] tiles) {
        ham = 0;
        manha = 0;
        int P, X, Y, dis;
        N = tiles.length;
        this.tiles = tiles;
        
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != i * N + j + 1 && tileAt(i, j) != 0) {
                    ham++;
                    P = tileAt(i, j);
                    X = (P-1) / N;
                    Y = (P -1) % N;
                    dis = Math.abs(i - X) + Math.abs(j - Y);
                    manha += dis;
                }
            }
        }
    }
    // Tile at row i and column j.
    public int tileAt(int i, int j) {
        return this.tiles[i][j];
    }
    
    // Size of this board.
    public int size() {
        return N;
    }

    // Number of tiles out of place.
    public int hamming() {
        return ham;
    }

    // Sum of Manhattan distances between tiles and goal.
    public int manhattan() {
        return manha;
    }

    // Is this board the goal board?
    public boolean isGoal() {
        return (hamming() == 0); 
    }

    // Is this board solvable?
    public boolean isSolvable() {
        if (N % 2 == 1) {
            return (inversions() % 2 == 0);
        }
        else {
            int r = blankPos() / N;
            return ((inversions() + r) % 2 != 0);
        }
    }

    // Does this board equal that?
   public boolean equals(Board that) {
      if (that == null)
         return false;
      if (size() == that.size()) {
         for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
               if (tileAt(i, j) != that.tileAt(i, j)) {
                   return false;
                }
            }
        }
        return true;
      }
      return false;
    }

    // All neighboring boards.
    public Iterable<Board> neighbors() {
        LinkedQueue<Board> nei = new LinkedQueue<Board>();
        int br = blankPos() / N;
        int bc = blankPos() % N;
        int [][]bd = new int [N][N];
        

        if (br > 0) {
           bd = cloneTiles();
           int i = bd[br-1][bc];
           bd[br-1][bc] = bd[br][bc];
           bd[br][bc] = i;
           Board board = new Board(bd);
           nei.enqueue(board);
        }
       
       
        if (bc < N - 1) {
           bd = cloneTiles();
           int i = bd[br][bc+1];
           bd[br][bc+1] = bd[br][bc];
           bd[br][bc] = i;
           Board board = new Board(bd);
           nei.enqueue(board); 
        }
         if (br < N-1) {
           bd = cloneTiles();
           int i = bd[br+1][bc];
           bd[br+1][bc] = bd[br][bc];
           bd[br][bc] = i;
           Board board = new Board(bd);
           nei.enqueue(board);
        }
       
         if (bc > 0) {
           bd = cloneTiles();
           int i = bd[br][bc-1];
           bd[br][bc-1] = bd[br][bc];
           bd[br][bc] = i;
           Board board = new Board(bd);
           nei.enqueue(board);
            
        }
       
        return nei;
    }

    // String representation of this board.
    public String toString() {
        String result = new String();
        result += N + "\n";
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                result += String.format("%2d", tileAt(i, j));
                if (j != N - 1)
                result += " ";
            }
            if (i != N - 1)
            result += "\n";
        }
        return result;
    }

    // Helper method that returns the position (in row-major order) of the 
    // blank (zero) tile.
    private int blankPos() {
        int b = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == 0) {
                    b = i * N + j;
                }
            }
        }   
        return b;
    }

    // Helper method that returns the number of inversions.
    private int inversions() {
        int inv = 0;
        int b = 0;
        int [] a = new int [N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                  a[b] = tileAt(i, j);
                  b++;
                  
            }
        }
        for (int i = 0; i < N*N; i++) {
           for (int j = i + 1; j < N * N; j++) {
             if (a[i] > a[j] && a[i] != 0 && a[j] != 0)
                 inv++;
           }
       }
       return inv;
    }

    // Helper method that clones the tiles[][] array in this board and 
    // returns it.
    private int[][] cloneTiles() {
        int [][]clone = new int [N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                clone[i][j] = tiles[i][j];
            }
        }
        return clone;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board board = new Board(tiles);
        StdOut.println(board.hamming());
        StdOut.println(board.manhattan());
        StdOut.println(board.isGoal());
        StdOut.println(board.isSolvable());
        for (Board neighbor : board.neighbors()) {
            StdOut.println(neighbor);
        }
    }
}
