public class Puzzle {
	private Board originalPuzzle;
	private int[][] state;
	private int[][] solution;
	private int n;
	
	public Puzzle(int n) {
		this.n = n;
		Board board = new Board(n);
		Generator g = new Generator(board);
		originalPuzzle = g.generatePuzzle();
		state = deepCopy(originalPuzzle);
		getSolution();
	}
	
	public Puzzle(Board board) {
		originalPuzzle = board;
		n = originalPuzzle.getN();
		state = deepCopy(originalPuzzle);
		getSolution();
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
	
	public void setState(int[][] state) {
		this.state = state;
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
	
	public boolean isSolved() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(solution[i][j] != state[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void getSolution() {
		Solver s = new Solver(originalPuzzle);
		s.solve();
		solution = s.getSolution().getState();
	}
	
	public void checkSolution() {
		if(isSolved()) {
			System.out.println("Solved!");
		}
		else {
			System.out.println("Not solved!");
		}
	}
	
}