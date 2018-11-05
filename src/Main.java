public class Main {
	public static void main(String[] args) {
		Puzzle puzzle = new Puzzle();
		Display display = new Display(puzzle);
		new Window(display);
		Input mouseListener = new Input(display);
		//new KeyInput(mouseListener);
		
		
		/*
		Board solvedState = generateSolvedState();
		Display.display(solvedState);
		
		Board puzzle = generatePuzzle(solvedState);
		Display.display(solvedState);
		
		Board solution = solvePuzzle(puzzle);
		Display.display(solution);
		
		generatePuzzleData();*/
		
		
		
		
		
		
		/*
		long startTime, endTime, maxStartTime, count = 0, maxCount = 0, puzzles = 0;
		double seconds = 0, totalSeconds = 0;
		Display d = new Display();
		startTime = System.nanoTime();
		maxStartTime = System.nanoTime();
		
		int[] puzzleTallies = new int[60];
		
		//while((System.nanoTime() - startTime) / 1000000000.0 < 10) {
		//while(count < 100) {
		while(true) {
			count++;
			maxCount++;
			Board newGame = new Board(9);
			//d.display(newGame);
			//newGame.display();
			//System.out.println((System.nanoTime() - startTime) / 1000000000.0);
			Solver s = new Solver(newGame);
			//s.solve();
			//s.display();
			
			Generator g = new Generator(newGame);
			Board puzzle = g.generatePuzzle();
			puzzleTallies[g.filledTiles - 17]++;
			//if(true) {
			if(g.filledTiles < 22) {
				puzzles++;
				System.out.println("\nFilled in tiles: " + g.filledTiles);
				puzzle.display();
				System.out.println();
				s.solve();
				s.display();
				endTime = System.nanoTime();
				System.out.println("Puzzles generated: " + count);
				System.out.println("Seconds: " + (endTime - startTime) / 1000000000.0);
				System.out.println("Total 21 puzzles: " + puzzles);
				System.out.println("Total puzzles generated: " + maxCount);
				totalSeconds = (endTime - maxStartTime) / 1000000000.0;
				System.out.println("Total seconds: " + totalSeconds);
				System.out.println("Average puzzles generated per second: " + maxCount / totalSeconds);
				System.out.println("Average puzzles generated per 21 puzzle generated: " + maxCount / puzzles);
				System.out.println("Average time elapsed per 21 puzzle generated: " + totalSeconds / puzzles);
				printTallies(puzzleTallies);
				startTime = System.nanoTime();
				count = 0;
				if(g.filledTiles < 21) {
					break;
				}
			}
		}*/
	}
	/*
	public static runPuzzle() {
		Puzzle puzzle = new Puzzle();
		Display display = new Display(puzzle);
		Window window = new Window(display);
		MouseInput mouseListener = new MouseInput(display);
	}*/
	
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
	
	private static void generatePuzzleData() {
		int puzzles = 0;
		int[] puzzleTallies = new int[60];
		long solvedStateStartTime, solvedStateEndTime;
		long puzzleGenStartTime, puzzleGenEndTime;
		long puzzleSolvingStartTime, puzzleSolvingEndTime;
		double totalSolvedStateGenTime = 0,  totalPuzzleGenTime = 0, totalPuzzleSolvingTime = 0;
		double averageSolvedStateGenTime = 0, averagePuzzleGenTime = 0, averagePuzzleSolvingTime = 0;
		double averageSeconds, totalSeconds = 0;
		//long startTime = System.nanoTime();
		do {
			solvedStateStartTime = System.nanoTime();
			Board board = new Board(9);
			solvedStateEndTime = System.nanoTime();
			
			puzzleGenStartTime = System.nanoTime();
			Generator g = new Generator(board);
			Board puzzle = g.generatePuzzle();
			puzzleGenEndTime = System.nanoTime();
			
			puzzleSolvingStartTime = System.nanoTime();
			Solver s = new Solver(puzzle);
			s.solve();
			puzzleSolvingEndTime = System.nanoTime();
			
			puzzleTallies[g.filledTiles - 17]++;
			puzzles++;
			
			
			totalSolvedStateGenTime += totalSeconds(solvedStateStartTime, solvedStateEndTime);
			totalPuzzleGenTime += totalSeconds(puzzleGenStartTime, puzzleGenEndTime);
			totalPuzzleSolvingTime += totalSeconds(puzzleSolvingStartTime, puzzleSolvingEndTime);
			totalSeconds = totalSolvedStateGenTime + totalPuzzleGenTime + totalPuzzleSolvingTime;
			
			averageSolvedStateGenTime = totalSolvedStateGenTime / puzzles;
			averagePuzzleGenTime = totalPuzzleGenTime / puzzles;
			averagePuzzleSolvingTime = totalPuzzleSolvingTime / puzzles;
			averageSeconds = averageSolvedStateGenTime + averagePuzzleGenTime + averagePuzzleSolvingTime;
			
			if(puzzles % 1000 == 0) {
				System.out.println();
				printTallies(puzzleTallies);
				System.out.println("Puzzles generated: " + puzzles);
				System.out.println("Average solved state generation time (s): " + averageSolvedStateGenTime);
				System.out.println("Average puzzle generation time (s): " + averagePuzzleGenTime);
				System.out.println("Average puzzle solving time (s): " + averagePuzzleSolvingTime);
				System.out.println("Average time elapsed (s): " + averageSeconds);
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
				System.out.println((i + 17) + " clue puzzle tally: " + tallies[i]);
				count += tallies[i];
			}
		}
		System.out.println("Total puzzles generated : " + count);
	}
}