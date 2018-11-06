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
			//System.out.println("Null");
		}
		else {
			//System.out.println("Not null");
			convertSolution(solution);
		}
	}
	
	public int numberOfSolutions() {
		//System.out.println(pmb.uniqueSolutions(solutions));
		return pmb.uniqueSolutions();
	}
	
	private boolean hasSolution() {
		return solution != null;
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
	
	public void display() {
		if(hasSolution()) {
			int columnSize = Integer.toString(n).length() + 1;
			System.out.print("  ");
			for(int i = 0; i < n; i++) {
				System.out.printf("%" + columnSize + "s", (char)('A' + i));	//Print column titles in a format
			}
			System.out.println();
			for(int j = 0; j < n; j++) {
				System.out.printf("%" + columnSize + "s", (j + 1));	 //Print column titles in a format
				for(int i = 0; i < n; i++) {
					System.out.printf("%" + columnSize + "s", solution[i][j]);	//Display board
				}
				System.out.printf(" %-" + columnSize + "s", (j + 1));	//Print column titles in a format
				System.out.println();
			}
			System.out.print("  ");
			for(int i = 0; i < n; i++) {
				System.out.printf("%" + columnSize + "s", (char)('A' + i));	//Print column titles in a format
			}
			System.out.println();
		}
		else {
			System.out.println("No solution!");
		}
	}
}
