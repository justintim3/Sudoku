public class Puzzle {
	private Board originalPuzzle;
	private int[][] state;
	private int n;
	
	public Puzzle() {
		Board board = new Board(9);
		Generator g = new Generator(board);
		originalPuzzle = g.generatePuzzle();
		n = originalPuzzle.getN();
		state = deepCopy(originalPuzzle);
	}
	
	public Puzzle(Board puzzle) {
		this.state = puzzle.getState();
	}
	
	public int getN() {
		return n;
	}
	
	public void setTile(int x, int y, int num) {
		state[x][y] = num;
	}
	
	public int[][] getState() {
		return state;
	}
	
	public Board getOriginalPuzzle() {
		return this.originalPuzzle;
	}
	
	private int[][] deepCopy(Board board) {
		int[][] state = board.getState();
		int[][] copy = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copy[i][j] = state[i][j];
			}
		}
		return copy;
	}
}