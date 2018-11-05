import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Display extends JPanel {
	public static final int TILE_SIZE = 80;
	private Puzzle puzzle;
	private Graphics2D g2d;
	
	public Display() {
	}
	
	public Display(Puzzle puzzle) {
		this.puzzle = puzzle;
	}
	
	public Puzzle getPuzzle() {
		return puzzle;
	}
	
	public void paint(Graphics g) {
		g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		drawBoard();
		drawNumbers();
	}
	
	private void drawBoard() {
		int n = puzzle.getN();
		int groupLineWidth = (int)(TILE_SIZE * 0.1);
	    //paint the board tiles
		g2d.setColor(Color.BLACK);
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < n; j++) {
	            if(i % 3 == 0) {
	            	g2d.fillRect((i * TILE_SIZE) - (groupLineWidth / 2), 0, groupLineWidth, n * TILE_SIZE);
	            }
	            else {
	            	g2d.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, n * TILE_SIZE);
	            }
	            if(j % 3 == 0) {
	            	g2d.fillRect(0, (j * TILE_SIZE) - (groupLineWidth / 2), n * TILE_SIZE, groupLineWidth);
	            }
	            else {
	            	g2d.drawLine(0, j * TILE_SIZE, n * TILE_SIZE, j * TILE_SIZE);
	            }
	        }
	    }
	}
	
	private void drawNumbers() {
		int[][] originalState = puzzle.getOriginalPuzzle().getState();
		int[][] state = puzzle.getState();
		int n = state.length;
		int fontSize = 80;
		int startingX, startingY;
		

		g2d.setColor(Color.BLACK);
		FontMetrics fm;
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(state[i][j] != 0) {
					if(originalState[i][j] != 0) {
						g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
					}
					else {
						g2d.setFont(new Font("Arial", Font.PLAIN, fontSize));
					}
					fm = getFontMetrics(g2d.getFont());
					String number = Integer.toString(state[i][j]);
					startingX = fm.stringWidth(number);
					startingY = fm.getHeight();
					g2d.drawString(Integer.toString(state[i][j]), (i * TILE_SIZE) + ((TILE_SIZE - startingX) / 2), (j * TILE_SIZE) + (int)((TILE_SIZE - startingY) / 1.2));
					//g2d.drawString(Integer.toString(state[i][j]), (i * TILE_SIZE) + (TILE_SIZE / 4), (j * TILE_SIZE) - (TILE_SIZE / 8));
				}
			}
		}
	}
	
	public static void display(Board b) {
		int n = b.getN(), groupSize = b.getGroupSize(), columnSize = Integer.toString(n).length() + 1;
		int[][] state = b.getState();
		System.out.print("  ");
		for(int i = 0; i < n; i++) {
			if(i % groupSize == 0 && i != 0) {
				System.out.printf("%" + columnSize + "s", "|");	//Display group separator
			}
			System.out.printf("%" + columnSize + "s", (char)('A' + i));	//Print column titles in a format
		}
		System.out.println();
		for(int i = 0; i < n; i++) {
			if(i % groupSize == 0) {
				for(int x = 0; x < n + (columnSize * n); x++) {
					if(x % (n - 1) == 1) {
						System.out.printf("+");	//Display group separator
					}
					else {
						System.out.printf("-");	//Display group separator
					}
				}
				System.out.println();
			}
			System.out.printf("%" + columnSize + "s", (i + 1) + "|");	 //Print column titles in a format
			for(int j = 0; j < n; j++) {
				if(j % groupSize == 0 && j != 0) {
					System.out.printf("%" + columnSize + "s", "|");	//Display group separator
				}
				if(state[i][j] == 0) {
					System.out.printf("%" + columnSize + "s", "-");	//Display blank tiles
				}
				else {
					System.out.printf("%" + columnSize + "s", state[i][j]);	//Display board
				}
				
			}
			System.out.printf(" %-" + columnSize + "s", "|" + (i + 1));	//Print column titles in a format
			System.out.println();
		}
		
		for(int x = 0; x < n + (columnSize * n); x++) {
			if(x % (n - 1) == 1) {
				System.out.printf("+");	//Display group separator
			}
			else {
				System.out.printf("-");	//Display group separator
			}
		}
		
		System.out.println();
		System.out.print("  ");
		for(int i = 0; i < n; i++) {
			if(i % groupSize == 0 && i != 0) {
				System.out.printf("%" + columnSize + "s", "|");	//Display group separator
			}
			System.out.printf("%" + columnSize + "s", (char)('A' + i));	//Print column titles in a format
		}
		System.out.println();
	}
}