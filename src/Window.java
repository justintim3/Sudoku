import javax.swing.JFrame;

public class Window {
	//default border size for all operating systems
	private static int WINDOW_SIZE;
	private static int TOPBORDER = 50;
	private static int BOTTOMBORDER = 50;
	private static int LEFTBORDER = 50;
	private static int RIGHTBORDER = 50;
	private static String OS = System.getProperty("os.name").toLowerCase();

	public Window(Display puzzleDisplay) {
		if(isWindows()) {
			TOPBORDER = 26;
			BOTTOMBORDER = 25;
			LEFTBORDER = 3;
			RIGHTBORDER = 3;
		}
		else if(isMac()) {
			TOPBORDER = 22;
			BOTTOMBORDER = 0;
			LEFTBORDER = 0;
			RIGHTBORDER = 0;
		}
		//System.out.println(OS);
		JFrame frame = new JFrame("Sudoku");
		WINDOW_SIZE = Display.TILE_SIZE * puzzleDisplay.getPuzzle().getN();
		frame.setSize(WINDOW_SIZE + LEFTBORDER + RIGHTBORDER
			,WINDOW_SIZE + TOPBORDER + BOTTOMBORDER);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(puzzleDisplay);
		frame.setVisible(true);
	}

	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}

	private boolean isMac() {
		return (OS.indexOf("mac") >= 0);
	}
}