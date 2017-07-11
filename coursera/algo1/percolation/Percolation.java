import edu.princeton.cs.algs4.StdOut;

public class Percolation {
	private boolean[] open;
	private int[] parents;
	private int[] weights;
	private int n;

    public Percolation(int n) {
    	// there are n*n grids, the n*nth grid represents the "Top"
    	// the n*n+1 th grid represents the "Bottom"
    	open = new boolean[n*n+2];
    	parents = new int[n*n+2];
    	weights = new int[n*n+2];
    	this.n = n;
    	
    	// initialize all parents to empty
    	for (int i=0; i < parents.length; ++i) {
    		parents[i] = i; // -1 means empty
    	}
    	// initialize all weights to 0
    	for (int i=0; i < parents.length; ++i) {
    		weights[i] = 1;
    	}
    	// everything is closed for now
    	for (int i=0; i < open.length; ++i) {
    		open[i] = false;
    	}
    	
    	// Set up the Top Cell, and join entire first row to it
    	open[n*n] = true;
    	for (int i=0; i < n; ++i) {
    		join(i, n*n);	
    	}
    	
    	// Set up the Bottom Cell, and join entire bottom row to it
    	open[n*n+1] = true;
    	for (int i = n*n-n; i < n*n; ++i) {
    		join(i, n*n+1);
    	}
    	
    	
    }
    private int coord2ind(int i, int j) {
    	// returns index 0 - n^2
    	// assumes i, j are 1 indexed
        // row i, column j
    	return (i-1)*n + (j-1);
    }
    public boolean isFull(int i, int j) {
    	return (!this.open[coord2ind(i, j)]);
    }
    public boolean isOpen(int i, int j) {
    	return !isFull(i, j);
    }
    private int findRoot(int index) {
    	while(index != this.parents[index]) {
    		index = this.parents[index];    		
    	}
    	return index;    	
    }
    private boolean isConnected(int index1, int index2) {
    	return findRoot(index1) == findRoot(index2);    	
    }
    private void join(int index1, int index2) {
    	if (findRoot(index1) == findRoot(index2)) {
    		return; // nothing to do already joined
    	} else {
    		if (weights[index1] >= weights[index2]) {
    			parents[findRoot(index2)] = findRoot(index1);
    			weights[findRoot(index1)] += weights[findRoot(index2)];
    		} else {
    			parents[findRoot(index1)] = findRoot(index2);
    			weights[findRoot(index2)] += weights[findRoot(index1)];
    		}
    	}
    }
    public void open(int i, int j) {
    	// switch the coordinate from -1 to itself
    	int index = coord2ind(i, j);
    	this.open[index] = true; // now initialized
    	// then go and find all the "neighbors", and then join them
    	// Top neighbor
    	if (i > 1 && this.isOpen(i-1, j)) {
            StdOut.println("joining with top neighbor");
            join(coord2ind(i,j), coord2ind(i-1,j));
    	}
    	// Bottom neighbor
    	if (i < n && this.isOpen(i+1, j)) {
    		StdOut.println("joining with bottom neighbor");
            
    		join(coord2ind(i,j), coord2ind(i+1,j));
    	}
    	// Left neighbor
    	if (j > 1 && this.isOpen(i, j-1)) {
    		StdOut.println("joining with left neighbor");
    		join(coord2ind(i,j), coord2ind(i,j-1));
    	}
    	// Right neighbor
    	if (j < n && this.isOpen(i, j+1)) {
    		StdOut.println("joining with right neighbor");
    		join(coord2ind(i,j), coord2ind(i,j+1));
    	}
    }
    public boolean percolates() {
    	
    	return isConnected(n*n, n*n+1);
    }
}
