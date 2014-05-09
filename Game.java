import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel{
	static int HEIGHT = 400;
	static int WIDTH = 400;
	static int TILE_SIZE = 100;
	static int[] keys = new int[]{
		KeyEvent.VK_A, 
		KeyEvent.VK_S, 
		KeyEvent.VK_D, 
		KeyEvent.VK_F
		};
	static Tile[][] board = new Tile[50][4];
	static boolean keypress = false;
	static int counter = 0;
	static long start = -1;
	static int direction_pressed = -1;
	static ProgressBar p = new ProgressBar(0, 0, Color.RED, WIDTH);
	public Game() {
		for (int i = 0; i < board.length; i++) {
			int random = (int) (Math.random() * 4);
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Tile(j * TILE_SIZE, (i - 46) * TILE_SIZE, j == random, (int) (Math.random() * 4));
			}
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for (Tile[] t: board) {
			for (Tile t1: t) {
				t1.draw(g2d);
			}
		}
		p.draw(g2d);
		g.setColor(Color.RED);
		Font f = new Font("Arial", Font.BOLD, 72);
		int length = (int) f.getStringBounds("0.000\"", g2d.getFontRenderContext()).getWidth();
		g.setFont(f);
		g.drawString(start == -1 ? "0.000\"": (double) (System.currentTimeMillis() - start) / 1000 + "\"", (WIDTH - length) / 2, 75);
	}
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Piano Tiles in Java by Aditya Chopra");
		final Game game = new Game();
		frame.add(game);
		Container c = frame.getContentPane();
		Dimension d = new Dimension(WIDTH,HEIGHT);
		c.setPreferredSize(d);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int ID = e.getKeyCode();
                if (ID == KeyEvent.VK_UP) direction_pressed = 0;
                if (ID == KeyEvent.VK_RIGHT) direction_pressed = 1;
                if (ID == KeyEvent.VK_DOWN) direction_pressed = 2;
                if (ID == KeyEvent.VK_LEFT) direction_pressed = 3;
                if (ID == KeyEvent.VK_ESCAPE) System.exit(0);
                for (int i = 0; i < keys.length; i++) {
                	if (keys[i] == ID) {
                		if (start == -1) start = System.currentTimeMillis();
                		ID = i;
                		break;
                	}
                }
                if (!board[board.length - 1 - counter][ID].getBlack()) lose();
                else {
                	keypress = true;
                }
            }
            public void keyReleased(KeyEvent e) {
            	int ID  = e.getKeyCode();
            	if (ID == KeyEvent.VK_UP || ID == KeyEvent.VK_RIGHT || ID == KeyEvent.VK_DOWN || ID == KeyEvent.VK_LEFT) direction_pressed = -1;
            }
        });
		while (true) {
			game.update();
			Thread.sleep(1);
		}
	}
	private void update() {
		repaint();
		if (counter == 50) {
			System.out.println("YOUR TIME WAS: " + (double) (System.currentTimeMillis() - start) / 1000 + " SECONDS");
			System.exit(0);
		}
		for (Tile[] t: board) {
			for (Tile t1: t) {
				if (t1.getY() > HEIGHT - TILE_SIZE + 1) t1.setColor(Color.GRAY);
				t1.update();
			}
		}
		if (keypress) {
			for (Tile[] t: board) {
				for (Tile t1: t) {
					t1.setdY(1);
				}
			}
			counter++;
			keypress = false;
		}
		if (counter == board.length || board[board.length - 1 - counter][0].getY() > HEIGHT - TILE_SIZE) {
			for (Tile[] t: board) {
				for (Tile t1: t) {
					t1.setdY(0);
				}
			}
			keypress = false;
		}
		p.update(counter / 50.0);
	}
	private static void lose() {
		start -= 250;
		System.out.println("WRONG TILE! (+ .25 seconds)");
	}
}