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
		//harder
		state[0] = new int[]{0,1,0,0,0,0,0,0,0};
		state[1] = new int[]{0,0,0,0,0,0,0,0,4};
		state[2] = new int[]{0,0,0,0,0,0,0,2,0};
		state[3] = new int[]{7,0,4,0,5,0,0,0,0};
		state[4] = new int[]{0,0,3,0,0,0,8,0,0};
		state[5] = new int[]{0,0,0,0,9,0,1,0,0};
		state[6] = new int[]{0,0,2,0,0,4,0,0,3};
		state[7] = new int[]{0,0,0,0,0,1,0,5,0};
		state[8] = new int[]{0,0,0,6,0,8,0,0,0};*/
		
		/*
		state[0] = new int[]{0,5,0,0,0,0,0,0,0};
		state[1] = new int[]{0,0,0,0,7,2,6,0,0};
		state[2] = new int[]{0,0,0,0,0,0,2,3,0};
		state[3] = new int[]{0,0,0,0,0,0,1,8,2};
		state[4] = new int[]{0,9,0,0,0,0,0,0,0};
		state[5] = new int[]{7,0,0,0,0,0,4,0,0};
		state[6] = new int[]{0,3,0,7,0,0,0,9,5};
		state[7] = new int[]{0,0,7,0,0,6,0,0,0};
		state[8] = new int[]{0,0,2,9,4,0,0,0,0};*/
		
		/*
		//hard
		state[0] = new int[]{8,0,0,0,0,0,0,0,0};
		state[1] = new int[]{0,0,7,5,0,0,0,0,9};
		state[2] = new int[]{0,3,0,0,0,0,1,8,0};
		state[3] = new int[]{0,6,0,0,0,1,0,5,0};
		state[4] = new int[]{0,0,9,0,4,0,0,0,0};
		state[5] = new int[]{0,0,0,7,5,0,0,0,0};
		state[6] = new int[]{0,0,2,0,7,0,0,0,4};
		state[7] = new int[]{0,0,0,0,0,3,6,1,0};
		state[8] = new int[]{0,0,0,0,0,0,8,0,0};*/
		
		
		/*
		state[0] = new int[]{5,3,0,0,7,0,0,0,0};
		state[1] = new int[]{6,0,0,1,9,5,0,0,0};
		state[2] = new int[]{0,9,8,0,0,0,0,6,0};
		state[3] = new int[]{8,0,0,0,6,0,0,0,3};
		state[4] = new int[]{4,0,0,8,0,3,0,0,1};
		state[5] = new int[]{7,0,0,0,2,0,0,0,6};
		state[6] = new int[]{0,6,0,0,0,0,2,8,0};
		state[7] = new int[]{0,0,0,4,1,9,0,0,5};
		state[8] = new int[]{0,0,0,0,8,0,0,7,9};*/
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
		
		/*
		state[0] = new int[]{5,3,5,2,6,9,7,8,1};
		state[1] = new int[]{6,8,2,5,7,1,4,9,3};
		state[2] = new int[]{1,9,7,8,3,4,5,6,2};
		state[3] = new int[]{8,2,6,1,9,5,3,4,7};
		state[4] = new int[]{3,7,4,6,8,2,9,1,5};
		state[5] = new int[]{9,5,1,7,4,3,6,2,8};
		state[6] = new int[]{5,1,9,3,2,6,8,7,4};
		state[7] = new int[]{2,4,8,9,5,7,1,3,6};
		state[8] = new int[]{7,6,3,4,1,8,2,5,9};*/
		
		/*
		System.out.println(value());
		LinkedList<Integer> minConflicts = new LinkedList<Integer>();
		findMinConflicts(new Coordinate(0,0), minConflicts);
		
		System.out.println(minConflicts.size());
		
		while(!minConflicts.isEmpty()) {
			System.out.print(minConflicts.poll() + " ");
		}
		System.out.println();*/
		//this.display();

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
				if(state[i][j] == 0) {
					System.out.printf("%" + columnSize + "s", "-");	//Display blank tiles
				}
				else {
					System.out.printf("%" + columnSize + "s", state[i][j]);	//Display board
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
