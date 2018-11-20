import javax.swing.JFrame;
import java.awt.Insets;

public class Window {
	public Window(Display puzzleDisplay) {
		final int WINDOW_SIZE = Display.TILE_SIZE * puzzleDisplay.getPuzzle().getN();
		JFrame frame = new JFrame("Sudoku");
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(puzzleDisplay);
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		frame.setSize(WINDOW_SIZE + insets.left + insets.right, 
			WINDOW_SIZE + insets.top + insets.bottom);
		frame.setLocationRelativeTo(null);
	}
}