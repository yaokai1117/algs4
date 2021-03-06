/******************
 * 
 * Author: yaokai
 * Written: 1/27/2015
 * Last updated: 1/27/2015
 * 
 * Compilation:	javac PercolationStats.java
 * Execution:	java PercolationStats arg0 arg1
 *  
 */

public class PercolationStats {

	private int  T;				// T independent experiments on an N-by-N grid
	private double[] results;		// save the results of each experiments
	private static final double ConfidenFactor = 1.96; 
	
	/**
	 * perform T independent experiments on an N-by-N grid
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T){
		if(N <= 0 || T <= 0)
			throw new java.lang.IllegalArgumentException("Invalid input!");
		
		this.T = T;
		results = new double[T];		
		while(T-- > 0){
			int count = 0;
			Percolation samplePercolation = new Percolation(N);
			while(!samplePercolation.percolates()){
				int i = StdRandom.uniform(N) + 1;
				int j = StdRandom.uniform(N) + 1;
				if(samplePercolation.isOpen(i, j))
					continue;
				samplePercolation.open(i, j);
				count++;
			}
			results[T] = (double)count / (double)(N * N);
		}
	}
	
	/**
	 * sample mean of percolation threshold
	 * @return
	 */
	public double mean() {
		return StdStats.mean(results); 
	}
	
	/**
	 * sample standard deviation of percolation threshold
	 * @return
	 */
	public double stddev() {
		return StdStats.stddev(results);
	}
	
	/**
	 * low  endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceLo(){
		return mean() - stddev() * ConfidenFactor / Math.sqrt((double)T);
	}
	
	/**
	 * high endpoint of 95% confidence interval
	 * @return
	 */
	public double confidenceHi(){
		return mean() + stddev() * ConfidenFactor / Math.sqrt((double)T);
	}
	
	/**
	 * Test client
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats teStats = new PercolationStats(N, T);
		System.out.println("mean                    = " + teStats.mean());
		System.out.println("stddev                  = " + teStats.stddev());
		System.out.println("95% confidence interval = " + teStats.confidenceLo() + ", " + teStats.confidenceHi());
	}

}
