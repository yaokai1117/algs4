
public class Board {
	private int[][] blocks;
	private int dimension;
	
	public Board(int[][] blocks) {
		// TODO Auto-generated constructor stub
		this.dimension = blocks.length;
		this.blocks = blocks.clone();
	}
	
	public int dimension() {
		return dimension;
	}
	
	public int hamming() {
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = new int[10][10];
		System.out.println(test.length);
	}

}
