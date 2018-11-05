import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener, KeyListener {
	private Display display;
	private int[][] originalPuzzleState;
	private int[][] state;
	private int x, y;
	
	public Input(Display display) {
        this.display = display;
        display.addMouseListener(this);
        display.addKeyListener(this);
        display.setFocusable(true);
        
        this.originalPuzzleState = display.getPuzzle().getOriginalPuzzle().getState();
        this.state = display.getPuzzle().getState();
        x = -1;
        y = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {    	
    }

	@Override
	public void mousePressed(MouseEvent e) {
		if(true) {
		//if(!display.isAnimating() && display.getGame().isRunning()) {
			x = e.getX() / Display.TILE_SIZE;
	        y = e.getY() / Display.TILE_SIZE;
	        System.out.println("mouse click: " + e.getX() + ", " + e.getY());
	        System.out.println("highlightX, highlightY: " + x + " " + y);
	        display.setX(x);
	        display.setY(y);

	        //display.getGame().selectTile(new BoardCoordinate(x, y), display);
	        display.repaint();    //after everyclick repaint
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
		if(x != -1 && y != -1 && originalPuzzleState[x][y] == 0) {
			char charTyped;
			charTyped = e.getKeyChar();
			if(charTyped > '0' && charTyped <= '9') {
				System.out.println(charTyped);
				display.getPuzzle().setTile(x, y, Character.getNumericValue(charTyped));
				System.out.println(state[x][y]);
				System.out.println(x + "       " + y);
				display.repaint();
			}
		}
    }
	
	@Override
	public void keyTyped(KeyEvent e) {/*
		System.out.println(e.getKeyChar());
		if(x != -1 && y != -1) {
			char charTyped;
			charTyped = e.getKeyChar();
			if(charTyped > '0' && charTyped <= '9') {
				display.getPuzzle().setTile(x, y, Character.getNumericValue(charTyped));
			}
			System.out.println(charTyped);
		}
		display.repaint();*/
    }
	
	@Override
	public void keyReleased(KeyEvent e) {
    }
}