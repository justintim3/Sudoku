public class Main {
	public static void main(String[] args) {
		int n = 9;
		Puzzle puzzle = new Puzzle(n);
		Display display = new Display(puzzle);
		new Window(display);
		new Input(display);
		
		/*
		Board solvedState = generateSolvedState();
		Display.display(solvedState);
		
		Board puzzle = generatePuzzle(solvedState);
		Display.display(solvedState);
		
		Board solution = solvePuzzle(puzzle);
		Display.display(solution);*/
		
		//generatePuzzleData(n);

	}
	
	public static Board generateSolvedState() {
		Board board = new Board(9);
		//board.display();
		return board;
	}
	
	public static Board generatePuzzle(Board board) {
		Generator g = new Generator(board);
		Board puzzle = g.generatePuzzle();
		System.out.println("\nFilled in tiles: " + g.filledTiles);
		//puzzle.display();
		return puzzle;
	}
	
	public static Board solvePuzzle(Board puzzle) {
		Solver s = new Solver(puzzle);
		System.out.println();
		s.solve();
		return s.getSolution();
	}
	
	private static void generatePuzzleData(int n) {
		int puzzles = 0;
		int[] puzzleTallies = new int[n * n];
		long solvedStateStartTime, solvedStateEndTime;
		long puzzleGenStartTime, puzzleGenEndTime;
		long puzzleSolvingStartTime, puzzleSolvingEndTime;
		double totalSolvedStateGenTime = 0,  totalPuzzleGenTime = 0, totalPuzzleSolvingTime = 0;
		double averageSolvedStateGenTime = 0, averagePuzzleGenTime = 0, averagePuzzleSolvingTime = 0;
		double averageSeconds, totalSeconds = 0, puzzlesGenSolvedPerSec;
		//long startTime = System.nanoTime();
		do {
			solvedStateStartTime = System.nanoTime();
			Board board = new Board(n);
			solvedStateEndTime = System.nanoTime();
			
			puzzleGenStartTime = System.nanoTime();
			Generator g = new Generator(board);
			Board puzzle = g.generatePuzzle();
			puzzleGenEndTime = System.nanoTime();
			
			puzzleSolvingStartTime = System.nanoTime();
			Solver s = new Solver(puzzle);
			s.solve();
			puzzleSolvingEndTime = System.nanoTime();
			
			puzzleTallies[g.filledTiles - 1]++;
			puzzles++;
			
			totalSolvedStateGenTime += totalSeconds(solvedStateStartTime, solvedStateEndTime);
			totalPuzzleGenTime += totalSeconds(puzzleGenStartTime, puzzleGenEndTime);
			totalPuzzleSolvingTime += totalSeconds(puzzleSolvingStartTime, puzzleSolvingEndTime);
			totalSeconds = totalSolvedStateGenTime + totalPuzzleGenTime + totalPuzzleSolvingTime;
			
			averageSolvedStateGenTime = totalSolvedStateGenTime / puzzles;
			averagePuzzleGenTime = totalPuzzleGenTime / puzzles;
			averagePuzzleSolvingTime = totalPuzzleSolvingTime / puzzles;
			averageSeconds = averageSolvedStateGenTime + averagePuzzleGenTime + averagePuzzleSolvingTime;
			puzzlesGenSolvedPerSec = 1 / averageSeconds;
			
			if(puzzles % 1000 == 0) {
				System.out.println();
				printTallies(puzzleTallies);
				System.out.println("Puzzles generated: " + puzzles);
				System.out.println("Average solved state generation time (s): " + averageSolvedStateGenTime);
				System.out.println("Average puzzle generation time (s): " + averagePuzzleGenTime);
				System.out.println("Average puzzle solving time (s): " + averagePuzzleSolvingTime);
				System.out.println("Average time elapsed (s): " + averageSeconds);
				System.out.println("Puzzles generated and solved per second: " + puzzlesGenSolvedPerSec);
				System.out.println("Total time elapsed (s): " + totalSeconds);
			}
		} while(true);
	}
	
	private static double totalSeconds(long startTime, long endTime){
		return (endTime - startTime) / 1000000000.0;
	}
	
	private static void printTallies(int[] tallies) {
		int count = 0;
		for(int i = 0; i < tallies.length; i++) {
			if(tallies[i] != 0) {
				System.out.println((i + 1) + " clue puzzle tally: " + tallies[i]);
				count += tallies[i];
			}
		}
		System.out.println("Total puzzles generated : " + count);
	}
}