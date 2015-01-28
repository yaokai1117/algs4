/******************
 * 
 * Author: yaokai
 * Written: 1/27/2015
 * Last updated: 1/27/2015
 * 
 * Compilation:	javac Percolation.java
 * Execution:	java Percolation
 *  
 */


public class Percolation {

	private int N;						//the size of grid is N-by-N
	private boolean [] OpenState;		//record if one site is open
	private WeightedQuickUnionUF uf1;	//uf1 has its  first line connected 
	private WeightedQuickUnionUF uf2;	//uf2 has both its first line and last line connected
	
	/**
	 * create N-by-N grid, with all sites blocked
	 * @param N
	 */
	public Percolation(int N){
		this.N = N;
		OpenState = new boolean[N * N];
		uf1 = new WeightedQuickUnionUF(N * N);
		uf2 = new WeightedQuickUnionUF(N * N);
		for (int i = 0; i < N; i++) {
			uf1.union(0, i);					//union uf1's first line of sites
			uf2.union(0, i);					//union uf2's first line of sites
			uf2.union(N * N - 1, N * N - i - 1);//union uf2's last line 
		}
	}
	
	/**
	 * is site (row i, column j) open?
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		checkIndices(i, j);
		return OpenState[xyTo1D(i, j)];
	}
	
	/**
	 *  open site (row i, column j) if it is not open already
	 * @param i
	 * @param j
	 */
	public void open(int i, int j) {
		
		checkIndices(i, j);
		
		if(isOpen(i, j))					//set it opened
			return;
		int position = xyTo1D(i, j);
		OpenState[position] = true;
		
		int [] neighbor = new int[4];				
		neighbor[0] = xyTo1D(i - 1, j);
		neighbor[1] = xyTo1D(i, j + 1);
		neighbor[2] = xyTo1D(i + 1, j);
		neighbor[3] = xyTo1D(i, j - 1);
		for (int k = 0; k < neighbor.length; k++) {
			if(neighbor[k] >= 0 && OpenState[neighbor[k]] == true){
				uf1.union(position, neighbor[k]);
				uf2.union(position, neighbor[k]);
			}
		}
	}
	
	/**
	 * is site (row i, column j) full?
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		checkIndices(i, j);
		int position = xyTo1D(i, j);
		return isOpen(i, j) && uf1.connected(position, 0);
	}
	
	/**
	 * does the system percolate?
	 * @return
	 */
	public boolean percolates() {
		return uf2.connected(0, N * N - 1);
	}
	
	/**
	 * map 2D coordinates to 1D coordinates
	 * @param x
	 * @param y
	 * @param N
	 * @return
	 */
	private int xyTo1D(int i, int j) {
		if(i < 1 || i > N || j < 1 || j > N)
			return -1;
		else
			return (i - 1) * N + j - 1;
	}
	
	/**
	 * check if indices are from 1 to N
	 * @param i
	 * @param j
	 * @return
	 */
	private void checkIndices(int i, int j) {
		if(i < 1 || i > N)
			throw new IndexOutOfBoundsException("row index i out of bounds");
		if(j < 1 || j > N)
			throw new IndexOutOfBoundsException("row index j out of bounds");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	}

}
