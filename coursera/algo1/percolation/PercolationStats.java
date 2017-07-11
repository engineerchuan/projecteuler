import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private final double[] percOpen;
    private final double mean;
    private final double stddev;
    
    public PercolationStats(int n, int trials) {
        if (n < 0) throw new IllegalArgumentException("n cannot be les than 0");
        if (trials < 0) throw new IllegalArgumentException("trials cannot be les than 0");        
        
        percOpen = new double[trials];
        
        for (int iTrial = 0; iTrial < trials; ++iTrial) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int iRow = StdRandom.uniform(n) + 1;
                int iCol = StdRandom.uniform(n) + 1;
                p.open(iRow, iCol);

                // StdOut.println(p.numberOfOpenSites());
            }
            // StdOut.println("iTrial: " + iTrial + ", " + p.numberOfOpenSites());
            percOpen[iTrial] = ((double) p.numberOfOpenSites() / (double) (n*n));
        }
        mean = StdStats.mean(percOpen);
        stddev = StdStats.stddev(percOpen);
    }
    public double mean() {
        return this.mean;
    }
    public double stddev() {
        return this.stddev;
    }
    public double confidenceLo() {
        return this.mean - 1.96 * stddev()/Math.sqrt((double) percOpen.length);
    }
    
    public double confidenceHi() {
        return 2 * this.mean - confidenceLo();
    } 

    public static void main(String[] args)  {
        if (args.length != 2) {
            throw new IllegalArgumentException("not enough arguments");
        }
        int n = Integer.parseInt(args[0]);
        int nTrials = Integer.parseInt(args[1]);
        
        PercolationStats pStats = new PercolationStats(n, nTrials);
        StdOut.println("mean   = " + pStats.mean());
        StdOut.println("stddev = " + pStats.stddev());
        StdOut.println("95% confidence interval = [" + pStats.confidenceLo() + ", " + pStats.confidenceHi() + "]");
        
    }
}