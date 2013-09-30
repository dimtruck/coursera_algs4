public class PercolationStats {
    private int[] thresholdArray;
    private int threshold;
    private int totalAvailable;
    
    public PercolationStats(int N, int T) {
        this.thresholdArray = new int[T];
        this.threshold = T;
        this.totalAvailable = N*N;
        //loop through the threshold array
        for (int i = 0; i < T; i++) {
            //run the percolation 
            Percolation p = new Percolation(N);
            int openCount = 0;
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, N+1);
                int col = StdRandom.uniform(1, N+1);
                if (!p.isOpen(row, col)) {
                    openCount++;
                    p.open(row, col);
                    if (openCount >= this.totalAvailable)
                        break;
                }
            }
            //System.out.println("total count: " + openCount);
            thresholdArray[i] = openCount;
        }
    }
    public double mean() {
        return StdStats.mean(this.thresholdArray)/this.totalAvailable;
    }
    public double stddev() {
        return StdStats.stddev(this.thresholdArray)/this.totalAvailable;
    }
    public double confidenceLo() {
        return (mean() - 1.96*stddev()/Math.sqrt(this.threshold));
    }
    public double confidenceHi() {
        return (mean() + 1.96*stddev()/Math.sqrt(this.threshold));
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if (N <= 0 || T <= 0) throw new java.lang.IllegalArgumentException();
        Stopwatch s = new Stopwatch();
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean = " + stats.mean());
        System.out.println("stddev = " + stats.stddev());
        System.out.println("95% confidence interval = " 
                               + stats.confidenceLo() + "," 
                               + stats.confidenceHi());
        System.out.println("time: " + s.elapsedTime());
    }
}