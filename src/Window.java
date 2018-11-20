import javax.swing.JFrame;
import java.awt.Insets;

public class Window {
	public Window(Display puzzleDisplay) {
		final int BOARD_SIZE = Display.TILE_SIZE * puzzleDisplay.getPuzzle().getN();
		JFrame frame = new JFrame("Sudoku");
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(puzzleDisplay);
		frame.setVisible(true);
		Insets insets = frame.getInsets();
		frame.setSize(BOARD_SIZE + insets.left + insets.right, 
			BOARD_SIZE + insets.top + insets.bottom);
		frame.setLocationRelativeTo(null);
	}
}