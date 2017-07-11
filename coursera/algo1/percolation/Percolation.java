// import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    private boolean[] active;
    private WeightedQuickUnionUF uf1;
    private WeightedQuickUnionUF uf2;
    private final int n;
    public Percolation(int n) {
        
        if (n < 0) {
            throw new IllegalArgumentException("input argument less than 0");
        }
        
        uf1 = new WeightedQuickUnionUF(n*n+1);
        uf2 = new WeightedQuickUnionUF(n*n+2);
        
        // there are n*n grids, the n*nth grid represents the "Top"
        // the n*n+1 th grid represents the "Bottom"
        active = new boolean[n*n+2];
        this.n = n;
        
        // everything is closed for now
        for (int i = 0; i < active.length; ++i) {
            active[i] = false;
        }
        
        // Set up the Top Cell, and join entire first row to it
        active[n*n] = true;
        for (int i = 0; i < n; ++i) {
            uf1.union(i, n*n);
            uf2.union(i, n*n);
            
        }
        
        // Set up the Bottom Cell, and join entire bottom row to it
        active[n*n+1] = true;
        for (int i = n*n-n; i < n*n; ++i) {
            uf2.union(i, n*n+1);
        }  
    }
    private int coord2ind(int row, int col) {
        // returns index 0 - n^2
        // assumes i, j are 1 indexed
        // row i, column j
        return (row-1)*n + (col-1);
    }
    public boolean isFull(int row, int col) {
        checkDimensions(row, col);   
        return isOpen(row, col) && uf1.connected(coord2ind(row, col), n*n);
    }
    private void checkDimensions(int row, int col) {
        if (row < 0 || row > n || col < 0 || col > n) {
            throw new IllegalArgumentException("Row / column exceeds boundaries");
        }
    }
    public boolean isOpen(int row, int col) {
        checkDimensions(row, col);        
        return this.active[coord2ind(row, col)];
    }


    private void join(int index1, int index2) {
        if (uf1.connected(index1, index2)) {
            return; // nothing to do already joined
        }
        else {
            uf1.union(index1, index2);
            uf2.union(index1, index2);
        }
    }
    public void open(int row, int col) {
        checkDimensions(row, col);   
        // switch the coordinate from -1 to itself
        int index = coord2ind(row, col);
        this.active[index] = true; // now initialized
        // then go and find all the "neighbors", and then join them
        // Top neighbor
        if (row > 1 && this.isOpen(row-1, col)) {
            // StdOut.println("joining with top neighbor");
            join(coord2ind(row, col), coord2ind(row-1, col));
        }
        // Bottom neighbor
        if (row < n && this.isOpen(row+1, col)) {
            // StdOut.println("joining with bottom neighbor");
            join(coord2ind(row, col), coord2ind(row+1, col));
        }
        // Left neighbor
        if (col > 1 && this.isOpen(row, col-1)) {
            // StdOut.println("joining with left neighbor");
            join(coord2ind(row, col), coord2ind(row, col-1));
        }
        // Right neighbor
        if (col < n && this.isOpen(row, col+1)) {
            // StdOut.println("joining with right neighbor");
            join(coord2ind(row, col), coord2ind(row, col+1));
        }
    }
    public int numberOfOpenSites() {
        int nCount = 0;
        for (int i = 0; i < n*n; ++i) {
            if (active[i]) {
                nCount += 1;
            }
        }
        return nCount;
    }
    public boolean percolates() {
        if (1 == n && !this.active[0]) return false; 
        return uf2.connected(n*n, n*n+1);
    }
}
