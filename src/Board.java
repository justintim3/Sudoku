import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	private int[][] state;
	private final int n;
	private final int groupSize;
	
	public Board(int n) {
		this.n = n;
		this.groupSize = (int)Math.sqrt(n);
		this.state = new int[n][n];
		randomPuzzle();
		/*
		state[0] = new int[]{8,0,0,0,0,0,0,0,0};
		state[1] = new int[]{0,0,7,5,0,0,0,0,9};
		state[2] = new int[]{0,3,0,0,0,0,1,8,0};
		state[3] = new int[]{0,6,0,0,0,1,0,5,0};
		state[4] = new int[]{0,0,9,0,4,0,0,0,0};
		state[5] = new int[]{0,0,0,7,5,0,0,0,0};
		state[6] = new int[]{0,0,2,0,7,0,0,0,4};
		state[7] = new int[]{0,0,0,0,0,3,6,1,0};
		state[8] = new int[]{0,0,0,0,0,0,8,0,0};*/
	}
	
	public Board(int[][] board) {
		state = board;
		n = state.length;
		this.groupSize = (int)Math.sqrt(n);
	}
	
	public int[][] getState() {
		return state;
	}
	
	public int getN() {
		return n;
	}
	
	public int getGroupSize() {
		return groupSize;
	}
	
	private void initializeBoard() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				state[i][j] = 1;
			}
		}
	}

	public void randomPuzzle() {
		double count = 0, avgCost = 0, iterations = 1;
		for(int k = 0; k < iterations; k++) {
		
		do {
			initializeBoard();
			avgCost += minConflicts();
			count++;
		} while(!isSolved());
		}
		//System.out.println("Iterations: " + (count / iterations));
		//System.out.println("Average Cost: " + (avgCost / iterations));
	}
	
	private int minConflicts() {
		LinkedList<Coordinate> conflicters = new LinkedList<Coordinate>();
		LinkedList<Integer> minConflicts = new LinkedList<Integer>();
		Random rand = ThreadLocalRandom.current();
		int randMinConflict, searchCost = 0, maxSteps = 350;	// 9x9 = 350, 16*16 = 2500?
		Coordinate randSquare;
		
		while(!isSolved()) {
			conflicters(conflicters);
			randSquare = conflicters.get(rand.nextInt(conflicters.size()));
			findMinConflicts(randSquare, minConflicts);
			randMinConflict = minConflicts.get(rand.nextInt(minConflicts.size()));
			state[randSquare.x][randSquare.y] = randMinConflict;
			searchCost++;
			if(searchCost >= maxSteps) {
				return searchCost;
			}
			//display();
			//System.out.println(value());
			conflicters.clear();
			minConflicts.clear();
		}
		return searchCost;
	}
	
	private void conflicters(LinkedList<Coordinate> conflicters) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(conflictingCol(i, j) || conflictingRow(i, j)	|| conflictingGroup(i, j)) {
					conflicters.add(new Coordinate(i, j));
				}
			}
		}
	}
	
	private void findMinConflicts(Coordinate randSquare, LinkedList<Integer> minConflictsll) {
		int x = randSquare.x, y = randSquare.y, conflicts;
		int minConflicts = Integer.MAX_VALUE;
		for(int k = 1; k <= n; k++) {
			state[x][y] = k;
			conflicts = 0;
			
			conflicts += conflictingColCount(x, y);
			conflicts += conflictingRowCount(x, y);
			conflicts += conflictingGroupCount(x, y);
			
			if(conflicts < minConflicts) {
				minConflicts = conflicts;
				minConflictsll.clear();
				minConflictsll.add(k);
			}
			else if(conflicts == minConflicts) {
				minConflicts = conflicts;
				minConflictsll.add(k);
			}
		}
	}
	
	private int value() {
		int conflicts = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(conflictingCol(i, j) || conflictingRow(i, j) || conflictingGroup(i, j)) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}
	
	private boolean conflictingCol(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y] == state[x][i] && y != i) {
				return true;
			}
		}
		return false;
	}
	
	private boolean conflictingRow(int x, int y) {
		for(int i = 0; i < n; i++) {
			if(state[x][y] == state[i][y] && x != i) {
				return true;
			}
		}
		return false;
	}
	
	private boolean conflictingGroup(int x, int y) {
		int startX = startingPoint(x), startY = startingPoint(y);
		
		for(int i = startX; i < startX + groupSize; i++) {
			for(int j = startY; j < startY + groupSize; j++) {
				if(state[x][y] == state[i][j] && x != i && y != j) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int startingPoint(int coord) {
		return (coord / groupSize) * groupSize;
	}
	
	private int conflictingColCount(int x, int y) {
		int conflicts = 0;
		for(int i = 0; i < n; i++) {
			if(state[x][y] == state[x][i] && y != i) {
				conflicts++;
			}
		}
		return conflicts;
	}
	
	private int conflictingRowCount(int x, int y) {
		int conflicts = 0;
		for(int i = 0; i < n; i++) {
			if(state[x][y] == state[i][y] && x != i) {
				conflicts++;
			}
		}
		return conflicts;
	}
	
	private int conflictingGroupCount(int x, int y) {
		int startX = startingPoint(x), startY = startingPoint(y), conflicts = 0;
		
		for(int i = startX; i < startX + groupSize; i++) {
			for(int j = startY; j < startY + groupSize; j++) {
				if(state[x][y] == state[i][j] && (x != i && y != j)) {
					conflicts++;
				}
			}
		}
		return conflicts;
	}
	
	public boolean isSolved() {
		return value() == 0;
	}
}