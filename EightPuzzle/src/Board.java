import java.util.Stack;

public class Board {
	private char[] blocks;
	private int dimension;
	private int N;
	private int white;
	
	public Board(int[][] blocks) {
		// TODO Auto-generated constructor stub
		this.dimension = blocks.length;
		this.N = dimension*dimension;
		this.blocks = new char[N];
		for (int i = 0; i < dimension; i++)
			for (int j = 0; j < dimension; j++) {
				if (blocks[i][j] == 0)
					this.white = i*dimension + j;
				this.blocks[i*dimension + j] = (char)blocks[i][j];
			}
	}
	
	public int dimension() {
		return dimension;
	}
	
	public int hamming() {
		int ret = 0;
		for (int i = 0; i < N - 1; i++)
			if (blocks[i] != i + 1)
				ret++;
		return ret;
	}
	
	public int manhattan() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			if (blocks[i] == 0)
				continue;
			ret += Math.abs(i%dimension - (blocks[i]-1)%dimension);
			ret += Math.abs(i/dimension - (blocks[i]-1)/dimension);
		}
		return ret;
	}
	
	public boolean isGoal() {
		return true;
	}
	
	private void swap(int i, int j) {
		char temp = blocks[i];
		blocks[i] = blocks[j];
		blocks[j] = temp;
		if (blocks[i] == 0)
			white = i;
		else if (blocks[j] == 0)
			white = j;
	}
	
	private Board myClone() {
		int[][] aux = new int[dimension][dimension];
		for (int k = 0; k < N; k++)
			aux[k/dimension][k%dimension] = blocks[k];
		Board cloneBoard = new Board(aux);
		return cloneBoard;
	}
	
	public Board twin() {
		int i = 0;
		if (blocks[i] == 0)
			i++;
		int j = i + 1;
		if (blocks[j] == 0)
			j++;
		Board twinBoard = this.myClone();
		twinBoard.swap(i, j);
		return twinBoard;
	}
	
	@Override
	public boolean equals(Object y) {
		// TODO Auto-generated method stub
		if (y == null)
			return false;
		Board that = (Board)y;
		for (int i = 0; i < N; i++)
			if (this.blocks[i] != that.blocks[i])
				return false;
		return true;
	}
	
	public Iterable<Board> neighbors() {
		Stack<Board> retBoards = new Stack<Board>();
		int i = white / dimension;
		int j = white % dimension;
		if (i != 0) {
			Board neighbor = this.myClone();
			neighbor.swap(white, white - dimension);
			retBoards.push(neighbor);
		}
		if (i != dimension - 1) {
			Board neighbor = this.myClone();
			neighbor.swap(white, white + dimension);
			retBoards.push(neighbor);
		}
		if (j != 0) {
			Board neighbor = this.myClone();
			neighbor.swap(white, white - 1);
			retBoards.push(neighbor);
		}
		if (j != dimension - 1) {
			Board neighbor = this.myClone();
			neighbor.swap(white, white + 1);
			retBoards.push(neighbor);
		}	
		return retBoards;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder s = new StringBuilder();
		s.append(dimension + "\n");
		for (int i = 0; i < N; i++){
			s.append(String.format("%2d ", (int)blocks[i]));
			if ((i + 1) % dimension == 0)
				s.append("\n");
		}
		return s.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] test = {{8,1,3},{4,0,2},{7,6,5}};
		Board testBoard = new Board(test);
		System.out.println(testBoard.dimension() + " " + testBoard.hamming() + " " + testBoard.manhattan());
		System.out.println(testBoard);
		for (Board board : testBoard.neighbors())
			System.out.println(board);
		System.out.println(testBoard.twin());
		System.out.println(testBoard.equals(testBoard));
		System.out.println(testBoard.equals(testBoard.twin()));
	}

}
