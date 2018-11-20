import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener, KeyListener {
	private Display display;
	private int[][] originalPuzzleState;
	private Puzzle puzzle;
	private int x, y;
	
	public Input(Display display) {
        this.display = display;
        display.addMouseListener(this);
        display.addKeyListener(this);
        display.setFocusable(true);
        
        this.originalPuzzleState = display.getPuzzle().getOriginalPuzzle().getState();
        this.puzzle = display.getPuzzle();
        x = -1;
        y = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {    	
    }

	@Override
	public void mousePressed(MouseEvent e) {
		if(true) {
			x = e.getX() / Display.TILE_SIZE;
	        y = e.getY() / Display.TILE_SIZE;
	        display.setX(x);
	        display.setY(y);
	        display.repaint();    //after every click repaint
    	}
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
    public void keyPressed(KeyEvent e) {
		if(!puzzle.isSolved()) {
			char charTyped = e.getKeyChar();
			if(charTyped == 's') {
				Solver s = new Solver(puzzle.getOriginalPuzzle());
				s.solve();
				puzzle.setState(s.getSolution().getState());
				display.repaint();
			}
			else if(x != -1 && y != -1 && originalPuzzleState[x][y] == 0) {
				if(charTyped > '0' && charTyped <= '9') {
					puzzle.setTile(x, y, Character.getNumericValue(charTyped));
				}
				else if(charTyped == 8) {
					puzzle.setTile(x, y, 0);
				}
				display.repaint();
			}
			puzzle.checkSolution();
		}
    }
	
	@Override
	public void keyTyped(KeyEvent e) {
    }
	
	@Override
	public void keyReleased(KeyEvent e) {
    }
}