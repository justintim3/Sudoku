import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator {
	private int[][] state;
	public int filledTiles = 0;
	private final int n;
	
	public Generator(Board b) {
		state = b.getState();
		n = state.length;
	}
	
	public Board generatePuzzle() {
		Random rand = ThreadLocalRandom.current();
		int x, y, randInt, solutions, temp;
		LinkedList<Integer> remainingBlankAttempts = new LinkedList<Integer>();
		for(int i = 0; i < n*n; i++) {
			remainingBlankAttempts.add(i);
		}
			
		for(int i = 0; i < n*n; i++) {
			randInt = rand.nextInt(remainingBlankAttempts.size());
			x = remainingBlankAttempts.get(randInt) % n;
			y = remainingBlankAttempts.get(randInt) / n;
			//y = rand.nextInt(n);
			if(state[x][y] != 0) {
				temp = state[x][y];
				state[x][y] = 0;
				Solver s = new Solver(new Board(state));
				solutions = s.numberOfSolutions();
				if(solutions > 1) {
					state[x][y] = temp;
					filledTiles++;
					//System.out.println("Multiple Solutions");
				}
				/*else if(solutions == 1) {
					//System.out.println("Single Solutions");
				}*/
			}
			remainingBlankAttempts.remove(randInt);
		}
		//System.out.println("Filled in tiles: " + filledTiles);
		return new Board(state);
	}
}
