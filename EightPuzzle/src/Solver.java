import java.util.Stack;

public class Solver {
	private Stack<Board> solutionBoards;
	private boolean solvability;
	private int movNumber;

	private class SearchNode implements Comparable<SearchNode> {
		Board board;
		int steps;
		int priority;
		SearchNode previous;
		
		public SearchNode(Board board, int steps, SearchNode previous) {
			// TODO Auto-generated constructor stub
			this.board = board;
			this.steps = steps;
			this.previous = previous;
			priority = board.manhattan() + steps;
		}
		
		@Override
		public int compareTo(SearchNode o) {
			// TODO Auto-generated method stub
			return this.priority - o.priority;
		}
		
	}
	
	
	public Solver(Board initial) {
		// TODO Auto-generated constructor stub
		if (initial == null)
			throw new NullPointerException();
		
		Board twinBoard = initial.twin();
		MinPQ<SearchNode> pq1 = new MinPQ<SearchNode>();
		MinPQ<SearchNode> pq2 = new MinPQ<SearchNode>();
		pq1.insert(new SearchNode(initial, 0, null));
		pq2.insert(new SearchNode(twinBoard, 0, null));
		
		SearchNode ansNode;
		while (true) {
			SearchNode node1 = pq1.delMin();
			SearchNode node2 = pq2.delMin();
			
			if (node1.board.isGoal()) {
				solvability = true;
				ansNode = node1;
				break;
			}
			if (node2.board.isGoal()) {
				solvability = false;
				ansNode = null;
				break;
			}
			
			Board preBoard;
			int curSteps;
			
			curSteps = node1.steps;
			if (node1.previous != null)
				preBoard = node1.previous.board;
			else 
				preBoard = null;
			for (Board neighbor : node1.board.neighbors()) {
				if (!neighbor.equals(preBoard))
					pq1.insert(new SearchNode(neighbor, curSteps + 1, node1));
			}
			
			curSteps = node2.steps;
			if (node2.previous != null)
				preBoard = node2.previous.board;
			else
				preBoard = null;
			for (Board neighbor : node2.board.neighbors()) {
				if (!neighbor.equals(preBoard))
					pq2.insert(new SearchNode(neighbor, curSteps + 1, node2));
			}
		}
		
		
		if (solvability == false) {
			movNumber = -1;
			solutionBoards = null;
		}
		else {
			Stack<Board> auxBoards = new Stack<Board>();
			movNumber = ansNode.steps;
			while (ansNode != null) {
				auxBoards.push(ansNode.board);
				ansNode = ansNode.previous;
			}
			solutionBoards = new Stack<Board>();
			while (!auxBoards.isEmpty()) {
				solutionBoards.push(auxBoards.pop());
			}
		}
	}
	
	public int moves() {
		return movNumber;
	}
	
	public boolean isSolvable() {
		return solvability;
	}
	
	public Iterable<Board> solution() {
		return solutionBoards;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// create initial board from file
	    In in = new In(args[0]);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}

}
