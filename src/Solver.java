public class Solver {
	private PotentialMovesBoard pmb;
	private int[][] solution;
	private final int n;
	
	public Solver(Board b) {
		pmb = new PotentialMovesBoard(b);
		pmb.simplify();
		
		int[][] board = b.getState();
		n = board.length;
	}
	
	public void solve() {
		PotentialMovesBoard solution = pmb.solve();
		if(solution == null) {
		}
		else {
			convertSolution(solution);
		}
	}
	
	public int numberOfSolutions() {
		return pmb.uniqueSolutions();
	}
	
	private void convertSolution(PotentialMovesBoard pmb) {
		solution = new int[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				solution[i][j] = pmb.getState()[i][j].get(0);
			}
		}
	}
	
	public Board getSolution() {
		return new Board(solution);
	}
}
