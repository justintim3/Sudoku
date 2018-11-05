import java.util.LinkedList;

public class PotentialMovesBoard {
	private LinkedList<Integer>[][] state;
	private final int n;
	private final int groupSize;
	
	public PotentialMovesBoard(Board b) {
		int[][] board = b.getState();
		n = board.length;
		groupSize = (int)Math.sqrt(n);
		
		instantiate();
		initialize(board);
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				removeConflicts(i, j);
			}
		}
	}
	
	public PotentialMovesBoard(PotentialMovesBoard b, int x, int y, int value) {
		n = b.n;
		groupSize = b.groupSize;
		
		instantiate();
		state = b.getStateDeepCopy();
		state[x][y].clear();
		state[x][y].add(value);
		removeConflicts(x, y);
	}
	
	public int getN() {
		return n;
	}
	
	public LinkedList<Integer>[][] getState() {
		return state;
	}
	
	public LinkedList<Integer>[][] getStateDeepCopy() {
		LinkedList<Integer>[][] copy = new LinkedList[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				copy[i][j] = new LinkedList<Integer>();
				for(int k = 0; k < state[i][j].size(); k++) {
					copy[i][j].add(state[i][j].get(k));
				}
			}
		}
		return copy;
	}
	
	private void instantiate() {
		state = new LinkedList[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				state[i][j] = new LinkedList<Integer>();
			}
		}
	}
	
	private void initialize(int[][] board) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j] != 0) {	//If tile is not blank
					state[i][j].add(board[i][j]);
				}
				else {
					for(int k = 1; k <= n; k++) {	//Add all possible tile numbers for that tile
						state[i][j].add(k);
					}
				}
			}
		}
	}
	
	public PotentialMovesBoard solve() {
		if(isSolved() && isViable()) {
			return this;
		}
		Coordinate mostConstrained = mostConstrained();
		int potentialMoves, x = mostConstrained.x, y = mostConstrained.y;;
		
		potentialMoves = state[x][y].size();
		if(potentialMoves > 1) {
			for(int k = 0; k < potentialMoves; k++) {
				//steps++;
				//display();
				PotentialMovesBoard nextMove = new PotentialMovesBoard(this, x, y, this.getState()[x][y].get(k));
				if(nextMove.isViable()) {
					if(nextMove.isSolved()) {
						//System.out.println(steps);
						return nextMove;
					}
					else {
						PotentialMovesBoard nextMoveSolve;
						if((nextMoveSolve = nextMove.solve()) != null) {
							if(nextMoveSolve.isSolved() && nextMoveSolve.isViable()) {
								//System.out.println(steps);
								return nextMoveSolve;
							}
							else {
								return null;
							}
						}
					}
				}
			}
			//System.out.println(steps);
		}
		//System.out.println("Return null");
		if(isSolved()) {
			return this;
		}
		else {
			return null;
		}
	}
	
	public int uniqueSolutions() {
		if(isSolved() && isViable()) {
			return 1;
		}
		int potentialMoves, solutions = 0;
		//Coordinate mostConstrained = mostConstrained();
		//int i = mostConstrained.x;
		//int j = mostConstrained.y;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				potentialMoves = state[i][j].size();
				if(potentialMoves > 1) {
					for(int k = 0; k < potentialMoves; k++) {
						//display();
						PotentialMovesBoard nextMove = new PotentialMovesBoard(this, i, j, this.getState()[i][j].get(k));
						if(nextMove.isViable()) {
							if(nextMove.isSolved()) {
								//System.out.println(steps);
								solutions++;
							}
							else {
								PotentialMovesBoard nextMoveSolve;
								if((nextMoveSolve = nextMove.solve()) != null) {
									if(nextMoveSolve.isSolved() && nextMoveSolve.isViable()) {
										//System.out.println(steps);
										solutions++;
									}
								}
							}
							if(solutions > 1) {
								return solutions;
							}
						}
					}
					//System.out.println(steps);
				}
			}
		}
		//System.out.println("Return null");
		return solutions;
	}
	
	private Coordinate mostConstrained() {
		int min = n, minX = 0, minY = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(state[i][j].size() < min && state[i][j].size() > 1) {
					min = state[i][j].size();
					minX = i;
					minY = j;
				}
			}
		}
		return new Coordinate(minX, minY);
	}
	
	private void removeConflicts(int x, int y) {
		filterCol(x, y);
		filterRow(x, y);
		filterGroup(x, y);
	}
	
	private void filterCol(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y].size() == 1 && state[x][i].contains(state[x][y].peek()) && y != i) {
				state[x][i].removeFirstOccurrence(state[x][y].peek());
				if(state[x][i].size() == 1) {
					removeConflicts(x, i);
				}
			}
		}
	}
	
	private void filterRow(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y].size() == 1 && state[i][y].contains(state[x][y].peek()) && x != i) {
				state[i][y].removeFirstOccurrence(state[x][y].peek());
				if(state[i][y].size() == 1) {
					removeConflicts(i, y);
				}
			}
		}
	}
	
	private void filterGroup(int x, int y) {
		int startX = startingPoint(x), startY = startingPoint(y);
		
		for(int i = startX; i < startX + groupSize; i++) {
			for(int j = startY; j < startY + groupSize; j++) {
				if(state[x][y].size() == 1 && state[i][j].contains(state[x][y].peek()) && (x != i || y != j)) {
					state[i][j].removeFirstOccurrence(state[x][y].peek());
					if(state[i][j].size() == 1) {
						removeConflicts(i, j);
					}
				}
			}
		}
	}
	
	private int startingPoint(int coord) {
		return (coord / groupSize) * groupSize;
	}
	
	public boolean isViable() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(state[i][j].size() == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isSolved() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(state[i][j].size() != 1) {
					return false;
				}
				else if(conflictingCol(i, j) || conflictingRow(i, j) || conflictingGroup(i, j)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean conflictingCol(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y].peek() == state[x][i].peek() && y != i) {
				return true;
			}
		}
		return false;
	}
	
	private boolean conflictingRow(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y].peek() == state[i][y].peek() && x != i) {
				return true;
			}
		}
		return false;
	}
	
	private boolean conflictingGroup(int x, int y) {
		int startX = startingPoint(x), startY = startingPoint(y);
		
		for(int i = startX; i < startX + groupSize; i++) {
			for(int j = startY; j < startY + groupSize; j++) {
				if(state[x][y].peek() == state[i][j].peek() && x != i && y != j) {
					return true;
				}
			}
		}
		return false;
	}
	
	public void display() { 
		int columnSize = Integer.toString(n).length() + 1;
		System.out.print("  ");
		for(int i = 0; i < n; i++) {
			System.out.printf("%" + columnSize + "s", (char)('A' + i));	//Print column titles in a format
		}
		System.out.println();
		for(int j = 0; j < n; j++) {
			System.out.printf("%" + columnSize + "s", (j + 1));	 //Print column titles in a format
			for(int i = 0; i < n; i++) {
				for(int k = 0; k < state[i][j].size(); k++) {
					System.out.printf("%" + columnSize + "s", state[i][j].get(k));	//Display board
				}
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
}