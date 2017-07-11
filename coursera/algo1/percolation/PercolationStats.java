import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private double[] percOpen;
    private int n;
    private int trials;
    
    public PercolationStats(int nInput, int trials) {
        percOpen = new double[trials];
        this.n = nInput;
        this.trials = trials;
        StdRandom.setSeed(0);
        
        for (int iTrial = 0; iTrial < trials; ++iTrial) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int iRow = StdRandom.uniform(n) + 1;
                int iCol = StdRandom.uniform(n) + 1;
                p.open(iRow, iCol);

                //StdOut.println(p.numberOfOpenSites());
            }
            //StdOut.println("iTrial: " + iTrial + ", " + p.numberOfOpenSites());
            percOpen[iTrial] = ((double) p.numberOfOpenSites() / (double) (n*n));
        }
    }
    public double mean() {
        return StdStats.mean(percOpen);    
    }                          // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(percOpen);
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo() {
        return mean() - 1.96 * stddev()/Math.sqrt((double) this.trials);
    }                  // low  endpoint of 95% confidence interval
    
    public double confidenceHi() {
        return 2 * mean() - confidenceLo();
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