import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{
	private Display display;
	
	public KeyInput(MouseInput mouseInput) {
        this.display = display;
        display.addKeyListener(this);
    }
	
	@Override
    public void keyPressed(KeyEvent e) {    	
    }
	
	@Override
	public void keyTyped(KeyEvent e) {    	
    }
	
	@Override
	public void keyReleased(KeyEvent e) {    	
    }
}
